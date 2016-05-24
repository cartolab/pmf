package es.icarto.gvsig.importer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestHeader {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
	Header header = new Header();
	header.setFromRules(SetUp.setup());
	HeaderField hf = header.addFromSource("name", 0);

	assertFalse(hf.isNotDefinedField());

	assertEquals(1, header.getFields().size());
	assertTrue(header.getFields().get(0) == hf);
	assertEquals("name", hf.getActualName());
	assertEquals(0, hf.getActualPos());
    }

}

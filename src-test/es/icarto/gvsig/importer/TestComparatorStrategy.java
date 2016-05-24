package es.icarto.gvsig.importer;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestComparatorStrategy {

    private List<HeaderField> fields;

    @Before
    public void setUp() {
	fields = SetUp.setup();
    }

    @Test
    public void test() {
	ComparatorStrategy c = new ComparatorStrategy();

	HeaderField h = c.find(fields, "LnG", -1);
	assertTrue(h.getRuleNames().contains("lng"));
    }
}

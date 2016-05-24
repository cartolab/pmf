package es.icarto.gvsig.importer;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestFoo {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testHeader() throws InvalidFormatException, IOException {
	File file = new File("/tmp/ejemplo.xlsx");

	ImportManager importManager = new ImportManager();

	XLS xls = new XLS(file);
	xls.setHeaderLine(XLS.FIRST_NOT_EMPTY);

	importManager.setReader(xls);
	Header header = new Header();
	header.setFromRules(SetUp.setup());
	importManager.setHeader(header);

	importManager.readHeader();

	assertEquals("name", header.getFields().get(0).getActualName());
	assertEquals(0, header.getFields().get(0).getActualPos());
	assertEquals("x", header.getFields().get(1).getActualName());
	assertEquals(1, header.getFields().get(1).getActualPos());
	assertEquals("long", header.getFields().get(2).getActualName());
	assertEquals(2, header.getFields().get(2).getActualPos());

	importManager.processFile(); // TODO
    }

}

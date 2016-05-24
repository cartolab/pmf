package es.icarto.gvsig.importer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import javax.swing.table.DefaultTableModel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Before;
import org.junit.Test;

public class TestXLS {

    private XLS xls;

    @Before
    public void setUp() throws InvalidFormatException, IOException {
	final File file = new File("/tmp/ejemplo.xlsx");
	xls = new XLS(file);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetHeaderLineThrowsException()
	    throws InvalidFormatException, IOException {

	xls.setHeaderLine(-5);
    }

    @Test
    public void testSetHeaderLineNotThrowsException()
	    throws InvalidFormatException, IOException {

	xls.setHeaderLine(XLS.FIRST_NOT_EMPTY);
	xls.setHeaderLine(XLS.NO_HEADER);
	xls.setHeaderLine(5);
	assertTrue(true);
    }

    @Test
    public void testGetValues() {

	xls.getSimpleHeader();
	DefaultTableModel values = xls.getValues();

	assertEquals("id", values.getColumnName(0));
	assertEquals("x", values.getColumnName(1));
	assertEquals("y", values.getColumnName(2));

	assertEquals("a", values.getValueAt(0, 0));
	assertEquals("12.55", values.getValueAt(1, 2));

    }

}

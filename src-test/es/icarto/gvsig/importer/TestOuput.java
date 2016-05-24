package es.icarto.gvsig.importer;

import static org.junit.Assert.assertEquals;

import javax.swing.table.DefaultTableModel;

import org.junit.Before;
import org.junit.Test;

public class TestOuput {

    private Output output;

    @Before
    public void setUp() throws Exception {
	output = new Output();
    }

    @Test
    public void testComunidad() {
	DefaultTableModel table = new DefaultTableModel(1, 4);
	table.setValueAt("foo", 0, 3);
	output.reorder(table);
	assertEquals("foo", table.getValueAt(0, 3));

	table = new DefaultTableModel(1, 4);
	table.setValueAt("comunidades", 0, 3);
	output.reorder(table);
	assertEquals("comunidades", table.getValueAt(0, 3));

	table = new DefaultTableModel(2, 4);
	table.setValueAt("comunidades", 0, 3);
	table.setValueAt("foo", 1, 3);
	output.reorder(table);
	assertEquals("comunidades", table.getValueAt(0, 3));
	assertEquals("foo", table.getValueAt(1, 3));

	table = new DefaultTableModel(2, 4);
	table.setValueAt("foo", 0, 3);
	table.setValueAt("comunidades", 1, 3);
	output.reorder(table);
	assertEquals("comunidades", table.getValueAt(0, 3));
	assertEquals("foo", table.getValueAt(1, 3));

	table = new DefaultTableModel(6, 4);
	table.setValueAt("foo1", 0, 3);
	table.setValueAt("comunidades", 1, 3);
	table.setValueAt("foo2", 2, 3);
	table.setValueAt("comunidades", 3, 3);
	table.setValueAt("comunidades", 4, 3);
	table.setValueAt("foo3", 5, 3);
	output.reorder(table);
	assertEquals("comunidades", table.getValueAt(0, 3));
	assertEquals("comunidades", table.getValueAt(1, 3));
	assertEquals("comunidades", table.getValueAt(2, 3));
	assertEquals("foo1", table.getValueAt(3, 3));
	assertEquals("foo2", table.getValueAt(4, 3));
	assertEquals("foo3", table.getValueAt(5, 3));
    }

    @Test
    public void testVivienda() {

	DefaultTableModel table = new DefaultTableModel(1, 4);
	table.setValueAt("informacion_general", 0, 3);
	output.reorder(table);
	assertEquals("informacion_general", table.getValueAt(0, 3));

	table = new DefaultTableModel(2, 4);
	table.setValueAt("informacion_general", 0, 3);
	table.setValueAt("foo", 1, 3);
	output.reorder(table);
	assertEquals("informacion_general", table.getValueAt(0, 3));
	assertEquals("foo", table.getValueAt(1, 3));

	table = new DefaultTableModel(2, 4);
	table.setValueAt("foo", 0, 3);
	table.setValueAt("informacion_general", 1, 3);
	output.reorder(table);
	assertEquals("informacion_general", table.getValueAt(0, 3));
	assertEquals("foo", table.getValueAt(1, 3));

	table = new DefaultTableModel(6, 4);
	table.setValueAt("foo1", 0, 3);
	table.setValueAt("informacion_general", 1, 3);
	table.setValueAt("foo2", 2, 3);
	table.setValueAt("informacion_general", 3, 3);
	table.setValueAt("informacion_general", 4, 3);
	table.setValueAt("foo3", 5, 3);
	output.reorder(table);
	assertEquals("informacion_general", table.getValueAt(0, 3));
	assertEquals("informacion_general", table.getValueAt(1, 3));
	assertEquals("informacion_general", table.getValueAt(2, 3));
	assertEquals("foo1", table.getValueAt(3, 3));
	assertEquals("foo2", table.getValueAt(4, 3));
	assertEquals("foo3", table.getValueAt(5, 3));

	table = new DefaultTableModel(7, 4);
	table.setValueAt("foo1", 0, 3);
	table.setValueAt("informacion_general", 1, 3);
	table.setValueAt("foo2", 2, 3);
	table.setValueAt("informacion_general", 3, 3);
	table.setValueAt("informacion_general", 4, 3);
	table.setValueAt("comunidades", 5, 3);
	table.setValueAt("foo3", 6, 3);
	output.reorder(table);
	assertEquals("comunidades", table.getValueAt(0, 3));
	assertEquals("informacion_general", table.getValueAt(1, 3));
	assertEquals("informacion_general", table.getValueAt(2, 3));
	assertEquals("informacion_general", table.getValueAt(3, 3));
	assertEquals("foo1", table.getValueAt(4, 3));
	assertEquals("foo2", table.getValueAt(5, 3));
	assertEquals("foo3", table.getValueAt(6, 3));

    }

    public static void main(String[] args) {
	DefaultTableModel table = new DefaultTableModel(6, 4);
	Output output = new Output();

	table = new DefaultTableModel(7, 4);
	table.setValueAt("foo1", 0, 3);
	table.setValueAt("informacion_general", 1, 3);
	table.setValueAt("foo2", 2, 3);
	table.setValueAt("informacion_general", 3, 3);
	table.setValueAt("informacion_general", 4, 3);
	table.setValueAt("comunidades", 5, 3);
	table.setValueAt("foo3", 6, 3);

	output.process(table);
    }

}

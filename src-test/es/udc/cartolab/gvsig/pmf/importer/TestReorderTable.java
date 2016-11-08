package es.udc.cartolab.gvsig.pmf.importer;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.icarto.gvsig.commons.utils.Field;
import es.icarto.gvsig.importer.ImporterTM;

public class TestReorderTable {

    private Field comunidades;
    private Field viviendas;
    private Field otherField;
    private PMFOutput output;
    private ImporterTM table;

    @Before
    public void setUp() throws Exception {
	output = new PMFOutput();

	comunidades = new Field("comunidades");
	viviendas = new Field("informacion_general");
	otherField = new Field("other_field");

	table = new ImporterTM();
	table.addColumn("Código");
	table.addColumn("Capa destino");
    }

    @Test
    public void testEmptyTable() {
	output.reorder(table);
	assertEquals(0, table.getRowCount());
    }

    @Test
    public void testOneComunidad() {
	table.addRow(new Object[] { "foo", comunidades });

	output.reorder(table);

	assertEquals(1, table.getRowCount());
	assertEquals("foo", table.getCode(0));
	assertEquals(comunidades, table.getTarget(0));
    }

    @Test
    public void testOneVivienda() {
	table.addRow(new Object[] { "foo", viviendas });

	output.reorder(table);

	assertEquals(1, table.getRowCount());
	assertEquals("foo", table.getCode(0));
	assertEquals(viviendas, table.getTarget(0));
    }

    @Test
    public void testOneOther() {
	table.addRow(new Object[] { "foo", otherField });

	output.reorder(table);

	assertEquals(1, table.getRowCount());
	assertEquals("foo", table.getCode(0));
	assertEquals(otherField, table.getTarget(0));
    }

    @Test
    public void testOnlyComunidadesCorrectlySorted() {
	table.addRow(new Object[] { "a", comunidades });
	table.addRow(new Object[] { "e", comunidades });
	table.addRow(new Object[] { "c", comunidades });
	table.addRow(new Object[] { "b", comunidades });
	table.addRow(new Object[] { "d", comunidades });

	output.reorder(table);

	assertEquals(5, table.getRowCount());
	assertEquals(comunidades, table.getTarget(0));

	assertEquals("a", table.getCode(0));
	assertEquals("b", table.getCode(1));
	assertEquals("c", table.getCode(2));
	assertEquals("d", table.getCode(3));
	assertEquals("e", table.getCode(4));
    }

    @Test
    public void testOnlyViviendasCorrectlySorted() {
	table.addRow(new Object[] { "e", viviendas });
	table.addRow(new Object[] { "c", viviendas });
	table.addRow(new Object[] { "b", viviendas });
	table.addRow(new Object[] { "d", viviendas });
	table.addRow(new Object[] { "a", viviendas });

	output.reorder(table);

	assertEquals(5, table.getRowCount());
	assertEquals(viviendas, table.getTarget(0));

	assertEquals("a", table.getCode(0));
	assertEquals("b", table.getCode(1));
	assertEquals("c", table.getCode(2));
	assertEquals("d", table.getCode(3));
	assertEquals("e", table.getCode(4));
    }

    @Test
    public void testOnlyOthersCorrectlySorted() {
	table.addRow(new Object[] { "a", otherField });
	table.addRow(new Object[] { "b", otherField });
	table.addRow(new Object[] { "c", otherField });
	table.addRow(new Object[] { "d", otherField });
	table.addRow(new Object[] { "e", otherField });

	output.reorder(table);

	assertEquals(5, table.getRowCount());
	assertEquals(otherField, table.getTarget(0));

	assertEquals("a", table.getCode(0));
	assertEquals("b", table.getCode(1));
	assertEquals("c", table.getCode(2));
	assertEquals("d", table.getCode(3));
	assertEquals("e", table.getCode(4));
    }

    @Test
    public void testMixed() {
	table.addRow(new Object[] { "a", comunidades });
	table.addRow(new Object[] { "d", otherField });
	table.addRow(new Object[] { "b", viviendas });
	table.addRow(new Object[] { "b", otherField });
	table.addRow(new Object[] { "c", viviendas });
	table.addRow(new Object[] { "a", otherField });
	table.addRow(new Object[] { "e", viviendas });
	table.addRow(new Object[] { "b", comunidades });
	table.addRow(new Object[] { "e", comunidades });

	output.reorder(table);

	assertEquals(9, table.getRowCount());
	assertEquals(comunidades, table.getTarget(0));
	assertEquals(comunidades, table.getTarget(1));
	assertEquals(comunidades, table.getTarget(2));
	assertEquals(viviendas, table.getTarget(3));
	assertEquals(viviendas, table.getTarget(4));
	assertEquals(viviendas, table.getTarget(5));
	assertEquals(otherField, table.getTarget(6));
	assertEquals(otherField, table.getTarget(7));
	assertEquals(otherField, table.getTarget(8));

	assertEquals("a", table.getCode(0));
	assertEquals("b", table.getCode(1));
	assertEquals("e", table.getCode(2));
	assertEquals("b", table.getCode(3));
	assertEquals("c", table.getCode(4));
	assertEquals("e", table.getCode(5));
	assertEquals("a", table.getCode(6));
	assertEquals("b", table.getCode(7));
	assertEquals("d", table.getCode(8));
    }

}

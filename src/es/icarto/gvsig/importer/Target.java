package es.icarto.gvsig.importer;

import javax.swing.table.DefaultTableModel;

import es.icarto.gvsig.commons.utils.Field;

public interface Target {

    boolean matches(String value);

    boolean process(String value, DefaultTableModel table, int i);

    Field getField();

}

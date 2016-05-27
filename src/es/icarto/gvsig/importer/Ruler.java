package es.icarto.gvsig.importer;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import es.icarto.gvsig.commons.utils.Field;

public interface Ruler {
    void processValue(String id, DefaultTableModel table, int i);

    List<Field> getFields();
}

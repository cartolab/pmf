package es.icarto.gvsig.importer;

import javax.swing.table.DefaultTableModel;

public interface Ruler {

    void processValue(String id, DefaultTableModel table, int i);
}

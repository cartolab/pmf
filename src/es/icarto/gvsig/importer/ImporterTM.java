package es.icarto.gvsig.importer;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ImporterTM extends DefaultTableModel {
    public ImporterTM() {
	addColumn("Código");
    }

    @Override
    public boolean isCellEditable(int row, int column) {
	if (column == 0) {
	    return false;
	}
	return super.isCellEditable(row, column);
    }

}

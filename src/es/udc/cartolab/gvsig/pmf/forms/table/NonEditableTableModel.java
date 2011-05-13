package es.udc.cartolab.gvsig.pmf.forms.table;

import javax.swing.table.DefaultTableModel;

public class NonEditableTableModel extends DefaultTableModel {

    public NonEditableTableModel(Object[][] rows, Object[] columns) {
	super(rows, columns);
    }

    public boolean isCellEditable(int row, int column) {
	return false;
    }

}

package es.udc.cartolab.gvsig.pmf.importer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JTable;

import es.icarto.gvsig.importer.ImporterTM;

public class RemoveRowContextualMenu implements ImporterContextualMenu,
	ActionListener {

    private final JTable table;

    public RemoveRowContextualMenu(JTable table) {
	this.table = table;
    }

    @Override
    public boolean isVisible() {
	return table.getSelectedRowCount() > 0;
    }

    @Override
    public JMenuItem getMenuItem() {
	int numRows = table.getSelectedRowCount();
	String msg = numRows > 1 ? "Eliminar filas" : "Eliminar fila";
	JMenuItem menu = new JMenuItem(msg);
	menu.addActionListener(this);
	return menu;
    }

    @Override
    public boolean isEnabled() {
	return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	int numRows = table.getSelectedRows().length;
	ImporterTM model = (ImporterTM) table.getModel();
	for (int i = 0; i < numRows; i++) {
	    model.removeRow(table.getSelectedRow());
	}
	model.reCheckErrors();
    }

}

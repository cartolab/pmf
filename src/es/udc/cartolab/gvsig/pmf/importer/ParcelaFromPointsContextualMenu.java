package es.udc.cartolab.gvsig.pmf.importer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.JMenuItem;
import javax.swing.JTable;

import com.iver.cit.gvsig.fmap.core.IGeometry;

import es.icarto.gvsig.commons.utils.Field;
import es.icarto.gvsig.importer.ImporterTM;
import es.icarto.gvsig.importer.Ruler;

public class ParcelaFromPointsContextualMenu implements ImporterContextualMenu,
	ActionListener {

    private final JTable table;
    private Field parcelaField;

    public ParcelaFromPointsContextualMenu(JTable table, Ruler ruler) {
	this.table = table;

	for (Field f : ruler.getFields()) {
	    if (f.getKey().equals("parcelas")) {
		parcelaField = f;
	    }
	}
    }

    @Override
    public boolean isVisible() {
	if (table.getSelectedRowCount() < 3) {
	    return false;
	}

	final int[] selectedRows = table.getSelectedRows();
	ImporterTM model = (ImporterTM) table.getModel();

	boolean visible = true;
	for (int selectedRow : selectedRows) {
	    Field target = model.getTarget(selectedRow);
	    if (!target.getKey().equals("")) {
		visible = false;
		break;
	    }
	}

	return visible;
    }

    @Override
    public JMenuItem getMenuItem() {
	JMenuItem menu = new JMenuItem("Crear parcela desde puntos");
	menu.addActionListener(this);
	return menu;
    }

    @Override
    public boolean isEnabled() {
	return true;
    }

    public void removeRows() {
	int numRows = table.getSelectedRows().length;
	ImporterTM model = (ImporterTM) table.getModel();
	for (int i = 0; i < numRows; i++) {
	    model.removeRow(table.getSelectedRow());
	}
	model.reCheckErrors();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	final int[] selectedRows = table.getSelectedRows();
	ImporterTM model = (ImporterTM) table.getModel();

	Object[] data = new Object[model.getColumnCount()];

	setCodesInTheModel(selectedRows, model);
	insertID(data, selectedRows, model);
	Map<String, IGeometry> points = getPoints(selectedRows, model);
	ConfirmCroquisDialog confirmCroquisDialog = new ConfirmCroquisDialog(
		points);
	confirmCroquisDialog.openDialog();
	if (confirmCroquisDialog.isGood()) {
	    IGeometry poly = confirmCroquisDialog.getPoly();
	    insertPolyGeom(data, poly, model);
	    postProcess(model, selectedRows, data);
	}
    }

    private void setCodesInTheModel(int[] selectedRows, ImporterTM model) {
	for (int selectedRow : selectedRows) {
	    String code = model.getCode(selectedRow);
	    if (code.trim().isEmpty()) {
		code = model.getID(selectedRow);
		model.setCode(code, selectedRow);
	    }
	}
    }

    private void insertPolyGeom(Object[] data, IGeometry poly, ImporterTM model) {
	int column = -1;
	for (int i = 0; i < table.getColumnCount(); i++) {
	    if (table.getColumnName(i).equals("Geometría destino")) {
		column = i;
		break;
	    }
	}
	data[column] = poly;
    }

    private Map<String, IGeometry> getPoints(int[] selectedRows,
	    ImporterTM model) {
	SortedMap<String, IGeometry> points = new TreeMap<String, IGeometry>();

	for (int selectedRow : selectedRows) {
	    IGeometry g = model.getGeom(selectedRow);
	    String code = model.getCode(selectedRow);
	    points.put(code, g);
	}
	return points;
    }

    private void insertID(Object[] data, int[] selectedRows, ImporterTM model) {
	String aggID = "Creado a partir de los puntos: ";
	for (int selectedRow : selectedRows) {
	    String id = model.getID(selectedRow);
	    aggID = aggID + String.format("'%s, '", id);
	}

	int column = -1;
	for (int i = 0; i < table.getColumnCount(); i++) {
	    if (table.getColumnName(i).equals("id")) {
		column = i;
		break;
	    }
	}

	data[column] = aggID;
    }

    private void postProcess(ImporterTM model, int[] selectedRows, Object[] data) {
	// removeRows();
	int row = selectedRows[0];
	// model.insertRow(row, data);
	model.addRow(data);
	row = model.getRowCount() - 1;
	setField(model, row);
	model.reCheckErrors();
    }

    private void setField(ImporterTM model, int row) {
	int tablenameIdx = -1;
	for (int i = 0; i < model.getColumnCount(); i++) {
	    final Object o = model.getValueAt(0, i);
	    if (o instanceof Field) {
		tablenameIdx = i;
	    }

	}
	// To recalculate the code setValueAt instead of setTarget is used
	// model.setTarget(parcelaField, row);
	model.setValueAt(parcelaField, row, tablenameIdx);
    }

}

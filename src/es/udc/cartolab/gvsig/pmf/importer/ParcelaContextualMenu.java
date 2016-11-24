package es.udc.cartolab.gvsig.pmf.importer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JTable;

import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.iver.cit.gvsig.fmap.core.v02.FConverter;
import com.vividsolutions.jts.geom.Geometry;

import es.icarto.gvsig.commons.utils.Field;
import es.icarto.gvsig.importer.ImporterTM;
import es.icarto.gvsig.importer.Ruler;

public class ParcelaContextualMenu implements ImporterContextualMenu,
	ActionListener {

    private final JTable table;
    private Field parcelaField;

    public ParcelaContextualMenu(JTable table, Ruler ruler) {
	this.table = table;

	for (Field f : ruler.getFields()) {
	    if (f.getKey().equals("parcelas")) {
		parcelaField = f;
	    }
	}
    }

    @Override
    public boolean isVisible() {
	if (table.getSelectedRowCount() != 1) {
	    return false;
	}
	ImporterTM importer = (ImporterTM) table.getModel();
	int row = table.getSelectedRow();
	Field target = importer.getTarget(row);
	return target.getKey().equals("informacion_general");
    }

    @Override
    public boolean isEnabled() {
	return true;
    }

    @Override
    public JMenuItem getMenuItem() {
	JMenuItem menu = new JMenuItem("Crear Parcela Ficticia");
	menu.addActionListener(this);
	menu.setEnabled(isEnabled());
	return menu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	int row = table.getSelectedRow();
	ImporterTM model = (ImporterTM) table.getModel();

	Object[] data = new Object[model.getColumnCount()];

	int tablenameIdx = -1;
	for (int i = 0; i < model.getColumnCount(); i++) {
	    final Object o = model.getValueAt(row, i);
	    if (o instanceof Field) {
		tablenameIdx = i;
	    }
	    data[i] = o;
	}

	fixDataId(data);
	fixDataGeom(data);
	fixX(data);
	fixY(data);
	model.insertRow(row, data);

	model.setValueAt(parcelaField, row, tablenameIdx);
	model.reCheckErrors();
    }

    private void fixDataId(Object[] data) {
	int column = -1;
	for (int i = 0; i < table.getColumnCount(); i++) {
	    if (table.getColumnName(i).equals("id")) {
		column = i;
		break;
	    }
	}
	data[column] = "Creado desde: " + data[column].toString();
    }

    private void fixDataGeom(Object[] data) {

	int column = -1;
	for (int i = 0; i < table.getColumnCount(); i++) {
	    if (table.getColumnName(i).equals("Geometría destino")) {
		column = i;
		break;
	    }
	}
	IGeometry geom = (IGeometry) data[column];
	Geometry geomParcela = geom.toJTSGeometry().buffer(6);
	geom = FConverter.jts_to_igeometry(geomParcela);
	data[column] = geom;
    }

    private void fixX(Object[] data) {
	int column = -1;
	for (int i = 0; i < table.getColumnCount(); i++) {
	    if (table.getColumnName(i).equals("x")) {
		column = i;
		break;
	    }
	}
	data[column] = "";
    }

    private void fixY(Object[] data) {
	int column = -1;
	for (int i = 0; i < table.getColumnCount(); i++) {
	    if (table.getColumnName(i).equals("y")) {
		column = i;
		break;
	    }
	}
	data[column] = "";
    }
}

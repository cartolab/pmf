package es.udc.cartolab.gvsig.pmf.importer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.JMenuItem;
import javax.swing.JTable;

import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.iver.cit.gvsig.fmap.core.v02.FConverter;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;

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
	IGeometry p = insertPolyGeom(data, selectedRows, model);
	insertID(data, selectedRows, model);

	ConfirmCroquisDialog confirmCroquisDialog = new ConfirmCroquisDialog(p);
	confirmCroquisDialog.openDialog();
	if (confirmCroquisDialog.isGood()) {
	    postProcess(model, selectedRows, data);
	}
    }

    private void setCodesInTheModel(int[] selectedRows, ImporterTM model) {
	for (int selectedRow : selectedRows) {
	    String id = model.getID(selectedRow);
	    model.setCode(id, selectedRow);
	}
    }

    private IGeometry insertPolyGeom(Object[] data, int[] selectedRows,
	    ImporterTM model) {
	SortedMap<String, Geometry> jtsPointGeoms = new TreeMap<String, Geometry>();

	for (int selectedRow : selectedRows) {
	    IGeometry g = model.getGeom(selectedRow);
	    String id = model.getID(selectedRow);

	    model.setCode(id, selectedRow);
	    jtsPointGeoms.put(id, g.toJTSGeometry());
	}
	IGeometry p = points2polygon(jtsPointGeoms);

	int column = -1;
	for (int i = 0; i < table.getColumnCount(); i++) {
	    if (table.getColumnName(i).equals("Geometría destino")) {
		column = i;
		break;
	    }
	}

	data[column] = p;
	return p;
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

    private IGeometry points2polygon(SortedMap<String, Geometry> jtsPointGeoms) {

	GeometryFactory fact = new GeometryFactory();
	List<Coordinate> a = new ArrayList<Coordinate>();

	for (Geometry g : jtsPointGeoms.values()) {
	    a.add(g.getCoordinate());
	}

	// To close the ring
	a.add(a.get(0));

	LinearRing linear = fact.createLinearRing(a.toArray(new Coordinate[0]));
	Polygon poly = new Polygon(linear, null, fact);

	IGeometry gvPolygonGeom = FConverter.jts_to_igeometry(poly);

	return gvPolygonGeom;
    }

}

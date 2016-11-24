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

    public void removeRows(JTable table) {
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
	SortedMap<String, Geometry> jtsPointGeoms = new TreeMap<String, Geometry>();

	for (int selectedRow : selectedRows) {
	    IGeometry g = model.getGeom(selectedRow);
	    String key = model.getCode(selectedRow);
	    jtsPointGeoms.put(key, g.toJTSGeometry());
	}
	IGeometry p = points2polygon(jtsPointGeoms);

	Object[] data = new Object[model.getColumnCount()];
	int NUMERO_ROW_ENGADIR = 0;
	model.insertRow(NUMERO_ROW_ENGADIR, data);
	model.setGeom(p, NUMERO_ROW_ENGADIR);

	model.setTarget(parcelaField, NUMERO_ROW_ENGADIR);
	removeRows(table);
	model.reCheckErrors();

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

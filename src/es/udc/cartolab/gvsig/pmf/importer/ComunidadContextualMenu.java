package es.udc.cartolab.gvsig.pmf.importer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JTable;

import com.iver.cit.gvsig.fmap.core.FShape;
import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.iver.cit.gvsig.fmap.core.v02.FConverter;
import com.vividsolutions.jts.geom.Geometry;

import es.icarto.gvsig.commons.utils.Field;
import es.icarto.gvsig.importer.ImporterTM;
import es.icarto.gvsig.importer.Ruler;

public class ComunidadContextualMenu implements ImporterContextualMenu,
ActionListener {

    private final JTable table;
    private Field comunidadField;

    public ComunidadContextualMenu(JTable table, Ruler ruler) {
	this.table = table;
	for (Field f : ruler.getFields()) {
	    if (f.getKey().equals("comunidades")) {
		comunidadField = f;
	    }
	}
    }

    @Override
    public boolean isVisible() {
	if (table.getSelectedRowCount() == 1) {
	    return true;
	}
	return false;
    }

    @Override
    public boolean isEnabled() {
	return true;
    }

    @Override
    public JMenuItem getMenuItem() {
	JMenuItem menu = new JMenuItem("Crear Comunidad");
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

	fixDataGeom(data);
	model.insertRow(row, data);

	model.setValueAt(comunidadField, row, tablenameIdx);
	model.reCheckErrors();
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
	if (geom.getGeometryType() == FShape.POLYGON) {
	    Geometry geomPoint = geom.toJTSGeometry().getCentroid();
	    geom = FConverter.jts_to_igeometry(geomPoint);
	    data[column] = geom;
	}
    }
}
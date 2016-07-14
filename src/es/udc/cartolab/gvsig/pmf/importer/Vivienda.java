package es.udc.cartolab.gvsig.pmf.importer;

import javax.swing.table.DefaultTableModel;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

import es.icarto.gvsig.importer.JDBCUtils;
import es.icarto.gvsig.importer.RegionI;

public class Vivienda implements RegionI {

    public final static String tablename = "informacion_general";
    public final static String pkName = "cod_viv";

    // propietario/a de la vivienda
    public final static String nameName = "nom_produ";

    public final String pk;
    public final String name;
    private final Geometry geom;

    private Vivienda(String pk, String xStr, String yStr) {
	this.pk = pk;
	this.name = "";
	GeometryFactory factory = new GeometryFactory();
	Coordinate c = new Coordinate(Double.parseDouble(xStr),
		Double.parseDouble(yStr));
	this.geom = factory.createPoint(c);
    }

    @Override
    public String getPKField() {
	return pkName;
    }

    @Override
    public String getPKValue() {
	return pk;
    }

    public static Vivienda closestTo(String pointStr, RegionI region) {
	JDBCUtils jdbcUtils = new JDBCUtils();
	String closestWhere = String.format(" WHERE substr(%s, 1, 6) = '%s'",
		pkName, region.getPKValue());
	DefaultTableModel closest = jdbcUtils.closest(tablename, pointStr,
		closestWhere, pkName, nameName, "st_x(geom)", "st_y(geom)");

	if (closest.getRowCount() == 1) {
	    String pk = closest.getValueAt(0, 0).toString();
	    String name = closest.getValueAt(0, 1).toString();
	    String xStr = closest.getValueAt(0, 2).toString();
	    String yStr = closest.getValueAt(0, 3).toString();
	    return new Vivienda(pk, xStr, yStr);
	}
	return null;
    }

    public double distanceTo(Geometry point) {
	return this.geom.distance(geom);
    }

}

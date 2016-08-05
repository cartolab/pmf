package es.udc.cartolab.gvsig.pmf.importer.entities;

import java.sql.Connection;

import javax.swing.table.DefaultTableModel;

import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

import es.icarto.gvsig.importer.JDBCUtils;
import es.icarto.gvsig.importer.RegionI;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class Comunidad implements RegionI {

    public final static String tablename = "comunidades";
    public final static String pkName = "cod_com";

    // Representa el nombre del campo en la base de datos que contiene el nombre
    // común de la región (por ejemplo el nombre de la aldea)
    public final static String nameName = "nombre";

    public final String pk;
    public final String name;

    private Geometry geom;

    public Comunidad(String pk, String name) {
	this.pk = pk;
	this.name = name;
    }

    private Comunidad(String code, IGeometry geom) {
	this.pk = code;
	this.name = "";
	this.geom = geom.toJTSGeometry();
    }

    private Comunidad(String codCom, String xStr, String yStr) {
	this.pk = codCom;
	this.name = "";
	GeometryFactory factory = new GeometryFactory();
	Coordinate c = new Coordinate(Double.parseDouble(xStr),
		Double.parseDouble(yStr));
	this.geom = factory.createPoint(c);
    }

    public static Comunidad thatIntersectsWith(String pointStr) {
	Connection con = DBSession.getCurrentSession().getJavaConnection();
	JDBCUtils jdbcUtils = new JDBCUtils(con);
	DefaultTableModel result = jdbcUtils.intersects(tablename, pointStr,
		pkName, nameName);
	String pk = result.getValueAt(0, 0).toString();
	String name = result.getValueAt(0, 1).toString();
	return new Comunidad(pk, name);
    }

    public double distanceTo(IGeometry geom) {
	return this.geom.distance(geom.toJTSGeometry());
    }

    public double distanceTo(Geometry geom) {
	return this.geom.distance(geom);
    }

    public static Comunidad closestTo(String pointStr, RegionI region) {
	Connection con = DBSession.getCurrentSession().getJavaConnection();
	JDBCUtils jdbcUtils = new JDBCUtils(con);
	String closestWhere = String.format(" WHERE substr(%s, 1, 6) = '%s'",
		pkName, region.getPKValue());
	DefaultTableModel closest = jdbcUtils.closest(tablename, pointStr,
		closestWhere, pkName, nameName, "st_x(geom)", "st_y(geom)");

	if (closest.getRowCount() == 1) {
	    String pk = closest.getValueAt(0, 0).toString();
	    String name = closest.getValueAt(0, 1).toString();
	    String xStr = closest.getValueAt(0, 2).toString();
	    String yStr = closest.getValueAt(0, 3).toString();
	    return new Comunidad(pk, xStr, yStr);
	}
	return null;
    }

    @Override
    public String getPKField() {
	return pkName;
    }

    @Override
    public String getPKValue() {
	return pk;
    }

    public static Comunidad from(String pk, IGeometry geom) {
	return new Comunidad(pk, geom);
    }

    public static Comunidad from(String pk, String xStr, String yStr) {
	return new Comunidad(pk, xStr, yStr);
    }

}

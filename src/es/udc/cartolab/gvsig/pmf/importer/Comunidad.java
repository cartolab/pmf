package es.udc.cartolab.gvsig.pmf.importer;

import javax.swing.table.DefaultTableModel;

import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

import es.icarto.gvsig.importer.JDBCUtils;

public class Comunidad {

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

    public Comunidad(String code, IGeometry geom) {
	this.pk = code;
	this.name = "";
	this.geom = geom.toJTSGeometry();
    }

    public Comunidad(String codCom, String xStr, String yStr) {
	this.pk = codCom;
	this.name = "";
	GeometryFactory factory = new GeometryFactory();
	Coordinate c = new Coordinate(Double.parseDouble(xStr),
		Double.parseDouble(yStr));
	this.geom = factory.createPoint(c);
    }

    public static Comunidad thatIntersectsWith(String pointStr) {
	JDBCUtils jdbcUtils = new JDBCUtils();
	DefaultTableModel result = jdbcUtils.intersects(tablename, pointStr,
		pkName, nameName);
	String pk = result.getValueAt(0, 0).toString();
	String name = result.getValueAt(0, 1).toString();
	return new Comunidad(pk, name);
    }

    public double distanceTo(IGeometry geom) {
	return this.geom.distance(geom.toJTSGeometry());
    }

}

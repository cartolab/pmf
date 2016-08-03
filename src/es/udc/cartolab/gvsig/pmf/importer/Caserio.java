package es.udc.cartolab.gvsig.pmf.importer;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

import es.icarto.gvsig.importer.JDBCUtils;
import es.icarto.gvsig.importer.RegionI;
import es.udc.cartolab.gvsig.users.utils.DBSession;

// Un caserío es una comunidad pero que proviene de la capa de cartografía base
public class Caserio implements RegionI {

    private static final Logger logger = Logger.getLogger(Caserio.class);

    public final static String tablename = "caserios_comunidades";
    public final static String pkName = "cod_caseri";

    // Representa el nombre del campo en la base de datos que contiene el nombre
    // común de la región (por ejemplo el nombre de la aldea)
    public final static String nameName = "caserio";

    public final String pk;
    public final String name;

    private Geometry geom;

    public Caserio(String pk, String name) {
	this.pk = pk;
	this.name = name;
    }

    public Caserio(String code, IGeometry geom) {
	this.pk = code;
	this.name = "";
	this.geom = geom.toJTSGeometry();
    }

    private Caserio(String code, String xStr, String yStr) {
	this.pk = code;
	this.name = "";
	GeometryFactory factory = new GeometryFactory();
	Coordinate c = new Coordinate(Double.parseDouble(xStr),
		Double.parseDouble(yStr));
	this.geom = factory.createPoint(c);
    }

    /**
     * Retrieves the Caserio with pk = code from the database or null if not
     * exists a Caserio with that pk
     *
     */
    public static Caserio fromDB(String code) {
	DBSession session = DBSession.getCurrentSession();
	String where = String.format(" WHERE %s = '%s' ", pkName, code);
	Caserio c = null;
	try {
	    String[][] table = session.getTable(tablename, null, new String[] {
		    pkName, nameName, "st_x(geom)", "st_y(geom)" }, where,
		    null, false);
	    if ((table != null) && (table.length == 1)) {
		String nameName = table[0][1];
		String xStr = table[0][2];
		String yStr = table[0][3];
		c = new Caserio(code, xStr, yStr);
	    }
	} catch (SQLException e) {
	    logger.error(e.getStackTrace(), e);
	}
	return c;
    }

    public static Caserio thatIntersectsWith(String pointStr) {
	Connection con = DBSession.getCurrentSession().getJavaConnection();
	JDBCUtils jdbcUtils = new JDBCUtils(con);
	DefaultTableModel result = jdbcUtils.intersects(tablename, pointStr,
		pkName, nameName);
	String pk = result.getValueAt(0, 0).toString();
	String name = result.getValueAt(0, 1).toString();
	return new Caserio(pk, name);
    }

    public double distanceTo(IGeometry geom) {
	return this.geom.distance(geom.toJTSGeometry());
    }

    public double distanceTo(Geometry geom) {
	return this.geom.distance(geom);
    }

    public static Caserio closestTo(String pointStr, RegionI region) {
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
	    return new Caserio(pk, xStr, yStr);
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

}

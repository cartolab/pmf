package es.udc.cartolab.gvsig.pmf.importer.entities;

import java.sql.Connection;

import javax.swing.table.DefaultTableModel;

import com.iver.cit.gvsig.fmap.core.IGeometry;

import es.icarto.gvsig.importer.JDBCUtils;
import es.icarto.gvsig.importer.RegionI;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class Aldea implements RegionI {

    public final static String tablename = "cantones";
    public final static String pkName = "cod_canton";

    // Representa el nombre del campo en la base de datos que contiene el nombre
    // común de la región (por ejemplo el nombre de la aldea)
    public final static String nameName = "canton";

    public final String pk;
    public final String name;

    private Aldea(String pk, String name) {
	this.pk = pk;
	this.name = name;
    }

    public static Aldea thatIntersectsWith(String pointStr) {
	Connection con = DBSession.getCurrentSession().getJavaConnection();
	JDBCUtils jdbcUtils = new JDBCUtils(con);
	DefaultTableModel result = jdbcUtils.intersects(tablename, pointStr,
		pkName, nameName);
	String pk = result.getValueAt(0, 0).toString();
	String name = result.getValueAt(0, 1).toString();
	return new Aldea(pk, name);
    }

    @Override
    public String getPKField() {
	return pkName;
    }

    @Override
    public String getPKValue() {
	return pk;
    }

    @Override
    public double distanceTo(IGeometry geom) {
	throw new RuntimeException("Not implementet yet");
    }
}

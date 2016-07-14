package es.icarto.gvsig.importer;

import java.sql.Connection;

import javax.swing.table.DefaultTableModel;

import es.icarto.gvsig.commons.db.ConnectionWrapper;
import es.icarto.gvsig.commons.utils.StrUtils;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class JDBCUtils {

    public DefaultTableModel intersects(String tablename, String point,
	    String... fields) {
	DBSession session = DBSession.getCurrentSession();
	Connection javaCon = session.getJavaConnection();
	ConnectionWrapper con = new ConnectionWrapper(javaCon);
	String fieldStr = StrUtils.join(", ", (Object[]) fields);
	String query = String.format(
		"SELECT %s FROM %s WHERE ST_Intersects(geom, %s)", fieldStr,
		tablename, point);
	DefaultTableModel table = con.execute(query);
	if (table == null) {
	    throw new RuntimeException("Error desconocido");
	}
	if (table.getRowCount() < 1) {
	    throw new RuntimeException("Sin resultados");
	}
	if (table.getRowCount() > 1) {
	    throw new RuntimeException("Más de un resultado");
	}
	return table;
    }

}

package es.icarto.gvsig.importer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import es.icarto.gvsig.commons.db.ConnectionWrapper;
import es.icarto.gvsig.commons.utils.Field;
import es.icarto.gvsig.commons.utils.StrUtils;
import es.udc.cartolab.gvsig.pmf.importer.Comunidad;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public abstract class JDBCTarget implements Target {

    private static final Logger logger = Logger.getLogger(JDBCTarget.class);

    // TODO. El campo field se está usando sólo para rellenar el combo de
    // targets en la tabla de reporte. No tengo claro que esto tenga sentido.
    // Seguramente el combo se tiene que rellenar con objetos Target
    // directamente
    protected Field field;

    @SuppressWarnings("unchecked")
    // TODO. To be moved to another object
    protected void addWarning(DefaultTableModel table, int row, String msg) {
	int errorIdx = table.findColumn("Errores");
	List<String> l = (List<String>) table.getValueAt(row, errorIdx);
	if (l == null) {
	    l = new ArrayList<String>();
	    table.setValueAt(l, row, errorIdx);

	}
	l.add(msg);
    }

    protected boolean existsInProcessed(DefaultTableModel table,
	    String tablename, String code, int rowToIgnore) {
	int tablenameIdx = table.findColumn("tablename");
	int idIdx = table.findColumn("id");
	for (int row = 0; row < table.getRowCount(); row++) {
	    if (row == rowToIgnore) {
		continue;
	    }
	    Object c = table.getValueAt(row, tablenameIdx);
	    if ((c != null) && c.toString().equalsIgnoreCase(tablename)) {
		if (code.equalsIgnoreCase(table.getValueAt(row, idIdx)
			.toString())) {
		    return true;
		}
	    }
	}
	return false;
    }

    protected Comunidad getComunidad(ImporterTM table, String tablename,
	    String fieldname, String code) {
	int tablenameIdx = table.findColumn("tablename");
	int idIdx = table.findColumn("id");
	for (int row = 0; row < table.getRowCount(); row++) {
	    Object c = table.getValueAt(row, tablenameIdx);
	    if ((c != null) && c.toString().equalsIgnoreCase(tablename)) {
		if (code.equalsIgnoreCase(table.getValueAt(row, idIdx)
			.toString())) {
		    return new Comunidad(table.getValueAt(row, idIdx)
			    .toString(), table.getGeom(row));
		}
	    }
	}

	Connection con = DBSession.getCurrentSession().getJavaConnection();
	ConnectionWrapper conW = new ConnectionWrapper(con);
	final String whereClause = String.format("WHERE %s = '%s'", fieldname,
		code);
	String sql = String.format(
		"SELECT cod_com, st_x(geom), st_y(geom) FROM comunidades %s",
		whereClause);
	DefaultTableModel r = conW.execute(sql);
	if (r.getRowCount() > 0) {
	    final String codCom = r.getValueAt(0, 0).toString();
	    final String xStr = r.getValueAt(0, 1).toString();
	    final String yStr = r.getValueAt(0, 2).toString();
	    return new Comunidad(codCom, xStr, yStr);
	}

	return null;
    }

    protected boolean existsInDB(String tablename, String fieldname, String code) {
	DBSession session = DBSession.getCurrentSession();
	String[][] r;
	try {
	    final String whereClause = String.format("WHERE %s = '%s'",
		    fieldname, code);
	    r = session.getTable(tablename, "", new String[] { fieldname },
		    whereClause, null, false);

	    if ((r.length != 1) || (r[0] == null) || (r[0].length != 1)
		    || (r[0][0] == null)) {

		return false;
	    }
	} catch (SQLException e) {
	    logger.error(e.getStackTrace(), e);
	}

	return true;
    }

    protected DefaultTableModel intersects(String tablename, String point,
	    String... fields) {
	JDBCUtils jdbcUtils = new JDBCUtils();
	return jdbcUtils.intersects(tablename, point, fields);
    }

    protected DefaultTableModel closest(String tablename, String point,
	    String where, String... fields) {
	where = where == null ? "" : where;
	DBSession session = DBSession.getCurrentSession();
	Connection javaCon = session.getJavaConnection();
	ConnectionWrapper con = new ConnectionWrapper(javaCon);
	String fieldStr = StrUtils.join(", ", (Object[]) fields);

	String query = String
		.format("SELECT %s,  ST_Distance(%s, geom) from %s %s ORDER BY ST_Distance(%s, geom) LIMIT 1;",
			fieldStr, point, tablename, where, point);

	DefaultTableModel table = con.execute(query);
	if (table == null) {
	    throw new RuntimeException("Error desconocido");
	}
	return table;
    }

    protected DefaultTableModel closestInTable(String tablename, String point,
	    String where, String... fields) {
	return null;
    }

    protected DefaultTableModel maxCode(String tablename, String codeFieldName,
	    String fkName, String fkValue) {
	DBSession session = DBSession.getCurrentSession();
	Connection javaCon = session.getJavaConnection();
	ConnectionWrapper con = new ConnectionWrapper(javaCon);

	String query = String.format(
		"SELECT %s from %s WHERE %s = '%s' ORDER BY %s DESC LIMIT 1;",
		codeFieldName, tablename, fkName, fkValue, codeFieldName);

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

    protected DefaultTableModel maxCode(String tablename, String codeFieldName,
	    int ncharacters, String fkValue) {
	DBSession session = DBSession.getCurrentSession();
	Connection javaCon = session.getJavaConnection();
	ConnectionWrapper con = new ConnectionWrapper(javaCon);

	String where = String.format("WHERE substr(%s, 1, %d) = '%s'",
		codeFieldName, ncharacters, fkValue);
	String query = String.format(
		"SELECT %s from %s %s ORDER BY %s DESC LIMIT 1;",
		codeFieldName, tablename, where, codeFieldName);

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

    @Override
    public Field getField() {
	return field;
    }
}

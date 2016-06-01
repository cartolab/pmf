package es.icarto.gvsig.importer;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import com.iver.cit.gvsig.fmap.core.FPoint2D;
import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.iver.cit.gvsig.fmap.core.ShapeFactory;

import es.icarto.gvsig.commons.db.ConnectionWrapper;
import es.icarto.gvsig.commons.utils.Field;
import es.icarto.gvsig.commons.utils.StrUtils;
import es.udc.cartolab.gvsig.navtable.format.DoubleFormatNT;
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

    protected IGeometry getGeometry(String xStr, String yStr) {
	Number x = toNumeric(xStr);
	Number y = toNumeric(yStr);

	FPoint2D fpoint2d = new FPoint2D(x.doubleValue(), y.doubleValue());
	IGeometry geom = ShapeFactory.createPoint2D(fpoint2d);

	// TODO
	// IProjection crs4326 = CRSFactory.getCRS("EPSG:4326");
	// IProjection crs32616 = CRSFactory.getCRS("EPSG:32616");
	// ICoordTrans ct = crs4326.getCT(crs32616);
	// geom.reProject(ct);

	return geom;
    }

    private Number toNumeric(String v) {
	NumberFormat formatter = DoubleFormatNT.getEditingFormat();
	try {
	    return formatter.parse(v);
	} catch (ParseException e) {
	    logger.error(e.getStackTrace(), e);
	}
	return null;
    }

    protected boolean existsInProcessed(DefaultTableModel table,
	    String tablename, String code) {
	int tablenameIdx = table.findColumn("tablename");
	int idIdx = table.findColumn("id");
	for (int row = 0; row < table.getRowCount(); row++) {
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
	DBSession session = DBSession.getCurrentSession();
	Connection javaCon = session.getJavaConnection();
	ConnectionWrapper con = new ConnectionWrapper(javaCon);
	String fieldStr = StrUtils.join(", ", (Object[]) fields);
	String query = String.format(
		"SELECT %s FROM %s WHERE ST_Intersects(geometry, %s)",
		fieldStr, tablename, point);
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

    protected DefaultTableModel closest(String tablename, String point,
	    String where, String... fields) {
	where = where == null ? "" : where;
	DBSession session = DBSession.getCurrentSession();
	Connection javaCon = session.getJavaConnection();
	ConnectionWrapper con = new ConnectionWrapper(javaCon);
	String fieldStr = StrUtils.join(", ", (Object[]) fields);

	String query = String
		.format("SELECT %s,  ST_Distance(%s, geometry) from %s %s ORDER BY ST_Distance(%s, geometry) LIMIT 1;",
			fieldStr, point, tablename, where, point);

	DefaultTableModel table = con.execute(query);
	if (table == null) {
	    throw new RuntimeException("Error desconocido");
	}
	return table;
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

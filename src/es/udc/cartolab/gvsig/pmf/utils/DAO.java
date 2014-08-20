package es.udc.cartolab.gvsig.pmf.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import es.udc.cartolab.gvsig.pmf.forms.ComunidadesForm;
import es.udc.cartolab.gvsig.pmf.forms.InformacionGeneralForm;
import es.udc.cartolab.gvsig.pmf.forms.ParcelasForm;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public final class DAO {

    private DAO() {
	throw new AssertionError("Not instanciable class");
    }

    private static <T> Collection<T> flat(T[][] array) {
	Collection<T> result = new ArrayList<T>();

	for (T[] e : array) {
	    Collections.addAll(result, e);
	}

	return result;
    }

    public static Collection<String> getCommunityCodes() throws SQLException {
	DBSession session = DBSession.getCurrentSession();
	String[][] table = session.getTable(ComunidadesForm.NAME,
		PmfConstants.DATA_SCHEMA,
		new String[] { ComunidadesForm.PKFIELD }, null, null, false);
	return flat(table);
    }

    public static Collection<String> getPlotCodes() throws SQLException {
	DBSession session = DBSession.getCurrentSession();
	String[][] table = session.getTable(ParcelasForm.NAME,
		PmfConstants.DATA_SCHEMA,
		new String[] { ParcelasForm.PKFIELD }, null, null, false);
	return flat(table);
    }

    public static Collection<String> getPlotCodes(String comCode)
	    throws SQLException {
	DBSession session = DBSession.getCurrentSession();
	String[][] table = session.getTable(ParcelasForm.NAME,
		PmfConstants.DATA_SCHEMA,
		new String[] { ParcelasForm.PKFIELD },
		String.format("%s='%s'", ParcelasForm.CODCOM, comCode), null,
		false);
	return flat(table);
    }

    public static Collection<String> getHousingCodes() throws SQLException {
	DBSession session = DBSession.getCurrentSession();
	String[][] table = session.getTable(InformacionGeneralForm.NAME,
		PmfConstants.DATA_SCHEMA,
		new String[] { InformacionGeneralForm.PKFIELD }, null, null,
		false);
	return flat(table);
    }

    public static List<String> getPlotCodesForViv(String selectedItem)
	    throws SQLException {

	DBSession session = DBSession.getCurrentSession();
	String[][] table = session.getTable(
		ParcelasForm.NAME,
		PmfConstants.DATA_SCHEMA,
		new String[] { ParcelasForm.PKFIELD },
		String.format("%s='%s'", ParcelasForm.CODVIV,
			selectedItem.toString()), null, false);
	return (List<String>) flat(table);

    }

    public static String[][] getRubrosAgregated() throws SQLException {
	ArrayList<String[]> rows = new ArrayList<String[]>();
	Connection con = DBSession.getCurrentSession().getJavaConnection();

	Statement statement = con.createStatement();
	String sql = "SELECT a.cod_com, b.rubro, sum(b.area_prod), sum(b.volumen_prod_kg), sum(b.volumen_prod_ud), sum(b.venta_total), sum(b.consumo_familiar) FROM balances AS b join parcelas AS a ON a.cod_parcela = b.cod_parcela GROUP BY a.cod_com, b.rubro";
	ResultSet rs = statement.executeQuery(sql);
	int columnCount = rs.getMetaData().getColumnCount();
	while (rs.next()) {
	    String[] row = new String[columnCount];
	    for (int i = 1; i <= columnCount; i++) {
		String val = rs.getString(i);
		if (val == null) {
		    val = "";
		}
		row[i - 1] = val;
	    }
	    rows.add(row);
	}
	rs.close();
	statement.close();
	return rows.toArray(new String[0][0]);
    }

    public static String[][] getEmpleoComunidad() throws SQLException {
	ArrayList<String[]> rows = new ArrayList<String[]>();
	Connection con = DBSession.getCurrentSession().getJavaConnection();

	Statement statement = con.createStatement();

	String sql = "SELECT a.cod_com, b.rubro, sum(b.empleo_temp_total), sum(b.empleo_temp_hombre), sum(b.empleo_temp_mujer) FROM balances AS b join parcelas AS a ON a.cod_parcela = b.cod_parcela GROUP BY a.cod_com, b.rubro ORDER BY sum(b.rubro)";
	ResultSet rs = statement.executeQuery(sql);
	int columnCount = rs.getMetaData().getColumnCount();
	while (rs.next()) {
	    String[] row = new String[columnCount];
	    for (int i = 1; i <= columnCount; i++) {
		String val = rs.getString(i);
		if (val == null) {
		    val = "";
		}
		row[i - 1] = val;
	    }
	    rows.add(row);
	}
	rs.close();
	statement.close();
	return rows.toArray(new String[0][0]);
    }

    public static String[][] getIngresosProduccionFamiliar()
	    throws SQLException {
	ArrayList<String[]> rows = new ArrayList<String[]>();
	Connection con = DBSession.getCurrentSession().getJavaConnection();

	Statement statement = con.createStatement();

	String sql = "SELECT ig.cod_viv, ig.nom_produ, b.rubro, sum(b.volumen_prod_ud), sum(b.volumen_prod_kg), sum(b.beneficio), FROM informacion_general AS ig join parcelas as p on ig.cod_viv = p.cod_viv join balances AS b  ON p.cod_parcela = b.cod_parcela GROUP BY ig.cod_viv, ig.nom_produ, b.rubro ORDER BY sum(b.beneficio)";
	ResultSet rs = statement.executeQuery(sql);
	int columnCount = rs.getMetaData().getColumnCount();
	while (rs.next()) {
	    String[] row = new String[columnCount];
	    for (int i = 1; i <= columnCount; i++) {
		String val = rs.getString(i);
		if (val == null) {
		    val = "";
		}
		row[i - 1] = val;
	    }
	    rows.add(row);
	}
	rs.close();
	statement.close();
	return rows.toArray(new String[0][0]);
    }
}
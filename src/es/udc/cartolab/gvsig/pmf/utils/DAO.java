package es.udc.cartolab.gvsig.pmf.utils;

import java.sql.SQLException;
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
}
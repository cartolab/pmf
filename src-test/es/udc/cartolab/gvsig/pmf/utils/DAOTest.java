package es.udc.cartolab.gvsig.pmf.utils;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;

import com.iver.cit.gvsig.fmap.drivers.DBException;

import es.udc.cartolab.cit.gvsig.fmap.drivers.jdbc.spatialite.SpatiaLiteDriver;
import es.udc.cartolab.gvsig.users.utils.DBSessionSpatiaLite;
import es.udc.cartolab.testutils.Drivers;
import es.udc.cartolab.testutils.TestProperties;

public class DAOTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	new SpatiaLiteDriver(false, TestProperties.driversPath.replace(
		"/drivers", "/lib/"));
	Drivers.initgvSIGDrivers(TestProperties.driversPath);
	String sqliteFile = PmfConstants.SQLITE_FILE;
	DBSessionSpatiaLite.createConnection(sqliteFile);
    }

    @Test
    public void getCommunityCodes() throws DBException, SQLException {
	Collection<String> expected = new ArrayList<String>();
	Collections.addAll(expected, "000001", "000002");
	assertEquals(expected, DAO.getCommunityCodes());
    }

    @Test
    public void getPlotCodes() throws SQLException {
	Collection<String> expected = new ArrayList<String>();
	Collections.addAll(expected, "01");
	final String comCode = "000001";
	assertEquals(expected, DAO.getPlotCodes(comCode));
    }

    @Test
    public void getHousingCodes() throws SQLException {
	Collection<String> expected = new ArrayList<String>();
	Collections.addAll(expected, "01");
	assertEquals(expected, DAO.getHousingCodes());
    }
}

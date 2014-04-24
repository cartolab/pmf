package es.udc.cartolab.gvsig.pmf.utils;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;

import com.iver.cit.gvsig.fmap.drivers.DBException;

import es.udc.cartolab.gvsig.users.utils.DBSessionSpatiaLite;
import es.udc.cartolab.testutils.Drivers;
import es.udc.cartolab.testutils.TestProperties;

public class DAOTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	Drivers.initgvSIGDrivers(TestProperties.driversPath);
	String sqliteFile = PmfConstants.SQLITE_FILE;
	DBSessionSpatiaLite.createConnection(sqliteFile);
    }

    @Test
    public void getCommunityCodes() throws DBException, SQLException {
	Collection<String> expected = new ArrayList<String>();
	Collections.addAll(expected, "1", "2");
	assertEquals(expected, DAO.getCommunityCodes());
    }

    @Test
    public void getPlotCodes() throws SQLException {
	Collection<String> expected = new ArrayList<String>();
	Collections.addAll(expected, "110", "120");
	final String comCode = "1";
	assertEquals(expected, DAO.getPlotCodes(comCode));
    }

    @Test
    public void getHousingCodes() throws SQLException {
	Collection<String> expected = new ArrayList<String>();
	Collections.addAll(expected, "1", "2");
	assertEquals(expected, DAO.getHousingCodes());
    }
}

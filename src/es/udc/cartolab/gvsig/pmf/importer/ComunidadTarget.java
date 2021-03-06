package es.udc.cartolab.gvsig.pmf.importer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.vividsolutions.jts.geom.Geometry;

import es.icarto.gvsig.commons.utils.Field;
import es.icarto.gvsig.importer.Entity;
import es.icarto.gvsig.importer.GFactory;
import es.icarto.gvsig.importer.ImportError;
import es.icarto.gvsig.importer.ImporterTM;
import es.icarto.gvsig.importer.JDBCTarget;
import es.udc.cartolab.gvsig.pmf.importer.entities.Aldea;
import es.udc.cartolab.gvsig.pmf.importer.entities.Caserio;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class ComunidadTarget extends JDBCTarget {

    private static final Logger logger = Logger
	    .getLogger(ComunidadTarget.class);

    private final Pattern pattern;
    private final String tablename;
    private final String pkname;
    private final String idDiff;
    private final String digitsDiff;

    public ComunidadTarget() {
	super(DBSession.getCurrentSession().getJavaConnection());
	this.tablename = "comunidades";
	field = new Field("comunidades");
	field.setValue(this);
	this.pattern = Pattern.compile("^\\d{8}$", Pattern.CASE_INSENSITIVE);
	this.pkname = "cod_com";
	this.idDiff = "";
	this.digitsDiff = "%02d";
	// geomBuild = new XYPointBuilder("", "");
    }

    @Override
    public boolean matches(String value) {
	Matcher matcher = pattern.matcher(value);
	return matcher.matches();
    }

    @Override
    public boolean process(String value, ImporterTM table, int i) {
	Matcher matcher = pattern.matcher(value);
	if (!matcher.matches()) {
	    return false;
	}
	final String code = matcher.group();

	IGeometry geom = new GFactory().getGeometry(table, i);
	table.setGeom(geom, i);
	table.setTarget(field, i);
	table.setCode(code, i);

	return true;

    }

    @Override
    public String calculateCode(ImporterTM table, int i) {
	String code = null;
	try {
	    code = doCalculateCode(table, i);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	}
	return code == null ? "" : code;
    }

    private final static double MIN_DISTANCE_TO_PARENT = 2000;

    /**
     * Si hay un caserio en la aldea donde est� el nuevo punto a menos de 1km se
     * coge ese caserio como el c�digo de la comunidad. En caso contrario se
     * coge el c�digo de m�ximo valor entre los que ya existan (para esa aldea)
     * en la tabla o en comunidades o en caserios y se le suma 1
     */
    public String doCalculateCode(ImporterTM table, int i) {
	Geometry point = table.getGeom(i).toJTSGeometry();
	String pointStr = "ST_GeomFromText( '" + point.toText() + "' )";

	Aldea aldea = Aldea.f().thatIntersectsWith(pointStr);
	if (aldea == null) {
	    return null;
	}
	Caserio caserio = Caserio.f().closestTo(pointStr, aldea);
	if (caserio != null) {
	    double d = caserio.distanceTo(point);
	    if (d < MIN_DISTANCE_TO_PARENT) {
		return caserio.getPK();
	    }
	}

	DefaultTableModel results2 = maxCode(Caserio.tablename, Caserio.pkname,
		aldea.getPK());
	DefaultTableModel results3 = maxCode(tablename, pkname, aldea.getPK());

	String maxCodeInDB = results2.getValueAt(0, 0).toString();
	String maxCodeInData = results3.getValueAt(0, 0).toString();
	String maxCodeInTable = table.maxCodeValueForTarget(this.field, i,
		aldea.getPK());

	String code = codeIt(aldea, maxCodeInDB, maxCodeInData, maxCodeInTable);

	return code;
    }

    private String codeIt(Entity parent, String maxCodeInDB,
	    String maxCodeInData, String maxCodeInTable) {
	String maxCode = "000000" + idDiff + "00";

	if (maxCode.compareTo(maxCodeInDB) < 0) {
	    maxCode = maxCodeInDB;
	}
	if (maxCode.compareTo(maxCodeInTable) < 0) {
	    maxCode = maxCodeInTable;
	}
	if (maxCode.compareTo(maxCodeInData) < 0) {
	    maxCode = maxCodeInData;
	}

	int parseInt = Integer.parseInt(maxCode.substring(6)) + 1;
	String code = parent.getPK() + idDiff
		+ String.format(digitsDiff, parseInt);
	return code;
    }

    @Override
    public List<ImportError> checkErrors(ImporterTM table, int row) {
	ErrorCheck errorCheck = new ErrorCheck("La comunidad");
	List<ImportError> l = new ArrayList<ImportError>();
	ImportError error = null;

	String code = table.getCode(row);

	error = checkCode(table, code, row);
	if (error != null) {
	    l.add(error);
	    table.setError(l, row);
	    return l;
	}

	error = checkTableUnique(table, tablename, code, row);
	if (error != null) {
	    l.add(error);
	}
	error = checkDBUnique(tablename, pkname, code, row);
	if (error != null) {
	    l.add(error);
	}

	error = errorCheck
		.checkPointInCorrectAldea(table, tablename, code, row);
	if (error != null) {
	    l.add(error);
	}

	error = checkDistanceToCaserio(table, code, table.getGeom(row), row);
	if (error != null) {
	    l.add(error);
	}

	table.setError(l, row);
	return l;
    }

    private ImportError checkCode(ImporterTM table, String code, int row) {
	Matcher matcher = pattern.matcher(code);
	if (!matcher.matches()) {
	    return new ImportError("C�digo no v�lido", row);
	}
	return null;
    }

    // "Comunidad %s duplicada en el fichero de entrada"
    private ImportError checkTableUnique(ImporterTM table, String tablename,
	    String code, int row) {

	// TODO. Ver como personalizar el msg para el elemento. Cada target
	// deber�a tener un nombre "Comunidad", "Vivienda" con el que referirse
	// a �l, y no el nombre de la tabla (tablename") Y tambi�n deber�a
	// definir el g�nero duplicado/duplicada
	if (existsInProcessed(table, tablename, code, row)) {
	    String errorMsg = String
		    .format("La comunidad '%s' est� duplicada en el fichero de entrada",
			    code);
	    return new ImportError(errorMsg, row);
	}
	return null;
    }

    // "La comunidad %s ya existe en la base de datos"
    private ImportError checkDBUnique(String tablename, String pkName,
	    String code, int row) {
	if (existsInDB(tablename, pkName, code)) {
	    String errorMsg = String.format(
		    "La comunidad '%s' ya existe en la base de datos", code);
	    return new ImportError(errorMsg, row);
	}

	return null;
    }

    private ImportError checkDistanceToCaserio(ImporterTM table, String code,
	    IGeometry geom, int row) {
	Caserio caserio = Caserio.f().fromDB(code);
	if (caserio != null) {
	    double distance = caserio.distanceTo(geom);
	    if (distance > MIN_DISTANCE_TO_PARENT) {
		return new ImportError(
			"Existe un caser�o con ese c�digo a m�s de 2km de esta comunidad",
			row);
	    }

	}

	return null;
    }

    @Override
    public String getInsertSQL(String parentCode, String code, String geomAsWKT) {
	return String
		.format("INSERT INTO comunidades (cod_com, geom) VALUES ('%s', ST_GeomFromText('%s', 32616))",
			code, geomAsWKT);
    }

}

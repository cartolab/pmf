package es.udc.cartolab.gvsig.pmf.importer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;

import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.vividsolutions.jts.geom.Geometry;

import es.icarto.gvsig.commons.utils.Field;
import es.icarto.gvsig.importer.Foo;
import es.icarto.gvsig.importer.ImportError;
import es.icarto.gvsig.importer.ImporterTM;
import es.icarto.gvsig.importer.JDBCTarget;

public class CentroTarget extends JDBCTarget {

    private final Pattern pattern;
    private final String tablename;
    private final String pkname;
    protected final String idDiff;

    public CentroTarget(String tablename, String pkname, Pattern pattern,
	    String idDiff) {
	this.tablename = tablename;
	field = new Field(tablename);
	field.setValue(this);
	this.pattern = pattern;
	this.pkname = pkname;
	this.idDiff = idDiff;
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

	IGeometry geom = new Foo().getGeometry(table, i);
	table.setGeom(geom, i);
	table.setTarget(field, i);
	table.setCode(code, i);

	return true;
    }

    @Override
    public String calculateCode(ImporterTM table, int i) {
	String codComunidad = null;
	String nombreComunidad = null;
	double minDistance = Double.MAX_VALUE;
	Geometry point = table.getGeom(i).toJTSGeometry();
	String pointStr = "ST_GeomFromText( '" + point.toText() + "' )";

	Aldea aldea = Aldea.thatIntersectsWith(pointStr);

	String closestWhere = String.format(" WHERE substr(%s, 1, 6) = '%s'",
		"cod_com", aldea.pk);
	DefaultTableModel closest = closest("comunidades", pointStr,
		closestWhere, "cod_com", "nombre");
	if (closest.getRowCount() > 0) {
	    Double d = (Double) closest.getValueAt(0, 2);
	    if (d < 2000) {
		minDistance = d;
		codComunidad = closest.getValueAt(0, 0).toString();
		nombreComunidad = closest.getValueAt(0, 1).toString();
	    }
	}

	for (int row = 0; row < table.getRowCount(); row++) {
	    Object o = table.getTarget(row);
	    String tablename = o != null ? o.toString() : "";
	    if (tablename.equals("comunidades")) {
		Geometry tmpGeom = table.getGeom(row).toJTSGeometry();
		if (tmpGeom.distance(point) < minDistance) {
		    codComunidad = table.getCode(row);
		    nombreComunidad = "";
		    minDistance = tmpGeom.distance(point);
		}
	    }
	}

	if (codComunidad == null) {
	    return null;
	}

	DefaultTableModel results3 = maxCode("informacion_general", "cod_viv",
		8, codComunidad);
	String maxCodeInData = results3.getValueAt(0, 0).toString();
	String maxCodeInTable = table.maxCodeValue("tablename", this.field, i);

	String maxCode = maxCodeInTable;
	if (maxCode.compareTo(maxCodeInData) < 0) {
	    maxCode = maxCodeInData;
	}
	int parseInt = Integer.parseInt(maxCode.substring(10)) + 1;
	String code = codComunidad + idDiff + parseInt;

	return code;
    }

    @Override
    public List<ImportError> checkErrors(ImporterTM table, int row) {
	List<ImportError> l = new ArrayList<ImportError>();
	ImportError error = null;

	String code = table.getCode(row);

	error = checkCode(table, code, row);
	if (error != null) {
	    l.add(error);
	    return l;
	}

	Matcher matcher = pattern.matcher(code);
	matcher.matches();
	final String parentPKValue = matcher.group(1);

	error = checkParentExists(table, parentPKValue, row);
	if (error != null) {
	    l.add(error);
	}

	error = checkTableUnique(table, tablename, code, row);
	if (error != null) {
	    l.add(error);
	}
	error = checkDBUnique(tablename, pkname, code, row);
	if (error != null) {
	    l.add(error);
	}

	error = checkPointInCorrectAldea(table, tablename, code, row);
	if (error != null) {
	    l.add(error);
	}

	error = checkDistanceToParent(table, code, parentPKValue,
		table.getGeom(row), row);
	if (error != null) {
	    l.add(error);
	}

	table.setError(l, row);
	return l;
    }

    private ImportError checkCode(ImporterTM table, String code, int row) {
	Matcher matcher = pattern.matcher(code);
	if (!matcher.matches()) {
	    return new ImportError("Código no válido", row);
	}
	return null;
    }

    private ImportError checkParentExists(ImporterTM table,
	    String parentPKValue, int row) {
	if (!existsInProcessed(table, Comunidad.tablename, parentPKValue, row)
		&& !existsInDB(Comunidad.tablename, Comunidad.pkName,
			parentPKValue)) {
	    String errorMsg = String.format("La comunidad %s no existe",
		    parentPKValue);
	    return new ImportError(errorMsg, row);
	}
	return null;
    }

    private ImportError checkTableUnique(ImporterTM table, String tablename,
	    String code, int row) {

	// TODO. Ver como personalizar el msg para el elemento. Cada target
	// debería tener un nombre "Comunidad", "Vivienda" con el que referirse
	// a él, y no el nombre de la tabla (tablename") Y también debería
	// definir el género duplicado/duplicada
	if (existsInProcessed(table, tablename, code, row)) {
	    String errorMsg = String.format(tablename
		    + " %s duplicado en el fichero de entrada", code);
	    return new ImportError(errorMsg, row);
	}
	return null;
    }

    private ImportError checkDBUnique(String tablename, String pkName,
	    String code, int row) {
	if (existsInDB(tablename, pkName, code)) {
	    String errorMsg = String.format("El " + tablename
		    + " %s ya existe en la base de datos", code);
	    return new ImportError(errorMsg, row);
	}

	return null;
    }

    private ImportError checkPointInCorrectAldea(ImporterTM table,
	    String tablename, String code, int row) {
	Geometry point = table.getGeom(row).toJTSGeometry();
	String pointStr = "ST_GeomFromText( '" + point.toText() + "' )";
	Aldea aldea = Aldea.thatIntersectsWith(pointStr);
	if (!code.startsWith(aldea.pk)) {
	    String errorMsg = String
		    .format("El %s no está en la aldea que indica su código",
			    tablename);
	    return new ImportError(errorMsg, row);
	}
	return null;
    }

    private ImportError checkDistanceToParent(ImporterTM table, String code,
	    String parentPKValue, IGeometry geom, int row) {

	Comunidad comunidad = getComunidad(table, "comunidades", "cod_com",
		parentPKValue);
	double distance = comunidad.distanceTo(geom);
	if (distance > 5000) {
	    return new ImportError("Elemento a más de 5km de la comunidad", row);
	}

	return null;
    }
}
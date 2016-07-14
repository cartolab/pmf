package es.udc.cartolab.gvsig.pmf.importer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.vividsolutions.jts.geom.Geometry;

import es.icarto.gvsig.commons.utils.Field;
import es.icarto.gvsig.importer.Foo;
import es.icarto.gvsig.importer.ImportError;
import es.icarto.gvsig.importer.ImporterTM;
import es.icarto.gvsig.importer.JDBCTarget;
import es.icarto.gvsig.importer.Target;

public class VerticeTarget extends JDBCTarget implements Target {

    private final Pattern pattern;
    private final String tablename;
    private final String pkname;

    public VerticeTarget(String tablename, String pkname, Pattern pattern) {
	this.tablename = tablename;
	field = new Field(tablename);
	field.setValue(this);
	this.pattern = pattern;
	this.pkname = pkname;
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
	return null;
	// substring(16)
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
	// Es la vivienda no la parcela
	final String parentPKValue = matcher.group(2);

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
	if (!existsInProcessed(table, "informacion_general", parentPKValue, row)
		&& !existsInDB("informacion_general", "cod_viv", parentPKValue)) {
	    String errorMsg = String.format("La vivienda %s no existe",
		    parentPKValue);
	    return new ImportError(errorMsg, row);
	}
	return null;
    }

    // "Vivienda %s duplicada en el fichero de entrada"
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

    // "Vivienda %s ya existe en la base de datos"
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
	// TODO: Habría que referirlo a la vivienda y sería menos de 5km
	Comunidad comunidad = getComunidad(table, "comunidades", "cod_com",
		parentPKValue);
	if (comunidad == null) {
	    // en ciertos errores tendría más lógica parar en cuando hay un
	    // error
	    return null;
	}
	double distance = comunidad.distanceTo(geom);
	if (distance > 5000) {
	    return new ImportError("Elemento a más de 5km de la comunidad", row);
	}

	return null;
    }

}

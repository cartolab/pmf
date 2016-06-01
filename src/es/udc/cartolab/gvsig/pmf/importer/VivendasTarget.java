package es.udc.cartolab.gvsig.pmf.importer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.iver.cit.gvsig.fmap.core.IGeometry;

import es.icarto.gvsig.commons.utils.Field;
import es.icarto.gvsig.importer.ImporterTM;
import es.icarto.gvsig.importer.JDBCTarget;

public class VivendasTarget extends JDBCTarget {
    private final static Pattern pattern = Pattern.compile(
	    "^(\\d{9})vi\\d{3}$", Pattern.CASE_INSENSITIVE);

    public VivendasTarget() {
	field = new Field("informacion_general");
	field.setValue(this);
    }

    @Override
    public boolean matches(String value) {
	Matcher matcher = pattern.matcher(value);
	return matcher.find();
    }

    @Override
    public boolean process(String value, ImporterTM table, int i) {
	Matcher matcher = pattern.matcher(value);
	if (!matcher.find()) {
	    return false;
	}
	final String code = matcher.group();
	if (existsInProcessed(table, "informacion_general", code)) {
	    addWarning(table, i, String.format(
		    "Vivienda %s duplicada en el fichero de entrada", code));
	}
	if (existsInDB("informacion_general", "cod_viv", code)) {
	    addWarning(table, i,
		    String.format("Vivienda %s ya existe en la tabla", code));
	}

	if (!existsInProcessed(table, "comunidades", matcher.group(1))
		&& !existsInDB("comunidades", "cod_com", matcher.group(1))) {
	    addWarning(table, i, String.format("La comunidad %s no existe",
		    matcher.group(1)));
	}

	int xIdx = table.findColumn("x");
	int yIdx = table.findColumn("y");
	String xStr = table.getValueAt(i, xIdx).toString();
	String yStr = table.getValueAt(i, yIdx).toString();
	IGeometry geom = getGeometry(xStr, yStr);
	table.setGeom(geom, i);

	table.setTarget(field, i);
	table.setCode(code, i);

	// Comprobar que la geometría está dentro de la zona de interés
	// Double bbox = new Rectangle2D.Double(x, y, w, h);
	// bbox.contains(geom);

	// Comprobar que la vivienda está cerca de la comunidad
	return true;

    }

    @Override
    public String calculateCode(ImporterTM table, int i) {
	return null;
    }
}

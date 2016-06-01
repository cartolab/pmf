package es.udc.cartolab.gvsig.pmf.importer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.iver.cit.gvsig.fmap.core.IGeometry;

import es.icarto.gvsig.commons.utils.Field;
import es.icarto.gvsig.importer.ImporterTM;
import es.icarto.gvsig.importer.JDBCTarget;

public class CentroTarget extends JDBCTarget {

    private final Pattern pattern;
    private final String tablename;
    private final String pkname;

    public CentroTarget(String tablename, String pkname, Pattern pattern) {
	field = new Field(tablename);
	field.setValue(this);
	this.pattern = pattern;
	this.tablename = tablename;
	this.pkname = pkname;
    }

    @Override
    public boolean matches(String value) {
	Matcher matcher = pattern.matcher(value);
	return matcher.find();
    }

    // TODO
    // select a.nombre, a.cod_aldea, c.cod_com from comunidades c join
    // aldeas_pmf a on substring(c.cod_com, 0, 6) = cod_aldea and
    // st_contains(a.geom, c.geom)

    @Override
    public boolean process(String value, ImporterTM table, int i) {
	Matcher matcher = pattern.matcher(value);
	if (!matcher.find()) {
	    return false;
	}
	final String code = matcher.group();
	if (existsInProcessed(table, tablename, code)) {
	    addWarning(
		    table,
		    i,
		    String.format(tablename
			    + " %s duplicado en el fichero de entrada", code));
	}
	if (existsInDB(tablename, pkname, code)) {
	    addWarning(
		    table,
		    i,
		    String.format("El " + tablename
			    + " %s ya existe en la tabla", code));
	}

	if (!existsInProcessed(table, "comunidades", matcher.group(1))
		&& !existsInDB("comunidades", "cod_com", matcher.group(1))) {
	    addWarning(table, i, String.format("La comunidad %s no existe",
		    matcher.group(1)));
	}

	int geomIdx = table.findColumn("geom");
	int xIdx = table.findColumn("x");
	int yIdx = table.findColumn("y");
	table.setTarget(field, i);
	String xStr = table.getValueAt(i, xIdx).toString();
	String yStr = table.getValueAt(i, yIdx).toString();
	IGeometry geom = getGeometry(xStr, yStr);
	table.setValueAt(geom, i, geomIdx);
	table.setCode(code, i);

	// Comprobar que la geometría está dentro de la zona de interés
	// Double bbox = new Rectangle2D.Double(x, y, w, h);
	// bbox.contains(geom);

	// Comprobar que el punto está cerca de la comunidad
	return true;
    }
}
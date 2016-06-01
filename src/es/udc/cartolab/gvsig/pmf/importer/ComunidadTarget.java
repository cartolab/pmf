package es.udc.cartolab.gvsig.pmf.importer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;

import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.vividsolutions.jts.geom.Geometry;

import es.icarto.gvsig.commons.utils.Field;
import es.icarto.gvsig.importer.ImporterTM;
import es.icarto.gvsig.importer.JDBCTarget;

public class ComunidadTarget extends JDBCTarget {

    private final static Pattern pattern = Pattern.compile("^\\d{9}$",
	    Pattern.CASE_INSENSITIVE);

    public ComunidadTarget() {
	field = new Field("comunidades");
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
	if (existsInProcessed(table, "comunidades", code)) {
	    addWarning(table, i, String.format(
		    "Comunidad %s duplicada en el fichero de entrada", code));
	}
	if (existsInDB("comunidades", "cod_com", code)) {
	    addWarning(table, i,
		    String.format("Comunidad %s ya existe en la tabla", code));
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
	return true;

    }

}

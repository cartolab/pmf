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

	int xIdx = table.findColumn("x");
	int yIdx = table.findColumn("y");
	String xStr = table.getValueAt(i, xIdx).toString();
	String yStr = table.getValueAt(i, yIdx).toString();
	IGeometry geom = getGeometry(xStr, yStr);
	table.setGeom(geom, i);

	table.setTarget(field, i);
	table.setCode(code, i);
	// Comprobar que la geometr�a est� dentro de la zona de inter�s
	// Double bbox = new Rectangle2D.Double(x, y, w, h);
	// bbox.contains(geom);
	return true;

    }

    @Override
    /**
     * Si hay un caserio en la aldea donde est� el nuevo punto a menos de 1km
     * se coge ese caserio como el c�digo de la comunidad. En caso contrario se
     * coge el c�digo de m�ximo valor entre los que ya existan (para esa aldea)
     * en la tabla o en comunidades o en caserios y se le suma 1
     */
    public String calculateCode(ImporterTM table, int i) {
	String code = null;
	String nombreComunidad = null;
	Geometry geom = table.getGeom(i).toJTSGeometry();
	String point = "ST_GeomFromText( '" + geom.toText() + "' )";

	DefaultTableModel aldea = intersects("aldeas_pmf", point, "cod_aldea",
		"nombre");
	String codAldea = aldea.getValueAt(0, 0).toString();

	String closestWhere = String.format(
		" WHERE substr(cod_caseri, 1, 6) = '%s'", codAldea);
	DefaultTableModel closest = closest("caserios_comunidades_pmf", point,
		closestWhere, "cod_caseri", "caserio");
	if (closest.getRowCount() > 0) {
	    Double d = (Double) closest.getValueAt(0, 2);
	    if (d < 1000) {
		code = closest.getValueAt(0, 0).toString();
		nombreComunidad = closest.getValueAt(0, 1).toString();
		return code;
	    }
	}

	DefaultTableModel results2 = maxCode("caserios_comunidades_pmf",
		"cod_caseri", 6, codAldea);
	DefaultTableModel results3 = maxCode("comunidades", "cod_com", 6,
		codAldea);

	String maxCodeInDB = results2.getValueAt(0, 0).toString();
	String maxCodeInData = results3.getValueAt(0, 0).toString();
	String maxCodeInTable = table.maxCodeValue("tablename", this.field, i);

	String maxCode = maxCodeInTable;
	if (maxCode.compareTo(maxCodeInDB) < 0) {
	    maxCode = maxCodeInDB;
	}
	if (maxCode.compareTo(maxCodeInData) < 0) {
	    maxCode = maxCodeInData;
	}
	int parseInt = Integer.parseInt(maxCode) + 1;
	code = parseInt + "";

	return code;
    }
}

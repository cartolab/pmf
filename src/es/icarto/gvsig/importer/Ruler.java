package es.icarto.gvsig.importer;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import com.iver.cit.gvsig.fmap.core.FPoint2D;
import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.iver.cit.gvsig.fmap.core.ShapeFactory;

import es.udc.cartolab.gvsig.navtable.format.DoubleFormatNT;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class Ruler {

    private static final Logger logger = Logger.getLogger(Ruler.class);
    /*
     * Código de comunidad: 8 dígitos. Por ejemplo: 17090401.
     *
     * Código de vivienda (información general): 13 dígitos. Código de Comunidad
     * + VI + XXX. Por ejemplo: 17090401vi001.
     *
     * Código de parcela: 16 dígitos. Código de vivienda + P + XX. Por ejemplo
     * 17090401vi001p01
     *
     * Código de vértices de parcelas: 19 dígitos. Código de Parcela + V + XX.
     * Por ejemplo 17090401vi001p01v01.
     *
     * Centro educativo: Codigo Comunidad + CE + XX
     *
     * Centro reuniones: Codigo Comunidad + CR + XX
     *
     * Centro salud: Codigo Comunidad + CS + XX
     *
     * Fuente comunitaria: Codigo Comunidad + FC + XX
     *
     *
     * Comprador
     */
    public final static Pattern comunidadPattern = Pattern.compile("^\\d{8}$",
	    Pattern.CASE_INSENSITIVE);

    public final static Pattern viviendaPattern = Pattern.compile(
	    "^(\\d{8})vi\\d{3}$", Pattern.CASE_INSENSITIVE);

    public final static Pattern educativoPattern = Pattern.compile(
	    "^(\\d{8})ce\\d{2}$", Pattern.CASE_INSENSITIVE);

    public final static Pattern reunionesPattern = Pattern.compile(
	    "^(\\d{8})cr\\d{2}$", Pattern.CASE_INSENSITIVE);

    public final static Pattern saludPattern = Pattern.compile(
	    "^(\\d{8})cs\\d{2}$", Pattern.CASE_INSENSITIVE);

    public final static Pattern fuentePattern = Pattern.compile(
	    "^(\\d{8})fc\\d{2}$", Pattern.CASE_INSENSITIVE);

    public final static Pattern verticePattern = Pattern.compile(
	    "^(((\\d{8})vi\\d{3})p\\d{2})v\\d{2}$", Pattern.CASE_INSENSITIVE);

    public final static Pattern parcelaPattern = Pattern.compile(
	    "^((\\d{8})vi\\d{3})p\\d{2}$", Pattern.CASE_INSENSITIVE);

    public void processValue(String value, DefaultTableModel table, int i) {
	Matcher matcher;

	matcher = comunidadPattern.matcher(value);
	if (matcher.find()) {
	    processComunidad(table, i, matcher);
	    return;
	}

	matcher = viviendaPattern.matcher(value);
	if (matcher.find()) {
	    processVivienda(table, i, matcher);
	    return;
	}

	matcher = educativoPattern.matcher(value);
	if (matcher.find()) {
	    processCentro(table, i, matcher, "centros_educativos", "cod_cedu");
	    return;
	}

	matcher = reunionesPattern.matcher(value);
	if (matcher.find()) {
	    processCentro(table, i, matcher, "centros_reuniones", "cod_creu");
	    return;
	}

	matcher = saludPattern.matcher(value);
	if (matcher.find()) {
	    processCentro(table, i, matcher, "centros_salud", "cod_csalud");
	    return;
	}

	matcher = fuentePattern.matcher(value);
	if (matcher.find()) {
	    processCentro(table, i, matcher, "fuentes_comunitarias",
		    "codigo_fc");
	    return;
	}

	int tablenameIdx = table.findColumn("tablename");
	table.setValueAt("", i, tablenameIdx);
	addWarning(table, i, "Identificador no reconocido");
    }

    // TODO
    // select a.nombre, a.cod_aldea, c.cod_com from comunidades c join
    // aldeas_pmf a on substring(c.cod_com, 0, 6) = cod_aldea and
    // st_contains(a.geom, c.geom)

    private void processCentro(DefaultTableModel table, int i, Matcher matcher,
	    String tablename, String pkname) {

	if (existsInProcessed(table, tablename, matcher.group())) {
	    addWarning(
		    table,
		    i,
		    String.format(tablename
			    + " %s duplicado en el fichero de entrada",
			    matcher.group()));
	}
	if (existsInDB(tablename, pkname, matcher.group())) {
	    addWarning(
		    table,
		    i,
		    String.format("El " + tablename
			    + " %s ya existe en la tabla", matcher.group()));
	}

	if (!existsInProcessed(table, "comunidades", matcher.group(1))
		&& !existsInDB("comunidades", "cod_com", matcher.group(1))) {
	    addWarning(table, i, String.format("La comunidad %s no existe",
		    matcher.group(1)));
	}

	int tablenameIdx = table.findColumn("tablename");
	int geomIdx = table.findColumn("geom");
	table.setValueAt(tablename, i, tablenameIdx);
	String xStr = table.getValueAt(i, 1).toString();
	String yStr = table.getValueAt(i, 2).toString();
	IGeometry geom = getGeometry(xStr, yStr);
	table.setValueAt(geom, i, geomIdx);

	// Comprobar que la geometría está dentro de la zona de interés
	// Double bbox = new Rectangle2D.Double(x, y, w, h);
	// bbox.contains(geom);

	// Comprobar que el punto está cerca de la comunidad

    }

    private void processVivienda(DefaultTableModel table, int i, Matcher matcher) {

	if (existsInProcessed(table, "informacion_general", matcher.group())) {
	    addWarning(table, i, String.format(
		    "Vivienda %s duplicada en el fichero de entrada",
		    matcher.group()));
	}
	if (existsInDB("informacion_general", "cod_viv", matcher.group())) {
	    addWarning(
		    table,
		    i,
		    String.format("Vivienda %s ya existe en la tabla",
			    matcher.group()));
	}

	if (!existsInProcessed(table, "comunidades", matcher.group(1))
		&& !existsInDB("comunidades", "cod_com", matcher.group(1))) {
	    addWarning(table, i, String.format("La comunidad %s no existe",
		    matcher.group(1)));
	}

	int tablenameIdx = table.findColumn("tablename");
	int geomIdx = table.findColumn("geom");
	table.setValueAt("informacion_general", i, tablenameIdx);
	String xStr = table.getValueAt(i, 1).toString();
	String yStr = table.getValueAt(i, 2).toString();
	IGeometry geom = getGeometry(xStr, yStr);
	table.setValueAt(geom, i, geomIdx);

	// Comprobar que la geometría está dentro de la zona de interés
	// Double bbox = new Rectangle2D.Double(x, y, w, h);
	// bbox.contains(geom);

	// Comprobar que la vivienda está cerca de la comunidad

    }

    private void processComunidad(DefaultTableModel table, int i,
	    Matcher matcher) {

	if (existsInProcessed(table, "comunidades", matcher.group())) {
	    addWarning(table, i, String.format(
		    "Comunidad %s duplicada en el fichero de entrada",
		    matcher.group()));
	}
	if (existsInDB("comunidades", "cod_com", matcher.group())) {
	    addWarning(
		    table,
		    i,
		    String.format("Comunidad %s ya existe en la tabla",
			    matcher.group()));
	}

	int tablenameIdx = table.findColumn("tablename");
	int geomIdx = table.findColumn("geom");
	table.setValueAt("comunidades", i, tablenameIdx);
	String xStr = table.getValueAt(i, 1).toString();
	String yStr = table.getValueAt(i, 2).toString();
	IGeometry geom = getGeometry(xStr, yStr);
	table.setValueAt(geom, i, geomIdx);

	// Comprobar que la geometría está dentro de la zona de interés
	// Double bbox = new Rectangle2D.Double(x, y, w, h);
	// bbox.contains(geom);
    }

    private boolean existsInProcessed(DefaultTableModel table,
	    String tablename, String code) {
	int tablenameIdx = table.findColumn("tablename");
	for (int row = 0; row < table.getRowCount(); row++) {
	    Object c = table.getValueAt(row, tablenameIdx);
	    if ((c != null) && c.toString().equalsIgnoreCase(tablename)) {
		if (code.equalsIgnoreCase(table.getValueAt(row, 0).toString())) {
		    return true;
		}
	    }
	}
	return false;
    }

    private boolean existsInDB(String tablename, String fieldname, String code) {
	DBSession session = DBSession.getCurrentSession();
	String[][] r;
	try {
	    final String whereClause = String.format("WHERE %s = '%s'",
		    fieldname, code);
	    r = session.getTable(tablename, "", new String[] { fieldname },
		    whereClause, null, false);

	    if ((r.length != 1) || (r[0] == null) || (r[0].length != 1)
		    || (r[0][0] == null)) {

		return false;
	    }
	} catch (SQLException e) {
	    logger.error(e.getStackTrace(), e);
	}

	return true;
    }

    @SuppressWarnings("unchecked")
    private void addWarning(DefaultTableModel table, int row, String msg) {
	int errorIdx = table.findColumn("Errores");
	List<String> l = (List<String>) table.getValueAt(row, errorIdx);
	if (l == null) {
	    l = new ArrayList<String>();
	    table.setValueAt(l, row, errorIdx);

	}
	l.add(msg);
    }

    private IGeometry getGeometry(String xStr, String yStr) {
	Number x = toNumeric(xStr);
	Number y = toNumeric(yStr);

	FPoint2D fpoint2d = new FPoint2D(x.doubleValue(), y.doubleValue());
	IGeometry geom = ShapeFactory.createPoint2D(fpoint2d);

	// TODO
	// IProjection crs4326 = CRSFactory.getCRS("EPSG:4326");
	// IProjection crs32616 = CRSFactory.getCRS("EPSG:32616");
	// ICoordTrans ct = crs4326.getCT(crs32616);
	// geom.reProject(ct);

	return geom;
    }

    private Number toNumeric(String v) {
	NumberFormat formatter = DoubleFormatNT.getEditingFormat();
	try {
	    return formatter.parse(v);
	} catch (ParseException e) {
	    logger.error(e.getStackTrace(), e);
	}
	return null;
    }

}

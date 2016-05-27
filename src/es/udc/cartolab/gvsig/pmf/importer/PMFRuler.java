package es.udc.cartolab.gvsig.pmf.importer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;

import es.icarto.gvsig.commons.utils.Field;
import es.icarto.gvsig.importer.Ruler;
import es.icarto.gvsig.importer.Target;

public class PMFRuler implements Ruler {

    private List<Target> targets;

    public PMFRuler() {
	targets = new ArrayList<Target>();
	targets.add(new ComunidadTarget());
	targets.add(new VivendasTarget());

	Pattern patternFuentes = Pattern.compile("^(\\d{8})fc\\d{2}$",
		Pattern.CASE_INSENSITIVE);
	Target fuentesTarget = new CentroTarget("fuentes_comunitarias",
		"codigo_fc", patternFuentes);
	targets.add(fuentesTarget);

	Pattern eduPattern = Pattern.compile("^(\\d{8})ce\\d{2}$",
		Pattern.CASE_INSENSITIVE);
	Target eduTarget = new CentroTarget("centros_educativos", "cod_cedu",
		eduPattern);
	targets.add(eduTarget);

	Pattern reuPattern = Pattern.compile("^(\\d{8})cr\\d{2}$",
		Pattern.CASE_INSENSITIVE);
	Target reuTarget = new CentroTarget("centros_reuniones", "cod_creu",
		reuPattern);
	targets.add(reuTarget);

	Pattern saludPattern = Pattern.compile("^(\\d{8})cs\\d{2}$",
		Pattern.CASE_INSENSITIVE);
	Target saludTarget = new CentroTarget("centros_salud", "cod_csalud",
		saludPattern);
	targets.add(saludTarget);
    }

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

    // TODO. En lugar de 2 o tres digitos después del código podriamos permitir
    // {1,3} o {2,3} o *

    private final static Pattern verticePattern = Pattern.compile(
	    "^(((\\d{8})vi\\d{3})p\\d{2})v\\d{2}$", Pattern.CASE_INSENSITIVE);

    private final static Pattern parcelaPattern = Pattern.compile(
	    "^((\\d{8})vi\\d{3})p\\d{2}$", Pattern.CASE_INSENSITIVE);

    public void processValue(String value, DefaultTableModel table, int i) {

	boolean anyMatch = false;
	for (Target target : targets) {
	    boolean match = target.process(value, table, i);
	    anyMatch = anyMatch || match;
	}

	if (!anyMatch) {
	    int tablenameIdx = table.findColumn("tablename");
	    table.setValueAt("", i, tablenameIdx);
	    addWarning(table, i, "Identificador no reconocido");
	}

    }

    @SuppressWarnings("unchecked")
    // TODO. To be moved to another object
    protected void addWarning(DefaultTableModel table, int row, String msg) {
	int errorIdx = table.findColumn("Errores");
	List<String> l = (List<String>) table.getValueAt(row, errorIdx);
	if (l == null) {
	    l = new ArrayList<String>();
	    table.setValueAt(l, row, errorIdx);

	}
	l.add(msg);
    }

    @Override
    public List<Field> getFields() {
	List<Field> fields = new ArrayList<Field>();
	for (Target t : targets) {
	    fields.add(t.getField());
	}
	return fields;
    }
}

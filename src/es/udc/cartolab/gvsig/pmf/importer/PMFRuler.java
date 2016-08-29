package es.udc.cartolab.gvsig.pmf.importer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import es.icarto.gvsig.commons.utils.Field;
import es.icarto.gvsig.importer.ImporterTM;
import es.icarto.gvsig.importer.Ruler;
import es.icarto.gvsig.importer.Target;

public class PMFRuler implements Ruler {

    private final List<Target> targets;
    private final NoTarget noTarget;

    public PMFRuler() {
	targets = new ArrayList<Target>();
	targets.add(new ComunidadTarget());

	Pattern vivPattern = Pattern.compile("^(\\d{8})VI\\d{3}$",
		Pattern.CASE_INSENSITIVE);
	Target vivTarget = new CentroTarget("informacion_general", "cod_viv",
		vivPattern, "VI", "%03d", "La vivienda");
	targets.add(vivTarget);

	Pattern patternFuentes = Pattern.compile("^(\\d{8})FC\\d{2}$",
		Pattern.CASE_INSENSITIVE);
	Target fuentesTarget = new CentroTarget("fuentes_comunitarias",
		"codigo_fc", patternFuentes, "FC", "%02d", "La fuente");
	targets.add(fuentesTarget);

	Pattern eduPattern = Pattern.compile("^(\\d{8})CE\\d{2}$",
		Pattern.CASE_INSENSITIVE);
	Target eduTarget = new CentroTarget("centros_educativos", "cod_cedu",
		eduPattern, "CE", "%02d", "El centro educativo");
	targets.add(eduTarget);

	Pattern reuPattern = Pattern.compile("^(\\d{8})CR\\d{2}$",
		Pattern.CASE_INSENSITIVE);
	Target reuTarget = new CentroTarget("centros_reuniones", "cod_creu",
		reuPattern, "CR", "%02d", "El centro");
	targets.add(reuTarget);

	Pattern saludPattern = Pattern.compile("^(\\d{8})CS\\d{2}$",
		Pattern.CASE_INSENSITIVE);
	Target saludTarget = new CentroTarget("centros_salud", "cod_csalud",
		saludPattern, "CS", "%02d", "El centro de salud");
	targets.add(saludTarget);

	// Pattern verticePattern = Pattern.compile(
	// "^(((\\d{8})vi\\d{3})p\\d{2})v\\d{2}$",
	// Pattern.CASE_INSENSITIVE);
	// Target verticeTarget = new VerticeTarget("limites_parcela",
	// "cod_lim_p", verticePattern);
	// targets.add(verticeTarget);

	noTarget = new NoTarget();
    }

    public final static Pattern parcelaPattern = Pattern.compile(
	    "^((\\d{8})VI\\d{3})P\\d{2}$", Pattern.CASE_INSENSITIVE);

    @Override
    public void processValue(String value, ImporterTM table, int i) {

	boolean anyMatch = false;
	for (Target target : targets) {
	    boolean match = target.process(value, table, i);
	    if (match) {
		target.checkErrors(table, i);
	    }
	    anyMatch = anyMatch || match;
	}

	if (!anyMatch) {
	    noTarget.process(value, table, i);
	    noTarget.checkErrors(table, i);
	}

    }

    @Override
    public List<Field> getFields() {
	List<Field> fields = new ArrayList<Field>();
	fields.add(noTarget.getField());
	for (Target t : targets) {
	    fields.add(t.getField());
	}
	return fields;
    }
}

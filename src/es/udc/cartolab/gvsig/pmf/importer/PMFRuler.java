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
	targets.add(new VivendasTarget());

	Pattern patternFuentes = Pattern.compile("^(\\d{9})fc\\d{2}$",
		Pattern.CASE_INSENSITIVE);
	Target fuentesTarget = new CentroTarget("fuentes_comunitarias",
		"codigo_fc", patternFuentes);
	targets.add(fuentesTarget);

	Pattern eduPattern = Pattern.compile("^(\\d{9})ce\\d{2}$",
		Pattern.CASE_INSENSITIVE);
	Target eduTarget = new CentroTarget("centros_educativos", "cod_cedu",
		eduPattern);
	targets.add(eduTarget);

	Pattern reuPattern = Pattern.compile("^(\\d{9})cr\\d{2}$",
		Pattern.CASE_INSENSITIVE);
	Target reuTarget = new CentroTarget("centros_reuniones", "cod_creu",
		reuPattern);
	targets.add(reuTarget);

	Pattern saludPattern = Pattern.compile("^(\\d{9})cs\\d{2}$",
		Pattern.CASE_INSENSITIVE);
	Target saludTarget = new CentroTarget("centros_salud", "cod_csalud",
		saludPattern);
	targets.add(saludTarget);

	noTarget = new NoTarget();
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
	    "^(((\\d{9})vi\\d{3})p\\d{2})v\\d{2}$", Pattern.CASE_INSENSITIVE);

    private final static Pattern parcelaPattern = Pattern.compile(
	    "^((\\d{9})vi\\d{3})p\\d{2}$", Pattern.CASE_INSENSITIVE);

    public void processValue(String value, ImporterTM table, int i) {

	boolean anyMatch = false;
	for (Target target : targets) {
	    boolean match = target.process(value, table, i);
	    anyMatch = anyMatch || match;
	}

	if (!anyMatch) {
	    noTarget.process(value, table, i);
	}

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

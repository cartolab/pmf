package es.udc.cartolab.gvsig.pmf.forms;

import java.util.HashMap;

public abstract class ColumnNamesTranslator {

    private static HashMap<String, String> translations = null;

    private static void fillTranslations() {
	translations = new HashMap<String, String>();
	translations.put("cod_com", "Comunidad");
	translations.put("cod_viv", "Parcela");
	translations.put("tipo_cul", "Cultivo");
	translations.put("area", "Área cultivada (Mz.)");
	translations.put("vol_proc", "Vol. producido (Qt.)");
	translations.put("vol_cons", "Vol. consumido (Qt.)");
    }

    public static String getLongName(String name) {
	if (translations == null)
	    fillTranslations();
	return translations.get(name);
    }

}

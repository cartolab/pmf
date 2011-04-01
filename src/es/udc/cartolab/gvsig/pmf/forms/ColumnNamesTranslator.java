package es.udc.cartolab.gvsig.pmf.forms;

import java.util.HashMap;

public abstract class ColumnNamesTranslator {

	private static HashMap<String, String> translations = null;

	private static void fillTranslations() {
		translations = new HashMap<String, String>();
		translations.put("cod_com", "Comunidad");
		translations.put("tipo_cul", "Cultivo");
		translations.put("area", "�rea cultivada (Mz.)");
		translations.put("vol_proc", "Volumen producido (Qt.)");
		translations.put("vol_cons", "Volumen consumido (Qt.)");
	}

	public static String getLongName(String name) {
		if (translations == null)
			fillTranslations();
		return translations.get(name);
	}

}

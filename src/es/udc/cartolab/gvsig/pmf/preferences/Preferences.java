package es.udc.cartolab.gvsig.pmf.preferences;

import java.io.File;

public class Preferences {

	public static String  getXMLFileName() {
		String xmlFile = "gvSIG" + File.separator
		+ "extensiones" + File.separator
		+ "es.udc.cartolab.gvsig.pmf" + File.separator
		+ "pmf.xml";
		return xmlFile;
	}

}

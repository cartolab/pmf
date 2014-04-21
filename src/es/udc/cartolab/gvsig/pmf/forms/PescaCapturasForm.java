package es.udc.cartolab.gvsig.pmf.forms;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class PescaCapturasForm extends AbstractSubForm {

    public static final String NAME = "pesca_capturas";
    public static String[] colNames = {
"especie", "especie_otra", "cantidad", "precio"
    };
    public static String[] colAlias = {
"Especie", "Otra", "Cantidad", "Precio"
    };
    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}

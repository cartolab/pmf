package es.udc.cartolab.gvsig.pmf.forms;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class PresenciaInstitucionalForm extends AbstractSubForm {

    public static final String NAME = "presencia_institucional";
    public static String[] colNames = {
"org_exist", "func", "resp"
    };
    public static String[] colAlias = {
"Nombre", "Función", "Responsable"
    };
    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}

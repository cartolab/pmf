package es.udc.cartolab.gvsig.pmf.forms;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class CultivosForm extends AbstractSubForm {

    public static final String NAME = "cultivos";
    public static final String COD_PARCELA = "cod_parcela";
    public static final Object COD_COM = "cod_com";
    public static String[] colNames = { "cultivo", "area", "vol_prod",
	    "vol_con" };
    public static String[] colAlias = { "Tipo", "Area", "Producido",
	    "Consumido" };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}

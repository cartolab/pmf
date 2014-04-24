package es.udc.cartolab.gvsig.pmf.forms;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class BalancesForm extends AbstractSubForm {

    public static final String NAME = "balances";
    public static String[] colNames = { "cultivo", "f_siembra", "f_cosecha",
	    "coste_total", "venta_total", "beneficio" };
    public static String[] colAlias = { "Cultivo", "Fecha siembra",
	    "Fecha cosecha", "Coste (L)", "Venta (L)", "Beneficio (L)" };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}

package es.udc.cartolab.gvsig.pmf.forms;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;
import es.icarto.gvsig.navtableforms.gui.tables.handler.AlphanumericTableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.handler.BaseTableHandler;

@SuppressWarnings("serial")
public class BalancesForm extends AbstractSubForm {

    public static final String NAME = "balances";
    private static final String PKFIELD = "cod_balance";
    public static String[] colNames = { "rubro", "f_siembra", "f_cosecha",
	    "coste_total", "venta_total", "beneficio", "rendimiento_prod" };
    public static String[] colAlias = { "Rubro", "Fecha siembra",
	    "Fecha cosecha", "Coste (L)", "Venta (L)", "Beneficio (L)",
	    "Rendimiento ({Kg,Ud}/Ha)" };

    public BalancesForm() {
	addTableHandler(new AlphanumericTableHandler(VentasForm.NAME,
		getWidgetComponents(), PKFIELD, VentasForm.colNames4Balances,
		VentasForm.colAlias4Balances));
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected String getPrimaryKeyValue() {
	return getFormController().getValue(PKFIELD);
    }

    @Override
    protected void fillSpecificValues() {
	if ((getPrimaryKeyValue() != null)
		&& (getPrimaryKeyValue().length() > 1)) {
	    super.fillSpecificValues();
	} else {
	    BaseTableHandler baseTableHandler = getTableHandlers().get(0);
	    baseTableHandler.removeListeners();
	}
    }
}

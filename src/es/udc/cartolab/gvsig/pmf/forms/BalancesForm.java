package es.udc.cartolab.gvsig.pmf.forms;

import javax.swing.JComboBox;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;
import es.icarto.gvsig.navtableforms.gui.tables.handler.AlphanumericTableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.handler.BaseTableHandler;
import es.icarto.gvsig.navtableforms.ormlite.domainvalidator.listeners.DependentComboboxHandler;

@SuppressWarnings("serial")
public class BalancesForm extends AbstractSubForm {

    public static final String NAME = "balances";
    public static final String PKFIELD = "cod_balance";
    public static final String RUBRO = "rubro";
    public static final String COD_PARCELA = "cod_parcela";

    public static String[] colNames = { "rubro", "f_siembra", "f_cosecha",
	    "coste_total", "venta_total", "beneficio", "rendimiento_prod" };
    public static String[] colAlias = { "Rubro", "Fecha siembra",
	    "Fecha cosecha", "Coste (L)", "Venta (L)", "Beneficio (L)",
	    "Rendimiento ({Kg,Ud}/Ha)" };

    private DependentComboboxHandler rubroDependencyHandler;
    private final JComboBox tipoRubroCB;
    private final JComboBox rubroCB;

    public BalancesForm() {
	super();
	addTableHandler(new AlphanumericTableHandler(VentasForm.NAME,
		getWidgetComponents(), PKFIELD, VentasForm.colNames4Balances,
		VentasForm.colAlias4Balances));
	tipoRubroCB = (JComboBox) getWidgets().get("tipo_rubro");
	rubroCB = (JComboBox) getWidgets().get("rubro");
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
    public void setListeners() {
	super.setListeners();

	rubroDependencyHandler = new DependentComboboxHandler(this,
		tipoRubroCB, rubroCB);
	tipoRubroCB.addActionListener(rubroDependencyHandler);
    }

    @Override
    public void removeListeners() {
	super.removeListeners();
	tipoRubroCB.removeFocusListener(rubroDependencyHandler);
    }

    @Override
    protected void fillSpecificValues() {
	rubroDependencyHandler.updateComboBoxValues();
	if ((getPrimaryKeyValue() != null)
		&& (getPrimaryKeyValue().length() > 1)) {
	    super.fillSpecificValues();
	} else {
	    BaseTableHandler baseTableHandler = getTableHandlers().get(0);
	    baseTableHandler.removeListeners();
	}
    }
}

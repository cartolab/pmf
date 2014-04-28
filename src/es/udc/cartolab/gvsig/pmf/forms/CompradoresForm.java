package es.udc.cartolab.gvsig.pmf.forms;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.handler.AlphanumericUpdateTableHandler;

@SuppressWarnings("serial")
public class CompradoresForm extends BasicAbstractForm {

    public static final String NAME = "compradores";
    public static final String PKFIELD = "cod_comprador";
    public static String[] colNames = {};
    public static String[] colAlias = {};

    public CompradoresForm(FLyrVect layer) {
	super(layer);
	addTableHandler(new AlphanumericUpdateTableHandler(VentasForm.NAME,
		getWidgetComponents(), PKFIELD,
		VentasForm.colNames4Compradores,
		VentasForm.colAlias4Compradores));
    }

    @Override
    protected String getPrimaryKeyValue() {
	return getFormController().getValue(PKFIELD);
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }
}

package es.udc.cartolab.gvsig.pmf.forms;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.handler.AlphanumericTableHandler;

@SuppressWarnings("serial")
public class ViviendasForm extends BasicAbstractForm {

    public static final String NAME = "viviendas";
    public static final String PKFIELD = "cod_viv";

    public ViviendasForm(FLyrVect layer) {
	super(layer);

	addTableHandler(new AlphanumericTableHandler("pesca_capturas",
		getWidgetComponents(), PKFIELD, PescaCapturasForm.colNames,
		PescaCapturasForm.colAlias));
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

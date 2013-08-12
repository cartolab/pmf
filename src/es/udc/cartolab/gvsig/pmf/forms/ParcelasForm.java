package es.udc.cartolab.gvsig.pmf.forms;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.handler.AlphanumericTableHandler;

@SuppressWarnings("serial")
public class ParcelasForm extends BasicAbstractForm {

    public static final String NAME = "parcelas";
    public static final String PKFIELD = "cod_viv";

    public ParcelasForm(FLyrVect layer) {
	super(layer);

	addTableHandler(new AlphanumericTableHandler("cultivos",
		getWidgetComponents(), PKFIELD, CultivosForm.colNames,
		CultivosForm.colAlias));
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

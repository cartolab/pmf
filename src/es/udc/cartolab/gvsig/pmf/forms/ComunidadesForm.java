package es.udc.cartolab.gvsig.pmf.forms;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.handler.AlphanumericTableHandler;

@SuppressWarnings("serial")
public class ComunidadesForm extends BasicAbstractForm {

    public static final String NAME = "comunidades";
    public static final String PKFIELD = "cod_com";

    public ComunidadesForm(FLyrVect layer) {
	super(layer);

	addTableHandler(new AlphanumericTableHandler("organizaciones_base",
		getWidgetComponents(), PKFIELD,
		OrganizacionesBaseForm.colNames,
		OrganizacionesBaseForm.colAlias));
	addTableHandler(new AlphanumericTableHandler("presencia_institucional",
		getWidgetComponents(), PKFIELD,
		PresenciaInstitucionalForm.colNames,
		PresenciaInstitucionalForm.colAlias));
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

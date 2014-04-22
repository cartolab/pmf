package es.udc.cartolab.gvsig.pmf.forms;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.handler.AlphanumericTableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.handler.VectorialTableHandler;

@SuppressWarnings("serial")
public class ComunidadesForm extends BasicAbstractForm {

    public static final String NAME = "comunidades";
    public static final String PKFIELD = "cod_com";

    public ComunidadesForm(FLyrVect layer) {
	super(layer);

	addTableHandler(new AlphanumericTableHandler(
		OrganizacionesBaseForm.NAME, getWidgetComponents(), PKFIELD,
		OrganizacionesBaseForm.colNames,
		OrganizacionesBaseForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(
		PresenciaInstitucionalForm.NAME, getWidgetComponents(),
		PKFIELD, PresenciaInstitucionalForm.colNames,
		PresenciaInstitucionalForm.colAlias));
	addTableHandler(new VectorialTableHandler(CentrosEducativosForm.NAME,
		getWidgetComponents(), PKFIELD, CentrosEducativosForm.colNames,
		CentrosEducativosForm.colAlias));
	addTableHandler(new VectorialTableHandler(CentrosSaludForm.NAME,
		getWidgetComponents(), PKFIELD, CentrosSaludForm.colNames,
		CentrosSaludForm.colAlias));
	addTableHandler(new VectorialTableHandler(CentrosReunionesForm.NAME,
		getWidgetComponents(), PKFIELD, CentrosReunionesForm.colNames,
		CentrosReunionesForm.colAlias));
	addTableHandler(new VectorialTableHandler(InformacionGeneralForm.NAME,
		getWidgetComponents(), PKFIELD, InformacionGeneralForm.colNames,
		InformacionGeneralForm.colAlias));
	addTableHandler(new VectorialTableHandler(FuentesComunitariasForm.NAME,
		getWidgetComponents(), PKFIELD,
		FuentesComunitariasForm.colNames,
		FuentesComunitariasForm.colAlias));
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

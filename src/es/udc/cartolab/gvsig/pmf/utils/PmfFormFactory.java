package es.udc.cartolab.gvsig.pmf.utils;

import java.util.HashSet;
import java.util.Set;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.AbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;
import es.icarto.gvsig.navtableforms.utils.DBConnectionBaseFormFactory;
import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.pmf.forms.BalancesForm;
import es.udc.cartolab.gvsig.pmf.forms.CentrosEducativosForm;
import es.udc.cartolab.gvsig.pmf.forms.CentrosReunionesForm;
import es.udc.cartolab.gvsig.pmf.forms.CentrosSaludForm;
import es.udc.cartolab.gvsig.pmf.forms.CompradoresForm;
import es.udc.cartolab.gvsig.pmf.forms.ComunidadesForm;
import es.udc.cartolab.gvsig.pmf.forms.CultivosForm;
import es.udc.cartolab.gvsig.pmf.forms.FuentesComunitariasForm;
import es.udc.cartolab.gvsig.pmf.forms.InformacionGeneralForm;
import es.udc.cartolab.gvsig.pmf.forms.LimitesParcelaForm;
import es.udc.cartolab.gvsig.pmf.forms.OrganizacionesBaseForm;
import es.udc.cartolab.gvsig.pmf.forms.ParcelasForm;
import es.udc.cartolab.gvsig.pmf.forms.PescaCapturasForm;
import es.udc.cartolab.gvsig.pmf.forms.PresenciaInstitucionalForm;
import es.udc.cartolab.gvsig.pmf.forms.VentasForm;

public class PmfFormFactory extends DBConnectionBaseFormFactory {

    private static PmfFormFactory instance = null;

    private static final Set<String> mainFormNames = new HashSet<String>();

    static {
	mainFormNames.add(ComunidadesForm.NAME);
	mainFormNames.add(CentrosEducativosForm.NAME);
	mainFormNames.add(CentrosReunionesForm.NAME);
	mainFormNames.add(CentrosSaludForm.NAME);
	mainFormNames.add(ParcelasForm.NAME);
	mainFormNames.add(InformacionGeneralForm.NAME);
	mainFormNames.add(LimitesParcelaForm.NAME);
	mainFormNames.add(FuentesComunitariasForm.NAME);
	mainFormNames.add(CompradoresForm.NAME);
	instance = new PmfFormFactory();
    }

    public static void registerFormFactory() {
	FormFactory.registerFormFactory(instance);
    }

    public static PmfFormFactory getInstance() {
	return instance;
    }

    private PmfFormFactory() {
    }

    @Override
    public AbstractForm createForm(FLyrVect layer) {
	if (layer != null) {
	    if (layer.getName().equals(ComunidadesForm.NAME)) {
		return new ComunidadesForm(layer);
	    } else if (layer.getName().equals(CentrosEducativosForm.NAME)) {
		return new CentrosEducativosForm(layer);
	    } else if (layer.getName().equals(CentrosReunionesForm.NAME)) {
		return new CentrosReunionesForm(layer);
	    } else if (layer.getName().equals(CentrosSaludForm.NAME)) {
		return new CentrosSaludForm(layer);
	    } else if (layer.getName().equals(ParcelasForm.NAME)) {
		return new ParcelasForm(layer);
	    } else if (layer.getName().equals(InformacionGeneralForm.NAME)) {
		return new InformacionGeneralForm(layer);
	    } else if (layer.getName().equals(LimitesParcelaForm.NAME)) {
		return new LimitesParcelaForm(layer);
	    } else if (layer.getName().equals(FuentesComunitariasForm.NAME)) {
		return new FuentesComunitariasForm(layer);
	    } else if (layer.getName().equals(CompradoresForm.NAME)) {
		return new CompradoresForm(layer);
	    }
	}
	return null;
    }

    @Override
    public AbstractSubForm createSubForm(String tableName) {
	if (tableName != null) {
	    if (tableName.equals(CultivosForm.NAME)) {
		return new CultivosForm();
	    } else if (tableName.equals(OrganizacionesBaseForm.NAME)) {
		return new OrganizacionesBaseForm();
	    } else if (tableName.equals(PescaCapturasForm.NAME)) {
		return new PescaCapturasForm();
	    } else if (tableName.equals(PresenciaInstitucionalForm.NAME)) {
		return new PresenciaInstitucionalForm();
	    } else if (tableName.equals(BalancesForm.NAME)) {
		return new BalancesForm();
	    } else if (tableName.equals(VentasForm.NAME)) {
		return new VentasForm();
	    }
	}
	return null;
    }

    @Override
    public AbstractForm createSingletonForm(FLyrVect arg0) {
	return null;
    }

    @Override
    public AbstractForm createForm(String layerName) {
	FLyrVect layer = new TOCLayerManager().getLayerByName(layerName);
	return createForm(layer);
    }

    @Override
    public AbstractForm createSingletonForm(String layerName) {
	FLyrVect layer = new TOCLayerManager().getLayerByName(layerName);
	return createSingletonForm(layer);
    }

    @Override
    public boolean hasMainForm(String layerName) {
	return mainFormNames.contains(layerName);
    }

    @Override
    public boolean allLayersLoaded() {
	for (String layer : mainFormNames) {
	    if (new TOCLayerManager().getLayerByName(layer) == null) {
		return false;
	    }
	}
	return true;
    }

    @Override
    public void loadLayer(String layerName) {
	loadLayer(layerName, PmfConstants.DATA_SCHEMA);
    }

    @Override
    public void loadTable(String tableName) {
	loadTable(tableName, PmfConstants.DATA_SCHEMA);
    }

}

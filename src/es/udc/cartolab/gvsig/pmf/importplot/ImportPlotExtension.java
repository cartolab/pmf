package es.udc.cartolab.gvsig.pmf.importplot;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.cit.gvsig.fmap.core.FShape;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.pmf.forms.ComunidadesForm;
import es.udc.cartolab.gvsig.pmf.forms.ParcelasForm;
import es.udc.cartolab.gvsig.pmf.forms.ViviendasForm;
import es.udc.cartolab.gvsig.pmf.utils.PmfConstants;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class ImportPlotExtension extends Extension {

    @Override
    public void execute(String actionCommand) {

	// We retrieve all layers and filter them so we keep
	// only the point ones
	List<FLyrVect> validLayers = new ArrayList<FLyrVect>();
	FLyrVect[] layers = new TOCLayerManager().getAllLayers();
	for (FLyrVect layer : layers) {
	    try {
		if (layer.getShapeType() == FShape.POINT) {
		    validLayers.add(layer);
		}
	    } catch (ReadDriverException e) {
		e.printStackTrace();
	    }
	}

	try {
	    String[] codComs = DBSession.getCurrentSession().getDistinctValues(
		    ComunidadesForm.NAME, PmfConstants.dataSchema,
		    ComunidadesForm.PKFIELD, true, false);
	    String[] codVivs = DBSession.getCurrentSession().getDistinctValues(
		    ViviendasForm.NAME, PmfConstants.dataSchema,
		    ViviendasForm.PKFIELD, true, false);

	    ImportPlotDialog dialog = new ImportPlotDialog(
		    validLayers.toArray(new FLyrVect[0]), codComs, codVivs);
	    PluginServices.getMDIManager().addWindow(dialog);
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

    protected void registerIcons() {
	PluginServices.getIconTheme().registerDefault(
		"import-plot-launcher-icon",
		this.getClass().getClassLoader()
			.getResource("images/queries.png"));
    }

    @Override
    public void initialize() {
	registerIcons();
    }

    @Override
    public boolean isEnabled() {
	return FormFactory.checkLayerLoadedRegistered(ParcelasForm.NAME);
    }

    @Override
    public boolean isVisible() {
	return true;
    }

}

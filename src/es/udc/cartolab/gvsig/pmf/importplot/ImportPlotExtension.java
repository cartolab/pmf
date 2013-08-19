package es.udc.cartolab.gvsig.pmf.importplot;

import java.util.ArrayList;
import java.util.List;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.cit.gvsig.fmap.core.FShape;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;

import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.pmf.forms.ComunidadesForm;
import es.udc.cartolab.gvsig.pmf.forms.ParcelasForm;
import es.udc.cartolab.gvsig.pmf.forms.ViviendasForm;

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
	    FLyrVect layer = new TOCLayerManager()
		    .getLayerByName(ComunidadesForm.NAME);
	    SelectableDataSource comSource = layer.getSource().getRecordset();

	    layer = new TOCLayerManager().getLayerByName(ViviendasForm.NAME);
	    SelectableDataSource vivSource = layer.getSource().getRecordset();

	    ImportPlotDialog dialog = new ImportPlotDialog(
		    validLayers.toArray(new FLyrVect[0]), comSource, vivSource);
	    PluginServices.getMDIManager().addWindow(dialog);
	} catch (ReadDriverException e) {
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

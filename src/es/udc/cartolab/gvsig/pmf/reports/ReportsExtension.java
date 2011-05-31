package es.udc.cartolab.gvsig.pmf.reports;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.fmap.layers.FLayers;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.ReadableVectorial;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;
import com.iver.cit.gvsig.project.documents.view.gui.BaseView;

import es.udc.cartolab.gvsig.navtableforms.Utils;
import es.udc.cartolab.gvsig.pmf.reports.community.SelectCommunityDialog;
import es.udc.cartolab.gvsig.pmf.reports.plot.SelectPlotDialog;

public class ReportsExtension extends Extension {

    FLyrVect layer;
    BaseView view = null;
    private String communityLayerName;
    private String plotLayerName;

    /**
     * String used for identifying the output directory parameter.
     */
    private String DEFAULT_OUTPUT_DIR_KEY_NAME = "ReportOutput";

    public final String getDefaultReportOutputDirKeyName() {
	return DEFAULT_OUTPUT_DIR_KEY_NAME;
    }

    @Override
    public void execute(String actionCommand) {

	if (actionCommand.equals("comunidad")) {
	    try {

		layer = getLayerNameFromXML(communityLayerName);

		ReadableVectorial readable = layer.getSource();

		SelectableDataSource source;
		source = readable.getRecordset();

		SelectCommunityDialog dialog = new SelectCommunityDialog(
			source, view);
		PluginServices.getMDIManager().addWindow(dialog);

	    } catch (ReadDriverException e) {
		e.printStackTrace();
	    }
	} else {
	    try {

		layer = getLayerNameFromXML(plotLayerName);

		ReadableVectorial readable = layer.getSource();

		SelectableDataSource source;
		source = readable.getRecordset();

		SelectPlotDialog dialog = new SelectPlotDialog(source, view);
		PluginServices.getMDIManager().addWindow(dialog);

	    } catch (ReadDriverException e) {
		e.printStackTrace();
	    }

	}

    }

    protected void registerIcons() {
	PluginServices.getIconTheme().registerDefault(
		"plot-reports-launcher-icon",
		this.getClass().getClassLoader().getResource(
			"images/report-plot.png"));
	PluginServices.getIconTheme().registerDefault(
		"community-reports-launcher-icon",
		this.getClass().getClassLoader().getResource(
			"images/report-community.png"));
    }

    private FLyrVect getLayerNameFromXML(String layerName) {
	return Utils.getFlyrVect(view, layerName);
    }

    @Override
    public void initialize() {
	registerIcons();
    }

    @Override
    public boolean isEnabled() {
	IWindow window = PluginServices.getMDIManager().getActiveWindow();
	boolean hasCommunity = false;
	boolean hasPlot = false;
	if (window instanceof BaseView) {
	    view = (BaseView) window;
	    FLayers layers = view.getMapControl().getMapContext().getLayers();
	    for (int i = 0; i < layers.getLayersCount(); i++) {
		if (layers.getLayer(i).getName().toLowerCase().equals(
			"comunidad")) {
		    communityLayerName = layers.getLayer(i).getName();
		    hasCommunity = true;
		}
		if (layers.getLayer(i).getName().toLowerCase().equals(
			"vivienda")) {
		    plotLayerName = layers.getLayer(i).getName();
		    hasPlot = true;
		}
	    }
	}

	return (hasCommunity & hasPlot);
    }

    @Override
    public boolean isVisible() {
	return true;
    }

}

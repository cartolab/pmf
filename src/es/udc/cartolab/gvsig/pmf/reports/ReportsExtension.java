package es.udc.cartolab.gvsig.pmf.reports;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.ReadableVectorial;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;

import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.pmf.forms.ComunidadesForm;
import es.udc.cartolab.gvsig.pmf.forms.ViviendasForm;
import es.udc.cartolab.gvsig.pmf.reports.community.SelectCommunityDialog;
import es.udc.cartolab.gvsig.pmf.reports.plot.SelectPlotDialog;

public class ReportsExtension extends Extension {

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

		FLyrVect layer = new TOCLayerManager()
			.getLayerByName(ComunidadesForm.NAME);

		ReadableVectorial readable = layer.getSource();

		SelectableDataSource source;
		source = readable.getRecordset();

		SelectCommunityDialog dialog = new SelectCommunityDialog(source);
		PluginServices.getMDIManager().addWindow(dialog);

	    } catch (ReadDriverException e) {
		e.printStackTrace();
	    }
	} else {
	    try {

		FLyrVect layer = new TOCLayerManager()
			.getLayerByName(ViviendasForm.NAME);

		ReadableVectorial readable = layer.getSource();

		SelectableDataSource source;
		source = readable.getRecordset();

		SelectPlotDialog dialog = new SelectPlotDialog(source);
		PluginServices.getMDIManager().addWindow(dialog);

	    } catch (ReadDriverException e) {
		e.printStackTrace();
	    }

	}

    }

    protected void registerIcons() {
	PluginServices.getIconTheme().registerDefault(
		"plot-reports-launcher-icon",
		this.getClass().getClassLoader()
			.getResource("images/report-plot.png"));
	PluginServices.getIconTheme().registerDefault(
		"community-reports-launcher-icon",
		this.getClass().getClassLoader()
			.getResource("images/report-community.png"));
    }

    @Override
    public void initialize() {
	registerIcons();
    }

    @Override
    public boolean isEnabled() {
	return FormFactory.allLayersLoadedRegistered();
    }

    @Override
    public boolean isVisible() {
	return true;
    }

}

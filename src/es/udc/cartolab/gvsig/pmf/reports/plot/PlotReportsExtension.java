package es.udc.cartolab.gvsig.pmf.reports.plot;

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

public class PlotReportsExtension extends Extension {

	FLyrVect layer;
	BaseView view = null;
	private String layerName;

	/**
	 * String used for identifying the output directory parameter.
	 */
	private String DEFAULT_OUTPUT_DIR_KEY_NAME = "ReportOutput";

	public final String getDefaultReportOutputDirKeyName() {
		return DEFAULT_OUTPUT_DIR_KEY_NAME;
	}

	@Override
	public void execute(String actionCommand) {

		/*
		 * HashMap<String,Boolean> orderings = new HashMap<String,Boolean>();
		 * orderings.put("LimiteN", true);
		 * 
		 * if (ReportsGenerator.generateReport("comunidades.shp", orderings,
		 * "/home/jlopez/ultraSimple.jrxml")) {
		 * System.out.println("Report generated!"); } else {
		 * System.out.println("Some error happened while generating the report..."
		 * ); }
		 */
		try {

			layer = getLayerNameFromXML();

			ReadableVectorial readable = layer.getSource();

			SelectableDataSource source;
			source = readable.getRecordset();

			SelectPlotDialog dialog = new SelectPlotDialog(source, view);
			PluginServices.getMDIManager().addWindow(dialog);

		} catch (ReadDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void registerIcons() {
		PluginServices.getIconTheme().registerDefault(
				"plot-reports-launcher-icon",
				this.getClass().getClassLoader().getResource(
						"images/report.png"));
	}

	private FLyrVect getLayerNameFromXML() {
		return Utils.getFlyrVect(view, layerName);
	}

	@Override
	public void initialize() {
		registerIcons();
	}

	@Override
	public boolean isEnabled() {
		IWindow window = PluginServices.getMDIManager().getActiveWindow();
		boolean isEnabled = false;
		if (window instanceof BaseView) {
			view = (BaseView) window;
			FLayers layers = view.getMapControl().getMapContext().getLayers();
			for (int i = 0; i < layers.getLayersCount(); i++) {
				layerName = layers.getLayer(i).getName();
				if (layerName.toLowerCase().equals("vivienda")) {
					isEnabled = true;
					break;
				}
			}
		}

		return isEnabled;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

}

package es.udc.cartolab.gvsig.pmf.reports;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.fmap.layers.FLayer;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.ReadableVectorial;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;
import com.iver.cit.gvsig.project.documents.view.gui.BaseView;

import es.udc.cartolab.gvsig.navtableforms.Utils;
import es.udc.cartolab.gvsig.navtableforms.ormlite.ORMLite;
import es.udc.cartolab.gvsig.navtableforms.ormlite.ORMLiteDataBase.ORMLiteTable;
import es.udc.cartolab.gvsig.pmf.preferences.Preferences;

public class ReportsExtension extends Extension {

	FLyrVect layer;
	BaseView view = null;
	private String layerName;

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

			new RtfCommunityReport(0, source, "test.rtf", view);

		} catch (ReadDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private FLyrVect getLayerNameFromXML() {
		return Utils.getFlyrVect(view, layerName);
	}

	@Override
	public void initialize() {

	}

	@Override
	public boolean isEnabled() {
		IWindow window = PluginServices.getMDIManager().getActiveWindow();
		boolean isEnabled = false;
		if (window instanceof BaseView) {
			view = (BaseView) window;
			FLayer[] actives = view.getMapControl().getMapContext().getLayers()
					.getActives();
			if (1 == actives.length) {
				layerName = actives[0].getName();
				ORMLiteTable table = ORMLite.getDataBaseObject(
						Preferences.getXMLFileName()).getTable(layerName);
				if (table != null) {
					isEnabled = true;
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

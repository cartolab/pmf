package es.udc.cartolab.gvsig.pmf.queries;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.project.documents.view.gui.BaseView;

import es.udc.cartolab.gvsig.navtableforms.Utils;

public class QueryExtension extends Extension {

    private final String neededLayers[] = { "vivienda", "parcela", "comunidad",
	    "areas_protegidas" };

    private BaseView view;

    @Override
    public void initialize() {
	PluginServices.getIconTheme().registerDefault(
		"query-launcher-icon",
		this.getClass().getClassLoader()
			.getResource("images/report.png"));
    }

    @Override
    public void execute(String actionCommand) {
	SelectQueryDialog d = new SelectQueryDialog();
    }

    @Override
    public boolean isEnabled() {
	IWindow window = PluginServices.getMDIManager().getActiveWindow();
	boolean isView = false;
	boolean neededLayersArePresent = true;
	if (window instanceof BaseView) {
	    isView = true;
	    view = (BaseView) window;
	    for (String layerName : neededLayers) {
		if (Utils.getFlyrVect(view, layerName) == null) {
		    neededLayersArePresent = false;
		    break;
		}
	    }

	}
	return isView && neededLayersArePresent;
    }

    @Override
    public boolean isVisible() {
	return true;
    }

}

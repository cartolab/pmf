package es.udc.cartolab.gvsig.pmf.forms;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.About;
import com.iver.cit.gvsig.fmap.layers.FLayer;
import com.iver.cit.gvsig.gui.panels.FPanelAbout;
import com.iver.cit.gvsig.project.documents.view.gui.BaseView;

import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.udc.cartolab.gvsig.navtable.AbstractNavTable;
import es.udc.cartolab.gvsig.pmf.utils.PmfFormFactory;

public class FormsLauncherExtension extends Extension {

    private BaseView view = null;
    private String layerName;

    public void execute(String actionCommand) {

	AbstractNavTable dialog = FormFactory.createFormRegistered(layerName);

	if (dialog != null) {
	    if (dialog.init()) {
		PluginServices.getMDIManager().addWindow(dialog);
	    }
	}
    }

    protected void registerIcons() {
	PluginServices.getIconTheme()
		.registerDefault(
			"forms-launcher-icon",
			this.getClass().getClassLoader()
				.getResource("images/form.png"));
    }

    public void initialize() {
	registerIcons();
	// CopyFeaturesExtension cfe = ((CopyFeaturesExtension) PluginServices
	// .getExtension(CopyFeaturesExtension.class));
	// cfe.setDefaultPath(LoadDataConfigDialog.getConfigPath(false));
	About about = (About) PluginServices.getExtension(About.class);
	FPanelAbout panelAbout = about.getAboutPanel();
	java.net.URL aboutURL = this.getClass().getResource("/about.htm");
	panelAbout.addAboutUrl("PMF", aboutURL);
	PmfFormFactory.registerFormFactory();
    }

    public boolean isEnabled() {
	IWindow window = PluginServices.getMDIManager().getActiveWindow();
	boolean isEnabled = false;
	if (window instanceof BaseView) {
	    view = (BaseView) window;
	    FLayer[] actives = view.getMapControl().getMapContext().getLayers()
		    .getActives();
	    if (1 == actives.length) {
		layerName = actives[0].getName();
		isEnabled = FormFactory.hasMainFormRegistered(layerName);
	    }
	}

	return isEnabled;
    }

    public boolean isVisible() {
	return true;
    }
}

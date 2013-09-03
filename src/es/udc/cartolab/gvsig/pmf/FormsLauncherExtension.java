package es.udc.cartolab.gvsig.pmf;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.About;
import com.iver.cit.gvsig.fmap.layers.FLayer;
import com.iver.cit.gvsig.gui.panels.FPanelAbout;
import com.iver.cit.gvsig.project.documents.view.gui.BaseView;
import com.iver.utiles.extensionPoints.ExtensionPoints;
import com.iver.utiles.extensionPoints.ExtensionPointsSingleton;

import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.udc.cartolab.gvsig.navtable.AbstractNavTable;
import es.udc.cartolab.gvsig.pmf.utils.PmfConstants;
import es.udc.cartolab.gvsig.pmf.utils.PmfFormFactory;
import es.udc.cartolab.gvsig.pmf.utils.PmfTocMenuEntry;
import es.udc.cartolab.gvsig.tools.CopyFeaturesExtension;

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
	// Workaround to use another icon for CopyFeatures extension instead of
	// the default
	PluginServices.getIconTheme().registerDefault(
		CopyFeaturesExtension.COPY_FEATURES_ICON,
		this.getClass().getClassLoader()
			.getResource("images/copy_features.png"));
	PluginServices.getIconTheme()
		.registerDefault(
			"forms-launcher-icon",
			this.getClass().getClassLoader()
				.getResource("images/form.png"));
    }

    public void initialize() {
	registerIcons();
	// Entry at TOC contextual menu
	ExtensionPoints extensionPoints = ExtensionPointsSingleton
		.getInstance();
	extensionPoints.add("View_TocActions", "PMF", new PmfTocMenuEntry());
	// CopyFeaturesExtension cfe = ((CopyFeaturesExtension) PluginServices
	// .getExtension(CopyFeaturesExtension.class));
	// cfe.setDefaultPath(LoadDataConfigDialog.getConfigPath(false));
	About about = (About) PluginServices.getExtension(About.class);
	FPanelAbout panelAbout = about.getAboutPanel();
	java.net.URL aboutURL = this.getClass().getResource("/about.htm");
	panelAbout.addAboutUrl("PMF", aboutURL);
	PmfFormFactory.registerFormFactory();
    }

    @Override
    public void postInitialize() {
	// We configure the CopyFeatures dialog to open in a predefined folder
	CopyFeaturesExtension.setDefaultPath(PmfConstants.GPS_MATCHING_FILES);
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

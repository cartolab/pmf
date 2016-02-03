package es.udc.cartolab.gvsig.pmf;

import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.About;
import com.iver.cit.gvsig.fmap.layers.FLayer;
import com.iver.cit.gvsig.gui.panels.FPanelAbout;
import com.iver.cit.gvsig.project.documents.view.gui.BaseView;
import com.iver.utiles.extensionPoints.ExtensionPoints;
import com.iver.utiles.extensionPoints.ExtensionPointsSingleton;

import es.icarto.gvsig.commons.AbstractExtension;
import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.udc.cartolab.gvsig.navtable.AbstractNavTable;
import es.udc.cartolab.gvsig.pmf.utils.PmfFormFactory;
import es.udc.cartolab.gvsig.pmf.utils.PmfTocMenuEntry;

public class FormsLauncherExtension extends AbstractExtension {

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

    @Override
    public void initialize() {
	super.initialize();
	// Entry at TOC contextual menu
	ExtensionPoints extensionPoints = ExtensionPointsSingleton
		.getInstance();
	extensionPoints.add("View_TocActions", "PMF", new PmfTocMenuEntry());
	About about = (About) PluginServices.getExtension(About.class);
	FPanelAbout panelAbout = about.getAboutPanel();
	java.net.URL aboutURL = this.getClass().getResource("/about.htm");
	panelAbout.addAboutUrl("PMF", aboutURL);
	PmfFormFactory.registerFormFactory();
    }

    public boolean isEnabled() {
	view = getView();
	if (view == null) {
	    return false;
	}
	boolean isEnabled = false;

	FLayer[] actives = view.getMapControl().getMapContext().getLayers()
		.getActives();
	if (1 == actives.length) {
	    layerName = actives[0].getName();
	    isEnabled = FormFactory.hasMainFormRegistered(layerName);
	}

	return isEnabled;
    }

}

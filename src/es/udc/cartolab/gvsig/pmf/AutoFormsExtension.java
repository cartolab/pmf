package es.udc.cartolab.gvsig.pmf;

import java.io.File;
import java.io.IOException;

import com.iver.andami.Launcher;
import com.iver.andami.PluginServices;
import com.iver.andami.messages.NotificationManager;
import com.iver.andami.ui.mdiFrame.JToolBarToggleButton;
import com.iver.andami.ui.mdiFrame.MDIFrame;
import com.iver.cit.gvsig.fmap.layers.FLayer;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.listeners.CADListenerManager;
import com.iver.cit.gvsig.listeners.EndGeometryListener;

import es.icarto.gvsig.commons.AbstractExtension;
import es.icarto.gvsig.navtableforms.AbstractForm;
import es.icarto.gvsig.navtableforms.utils.FormFactory;

public class AutoFormsExtension extends AbstractExtension {

    private boolean formsEnabled = false;
    private File fileFlag;

    @Override
    public void initialize() {
	super.initialize();
	fileFlag = new File(Launcher.getAppHomeDir() + "pmf-auto-forms");
	formsEnabled = getLastSessionEnabilityState();

	if (formsEnabled) {
	    NTEndGeometryListener listener = new NTEndGeometryListener();
	    CADListenerManager.addEndGeometryListener(id, listener);
	}
    }

    private boolean getLastSessionEnabilityState() {
	return fileFlag.exists();
    }

    @Override
    public void postInitialize() {
	toggleButton(formsEnabled);
    }

    @Override
    public void execute(String actionCommand) {

	if (!formsEnabled) {
	    NTEndGeometryListener listener = new NTEndGeometryListener();
	    CADListenerManager.addEndGeometryListener(id, listener);
	    NotificationManager.addInfo("Formularios automáticos activados");
	    formsEnabled = true;
	} else {
	    CADListenerManager.removeEndGeometryListener(id);
	    NotificationManager.addInfo("Formularios automáticos desactivados");
	    formsEnabled = false;
	}

	toggleButton(formsEnabled);
	persistsEnabilityState();

    }

    public void persistsEnabilityState() {
	try {
	    if (formsEnabled) {
		fileFlag.createNewFile();

	    } else {
		fileFlag.delete();
	    }
	} catch (IOException e) {
	}
    }

    private void toggleButton(boolean pushed) {
	String tooltip;

	MDIFrame f = ((MDIFrame) PluginServices.getMainFrame());

	if (f.getSelectedTools() == null) {
	    f.setSelectedTools(f.getInitialSelectedTools());
	}

	if (!pushed) {
	    f.setSelectedTool("PMF", "empty");
	    tooltip = PluginServices.getText(this, "enable_auto_forms");
	} else {
	    f.setSelectedTool("PMF", "auto-forms");
	    tooltip = PluginServices.getText(this, "disable_auto_forms");
	}
	JToolBarToggleButton throwNTButton = (JToolBarToggleButton) f
		.getComponentByName("auto-forms");
	if (throwNTButton != null) {
	    throwNTButton.setToolTip(tooltip);
	}
    }

    @Override
    public boolean isEnabled() {
	return true;
    }

    private class NTEndGeometryListener implements EndGeometryListener {

	@Override
	public void endGeometry(FLayer layer, String cadToolKey) {

	    if ((layer instanceof FLyrVect)
		    && (FormFactory.hasMainFormRegistered(layer.getName()))) {
		AbstractForm form = FormFactory
			.createFormRegistered((FLyrVect) layer);
		if (form.init()) {
		    form.last();
		    PluginServices.getMDIManager().addWindow(form);
		}
	    }
	}
    }
}

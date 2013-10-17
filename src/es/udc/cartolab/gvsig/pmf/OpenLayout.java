package es.udc.cartolab.gvsig.pmf;

import java.io.File;

import org.apache.log4j.Logger;

import com.iver.andami.messages.NotificationManager;
import com.iver.andami.plugins.Extension;
import com.iver.cit.gvsig.project.documents.layout.gui.Layout;
import com.iver.utiles.FileUtils;

import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.udc.cartolab.gvsig.pmf.layout.ExternalError;
import es.udc.cartolab.gvsig.pmf.layout.LayoutWrapper;
import es.udc.cartolab.gvsig.pmf.utils.PmfConstants;

public class OpenLayout extends Extension {

    Logger logger = Logger.getLogger(OpenLayout.class);
    private String templatePath = FileUtils.getAppHomeDir()
	    + PmfConstants.TEMPLATE_FILE;

    @Override
    public void initialize() {
    }

    @Override
    public void execute(String actionCommand) {
	try {
	    LayoutWrapper lw = new LayoutWrapper();
	    // lw.foo();
	    Layout layout = lw.getLayoutFromTemplate(new File(templatePath));
	    lw.openLayoutWindow(layout, "PMF");
	} catch (ExternalError e) {
	    NotificationManager.addWarning(e.getMessage());
	    logger.error(e);
	}
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

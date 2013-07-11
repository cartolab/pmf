package es.udc.cartolab.gvsig.loadData;

import java.io.File;

import com.iver.andami.PluginServices;
import com.iver.andami.messages.NotificationManager;
import com.iver.andami.plugins.Extension;

import es.udc.cartolab.gvsig.loadData.preferences.LoadDataConfigDialog;

/**
 * Load Data Extension Class
 * 
 * Main class of the Layer Data Loader Extension.
 * 
 * @author Jorge López Fernández <jlopez@cartolab.es>
 * @author Francisco Puga <fpuga@cartolab.es>
 */

public class LoadDataExtension extends Extension {


    private static final String projectionCode = "EPSG:32616";
    private static final String projectName = "PMF";
    private static final String gvpName = "pmf.gvp";



    /**
     * Action Command Executer.
     * 
     * Function that executes the passed action command.
     * 
     * @param  actionCommand the command we want to execute.
     */
    public void execute(String actionCommand) {

	if (LoadDataConfigDialog.getConfigPath() == null) {
	    return;
	}

	String gvpPath = LoadDataConfigDialog.getConfigPath() + gvpName;
	File f = new File(gvpPath);
	if (f.exists()) {
	    LoadProject.loadProject(f, projectName);
	} else {
	    NotificationManager.addWarning("_projectNotFound");
	}

    }



    public void initialize() {
	PluginServices.getIconTheme().registerDefault("load-data-icon",
		this.getClass().getClassLoader().getResource("images/load_data.png"));
    }

    public boolean isEnabled() {
	return true;
    }

    public boolean isVisible() {
	return true;
    }

}

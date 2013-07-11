package es.udc.cartolab.gvsig.loadData.preferences;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;


/**
 * Load Data Preferences Extension Class
 * 
 * Main class of the Data Load Extension preferences.
 * 
 * @author Jorge López Fernández <jlopez@cartolab.es>
 * @author Francisco Puga <fpuga@cartolab.es>
 */

public class LoadDataPreferencesExtension extends Extension{

    /**
     *  String used for identifying the data directory parameter.
     */
    private String DEFAULT_DATA_DIR_KEY_NAME = "DataDir";

    public final String getDefaultDataDirKeyName() {
	return DEFAULT_DATA_DIR_KEY_NAME;
    }


    /**
     * Action Command Executer.
     * 
     * Function that executes the passed action command.
     * 
     * @param  actionCommand the command we want to execute.
     */
    public void execute(String actionCommand) {
	LoadDataConfigDialog dialog = new LoadDataConfigDialog();
	PluginServices.getMDIManager().addWindow(dialog);
    }


    public void initialize() {
	PluginServices.getIconTheme().registerDefault("preferences-icon",
		this.getClass().getClassLoader().getResource("images/preferences.png"));
    }


    public boolean isEnabled() {
	return true;
    }

    public boolean isVisible() {
	return true;
    }

}

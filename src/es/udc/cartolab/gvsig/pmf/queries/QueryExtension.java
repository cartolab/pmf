package es.udc.cartolab.gvsig.pmf.queries;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;

import es.icarto.gvsig.navtableforms.utils.FormFactory;

public class QueryExtension extends Extension {

    @Override
    public void initialize() {
	PluginServices.getIconTheme().registerDefault(
		"query-launcher-icon",
		this.getClass().getClassLoader()
			.getResource("images/queries.png"));
    }

    @Override
    public void execute(String actionCommand) {
	PluginServices.getMDIManager().addWindow(new SelectQueryDialog());
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

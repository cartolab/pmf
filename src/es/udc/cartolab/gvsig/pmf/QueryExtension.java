package es.udc.cartolab.gvsig.pmf;

import com.iver.andami.PluginServices;

import es.icarto.gvsig.commons.AbstractExtension;
import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.udc.cartolab.gvsig.pmf.queries.SelectQueryDialog;

public class QueryExtension extends AbstractExtension {

    @Override
    public void execute(String actionCommand) {
	PluginServices.getMDIManager().addWindow(new SelectQueryDialog());
    }

    @Override
    public boolean isEnabled() {
	return FormFactory.allLayersLoadedRegistered();
    }

}

package es.udc.cartolab.gvsig.pmf;

import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.fmap.MapContext;
import com.iver.cit.gvsig.project.documents.view.gui.View;
import com.iver.utiles.FileUtils;

import es.icarto.gvsig.commons.AbstractExtension;
import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.udc.cartolab.gvsig.pmf.layout.LayoutWrapper;
import es.udc.cartolab.gvsig.pmf.utils.PmfConstants;

public class OpenLayoutExtension extends AbstractExtension {

    private static String templatePath = FileUtils.getAppHomeDir()
	    + PmfConstants.TEMPLATE_FILE;

    @Override
    public void initialize() {
    }

    @Override
    public void execute(String actionCommand) {
	SelectHousingLayoutDialog dialog = new SelectHousingLayoutDialog();
	PluginServices.getMDIManager().addCentredWindow(dialog);
	String housingCode = dialog.getHousingCode();
	if ((housingCode == null) || housingCode.isEmpty()) {
	    return;
	}
	View view = (View) PluginServices.getMDIManager().getActiveWindow();
	MapContext mapContext = view.getMapControl().getMapContext();
	LayoutWrapper lw = new LayoutWrapper(mapContext, templatePath);
	lw.zoomTo(housingCode);

    }

    @Override
    public boolean isEnabled() {
	return FormFactory.allLayersLoadedRegistered();
    }

}

package es.udc.cartolab.gvsig.pmf;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.iver.andami.PluginServices;
import com.iver.andami.messages.NotificationManager;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.commons.AbstractExtension;
import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.pmf.forms.ParcelasForm;
import es.udc.cartolab.gvsig.pmf.importplot.ImportPlotDialog;
import es.udc.cartolab.gvsig.pmf.utils.DAO;

public class ImportPlotExtension extends AbstractExtension {

    @Override
    public void execute(String actionCommand) {

	FLyrVect parcelas = new TOCLayerManager()
		.getLayerByName(ParcelasForm.NAME);
	if (parcelas.isEditing()) {
	    final String message = String.format(
		    PluginServices.getText(this, "close_edition_in_layer"),
		    ParcelasForm.NAME);
	    NotificationManager.addWarning(message);
	    return;
	}
	List<FLyrVect> validLayers = Arrays.asList(new TOCLayerManager()
		.getAllLayers());

	try {
	    Collection<String> codComs = DAO.getCommunityCodes();
	    Collection<String> codVivs = DAO.getHousingCodes();

	    ImportPlotDialog dialog = new ImportPlotDialog(
		    validLayers.toArray(new FLyrVect[0]), codComs, codVivs,
		    parcelas);
	    PluginServices.getMDIManager().addWindow(dialog);
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

    @Override
    public boolean isEnabled() {
	return FormFactory.checkLayerLoadedRegistered(ParcelasForm.NAME);
    }

}

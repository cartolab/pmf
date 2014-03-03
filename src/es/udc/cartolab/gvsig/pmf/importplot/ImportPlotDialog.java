package es.udc.cartolab.gvsig.pmf.importplot;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.hardcode.gdbms.engine.values.Value;
import com.iver.andami.PluginServices;
import com.iver.andami.messages.NotificationManager;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.WindowInfo;
import com.iver.cit.gvsig.exceptions.visitors.StopWriterVisitorException;
import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.iver.cit.gvsig.fmap.core.IRow;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.udc.cartolab.gvsig.pmf.forms.ParcelasForm;
import es.udc.cartolab.gvsig.pmf.utils.EditLayerHelper;

@SuppressWarnings("serial")
public class ImportPlotDialog extends JPanel implements IWindow, ActionListener {

    private static Logger logger = Logger.getLogger(ImportPlotDialog.class);

    /*
     * The next properties are the ones related to the interface itself, so they
     * don't need a real explanation
     */

    private WindowInfo windowInfo = null;
    private JButton cancelButton = null;
    private JButton okButton = null;
    private FLyrVect[] validLayers;
    private String[] codComs;
    private String[] codVivs;

    private JComboBox originCombo = null;
    private JComboBox codComCombo = null;
    private JComboBox codVivCombo = null;

    private FLyrVect targetLayer;

    public WindowInfo getWindowInfo() {
	if (windowInfo == null) {
	    windowInfo = new WindowInfo(WindowInfo.MODALDIALOG
		    | WindowInfo.PALETTE);
	    windowInfo.setTitle(PluginServices.getText(this, "Import_plot"));
	    Dimension dim = getPreferredSize();
	    int width, height = 0;
	    if (dim.getHeight() > 550) {
		height = 550;
	    } else {
		height = 85;
	    }
	    if (dim.getWidth() > 550) {
		width = 550;
	    } else {
		width = new Double(dim.getWidth()).intValue() + 5;
	    }
	    windowInfo.setWidth(width);
	    windowInfo.setHeight(height);
	}
	return windowInfo;
    }

    public ImportPlotDialog(FLyrVect[] validLayers, String[] codComs,
	    String[] codVivs, FLyrVect targetLayer) {
	super();
	this.validLayers = validLayers;
	this.codComs = codComs;
	this.codVivs = codVivs;
	this.targetLayer = targetLayer;
	try {
	    initialize();
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	}
    }

    private void initialize() throws Exception {

	this.setLayout(new MigLayout());

	JLabel label = new JLabel(PluginServices.getText(this, "Origin_layer"));
	this.add(label);

	Vector<String> items = new Vector<String>();
	for (int i = validLayers.length - 1; i >= 0; i--) {
	    items.add(validLayers[i].getName());
	}

	originCombo = new JComboBox(items);
	this.add(originCombo, "wrap");

	label = new JLabel(PluginServices.getText(this, "Cod_com"));
	this.add(label);

	items = new Vector<String>();
	for (String codCom : codComs) {
	    items.add(codCom);
	}

	codComCombo = new JComboBox(items);
	this.add(codComCombo, "wrap");

	label = new JLabel(PluginServices.getText(this, "Cod_viv"));
	this.add(label);

	items = new Vector<String>();
	for (String codViv : codVivs) {
	    items.add(codViv);
	}

	codVivCombo = new JComboBox(items);
	this.add(codVivCombo, "wrap");

	okButton = new JButton();
	okButton.setText(PluginServices.getText(this, "Ok"));
	okButton.addActionListener(this);

	cancelButton = new JButton();
	cancelButton.setText(PluginServices.getText(this, "Cancel"));
	cancelButton.addActionListener(this);

	this.add(okButton, "center, bottom, cell 1 4");
	this.add(cancelButton, "center, bottom, cell 1 4");

    }

    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == okButton) {
	    String layerName = getComboboxValue(originCombo);
	    String codViv = getComboboxValue(codVivCombo);
	    String codCom = getComboboxValue(codComCombo);

	    if (allNotNull(layerName, codViv, codCom)) {
		FLyrVect layer = getValidLayer(layerName);

		try {

		    EditLayerHelper elh = new EditLayerHelper(targetLayer);
		    final int[] indexes = elh.getIndexes(ParcelasForm.PKFIELD,
			    ParcelasForm.CODCOM);
		    final Value[] values = elh.getValues(codViv, codCom);
		    final IGeometry geom = Points2Polygon.toPolygon(layer,
			    codViv);
		    final IRow row = elh.getRow(geom, indexes, values);
		    elh.addRowToLayer(row);
		} catch (ReadDriverException rde) {
		    NotificationManager.addWarning(PluginServices.getText(this,
			    ""));
		    logger.error(rde);
		} catch (StopWriterVisitorException swve) {
		    String message = String.format(
			    PluginServices.getText(this, "error_saving_layer"),
			    layerName);
		    NotificationManager.addWarning(message);
		    logger.error(swve);
		}
	    }
	}
	PluginServices.getMDIManager().closeWindow(this);
    }

    private String getComboboxValue(JComboBox combo) {
	Object selectedItem = combo.getSelectedItem();
	if (selectedItem instanceof String
		&& (selectedItem).toString().trim().length() > 0) {
	    return selectedItem.toString();
	}
	return null;
    }

    private boolean allNotNull(String layerName, String codViv, String codCom) {
	return layerName == null ? false : codViv == null ? false
		: codCom == null ? false : true;
    }

    private FLyrVect getValidLayer(String layerName) {
	for (FLyrVect layer : validLayers) {
	    if (layer.getName().equals(layerName)) {
		return layer;
	    }
	}
	throw new AssertionError("This should never happer");
    }

    public Object getWindowProfile() {
	return null;
    }
}

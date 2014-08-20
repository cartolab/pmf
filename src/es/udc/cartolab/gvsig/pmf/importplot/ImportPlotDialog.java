package es.udc.cartolab.gvsig.pmf.importplot;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

import es.udc.cartolab.gvsig.commons.ui.AcceptCancelPanel;
import es.udc.cartolab.gvsig.pmf.forms.ParcelasForm;
import es.udc.cartolab.gvsig.pmf.utils.DAO;
import es.udc.cartolab.gvsig.pmf.utils.EditLayerHelper;

@SuppressWarnings("serial")
public class ImportPlotDialog extends JPanel implements IWindow, ActionListener {

    private static final String prototypeDisplayValue = "XXXXXXXXXXXXXXXXXXXX";

    private static Logger logger = Logger.getLogger(ImportPlotDialog.class);

    private WindowInfo windowInfo = null;
    private FLyrVect[] validLayers;
    private Collection<String> codComs;
    private Collection<String> codVivs;

    private JComboBox originCombo = null;
    private JComboBox codComCombo = null;
    private JComboBox codVivCombo = null;

    private FLyrVect targetLayer;

    private JTextField codParTF;

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
	    windowInfo.setWidth(width + 10);
	    windowInfo.setHeight(height + 10);
	}
	return windowInfo;
    }

    public ImportPlotDialog(FLyrVect[] validLayers, Collection<String> codComs,
	    Collection<String> codVivs, FLyrVect targetLayer) {
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
	originCombo.setPrototypeDisplayValue(prototypeDisplayValue);
	this.add(originCombo, "wrap");

	label = new JLabel(PluginServices.getText(this, "Cod_com"));
	this.add(label);

	codComCombo = new JComboBox(codComs.toArray());
	codComCombo.addItem("");
	codComCombo.setSelectedIndex(codComCombo.getItemCount() - 1);
	codComCombo.setPrototypeDisplayValue(prototypeDisplayValue);
	this.add(codComCombo, "wrap");

	label = new JLabel(PluginServices.getText(this, "Cod_viv"));
	this.add(label);

	codVivCombo = new JComboBox(codVivs.toArray());
	codVivCombo.addItem("");
	codVivCombo.setSelectedIndex(codVivCombo.getItemCount() - 1);
	codVivCombo.setPrototypeDisplayValue(prototypeDisplayValue);
	this.add(codVivCombo, "wrap");
	codVivCombo.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		String expectedPlotCode = "";
		if ((codVivCombo.getSelectedItem() == null)
			|| (codVivCombo.getSelectedItem().toString().trim()
				.isEmpty())) {
		    codParTF.setText("");
		    return;
		}

		try {
		    List<String> plotCodes = DAO.getPlotCodesForViv(codVivCombo
			    .getSelectedItem().toString());
		    if (plotCodes.isEmpty()) {
			expectedPlotCode = codVivCombo.getSelectedItem()
				.toString() + "P01";
		    } else {
			Collections.sort(plotCodes, new Comparator<String>() {

			    @Override
			    public int compare(String o1, String o2) {
				return o1.compareToIgnoreCase(o2);
			    }
			});
			String a = plotCodes.get(plotCodes.size() - 1);
			String lastChar = a.substring(a.length() - 1);
			int newLastChar = Integer.parseInt(lastChar) + 1;
			expectedPlotCode = a.substring(0, a.length() - 1)
				+ newLastChar;
		    }

		} catch (SQLException e1) {
		    expectedPlotCode = "";
		    logger.error(e1.getStackTrace(), e1);
		} catch (NumberFormatException e2) {
		    expectedPlotCode = "";
		    logger.error(e2.getStackTrace(), e2);
		}
		codParTF.setText(expectedPlotCode);
	    }
	});

	label = new JLabel("Código de parcela esperado");
	this.add(label);
	codParTF = new JTextField(15);
	codParTF.setEditable(false);
	codParTF.setEnabled(false);
	this.add(codParTF, "wrap");

	AcceptCancelPanel acceptCancelPanel = new AcceptCancelPanel(this, this);
	add(acceptCancelPanel, "dock south");
    }

    public void actionPerformed(ActionEvent e) {
	if (e.getActionCommand() == AcceptCancelPanel.OK_ACTION_COMMAND) {
	    String layerName = getComboboxValue(originCombo);
	    String codViv = getComboboxValue(codVivCombo);
	    String codCom = getComboboxValue(codComCombo);
	    String codPar = codParTF.getText();
	    if (allNotNull(layerName, codViv, codCom, codPar)) {
		if (codPar.trim().isEmpty()) {
		    JOptionPane.showConfirmDialog(this,
			    "El código de parcela no es válido");
		    return;
		}
		FLyrVect layer = getValidLayer(layerName);

		try {

		    EditLayerHelper elh = new EditLayerHelper(targetLayer);
		    final int[] indexes = elh.getIndexes(ParcelasForm.PKFIELD,
			    ParcelasForm.CODVIV, ParcelasForm.CODCOM);
		    final Value[] values = elh
			    .getValues(codPar, codViv, codCom);
		    final IGeometry geom = Points2Polygon.toPolygon(layer,
			    codPar);
		    final IRow row = elh.getRow(geom, indexes, values);
		    elh.addRowToLayer(row);
		} catch (ReadDriverException rde) {
		    JOptionPane.showConfirmDialog(this,
			    "Error procesando la capa", "Error",
			    JOptionPane.DEFAULT_OPTION,
			    JOptionPane.ERROR_MESSAGE);
		    logger.error(rde);
		} catch (StopWriterVisitorException swve) {
		    String message = String.format(
			    PluginServices.getText(this, "error_saving_layer"),
			    layerName);
		    JOptionPane.showConfirmDialog(this, message, "Error",
			    JOptionPane.DEFAULT_OPTION,
			    JOptionPane.ERROR_MESSAGE);
		    NotificationManager.addWarning(message);
		    logger.error(swve);
		} catch (InvalidInputDataException e1) {
		    JOptionPane.showConfirmDialog(this, e1.getMessage(),
			    "Error", JOptionPane.DEFAULT_OPTION,
			    JOptionPane.ERROR_MESSAGE);
		    NotificationManager.addWarning(e1.getMessage());
		    logger.error(e1.getStackTrace(), e1);
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

    private boolean allNotNull(String... values) {
	for (String s : values) {
	    if (s == null) {
		return false;
	    }
	}
	return true;
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
	return WindowInfo.DIALOG_PROFILE;
    }
}

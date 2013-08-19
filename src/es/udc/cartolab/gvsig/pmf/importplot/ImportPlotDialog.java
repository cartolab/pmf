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

import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.WindowInfo;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;

import es.udc.cartolab.gvsig.pmf.forms.ComunidadesForm;
import es.udc.cartolab.gvsig.pmf.forms.ViviendasForm;

@SuppressWarnings("serial")
public class ImportPlotDialog extends JPanel implements IWindow, ActionListener {

    private static Logger logger = Logger.getLogger("ReportsExtension");

    /*
     * The next properties are the ones related to the interface itself, so they
     * don't need a real explanation
     */

    private WindowInfo windowInfo = null;
    private JButton cancelButton = null;
    private JButton okButton = null;
    private FLyrVect[] validLayers;
    private SelectableDataSource comSource;
    private SelectableDataSource vivSource;

    private JComboBox originCombo = null;
    private JComboBox codComCombo = null;
    private JComboBox codVivCombo = null;

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

    public ImportPlotDialog(FLyrVect[] validLayers,
	    SelectableDataSource comSource, SelectableDataSource vivSource) {
	super();
	this.validLayers = validLayers;
	this.comSource = comSource;
	this.vivSource = vivSource;
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
	for (int i = 0; i < comSource.getRowCount(); i++) {
	    items.add(comSource.getFieldValue(i,
		    comSource.getFieldIndexByName(ComunidadesForm.PKFIELD))
		    .toString());
	}

	codComCombo = new JComboBox(items);
	this.add(codComCombo, "wrap");

	label = new JLabel(PluginServices.getText(this, "Cod_viv"));
	this.add(label);

	items = new Vector<String>();
	for (int i = 0; i < vivSource.getRowCount(); i++) {
	    items.add(vivSource.getFieldValue(i,
		    vivSource.getFieldIndexByName(ViviendasForm.PKFIELD))
		    .toString());
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
	    PluginServices.getMDIManager().closeWindow(this);
	} else if (e.getSource() == cancelButton) {
	    PluginServices.getMDIManager().closeWindow(this);
	}

    }

    public Object getWindowProfile() {
	return null;
    }
}

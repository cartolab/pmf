package es.udc.cartolab.gvsig.pmf.reports.plot;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.WindowInfo;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;

import es.udc.cartolab.gvsig.commons.ui.AcceptCancelPanel;

@SuppressWarnings("serial")
public class SelectPlotDialog extends JPanel implements IWindow, ActionListener {

    private static Logger logger = Logger.getLogger(SelectPlotDialog.class);
    private SelectableDataSource source;

    private WindowInfo windowInfo = null;

    private JComboBox communityCombo = null;

    private JTextField directoryField = null;
    private JButton dotsButton = null;

    public WindowInfo getWindowInfo() {
	if (windowInfo == null) {
	    windowInfo = new WindowInfo(WindowInfo.MODALDIALOG
		    | WindowInfo.RESIZABLE | WindowInfo.PALETTE);
	    windowInfo.setTitle(PluginServices.getText(this, "Plot_report"));
	    Dimension dim = getPreferredSize();
	    int width, height = 0;
	    if (dim.getHeight() > 550) {
		height = 550;
	    } else {
		height = 65;
	    }
	    if (dim.getWidth() > 550) {
		width = 550;
	    } else {
		width = new Double(dim.getWidth()).intValue() + 20;
	    }
	    windowInfo.setWidth(width);
	    windowInfo.setHeight(height);
	}
	return windowInfo;
    }

    public SelectPlotDialog(SelectableDataSource source) {
	super();
	this.source = source;
	try {
	    initialize();
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	}
    }

    private void initialize() throws Exception {

	Vector<String> items = new Vector<String>();
	for (int i = 0; i < source.getRowCount(); i++) {
	    items.add(source.getFieldValue(i,
		    source.getFieldIndexByName("nom_produ")).toString());
	}

	String userHome = System.getProperty("user.home");

	this.setLayout(new MigLayout("center", "[fill][grow 0][grow 0][fill]",
		"20[fill][fill]30%[fill, bottom]"));
	this.setLayout(new MigLayout());

	JLabel housingLabel = new JLabel(
		PluginServices.getText(this, "Housing"));
	this.add(housingLabel);

	communityCombo = new JComboBox(items);
	this.add(communityCombo, "wrap");

	JLabel outputLabel = new JLabel(PluginServices.getText(this, "Save_in"));
	this.add(outputLabel);

	directoryField = new JTextField("");
	this.add(directoryField, "grow, width 150::");
	directoryField.setText(userHome);

	dotsButton = new JButton("...");
	dotsButton.addActionListener(this);
	this.add(dotsButton, " wrap");

	AcceptCancelPanel acceptCancelPanel = new AcceptCancelPanel(this, this);
	add(acceptCancelPanel, "dock south");
    }

    private void displayFileChooser() {
	File currentDirectory = new File(directoryField.getText());
	JFileChooser chooser;
	if (currentDirectory.isDirectory()) {
	    chooser = new JFileChooser(currentDirectory);
	} else {
	    chooser = new JFileChooser();
	}
	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	int returnVal = chooser.showOpenDialog(directoryField);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    directoryField.setText(chooser.getSelectedFile().getAbsolutePath());
	}
    }

    private void generateReport(String outputPath) {
	if (!outputPath.endsWith(File.separator)) {
	    outputPath = outputPath + File.separator;
	}
	File dir = new File(outputPath);
	if (dir.isDirectory() && dir.canWrite()) {
	    try {
		String outputReport = outputPath
			+ source.getFieldValue(
				communityCombo.getSelectedIndex(),
				source.getFieldIndexByName("nom_produ"))
			+ "-report.rtf";
		new RtfPlotReport(communityCombo.getSelectedIndex(), source,
			outputReport);
	    } catch (ReadDriverException e) {
		e.printStackTrace();
	    }
	} else {
	    JOptionPane.showMessageDialog(this,
		    "El directorio seleccionado no es válido.", "Error",
		    JOptionPane.ERROR_MESSAGE);
	}
    }

    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == dotsButton) {
	    displayFileChooser();
	} else if (e.getActionCommand() == AcceptCancelPanel.OK_ACTION_COMMAND) {
	    generateReport(directoryField.getText());
	    PluginServices.getMDIManager().closeWindow(this);
	} else if (e.getActionCommand() == AcceptCancelPanel.CANCEL_ACTION_COMMAND) {
	    PluginServices.getMDIManager().closeWindow(this);
	}

    }

    public Object getWindowProfile() {
	return null;
    }
}

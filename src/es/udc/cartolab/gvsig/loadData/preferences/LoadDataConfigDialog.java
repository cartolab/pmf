package es.udc.cartolab.gvsig.loadData.preferences;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import com.iver.andami.PluginServices;
import com.iver.andami.preferences.StoreException;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.WindowInfo;
import com.iver.utiles.NotExistInXMLEntity;
import com.iver.utiles.XMLEntity;


/**
 * Load Data Config Dialog Class
 *
 * Class which contains the dialog used for displaying the Data Load Extension preferences.
 * Only the not GUI-related functions are documented.
 *
 * @author Jorge López Fernández <jlopez@cartolab.es>
 * @author Francisco Puga <fpuga@cartolab.es>
 */

public class LoadDataConfigDialog extends JPanel implements IWindow, ActionListener {


    private static Logger logger = Logger.getLogger("DataLoaderExtension");


    private final String DEFAULT_DATA_DIR_KEY_NAME;

    // True if the path is not set or is not valid
    private boolean error = true;

    /*
     * The next properties are the ones related to the interface itself, so they don't need a real explanation
     */

    private WindowInfo windowInfo = null;
    private JButton cancelButton = null;
    private JButton okButton = null;

    private JTextField directoryField = null;
    private JButton dotsButton = null;

    public void setError(boolean error) {
	this.error = error;
    }

    public boolean getError() {
	return this.error;
    }

    public WindowInfo getWindowInfo() {
	if (windowInfo == null) {
	    windowInfo = new WindowInfo(WindowInfo.MODALDIALOG
		    | WindowInfo.RESIZABLE | WindowInfo.PALETTE);
	    windowInfo.setTitle(PluginServices.getText(this, "Preferences"));
	    Dimension dim = getPreferredSize();
	    int width, heigth = 0;
	    if (dim.getHeight() > 550) {
		heigth = 550;
	    } else {
		heigth = new Double(dim.getHeight()).intValue() + 15;
	    }
	    if (dim.getWidth() > 550) {
		width = 550;
	    } else {
		width = new Double(dim.getWidth()).intValue() + 15;
	    }
	    windowInfo.setWidth(width);
	    windowInfo.setHeight(heigth);
	}
	return windowInfo;
    }

    public LoadDataConfigDialog() {
	super();
	DEFAULT_DATA_DIR_KEY_NAME = ((LoadDataPreferencesExtension)
		PluginServices.getExtension(LoadDataPreferencesExtension.class)).getDefaultDataDirKeyName();
	try {
	    initialize();
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	}
    }

    public JTextField getDirectoryField() {
	return directoryField;
    }

    private String getInnerConfigPath() {
	PluginServices ps = PluginServices.getPluginServices(this);
	XMLEntity xml = ps.getPersistentXML();
	String configPath = xml.getStringProperty(DEFAULT_DATA_DIR_KEY_NAME);
	String gvpPath = configPath + "pmf.gvp";
	File f = new File(gvpPath);
	if (!f.exists()) {
	    throw new NotExistInXMLEntity();
	}
	return configPath;
    }

    public static String getConfigPath(boolean showPreferencesDialog) {
	String configPath = null;
	LoadDataConfigDialog dialog = new LoadDataConfigDialog();
	try {
	    configPath = dialog.getInnerConfigPath();
	} catch (NotExistInXMLEntity notExist) {
	    if (showPreferencesDialog) {
		PluginServices.getMDIManager().addCentredWindow(dialog);
		if (!dialog.getError()) {
		    configPath = dialog.getInnerConfigPath();
		}
	    }
	}

	return configPath;

    }

    public static String getConfigPath() {
	return getConfigPath(true);
    }

    private void initialize() throws Exception {


	this.setLayout(new MigLayout("center", "[fill][grow 0][grow 0][fill]", "20[fill][fill]30%[fill, bottom]"));
	this.setLayout(new MigLayout());

	JLabel directoryLabel = new JLabel(PluginServices.getText(this, "Folder"));
	this.add(directoryLabel);

	String defaultDirectory = "";
	try {
	    defaultDirectory = getInnerConfigPath();
	} catch (Exception e){
	    ;
	}

	directoryField = new JTextField(defaultDirectory);
	this.add(directoryField, "grow, width 150::");

	dotsButton = new JButton("...");
	dotsButton.addActionListener(this);
	this.add(dotsButton, " wrap");

	okButton = new JButton();
	okButton.setText(PluginServices.getText(this, "OK"));
	okButton.addActionListener(this);

	cancelButton = new JButton();
	cancelButton.setText(PluginServices.getText(this, "Cancel"));
	cancelButton.addActionListener(this);

	this.add(okButton, "center, bottom, cell 1 2");
	this.add(cancelButton, "center, bottom, cell 1 2");

    }

    /**
     * Config File Saver
     *
     * Function which saves the config changes made.
     *
     * @throws StoreException
     */
    public void saveConfigFile() throws StoreException {
	PluginServices ps = PluginServices.getPluginServices(this);
	XMLEntity xml = ps.getPersistentXML();

	storeDir(xml);
    }

    /**
     * Directory Changes Storer
     *
     * Function which stores the dir configured inside the configuration xml file.
     *
     * @throws StoreException
     */
    private void storeDir(XMLEntity xml) throws StoreException {
	String dataDir = directoryField.getText();
	File f = new File(dataDir);
	if (f.exists() && f.isDirectory() && f.canRead()) {
	    if (!dataDir.endsWith(File.separator)) {
		dataDir = dataDir + File.separator;
	    }
	    xml.putProperty(DEFAULT_DATA_DIR_KEY_NAME,
		    dataDir);
	} else {
	    JOptionPane.showMessageDialog(this,
		    "El directorio seleccionado no es válido.", "Error",
		    JOptionPane.ERROR_MESSAGE);
	}
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
	    directoryField.setText(chooser.getSelectedFile()
		    .getAbsolutePath());
	}
    }

    /**
     * Data Directory Validator
     *
     * Function which checks whether the selected directory contains the right hierarchy or not.
     *
     * @param The path of the dir we want to check.
     */
    private boolean validateDir(String dirPath) {
	boolean isValidDir = false;
	if (!dirPath.endsWith(File.separator)) {
	    dirPath = dirPath + File.separator;
	}
	File dir = new File(dirPath);
	try {
	    if (dir.isDirectory()) {
		File shpFile = new File(dirPath + "SHP"),
		dbfFile = new File(dirPath + "DBF"),
		stylesFile = new File(dirPath + "STYLES");
		if (shpFile.isDirectory() && dbfFile.isDirectory() && stylesFile.isDirectory()) {
		    saveConfigFile();
		    setError(false);
		    isValidDir = true;
		    PluginServices.getMDIManager().closeWindow(this);
		} else {
		    JOptionPane.showMessageDialog(this,
			    "El directorio seleccionado no presenta la estructura necesaria (subdirs 'SHP', 'DBF' y 'STYLES').",
			    "Error", JOptionPane.ERROR_MESSAGE);
		}
	    } else {
		JOptionPane.showMessageDialog(this,
			"El directorio seleccionado no es válido.",
			"Error", JOptionPane.ERROR_MESSAGE);
	    }
	} catch (StoreException ex) {
	    logger.error(ex.getMessage(), ex);
	}
	return isValidDir;
    }

    public void actionPerformed(ActionEvent e) {

	if (e.getSource() == dotsButton) {
	    displayFileChooser();
	}
	else if (e.getSource() == okButton) {
	    validateDir(directoryField.getText());
	}
	else if (e.getSource() == cancelButton) {
	    PluginServices.getMDIManager().closeWindow(this);
	}

    }

    public Object getWindowProfile() {
	return null;
    }
}

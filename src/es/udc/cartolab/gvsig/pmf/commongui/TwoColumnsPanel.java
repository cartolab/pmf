package es.udc.cartolab.gvsig.pmf.commongui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiFrame.MDIFrame;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.IWindowListener;
import com.iver.andami.ui.mdiManager.WindowInfo;
import com.iver.cit.gvsig.fmap.layers.FLayer;
import com.iver.cit.gvsig.fmap.layers.FLayers;
import com.iver.cit.gvsig.project.documents.view.gui.BaseView;

/**
 * A basic panel with Gridbaglayout of two columns, an undefined number of rows
 * and buttons OK and cancel. This class provides methods to add some kind of
 * JComponent in a easy way.
 * 
 * Warnings: + Some text is passed to getText inside TwoColumnsPanel so you
 * don't have to do it for yourselves. But you have to add it to properties
 * file. + The string passed to the method addSelectTOCLayer is used to get the
 * selected item to.
 * 
 * Usage Example: panel = new TwoColumnsPanel(this)
 * panel.addSelectTOCLayerCB("Some text");
 * PluginServices.getMDIManager().addWindow(panel); .... public void
 * actionPerformed(ActionEvent e) { if (e.getSource() == panel.getOKButton()) {
 * FLayer ly = panel.getFLayer("Some text"); ly.setVisible(false); }
 * PluginServices.getMDIManager().closeWindow(panel); }
 * 
 * Components that can be added:
 * 
 * addSelectTOCLayerCB(String): add a combobox, the items are the name of the
 * layers in the toc addTextField(String) addCheckBox(String)
 * 
 * How to get the results: Caller must implement ActionListener. In the
 * actionPerformed this methods can be called getText(String): returns a String
 * selected from addTextField. getLayer(String): returns a FLayer selected from
 * addSelectTOCLayerCB. getBoolean(String): returns if the addCheckBox is
 * selected or not.
 * 
 * @author fpuga <fpuga@cartolab.es>
 * 
 */

// TODO: Crear un modelo para el JComboBox que admita FLayer directamente como
// item
// TODO: No more that 50 rows are allowed, if you need more you must modify
// igridx

public class TwoColumnsPanel extends JPanel implements IWindow, IWindowListener {

    private WindowInfo windowInfo = null;
    private String windowTitle = "";

    // private JPanel panelButtons = null;
    private JButton cancelButton = null;
    private JButton okButton = null;

    private GridBagConstraints c = null;
    private Insets insets = null;

    private FLayers layers = null;

    private JRootPane jRootPane = null;

    // The last row where a component was placed
    private int lastY = 0;

    // To define if the component must be placed on the left or in the rigth
    // column
    private final int RIGTH = 1;
    private final int LEFT = 0;

    // the text that acts as label for the components is used to "map" them
    // and recuperate the values later
    private final HashMap<String, JComponent> ComponentMap = new HashMap<String, JComponent>();

    // private HashMap<String,String> ResultMap = new HashMap<String,String>();

    public TwoColumnsPanel(ActionListener l, boolean withCancelButton) {

	setLayout(new GridBagLayout());
	c = new GridBagConstraints();
	insets = new Insets(5, 10, 5, 10);

	okButton = setOKButton(l);
	add(okButton, c);

	if (withCancelButton) {
	    cancelButton = setCancelButton(l);
	    add(cancelButton, c);
	}
	// TODO: The UI not update jet the sizes. Cancel and OK button should
	// have the same size
	// if (widthOK > widthCancel) {
	// System.out.println("aaaaaaaaaaaa " + widthOK);
	// cancelButton.setSize(okButton.getSize());
	// } else {
	// System.out.println("bbbbbbbbbbbbbbbb " + widthCancel);
	// okButton.setSize(cancelButton.getSize());
	// }
    }

    public TwoColumnsPanel(ActionListener l) {
	this(l, true);
    }

    // It should be more polite pass layers as a parameter
    // private Object[] layersToItems() {
    // Object[] items = new Object[layers.getLayersCount()];
    // for (int i=0; i<layers.getLayersCount(); i++) {
    // items[i] = (layers.getLayer(i).getName());
    // }
    // return items;
    // }

    // TODO: Think if we should check here that baseview is active window
    public void addSelectTOCLayerCB(String fieldName) {
	BaseView view = (BaseView) PluginServices.getMDIManager()
		.getActiveWindow();
	layers = view.getMapControl().getMapContext().getLayers();

	Object[] items = new Object[layers.getLayersCount()];
	for (int i = 0; i < layers.getLayersCount(); i++) {
	    items[i] = (layers.getLayer(i).getName());
	}
	addCB(items, fieldName);
    }

    private void addLabel(String text, int column) {
	JLabel label = new JLabel(PluginServices.getText(this, text));
	c.gridx = column;
	c.gridy = lastY;
	c.fill = GridBagConstraints.BOTH;
	c.ipadx = 1;
	c.ipady = 1;
	c.insets = insets;
	add(label, c);
    }

    public void addTextField(String fieldName) {
	addLabel(fieldName, LEFT);

	JTextField tf = new JTextField();
	c.gridx = RIGTH;
	c.gridy = lastY;
	c.fill = GridBagConstraints.BOTH;
	c.ipadx = 1;
	c.ipady = 1;
	c.insets = insets;
	add(tf, c);

	addComponentToMap(fieldName, tf);
    }

    public void addCB(Object[] items, String fieldName) {
	addLabel(fieldName, LEFT);

	JComboBox cb = new JComboBox(items);
	c.gridx = RIGTH;
	c.gridy = lastY;
	c.fill = GridBagConstraints.BOTH;
	c.ipadx = 1;
	c.ipady = 1;
	c.insets = insets;
	add(cb, c);

	addComponentToMap(fieldName, cb);
    }

    public void addCheckBox(String fieldName) {
	addLabel(fieldName, RIGTH);

	JCheckBox chb = new JCheckBox();
	c.gridx = LEFT;
	c.gridy = lastY;
	c.fill = GridBagConstraints.BOTH;
	c.ipadx = 1;
	c.ipady = 1;
	c.insets = insets;
	add(chb, c);

	addComponentToMap(fieldName, chb);
    }

    public void addTextField(String fieldName, String value) {
	addLabel(fieldName, LEFT);

	JTextField tf = new JTextField(value);

	c.gridx = RIGTH;
	c.gridy = lastY;
	c.fill = GridBagConstraints.BOTH;
	c.ipadx = 1;
	c.ipady = 1;
	c.insets = insets;

	tf.setEditable(false);

	add(tf, c);

	addComponentToMap(fieldName, tf);
    }

    public void addSaveFile(String fieldName, final String description,
	    final String... extensions) {
	addLabel(fieldName, LEFT);

	final JTextField tf = new JTextField();
	c.gridx = LEFT;
	c.gridy = ++lastY;
	c.fill = GridBagConstraints.BOTH;
	c.ipadx = 1;
	c.ipady = 1;
	c.insets = insets;
	add(tf, c);

	JButton bt = new JButton(PluginServices.getText(this, "_examinar"));
	bt.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		SaveFileDialog sfd = new SaveFileDialog(description, extensions);
		String filePath = sfd.showDialog().getAbsolutePath();
		tf.setText(filePath);
	    }
	});
	c.gridx = RIGTH;
	add(bt, c);
	addComponentToMap(fieldName, tf);
    }

    /**
     * Each new method that a new type of component to the panel must call this
     * function before finish.
     * 
     * @param fieldName
     *            name of the component (also the text associated to it)
     * @param component
     */

    public void addComponentToMap(String fieldName, JComponent component) {
	ComponentMap.put(fieldName, component);
	lastY++;
    }

    /**
     * 
     * @param fieldName
     * @return the string value provided or selected by the user
     */

    // TODO: Think if must do trim()
    public String getText(String fieldName) {
	JComponent component = ComponentMap.get(fieldName);
	String value = null;
	if (component instanceof JComboBox) {
	    value = ((JComboBox) component).getSelectedItem().toString();
	} else if (component instanceof JTextField) {
	    value = ((JTextField) component).getText();
	}

	return value;
    }

    public Boolean getBoolean(String fieldName) {
	JComponent component = ComponentMap.get(fieldName);
	Boolean value = null;
	if (component instanceof JCheckBox) {
	    value = ((JCheckBox) component).isSelected();
	}

	return value;
    }

    public FLayer getFLayer(String fieldName) {
	JComponent component = ComponentMap.get(fieldName);
	FLayer value = null;
	if (component instanceof JComboBox) {
	    String layerName = ((JComboBox) component).getSelectedItem()
		    .toString();
	    value = layers.getLayer(layerName);
	}

	return value;
    }

    // public FLyrVect getFLyrVect (String fieldName) {
    // JComponent component = ComponentMap.get(fieldName);
    // FLyrVect value = null;
    // if (component instanceof JComboBox) {
    // String layerName = ((JComboBox)component).getSelectedItem().toString();
    // value = (FLyrVect) layers.getLayer(layerName);
    // }
    //
    // return value;
    // }

    private JButton setOKButton(ActionListener l) {
	c.gridx = 0;
	c.gridy = 50;
	c.anchor = GridBagConstraints.CENTER;
	c.ipadx = 1;
	c.ipady = 1;
	c.insets = insets;
	okButton = new JButton(PluginServices.getText(this, "OK"));
	okButton.addActionListener(l);

	return okButton;
    }

    public JButton getOKButton() {
	return okButton;
    }

    private JButton setCancelButton(ActionListener l) {
	c.gridx = 1;
	c.gridy = 50;
	c.anchor = GridBagConstraints.CENTER;
	c.ipadx = 1;
	c.ipady = 1;
	c.insets = insets;
	cancelButton = new JButton(PluginServices.getText(this, "Cancel"));
	cancelButton.addActionListener(l);

	return cancelButton;
    }

    private JButton getCancelButton() {
	return cancelButton;
    }

    //
    // private JPanel getJPanelButtons() {
    // if (panelButtons == null) {
    // panelButtons = new JPanel();
    // panelButtons.add(getOkButton());
    // panelButtons.add(getCancelButton());
    // }
    // return panelButtons;
    // }

    public WindowInfo getWindowInfo() {
	if (windowInfo == null) {
	    windowInfo = new WindowInfo(WindowInfo.MODALDIALOG
		    | WindowInfo.RESIZABLE);
	    windowInfo.setTitle(windowTitle);
	    Dimension dim = getPreferredSize();
	    MDIFrame a = (MDIFrame) PluginServices.getMainFrame();
	    int maxHeight = a.getHeight() - 175;
	    int maxWidth = a.getWidth() - 15;

	    int width, heigth = 0;
	    if (dim.getHeight() > maxHeight) {
		heigth = maxHeight;
	    } else {
		heigth = new Double(dim.getHeight()).intValue();
	    }
	    if (dim.getWidth() > maxWidth) {
		width = maxWidth;
	    } else {
		width = new Double(dim.getWidth()).intValue();
	    }
	    windowInfo.setWidth(width + 15);
	    windowInfo.setHeight(heigth + 15);
	}
	return windowInfo;
    }

    public void windowActivated() {
	if (jRootPane == null) {
	    jRootPane = this.getRootPane();
	    if (jRootPane != null) {
		jRootPane.setDefaultButton(this.getOKButton());
	    }
	}
    }

    public void windowClosed() {
    }

    public Object getWindowProfile() {
	return null;
    }

}
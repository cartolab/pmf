package es.udc.cartolab.gvsig.pmf.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;

import es.icarto.gvsig.navtableforms.AbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;
import es.icarto.gvsig.navtableforms.ormlite.domainvalues.KeyValue;
import es.icarto.gvsig.navtableforms.utils.FormFactory;

@SuppressWarnings("serial")
public class VentasForm extends AbstractSubForm implements ActionListener {

    public static final String NAME = "ventas";
    public static String[] colNames4Balances = { "cod_comprador", "precio",
	    "volumen", "total" };
    public static String[] colAlias4Balances = { "Comprador", "Precio",
	    "Volumen", "Total" };

    public static String[] colNames4Compradores = { "cod_balance", "precio",
	    "volumen", "total" };
    public static String[] colAlias4Compradores = { "Balance", "Precio",
	    "Volumen", "Total" };

    private boolean formInitialized = false;
    private AbstractForm compradoresForm = null;

    public VentasForm() {
	super();
	JButton button = (JButton) getFormPanel(null).getComponentByName(
		"compradores_button");
	button.addActionListener(this);
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	JComboBox codComprador = (JComboBox) getWidgetComponents().get(
		"cod_comprador");
	Object item = codComprador.getSelectedItem();
	if (item != null) {
	    String key = ((KeyValue) item).getKey();
	    openFCForm(key);
	}
    }

    protected void openFCForm(String keyValue) {
	if (!formInitialized) {
	    formInitialized = true;
	    compradoresForm = FormFactory
		    .createFormRegistered(CompradoresForm.NAME);
	    compradoresForm.init();
	}
	int selectedFeature = 0;
	try {
	    SelectableDataSource sds = compradoresForm.getRecordset();
	    int pkIndex;
	    pkIndex = sds.getFieldIndexByName(CompradoresForm.PKFIELD);
	    long nRows = sds.getRowCount();
	    for (int i = 0; i < nRows; i++) {
		if (sds.getFieldValue(i, pkIndex).toString().equals(keyValue)) {
		    selectedFeature = i;
		    break;
		}
	    }
	} catch (ReadDriverException e) {
	    e.printStackTrace();
	}
	compradoresForm.setPosition(selectedFeature);
	PluginServices.getMDIManager().addWindow(compradoresForm);
    }
}

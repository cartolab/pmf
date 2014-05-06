package es.udc.cartolab.gvsig.pmf.forms;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;

import es.icarto.gvsig.navtableforms.AbstractForm;
import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.handler.AlphanumericTableHandler;
import es.icarto.gvsig.navtableforms.ormlite.domainvalues.KeyValue;
import es.icarto.gvsig.navtableforms.utils.FormFactory;

@SuppressWarnings("serial")
public class ParcelasForm extends BasicAbstractForm {

    public static final String NAME = "parcelas";
    public static final String PKFIELD = "cod_parcela";
    private static final String FCCBKEY = "codigo_fc";
    private static final String FCBTNKEY = "fc_button";
    public static final String CODCOM = "cod_com";
    public static final String CODVIV = "cod_viv";
    private JComboBox fcComboBox;
    private JButton fcButton;
    private AbstractForm fcForm = FormFactory
	    .createFormRegistered(FuentesComunitariasForm.NAME);
    private boolean formInitialized = false;

    public ParcelasForm(FLyrVect layer) {
	super(layer);

	addTableHandler(new AlphanumericTableHandler("cultivos",
		getWidgetComponents(), PKFIELD, CultivosForm.colNames,
		CultivosForm.colAlias));

	addTableHandler(new AlphanumericTableHandler("balances",
		getWidgetComponents(), PKFIELD, BalancesForm.colNames,
		BalancesForm.colAlias));

	// TODO: Filtrar por comunidad
	fcComboBox = (JComboBox) getWidgetComponents().get(FCCBKEY);
	fcButton = (JButton) formBody.getComponentByName(FCBTNKEY);
	fcComboBox.addActionListener(this);
	fcButton.addActionListener(this);
    }

    @Override
    protected String getPrimaryKeyValue() {
	return getFormController().getValue(PKFIELD);
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	super.actionPerformed(e);
	if (e.getSource() == fcComboBox) {
	    if (fcComboBox.getSelectedItem() instanceof KeyValue) {
		if (((KeyValue) fcComboBox.getSelectedItem()).getKey() != null) {
		    fcButton.setEnabled(true);
		} else {
		    fcButton.setEnabled(false);
		}
	    }
	} else {
	    if (e.getSource() == fcButton) {
		String key = ((KeyValue) fcComboBox.getSelectedItem()).getKey();
		if (key != null) {
		    openFCForm(key);
		}
	    }
	}
    }

    protected void openFCForm(String keyValue) {
	if (!formInitialized) {
	    formInitialized = true;
	    fcForm.init();
	}
	int selectedFeature = 0;
	try {
	    SelectableDataSource sds = fcForm.getRecordset();
	    int pkIndex;
	    pkIndex = sds.getFieldIndexByName(FuentesComunitariasForm.PKFIELD);
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
	fcForm.setPosition(selectedFeature);
	PluginServices.getMDIManager().addWindow(fcForm);
    }

}

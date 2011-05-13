package es.udc.cartolab.gvsig.pmf.forms;

import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.jeta.forms.components.panel.FormPanel;

import es.udc.cartolab.gvsig.navtableforms.AbstractForm;
import es.udc.cartolab.gvsig.navtableforms.validation.FormBinding;
import es.udc.cartolab.gvsig.navtableforms.validation.FormModel;
import es.udc.cartolab.gvsig.pmf.forms.validation.binding.CentroEducativoBinding;
import es.udc.cartolab.gvsig.pmf.forms.validation.model.CentroEducativoModel;
import es.udc.cartolab.gvsig.pmf.preferences.Preferences;

public class CentroEducativoForm extends AbstractForm {

    public CentroEducativoForm(FLyrVect layer) {
	super(layer);
	viewInfo.setHeight(550);
	viewInfo.setWidth(500);
	viewInfo.setTitle(PluginServices.getText(this, "_centro_educativo"));
    }

    @Override
    public FormPanel getFormBody() {
	return new FormPanel("centro_educativo.xml");
    }

    @Override
    public FormModel getFormModel(Container c) {
	return new CentroEducativoModel(c);
    }

    @Override
    public FormBinding getFormBinding(FormModel model) {
	return new CentroEducativoBinding(model);
    }

    @Override
    public Logger getLoggerName() {
	return Logger.getLogger("PMF");
    }

    protected String getXmlFileName() {
	return Preferences.getXMLFileName();
    }

    protected String getAliasInXML() {
	return "centros_educativos";
    }

    protected boolean isPKAlreadyInUse() {
	try {
	    if (recordset.getRowCount() == 0) {
		return false;
	    } else {
		String nameOfKeyInModel = "cod_cedu";
		String nameOfKeyInRecordSet = "cod_cedu";
		String valueFromModel = formModel.getWidgetValues().get(
			nameOfKeyInModel);
		for (int index = 0; index < recordset.getRowCount(); index++) {
		    int indiceCampo = recordset
			    .getFieldIndexByName(nameOfKeyInRecordSet);
		    String valueFromRecordSet = recordset.getFieldValue(index,
			    indiceCampo).toString();
		    if (valueFromRecordSet.equals(valueFromModel)) {
			if (isUpdatingTheSameRegister(index, currentPosition)) {
			    return false;
			} else {
			    return true;
			}
		    }
		}
		return false;
	    }
	} catch (ReadDriverException e) {
	    logger.error(e.getMessage(), e);
	    return false;
	}
    }

    private boolean isUpdatingTheSameRegister(int index, long currentPosition) {
	if (index == (int) currentPosition) {
	    return true;
	} else {
	    return false;
	}
    }

    protected boolean primaryKeyHasErrors() {
	if (isPKAlreadyInUse()) {
	    JOptionPane.showMessageDialog(this, PluginServices.getText(this,
		    "choose_other_pk"), PluginServices.getText(this,
		    "pk_already_used"), JOptionPane.ERROR_MESSAGE);
	    return true;
	} else {
	    return false;
	}
    }

    @Override
    protected boolean isSaveable() {
	if (validationHasErrors()) {
	    return false;
	} else if (primaryKeyHasErrors()) {
	    return false;
	}
	return true;
    }

    @Override
    protected void setListeners() {
	super.setListeners();
	JComboBox tipoCEdu = (JComboBox) formBody
		.getComponentByName("tipo_cedu.CB");
	tipoCEdu.setActionCommand("merEscolar");
	tipoCEdu.addActionListener(this);
    }

    private void setMerEscolarEnabledIfNeeded() {
	JCheckBox merEscol = (JCheckBox) formBody
		.getComponentByName("mer_escol.CHB");
	JComboBox tipoCEdu = (JComboBox) formBody
		.getComponentByName("tipo_cedu.CB");

	if (tipoCEdu.getSelectedItem().equals("Jard�n de ni�os")) {
	    merEscol.setEnabled(true);
	} else {
	    merEscol.setEnabled(false);
	    merEscol.setSelected(false);
	}
    }

    @Override
    protected void fillSpecificValues() {
	setMerEscolarEnabledIfNeeded();
    }

    @Override
    protected void removeListeners() {
	JComboBox tipoCEdu = (JComboBox) formBody
		.getComponentByName("tipo_cedu.CB");
	tipoCEdu.removeActionListener(this);
	super.removeListeners();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	super.actionPerformed(e);
	String action = e.getActionCommand();
	if (action.equals("merEscolar")) {
	    setMerEscolarEnabledIfNeeded();
	}
    }
}
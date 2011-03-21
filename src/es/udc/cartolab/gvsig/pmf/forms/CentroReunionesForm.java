package es.udc.cartolab.gvsig.pmf.forms;

import java.awt.Container;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.jeta.forms.components.panel.FormPanel;

import es.udc.cartolab.gvsig.navtableforms.AbstractForm;
import es.udc.cartolab.gvsig.navtableforms.validation.FormBinding;
import es.udc.cartolab.gvsig.navtableforms.validation.FormModel;
import es.udc.cartolab.gvsig.pmf.forms.validation.binding.CentroReunionesBinding;
import es.udc.cartolab.gvsig.pmf.forms.validation.model.CentroReunionesModel;
import es.udc.cartolab.gvsig.pmf.preferences.Preferences;

public class CentroReunionesForm extends AbstractForm
{

	public CentroReunionesForm(FLyrVect layer) {
		super(layer);
		viewInfo.setHeight(470);
		viewInfo.setWidth(520);
		viewInfo.setTitle(PluginServices.getText(this, "_centro_reuniones"));
	}

	@Override
	public FormPanel getFormBody() {
		return new FormPanel("centros_reuniones.xml");
	}

	@Override
	public FormModel getFormModel(Container c) {
		return new CentroReunionesModel(c);
	}

	@Override
	public FormBinding getFormBinding(FormModel model) {
		return new CentroReunionesBinding(model);
	}

	@Override
	public Logger getLoggerName() {
		return Logger.getLogger("PMF");
	}


	protected String getXmlFileName() {
		return Preferences.getXMLFileName();
	}


	protected String getAliasInXML() {
		return "centros_reuniones";
	}


	protected boolean isPKAlreadyInUse() {
		try {
			if (recordset.getRowCount() == 0) {
				return false;
			} else {
				String nameOfKeyInModel = "cod_creu";
				String nameOfKeyInRecordSet = "cod_creu";
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
			JOptionPane
			.showMessageDialog(
					this,
					PluginServices.getText(this, "choose_other_pk"),
					PluginServices.getText(this, "pk_already_used"),
					JOptionPane.ERROR_MESSAGE);
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
	protected void fillSpecificValues() {
	}
}
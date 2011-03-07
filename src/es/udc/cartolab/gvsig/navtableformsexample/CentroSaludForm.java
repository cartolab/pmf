package es.udc.cartolab.gvsig.navtableformsexample;

import java.awt.Container;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.jeta.forms.components.panel.FormPanel;

import es.udc.cartolab.gvsig.arqueoponte.preferences.Preferences;
import es.udc.cartolab.gvsig.navtableforms.AbstractForm;
import es.udc.cartolab.gvsig.navtableforms.validation.FormBinding;
import es.udc.cartolab.gvsig.navtableforms.validation.FormModel;
import es.udc.cartolab.gvsig.navtableformsexample.validation.CentroSaludBinding;
import es.udc.cartolab.gvsig.navtableformsexample.validation.CentroSaludModel;

public class CentroSaludForm extends AbstractForm
{

	public CentroSaludForm(FLyrVect layer) {
		super(layer);
		viewInfo.setHeight(500);
		viewInfo.setWidth(450);
		viewInfo.setTitle(PluginServices.getText(this, "Centros de salud"));
	}

	@Override
	public FormPanel getFormBody() {
		return new FormPanel("centro_salud.xml");
	}

	@Override
	public FormModel getFormModel(Container c) {
		return new CentroSaludModel(c);
	}

	@Override
	public FormBinding getFormBinding(FormModel model) {
		return new CentroSaludBinding(model);
	}

	@Override
	public Logger getLoggerName() {
		return Logger.getLogger("PMF");
	}


	protected String getXmlFileName() {
		return Preferences.getXMLFileName();
	}


	protected String getAliasInXML() {
		return "centros_salud";
	}


	protected String getBaseDirectory() {
		try {
			return Preferences.getPreferences().getBaseDirectory();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return "";
		}
	}

	protected boolean isPKAlreadyInUse() {
		try {
			if (recordset.getRowCount() == 0) {
				return false;
			} else {
				String nameOfKeyInModel = "cod_csalud";
				String nameOfKeyInRecordSet = "cod_csalud";
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

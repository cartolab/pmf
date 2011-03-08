package es.udc.cartolab.gvsig.pmf.forms;

import java.awt.Container;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.jeta.forms.components.panel.FormPanel;

import es.udc.cartolab.gvsig.navtableforms.AbstractForm;
import es.udc.cartolab.gvsig.navtableforms.validation.FormBinding;
import es.udc.cartolab.gvsig.navtableforms.validation.FormModel;
import es.udc.cartolab.gvsig.pmf.forms.validation.CentroComunalBinding;
import es.udc.cartolab.gvsig.pmf.forms.validation.CentroComunalModel;
import es.udc.cartolab.gvsig.pmf.preferences.Preferences;

public class CentroComunalForm extends AbstractForm
{

	public CentroComunalForm(FLyrVect layer) {
		super(layer);
		viewInfo.setHeight(500);
		viewInfo.setWidth(450);
		viewInfo.setTitle(PluginServices.getText(this, "Centros comunales"));
	}

	@Override
	public FormPanel getFormBody() {
		return new FormPanel("centro_comunal.xml");
	}

	@Override
	public FormModel getFormModel(Container c) {
		return new CentroComunalModel(c);
	}

	@Override
	public FormBinding getFormBinding(FormModel model) {
		return new CentroComunalBinding(model);
	}

	@Override
	public Logger getLoggerName() {
		return Logger.getLogger("PMF");
	}


	protected String getXmlFileName() {
		return Preferences.getXMLFileName();
	}


	protected String getAliasInXML() {
		return "centros_comunales";
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

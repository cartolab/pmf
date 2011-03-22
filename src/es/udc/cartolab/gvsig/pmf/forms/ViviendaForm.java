package es.udc.cartolab.gvsig.pmf.forms;

import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.jeta.forms.components.panel.FormPanel;

import es.udc.cartolab.gvsig.navtableforms.AbstractForm;
import es.udc.cartolab.gvsig.navtableforms.validation.FormBinding;
import es.udc.cartolab.gvsig.navtableforms.validation.FormModel;
import es.udc.cartolab.gvsig.pmf.forms.validation.binding.ViviendaBinding;
import es.udc.cartolab.gvsig.pmf.forms.validation.model.ViviendaModel;
import es.udc.cartolab.gvsig.pmf.preferences.Preferences;

public class ViviendaForm extends AbstractForm
{

	public ViviendaForm(FLyrVect layer) {
		super(layer);
		viewInfo.setHeight(800);
		viewInfo.setWidth(650);
		viewInfo.setTitle(PluginServices.getText(this, "_viviendas"));
	}

	@Override
	public FormPanel getFormBody() {
		return new FormPanel("vivienda.xml");
	}

	@Override
	public FormModel getFormModel(Container c) {
		return new ViviendaModel(c);
	}

	@Override
	public FormBinding getFormBinding(FormModel model) {
		return new ViviendaBinding(model);
	}

	@Override
	public Logger getLoggerName() {
		return Logger.getLogger("PMF");
	}


	protected String getXmlFileName() {
		return Preferences.getXMLFileName();
	}


	protected String getAliasInXML() {
		return "viviendas";
	}

	protected boolean isPKAlreadyInUse() {
		try {
			if (recordset.getRowCount() == 0) {
				return false;
			} else {
				String nameOfKeyInModel = "cod_viv";
				String nameOfKeyInRecordSet = "cod_viv";
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
	
	private void checkBoxEnablesTextField(String checkBoxName, String textFieldName) {
		JCheckBox checkBox = (JCheckBox) formBody.getComponentByName(checkBoxName);
		JTextField textField = (JTextField) formBody.getComponentByName(textFieldName);

		if (checkBox.isSelected()) {
			textField.setEnabled(true);
		} else {
			textField.setEnabled(false);
			textField.setText("");
		}
	}
	
	private void comboBoxEnablesTextField(String comboBoxName, String enabledValue, String textFieldName) {
		JComboBox comboBox = (JComboBox) formBody.getComponentByName(comboBoxName);
		JTextField textField = (JTextField) formBody.getComponentByName(textFieldName);

		if (comboBox.getSelectedItem().equals(enabledValue)) {
			textField.setEnabled(true);
		} else {
			textField.setEnabled(false);
			textField.setText("");
		}
	}
	
	private void checkBoxEnablesComboBox(String checkBoxName, String comboBoxName) {
		JCheckBox checkBox = (JCheckBox) formBody.getComponentByName(checkBoxName);
		JComboBox comboBox = (JComboBox) formBody.getComponentByName(comboBoxName);

		if (checkBox.isSelected()) {
			comboBox.setEnabled(true);
		} else {
			comboBox.setEnabled(false);
			comboBox.setSelectedIndex(0);
		}
	}

	@Override
	protected void fillSpecificValues() {
		checkBoxEnablesTextField("hay_ot_act.CHB", "otras_act.TF");
		checkBoxEnablesTextField("hay_ot_ing.CHB", "otros_ing.TF");
		comboBoxEnablesTextField("estatus_vi.CB", "Otro", "ot_stat_vi.TF");
		checkBoxEnablesComboBox("pro_viv.CHB", "pro_vivsex.CB");
		comboBoxEnablesTextField("mat_piso.CB", "Otro", "ot_mat_pi.TF");
		comboBoxEnablesTextField("mat_pared.CB", "Otro", "ot_mat_pa.TF");
		comboBoxEnablesTextField("mat_techo.CB", "Otro", "ot_mat_te.TF");
		checkBoxEnablesTextField("consumo_h.CHB", "cual_ch.TF");
		checkBoxEnablesTextField("dep_almac.CHB", "ot_dep_alm.TF");
	}

	protected void setListeners() {
		super.setListeners();
		JCheckBox hay_ot_act = (JCheckBox) formBody.getComponentByName("hay_ot_act.CHB");
		hay_ot_act.setActionCommand("otras_act");
		hay_ot_act.addActionListener(this);
		JCheckBox hay_ot_ing = (JCheckBox) formBody.getComponentByName("hay_ot_ing.CHB");
		hay_ot_ing.setActionCommand("otros_ing");
		hay_ot_ing.addActionListener(this);
		JComboBox estatus_vi = (JComboBox) formBody.getComponentByName("estatus_vi.CB");
		estatus_vi.setActionCommand("ot_stat_vi");
		estatus_vi.addActionListener(this);
		JCheckBox pro_viv = (JCheckBox) formBody.getComponentByName("pro_viv.CHB");
		pro_viv.setActionCommand("pro_vivsex");
		pro_viv.addActionListener(this);
		JComboBox mat_piso = (JComboBox) formBody.getComponentByName("mat_piso.CB");
		mat_piso.setActionCommand("ot_mat_pi");
		mat_piso.addActionListener(this);
		JComboBox mat_pared = (JComboBox) formBody.getComponentByName("mat_pared.CB");
		mat_pared.setActionCommand("ot_mat_pa");
		mat_pared.addActionListener(this);
		JComboBox mat_techo = (JComboBox) formBody.getComponentByName("mat_techo.CB");
		mat_techo.setActionCommand("ot_mat_te");
		mat_techo.addActionListener(this);
		JCheckBox consumo_h = (JCheckBox) formBody.getComponentByName("consumo_h.CHB");
		consumo_h.setActionCommand("cual_ch");
		consumo_h.addActionListener(this);
		JCheckBox dep_almac = (JCheckBox) formBody.getComponentByName("dep_almac.CHB");
		dep_almac.setActionCommand("ot_dep_alm");
		dep_almac.addActionListener(this);
	}

	@Override
	protected void removeListeners() {
		JCheckBox hay_ot_act = (JCheckBox) formBody.getComponentByName("hay_ot_act.CHB");
		hay_ot_act.removeActionListener(this);
		JCheckBox hay_ot_ing = (JCheckBox) formBody.getComponentByName("hay_ot_ing.CHB");
		hay_ot_ing.removeActionListener(this);
		JComboBox estatus_vi = (JComboBox) formBody.getComponentByName("estatus_vi.CB");
		estatus_vi.removeActionListener(this);
		JCheckBox pro_viv = (JCheckBox) formBody.getComponentByName("pro_viv.CHB");
		pro_viv.removeActionListener(this);
		JComboBox mat_piso = (JComboBox) formBody.getComponentByName("mat_piso.CB");
		mat_piso.removeActionListener(this);
		JComboBox mat_pared = (JComboBox) formBody.getComponentByName("mat_pared.CB");
		mat_pared.removeActionListener(this);
		JComboBox mat_techo = (JComboBox) formBody.getComponentByName("mat_techo.CB");
		mat_techo.removeActionListener(this);
		JCheckBox consumo_h = (JCheckBox) formBody.getComponentByName("consumo_h.CHB");
		consumo_h.removeActionListener(this);
		JCheckBox dep_almac = (JCheckBox) formBody.getComponentByName("dep_almac.CHB");
		dep_almac.removeActionListener(this);
		super.removeListeners();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		String action = e.getActionCommand();
		if (action.equals("otras_act")) {
			checkBoxEnablesTextField("hay_ot_act.CHB", "otras_act.TF");
			return;
		}
		if (action.equals("otros_ing")) {
			checkBoxEnablesTextField("hay_ot_ing.CHB", "otros_ing.TF");
			return;
		}
		if (action.equals("ot_stat_vi")) {
			comboBoxEnablesTextField("estatus_vi.CB", "Otro", "ot_stat_vi.TF");
			return;
		}
		if (action.equals("pro_vivsex")) {
			checkBoxEnablesComboBox("pro_viv.CHB", "pro_vivsex.CB");
			return;
		}
		if (action.equals("ot_mat_pi")) {
			comboBoxEnablesTextField("mat_piso.CB", "Otro", "ot_mat_pi.TF");
			return;
		}
		if (action.equals("ot_mat_pa")) {
			comboBoxEnablesTextField("mat_pared.CB", "Otro", "ot_mat_pa.TF");
			return;
		}
		if (action.equals("ot_mat_te")) {
			comboBoxEnablesTextField("mat_techo.CB", "Otro", "ot_mat_te.TF");
			return;
		}
		if (action.equals("cual_ch")) {
			checkBoxEnablesTextField("consumo_h.CHB", "cual_ch.TF");
			return;
		}
		if (action.equals("ot_dep_alm")) {
			checkBoxEnablesTextField("dep_almac.CHB", "ot_dep_alm.TF");
			return;
		}
	}
}

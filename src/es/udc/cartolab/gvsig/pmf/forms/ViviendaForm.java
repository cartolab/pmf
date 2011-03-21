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

	private void setOtras_actEnabledIfNeeded() {
		JCheckBox hay_ot_act = (JCheckBox) formBody.getComponentByName("hay_ot_act.CHB");
		JTextField otras_act = (JTextField) formBody.getComponentByName("otras_act.TF");

		if (hay_ot_act.isSelected()) {
			otras_act.setEnabled(true);
		} else {
			otras_act.setEnabled(false);
		}
	}

	private void setOtros_ingEnabledIfNeeded() {
		JCheckBox hay_ot_ing = (JCheckBox) formBody.getComponentByName("hay_ot_ing.CHB");
		JTextField otros_ing = (JTextField) formBody.getComponentByName("otros_ing.TF");

		if (hay_ot_ing.isSelected()) {
			otros_ing.setEnabled(true);
		} else {
			otros_ing.setEnabled(false);
		}
	}

	private void setOt_stat_viEnabledIfNeeded() {
		JComboBox estatus_vi = (JComboBox) formBody.getComponentByName("estatus_vi.CB");
		JTextField ot_stat_vi = (JTextField) formBody.getComponentByName("ot_stat_vi.TF");

		if (estatus_vi.getSelectedItem().equals("Otro")) {
			ot_stat_vi.setEnabled(true);
		} else {
			ot_stat_vi.setEnabled(false);
		}
	}

	private void setPro_vivsexEnabledIfNeeded() {
		JCheckBox pro_viv = (JCheckBox) formBody.getComponentByName("pro_viv.CHB");
		JComboBox pro_vivsex = (JComboBox) formBody.getComponentByName("pro_vivsex.CB");

		if (pro_viv.isSelected()) {
			pro_vivsex.setEnabled(true);
		} else {
			pro_vivsex.setEnabled(false);
		}
	}

	private void setOt_mat_paEnabledIfNeeded() {
		JComboBox mat_pared = (JComboBox) formBody.getComponentByName("mat_pared.CB");
		JTextField ot_mat_pa = (JTextField) formBody.getComponentByName("ot_mat_pa.TF");

		if (mat_pared.getSelectedItem().equals("Otro")) {
			ot_mat_pa.setEnabled(true);
		} else {
			ot_mat_pa.setEnabled(false);
		}
	}

	private void setOt_mat_teEnabledIfNeeded() {
		JComboBox mat_techo = (JComboBox) formBody.getComponentByName("mat_techo.CB");
		JTextField ot_mat_te = (JTextField) formBody.getComponentByName("ot_mat_te.TF");

		if (mat_techo.getSelectedItem().equals("Otro")) {
			ot_mat_te.setEnabled(true);
		} else {
			ot_mat_te.setEnabled(false);
		}
	}

	private void setOt_mat_piEnabledIfNeeded() {
		JComboBox mat_piso = (JComboBox) formBody.getComponentByName("mat_piso.CB");
		JTextField ot_mat_pi = (JTextField) formBody.getComponentByName("ot_mat_pi.TF");

		if (mat_piso.getSelectedItem().equals("Otro")) {
			ot_mat_pi.setEnabled(true);
		} else {
			ot_mat_pi.setEnabled(false);
		}
	}

	private void setCual_chEnabledIfNeeded() {
		JCheckBox consumo_h = (JCheckBox) formBody.getComponentByName("consumo_h.CHB");
		JTextField cual_ch = (JTextField) formBody.getComponentByName("cual_ch.TF");

		if (consumo_h.isSelected()) {
			cual_ch.setEnabled(true);
		} else {
			cual_ch.setEnabled(false);
		}
	}

	private void setOt_dep_almEnabledIfNeeded() {
		JCheckBox dep_almac = (JCheckBox) formBody.getComponentByName("dep_almac.CHB");
		JTextField ot_dep_alm = (JTextField) formBody.getComponentByName("ot_dep_alm.TF");

		if (dep_almac.isSelected()) {
			ot_dep_alm.setEnabled(true);
		} else {
			ot_dep_alm.setEnabled(false);
		}
	}

	@Override
	protected void fillSpecificValues() {
		setOtras_actEnabledIfNeeded();
		setOtros_ingEnabledIfNeeded();
		setOt_stat_viEnabledIfNeeded();
		setPro_vivsexEnabledIfNeeded();
		setOt_mat_paEnabledIfNeeded();
		setOt_mat_teEnabledIfNeeded();
		setOt_mat_piEnabledIfNeeded();
		setCual_chEnabledIfNeeded();
		setOt_dep_almEnabledIfNeeded();
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
			setOtras_actEnabledIfNeeded();
		} else 
			if (action.equals("otros_ing")) {
				setOtros_ingEnabledIfNeeded();
			} else 
				if (action.equals("ot_stat_vi")) {
					setOt_stat_viEnabledIfNeeded();
				} else 
					if (action.equals("pro_vivsex")) {
						setPro_vivsexEnabledIfNeeded();
					} else 
						if (action.equals("ot_mat_pi")) {
							setOt_mat_piEnabledIfNeeded();
						} else 
							if (action.equals("ot_mat_pa")) {
								setOt_mat_paEnabledIfNeeded();
							} else 
								if (action.equals("ot_mat_te")) {
									setOt_mat_teEnabledIfNeeded();
								} else 
									if (action.equals("cual_ch")) {
										setCual_chEnabledIfNeeded();
									} else 
										if (action.equals("ot_dep_alm")) {
											setOt_dep_almEnabledIfNeeded();
										}
	}
}

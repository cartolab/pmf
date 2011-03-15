package es.udc.cartolab.gvsig.pmf.forms;

import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
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
import es.udc.cartolab.gvsig.pmf.forms.validation.binding.ParcelaBinding;
import es.udc.cartolab.gvsig.pmf.forms.validation.model.ParcelaModel;
import es.udc.cartolab.gvsig.pmf.preferences.Preferences;

public class ParcelaForm extends AbstractForm
{

	public ParcelaForm(FLyrVect layer) {
		super(layer);
		viewInfo.setHeight(500);
		viewInfo.setWidth(450);
		viewInfo.setTitle(PluginServices.getText(this, "Parcelas"));
	}

	@Override
	public FormPanel getFormBody() {
		return new FormPanel("parcela.xml");
	}

	@Override
	public FormModel getFormModel(Container c) {
		return new ParcelaModel(c);
	}

	@Override
	public FormBinding getFormBinding(FormModel model) {
		return new ParcelaBinding(model);
	}

	@Override
	public Logger getLoggerName() {
		return Logger.getLogger("PMF");
	}


	protected String getXmlFileName() {
		return Preferences.getXMLFileName();
	}


	protected String getAliasInXML() {
		return "parcelas";
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

	private void setCodigo_fcEnabledIfNeeded() {
		JCheckBox fuente_co = (JCheckBox) formBody.getComponentByName("fuente_co.CHB");
		JTextField codigo_fc = (JTextField) formBody.getComponentByName("codigo_fc.TF");
		JButton codigo_fc_bt = (JButton) formBody.getComponentByName("codigo_fc.BT");

		if (fuente_co.isSelected()) {
			codigo_fc.setEnabled(true);
			codigo_fc_bt.setEnabled(true);
		} else {
			codigo_fc.setEnabled(false);
			codigo_fc_bt.setEnabled(false);
		}
	}

	private void setOt_legal_pEnabledIfNeeded() {
		JComboBox legal_par = (JComboBox) formBody.getComponentByName("legal_par.CB");
		JTextField ot_legal_p = (JTextField) formBody.getComponentByName("ot_legal_p.TF");

		if (legal_par.getSelectedItem().equals("Otro")) {
			ot_legal_p.setEnabled(true);
		} else {
			ot_legal_p.setEnabled(false);
		}
	}

	private void setD_fue_tanoEnabledIfNeeded() {
		JComboBox d_fue_tan = (JComboBox) formBody.getComponentByName("d_fue_tan.CB");
		JTextField d_fue_tano = (JTextField) formBody.getComponentByName("d_fue_tano.TF.REAL");

		if (d_fue_tan.getSelectedItem().equals("Otra")) {
			d_fue_tano.setEnabled(true);
		} else {
			d_fue_tano.setEnabled(false);
		}
	}

	private void setD_tan_hueoEnabledIfNeeded() {
		JComboBox d_tan_hue = (JComboBox) formBody.getComponentByName("d_tan_hue.CB");
		JTextField d_tan_hueo = (JTextField) formBody.getComponentByName("d_tan_hueo.TF.REAL");

		if (d_tan_hue.getSelectedItem().equals("Otra")) {
			d_tan_hueo.setEnabled(true);
		} else {
			d_tan_hueo.setEnabled(false);
		}
	}

	private void setOt_tip_suEnabledIfNeeded() {
		JComboBox tip_suelo = (JComboBox) formBody.getComponentByName("tip_suelo.CB");
		JTextField ot_tip_su = (JTextField) formBody.getComponentByName("ot_tip_su.TF");

		if (tip_suelo.getSelectedItem().equals("Otro")) {
			ot_tip_su.setEnabled(true);
		} else {
			ot_tip_su.setEnabled(false);
		}
	}

	private void setCercasEnabledIfNeeded() {
		JCheckBox cerca = (JCheckBox) formBody.getComponentByName("cerca.CHB");
		JCheckBox b_vivas = (JCheckBox) formBody.getComponentByName("b_vivas.CHB");
		JCheckBox b_muertas = (JCheckBox) formBody.getComponentByName("b_muertas.CHB");
		JCheckBox hay_ot_cer = (JCheckBox) formBody.getComponentByName("hay_ot_cer.CHB");
		JTextField ot_cerca = (JTextField) formBody.getComponentByName("ot_cerca.TF");

		if (cerca.isSelected()) {
			b_vivas.setEnabled(true);
			b_muertas.setEnabled(true);
			hay_ot_cer.setEnabled(true);
			if (hay_ot_cer.isSelected())
				ot_cerca.setEnabled(true);
		} else {
			b_vivas.setEnabled(false);
			b_muertas.setEnabled(false);
			hay_ot_cer.setEnabled(false);
			ot_cerca.setEnabled(false);
		}
	}

	private void setOt_cercaEnabledIfNeeded() {
		JCheckBox hay_ot_cer = (JCheckBox) formBody.getComponentByName("hay_ot_cer.CHB");
		JTextField ot_cerca = (JTextField) formBody.getComponentByName("ot_cerca.TF");

		if (hay_ot_cer.isSelected()) {
			ot_cerca.setEnabled(true);
		} else {
			ot_cerca.setEnabled(false);
		}
	}

	private void setOtrocanEnabledIfNeeded() {
		JCheckBox hay_ot_cul = (JCheckBox) formBody.getComponentByName("hay_ot_cul.CHB");
		JTextField otrocan = (JTextField) formBody.getComponentByName("otrocan.TF");

		if (hay_ot_cul.isSelected()) {
			otrocan.setEnabled(true);
		} else {
			otrocan.setEnabled(false);
		}
	}

	private void setOtrocspeEnabledIfNeeded() {
		JCheckBox hay_ot_sp = (JCheckBox) formBody.getComponentByName("hay_ot_sp.CHB");
		JTextField otrocspe = (JTextField) formBody.getComponentByName("otrocspe.TF");

		if (hay_ot_sp.isSelected()) {
			otrocspe.setEnabled(true);
		} else {
			otrocspe.setEnabled(false);
		}
	}

	private void setC_conseEnabledIfNeeded() {
		JCheckBox prac_conse = (JCheckBox) formBody.getComponentByName("prac_conse.CHB");
		JTextField c_conse = (JTextField) formBody.getComponentByName("c_conse.TF");

		if (prac_conse.isSelected()) {
			c_conse.setEnabled(true);
		} else {
			c_conse.setEnabled(false);
		}
	}

	private void setC_aborgEnabledIfNeeded() {
		JCheckBox uso_aborg = (JCheckBox) formBody.getComponentByName("uso_aborg.CHB");
		JTextField c_aborg = (JTextField) formBody.getComponentByName("c_aborg.TF");

		if (uso_aborg.isSelected()) {
			c_aborg.setEnabled(true);
		} else {
			c_aborg.setEnabled(false);
		}
	}

	private void setC_insectEnabledIfNeeded() {
		JCheckBox insect_org = (JCheckBox) formBody.getComponentByName("insect_org.CHB");
		JTextField c_insect = (JTextField) formBody.getComponentByName("c_insect.TF");

		if (insect_org.isSelected()) {
			c_insect.setEnabled(true);
		} else {
			c_insect.setEnabled(false);
		}
	}

	private void setC_quimEnabledIfNeeded() {
		JCheckBox uso_quim = (JCheckBox) formBody.getComponentByName("uso_quim.CHB");
		JTextField c_quim = (JTextField) formBody.getComponentByName("c_quim.TF");

		if (uso_quim.isSelected()) {
			c_quim.setEnabled(true);
		} else {
			c_quim.setEnabled(false);
		}
	}

	@Override
	protected void fillSpecificValues() {
		setCodigo_fcEnabledIfNeeded();
		setOt_legal_pEnabledIfNeeded();
		setD_tan_hueoEnabledIfNeeded();
		setD_fue_tanoEnabledIfNeeded();
		setOt_tip_suEnabledIfNeeded();
		setCercasEnabledIfNeeded();
		setOt_cercaEnabledIfNeeded();
		setOtrocanEnabledIfNeeded();
		setOtrocspeEnabledIfNeeded();
		setC_quimEnabledIfNeeded();
		setC_insectEnabledIfNeeded();
		setC_aborgEnabledIfNeeded();
		setC_conseEnabledIfNeeded();
	}

	protected void setListeners() {
		super.setListeners();
		JCheckBox fuente_co = (JCheckBox) formBody.getComponentByName("fuente_co.CHB");
		fuente_co.setActionCommand("codigo_fc");
		fuente_co.addActionListener(this);
		JComboBox legal_par = (JComboBox) formBody.getComponentByName("legal_par.CB");
		legal_par.setActionCommand("ot_legal_p");
		legal_par.addActionListener(this);
		JComboBox d_fue_tan = (JComboBox) formBody.getComponentByName("d_fue_tan.CB");
		d_fue_tan.setActionCommand("d_fue_tano");
		d_fue_tan.addActionListener(this);
		JComboBox d_tan_hue = (JComboBox) formBody.getComponentByName("d_tan_hue.CB");
		d_tan_hue.setActionCommand("d_tan_hueo");
		d_tan_hue.addActionListener(this);
		JComboBox tip_suelo = (JComboBox) formBody.getComponentByName("tip_suelo.CB");
		tip_suelo.setActionCommand("ot_tip_su");
		tip_suelo.addActionListener(this);
		JCheckBox cerca = (JCheckBox) formBody.getComponentByName("cerca.CHB");
		cerca.setActionCommand("cercas");
		cerca.addActionListener(this);
		JCheckBox hay_ot_cer = (JCheckBox) formBody.getComponentByName("hay_ot_cer.CHB");
		hay_ot_cer.setActionCommand("hay_ot_cer");
		hay_ot_cer.addActionListener(this);
		JCheckBox hay_ot_cul = (JCheckBox) formBody.getComponentByName("hay_ot_cul.CHB");
		hay_ot_cul.setActionCommand("hay_ot_cul");
		hay_ot_cul.addActionListener(this);
		JCheckBox hay_ot_sp = (JCheckBox) formBody.getComponentByName("hay_ot_sp.CHB");
		hay_ot_sp.setActionCommand("hay_ot_sp");
		hay_ot_sp.addActionListener(this);
		JCheckBox prac_conse = (JCheckBox) formBody.getComponentByName("prac_conse.CHB");
		prac_conse.setActionCommand("prac_conse");
		prac_conse.addActionListener(this);
		JCheckBox uso_aborg = (JCheckBox) formBody.getComponentByName("uso_aborg.CHB");
		uso_aborg.setActionCommand("uso_aborg");
		uso_aborg.addActionListener(this);
		JCheckBox insect_org = (JCheckBox) formBody.getComponentByName("insect_org.CHB");
		insect_org.setActionCommand("insect_org");
		insect_org.addActionListener(this);
		JCheckBox uso_quim = (JCheckBox) formBody.getComponentByName("uso_quim.CHB");
		uso_quim.setActionCommand("uso_quim");
		uso_quim.addActionListener(this);
	}

	@Override
	protected void removeListeners() {
		JCheckBox fuente_co = (JCheckBox) formBody.getComponentByName("fuente_co.CHB");
		fuente_co.removeActionListener(this);
		JComboBox legal_par = (JComboBox) formBody.getComponentByName("legal_par.CB");
		legal_par.removeActionListener(this);
		JComboBox d_fue_tan = (JComboBox) formBody.getComponentByName("d_fue_tan.CB");
		d_fue_tan.removeActionListener(this);
		JComboBox d_tan_hue = (JComboBox) formBody.getComponentByName("d_tan_hue.CB");
		d_tan_hue.removeActionListener(this);
		JComboBox tip_suelo = (JComboBox) formBody.getComponentByName("tip_suelo.CB");
		tip_suelo.removeActionListener(this);
		JCheckBox cerca = (JCheckBox) formBody.getComponentByName("cerca.CHB");
		cerca.removeActionListener(this);
		JCheckBox hay_ot_cer = (JCheckBox) formBody.getComponentByName("hay_ot_cer.CHB");
		hay_ot_cer.removeActionListener(this);
		JCheckBox hay_ot_cul = (JCheckBox) formBody.getComponentByName("hay_ot_cul.CHB");
		hay_ot_cul.removeActionListener(this);
		JCheckBox hay_ot_sp = (JCheckBox) formBody.getComponentByName("hay_ot_sp.CHB");
		hay_ot_sp.removeActionListener(this);
		JCheckBox prac_conse = (JCheckBox) formBody.getComponentByName("prac_conse.CHB");
		prac_conse.removeActionListener(this);
		JCheckBox uso_aborg = (JCheckBox) formBody.getComponentByName("uso_aborg.CHB");
		uso_aborg.removeActionListener(this);
		JCheckBox insect_org = (JCheckBox) formBody.getComponentByName("insect_org.CHB");
		insect_org.removeActionListener(this);
		JCheckBox uso_quim = (JCheckBox) formBody.getComponentByName("uso_quim.CHB");
		uso_quim.removeActionListener(this);
		super.removeListeners();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		String action = e.getActionCommand();
		if (action.equals("codigo_fc")) {
			setCodigo_fcEnabledIfNeeded();
		} else 
			if (action.equals("ot_legal_p")) {
				setOt_legal_pEnabledIfNeeded();
			} else
				if (action.equals("d_fue_tano")) {
					setD_fue_tanoEnabledIfNeeded();
				} else
					if (action.equals("d_tan_hueo")) {
						setD_tan_hueoEnabledIfNeeded();
					} else
						if (action.equals("ot_tip_su")) {
							setOt_tip_suEnabledIfNeeded();
						} else
							if (action.equals("cercas")) {
								setCercasEnabledIfNeeded();
							} else
								if (action.equals("hay_ot_cer")) {
									setOt_cercaEnabledIfNeeded();
								} else
									if (action.equals("hay_ot_cul")) {
										setOtrocanEnabledIfNeeded();
									} else
										if (action.equals("hay_ot_sp")) {
											setOtrocspeEnabledIfNeeded();
										} else
											if (action.equals("prac_conse")) {
												setC_conseEnabledIfNeeded();
											} else
												if (action.equals("uso_aborg")) {
													setC_aborgEnabledIfNeeded();
												} else
													if (action.equals("insect_org")) {
														setC_insectEnabledIfNeeded();
													} else
														if (action.equals("uso_quim")) {
															setC_quimEnabledIfNeeded();
														}
	}
}

package es.udc.cartolab.gvsig.pmf.forms;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import com.hardcode.driverManager.DriverLoadException;
import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.hardcode.gdbms.engine.instruction.EvaluationException;
import com.hardcode.gdbms.engine.instruction.SemanticException;
import com.hardcode.gdbms.parser.ParseException;
import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.fmap.drivers.FieldDescription;
import com.iver.cit.gvsig.fmap.edition.IEditableSource;
import com.iver.cit.gvsig.fmap.edition.IRowEdited;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.project.documents.table.gui.Table;
import com.jeta.forms.components.panel.FormPanel;

import es.udc.cartolab.gvsig.navtable.AlphanumericNavTable;
import es.udc.cartolab.gvsig.navtableforms.AbstractForm;
import es.udc.cartolab.gvsig.navtableforms.validation.FormBinding;
import es.udc.cartolab.gvsig.navtableforms.validation.FormModel;
import es.udc.cartolab.gvsig.pmf.forms.table.NonEditableTableModel;
import es.udc.cartolab.gvsig.pmf.forms.validation.binding.ParcelaBinding;
import es.udc.cartolab.gvsig.pmf.forms.validation.model.ParcelaModel;
import es.udc.cartolab.gvsig.pmf.preferences.Preferences;

public class ParcelaForm extends AbstractForm implements MouseListener, InternalFrameListener
{
	private JTable cultivosTable;

	public ParcelaForm(FLyrVect layer) {
		super(layer);
		viewInfo.setHeight(800);
		viewInfo.setWidth(650);
		viewInfo.setTitle(PluginServices.getText(this, "_parcelas"));
		cultivosTable = (JTable) formBody.getComponentByName("cultivos");
		
		fillJTable(cultivosTable, "cultivos");
		
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
	
	public void fillJTable(JTable table, String sourcename){
		
		IWindow[] windows = PluginServices.getMDIManager().getAllWindows();
		FieldDescription[] columns = {};
		for (int i=0; i<windows.length; i++) {
			if (windows[i] instanceof Table) {
				String name = ((Table) windows[i]).getModel().getName();
				if (name.endsWith(".dbf")) {
					name = name.substring(0, name.lastIndexOf(".dbf"));
					if (name.equals(sourcename)) {
						IEditableSource source = ((Table) windows[i]).getModel().getModelo();
						columns = source.getFieldsDescription();
						
						ArrayList<String> columnNames = new ArrayList<String>();
						
						for (int j=0; j < columns.length; j++) {
							columnNames.add(columns[j].getFieldName());							
						}
						
						ArrayList<Object[]> rows = new ArrayList<Object[]>();
						Object[] row;
						
						try {
							for (int j = 0; j < source.getRowCount(); j++) {
								IRowEdited sourceRow = source.getRow(j);
								row = sourceRow.getAttributes();
								rows.add(row);
							}
						} catch (ReadDriverException e) {
							e.printStackTrace();
						}
						
						Object[][] data = new Object[1][1];
						table.setModel(new NonEditableTableModel(rows.toArray(data), columnNames.toArray()));
						break;
					}
				}
			}
		}
		
		table.removeMouseListener(this);
		table.addMouseListener(this);

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
	
	private long doFilter(IEditableSource recordset, ArrayList<String> where) throws ReadDriverException, DriverLoadException, ParseException, SemanticException, EvaluationException, IOException {
		long recordPosition = 0;
		for (int i=0; i<recordset.getRowCount(); i++) {
			IRowEdited row = recordset.getRow(i);
			boolean same = true;
			for (int j=0; j<where.size(); j++) {
				if (!where.get(j).equals(row.getAttribute(j).toString())) {
					same = false;
					break;
				}
			}
			if (same) {
				recordPosition = i;
				break;
			}
		}
		return recordPosition+1;
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
	
	private void displayNavTable(JTable refTable, String dbfName) {
		IWindow[] windows = PluginServices.getMDIManager().getAllWindows();
		boolean found = false;
		for (int i=0; i<windows.length; i++) {
			if (windows[i] instanceof Table) {
				String name = ((Table) windows[i]).getModel().getName();
				if (name.endsWith(".dbf")) {
					name = name.substring(0, name.lastIndexOf(".dbf"));
					if (name.equals(dbfName)) {
						IEditableSource source = ((Table) windows[i]).getModel().getModelo();
						found = true;
						AlphanumericNavTable navTable;
						try {
							navTable = new AlphanumericNavTable(source, dbfName);
						
							if (navTable.init()) {
								int selected = refTable.getSelectedRow();
								ArrayList<String> where = new ArrayList<String>();
								TableModel model = refTable.getModel();
								for (int j=0; j<model.getColumnCount(); j++){
									where.add(model.getValueAt(selected, j).toString());
								}
								try {
									navTable.setPosition(doFilter(source,where));
								} catch (Exception e) {
									e.printStackTrace();
								}
								PluginServices.getMDIManager().addCentredWindow(navTable);
								JInternalFrame parent = (JInternalFrame) navTable.getRootPane().getParent();
								parent.addInternalFrameListener(this);
							}
						} catch (ReadDriverException e) {
							e.printStackTrace();
						}
						break;
					}
				}
			}
		}
		if (!found) {
			JOptionPane.showMessageDialog(this, "La tabla \"" + dbfName + "\" no esta cargada");
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		
		if ((event.getSource() == cultivosTable) &&(event.getClickCount() == 2)) {
			displayNavTable(cultivosTable, "cultivos");
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {

		fillJTable(cultivosTable, "cultivos");
		
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		
	}
}
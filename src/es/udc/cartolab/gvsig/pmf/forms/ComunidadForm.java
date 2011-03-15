package es.udc.cartolab.gvsig.pmf.forms;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.hardcode.driverManager.DriverLoadException;
import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.hardcode.gdbms.engine.data.DataSourceFactory;
import com.hardcode.gdbms.engine.instruction.EvaluationException;
import com.hardcode.gdbms.engine.instruction.SemanticException;
import com.hardcode.gdbms.engine.strategies.FilteredDataSource;
import com.hardcode.gdbms.parser.ParseException;
import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;
import com.jeta.forms.components.panel.FormPanel;

import es.udc.cartolab.gvsig.navtableforms.AbstractForm;
import es.udc.cartolab.gvsig.navtableforms.Utils;
import es.udc.cartolab.gvsig.navtableforms.validation.FormBinding;
import es.udc.cartolab.gvsig.navtableforms.validation.FormModel;
import es.udc.cartolab.gvsig.pmf.forms.validation.binding.ComunidadBinding;
import es.udc.cartolab.gvsig.pmf.forms.validation.model.ComunidadModel;
import es.udc.cartolab.gvsig.pmf.preferences.Preferences;

public class ComunidadForm extends AbstractForm
{

	private long csaludPosition;
	private long creunionPosition;
	private long centJardPosition;
	private long centEscPosition;
	private long centCCYDPosition;

	private boolean chbChanged = false;

	public ComunidadForm(FLyrVect layer) {
		super(layer);
		viewInfo.setHeight(500);
		viewInfo.setWidth(575);
		viewInfo.setTitle(PluginServices.getText(this, "_comunidades"));
	}

	public boolean init() {
		boolean  r = super.init();
		setChangedValues(getCHBChanged());
		return r;
	}

	@Override
	public FormPanel getFormBody() {
		return new FormPanel("comunidad.xml");
	}

	@Override
	public FormModel getFormModel(Container c) {
		return new ComunidadModel(c);
	}

	@Override
	public FormBinding getFormBinding(FormModel model) {
		return new ComunidadBinding(model);
	}

	@Override
	public Logger getLoggerName() {
		return Logger.getLogger("PMF");
	}


	protected String getXmlFileName() {
		return Preferences.getXMLFileName();
	}


	protected String getAliasInXML() {
		return "comunidad";
	}

	protected boolean isPKAlreadyInUse() {
		try {
			if (recordset.getRowCount() == 0) {
				return false;
			} else {
				String nameOfKeyInModel = "cod_com";
				String nameOfKeyInRecordSet = "cod_com";
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


	private long doFilter(String layerName, String where) throws ReadDriverException, DriverLoadException, ParseException, SemanticException, EvaluationException, IOException {
		long recordPosition = -1;
		SelectableDataSource recordset = Utils.getFlyrVect(view, layerName).getRecordset();
		String sql = "select * from " + recordset.getName() + " " + where + ";";
		recordset.start();
		FilteredDataSource result = (FilteredDataSource) recordset.getDataSourceFactory().executeSQL(sql, DataSourceFactory.MANUAL_OPENING);
		if (result.getRowCount() == 1) {
			recordPosition = result.getWhereFilter()[0];
		}
		recordset.stop();

		return recordPosition;
	}


	private long enableButton(String elementName, String tableName, String where) {
		long rowPos = 0;

		JButton button = (JButton) formBody.getComponentByName(elementName + ".BT");
		JCheckBox chb = (JCheckBox) formBody.getComponentByName(elementName + ".CHB");

		try {
			rowPos = doFilter(tableName, where) + 1;
		} catch (Exception e) {
			getLoggerName().error(e.getMessage(), e);
		}

		if (rowPos != 0) {
			button.setEnabled(true);
			if (! chb.isSelected()) {
				setCHBChanged(true);
			}
			chb.setSelected(true);

		} else {
			button.setEnabled(false);
			if (chb.isSelected()) {
				setCHBChanged(true);
			}
			chb.setSelected(false);
		}
		return rowPos;
	}

	@Override
	protected void fillSpecificValues() {
		String foreingKey = formModel.getWidgetValues().get("cod_com");
		String where = "where cod_com = '" + foreingKey + "'";
		csaludPosition = enableButton("csalud", "centros_salud", where);
		creunionPosition = enableButton("creunion", "centros_reuniones", where);
		centJardPosition = enableButton("cent_jard", "centros_educativos", where + " and tipo_cedu = 'Jard�n de ni�os'");
		centEscPosition = enableButton("cent_esc", "centros_educativos", where + " and tipo_cedu = 'Centro educativo escolar'");
		centCCYDPosition = enableButton("cent_ccyd", "centros_educativos", where + " and tipo_cedu = 'Centro educativo diversificado'");
	}

	protected void setListeners() {
		super.setListeners();

		//fpuga: maybe this should be a for
		JButton csaludBT = (JButton) formBody.getComponentByName("csalud.BT");
		csaludBT.setActionCommand("csalud.BT");
		csaludBT.addActionListener(this);

		JButton creunionBT = (JButton) formBody.getComponentByName("creunion.BT");
		creunionBT.setActionCommand("creunion.BT");
		creunionBT.addActionListener(this);

		JButton cent_jardBT = (JButton) formBody.getComponentByName("cent_jard.BT");
		cent_jardBT.setActionCommand("cent_jard.BT");
		cent_jardBT.addActionListener(this);

		JButton cent_escBT = (JButton) formBody.getComponentByName("cent_esc.BT");
		cent_escBT.setActionCommand("cent_esc.BT");
		cent_escBT.addActionListener(this);

		JButton cent_ccydBT = (JButton) formBody.getComponentByName("cent_ccyd.BT");
		cent_ccydBT.setActionCommand("cent_ccyd.BT");
		cent_ccydBT.addActionListener(this);
	}

	protected void removeListeners() {
		super.removeListeners();
		JButton csaludBT = (JButton) formBody.getComponentByName("csalud.BT");
		csaludBT.removeActionListener(this);
		JButton creunionBT = (JButton) formBody.getComponentByName("creunion.BT");
		creunionBT.removeActionListener(this);
		JButton cent_jardBT = (JButton) formBody.getComponentByName("cent_jard.BT");
		cent_jardBT.removeActionListener(this);
		JButton cent_escBT = (JButton) formBody.getComponentByName("cent_esc.BT");
		cent_escBT.removeActionListener(this);
		JButton cent_ccydBT = (JButton) formBody.getComponentByName("cent_ccyd.BT");
		cent_ccydBT.removeActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);

		AbstractForm dialog = null;
		long pos = 0;
		if (e.getActionCommand().equals("csalud.BT")) {
			dialog = new CentroSaludForm(Utils.getFlyrVect(view, "centros_salud"));
			pos = csaludPosition;
		} else if (e.getActionCommand().equals("creunion.BT")) {
			dialog = new CentroSaludForm(Utils.getFlyrVect(view, "centros_reuniones"));
			pos = creunionPosition;
		} else if (e.getActionCommand().equals("cent_jard.BT")) {
			dialog = new CentroSaludForm(Utils.getFlyrVect(view, "centros_educativos"));
			pos = centJardPosition;
		} else if (e.getActionCommand().equals("cent_esc.BT")) {
			dialog = new CentroSaludForm(Utils.getFlyrVect(view, "centros_educativos"));
			pos = centEscPosition;
		} else if (e.getActionCommand().equals("cent_ccyd.BT")) {
			dialog = new CentroSaludForm(Utils.getFlyrVect(view, "centros_educativos"));
			pos = centCCYDPosition;
		}

		if (dialog != null) {
			if (dialog.init()) {
				dialog.setPosition(pos);
				PluginServices.getMDIManager().addWindow(dialog);
			}
		}
	}

	private void setCHBChanged(boolean changed) {
		chbChanged = changed;
	}

	private boolean getCHBChanged() {
		return chbChanged;
	}

}

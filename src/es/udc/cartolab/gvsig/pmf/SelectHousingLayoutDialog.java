package es.udc.cartolab.gvsig.pmf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

import javax.swing.JLabel;

import org.apache.log4j.Logger;

import com.iver.andami.PluginServices;
import com.iver.utiles.swing.JComboBox;

import es.icarto.gvsig.commons.gui.AbstractIWindow;
import es.icarto.gvsig.commons.gui.OkCancelPanel;
import es.icarto.gvsig.commons.gui.WidgetFactory;
import es.udc.cartolab.gvsig.pmf.utils.DAO;

@SuppressWarnings("serial")
public class SelectHousingLayoutDialog extends AbstractIWindow implements
	ActionListener {

    private static Logger logger = Logger
	    .getLogger(SelectHousingLayoutDialog.class);

    private JComboBox cb;

    private String housingCode;

    public SelectHousingLayoutDialog() {
	super();
	setWindowTitle("Escoja una vivienda");
	addHousingCB();
	WidgetFactory.okCancelPanel(this, this, this);
    }

    private void addHousingCB() {
	Collection<String> housingCodes;
	try {
	    housingCodes = DAO.getHousingCodes();
	} catch (SQLException e) {
	    housingCodes = Collections.emptyList();
	    logger.error(e.getStackTrace(), e);
	}

	cb = new JComboBox(housingCodes.toArray());
	add(new JLabel("Código de vivienda: "));
	add(cb);
    }

    public String getHousingCode() {
	return housingCode;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getActionCommand() == OkCancelPanel.OK_ACTION_COMMAND) {
	    if (cb.getSelectedIndex() == -1) {
		return;
	    }
	    housingCode = cb.getSelectedItem().toString();
	}
	PluginServices.getMDIManager().closeWindow(this);
    }
}

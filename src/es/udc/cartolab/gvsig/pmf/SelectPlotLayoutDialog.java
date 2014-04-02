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

import es.udc.cartolab.gvsig.commons.ui.AbstractIWindow;
import es.udc.cartolab.gvsig.commons.ui.AcceptCancelPanel;
import es.udc.cartolab.gvsig.pmf.utils.DAO;

@SuppressWarnings("serial")
public class SelectPlotLayoutDialog extends AbstractIWindow implements
	ActionListener {

    private static Logger logger = Logger
	    .getLogger(SelectPlotLayoutDialog.class);

    private JComboBox cb;

    private String plotCode;

    public SelectPlotLayoutDialog() {
	super();
	setWindowTitle("Escoja una parcela");
	addPlotCB();
	addAcceptCancelPanel(this, this);
    }

    private void addPlotCB() {
	Collection<String> plotCodes;
	try {
	    plotCodes = DAO.getPlotCodes();
	} catch (SQLException e) {
	    plotCodes = Collections.emptyList();
	    logger.error(e.getStackTrace(), e);
	}

	cb = new JComboBox(plotCodes.toArray());
	add(new JLabel("Código de parcela: "));
	add(cb);
    }

    public String getPlotCode() {
	return plotCode;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getActionCommand() == AcceptCancelPanel.OK_ACTION_COMMAND) {
	    if (cb.getSelectedIndex() == -1) {
		return;
	    }
	    plotCode = cb.getSelectedItem().toString();
	}
	PluginServices.getMDIManager().closeWindow(this);
    }
}

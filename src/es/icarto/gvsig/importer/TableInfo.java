package es.icarto.gvsig.importer;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import com.iver.andami.ui.mdiManager.WindowInfo;

import es.icarto.gvsig.commons.gui.AbstractIWindow;
import es.icarto.gvsig.commons.gui.OkCancelPanel;
import es.icarto.gvsig.commons.gui.WidgetFactory;

@SuppressWarnings("serial")
public class TableInfo extends AbstractIWindow implements ActionListener {

    private final OkCancelPanel btPanel;
    private final JTable table;
    private boolean good = false;

    public TableInfo(DefaultTableModel tableModel) {
	super(new MigLayout("fill, insets 10"));
	setWindowTitle("Información procesada");
	setWindowInfoProperties(WindowInfo.MODALDIALOG | WindowInfo.RESIZABLE);
	btPanel = WidgetFactory.okCancelPanel(this, this, this);

	table = new JTable(tableModel);
	// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	table.getTableHeader().setReorderingAllowed(false);
	autoFit();
	table.setPreferredScrollableViewportSize(table.getPreferredSize());
	table.setFillsViewportHeight(true);

	JScrollPane scrollPane = new JScrollPane(table);

	this.add(scrollPane, "push, grow");
    }

    private void autoFit() {
	// TODO. Review:
	// http://stackoverflow.com/a/8478299/930271
	// https://tips4java.wordpress.com/2008/11/10/table-column-adjuster/
	// http://www.java2s.com/Code/Java/Swing-Components/CalculatedColumnTable.htm
	table.repaint();
	int avaliable = table.getColumnModel().getTotalColumnWidth();

	int[] maxLengths = getMaxLengths();
	double needed = 0.0;
	for (int i = 0; i < table.getColumnCount(); i++) {
	    int m = (table.getColumnName(i).length() > maxLengths[i]) ? table
		    .getColumnName(i).length() : maxLengths[i];
		    needed += m;
	}

	for (int i = 0; i < table.getModel().getColumnCount(); i++) {
	    double preferredWidth = avaliable * (maxLengths[i] / needed);

	    preferredWidth = 150;
	    table.getColumnModel().getColumn(i)
		    .setPreferredWidth((int) preferredWidth);
	}
    }

    public int[] getMaxLengths() {
	int[] maxLengths = new int[table.getColumnCount()];
	Arrays.fill(maxLengths, 50);

	for (int i = 0; i < table.getRowCount(); i++) {
	    for (int j = 0; j < table.getColumnCount(); j++) {
		final Object o = table.getValueAt(i, j);
		int l = (o == null) ? 0 : o.toString().length();
		if (l > maxLengths[j]) {
		    maxLengths[j] = l;
		}
	    }
	}
	return maxLengths;
    }

    @Override
    protected JButton getDefaultButton() {
	return btPanel.getOkButton();
    }

    @Override
    protected Component getDefaultFocusComponent() {
	return table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getActionCommand().equals(OkCancelPanel.OK_ACTION_COMMAND)) {
	    good = true;
	} else {
	    good = false;
	}

	closeDialog();
    }

    public boolean isGood() {
	return good;
    }

}

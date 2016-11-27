package es.udc.cartolab.gvsig.pmf.importer;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import net.miginfocom.swing.MigLayout;

import com.iver.cit.gvsig.fmap.core.IGeometry;

import es.icarto.gvsig.commons.gui.AbstractIWindow;
import es.icarto.gvsig.commons.gui.OkCancelPanel;
import es.icarto.gvsig.commons.gui.WidgetFactory;

@SuppressWarnings("serial")
public class ConfirmCroquisDialog extends AbstractIWindow implements
	ActionListener {

    private OkCancelPanel btPanel;
    private boolean okClickedWithoutError;
    private Map<String, IGeometry> points;
    private PlotPanel plotPanel;

    public ConfirmCroquisDialog(final Map<String, IGeometry> points) {
	super(new MigLayout("fill, insets 10"));
	setWindowTitle("Confirme que la parcela es correcta");
	btPanel = WidgetFactory.okCancelPanel(this, this, this);
	this.points = points;

	plotPanel = new PlotPanel(points);

	this.add(plotPanel, "push, grow");
	OrderableJList list = new OrderableJList(points);
	list.addListDataListener(new MyListDataListener());
	this.add(list, "dock east");
    }

    @Override
    protected JButton getDefaultButton() {
	return btPanel.getOkButton();
    }

    @Override
    protected Component getDefaultFocusComponent() {
	return btPanel.getOkButton();
    }

    /**
     * @return true when there is no okClickedWithoutError and the user press ok
     */
    public boolean isGood() {
	return okClickedWithoutError;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	okClickedWithoutError = false;
	if (e.getActionCommand().equals(OkCancelPanel.OK_ACTION_COMMAND)) {
	    okClickedWithoutError = true;
	}
	closeDialog();
    }

    public IGeometry getPoly() {
	return plotPanel.getPoly();
    }

    class MyListDataListener implements ListDataListener {
	public void contentsChanged(ListDataEvent e) {
	    SortedMap<String, IGeometry> pointsReordered = new TreeMap<String, IGeometry>();
	    DefaultListModel model = (DefaultListModel) e.getSource();
	    for (int i = 0; i < model.getSize(); i++) {
		String id = model.getElementAt(i).toString();
		IGeometry geom = points.get(id);
		pointsReordered.put(i + "", geom);
	    }
	    plotPanel.repaintIt(pointsReordered);
	}

	public void intervalAdded(ListDataEvent e) {
	}

	public void intervalRemoved(ListDataEvent e) {
	}
    }

}

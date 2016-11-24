package es.udc.cartolab.gvsig.pmf.importer;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import es.icarto.gvsig.importer.Ruler;

public class MyMouseListener implements MouseListener {

    public static final String CONTEXT_MENU = MyMouseListener.class.getName();
    private final JTable table;
    private final ArrayList<ImporterContextualMenu> items;

    public MyMouseListener(Ruler ruler, JTable table) {
	this.table = table;

	items = new ArrayList<ImporterContextualMenu>();
	items.add(new ComunidadContextualMenu(table, ruler));
	items.add(new ParcelaContextualMenu(table, ruler));
	items.add(new ParcelaFromPointsContextualMenu(table, ruler));
	items.add(new RemoveRowContextualMenu(table));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	if (notValidClick(e)) {
	    return;
	}
	JPopupMenu popup = createPopup(e);
	if (popup.getComponents().length != 0) {
	    popup.show(((Component) e.getSource()), e.getX(), e.getY());
	}
    }

    private boolean notValidClick(MouseEvent e) {
	if (e.getButton() != MouseEvent.BUTTON3) {
	    return true;
	}

	int row = rowClicked(e);
	if (row < 0) {
	    return true;
	}
	return false;
    }

    private int rowClicked(MouseEvent e) {
	Point point = e.getPoint();
	return table.rowAtPoint(point);
    }

    private boolean contains(int[] arr, int v) {
	for (int i : arr) {
	    if (i == v) {
		return true;
	    }
	}
	return false;
    }

    private JPopupMenu createPopup(MouseEvent e) {
	JPopupMenu popup = new JPopupMenu();

	ListSelectionModel model = table.getSelectionModel();
	int rowClicked = rowClicked(e);
	int[] selectedRows = table.getSelectedRows();

	if (selectedRows.length == 0) {
	    model.setSelectionInterval(rowClicked, rowClicked);
	} else if ((selectedRows.length == 1)
		&& (rowClicked != selectedRows[0])) {
	    model.setSelectionInterval(rowClicked, rowClicked);
	} else if (!contains(selectedRows, rowClicked)) {
	    model.setSelectionInterval(rowClicked, rowClicked);
	}

	for (ImporterContextualMenu item : items) {
	    if (item.isVisible()) {
		popup.add(item.getMenuItem());
	    }
	}
	return popup;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}

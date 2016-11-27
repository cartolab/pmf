package es.udc.cartolab.gvsig.pmf.importer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.iver.cit.gvsig.fmap.core.IGeometry;

@SuppressWarnings("serial")
public class OrderableJList extends JPanel implements ListSelectionListener {
    private JList list;

    private DefaultListModel listModel;

    private static final String upString = "Subir";
    private static final String downString = "Bajar";

    private JButton upButton;
    private JButton downButton;

    public OrderableJList(Map<String, IGeometry> points) {
	super(new BorderLayout());
	// Create and populate the list model.
	listModel = new DefaultListModel();
	for (String s : points.keySet()) {
	    listModel.addElement(s);
	}

	// Create the list and put it in a scroll pane.
	list = new JList(listModel);
	list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	list.setSelectedIndex(0);
	list.addListSelectionListener(this);
	JScrollPane listScrollPane = new JScrollPane(list);

	upButton = new JButton(upString);
	upButton.setActionCommand(upString);
	upButton.addActionListener(new UpDownListener());

	downButton = new JButton(downString);
	downButton.setActionCommand(downString);
	downButton.addActionListener(new UpDownListener());

	JPanel upDownPanel = new JPanel(new GridLayout(2, 1));
	upDownPanel.add(upButton);
	upDownPanel.add(downButton);

	// Create a control panel, using the default FlowLayout.
	JPanel buttonPane = new JPanel();

	buttonPane.add(upDownPanel);

	add(listScrollPane, BorderLayout.CENTER);
	add(buttonPane, BorderLayout.EAST);
    }

    // Listen for clicks on the up and down arrow buttons.
    class UpDownListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    // This method can be called only when
	    // there's a valid selection,
	    // so go ahead and move the list item.
	    int moveMe = list.getSelectedIndex();

	    if (e.getActionCommand().equals(upString)) {
		// UP ARROW BUTTON
		if (moveMe != 0) {
		    // not already at top
		    swap(moveMe, moveMe - 1);
		    list.setSelectedIndex(moveMe - 1);
		    list.ensureIndexIsVisible(moveMe - 1);
		}
	    } else {
		// DOWN ARROW BUTTON
		if (moveMe != listModel.getSize() - 1) {
		    // not already at bottom
		    swap(moveMe, moveMe + 1);
		    list.setSelectedIndex(moveMe + 1);
		    list.ensureIndexIsVisible(moveMe + 1);
		}
	    }
	}
    }

    // Swap two elements in the list.
    private void swap(int a, int b) {
	Object aObject = listModel.getElementAt(a);
	Object bObject = listModel.getElementAt(b);
	listModel.set(a, bObject);
	listModel.set(b, aObject);
    }

    // Listener method for list selection changes.
    public void valueChanged(ListSelectionEvent e) {
	if (e.getValueIsAdjusting() == false) {

	    if (list.getSelectedIndex() == -1) {
		// No selection: disable up, and down buttons.
		upButton.setEnabled(false);
		downButton.setEnabled(false);
	    } else if (list.getSelectedIndices().length > 1) {
		// Multiple selection: disable up and down buttons.
		upButton.setEnabled(false);
		downButton.setEnabled(false);

	    } else {
		// Single selection: permit all operations.
		upButton.setEnabled(true);
		downButton.setEnabled(true);
	    }
	}
    }

    public void addListDataListener(ListDataListener l) {
	listModel.addListDataListener(l);
    }

}
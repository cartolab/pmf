package es.icarto.gvsig.importer;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;

import es.icarto.gvsig.commons.gui.AbstractIWindow;
import es.icarto.gvsig.commons.gui.OkCancelPanel;
import es.icarto.gvsig.commons.gui.SelectFileWidget;
import es.icarto.gvsig.commons.gui.WidgetFactory;

@SuppressWarnings("serial")
public class FileToImport extends AbstractIWindow implements ActionListener {

    private SelectFileWidget selectFile;
    private File file;

    public FileToImport() {
	selectFile = new SelectFileWidget(this, "Seleccione hoja de cálculo",
		null, false);
	selectFile.setFilter("Fichero excel", "xlsx");
	WidgetFactory.okCancelPanel(this, this, this);
    }

    @Override
    protected JButton getDefaultButton() {
	return null;
    }

    @Override
    protected Component getDefaultFocusComponent() {
	return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getActionCommand().equals(OkCancelPanel.OK_ACTION_COMMAND)) {
	    if (selectFile.isValidAndExist()) {
		file = selectFile.getFile();
	    }
	}
	closeDialog();
    }

    public File getFile() {
	return file;
    }

}

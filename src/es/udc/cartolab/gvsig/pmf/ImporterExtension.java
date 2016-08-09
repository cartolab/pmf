package es.udc.cartolab.gvsig.pmf;

import java.util.Arrays;
import java.util.List;

import es.icarto.gvsig.commons.AbstractExtension;
import es.icarto.gvsig.importer.FileToImport;
import es.icarto.gvsig.importer.Header;
import es.icarto.gvsig.importer.ImportManager;
import es.icarto.gvsig.importer.Output;
import es.icarto.gvsig.importer.Ruler;
import es.icarto.gvsig.importer.reader.DBF;
import es.icarto.gvsig.importer.reader.Reader;
import es.icarto.gvsig.importer.reader.XLS;
import es.udc.cartolab.gvsig.pmf.importer.GPX;
import es.udc.cartolab.gvsig.pmf.importer.PMFHeader;
import es.udc.cartolab.gvsig.pmf.importer.PMFOutput;
import es.udc.cartolab.gvsig.pmf.importer.PMFRuler;
import es.udc.cartolab.gvsig.users.utils.DBSession;

// Autodetectar cuando son UTM y cuando GEO
// Mejorar como se muestran errores y warnings
// Separar errores de warnings
public class ImporterExtension extends AbstractExtension {

    private FileToImport dialog;

    @Override
    public void initialize() {
	super.initialize();
	XLS xls = new XLS();
	xls.setHeaderLine(XLS.FIRST_NOT_EMPTY);
	List<Reader> readers = Arrays.asList(new DBF(), new GPX(), xls);
	dialog = new FileToImport(null);
	dialog.setReaders(readers);
    }

    @Override
    public void execute(String actionCommand) {

	dialog.openDialog();

	Reader reader = dialog.getInitializedReader();
	if (reader == null) {
	    return;
	}
	Header header = new PMFHeader().getHeader();
	Output output = new PMFOutput();
	Ruler ruler = new PMFRuler();

	ImportManager importManager = new ImportManager(reader, header, output,
		ruler);

	importManager.readHeader();
	importManager.processFile();
    }

    @Override
    public boolean isEnabled() {
	return DBSession.isActive() && (getView() != null);
    }

}

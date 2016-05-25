package es.udc.cartolab.gvsig.pmf;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import es.icarto.gvsig.commons.AbstractExtension;
import es.icarto.gvsig.importer.DBF;
import es.icarto.gvsig.importer.FileToImport;
import es.icarto.gvsig.importer.Header;
import es.icarto.gvsig.importer.ImportManager;
import es.icarto.gvsig.importer.Output;
import es.icarto.gvsig.importer.Reader;
import es.icarto.gvsig.importer.XLS;
import es.udc.cartolab.gvsig.pmf.importer.PMFHeader;
import es.udc.cartolab.gvsig.pmf.importer.PMFOutput;
import es.udc.cartolab.gvsig.users.utils.DBSession;

// Autodetectar cuando son UTM y cuando GEO
// Mejorar como se muestran errores y warnings
// Comparar código y ST_Contains con aldeas_pmf y distancia a caserios_comunidades_pmf
// Separar errores de warnings
public class ImporterExtension extends AbstractExtension {

    private String initFile = null;

    @Override
    public void execute(String actionCommand) {

	XLS xls = new XLS();
	xls.setHeaderLine(XLS.FIRST_NOT_EMPTY);
	List<Reader> readers = Arrays.asList(new DBF(), xls);

	FileToImport dialog = new FileToImport(initFile);
	dialog.setReaders(readers);

	dialog.openDialog();
	File file = dialog.getFile();
	if ((file == null) || !file.isFile()) {
	    return;
	}
	initFile = file.getAbsolutePath();

	Reader reader = dialog.getInitializedReader();
	Header header = new PMFHeader().getHeader();
	Output output = new PMFOutput();

	ImportManager importManager = new ImportManager(reader, header, output);

	importManager.readHeader();
	importManager.processFile();
    }

    @Override
    public boolean isEnabled() {
	return DBSession.isActive();
    }

}

package es.udc.cartolab.gvsig.pmf;

import java.io.File;

import es.icarto.gvsig.commons.AbstractExtension;
import es.icarto.gvsig.importer.DBF;
import es.icarto.gvsig.importer.FileToImport;
import es.icarto.gvsig.importer.Header;
import es.icarto.gvsig.importer.ImportManager;
import es.icarto.gvsig.importer.Output;
import es.icarto.gvsig.importer.Reader;
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

	Reader reader = new DBF();

	FileToImport dialog = new FileToImport(initFile);
	dialog.addChoosableFileFilter(reader.getFileFilter());
	dialog.openDialog();
	File file = dialog.getFile();
	if ((file == null) || !file.isFile()) {
	    return;
	}
	initFile = file.getAbsolutePath();

	reader.initReader(file);
	// XLS reader = new XLS(file);
	// reader.setHeaderLine(XLS.FIRST_NOT_EMPTY);

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

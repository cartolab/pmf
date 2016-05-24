package es.udc.cartolab.gvsig.pmf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import es.icarto.gvsig.commons.AbstractExtension;
import es.icarto.gvsig.importer.FileToImport;
import es.icarto.gvsig.importer.Header;
import es.icarto.gvsig.importer.HeaderField;
import es.icarto.gvsig.importer.ImportManager;
import es.icarto.gvsig.importer.XLS;
import es.udc.cartolab.gvsig.users.utils.DBSession;

// Autodetectar cuando son UTM y cuando GEO
// Mejorar como se muestran errores y warnings
// Comparar código y ST_Contains con aldeas_pmf y distancia a caserios_comunidades_pmf
// Separar errores de warnings
public class ImporterExtension extends AbstractExtension {

    private static final Logger logger = Logger
	    .getLogger(ImporterExtension.class);

    @Override
    public void execute(String actionCommand) {

	FileToImport fileToImport = new FileToImport();
	fileToImport.openDialog();
	File file = fileToImport.getFile();
	if ((file == null) || !file.isFile()) {
	    return;
	}

	ImportManager importManager = new ImportManager();
	try {
	    XLS xls = new XLS(file);
	    xls.setHeaderLine(XLS.FIRST_NOT_EMPTY);

	    importManager.setReader(xls);
	    Header header = new Header();
	    header.setFromRules(createHeader());
	    importManager.setHeader(header);

	    importManager.readHeader();

	    importManager.processFile();
	} catch (InvalidFormatException e) {
	    logger.error(e.getStackTrace(), e);
	} catch (IOException e) {
	    logger.error(e.getStackTrace(), e);
	}

    }

    private List<HeaderField> createHeader() {
	HeaderField h1 = new HeaderField("id");
	h1.setNotDefinedField(false);
	Set<String> s1 = new HashSet<String>(3);
	s1.add("name");
	s1.add("id");
	s1.add("nombre");
	h1.setRuleNames(s1);

	HeaderField h2 = new HeaderField("x");
	h2.setNotDefinedField(false);
	Set<String> s2 = new HashSet<String>(2);
	s2.add("lat");
	s2.add("x");
	h2.setRuleNames(s2);

	HeaderField h3 = new HeaderField("y");
	h3.setNotDefinedField(false);
	Set<String> s3 = new HashSet<String>(4);
	s3.add("lng");
	s3.add("y");
	s3.add("lon");
	s3.add("long");
	h3.setRuleNames(s3);

	List<HeaderField> l = new ArrayList<HeaderField>(3);
	Collections.addAll(l, h1, h2, h3);
	return l;
    }

    @Override
    public boolean isEnabled() {
	return DBSession.isActive();
    }

}

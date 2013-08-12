package es.udc.cartolab.gvsig.pmf.reports;

import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;

import org.gvsig.gui.beans.swing.JFileChooser;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.hardcode.gdbms.engine.values.Value;
import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.fmap.MapControl;
import com.iver.cit.gvsig.fmap.layers.FLayers;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;
import com.iver.cit.gvsig.project.documents.view.gui.BaseView;
import com.iver.utiles.GenericFileFilter;

public abstract class ReportsGenerator {

    private static FLayers getDataSources() throws ReadDriverException {

	BaseView view = (BaseView) PluginServices.getMDIManager()
		.getActiveWindow();
	MapControl mapControl = view.getMapControl();
	FLayers flayers = mapControl.getMapContext().getLayers();

	return flayers;

    }

    private static ArrayList<HashMap<String, Object>> retrieveRecords(
	    SelectableDataSource data) throws ReadDriverException {

	ArrayList<HashMap<String, Object>> records = new ArrayList<HashMap<String, Object>>();
	HashMap<String, Object> record;
	int i, j;

	Value row[];

	for (i = 0; i < data.getRowCount(); i++) {

	    row = data.getRow(i);

	    record = new HashMap<String, Object>();

	    for (j = 0; j < data.getFieldsDescription().length; j++) {
		record.put(data.getFieldsDescription()[j].getFieldName(),
			row[j].toString());
	    }

	    records.add(record);

	}

	return records;

    }

    private static void groupingsSort(
	    ArrayList<HashMap<String, Object>> records,
	    HashMap<String, Boolean> orderings) {
	Set<String> keys = orderings.keySet();
	Iterator<String> iterator = keys.iterator();
	String key;

	while (iterator.hasNext()) {
	    key = iterator.next();
	    Collections.sort(records,
		    new RecordsComparator(key, orderings.get(key)));
	}
    }

    public static boolean generateReport(String layerName,
	    HashMap<String, Boolean> orderings, String reportTemplate) {

	ArrayList<HashMap<String, Object>> records = new ArrayList<HashMap<String, Object>>();

	SelectableDataSource data = null;
	FLayers layers;
	try {
	    layers = getDataSources();
	    int i;
	    for (i = 0; i < layers.getLayersCount(); i++) {
		System.out.println(layers.getLayer(i).getName());
		if (layers.getLayer(i).getName().equals(layerName)) {
		    data = ((FLyrVect) layers.getLayer(i)).getRecordset();
		}
	    }
	    // data = getDataSource(layerName);
	} catch (ReadDriverException e1) {
	    e1.printStackTrace();
	    return false;
	}

	if (data != null) {

	    try {
		records = retrieveRecords(data);
	    } catch (ReadDriverException e1) {
		e1.printStackTrace();
		return false;
	    }

	    groupingsSort(records, orderings);

	    Map<String, String> parameters = new HashMap<String, String>();
	    parameters.put("ReportTitle", "Address Report");

	    try {
		JasperReport report = JasperCompileManager
			.compileReport(reportTemplate);

		JFileChooser jfc = new JFileChooser("PROJECT_FILE_CHOOSER_ID",
			"/home/jlopez/");

		jfc.setDialogTitle("Test");
		jfc.addChoosableFileFilter(new GenericFileFilter("doc",
			"Documento de Microsoft Word (*.doc)"));
		jfc.addChoosableFileFilter(new GenericFileFilter("odt",
			"Documento de Open Office (*.odt)"));
		jfc.addChoosableFileFilter(new GenericFileFilter("pdf",
			"Documento PDF (*.pdf)"));
		jfc.addChoosableFileFilter(new GenericFileFilter("html",
			"Documento HTML (*.html)"));

		if (jfc.showSaveDialog((Component) PluginServices
			.getMainFrame()) == JFileChooser.APPROVE_OPTION) {
		    File file = jfc.getSelectedFile();
		    if (jfc.getFileFilter().getDescription()
			    .endsWith(" (*.odt)")) {
			if (!(file.getPath().toLowerCase().endsWith(".odt"))) {
			    file = new File(file.getPath() + ".odt");
			}

			String filePath = file.getAbsolutePath();

			JROdtExporter exporter = new JROdtExporter();

			JasperPrint print = JasperFillManager.fillReport(
				report, parameters, new CustomDataSource(
					records));
			exporter.setParameter(
				JRExporterParameter.OUTPUT_FILE_NAME, filePath);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT,
				print);
			exporter.exportReport();
		    }

		    if (jfc.getFileFilter().getDescription()
			    .endsWith(" (*.html)")) {
			if (!(file.getPath().toLowerCase().endsWith(".html"))) {
			    file = new File(file.getPath() + ".html");
			}

			String filePath = file.getAbsolutePath();

			JRHtmlExporter exporter = new JRHtmlExporter();

			JasperPrint print = JasperFillManager.fillReport(
				report, parameters, new CustomDataSource(
					records));
			exporter.setParameter(
				JRExporterParameter.OUTPUT_FILE_NAME, filePath);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT,
				print);
			exporter.exportReport();
		    }

		    if (jfc.getFileFilter().getDescription()
			    .endsWith(" (*.pdf)")) {
			if (!(file.getPath().toLowerCase().endsWith(".pdf"))) {
			    file = new File(file.getPath() + ".pdf");
			}

			String filePath = file.getAbsolutePath();

			JRPdfExporter exporter = new JRPdfExporter();

			JasperPrint print = JasperFillManager.fillReport(
				report, parameters, new CustomDataSource(
					records));
			exporter.setParameter(
				JRExporterParameter.OUTPUT_FILE_NAME, filePath);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT,
				print);
			exporter.exportReport();
		    }

		    if (jfc.getFileFilter().getDescription()
			    .endsWith(" (*.doc)")) {
			File odtFile;
			if (!(file.getPath().toLowerCase().endsWith(".doc"))) {
			    odtFile = new File(file.getPath() + ".tmp.odt");
			    file = new File(file.getPath() + ".doc");
			} else {
			    odtFile = new File(file.getPath().replaceAll(
				    ".doc", ".tmp.odt"));
			}

			Runtime.getRuntime().exec("/home/jlopez/script");

			Thread.sleep(1500);

			JROdtExporter exporter = new JROdtExporter();

			JasperPrint print = JasperFillManager.fillReport(
				report, parameters, new CustomDataSource(
					records));
			exporter.setParameter(
				JRExporterParameter.OUTPUT_FILE_NAME,
				odtFile.getAbsolutePath());
			exporter.setParameter(JRExporterParameter.JASPER_PRINT,
				print);
			exporter.exportReport();

			OpenOfficeConnection connection = new SocketOpenOfficeConnection(
				8100);
			connection.connect();

			// convert
			DocumentConverter converter = new OpenOfficeDocumentConverter(
				connection);
			converter.convert(odtFile, file);

			// close the connection
			connection.disconnect();

			Runtime.getRuntime().exec("pkill soffice.bin");
			Runtime.getRuntime().exec(
				"rm " + odtFile.getAbsolutePath());
		    }

		}

	    } catch (Exception e) {
		return false;
	    }

	    return true;

	}

	return false;

    }

}

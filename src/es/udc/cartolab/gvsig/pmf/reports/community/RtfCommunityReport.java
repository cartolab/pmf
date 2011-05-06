package es.udc.cartolab.gvsig.pmf.reports.community;

/*
 * Copyright (c) 2010. Cartolab (Universidade da Coruña)
 * 
 * This file is part of gvSIG Fonsagua.
 * 
 * gvSIG Fonsagua is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * gvSIG Fonsagua is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with gvSIG Fonsagua.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.exceptions.expansionfile.ExpansionFileReadException;
import com.iver.cit.gvsig.fmap.drivers.FieldDescription;
import com.iver.cit.gvsig.fmap.edition.IEditableSource;
import com.iver.cit.gvsig.fmap.edition.IRowEdited;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;
import com.iver.cit.gvsig.project.documents.view.gui.BaseView;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.document.RtfDocumentSettings;
import com.lowagie.text.rtf.style.RtfFont;
import com.lowagie.text.rtf.table.RtfCell;

import es.udc.cartolab.gvsig.navtableforms.Utils;

public class RtfCommunityReport {

    private FLyrVect layer;
    private BaseView view = null;
    private int nRow;
    private String fileName;
    private boolean darkColor = true;
    private SelectableDataSource source;

    private final Document document;

    // FONT STYLES
    private final RtfFont titleFont = new RtfFont("Century Gothic", 24,
	    RtfFont.STYLE_BOLD);
    private final RtfFont subtitleFont = new RtfFont("Century Gothic", 24,
	    RtfFont.STYLE_NONE);
    private final RtfFont normalBoldTextFont = new RtfFont("Century Gothic",
	    10, RtfFont.STYLE_BOLD);
    private final RtfFont normalTextFont = new RtfFont("Century Gothic", 10,
	    RtfFont.STYLE_NONE);
    private final RtfFont normalItalicTextFont = new RtfFont("Century Gothic",
	    10, RtfFont.STYLE_ITALIC);
    private final RtfFont tableTitleTextFont = new RtfFont("Century Gothic", 9,
	    RtfFont.STYLE_NONE);
    private final RtfFont sectionFont = new RtfFont("Century Gothic", 14,
	    RtfFont.STYLE_BOLD);
    private final RtfFont subsectionFont = new RtfFont("Century Gothic", 11,
	    RtfFont.STYLE_BOLD);

    public RtfCommunityReport(int nRow, SelectableDataSource source,
	    String fileName, BaseView view) {

	this.view = view;
	this.source = source;
	this.nRow = nRow;
	this.fileName = fileName;
	document = new Document();
	try {

	    startDocument();
	    createSection1();
	    createSection2();
	    createSection3();
	    createSection4();

	    // close document
	    document.close();

	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (DocumentException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    private void startDocument() throws FileNotFoundException,
	    DocumentException, ReadDriverException {

	RtfWriter2 writer;
	// getting document instance and opening to write
	writer = RtfWriter2.getInstance(document,
		new FileOutputStream(fileName));
	document.open();
	RtfDocumentSettings settings = writer.getDocumentSettings();
	settings.setOutputTableRowDefinitionAfter(true);

	HeaderFooter footer = new HeaderFooter(new Phrase("", normalTextFont),
		true);
	footer.setAlignment(HeaderFooter.ALIGN_RIGHT);
	document.setFooter(footer);

	// Title
	Paragraph reportTitle = new Paragraph(
		"\n\n\n\n\n\nINFORME DE LA COMUNIDAD", titleFont);
	reportTitle.setAlignment(Paragraph.ALIGN_CENTER);
	document.add(reportTitle);

	// Subtitle
	Paragraph reportSubtitle = new Paragraph("("
		+ source.getFieldValue(nRow,
			source.getFieldIndexByName("nombre")).toString() + ")",
		subtitleFont);
	reportSubtitle.setAlignment(Paragraph.ALIGN_CENTER);
	document.add(reportSubtitle);

	Paragraph aldea = new Paragraph();
	aldea.add(new Chunk("\n\n\n\n\nCódigo de la comunidad:",
		normalBoldTextFont));
	aldea.add(new Chunk(" "
		+ source.getFieldValue(nRow,
			source.getFieldIndexByName("cod_com")).toString(),
		normalTextFont));
	aldea.setAlignment(Paragraph.ALIGN_RIGHT);
	document.add(aldea);

	document.newPage();
    }

    private void createSection1() throws DocumentException, ReadDriverException {

	// Section 1
	Paragraph sectionTitle = new Paragraph(
		"1. DATOS GENERALES DE LA COMUNIDAD\n", sectionFont);
	document.add(sectionTitle);
	Paragraph sectionBody = new Paragraph();
	List list = new List();
	list.setIndentationLeft(20);
	list.setSymbolIndent(15);
	list.setListSymbol(new Chunk("\u2022", normalBoldTextFont));
	Phrase phrase = new Phrase();
	phrase.add(new Chunk("Comunidad: ", normalBoldTextFont));
	phrase.add(new Chunk(source.getFieldValue(nRow,
		source.getFieldIndexByName("nombre")).toString(),
		normalTextFont));
	list.add(new ListItem(phrase));
	phrase = new Phrase();
	phrase.add(new Chunk("Código de la comunidad: ", normalBoldTextFont));
	phrase.add(new Chunk(source.getFieldValue(nRow,
		source.getFieldIndexByName("cod_com")).toString(),
		normalTextFont));
	list.add(new ListItem(phrase));
	sectionBody.add(list);
	document.add(sectionBody);

	// Section 1.1
	sectionTitle = new Paragraph("1.1. Ubicación\n", subsectionFont);
	document.add(sectionTitle);
	sectionBody = new Paragraph();
	list = new List();
	list.setIndentationLeft(20);
	list.setSymbolIndent(15);
	list.setListSymbol(new Chunk("\u2022", normalBoldTextFont));
	phrase = new Phrase();
	phrase.add(new Chunk("Departamento: ", normalBoldTextFont));
	phrase.add(new Chunk(source.getFieldValue(nRow,
		source.getFieldIndexByName("departa")).toString(),
		normalTextFont));
	list.add(new ListItem(phrase));
	phrase = new Phrase();
	phrase.add(new Chunk("Municipio: ", normalBoldTextFont));
	phrase.add(new Chunk(source.getFieldValue(nRow,
		source.getFieldIndexByName("municip")).toString(),
		normalTextFont));
	list.add(new ListItem(phrase));
	sectionBody.add(list);
	document.add(sectionBody);

	// Section 1.2
	sectionTitle = new Paragraph("1.2. Población\n", subsectionFont);
	document.add(sectionTitle);
	sectionBody = new Paragraph();
	list = new List();
	list.setIndentationLeft(20);
	list.setSymbolIndent(15);
	list.setListSymbol(new Chunk("\u2022", normalBoldTextFont));
	phrase = new Phrase();
	phrase.add(new Chunk("Número de habitantes: ", normalBoldTextFont));
	phrase.add(new Chunk(source.getFieldValue(nRow,
		source.getFieldIndexByName("n_habit")).toString(),
		normalTextFont));
	list.add(new ListItem(phrase));
	phrase = new Phrase();
	phrase.add(new Chunk("Número de familias: ", normalBoldTextFont));
	phrase
		.add(new Chunk(source.getFieldValue(nRow,
			source.getFieldIndexByName("n_fam")).toString(),
			normalTextFont));
	list.add(new ListItem(phrase));
	sectionBody.add(list);
	document.add(sectionBody);

    }

    private void createSection2() throws DocumentException, ReadDriverException {

	// Section 2
	Paragraph sectionTitle = new Paragraph(
		"\n\n2. ASPECTOS ORGANIZATIVOS:\n", sectionFont);
	document.add(sectionTitle);

	// Section 2.1
	sectionTitle = new Paragraph("2.1. Centro comunal de reuniones:\n",
		subsectionFont);
	document.add(sectionTitle);
	Paragraph sectionBody = new Paragraph();
	List list = new List();
	list.setIndentationLeft(20);
	list.setSymbolIndent(15);
	list.setListSymbol(new Chunk("\u2022", normalBoldTextFont));
	Phrase phrase;
	SelectableDataSource shpSource;
	if (source.getFieldValue(nRow, source.getFieldIndexByName("creunion"))
		.toString().equals("true")) {
	    layer = Utils.getFlyrVect(view, "centros_reuniones");
	    shpSource = this.layer.getSource().getRecordset();
	    for (int i = 0; i < shpSource.getRowCount(); i++) {
		if (shpSource.getFieldValue(i,
			shpSource.getFieldIndexByName("cod_com")).toString()
			.equals(
				source.getFieldValue(nRow,
					source.getFieldIndexByName("cod_com"))
					.toString())) {
		    phrase = new Phrase();
		    phrase.add(new Chunk("Nombre del centro de reuniones: ",
			    normalBoldTextFont));
		    phrase.add(new Chunk(shpSource.getFieldValue(i,
			    shpSource.getFieldIndexByName("nom_creu"))
			    .toString(), normalTextFont));
		    list.add(new ListItem(phrase));
		    phrase = new Phrase();
		    phrase.add(new Chunk("Dirección: ", normalBoldTextFont));
		    phrase.add(new Chunk(shpSource.getFieldValue(i,
			    shpSource.getFieldIndexByName("direccion"))
			    .toString(), normalTextFont));
		    list.add(new ListItem(phrase));
		    phrase = new Phrase();
		    phrase.add(new Chunk("Responsable: ", normalBoldTextFont));
		    phrase.add(new Chunk(shpSource.getFieldValue(i,
			    shpSource.getFieldIndexByName("responsa"))
			    .toString(), normalTextFont));
		    list.add(new ListItem(phrase));
		    sectionBody.add(list);
		}
	    }
	} else {
	    phrase = new Phrase();
	    phrase.add(new Chunk("\tNo presenta Centro de Reuniones.\n\n",
		    normalItalicTextFont));
	    sectionBody.add(phrase);
	}
	document.add(sectionBody);

	// Section 2.2
	sectionTitle = new Paragraph("2.2. Organizaciones de base:\n",
		subsectionFont);
	document.add(sectionTitle);

	// Base organizations table
	Table table = new Table(3);
	darkColor = true;
	// we add a cell with colspan 3
	RtfCell cell = new RtfCell(new Phrase("NOMBRE DE LA ORGANIZACIÓN",
		tableTitleTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("FUNCIONES DE LA ORGANIZACIÓN",
		tableTitleTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(
		new Phrase("PERSONA RESPONSABLE", tableTitleTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	String dbfName = "organizacion_base";
	IWindow[] windows = PluginServices.getMDIManager().getAllWindows();
	IEditableSource dbfSource = null;
	boolean found = false, hasRows = false;
	for (int i = 0; i < windows.length; i++) {
	    if (windows[i] instanceof com.iver.cit.gvsig.project.documents.table.gui.Table) {
		String name = ((com.iver.cit.gvsig.project.documents.table.gui.Table) windows[i])
			.getModel().getName();
		if (name.endsWith(".dbf")) {
		    name = name.substring(0, name.lastIndexOf(".dbf"));
		    if (name.equals(dbfName)) {
			dbfSource = ((com.iver.cit.gvsig.project.documents.table.gui.Table) windows[i])
				.getModel().getModelo();
			found = true;
			break;
		    }
		}
	    }
	}
	if (found) {
	    FieldDescription[] descriptions = dbfSource.getFieldsDescription();
	    HashMap<String, Integer> indexes = new HashMap<String, Integer>();
	    for (int i = 0; i < descriptions.length; i++) {
		indexes.put(descriptions[i].getFieldName(), i);
	    }
	    for (int i = 0; i < dbfSource.getRowCount(); i++) {
		IRowEdited row = dbfSource.getRow(i);
		if (row.getAttribute(indexes.get("cod_com")).toString().equals(
			source.getFieldValue(nRow,
				source.getFieldIndexByName("cod_com"))
				.toString())) {
		    darkColor = !darkColor;
		    hasRows = true;
		    cell = new RtfCell(new Phrase(row.getAttribute(
			    indexes.get("nom_org")).toString(),
			    normalBoldTextFont));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    setCellColor(cell);
		    table.addCell(cell);
		    cell = new RtfCell(new Phrase(row.getAttribute(
			    indexes.get("fun_org")).toString(),
			    normalBoldTextFont));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    setCellColor(cell);
		    table.addCell(cell);
		    cell = new RtfCell(new Phrase(row.getAttribute(
			    indexes.get("per_res")).toString(),
			    normalBoldTextFont));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    setCellColor(cell);
		    table.addCell(cell);
		}

	    }
	    if (!hasRows) {
		darkColor = !darkColor;
		cell = new RtfCell(new Phrase("", normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		setCellColor(cell);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("", normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		setCellColor(cell);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("", normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		setCellColor(cell);
		table.addCell(cell);
	    }
	} else {
	    darkColor = !darkColor;
	    cell = new RtfCell(new Phrase("(organizacion_base.nom_org)",
		    normalBoldTextFont));
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    setCellColor(cell);
	    table.addCell(cell);
	    cell = new RtfCell(new Phrase("(organizacion_base.fun_org)",
		    normalBoldTextFont));
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    setCellColor(cell);
	    table.addCell(cell);
	    cell = new RtfCell(new Phrase("(organizacion_base.per_res)",
		    normalBoldTextFont));
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    setCellColor(cell);
	    table.addCell(cell);
	}
	document.add(table);

	// Section 2.3
	sectionTitle = new Paragraph("\n\n2.3. Presencia institucional:\n",
		subsectionFont);
	document.add(sectionTitle);

	// Inst. presence table
	table = new Table(3);
	darkColor = true;
	cell = new RtfCell(new Phrase("NOMBRE DE LA ORGANIZACIÓN",
		tableTitleTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("FUNCIONES DE LA ORGANIZACIÓN",
		tableTitleTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(
		new Phrase("PERSONA RESPONSABLE", tableTitleTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	dbfName = "presencia_institucional";
	windows = PluginServices.getMDIManager().getAllWindows();
	dbfSource = null;
	found = false;
	for (int i = 0; i < windows.length; i++) {
	    if (windows[i] instanceof com.iver.cit.gvsig.project.documents.table.gui.Table) {
		String name = ((com.iver.cit.gvsig.project.documents.table.gui.Table) windows[i])
			.getModel().getName();
		if (name.endsWith(".dbf")) {
		    name = name.substring(0, name.lastIndexOf(".dbf"));
		    if (name.equals(dbfName)) {
			dbfSource = ((com.iver.cit.gvsig.project.documents.table.gui.Table) windows[i])
				.getModel().getModelo();
			found = true;
			break;
		    }
		}
	    }
	}
	if (found) {
	    FieldDescription[] descriptions = dbfSource.getFieldsDescription();
	    HashMap<String, Integer> indexes = new HashMap<String, Integer>();
	    hasRows = false;
	    for (int i = 0; i < descriptions.length; i++) {
		indexes.put(descriptions[i].getFieldName(), i);
	    }
	    for (int i = 0; i < dbfSource.getRowCount(); i++) {
		IRowEdited row = dbfSource.getRow(i);
		if (row.getAttribute(indexes.get("cod_com")).toString().equals(
			source.getFieldValue(nRow,
				source.getFieldIndexByName("cod_com"))
				.toString())) {
		    darkColor = !darkColor;
		    hasRows = true;
		    cell = new RtfCell(new Phrase(row.getAttribute(
			    indexes.get("nom_org")).toString(),
			    normalBoldTextFont));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    setCellColor(cell);
		    table.addCell(cell);
		    cell = new RtfCell(new Phrase(row.getAttribute(
			    indexes.get("fun_org")).toString(),
			    normalBoldTextFont));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    setCellColor(cell);
		    table.addCell(cell);
		    cell = new RtfCell(new Phrase(row.getAttribute(
			    indexes.get("per_res")).toString(),
			    normalBoldTextFont));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    setCellColor(cell);
		    table.addCell(cell);
		}

	    }
	    if (!hasRows) {
		darkColor = !darkColor;
		cell = new RtfCell(new Phrase("", normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		setCellColor(cell);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("", normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		setCellColor(cell);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("", normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		setCellColor(cell);
		table.addCell(cell);
	    }
	} else {
	    darkColor = !darkColor;
	    cell = new RtfCell(new Phrase("", normalTextFont));
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    setCellColor(cell);
	    table.addCell(cell);
	    cell = new RtfCell(new Phrase("", normalTextFont));
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    setCellColor(cell);
	    table.addCell(cell);
	    cell = new RtfCell(new Phrase("", normalTextFont));
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    setCellColor(cell);
	    table.addCell(cell);
	}
	document.add(table);
    }

    private void createSection3() throws DocumentException, ReadDriverException {

	// Section 3
	Paragraph sectionTitle = new Paragraph("\n\n\n3. SERVICIOS BÁSICOS:\n",
		sectionFont);
	document.add(sectionTitle);

	// Section 3.1
	sectionTitle = new Paragraph("3.1. Servicios básicos:\n",
		subsectionFont);
	document.add(sectionTitle);
	Paragraph sectionBody = new Paragraph();
	List list = new List();
	list.setIndentationLeft(20);
	list.setSymbolIndent(15);
	list.setListSymbol(new Chunk("\u2022", normalBoldTextFont));
	Phrase phrase = new Phrase();
	phrase.add(new Chunk("Electricidad: ", normalBoldTextFont));
	if (source.getFieldValue(nRow, source.getFieldIndexByName("luz_elec"))
		.toString().equals("true")) {
	    phrase.add(new Chunk("Sí", normalItalicTextFont));
	} else {
	    phrase.add(new Chunk("No", normalItalicTextFont));
	}
	list.add(new ListItem(phrase));
	phrase = new Phrase();
	phrase.add(new Chunk("Agua potable: ", normalBoldTextFont));
	if (source.getFieldValue(nRow, source.getFieldIndexByName("agua_pot"))
		.toString().equals("true")) {
	    phrase.add(new Chunk("Sí", normalItalicTextFont));
	} else {
	    phrase.add(new Chunk("No", normalItalicTextFont));
	}
	list.add(new ListItem(phrase));
	phrase = new Phrase();
	phrase.add(new Chunk("Alcantarillado: ", normalBoldTextFont));
	if (source.getFieldValue(nRow, source.getFieldIndexByName("alcantar"))
		.toString().equals("true")) {
	    phrase.add(new Chunk("Sí", normalItalicTextFont));
	} else {
	    phrase.add(new Chunk("No", normalItalicTextFont));
	}
	list.add(new ListItem(phrase));
	phrase = new Phrase();
	phrase.add(new Chunk("Teléfono fijo: ", normalBoldTextFont));
	if (source.getFieldValue(nRow, source.getFieldIndexByName("tfn_fijo"))
		.toString().equals("true")) {
	    phrase.add(new Chunk("Sí", normalItalicTextFont));
	} else {
	    phrase.add(new Chunk("No", normalItalicTextFont));
	}
	list.add(new ListItem(phrase));
	sectionBody.add(list);
	document.add(sectionBody);

	// Section 3.2
	sectionTitle = new Paragraph("3.2. Centros educativos:\n",
		subsectionFont);
	document.add(sectionTitle);
	sectionBody = new Paragraph();
	list = new List();
	list.setIndentationLeft(20);
	list.setSymbolIndent(15);
	list.setListSymbol(new Chunk("\u2022", normalBoldTextFont));
	if (!source
		.getFieldValue(nRow, source.getFieldIndexByName("cent_jard"))
		.toString().equals("true")
		&& !source.getFieldValue(nRow,
			source.getFieldIndexByName("cent_esc")).toString()
			.equals("true")
		&& !source.getFieldValue(nRow,
			source.getFieldIndexByName("cent_cc")).toString()
			.equals("true")
		&& !source.getFieldValue(nRow,
			source.getFieldIndexByName("cent_div")).toString()
			.equals("true")) {
	    phrase = new Phrase();
	    phrase.add(new Chunk("\tNo presenta Centro Educativo.\n\n",
		    normalItalicTextFont));
	    sectionBody.add(phrase);
	} else {
	    layer = Utils.getFlyrVect(view, "centros_educativos");
	    SelectableDataSource shpSource = this.layer.getSource()
		    .getRecordset();
	    for (int i = 0; i < shpSource.getRowCount(); i++) {
		if (shpSource.getFieldValue(i,
			shpSource.getFieldIndexByName("cod_com")).toString()
			.equals(
				source.getFieldValue(nRow,
					source.getFieldIndexByName("cod_com"))
					.toString())) {
		    phrase = new Phrase();
		    phrase.add(new Chunk("Nombre del centro educativo: ",
			    normalBoldTextFont));
		    phrase.add(new Chunk(shpSource.getFieldValue(i,
			    shpSource.getFieldIndexByName("nom_cedu"))
			    .toString(), normalTextFont));
		    list.add(new ListItem(phrase));
		    phrase = new Phrase();
		    phrase.add(new Chunk("Tipo de centro educativo: ",
			    normalBoldTextFont));
		    phrase.add(new Chunk(shpSource.getFieldValue(i,
			    shpSource.getFieldIndexByName("tipo_cedu"))
			    .toString(), normalTextFont));
		    list.add(new ListItem(phrase));
		    phrase = new Phrase();
		    phrase.add(new Chunk("Número de niños: ",
			    normalBoldTextFont));
		    phrase.add(new Chunk(shpSource.getFieldValue(i,
			    shpSource.getFieldIndexByName("n_ninhos"))
			    .toString(), normalTextFont));
		    list.add(new ListItem(phrase));
		    phrase = new Phrase();
		    phrase.add(new Chunk("Número de niñas: ",
			    normalBoldTextFont));
		    phrase.add(new Chunk(shpSource.getFieldValue(i,
			    shpSource.getFieldIndexByName("n_ninhas"))
			    .toString(), normalTextFont));
		    list.add(new ListItem(phrase));
		    phrase = new Phrase();
		    phrase.add(new Chunk("Índice de deserción (%): ",
			    normalBoldTextFont));
		    phrase.add(new Chunk(shpSource.getFieldValue(i,
			    shpSource.getFieldIndexByName("i_deserc"))
			    .toString(), normalTextFont));
		    list.add(new ListItem(phrase));
		    phrase = new Phrase();
		    phrase.add(new Chunk("Merienda escolar: ",
			    normalBoldTextFont));
		    if (shpSource.getFieldValue(i,
			    shpSource.getFieldIndexByName("mer_escol"))
			    .toString().equals("true")) {
			phrase.add(new Chunk("Sí", normalItalicTextFont));
		    } else {
			phrase.add(new Chunk("No", normalItalicTextFont));
		    }
		    list.add(new ListItem(phrase));
		    sectionBody.add(list);
		}
	    }

	}
	document.add(sectionBody);

	// Section 3.3
	sectionTitle = new Paragraph("3.3. Asistencia sanitaria:\n",
		subsectionFont);
	document.add(sectionTitle);
	sectionBody = new Paragraph();
	list = new List();
	list.setIndentationLeft(20);
	list.setSymbolIndent(15);
	list.setListSymbol(new Chunk("\u2022", normalBoldTextFont));
	if (source.getFieldValue(nRow, source.getFieldIndexByName("csalud"))
		.toString().equals("true")) {
	    layer = Utils.getFlyrVect(view, "centros_salud");
	    SelectableDataSource shpSource = this.layer.getSource()
		    .getRecordset();
	    for (int i = 0; i < shpSource.getRowCount(); i++) {
		if (shpSource.getFieldValue(i,
			shpSource.getFieldIndexByName("cod_com")).toString()
			.equals(
				source.getFieldValue(nRow,
					source.getFieldIndexByName("cod_com"))
					.toString())) {
		    phrase = new Phrase();
		    phrase.add(new Chunk("Nombre del centro de salud: ",
			    normalBoldTextFont));
		    phrase.add(new Chunk(shpSource.getFieldValue(i,
			    shpSource.getFieldIndexByName("nom_csalud"))
			    .toString(), normalTextFont));
		    list.add(new ListItem(phrase));
		    phrase = new Phrase();
		    phrase.add(new Chunk(
			    "Atención a infecciones respiratorias agudas: ",
			    normalBoldTextFont));
		    if (shpSource.getFieldValue(i,
			    shpSource.getFieldIndexByName("inf_resp"))
			    .toString().equals("true")) {
			phrase.add(new Chunk("Sí", normalItalicTextFont));
		    } else {
			phrase.add(new Chunk("No", normalItalicTextFont));
		    }
		    list.add(new ListItem(phrase));
		    phrase = new Phrase();
		    phrase.add(new Chunk("Atención a infecciones de la piel: ",
			    normalBoldTextFont));
		    if (shpSource.getFieldValue(i,
			    shpSource.getFieldIndexByName("inf_piel"))
			    .toString().equals("true")) {
			phrase.add(new Chunk("Sí", normalItalicTextFont));
		    } else {
			phrase.add(new Chunk("No", normalItalicTextFont));
		    }
		    list.add(new ListItem(phrase));
		    phrase = new Phrase();
		    phrase.add(new Chunk(
			    "Atención a infecciones intestinales: ",
			    normalBoldTextFont));
		    if (shpSource.getFieldValue(i,
			    shpSource.getFieldIndexByName("inf_inst"))
			    .toString().equals("true")) {
			phrase.add(new Chunk("Sí", normalItalicTextFont));
		    } else {
			phrase.add(new Chunk("No", normalItalicTextFont));
		    }
		    list.add(new ListItem(phrase));
		    phrase = new Phrase();
		    phrase.add(new Chunk(
			    "Atención a infecciones vectoriales: ",
			    normalBoldTextFont));
		    if (shpSource.getFieldValue(i,
			    shpSource.getFieldIndexByName("inf_vec"))
			    .toString().equals("true")) {
			phrase.add(new Chunk("Sí", normalItalicTextFont));
		    } else {
			phrase.add(new Chunk("No", normalItalicTextFont));
		    }
		    list.add(new ListItem(phrase));
		    sectionBody.add(list);
		}
	    }
	} else {
	    phrase = new Phrase();
	    phrase.add(new Chunk("\tNo presenta Centro de Salud.",
		    normalItalicTextFont));
	    sectionBody.add(phrase);
	}
	document.add(sectionBody);
    }

    private void createSection4() throws DocumentException,
	    ExpansionFileReadException, ReadDriverException {

	// Section 4
	Paragraph sectionTitle = new Paragraph(
		"\n\n4. RESUMEN DE CULTIVOS EN LA COMUNIDAD:\n", sectionFont);
	document.add(sectionTitle);

	// Farming table
	Table table = new Table(2);
	darkColor = true;
	// we add a cell with colspan 3
	RtfCell cell = new RtfCell(new Phrase("Área", tableTitleTextFont));
	setCellColor(cell);
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Tipo de cultivo", tableTitleTextFont));
	setCellColor(cell);
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(cell);
	String dbfName = "cultivos";
	IWindow[] windows = PluginServices.getMDIManager().getAllWindows();
	IEditableSource dbfSource = null;
	boolean found = false;
	for (int i = 0; i < windows.length; i++) {
	    if (windows[i] instanceof com.iver.cit.gvsig.project.documents.table.gui.Table) {
		String name = ((com.iver.cit.gvsig.project.documents.table.gui.Table) windows[i])
			.getModel().getName();
		if (name.endsWith(".dbf")) {
		    name = name.substring(0, name.lastIndexOf(".dbf"));
		    if (name.equals(dbfName)) {
			dbfSource = ((com.iver.cit.gvsig.project.documents.table.gui.Table) windows[i])
				.getModel().getModelo();
			found = true;
			break;
		    }
		}
	    }
	}
	if (found) {
	    FieldDescription[] descriptions = dbfSource.getFieldsDescription();
	    HashMap<String, Integer> indexes = new HashMap<String, Integer>();
	    boolean hasRows = false;
	    for (int i = 0; i < descriptions.length; i++) {
		indexes.put(descriptions[i].getFieldName(), i);
	    }
	    for (int i = 0; i < dbfSource.getRowCount(); i++) {
		IRowEdited row = dbfSource.getRow(i);
		if (row.getAttribute(indexes.get("cod_com")).toString().equals(
			source.getFieldValue(nRow,
				source.getFieldIndexByName("cod_com"))
				.toString())) {
		    darkColor = !darkColor;
		    hasRows = true;
		    cell = new RtfCell(
			    new Phrase(row.getAttribute(indexes.get("area"))
				    .toString(), normalBoldTextFont));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    setCellColor(cell);
		    table.addCell(cell);
		    cell = new RtfCell(new Phrase(row.getAttribute(
			    indexes.get("tipo_cul")).toString(),
			    normalBoldTextFont));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    setCellColor(cell);
		    table.addCell(cell);
		}

	    }
	    if (!hasRows) {
		darkColor = !darkColor;
		cell = new RtfCell(new Phrase("", normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		setCellColor(cell);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("", normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		setCellColor(cell);
		table.addCell(cell);
	    }
	} else {
	    darkColor = !darkColor;
	    cell = new RtfCell(new Phrase("", normalTextFont));
	    setCellColor(cell);
	    table.addCell(cell);
	    cell = new RtfCell(new Phrase("", normalTextFont));
	    setCellColor(cell);
	    table.addCell(cell);
	}
	document.add(table);
    }

    private void setCellColor(RtfCell cell) {
	if (darkColor) {
	    cell.setBackgroundColor(Color.LIGHT_GRAY);
	} else {
	    cell.setBackgroundColor(new Color(242, 242, 242));
	}
    }

}// Class

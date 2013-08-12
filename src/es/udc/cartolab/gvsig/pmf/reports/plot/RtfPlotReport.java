package es.udc.cartolab.gvsig.pmf.reports.plot;

/*
 * Copyright (c) 2010. Cartolab (Universidade da Coru�a)
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
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;
import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.document.RtfDocumentSettings;
import com.lowagie.text.rtf.style.RtfFont;
import com.lowagie.text.rtf.table.RtfCell;

import es.udc.cartolab.gvsig.pmf.forms.CentrosSaludForm;
import es.udc.cartolab.gvsig.pmf.forms.ComunidadesForm;
import es.udc.cartolab.gvsig.pmf.forms.ParcelasForm;
import es.udc.cartolab.gvsig.pmf.forms.ViviendasForm;
import es.udc.cartolab.gvsig.pmf.reports.RtfBaseReport;
import es.udc.cartolab.gvsig.pmf.utils.PmfConstants;

public class RtfPlotReport extends RtfBaseReport {

    private Set<String> cul_an;
    private Set<String> cul_sp;
    private Set<String> cul_per;
    private String codCom;
    private String codViv;

    // FONT STYLES
    private final RtfFont titleFont = new RtfFont("Century Gothic", 24,
	    RtfFont.STYLE_BOLD);
    private final RtfFont subtitleFont = new RtfFont("Century Gothic", 16,
	    RtfFont.STYLE_NONE);
    private final RtfFont subtitleBoldFont = new RtfFont("Century Gothic", 16,
	    RtfFont.STYLE_BOLD);
    private final RtfFont normalTextFont = new RtfFont("Century Gothic", 10,
	    RtfFont.STYLE_NONE);
    private final RtfFont normalBoldTextFont = new RtfFont("Century Gothic",
	    10, RtfFont.STYLE_BOLD);
    private final RtfFont normalItalicTextFont = new RtfFont("Century Gothic",
	    10, RtfFont.STYLE_ITALIC);
    private final RtfFont tableTitleTextFont = new RtfFont("Century Gothic", 9,
	    RtfFont.STYLE_BOLD);
    private final RtfFont sectionFont = new RtfFont("Century Gothic", 14,
	    RtfFont.STYLE_BOLD);
    private final RtfFont subsectionFont = new RtfFont("Century Gothic", 11,
	    RtfFont.STYLE_BOLD);
    private final String[] comunidadesFieldNames = {};
    private final String[] comunidadesBoolFieldNames = { "luz_elec",
	    "agua_pot", "alcantar", "tfn_fijo" };
    private final String[] comunidadesFieldHeaders = { "Luz el�ctrica",
	    "Agua potable", "Alcantarillado", "Telefon�a fija" };

    private void setCultAn() {

	cul_an = new HashSet<String>();
	String[] cul_an_array = { "maiz", "ma�z", "frijol", "maicillo", "yuca",
		"hortalizas" };
	for (int i = 0; i < cul_an_array.length; i++) {
	    cul_an.add(cul_an_array[i]);
	}

	try {
	    String[] fields = { "hay_ot_cul", "otrocan" };
	    String[][] values = session.getTable(ParcelasForm.NAME,
		    PmfConstants.dataSchema, fields, "WHERE "
			    + ViviendasForm.PKFIELD + " = '" + codViv + "'",
		    new String[0], false);
	    if (values[0][0].equalsIgnoreCase("t")) {
		String[] ot_cul_an = values[0][1].split(" *[,y] *");
		for (int i = 0; i < ot_cul_an.length; i++) {
		    cul_an.add(ot_cul_an[i].toLowerCase());
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    private void setCultSemi() {

	cul_sp = new HashSet<String>();
	String[] cul_sp_array = { "mus�ceas", "musaceas", "papaya", "pastos" };
	for (int i = 0; i < cul_sp_array.length; i++) {
	    cul_sp.add(cul_sp_array[i]);
	}

	try {
	    String[] fields = { "hay_ot_sp", "otrocspe" };
	    String[][] values = session.getTable(ParcelasForm.NAME,
		    PmfConstants.dataSchema, fields, "WHERE "
			    + ViviendasForm.PKFIELD + " = '" + codViv + "'",
		    new String[0], false);
	    if (values[0][0].equalsIgnoreCase("t")) {
		String[] ot_cul_sp = values[0][1].split(" *[,y] *");
		for (int i = 0; i < ot_cul_sp.length; i++) {
		    cul_sp.add(ot_cul_sp[i].trim().toLowerCase());
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    private void setCultPer() {

	cul_per = new HashSet<String>();
	String[] cul_per_array = { "�rboles forestales", "arboles forestales",
		"�rboles frutales", "arboles frutales" };
	for (int i = 0; i < cul_per_array.length; i++) {
	    cul_per.add(cul_per_array[i]);
	}

    }

    public RtfPlotReport(int nRow, SelectableDataSource source, String fileName) {

	this.source = source;
	this.nRow = nRow;
	this.fileName = fileName;
	try {
	    codCom = source.getFieldValue(nRow,
		    source.getFieldIndexByName("cod_com")).toString();

	    codViv = source.getFieldValue(nRow,
		    source.getFieldIndexByName("cod_viv")).toString();

	    setCultAn();
	    setCultSemi();
	    setCultPer();

	    startDocument();
	    createSection1();
	    createSection2();

	    // close document
	    document.close();

	} catch (Exception e) {
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
		"\n\n\n\n\n\nPlan de Manejo de Finca", titleFont);
	reportTitle.setAlignment(Paragraph.ALIGN_CENTER);
	document.add(reportTitle);

	// Subtitle
	Paragraph reportSubtitle = new Paragraph();
	reportSubtitle.add(new Chunk("\n\t\tFamilia: ", subtitleBoldFont));
	reportSubtitle.add(new Chunk(source.getFieldValue(nRow,
		source.getFieldIndexByName("nom_produ")).toString(),
		subtitleFont));
	reportSubtitle.add(new Chunk("\n\t\t\t\t", normalTextFont));
	reportSubtitle.add(new Chunk(source.getFieldValue(nRow,
		source.getFieldIndexByName("direccion")).toString(),
		subtitleFont));
	reportSubtitle.add(new Chunk("\n\t\tA�o: ", subtitleBoldFont));
	reportSubtitle.add(new Chunk(" "
		+ Calendar.getInstance().get(Calendar.YEAR), subtitleFont));
	document.add(reportSubtitle);

	document.newPage();
    }

    private void createSection1() throws DocumentException,
	    ReadDriverException, SQLException {

	String[] fields = { "nombre", "municip", "departa", "n_habit", "n_fam" };
	String[][] comValues = session.getTable(ComunidadesForm.NAME,
		PmfConstants.dataSchema, fields, "WHERE "
			+ ComunidadesForm.PKFIELD + " = '" + codCom + "'",
		new String[0], false);

	// Section 1
	Paragraph sectionTitle = new Paragraph(
		"1. DATOS GENERALES DE LA FAMILIA PRODUCTORA\n", sectionFont);
	document.add(sectionTitle);
	Paragraph sectionBody = new Paragraph();
	sectionBody.add(new Chunk("\tLa finca se ubica en la comunidad de ",
		normalTextFont));
	sectionBody.add(new Chunk(comValues[0][0], normalBoldTextFont));
	sectionBody.add(new Chunk(" en el municipio de ", normalTextFont));
	sectionBody.add(new Chunk(comValues[0][1], normalBoldTextFont));
	sectionBody.add(new Chunk(" en el departamento de ", normalTextFont));
	sectionBody.add(new Chunk(comValues[0][2], normalBoldTextFont));
	sectionBody.add(new Chunk(", en Honduras.", normalTextFont));
	sectionBody.add(new Chunk("\n\n\tEn la comunidad viven ",
		normalTextFont));
	sectionBody.add(new Chunk(comValues[0][3], normalBoldTextFont));
	sectionBody
		.add(new Chunk(" personas distribuidas en ", normalTextFont));
	sectionBody.add(new Chunk(comValues[0][4], normalBoldTextFont));
	sectionBody
		.add(new Chunk(
			" familias. La comunidad se caracteriza por contar con los siguientes servicios: ",
			normalTextFont));
	sectionBody.setAlignment(Element.ALIGN_JUSTIFIED);
	document.add(sectionBody);

	// Community public services table
	Table table = getFieldsTable(
		ComunidadesForm.NAME,
		ComunidadesForm.PKFIELD,
		source.getFieldValue(nRow,
			source.getFieldIndexByName(ComunidadesForm.PKFIELD))
			.toString(), comunidadesFieldNames,
		comunidadesBoolFieldNames, comunidadesFieldHeaders);
	table.addColumns(1);
	float[] f = { 1f, 1f, 1f, 1f, 1f };
	table.setWidths(f);
	String[] csalud = null;
	RtfCell cell;
	cell = new RtfCell(new Phrase("Centro de salud", normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	darkColor = !darkColor;
	setCellColor(cell);
	table.addCell(cell, 0, 4);
	try {
	    csalud = session.getDistinctValues(CentrosSaludForm.NAME,
		    PmfConstants.dataSchema, CentrosSaludForm.PKFIELD, false,
		    false, "WHERE " + ComunidadesForm.PKFIELD + " = '" + codCom
			    + "'");
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	if ((csalud != null) && (csalud.length > 0)) {
	    cell = new RtfCell(new Phrase("S�", normalBoldTextFont));
	} else {
	    cell = new RtfCell(new Phrase("No", normalBoldTextFont));
	}
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	darkColor = !darkColor;
	setCellColor(cell);
	table.addCell(cell, 1, 4);

	document.add(table);

	sectionBody = new Paragraph();
	sectionBody.add(new Chunk("\n\tLa persona productora de la finca es ",
		normalTextFont));
	sectionBody.add(new Chunk(source.getFieldValue(nRow,
		source.getFieldIndexByName("nom_produ")).toString(),
		normalBoldTextFont));
	sectionBody.add(new Chunk(" de ", normalTextFont));
	sectionBody.add(new Chunk(source.getFieldValue(nRow,
		source.getFieldIndexByName("edad_produ")).toString(),
		normalBoldTextFont));
	sectionBody
		.add(new Chunk(
			" a�os de edad, en el momento de la realizaci�n de este plan, con n�mero de identificaci�n ",
			normalTextFont));
	sectionBody.add(new Chunk(source.getFieldValue(nRow,
		source.getFieldIndexByName("nid_produ")).toString(),
		normalBoldTextFont));
	sectionBody.add(new Chunk(" y direcci�n en ", normalTextFont));
	sectionBody.add(new Chunk(source.getFieldValue(nRow,
		source.getFieldIndexByName("direccion")).toString(),
		normalBoldTextFont));
	sectionBody.add(new Chunk(
		".\n\n\tEn la vivienda familiar viven actualmente ",
		normalTextFont));
	sectionBody.add(new Chunk(source.getFieldValue(nRow,
		source.getFieldIndexByName("n_personas")).toString(),
		normalBoldTextFont));
	sectionBody.add(new Chunk(
		" personas, tal y como se muestra en la siguiente tabla:",
		normalTextFont));
	sectionBody.setAlignment(Element.ALIGN_JUSTIFIED);
	document.add(sectionBody);

	darkColor = false;
	// Inhabitants table
	table = new Table(2);
	cell = new RtfCell(new Phrase("Hombres mayores de 5 a�os",
		normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(nRow,
		source.getFieldIndexByName("n_hombr")).toString(),
		normalTextFont)));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Mujeres mayores de 5 a�os",
		normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(nRow,
		source.getFieldIndexByName("n_mujer")).toString(),
		normalTextFont)));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Ni�os menores de 5 a�os",
		normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(nRow,
		source.getFieldIndexByName("n_ninhos")).toString(),
		normalTextFont)));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Ni�as menores de 5 a�os",
		normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(nRow,
		source.getFieldIndexByName("n_ninhas")).toString(),
		normalTextFont)));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	darkColor = !darkColor;
	cell = new RtfCell(new Phrase("Total", normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	darkColor = !darkColor;
	cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(nRow,
		source.getFieldIndexByName("n_personas")).toString(),
		normalBoldTextFont)));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	cell.setBackgroundColor(Color.LIGHT_GRAY);
	table.addCell(cell);
	cell = new RtfCell(
		new Phrase("Mujeres embarazadas", normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(nRow,
		source.getFieldIndexByName("n_embaraz")).toString(),
		normalTextFont)));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);

	document.add(table);

	sectionBody = new Paragraph();
	sectionBody.add(new Chunk("\n\tLas ", normalTextFont));
	sectionBody.add(new Chunk("principales actividades econ�micas ",
		normalBoldTextFont));
	sectionBody.add(new Chunk("de la familia productora son ",
		normalTextFont));
	if (source.getFieldValue(nRow, source.getFieldIndexByName("agricult"))
		.toString().equals("true")) {
	    if (source
		    .getFieldValue(nRow,
			    source.getFieldIndexByName("ganaderia")).toString()
		    .equals("true")) {
		if (source
			.getFieldValue(nRow,
				source.getFieldIndexByName("comercio"))
			.toString().equals("true")) {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("hay_ot_act"))
			    .toString().equals("true")) {
			sectionBody.add(new Chunk(
				"la agricultura, la ganader�a, el comercio y ",
				normalBoldTextFont));
			sectionBody.add(new Chunk(source.getFieldValue(nRow,
				source.getFieldIndexByName("otras_act"))
				.toString(), normalBoldTextFont));
		    } else {
			sectionBody.add(new Chunk(
				"la agricultura, la ganader�a y el comercio",
				normalBoldTextFont));
		    }

		} else {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("hay_ot_act"))
			    .toString().equals("true")) {
			sectionBody.add(new Chunk(
				"la agricultura, la ganader�a y ",
				normalBoldTextFont));
			sectionBody.add(new Chunk(source.getFieldValue(nRow,
				source.getFieldIndexByName("otras_act"))
				.toString(), normalBoldTextFont));

		    } else {
			sectionBody.add(new Chunk(
				"la agricultura y la ganader�a",
				normalBoldTextFont));
		    }

		}

	    } else {
		if (source
			.getFieldValue(nRow,
				source.getFieldIndexByName("comercio"))
			.toString().equals("true")) {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("hay_ot_act"))
			    .toString().equals("true")) {
			sectionBody.add(new Chunk(
				"la agricultura, el comercio y ",
				normalBoldTextFont));
			sectionBody.add(new Chunk(source.getFieldValue(nRow,
				source.getFieldIndexByName("otras_act"))
				.toString(), normalBoldTextFont));
		    } else {
			sectionBody.add(new Chunk(
				"la agricultura y el comercio",
				normalBoldTextFont));
		    }

		} else {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("hay_ot_act"))
			    .toString().equals("true")) {
			sectionBody.add(new Chunk("la agricultura y ",
				normalBoldTextFont));
			sectionBody.add(new Chunk(source.getFieldValue(nRow,
				source.getFieldIndexByName("otras_act"))
				.toString(), normalBoldTextFont));
		    } else {
			sectionBody.add(new Chunk("la agricultura",
				normalBoldTextFont));
		    }

		}

	    }

	} else {
	    if (source
		    .getFieldValue(nRow,
			    source.getFieldIndexByName("ganaderia")).toString()
		    .equals("true")) {
		if (source
			.getFieldValue(nRow,
				source.getFieldIndexByName("comercio"))
			.toString().equals("true")) {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("hay_ot_act"))
			    .toString().equals("true")) {
			sectionBody.add(new Chunk(
				"la ganader�a, el comercio y ",
				normalBoldTextFont));
			sectionBody.add(new Chunk(source.getFieldValue(nRow,
				source.getFieldIndexByName("otras_act"))
				.toString(), normalBoldTextFont));
		    } else {
			sectionBody.add(new Chunk("la ganader�a y el comercio",
				normalBoldTextFont));
		    }

		} else {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("hay_ot_act"))
			    .toString().equals("true")) {
			sectionBody.add(new Chunk("la ganader�a y ",
				normalBoldTextFont));
			sectionBody.add(new Chunk(source.getFieldValue(nRow,
				source.getFieldIndexByName("otras_act"))
				.toString(), normalBoldTextFont));
		    } else {
			sectionBody.add(new Chunk("la ganader�a",
				normalBoldTextFont));
		    }

		}

	    } else {
		if (source
			.getFieldValue(nRow,
				source.getFieldIndexByName("comercio"))
			.toString().equals("true")) {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("hay_ot_act"))
			    .toString().equals("true")) {
			sectionBody.add(new Chunk("el comercio y ",
				normalBoldTextFont));
			sectionBody.add(new Chunk(source.getFieldValue(nRow,
				source.getFieldIndexByName("otras_act"))
				.toString(), normalBoldTextFont));
		    } else {
			sectionBody.add(new Chunk("el comercio",
				normalBoldTextFont));
		    }
		} else {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("hay_ot_act"))
			    .toString().equals("true")) {
			sectionBody.add(new Chunk(source.getFieldValue(nRow,
				source.getFieldIndexByName("otras_act"))
				.toString(), normalBoldTextFont));
		    } else {
			sectionBody
				.add(new Chunk("ninguna", normalBoldTextFont));
		    }

		}

	    }

	}
	sectionBody.add(new Chunk(", proviniendo los", normalTextFont));
	sectionBody.add(new Chunk(" ingresos", normalBoldTextFont));
	sectionBody.add(new Chunk(" principalmente de ", normalTextFont));
	if (source.getFieldValue(nRow, source.getFieldIndexByName("remesas"))
		.toString().equals("true")) {
	    if (source
		    .getFieldValue(nRow,
			    source.getFieldIndexByName("e_temporal"))
		    .toString().equals("true")) {
		if (source
			.getFieldValue(nRow,
				source.getFieldIndexByName("e_perman"))
			.toString().equals("true")) {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("hay_ot_ing"))
			    .toString().equals("true")) {
			sectionBody
				.add(new Chunk(
					"remesas, empleo temporal, empleo permanente y ",
					normalBoldTextFont));
			sectionBody
				.add(new Chunk(
					source.getFieldValue(
						nRow,
						source.getFieldIndexByName("otros_ing"))
						.toString().toLowerCase(),
					normalBoldTextFont));
		    } else {
			sectionBody.add(new Chunk(
				"remesas, empleo temporal y empleo permanente",
				normalBoldTextFont));
		    }

		} else {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("hay_ot_ing"))
			    .toString().equals("true")) {
			sectionBody.add(new Chunk(
				"remesas, empleo temporal y ",
				normalBoldTextFont));
			sectionBody
				.add(new Chunk(
					source.getFieldValue(
						nRow,
						source.getFieldIndexByName("otros_ing"))
						.toString().toLowerCase(),
					normalBoldTextFont));

		    } else {
			sectionBody.add(new Chunk("remesas y empleo temporal",
				normalBoldTextFont));
		    }

		}

	    } else {
		if (source
			.getFieldValue(nRow,
				source.getFieldIndexByName("e_perman"))
			.toString().equals("true")) {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("hay_ot_ing"))
			    .toString().equals("true")) {
			sectionBody.add(new Chunk(
				"remesas, empleo permanente y ",
				normalBoldTextFont));
			sectionBody
				.add(new Chunk(
					source.getFieldValue(
						nRow,
						source.getFieldIndexByName("otros_ing"))
						.toString().toLowerCase(),
					normalBoldTextFont));
		    } else {
			sectionBody.add(new Chunk(
				"remesas y empleo permanente",
				normalBoldTextFont));
		    }

		} else {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("hay_ot_ing"))
			    .toString().equals("true")) {
			sectionBody.add(new Chunk("remesas y ",
				normalBoldTextFont));
			sectionBody
				.add(new Chunk(
					source.getFieldValue(
						nRow,
						source.getFieldIndexByName("otros_ing"))
						.toString().toLowerCase(),
					normalBoldTextFont));
		    } else {
			sectionBody
				.add(new Chunk("remesas", normalBoldTextFont));
		    }

		}

	    }

	} else {
	    if (source
		    .getFieldValue(nRow,
			    source.getFieldIndexByName("e_temporal"))
		    .toString().equals("true")) {
		if (source
			.getFieldValue(nRow,
				source.getFieldIndexByName("e_perman"))
			.toString().equals("true")) {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("hay_ot_ing"))
			    .toString().equals("true")) {
			sectionBody.add(new Chunk(
				"empleo temporal, empleo permanente y ",
				normalBoldTextFont));
			sectionBody
				.add(new Chunk(
					source.getFieldValue(
						nRow,
						source.getFieldIndexByName("otros_ing"))
						.toString().toLowerCase(),
					normalBoldTextFont));
		    } else {
			sectionBody.add(new Chunk(
				"empleo temporal y empleo permanente",
				normalBoldTextFont));
		    }

		} else {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("hay_ot_ing"))
			    .toString().equals("true")) {
			sectionBody.add(new Chunk("empleo temporal y ",
				normalBoldTextFont));
			sectionBody
				.add(new Chunk(
					source.getFieldValue(
						nRow,
						source.getFieldIndexByName("otros_ing"))
						.toString().toLowerCase(),
					normalBoldTextFont));
		    } else {
			sectionBody.add(new Chunk("empleo temporal",
				normalBoldTextFont));
		    }

		}

	    } else {
		if (source
			.getFieldValue(nRow,
				source.getFieldIndexByName("e_perman"))
			.toString().equals("true")) {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("hay_ot_ing"))
			    .toString().equals("true")) {
			sectionBody.add(new Chunk("empleo permanente y ",
				normalBoldTextFont));
			sectionBody
				.add(new Chunk(
					source.getFieldValue(
						nRow,
						source.getFieldIndexByName("otros_ing"))
						.toString().toLowerCase(),
					normalBoldTextFont));
		    } else {
			sectionBody.add(new Chunk("empleo permanente",
				normalBoldTextFont));
		    }
		} else {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("hay_ot_ing"))
			    .toString().equals("true")) {
			sectionBody
				.add(new Chunk(
					source.getFieldValue(
						nRow,
						source.getFieldIndexByName("otros_ing"))
						.toString().toLowerCase(),
					normalBoldTextFont));
		    } else {
			sectionBody.add(new Chunk("ning�n sitio",
				normalBoldTextFont));
		    }

		}

	    }

	}
	sectionBody.add(new Chunk(".\n\n\tLa vivienda de la familia es ",
		normalTextFont));
	if (!source
		.getFieldValue(nRow, source.getFieldIndexByName("estatus_vi"))
		.toString().toLowerCase().contains("selecciona una opci�n")) {
	    if (source
		    .getFieldValue(nRow,
			    source.getFieldIndexByName("estatus_vi"))
		    .toString().toLowerCase().equals("otro")) {
		sectionBody.add(new Chunk(source
			.getFieldValue(nRow,
				source.getFieldIndexByName("ot_stat_vi"))
			.toString().toLowerCase(), normalTextFont));
	    } else {
		sectionBody.add(new Chunk(source
			.getFieldValue(nRow,
				source.getFieldIndexByName("estatus_vi"))
			.toString().toLowerCase(), normalTextFont));
	    }
	} else {
	    sectionBody.add(new Chunk(
		    " [no se ha seleccionado ninguna opci�n]", normalTextFont));
	}
	if (source
		.getFieldValue(nRow, source.getFieldIndexByName("estatus_vi"))
		.toString().toLowerCase().equals("propia")) {
	    if (source
		    .getFieldValue(nRow, source.getFieldIndexByName("pro_viv"))
		    .toString().toLowerCase().equals("true")) {
		sectionBody.add(new Chunk(
			", y es propiedad legal de la familia. ",
			normalTextFont));
	    } else {
		sectionBody.add(new Chunk(
			", y no es propiedad legal de la familia. ",
			normalTextFont));
	    }
	} else {
	    sectionBody.add(new Chunk(". ", normalTextFont));
	}
	sectionBody.add(new Chunk("La vivienda se caracteriza por:",
		normalTextFont));
	sectionBody.setAlignment(Element.ALIGN_JUSTIFIED);
	document.add(sectionBody);

	// Coordinates table
	darkColor = false;
	table = new Table(2);
	cell = new RtfCell(new Phrase("Ubicaci�n (coordenadas)",
		normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	Phrase phrase = new Phrase();
	phrase.add(new Chunk("X = ", normalTextFont));
	phrase.add(new Chunk(source.getFieldValue(nRow,
		source.getFieldIndexByName("x")).toString(), normalTextFont));
	phrase.add(new Chunk("\nY = ", normalTextFont));
	phrase.add(new Chunk(source.getFieldValue(nRow,
		source.getFieldIndexByName("y")).toString(), normalTextFont));
	phrase.add(new Chunk("\nZ = ", normalTextFont));
	phrase.add(new Chunk(source.getFieldValue(nRow,
		source.getFieldIndexByName("z")).toString(), normalTextFont));
	cell = new RtfCell(phrase);
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(cell);
	setCellColor(cell);

	document.add(table);

	// Materials table
	darkColor = true;
	table = new Table(2);
	cell = new RtfCell(new Phrase("MATERIALES DE LA VIVIENDA",
		tableTitleTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	cell.setColspan(2);
	table.addCell(cell);
	darkColor = !darkColor;
	cell = new RtfCell(new Phrase("Material de las paredes",
		normalBoldTextFont));
	setCellColor(cell);
	table.addCell(cell);
	if (!source
		.getFieldValue(nRow, source.getFieldIndexByName("mat_pared"))
		.toString().toLowerCase().contains("selecciona una opci�n")) {
	    if (source
		    .getFieldValue(nRow,
			    source.getFieldIndexByName("mat_pared")).toString()
		    .toLowerCase().equals("otro")) {
		cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(
			nRow, source.getFieldIndexByName("ot_mat_pa"))
			.toString(), normalTextFont)));
	    } else {
		cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(
			nRow, source.getFieldIndexByName("mat_pared"))
			.toString(), normalTextFont)));
	    }
	} else {
	    cell = new RtfCell(new Chunk("[----]", normalTextFont));
	}
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Material del techo", normalBoldTextFont));
	setCellColor(cell);
	table.addCell(cell);
	if (!source
		.getFieldValue(nRow, source.getFieldIndexByName("mat_techo"))
		.toString().toLowerCase().contains("selecciona una opci�n")) {
	    if (source
		    .getFieldValue(nRow,
			    source.getFieldIndexByName("mat_techo")).toString()
		    .toLowerCase().equals("otro")) {
		cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(
			nRow, source.getFieldIndexByName("ot_mat_te"))
			.toString(), normalTextFont)));
	    } else {
		cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(
			nRow, source.getFieldIndexByName("mat_techo"))
			.toString(), normalTextFont)));
	    }
	} else {
	    cell = new RtfCell(new Chunk("[----]", normalTextFont));
	}
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Material del piso", normalBoldTextFont));
	setCellColor(cell);
	table.addCell(cell);
	if (!source.getFieldValue(nRow, source.getFieldIndexByName("mat_piso"))
		.toString().toLowerCase().contains("selecciona una opci�n")) {
	    if (source
		    .getFieldValue(nRow, source.getFieldIndexByName("mat_piso"))
		    .toString().toLowerCase().equals("otro")) {
		cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(
			nRow, source.getFieldIndexByName("ot_mat_pi"))
			.toString(), normalTextFont)));
	    } else {
		cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(
			nRow, source.getFieldIndexByName("mat_piso"))
			.toString(), normalTextFont)));
	    }
	} else {
	    cell = new RtfCell(new Chunk("[----]", normalTextFont));
	}
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);

	document.add(table);

	// Public services table
	darkColor = true;
	table = new Table(2);
	cell = new RtfCell(new Phrase("SERVICIO", tableTitleTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("PRESENTE EN LA VIVIENDA",
		tableTitleTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	darkColor = !darkColor;
	cell = new RtfCell(new Phrase("Luz el�ctrica", normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	if (source.getFieldValue(nRow, source.getFieldIndexByName("luz_elec"))
		.toString().equals("true")) {
	    cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
	} else {
	    cell = new RtfCell(new Phrase("No", normalItalicTextFont));
	}
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Agua potable", normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	if (source.getFieldValue(nRow, source.getFieldIndexByName("agua_pot"))
		.toString().equals("true")) {
	    cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
	} else {
	    cell = new RtfCell(new Phrase("No", normalItalicTextFont));
	}
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Alcantarillado", normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	if (source.getFieldValue(nRow, source.getFieldIndexByName("alcantar"))
		.toString().equals("true")) {
	    cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
	} else {
	    cell = new RtfCell(new Phrase("No", normalItalicTextFont));
	}
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Telefon�a fija", normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	if (source.getFieldValue(nRow, source.getFieldIndexByName("telefono"))
		.toString().equals("true")) {
	    cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
	} else {
	    cell = new RtfCell(new Phrase("No", normalItalicTextFont));
	}
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Alumbrado p�blico", normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	if (source.getFieldValue(nRow, source.getFieldIndexByName("alum_publ"))
		.toString().equals("true")) {
	    cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
	} else {
	    cell = new RtfCell(new Phrase("No", normalItalicTextFont));
	}
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);

	document.add(table);

	// Materials table
	darkColor = true;
	table = new Table(2);
	cell = new RtfCell(new Phrase(
		"INFRAESTRUCTURAS B�SICAS DE LA VIVIENDA", tableTitleTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	cell.setColspan(2);
	table.addCell(cell);
	darkColor = !darkColor;
	cell = new RtfCell(new Phrase("Letrina", normalBoldTextFont));
	setCellColor(cell);
	table.addCell(cell);
	if (source.getFieldValue(nRow, source.getFieldIndexByName("letrina"))
		.toString().toLowerCase().equals("true")) {
	    cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
	} else {
	    cell = new RtfCell(new Phrase("No", normalItalicTextFont));
	}
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Cocina mejorada", normalBoldTextFont));
	setCellColor(cell);
	table.addCell(cell);
	if (source.getFieldValue(nRow, source.getFieldIndexByName("coc_mejor"))
		.toString().toLowerCase().equals("true")) {
	    cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
	} else {
	    cell = new RtfCell(new Phrase("No", normalItalicTextFont));
	}
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Filtro de aguas grises",
		normalBoldTextFont));
	setCellColor(cell);
	table.addCell(cell);
	if (source.getFieldValue(nRow, source.getFieldIndexByName("filtro_ag"))
		.toString().toLowerCase().equals("true")) {
	    cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
	} else {
	    cell = new RtfCell(new Phrase("No", normalItalicTextFont));
	}
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase(
		"Sistemas de almacenamiento de grano b�sicos",
		normalBoldTextFont));
	setCellColor(cell);
	table.addCell(cell);

	if (source.getFieldValue(nRow, source.getFieldIndexByName("silos"))
		.toString().equals("true")) {
	    if (source
		    .getFieldValue(nRow,
			    source.getFieldIndexByName("trojas_mej"))
		    .toString().equals("true")) {
		if (source
			.getFieldValue(nRow,
				source.getFieldIndexByName("sacos")).toString()
			.equals("true")) {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("ramadas"))
			    .toString().equals("true")) {
			cell = new RtfCell(
				new Chunk(
					"Silos met�licos, trojas mejoradas, sacos y ramadas",
					normalTextFont));
		    } else {
			cell = new RtfCell(new Chunk(
				"Silos met�licos, trojas mejoradas y sacos",
				normalTextFont));
		    }

		} else {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("ramadas"))
			    .toString().equals("true")) {
			cell = new RtfCell(new Chunk(
				"Silos met�licos, trojas mejoradas y ramadas",
				normalTextFont));

		    } else {
			cell = new RtfCell(new Chunk(
				"Silos met�licos y trojas mejoradas",
				normalTextFont));
		    }

		}

	    } else {
		if (source
			.getFieldValue(nRow,
				source.getFieldIndexByName("sacos")).toString()
			.equals("true")) {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("ramadas"))
			    .toString().equals("true")) {
			cell = new RtfCell(new Chunk(
				"Silos met�licos, sacos y ramadas",
				normalTextFont));
		    } else {
			cell = new RtfCell(new Chunk("Silos met�licos y sacos",
				normalTextFont));
		    }

		} else {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("ramadas"))
			    .toString().equals("true")) {
			cell = new RtfCell(new Chunk(
				"Silos met�licos y ramadas", normalTextFont));
		    } else {
			cell = new RtfCell(new Chunk("Silos met�licos",
				normalTextFont));
		    }

		}

	    }

	} else {
	    if (source
		    .getFieldValue(nRow,
			    source.getFieldIndexByName("trojas_mej"))
		    .toString().equals("true")) {
		if (source
			.getFieldValue(nRow,
				source.getFieldIndexByName("sacos")).toString()
			.equals("true")) {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("ramadas"))
			    .toString().equals("true")) {
			cell = new RtfCell(new Chunk(
				"Trojas mejoradas, sacos y ramadas",
				normalTextFont));
		    } else {
			cell = new RtfCell(new Chunk(
				"Trojas mejoradas y sacos", normalTextFont));
		    }

		} else {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("ramadas"))
			    .toString().equals("true")) {
			cell = new RtfCell(new Chunk(
				"Trojas mejoradas y ramadas", normalTextFont));
		    } else {
			cell = new RtfCell(new Chunk("Trojas mejoradas",
				normalTextFont));
		    }

		}

	    } else {
		if (source
			.getFieldValue(nRow,
				source.getFieldIndexByName("sacos")).toString()
			.equals("true")) {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("ramadas"))
			    .toString().equals("true")) {
			cell = new RtfCell(new Chunk("Sacos y ramadas",
				normalTextFont));
		    } else {
			cell = new RtfCell(new Chunk("Sacos", normalTextFont));
		    }
		} else {
		    if (source
			    .getFieldValue(nRow,
				    source.getFieldIndexByName("ramadas"))
			    .toString().equals("true")) {
			cell = new RtfCell(new Chunk("Ramadas", normalTextFont));
		    } else {
			cell = new RtfCell(new Chunk("Ninguno", normalTextFont));
		    }

		}

	    }

	}

	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);

	document.add(table);

	String[] fields2 = { "hay_in_par" };
	String[][] values = session.getTable(ParcelasForm.NAME,
		PmfConstants.dataSchema, fields2, "WHERE "
			+ ViviendasForm.PKFIELD + " = '" + codViv + "'",
		new String[0], false);

	sectionBody = new Paragraph();
	sectionBody.add(new Chunk("\n"));
	if (values[0][0].toLowerCase().equals("t")) {
	    if (source
		    .getFieldValue(nRow,
			    source.getFieldIndexByName("hay_in_viv"))
		    .toString().toLowerCase().equals("true")) {
		sectionBody
			.add(new Chunk("\tExiste riesgo de ", normalTextFont));
		sectionBody.add(new Chunk("inundaci�n", normalBoldTextFont));
		sectionBody.add(new Chunk(" tanto en la ", normalTextFont));
		sectionBody.add(new Chunk("parcela", normalBoldTextFont));
		sectionBody.add(new Chunk(" como en la ", normalTextFont));
		sectionBody.add(new Chunk("vivienda", normalBoldTextFont));
		sectionBody.add(new Chunk(".", normalTextFont));
	    } else {
		sectionBody
			.add(new Chunk("\tExiste riesgo de ", normalTextFont));
		sectionBody.add(new Chunk("inundaci�n", normalBoldTextFont));
		sectionBody.add(new Chunk(" en la ", normalTextFont));
		sectionBody.add(new Chunk("parcela", normalBoldTextFont));
		sectionBody.add(new Chunk(" pero no en la vivienda.",
			normalTextFont));
	    }
	} else {
	    if (source
		    .getFieldValue(nRow,
			    source.getFieldIndexByName("hay_in_viv"))
		    .toString().toLowerCase().equals("true")) {
		sectionBody.add(new Chunk("\tNo existe riesgo de ",
			normalTextFont));
		sectionBody.add(new Chunk("inundaci�n", normalBoldTextFont));
		sectionBody.add(new Chunk(" en la parcela, pero s� en la ",
			normalTextFont));
		sectionBody.add(new Chunk("vivienda", normalBoldTextFont));
		sectionBody.add(new Chunk(".", normalTextFont));
	    } else {
		sectionBody.add(new Chunk("\tNo existe riesgo de ",
			normalTextFont));
		sectionBody.add(new Chunk("inundaci�n", normalBoldTextFont));
		sectionBody
			.add(new Chunk(" ni en la parcela ni en la vivienda.",
				normalTextFont));
	    }
	}

	document.add(sectionBody);

    }

    private void createSection2() throws DocumentException,
	    ReadDriverException, SQLException {

	String[] fields = { "area_tot", "area_cul", "legal_par", "ot_legal_p",
		"legal_par", "pendiente", "tip_suelo", "ot_tip_su",
		"deg_suelo", "cerca", "b_vivas", "b_muertas", "hay_ot_cer",
		"ot_cerca", "poz_pro", "rio", "nacimiento", "poz_com",
		"reserv", "existe_fc", "d_fue_tan", "d_tan_hue", "prac_conse",
		"c_conse", "uso_aborg", "c_aborg", "insect_org", "c_insect",
		"uso_quim", "c_quim", "p_riego", "p_huerto", "p_coc_mejo",
		"p_filtroag", "p_galline" };
	String[][] values = session.getTable(ParcelasForm.NAME,
		PmfConstants.dataSchema, fields, "WHERE "
			+ ViviendasForm.PKFIELD + " = '" + codViv + "'",
		new String[0], false);

	// Section 2
	Paragraph sectionTitle = new Paragraph(
		"\n\n2. DATOS DE LA FINCA E INFRAESTRUCTURA PRODUCTIVA\n",
		sectionFont);
	document.add(sectionTitle);
	Paragraph sectionBody = new Paragraph();
	sectionBody.add(new Chunk(
		"\tLa finca de la familia productora tiene una ",
		normalTextFont));
	sectionBody.add(new Chunk("superficie total ", normalBoldTextFont));
	sectionBody.add(new Chunk("de ", normalTextFont));
	sectionBody.add(new Chunk(values[0][0], normalBoldTextFont));
	sectionBody.add(new Chunk(" manzanas, siendo la ", normalTextFont));
	sectionBody
		.add(new Chunk("superficie cultivable ", normalBoldTextFont));
	sectionBody.add(new Chunk("de ", normalTextFont));
	sectionBody.add(new Chunk(values[0][1], normalBoldTextFont));
	sectionBody.add(new Chunk(
		" manzanas. La finca pertenece a la familia por ",
		normalTextFont));
	if (values[0][2].toLowerCase().equals("otro")) {
	    sectionBody.add(new Chunk(values[0][3].toLowerCase(),
		    normalTextFont));
	} else {
	    sectionBody.add(new Chunk(values[0][4].toLowerCase(),
		    normalTextFont));
	}
	sectionBody.add(new Chunk(".", normalTextFont));
	sectionBody.setAlignment(Element.ALIGN_JUSTIFIED);

	document.add(sectionBody);

	// Subsection
	sectionTitle = new Paragraph("\n\nCARACTER�STICAS DE LA PARCELA:\n",
		subsectionFont);
	document.add(sectionTitle);

	sectionBody = new Paragraph();
	sectionBody.add(new Chunk("\tLa finca tiene una pendiente media del ",
		normalTextFont));
	sectionBody.add(new Chunk(values[0][5], normalTextFont));
	sectionBody.add(new Chunk(", el suelo es principalmente ",
		normalTextFont));
	if (values[0][6].toLowerCase().equals("otro")) {
	    sectionBody.add(new Chunk(values[0][7].toLowerCase(),
		    normalTextFont));
	} else {
	    sectionBody.add(new Chunk(values[0][6].toLowerCase(),
		    normalTextFont));
	}
	sectionBody.add(new Chunk(" y se encuentra en un nivel ",
		normalTextFont));
	sectionBody.add(new Chunk(values[0][8].toLowerCase(), normalTextFont));
	sectionBody.add(new Chunk(" de degradaci�n.\n\t", normalTextFont));

	if (values[0][9].equals("t")) {
	    if (values[0][10].equals("t")) {
		if (values[0][11].equals("t")) {
		    if (values[0][12].equals("t")) {
			sectionBody
				.add(new Chunk(
					"La parcela cuenta adem�s con barreras vivas, barreras muertas y ",
					normalTextFont));
			sectionBody.add(new Chunk(values[0][13].toLowerCase(),
				normalTextFont));
			sectionBody.add(new Chunk(" como cercado.\n\n",
				normalTextFont));
		    } else {
			sectionBody
				.add(new Chunk(
					"La parcela cuenta adem�s con barreras vivas y barreras muertas como cercado.\n\n",
					normalTextFont));
		    }

		} else {
		    if (values[0][12].equals("t")) {
			sectionBody
				.add(new Chunk(
					"La parcela cuenta adem�s con barreras vivas y ",
					normalTextFont));
			sectionBody.add(new Chunk(values[0][13].toLowerCase(),
				normalTextFont));
			sectionBody.add(new Chunk(" como cercado.\n\n",
				normalTextFont));

		    } else {
			sectionBody
				.add(new Chunk(
					"La parcela cuenta adem�s con barreras vivas como cercado.\n\n",
					normalTextFont));
		    }

		}

	    } else {
		if (values[0][11].equals("t")) {
		    if (values[0][12].equals("t")) {
			sectionBody
				.add(new Chunk(
					"La parcela cuenta adem�s con barreras muertas y ",
					normalTextFont));
			sectionBody.add(new Chunk(values[0][13].toLowerCase(),
				normalTextFont));
			sectionBody.add(new Chunk(" como cercado.\n\n",
				normalTextFont));
		    } else {
			sectionBody
				.add(new Chunk(
					"La parcela cuenta adem�s con barreras muertas como cercado.\n\n",
					normalTextFont));
		    }

		} else {
		    if (values[0][12].equals("t")) {
			sectionBody
				.add(new Chunk("La parcela cuenta adem�s con ",
					normalTextFont));
			sectionBody.add(new Chunk(values[0][13].toLowerCase(),
				normalTextFont));
			sectionBody.add(new Chunk(" como cercado.\n\n",
				normalTextFont));
		    } else {
			sectionBody
				.add(new Chunk(
					"La parcela carece de cualquier tipo de cercado.\n\n",
					normalTextFont));
		    }

		}

	    }

	} else {
	    sectionBody.add(new Chunk(
		    "La parcela carece de cualquier tipo de cercado.\n\n",
		    normalTextFont));

	}
	sectionBody.setAlignment(Element.ALIGN_JUSTIFIED);

	document.add(sectionBody);

	// Subsection
	sectionTitle = new Paragraph("SISTEMAS DE RIEGO:\n", subsectionFont);
	document.add(sectionTitle);

	sectionBody = new Paragraph();
	sectionBody.add(new Chunk("\tLa finca", normalTextFont));

	if (!values[0][14].equals("t")) {
	    sectionBody.add(new Chunk(" no dispone de fuentes de riego, ",
		    normalTextFont));
	} else {
	    if (values[0][15].equals("t")) {
		if (values[0][16].equals("t")) {
		    if (values[0][17].equals("t")) {
			if (values[0][18].equals("t")) {
			    if (values[0][14].equals("t")) {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de r�o, nacimiento, pozo comunitario, reservorio y pozo propio, ",
						normalTextFont));
			    } else {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de r�o, nacimiento, pozo comunitario y reservorio, ",
						normalTextFont));
			    }
			} else {
			    if (values[0][14].equals("t")) {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de r�o, nacimiento, pozo comunitario y pozo propio, ",
						normalTextFont));
			    } else {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de r�o, nacimiento y pozo comunitario, ",
						normalTextFont));
			    }
			}

		    } else {
			if (values[0][18].equals("t")) {
			    if (values[0][14].equals("t")) {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de r�o, nacimiento, reservorio y pozo propio, ",
						normalTextFont));
			    } else {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de r�o, nacimiento y reservorio, ",
						normalTextFont));
			    }
			} else {
			    if (values[0][14].equals("t")) {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de r�o, nacimiento y pozo propio, ",
						normalTextFont));
			    } else {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de r�o y nacimiento, ",
						normalTextFont));

			    }
			}
		    }

		} else {
		    if (values[0][17].equals("t")) {
			if (values[0][18].equals("t")) {
			    if (values[0][14].equals("t")) {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de r�o, pozo comunitario, reservorio y pozo propio, ",
						normalTextFont));
			    } else {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de r�o, pozo comunitario y reservorio, ",
						normalTextFont));
			    }
			} else {
			    if (values[0][14].equals("t")) {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de r�o, pozo comunitario y pozo propio, ",
						normalTextFont));
			    } else {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de r�o y pozo comunitario, ",
						normalTextFont));
			    }
			}

		    } else {
			if (values[0][18].equals("t")) {
			    if (values[0][14].equals("t")) {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de r�o, reservorio y pozo propio, ",
						normalTextFont));
			    } else {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de r�o y reservorio, ",
						normalTextFont));
			    }
			} else {
			    if (values[0][14].equals("t")) {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de r�o y pozo propio, ",
						normalTextFont));
			    } else {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de r�o, ",
						normalTextFont));

			    }
			}
		    }

		}

	    } else {
		if (values[0][16].equals("t")) {
		    if (values[0][17].equals("t")) {
			if (values[0][18].equals("t")) {
			    if (values[0][14].equals("t")) {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de nacimiento, pozo comunitario, reservorio y pozo propio, ",
						normalTextFont));
			    } else {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de nacimiento, pozo comunitario y reservorio, ",
						normalTextFont));
			    }
			} else {
			    if (values[0][14].equals("t")) {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de nacimiento, pozo comunitario y pozo propio, ",
						normalTextFont));
			    } else {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de nacimiento y pozo comunitario, ",
						normalTextFont));
			    }
			}

		    } else {
			if (values[0][18].equals("t")) {
			    if (values[0][14].equals("t")) {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de nacimiento, reservorio y pozo propio, ",
						normalTextFont));
			    } else {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de nacimiento y reservorio, ",
						normalTextFont));
			    }
			} else {
			    if (values[0][14].equals("t")) {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de nacimiento y pozo propio, ",
						normalTextFont));
			    } else {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de nacimiento, ",
						normalTextFont));

			    }
			}
		    }

		} else {
		    if (values[0][17].equals("t")) {
			if (values[0][18].equals("t")) {
			    if (values[0][14].equals("t")) {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de pozo comunitario, reservorio y pozo propio, ",
						normalTextFont));
			    } else {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de pozo comunitario y reservorio, ",
						normalTextFont));
			    }
			} else {
			    if (values[0][14].equals("t")) {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de pozo comunitario y pozo propio, ",
						normalTextFont));
			    } else {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de pozo comunitario, ",
						normalTextFont));
			    }
			}

		    } else {
			if (values[0][18].equals("t")) {
			    if (values[0][14].equals("t")) {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de reservorio y pozo propio, ",
						normalTextFont));
			    } else {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de reservorio, ",
						normalTextFont));
			    }
			} else {
			    if (values[0][14].equals("t")) {
				sectionBody
					.add(new Chunk(
						" dispone como fuentes de riego de pozo propio, ",
						normalTextFont));
			    } else {
				sectionBody.add(new Chunk(
					" no dispone de fuentes de riego, ",
					normalTextFont));

			    }
			}
		    }

		}

	    }
	}

	if (values[0][19].equals("t")) {
	    sectionBody
		    .add(new Chunk(
			    "y s� dispone de fuente de agua com�n para varios productores. ",
			    normalTextFont));
	} else {
	    sectionBody
		    .add(new Chunk(
			    "y no dispone de fuente de agua com�n para varios productores. ",
			    normalTextFont));
	}
	sectionBody.add(new Chunk(
		"La distancia registrada desde la fuente al tanque es de ",
		normalTextFont));
	sectionBody.add(new Chunk(values[0][20], normalTextFont));
	sectionBody
		.add(new Chunk(
			" m., mientras que la distancia desde el tanque al huerto es de ",
			normalTextFont));
	sectionBody.add(new Chunk(values[0][21], normalTextFont));
	sectionBody.add(new Chunk(" m.\n\n", normalTextFont));
	sectionBody.setAlignment(Element.ALIGN_JUSTIFIED);

	document.add(sectionBody);

	// Subsection
	sectionTitle = new Paragraph("MANEJO DEL MEDIO AMBIENTE:\n",
		subsectionFont);
	document.add(sectionTitle);

	sectionBody = new Paragraph();
	sectionBody
		.add(new Chunk(
			"\tLas pr�cticas agrarias utilizadas en el manejo de los recursos de la finca son:",
			normalTextFont));
	sectionBody.setAlignment(Element.ALIGN_JUSTIFIED);
	document.add(sectionBody);

	// Farming practices table
	darkColor = true;
	Table table = new Table(3);
	RtfCell cell = new RtfCell(new Phrase("Tipo", tableTitleTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Presente en la finca",
		tableTitleTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Descripci�n", tableTitleTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	darkColor = !darkColor;
	cell = new RtfCell(new Phrase("Pr�cticas conservacionistas",
		normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	if (values[0][22].equals("t")) {
	    cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
	} else {
	    cell = new RtfCell(new Phrase("No", normalItalicTextFont));
	}
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase(values[0][23], normalTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Abono org�nico", normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	if (values[0][24].equals("t")) {
	    cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
	} else {
	    cell = new RtfCell(new Phrase("No", normalItalicTextFont));
	}
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase(values[0][25], normalTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Insecticidas org�nicos",
		normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	if (values[0][26].equals("t")) {
	    cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
	} else {
	    cell = new RtfCell(new Phrase("No", normalItalicTextFont));
	}
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase(values[0][27], normalTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Plaguicidas qu�micos",
		normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	if (values[0][28].equals("t")) {
	    cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
	} else {
	    cell = new RtfCell(new Phrase("No", normalItalicTextFont));
	}
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase(values[0][29], normalTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);

	document.add(table);

	// Subsection
	sectionTitle = new Paragraph("\n\nUTILIZACI�N ACTUAL DE LA PARCELA:\n",
		subsectionFont);
	document.add(sectionTitle);

	sectionBody = new Paragraph();
	sectionBody
		.add(new Chunk(
			"\tActualmente se hace aprovechamiento de los siguientes cultivos en la parcela:",
			normalTextFont));
	sectionBody.setAlignment(Element.ALIGN_JUSTIFIED);
	document.add(sectionBody);

	// Farming table
	table = new Table(4);
	darkColor = true;
	cell = new RtfCell(new Phrase("Cultivo", tableTitleTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Tipo", tableTitleTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("�rea cultivada", tableTitleTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Volumen producido", tableTitleTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	String tableName = "cultivos";
	String[] fields2 = { "tipo", "area", "vol_prod" };
	String[][] values2 = session.getTable(tableName,
		PmfConstants.dataSchema, fields2, "WHERE "
			+ ViviendasForm.PKFIELD + " = '" + codViv + "'",
		new String[0], false);
	for (String[] row : values2) {
	    darkColor = !darkColor;
	    if (cul_an.contains(row[0].toLowerCase())) {
		cell = new RtfCell(new Phrase("Anual", normalTextFont));
	    } else if (cul_sp.contains(row[0].toLowerCase())) {
		cell = new RtfCell(new Phrase("Semi perenne", normalTextFont));
	    } else if (cul_per.contains(row[0].toLowerCase())) {
		cell = new RtfCell(new Phrase("Permanente", normalTextFont));
	    } else {
		cell = new RtfCell(new Phrase("Indeterminado", normalTextFont));
	    }
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    setCellColor(cell);
	    table.addCell(cell);
	    cell = new RtfCell(new Phrase(row[0], normalBoldTextFont));
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    setCellColor(cell);
	    table.addCell(cell);
	    cell = new RtfCell(new Phrase(row[1], normalBoldTextFont));
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    setCellColor(cell);
	    table.addCell(cell);
	    cell = new RtfCell(new Phrase(row[2], normalBoldTextFont));
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    setCellColor(cell);
	    table.addCell(cell);
	}
	if (values2.length == 0) {
	    darkColor = !darkColor;
	    cell = new RtfCell(new Phrase("", normalTextFont));
	    setCellColor(cell);
	    table.addCell(cell);
	    cell = new RtfCell(new Phrase("", normalTextFont));
	    setCellColor(cell);
	    table.addCell(cell);
	    cell = new RtfCell(new Phrase("", normalTextFont));
	    setCellColor(cell);
	    table.addCell(cell);
	    cell = new RtfCell(new Phrase("", normalTextFont));
	    setCellColor(cell);
	    table.addCell(cell);
	}
	document.add(table);

	// Subsection
	sectionTitle = new Paragraph(
		"\n\nPLANIFICACI�N DE MEJORAS A REALIZAR:\n", subsectionFont);
	document.add(sectionTitle);

	sectionBody = new Paragraph();
	sectionBody
		.add(new Chunk(
			"\tUna vez estudiada la finca se planificaron las siguientes mejoras:",
			normalTextFont));
	sectionBody.setAlignment(Element.ALIGN_JUSTIFIED);
	document.add(sectionBody);

	// Improvements table
	darkColor = false;
	table = new Table(2);
	cell = new RtfCell(new Phrase("Tipo de mejora", tableTitleTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	cell.setBackgroundColor(Color.LIGHT_GRAY);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Per�odo de realizaci�n",
		tableTitleTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	cell.setBackgroundColor(Color.LIGHT_GRAY);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Sistema de riego", normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase(values[0][30], normalTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Huerto familiar", normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase(values[0][31], normalTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Cocina mejorada", normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase(values[0][32], normalTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Filtro para aguas grises",
		normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase(values[0][33], normalTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase("Construcci�n de gallinero",
		normalBoldTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);
	cell = new RtfCell(new Phrase(values[0][34], normalTextFont));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	setCellColor(cell);
	table.addCell(cell);

	document.add(table);

    }

}// Class

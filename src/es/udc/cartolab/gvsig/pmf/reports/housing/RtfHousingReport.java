package es.udc.cartolab.gvsig.pmf.reports.housing;

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

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;
import com.iver.cit.gvsig.project.documents.view.gui.BaseView;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
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

import es.udc.cartolab.gvsig.navtableforms.Utils;

public class RtfHousingReport {

	private FLyrVect layer;
	private BaseView view = null;
	private int nRow;
	private String fileName;
	private boolean darkColor = true;
	private SelectableDataSource source;
	private SelectableDataSource comSource;
	private int nComRow;

	private final Document document;

	// FONT STYLES
	private final RtfFont titleFont = new RtfFont("Century Gothic", 28,
			RtfFont.STYLE_BOLD);
	private final RtfFont subtitleFont = new RtfFont("Century Gothic", 16,
			RtfFont.STYLE_NONE);
	private final RtfFont subtitleBoldFont = new RtfFont("Century Gothic", 16,
			RtfFont.STYLE_BOLD);
	private final RtfFont normalTextFont = new RtfFont("Century Gothic", 12,
			RtfFont.STYLE_NONE);
	private final RtfFont normalBoldTextFont = new RtfFont("Century Gothic",
			12, RtfFont.STYLE_BOLD);
	private final RtfFont normalItalicTextFont = new RtfFont("Century Gothic",
			10, RtfFont.STYLE_ITALIC);
	private final RtfFont tableTitleTextFont = new RtfFont("Century Gothic", 9,
			RtfFont.STYLE_NONE);
	private final RtfFont sectionFont = new RtfFont("Century Gothic", 14,
			RtfFont.STYLE_BOLD);
	private final RtfFont subsectionFont = new RtfFont("Century Gothic", 11,
			RtfFont.STYLE_BOLD);

	public RtfHousingReport(int nRow, SelectableDataSource source,
			String fileName, BaseView view) {

		this.view = view;
		this.source = source;
		this.nRow = nRow;
		this.fileName = fileName;
		layer = Utils.getFlyrVect(view, "comunidad");
		document = new Document();
		try {
			comSource = this.layer.getSource().getRecordset();

			String nomCom = source.getFieldValue(nRow,
					source.getFieldIndexByName("cod_com")).toString();

			for (int i = 0; i < comSource.getRowCount(); i++) {
				if (comSource.getFieldValue(i,
						comSource.getFieldIndexByName("cod_com")).toString()
						.equals(nomCom)) {
					nComRow = i;
					break;
				}
			}

			startDocument();
			createSection1();

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

		HeaderFooter footer = new HeaderFooter(new Phrase(""), true);
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
		reportSubtitle.add(new Chunk("\n\t\t\t\t"));
		reportSubtitle.add(new Chunk(source.getFieldValue(nRow,
				source.getFieldIndexByName("direccion")).toString(),
				subtitleFont));
		reportSubtitle.add(new Chunk("\n\t\tAño: ", subtitleBoldFont));
		reportSubtitle.add(new Chunk(" 2011", subtitleFont));
		document.add(reportSubtitle);

		document.newPage();
	}

	private void createSection1() throws DocumentException, ReadDriverException {

		// Section 1
		Paragraph sectionTitle = new Paragraph(
				"1. DATOS GENERALES DE LA FAMILIA PRODUCTORA\n", sectionFont);
		document.add(sectionTitle);
		Paragraph sectionBody = new Paragraph();
		sectionBody.add(new Chunk("\tLa finca se ubica en la comunidad de ",
				normalTextFont));
		sectionBody.add(new Chunk(comSource.getFieldValue(nComRow,
				comSource.getFieldIndexByName("nombre")).toString(),
				normalBoldTextFont));
		sectionBody.add(new Chunk(" en el municipio de ", normalTextFont));
		sectionBody.add(new Chunk(comSource.getFieldValue(nComRow,
				comSource.getFieldIndexByName("municip")).toString(),
				normalBoldTextFont));
		sectionBody.add(new Chunk(" en el departamento de ", normalTextFont));
		sectionBody.add(new Chunk(comSource.getFieldValue(nComRow,
				comSource.getFieldIndexByName("departa")).toString(),
				normalBoldTextFont));
		sectionBody.add(new Chunk(", en Honduras."));
		sectionBody.add(new Chunk("\n\n\tEn la comunidad viven ",
				normalTextFont));
		sectionBody.add(new Chunk(comSource.getFieldValue(nComRow,
				comSource.getFieldIndexByName("n_habit")).toString(),
				normalBoldTextFont));
		sectionBody
				.add(new Chunk(" personas distribuidas en ", normalTextFont));
		sectionBody.add(new Chunk(comSource.getFieldValue(nComRow,
				comSource.getFieldIndexByName("n_fam")).toString(),
				normalBoldTextFont));
		sectionBody
				.add(new Chunk(
						" familias. La comunidad se caracteriza por contar con los siguientes servicios: ",
						normalTextFont));
		document.add(sectionBody);

		// Base organizations table
		Table table = new Table(2);
		darkColor = true;
		// we add a cell with colspan 3
		RtfCell cell = new RtfCell(new Phrase("SERVICIO", tableTitleTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("PRESENTE EN LA COMUNIDAD",
				tableTitleTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Luz eléctrica", normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (comSource.getFieldValue(nComRow,
				comSource.getFieldIndexByName("luz_elec")).toString().equals(
				"true")) {
			cell = new RtfCell(new Phrase("Sí", normalItalicTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		}
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Agua potable", normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (comSource.getFieldValue(nComRow,
				comSource.getFieldIndexByName("agua_pot")).toString().equals(
				"true")) {
			cell = new RtfCell(new Phrase("Sí", normalItalicTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		}
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Alcantarillado", normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (comSource.getFieldValue(nComRow,
				comSource.getFieldIndexByName("alcantar")).toString().equals(
				"true")) {
			cell = new RtfCell(new Phrase("Sí", normalItalicTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		}
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Telefonía fija", normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (comSource.getFieldValue(nComRow,
				comSource.getFieldIndexByName("tfn_fijo")).toString().equals(
				"true")) {
			cell = new RtfCell(new Phrase("Sí", normalItalicTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		}
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Centro de salud", normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (comSource.getFieldValue(nComRow,
				comSource.getFieldIndexByName("csalud")).toString().equals(
				"true")) {
			cell = new RtfCell(new Phrase("Sí", normalItalicTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		}
		table.addCell(cell);

		document.add(table);
	}

}// Class

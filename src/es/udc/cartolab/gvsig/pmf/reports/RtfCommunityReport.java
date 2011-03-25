package es.udc.cartolab.gvsig.pmf.reports;

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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
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
	private String layerName;

	private final Document document;

	// FONT STYLES
	private final RtfFont titleFont = new RtfFont("Century Gothic", 24,
			RtfFont.STYLE_BOLD);
	private final RtfFont subtitleFont = new RtfFont("Century Gothic", 24,
			RtfFont.STYLE_NONE);
	private final RtfFont normalBoldTextFont = new RtfFont("Century Gothic",
			10, RtfFont.STYLE_BOLD);
	private final RtfFont normalItalicTextFont = new RtfFont("Century Gothic",
			10, RtfFont.STYLE_ITALIC);
	private final RtfFont normalTextFont = new RtfFont("Century Gothic", 10,
			RtfFont.STYLE_NONE);
	private final RtfFont sectionFont = new RtfFont("Century Gothic", 14,
			RtfFont.STYLE_BOLD);
	private final RtfFont subsectionFont = new RtfFont("Century Gothic", 11,
			RtfFont.STYLE_BOLD);

	public RtfCommunityReport(int nRow, SelectableDataSource source,
			String fileName, BaseView view) {

		this.view = view;
		document = new Document();
		RtfWriter2 writer;
		try {

			// getting document instance and opening to write
			writer = RtfWriter2.getInstance(document, new FileOutputStream(
					fileName));
			document.open();
			RtfDocumentSettings settings = writer.getDocumentSettings();
			settings.setOutputTableRowDefinitionAfter(true);

			HeaderFooter footer = new HeaderFooter(new Phrase(""), true);
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
							source.getFieldIndexByName("nombre")).toString()
					+ ")", subtitleFont);
			reportSubtitle.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(reportSubtitle);

			Paragraph aldea = new Paragraph();
			aldea.add(new Chunk("\n\n\n\n\nCódigo de la comunidad:",
					normalBoldTextFont));
			aldea.add(new Chunk(" "
					+ source.getFieldValue(0,
							source.getFieldIndexByName("cod_com")).toString()));
			aldea.setAlignment(Paragraph.ALIGN_RIGHT);
			document.add(aldea);

			document.newPage();

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
					source.getFieldIndexByName("nombre")).toString()));
			list.add(new ListItem(phrase));
			phrase = new Phrase();
			phrase
					.add(new Chunk("Código de la comunidad: ",
							normalBoldTextFont));
			phrase.add(new Chunk(source.getFieldValue(nRow,
					source.getFieldIndexByName("cod_com")).toString()));
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
					source.getFieldIndexByName("departa")).toString()));
			list.add(new ListItem(phrase));
			phrase = new Phrase();
			phrase.add(new Chunk("Municipio: ", normalBoldTextFont));
			phrase.add(new Chunk(source.getFieldValue(nRow,
					source.getFieldIndexByName("municip")).toString()));
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
			phrase.add(new Chunk("Número de familias: ", normalBoldTextFont));
			phrase.add(new Chunk(source.getFieldValue(nRow,
					source.getFieldIndexByName("n_fam")).toString()));
			list.add(new ListItem(phrase));
			phrase = new Phrase();
			phrase.add(new Chunk("Número de viviendas: ", normalBoldTextFont));
			phrase.add(new Chunk("comunidad.n_viv"));
			list.add(new ListItem(phrase));
			phrase = new Phrase();
			phrase.add(new Chunk("Número de habitantes: ", normalBoldTextFont));
			phrase.add(new Chunk(source.getFieldValue(nRow,
					source.getFieldIndexByName("n_habit")).toString()));
			list.add(new ListItem(phrase));
			sectionBody.add(list);
			document.add(sectionBody);

			// Population table
			Table table = new Table(5);
			RtfCell cell;
			// we add a cell with colspan 3
			cell = new RtfCell(new Phrase("Nº Habitantes", normalBoldTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("< 18 años", normalBoldTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("18-60 años", normalBoldTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("> 60 años", normalBoldTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("TOTAL", normalBoldTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			table.addCell(new Phrase("Mujeres", normalBoldTextFont));
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell(new Phrase("Hombres", normalBoldTextFont));
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell(new Phrase("TOTAL", normalBoldTextFont));
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell("");
			document.add(table);

			// Section 1.3
			sectionTitle = new Paragraph(
					"\n\n1.3. Economía, principal fuente de ingresos de las familias de la comunidad:\n",
					subsectionFont);
			document.add(sectionTitle);
			sectionBody = new Paragraph();
			list = new List();
			list.setIndentationLeft(20);
			list.setSymbolIndent(15);
			list.setListSymbol(new Chunk("\u2022", normalBoldTextFont));
			phrase = new Phrase();
			phrase.add(new Chunk("Actividad agropecuaria (%): ",
					normalBoldTextFont));
			list.add(new ListItem(phrase));
			phrase = new Phrase();
			phrase.add(new Chunk("Industria, construcción, maquilas (%): ",
					normalBoldTextFont));
			list.add(new ListItem(phrase));
			phrase = new Phrase();
			phrase.add(new Chunk("Sector servicios (%): ", normalBoldTextFont));
			list.add(new ListItem(phrase));
			sectionBody.add(list);
			document.add(sectionBody);

			// Section 2
			sectionTitle = new Paragraph("\n\n2. ASPECTOS ORGANIZATIVOS:\n",
					sectionFont);
			document.add(sectionTitle);

			// Section 2.1
			sectionTitle = new Paragraph("2.1. Centro comunal de reuniones:\n",
					subsectionFont);
			document.add(sectionTitle);
			sectionBody = new Paragraph();
			list = new List();
			list.setIndentationLeft(20);
			list.setSymbolIndent(15);
			list.setListSymbol(new Chunk("\u2022", normalBoldTextFont));
			phrase = new Phrase();
			phrase.add(new Chunk("Existe centro comunal de reuniones: ",
					normalBoldTextFont));
			SelectableDataSource shpSource;
			if (source.getFieldValue(nRow,
					source.getFieldIndexByName("creunion")).toString().equals(
					"true")) {
				phrase.add(new Chunk("Sí", normalItalicTextFont));
				sectionBody.add(phrase);
				document.add(sectionBody);
				sectionBody = new Paragraph();
				layer = Utils.getFlyrVect(view, "centros_reuniones");
				shpSource = this.layer.getSource().getRecordset();
				for (int i = 0; i < shpSource.getRowCount(); i++) {
					if (shpSource
							.getFieldValue(i,
									shpSource.getFieldIndexByName("cod_com"))
							.toString()
							.equals(
									source
											.getFieldValue(
													nRow,
													source
															.getFieldIndexByName("cod_com"))
											.toString())) {
						phrase = new Phrase();
						phrase.add(new Chunk(
								"Nombre del centro de reuniones: ",
								normalBoldTextFont));
						phrase.add(new Chunk(shpSource.getFieldValue(i,
								shpSource.getFieldIndexByName("nom_creu"))
								.toString()));
						list.add(new ListItem(phrase));
						phrase = new Phrase();
						phrase
								.add(new Chunk("Dirección: ",
										normalBoldTextFont));
						phrase.add(new Chunk(shpSource.getFieldValue(i,
								shpSource.getFieldIndexByName("direccion"))
								.toString()));
						list.add(new ListItem(phrase));
						phrase = new Phrase();
						phrase.add(new Chunk("Responsable: ",
								normalBoldTextFont));
						phrase.add(new Chunk(shpSource.getFieldValue(i,
								shpSource.getFieldIndexByName("responsa"))
								.toString()));
						list.add(new ListItem(phrase));
						sectionBody.add(list);
					}
				}
			} else {
				phrase.add(new Chunk("No", normalItalicTextFont));
				sectionBody.add(phrase);
				document.add(sectionBody);
				sectionBody = new Paragraph();
				phrase = new Phrase();
				phrase.add(new Chunk("Nombre del centro de reuniones: ",
						normalBoldTextFont));
				phrase.add(new Chunk(""));
				list.add(new ListItem(phrase));
				phrase = new Phrase();
				phrase.add(new Chunk("Dirección: ", normalBoldTextFont));
				phrase.add(new Chunk(""));
				list.add(new ListItem(phrase));
				phrase = new Phrase();
				phrase.add(new Chunk("Responsable: ", normalBoldTextFont));
				phrase.add(new Chunk(""));
				list.add(new ListItem(phrase));
				sectionBody.add(list);
			}
			document.add(sectionBody);

			// Section 2.2
			sectionTitle = new Paragraph("2.2. Organizaciones de base:\n",
					subsectionFont);
			document.add(sectionTitle);

			// Base organizations table
			table = new Table(3);
			// we add a cell with colspan 3
			cell = new RtfCell(new Phrase("NOMBRE ORGANIZACION"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("FUNCIONES DE LA ORGANIZACION"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("PERSONA RESPONSABLE"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			String dbfName = "organizacion_base";
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
				FieldDescription[] descriptions = dbfSource
						.getFieldsDescription();
				HashMap<String, Integer> indexes = new HashMap<String, Integer>();
				for (int i = 0; i < descriptions.length; i++) {
					indexes.put(descriptions[i].getFieldName(), i);
				}
				for (int i = 0; i < dbfSource.getRowCount(); i++) {
					IRowEdited row = dbfSource.getRow(i);
					if (row
							.getAttribute(indexes.get("cod_com"))
							.toString()
							.equals(
									source
											.getFieldValue(
													nRow,
													source
															.getFieldIndexByName("cod_com"))
											.toString())) {
						cell = new RtfCell(new Phrase(row.getAttribute(
								indexes.get("cod_com")).toString(),
								normalBoldTextFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						cell = new RtfCell(new Phrase(row.getAttribute(
								indexes.get("fun_org")).toString(),
								normalBoldTextFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						cell = new RtfCell(new Phrase(row.getAttribute(
								indexes.get("per_res")).toString(),
								normalBoldTextFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
					}

				}
			} else {
				cell = new RtfCell(new Phrase("(organizacion_base.nom_org)",
						normalBoldTextFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new RtfCell(new Phrase("(organizacion_base.fun_org)",
						normalBoldTextFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new RtfCell(new Phrase("(organizacion_base.per_res)",
						normalBoldTextFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
			}
			document.add(table);

			// Section 2.3
			sectionTitle = new Paragraph("\n\n2.3. Presencia institucional:\n",
					subsectionFont);
			document.add(sectionTitle);

			// Inst. presence table
			table = new Table(3);
			// we add a cell with colspan 3
			cell = new RtfCell(new Phrase("NOMBRE ORGANIZACION"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("FUNCIONES DE LA ORGANIZACION"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("PERSONA RESPONSABLE"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
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
				FieldDescription[] descriptions = dbfSource
						.getFieldsDescription();
				HashMap<String, Integer> indexes = new HashMap<String, Integer>();
				for (int i = 0; i < descriptions.length; i++) {
					indexes.put(descriptions[i].getFieldName(), i);
				}
				for (int i = 0; i < dbfSource.getRowCount(); i++) {
					IRowEdited row = dbfSource.getRow(i);
					if (row
							.getAttribute(indexes.get("cod_com"))
							.toString()
							.equals(
									source
											.getFieldValue(
													nRow,
													source
															.getFieldIndexByName("cod_com"))
											.toString())) {
						cell = new RtfCell(new Phrase(row.getAttribute(
								indexes.get("cod_com")).toString(),
								normalBoldTextFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						cell = new RtfCell(new Phrase(row.getAttribute(
								indexes.get("fun_org")).toString(),
								normalBoldTextFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						cell = new RtfCell(new Phrase(row.getAttribute(
								indexes.get("per_res")).toString(),
								normalBoldTextFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
					}

				}
			} else {
				cell = new RtfCell(
						new Phrase("(presencia_institucional.nom_org)",
								normalBoldTextFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new RtfCell(
						new Phrase("(presencia_institucional.fun_org)",
								normalBoldTextFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new RtfCell(
						new Phrase("(presencia_institucional.per_res)",
								normalBoldTextFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
			}
			document.add(table);

			// Section 3
			sectionTitle = new Paragraph("\n\n\n3. SERVICIOS BÁSICOS:\n",
					sectionFont);
			document.add(sectionTitle);

			// Section 3.1
			sectionTitle = new Paragraph("3.1. Servicios básicos:\n",
					subsectionFont);
			document.add(sectionTitle);
			sectionBody = new Paragraph();
			list = new List();
			list.setIndentationLeft(20);
			list.setSymbolIndent(15);
			list.setListSymbol(new Chunk("\u2022", normalBoldTextFont));
			phrase = new Phrase();
			phrase.add(new Chunk("Electricidad: ", normalBoldTextFont));
			if (source.getFieldValue(nRow,
					source.getFieldIndexByName("luz_elec")).toString().equals(
					"true")) {
				phrase.add(new Chunk("Sí", normalItalicTextFont));
			} else {
				phrase.add(new Chunk("No", normalItalicTextFont));
			}
			list.add(new ListItem(phrase));
			phrase = new Phrase();
			phrase.add(new Chunk("Agua potable: ", normalBoldTextFont));
			if (source.getFieldValue(nRow,
					source.getFieldIndexByName("agua_pot")).toString().equals(
					"true")) {
				phrase.add(new Chunk("Sí", normalItalicTextFont));
			} else {
				phrase.add(new Chunk("No", normalItalicTextFont));
			}
			list.add(new ListItem(phrase));
			phrase = new Phrase();
			phrase.add(new Chunk("Alcantarillado: ", normalBoldTextFont));
			if (source.getFieldValue(nRow,
					source.getFieldIndexByName("alcantar")).toString().equals(
					"true")) {
				phrase.add(new Chunk("Sí", normalItalicTextFont));
			} else {
				phrase.add(new Chunk("No", normalItalicTextFont));
			}
			list.add(new ListItem(phrase));
			phrase = new Phrase();
			phrase.add(new Chunk("Teléfono fijo: ", normalBoldTextFont));
			if (source.getFieldValue(nRow,
					source.getFieldIndexByName("tfn_fijo")).toString().equals(
					"true")) {
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
			phrase = new Phrase();
			phrase.add(new Chunk("Existe centro educativo: ",
					normalBoldTextFont));
			if (!source.getFieldValue(nRow,
					source.getFieldIndexByName("cent_jard")).toString().equals(
					"true")
					&& !source.getFieldValue(nRow,
							source.getFieldIndexByName("cent_esc")).toString()
							.equals("true")
					&& !source.getFieldValue(nRow,
							source.getFieldIndexByName("cent_ccyd")).toString()
							.equals("true")) {
				phrase.add(new Chunk("No", normalItalicTextFont));
				sectionBody.add(phrase);
				document.add(sectionBody);
				sectionBody = new Paragraph();
				phrase = new Phrase();
				phrase.add(new Chunk("Nombre del centro educativo: ",
						normalBoldTextFont));
				phrase.add(new Chunk(""));
				list.add(new ListItem(phrase));
				phrase = new Phrase();
				phrase.add(new Chunk("Tipo de centro educativo: ",
						normalBoldTextFont));
				phrase.add(new Chunk(""));
				list.add(new ListItem(phrase));
				phrase = new Phrase();
				phrase.add(new Chunk("Número de niños: ", normalBoldTextFont));
				phrase.add(new Chunk(""));
				list.add(new ListItem(phrase));
				phrase = new Phrase();
				phrase.add(new Chunk("Número de niñas: ", normalBoldTextFont));
				phrase.add(new Chunk(""));
				list.add(new ListItem(phrase));
				phrase = new Phrase();
				phrase.add(new Chunk("Índice de deserción (%): ",
						normalBoldTextFont));
				phrase.add(new Chunk(""));
				list.add(new ListItem(phrase));
				phrase = new Phrase();
				phrase.add(new Chunk("Merienda escolar: ", normalBoldTextFont));
				phrase.add(new Chunk(""));
				list.add(new ListItem(phrase));
				sectionBody.add(list);
			} else {
				phrase.add(new Chunk("Sí", normalItalicTextFont));
				sectionBody.add(phrase);
				document.add(sectionBody);
				sectionBody = new Paragraph();
				layer = Utils.getFlyrVect(view, "centros_educativos");
				shpSource = this.layer.getSource().getRecordset();
				for (int i = 0; i < shpSource.getRowCount(); i++) {
					if (shpSource
							.getFieldValue(i,
									shpSource.getFieldIndexByName("cod_com"))
							.toString()
							.equals(
									source
											.getFieldValue(
													nRow,
													source
															.getFieldIndexByName("cod_com"))
											.toString())) {
						phrase = new Phrase();
						phrase.add(new Chunk("Nombre del centro educativo: ",
								normalBoldTextFont));
						phrase.add(new Chunk(shpSource.getFieldValue(i,
								shpSource.getFieldIndexByName("nom_cedu"))
								.toString()));
						list.add(new ListItem(phrase));
						phrase = new Phrase();
						phrase.add(new Chunk("Tipo de centro educativo: ",
								normalBoldTextFont));
						phrase.add(new Chunk(shpSource.getFieldValue(i,
								shpSource.getFieldIndexByName("tipo_cedu"))
								.toString()));
						list.add(new ListItem(phrase));
						phrase = new Phrase();
						phrase.add(new Chunk("Número de niños: ",
								normalBoldTextFont));
						phrase.add(new Chunk(shpSource.getFieldValue(i,
								shpSource.getFieldIndexByName("n_ninhos"))
								.toString()));
						list.add(new ListItem(phrase));
						phrase = new Phrase();
						phrase.add(new Chunk("Número de niñas: ",
								normalBoldTextFont));
						phrase.add(new Chunk(shpSource.getFieldValue(i,
								shpSource.getFieldIndexByName("n_ninhas"))
								.toString()));
						list.add(new ListItem(phrase));
						phrase = new Phrase();
						phrase.add(new Chunk("Índice de deserción (%): ",
								normalBoldTextFont));
						phrase.add(new Chunk(shpSource.getFieldValue(i,
								shpSource.getFieldIndexByName("i_deserc"))
								.toString()));
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
			phrase = new Phrase();
			phrase
					.add(new Chunk("Existe centro de salud: ",
							normalBoldTextFont));
			if (source
					.getFieldValue(nRow, source.getFieldIndexByName("csalud"))
					.toString().equals("true")) {
				phrase.add(new Chunk("Sí", normalItalicTextFont));
				sectionBody.add(phrase);
				document.add(sectionBody);
				sectionBody = new Paragraph();
				layer = Utils.getFlyrVect(view, "centros_salud");
				shpSource = this.layer.getSource().getRecordset();
				for (int i = 0; i < shpSource.getRowCount(); i++) {
					if (shpSource
							.getFieldValue(i,
									shpSource.getFieldIndexByName("cod_com"))
							.toString()
							.equals(
									source
											.getFieldValue(
													nRow,
													source
															.getFieldIndexByName("cod_com"))
											.toString())) {
						phrase = new Phrase();
						phrase.add(new Chunk("Nombre del centro de salud: ",
								normalBoldTextFont));
						phrase.add(new Chunk(shpSource.getFieldValue(i,
								shpSource.getFieldIndexByName("nom_csalud"))
								.toString()));
						list.add(new ListItem(phrase));
						phrase = new Phrase();
						phrase.add(new Chunk(
								"Infecciones respiratorias agudas: ",
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
						phrase.add(new Chunk("Infecciones de la piel: ",
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
						phrase.add(new Chunk("Infecciones intestinales: ",
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
						phrase.add(new Chunk("Infecciones vectoriales: ",
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
				phrase.add(new Chunk("No", normalItalicTextFont));
				sectionBody.add(phrase);
				document.add(sectionBody);
				sectionBody = new Paragraph();
				phrase = new Phrase();
				phrase.add(new Chunk("Nombre del centro de salud: ",
						normalBoldTextFont));
				phrase.add(new Chunk(""));
				list.add(new ListItem(phrase));
				phrase = new Phrase();
				phrase.add(new Chunk("Infecciones respiratorias agudas: ",
						normalBoldTextFont));
				phrase.add(new Chunk(""));
				list.add(new ListItem(phrase));
				phrase = new Phrase();
				phrase.add(new Chunk("Infecciones de la piel: ",
						normalBoldTextFont));
				phrase.add(new Chunk(""));
				list.add(new ListItem(phrase));
				phrase = new Phrase();
				phrase.add(new Chunk("Infecciones intestinales: ",
						normalBoldTextFont));
				phrase.add(new Chunk(""));
				list.add(new ListItem(phrase));
				phrase = new Phrase();
				phrase.add(new Chunk("Infecciones vectoriales: ",
						normalBoldTextFont));
				phrase.add(new Chunk(""));
				list.add(new ListItem(phrase));
				sectionBody.add(list);
			}
			document.add(sectionBody);

			// Section 3.4
			sectionTitle = new Paragraph(
					"3.4. Infraestructura viaria y transporte:\n",
					subsectionFont);
			document.add(sectionTitle);
			sectionBody = new Paragraph();
			phrase = new Phrase();
			phrase
					.add(new Chunk("Estado de la carretera:",
							normalBoldTextFont));
			sectionBody.add(phrase);
			document.add(sectionBody);
			sectionBody = new Paragraph();
			list = new List();
			list.setIndentationLeft(20);
			list.setSymbolIndent(15);
			list.setListSymbol(new Chunk("\u2022", normalBoldTextFont));
			phrase = new Phrase();
			phrase.add(new Chunk("Verano: ", normalTextFont));
			list.add(new ListItem(phrase));
			phrase = new Phrase();
			phrase.add(new Chunk("Invierno: ", normalTextFont));
			list.add(new ListItem(phrase));
			sectionBody.add(list);
			document.add(sectionBody);
			sectionBody = new Paragraph();
			phrase = new Phrase();
			phrase.add(new Chunk("Acceso al transporte público:",
					normalBoldTextFont));
			sectionBody.add(phrase);
			document.add(sectionBody);
			sectionBody = new Paragraph();
			list = new List();
			list.setIndentationLeft(20);
			list.setSymbolIndent(15);
			list.setListSymbol(new Chunk("\u2022", normalBoldTextFont));
			phrase = new Phrase();
			phrase.add(new Chunk("Frecuencia: ", normalTextFont));
			list.add(new ListItem(phrase));
			phrase = new Phrase();
			phrase.add(new Chunk(
					"Distancia de la comunidad a la parada más cercana: ",
					normalTextFont));
			list.add(new ListItem(phrase));
			sectionBody.add(list);
			document.add(sectionBody);
			sectionBody = new Paragraph();
			phrase = new Phrase();
			phrase.add(new Chunk(
					"Tiempo hasta la cabecera municipal/mercado (pie):",
					normalBoldTextFont));
			sectionBody.add(phrase);
			document.add(sectionBody);

			// Section 4
			sectionTitle = new Paragraph("\n\n\n4. AGRICULTURA:\n", sectionFont);
			document.add(sectionTitle);

			// Section 4.1
			sectionTitle = new Paragraph(
					"4.1. Resumen de cultivos en la comunidad:\n",
					subsectionFont);
			document.add(sectionTitle);

			// Farming table
			table = new Table(2);
			// we add a cell with colspan 3
			cell = new RtfCell(new Phrase("Área"));
			table.addCell(cell);
			cell = new RtfCell(new Phrase("Tipo de cultivo"));
			table.addCell(cell);
			dbfName = "cultivos";
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
				FieldDescription[] descriptions = dbfSource
						.getFieldsDescription();
				HashMap<String, Integer> indexes = new HashMap<String, Integer>();
				for (int i = 0; i < descriptions.length; i++) {
					indexes.put(descriptions[i].getFieldName(), i);
				}
				for (int i = 0; i < dbfSource.getRowCount(); i++) {
					IRowEdited row = dbfSource.getRow(i);
					if (row
							.getAttribute(indexes.get("cod_com"))
							.toString()
							.equals(
									source
											.getFieldValue(
													nRow,
													source
															.getFieldIndexByName("cod_com"))
											.toString())) {
						cell = new RtfCell(new Phrase(row.getAttribute(
								indexes.get("area")).toString(),
								normalBoldTextFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
						cell = new RtfCell(new Phrase(row.getAttribute(
								indexes.get("tipo_cul")).toString(),
								normalBoldTextFont));
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(cell);
					}

				}
			} else {
				cell = new RtfCell(new Phrase("(cultivos.area)",
						normalBoldTextFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				cell = new RtfCell(new Phrase("(cultivos.tipo_cul)",
						normalBoldTextFont));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
			}
			document.add(table);

			// Section 4.2
			sectionTitle = new Paragraph("\n\n4.2. Producción agrícola:\n",
					subsectionFont);
			document.add(sectionTitle);
			sectionBody = new Paragraph();
			list = new List();
			list.setIndentationLeft(20);
			list.setSymbolIndent(15);
			list.setListSymbol(new Chunk("\u2022", normalBoldTextFont));
			phrase = new Phrase();
			phrase.add(new Chunk(
					"Número de familias que cultivan por cuenta propia: ",
					normalBoldTextFont));
			list.add(new ListItem(phrase));
			phrase = new Phrase();
			phrase.add(new Chunk(
					"Número de familias que cultivan por cuenta ajena: ",
					normalBoldTextFont));
			list.add(new ListItem(phrase));
			sectionBody.add(list);
			document.add(sectionBody);
			sectionBody = new Paragraph();
			list = new List();
			list.setIndentationLeft(20);
			list.setSymbolIndent(15);
			list.setListSymbol(new Chunk("\u2022", normalBoldTextFont));
			phrase = new Phrase();
			phrase.add(new Chunk(
					"Número de familias propietarias de la tierra: ",
					normalBoldTextFont));
			list.add(new ListItem(phrase));
			phrase = new Phrase();
			phrase.add(new Chunk(
					"Número de familias que arriendan la tierra: ",
					normalBoldTextFont));
			list.add(new ListItem(phrase));
			sectionBody.add(list);
			document.add(sectionBody);
			sectionBody = new Paragraph();
			list = new List();
			list.setIndentationLeft(20);
			list.setSymbolIndent(15);
			list.setListSymbol(new Chunk("\u2022", normalBoldTextFont));
			phrase = new Phrase();
			phrase.add(new Chunk("Área media cultivada por familia: ",
					normalBoldTextFont));
			list.add(new ListItem(phrase));
			sectionBody.add(list);
			document.add(sectionBody);

			// Section 5
			sectionTitle = new Paragraph("\n\n5. MEDIO FÍSICO:\n", sectionFont);
			document.add(sectionTitle);

			// Section 5.1
			sectionTitle = new Paragraph("5.1. Relieve (%):\n", subsectionFont);
			document.add(sectionTitle);
			sectionBody = new Paragraph();
			list = new List();
			list.setIndentationLeft(20);
			list.setSymbolIndent(15);
			list.setListSymbol(new Chunk("\u2022", normalBoldTextFont));
			phrase = new Phrase();
			phrase.add(new Chunk("Llano: ", normalBoldTextFont));
			list.add(new ListItem(phrase));
			phrase = new Phrase();
			phrase.add(new Chunk("Pendiente media: ", normalBoldTextFont));
			list.add(new ListItem(phrase));
			phrase = new Phrase();
			phrase.add(new Chunk("Pendiente elevada: ", normalBoldTextFont));
			list.add(new ListItem(phrase));
			sectionBody.add(list);
			document.add(sectionBody);

			// Section 5.2
			sectionTitle = new Paragraph("\n\n5.2. Tipo de vegetación (%):\n",
					subsectionFont);
			document.add(sectionTitle);

			// Inst. presence table
			table = new Table(2);
			// we add a cell with colspan 3
			cell = new RtfCell(new Phrase("TIPO DE VEGETACIÓN"));
			table.addCell(cell);
			cell = new RtfCell(new Phrase("PORCENTAJE VEGETACIÓN COMUNIDAD"));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("Coníferas"));
			table.addCell(cell);
			table.addCell("");
			cell = new RtfCell(new Phrase("Mangle"));
			table.addCell(cell);
			table.addCell("");
			cell = new RtfCell(new Phrase("Latifoliado"));
			table.addCell(cell);
			table.addCell("");
			cell = new RtfCell(new Phrase("Matorral"));
			table.addCell(cell);
			table.addCell("");
			cell = new RtfCell(new Phrase("Mixto"));
			table.addCell(cell);
			table.addCell("");
			cell = new RtfCell(new Phrase("Pasto"));
			table.addCell(cell);
			table.addCell("");
			cell = new RtfCell(new Phrase("Cultivos"));
			table.addCell(cell);
			table.addCell("");
			cell = new RtfCell(new Phrase("Otros"));
			table.addCell(cell);
			table.addCell("");
			document.add(table);

			// Section 5.3
			sectionTitle = new Paragraph("\n\n5.3. Otros:\n", subsectionFont);
			document.add(sectionTitle);
			sectionBody = new Paragraph();
			list = new List();
			list.setIndentationLeft(20);
			list.setSymbolIndent(15);
			list.setListSymbol(new Chunk("\u2022", normalBoldTextFont));
			phrase = new Phrase();
			phrase.add(new Chunk("Pedregosidad: ", normalBoldTextFont));
			list.add(new ListItem(phrase));
			phrase = new Phrase();
			phrase.add(new Chunk("Estado de deforestación: ",
					normalBoldTextFont));
			list.add(new ListItem(phrase));
			phrase = new Phrase();
			phrase.add(new Chunk(
					"Tendencia de avance de la frontera agrícola: ",
					normalBoldTextFont));
			list.add(new ListItem(phrase));
			phrase = new Phrase();
			phrase.add(new Chunk("Usos del suelo: ", normalBoldTextFont));
			list.add(new ListItem(phrase));
			phrase = new Phrase();
			phrase.add(new Chunk("Tipo de área: ", normalBoldTextFont));
			list.add(new ListItem(phrase));
			phrase = new Phrase();
			phrase.add(new Chunk("Frecuencia de incendios forestales: ",
					normalBoldTextFont));
			list.add(new ListItem(phrase));
			sectionBody.add(list);
			document.add(sectionBody);

			// Section 6
			sectionTitle = new Paragraph("\n\n6. AGUA:\n", sectionFont);
			document.add(sectionTitle);

			sectionBody = new Paragraph();
			phrase = new Phrase();
			phrase.add(new Chunk(
					"Diagnóstico de la Red Hidrográfica de la Comunidad:",
					normalBoldTextFont));
			sectionBody.add(phrase);
			document.add(sectionBody);

			// Community Hidr. Network Diagnostic presence table
			table = new Table(3);
			// we add a cell with colspan 3
			cell = new RtfCell(new Phrase("Descripción corriente de agua",
					normalBoldTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("Nombre", normalBoldTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("Nombre", normalBoldTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("1. Dirección", normalBoldTextFont));
			table.addCell(cell);
			table.addCell("");
			table.addCell("");
			cell = new RtfCell(new Phrase("2. Longitud (m.)",
					normalBoldTextFont));
			table.addCell(cell);
			table.addCell("");
			table.addCell("");
			cell = new RtfCell(new Phrase("3. Protegidos (m.)",
					normalBoldTextFont));
			table.addCell(cell);
			table.addCell("");
			table.addCell("");
			cell = new RtfCell(new Phrase("4. Desprotegidos (m.)",
					normalBoldTextFont));
			table.addCell(cell);
			table.addCell("");
			table.addCell("");
			cell = new RtfCell(
					new Phrase("5. Contaminadas", normalBoldTextFont));
			table.addCell(cell);
			table.addCell("");
			table.addCell("");
			cell = new RtfCell(new Phrase("6. No contaminadas",
					normalBoldTextFont));
			table.addCell(cell);
			table.addCell("");
			table.addCell("");
			cell = new RtfCell(new Phrase("7. Tipo de corriente",
					normalBoldTextFont));
			table.addCell(cell);
			table.addCell("");
			table.addCell("");
			cell = new RtfCell(new Phrase("8. Usos de la corriente",
					normalBoldTextFont));
			table.addCell(cell);
			table.addCell("");
			table.addCell("");
			document.add(table);

			sectionBody = new Paragraph();
			phrase = new Phrase();
			phrase
					.add(new Chunk(
							"\n\nDiagnóstico de los pozos de agua (artesanales o artesianos) de la Comunidad:",
							normalBoldTextFont));
			sectionBody.add(phrase);
			document.add(sectionBody);

			// Water Wells Diagnostic
			table = new Table(6);
			// we add a cell with colspan 3
			cell = new RtfCell(new Phrase("Pozos", normalBoldTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(
					new Phrase("Profundidad (m)", normalBoldTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("Calidad del agua",
					normalBoldTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("Cantidad de agua (m3)",
					normalBoldTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("Tiempo de recuperación (h)",
					normalBoldTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("Usos", normalBoldTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("Núm. 1", normalBoldTextFont));
			table.addCell(cell);
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell("");
			cell = new RtfCell(new Phrase("Núm. 2", normalBoldTextFont));
			table.addCell(cell);
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell("");
			cell = new RtfCell(new Phrase("Núm. 3", normalBoldTextFont));
			table.addCell(cell);
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell("");
			document.add(table);

			sectionBody = new Paragraph();
			phrase = new Phrase();
			phrase
					.add(new Chunk(
							"\n\nDiagnóstico de los reservorios de agua de la Comunidad:",
							normalBoldTextFont));
			sectionBody.add(phrase);
			document.add(sectionBody);

			// Water Wells Diagnostic
			table = new Table(6);
			// we add a cell with colspan 3
			cell = new RtfCell(new Phrase("Tipo de reservorio",
					normalBoldTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("Ubicación", normalBoldTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("Capacidad (m3)", normalBoldTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("Calidad del agua",
					normalBoldTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(
					new Phrase("Origen del agua", normalBoldTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("Usos", normalBoldTextFont));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new RtfCell(new Phrase("Núm. 1", normalBoldTextFont));
			table.addCell(cell);
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell("");
			cell = new RtfCell(new Phrase("Núm. 2", normalBoldTextFont));
			table.addCell(cell);
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell("");
			cell = new RtfCell(new Phrase("Núm. 3", normalBoldTextFont));
			table.addCell(cell);
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell("");
			table.addCell("");
			document.add(table);

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
}// Class

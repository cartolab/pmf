package es.udc.cartolab.gvsig.pmf.reports.housing;

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
import java.util.HashMap;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
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
	private SelectableDataSource plotSource;
	private int nComRow;
	private int nPlotRow;

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
			12, RtfFont.STYLE_ITALIC);
	private final RtfFont tableTitleTextFont = new RtfFont("Century Gothic",
			11, RtfFont.STYLE_BOLD);
	private final RtfFont sectionFont = new RtfFont("Century Gothic", 14,
			RtfFont.STYLE_BOLD);
	private final RtfFont subsectionFont = new RtfFont("Century Gothic", 12,
			RtfFont.STYLE_BOLD);

	public RtfHousingReport(int nRow, SelectableDataSource source,
			String fileName, BaseView view) {

		this.view = view;
		this.source = source;
		this.nRow = nRow;
		this.fileName = fileName;
		document = new Document();
		try {
			layer = Utils.getFlyrVect(view, "comunidad");
			comSource = this.layer.getSource().getRecordset();
			layer = Utils.getFlyrVect(view, "parcela");
			plotSource = this.layer.getSource().getRecordset();

			String codCom = source.getFieldValue(nRow,
					source.getFieldIndexByName("cod_com")).toString();

			String codViv = source.getFieldValue(nRow,
					source.getFieldIndexByName("cod_viv")).toString();

			for (int i = 0; i < comSource.getRowCount(); i++) {
				if (comSource.getFieldValue(i,
						comSource.getFieldIndexByName("cod_com")).toString()
						.equals(codCom)) {
					nComRow = i;
					break;
				}
			}

			for (int i = 0; i < plotSource.getRowCount(); i++) {
				if (plotSource.getFieldValue(i,
						plotSource.getFieldIndexByName("cod_viv")).toString()
						.equals(codViv)) {
					nPlotRow = i;
					break;
				}
			}

			startDocument();
			createSection1();
			createSection2();

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
		reportSubtitle.add(new Chunk("\n\t\tA�o: ", subtitleBoldFont));
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
		sectionBody.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(sectionBody);

		// Base organizations table
		Table table = new Table(2);
		RtfCell cell = new RtfCell(new Phrase("SERVICIO", tableTitleTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("PRESENTE EN LA COMUNIDAD",
				tableTitleTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Luz el�ctrica", normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (comSource.getFieldValue(nComRow,
				comSource.getFieldIndexByName("luz_elec")).toString().equals(
				"true")) {
			cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Agua potable", normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (comSource.getFieldValue(nComRow,
				comSource.getFieldIndexByName("agua_pot")).toString().equals(
				"true")) {
			cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Alcantarillado", normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (comSource.getFieldValue(nComRow,
				comSource.getFieldIndexByName("alcantar")).toString().equals(
				"true")) {
			cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Telefon�a fija", normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (comSource.getFieldValue(nComRow,
				comSource.getFieldIndexByName("tfn_fijo")).toString().equals(
				"true")) {
			cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Centro de salud", normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (comSource.getFieldValue(nComRow,
				comSource.getFieldIndexByName("csalud")).toString().equals(
				"true")) {
			cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

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
		sectionBody.add(new Chunk(" y direcci�n en "));
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

		// Inhabitants table
		table = new Table(2);
		cell = new RtfCell(new Phrase("Hombres mayores de 5 a�os",
				normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(nRow,
				source.getFieldIndexByName("n_hombr")).toString(),
				normalTextFont)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Mujeres mayores de 5 a�os",
				normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(nRow,
				source.getFieldIndexByName("n_mujer")).toString(),
				normalTextFont)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Ni�os menores de 5 a�os",
				normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(nRow,
				source.getFieldIndexByName("n_ninhos")).toString(),
				normalTextFont)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Ni�as menores de 5 a�os",
				normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(nRow,
				source.getFieldIndexByName("n_ninhas")).toString(),
				normalTextFont)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Total", normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		table.addCell(cell);
		cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(nRow,
				source.getFieldIndexByName("n_personas")).toString(),
				normalBoldTextFont)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		table.addCell(cell);
		cell = new RtfCell(
				new Phrase("Mujeres embarazadas", normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(nRow,
				source.getFieldIndexByName("n_embaraz")).toString(),
				normalTextFont)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
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
			if (source.getFieldValue(nRow,
					source.getFieldIndexByName("ganaderia")).toString().equals(
					"true")) {
				if (source.getFieldValue(nRow,
						source.getFieldIndexByName("comercio")).toString()
						.equals("true")) {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("hay_ot_act"))
							.toString().equals("true")) {
						sectionBody.add(new Chunk(
								"la agricultura, la ganader�a, el comercio y ",
								normalBoldTextFont));
						sectionBody.add(new Chunk(source.getFieldValue(nRow,
								source.getFieldIndexByName("otras_ac"))
								.toString(), normalBoldTextFont));
					} else {
						sectionBody.add(new Chunk(
								"la agricultura, la ganader�a y el comercio",
								normalBoldTextFont));
					}

				} else {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("hay_ot_act"))
							.toString().equals("true")) {
						sectionBody.add(new Chunk(
								"la agricultura, la ganader�a y ",
								normalBoldTextFont));
						sectionBody.add(new Chunk(source.getFieldValue(nRow,
								source.getFieldIndexByName("otras_ac"))
								.toString(), normalBoldTextFont));

					} else {
						sectionBody.add(new Chunk(
								"la agricultura y la ganader�a",
								normalBoldTextFont));
					}

				}

			} else {
				if (source.getFieldValue(nRow,
						source.getFieldIndexByName("comercio")).toString()
						.equals("true")) {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("hay_ot_act"))
							.toString().equals("true")) {
						sectionBody.add(new Chunk(
								"la agricultura, el comercio y ",
								normalBoldTextFont));
						sectionBody.add(new Chunk(source.getFieldValue(nRow,
								source.getFieldIndexByName("otras_ac"))
								.toString(), normalBoldTextFont));
					} else {
						sectionBody.add(new Chunk(
								"la agricultura y el comercio",
								normalBoldTextFont));
					}

				} else {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("hay_ot_act"))
							.toString().equals("true")) {
						sectionBody.add(new Chunk("la agricultura y ",
								normalBoldTextFont));
						sectionBody.add(new Chunk(source.getFieldValue(nRow,
								source.getFieldIndexByName("otras_ac"))
								.toString(), normalBoldTextFont));
					} else {
						sectionBody.add(new Chunk("la agricultura",
								normalBoldTextFont));
					}

				}

			}

		} else {
			if (source.getFieldValue(nRow,
					source.getFieldIndexByName("ganaderia")).toString().equals(
					"true")) {
				if (source.getFieldValue(nRow,
						source.getFieldIndexByName("comercio")).toString()
						.equals("true")) {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("hay_ot_act"))
							.toString().equals("true")) {
						sectionBody.add(new Chunk(
								"la ganader�a, el comercio y ",
								normalBoldTextFont));
						sectionBody.add(new Chunk(source.getFieldValue(nRow,
								source.getFieldIndexByName("otras_ac"))
								.toString(), normalBoldTextFont));
					} else {
						sectionBody.add(new Chunk("la ganader�a y el comercio",
								normalBoldTextFont));
					}

				} else {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("hay_ot_act"))
							.toString().equals("true")) {
						sectionBody.add(new Chunk("la ganader�a y ",
								normalBoldTextFont));
						sectionBody.add(new Chunk(source.getFieldValue(nRow,
								source.getFieldIndexByName("otras_ac"))
								.toString(), normalBoldTextFont));
					} else {
						sectionBody.add(new Chunk("la ganader�a",
								normalBoldTextFont));
					}

				}

			} else {
				if (source.getFieldValue(nRow,
						source.getFieldIndexByName("comercio")).toString()
						.equals("true")) {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("hay_ot_act"))
							.toString().equals("true")) {
						sectionBody.add(new Chunk("el comercio y ",
								normalBoldTextFont));
						sectionBody.add(new Chunk(source.getFieldValue(nRow,
								source.getFieldIndexByName("otras_ac"))
								.toString(), normalBoldTextFont));
					} else {
						sectionBody.add(new Chunk("el comercio",
								normalBoldTextFont));
					}
				} else {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("hay_ot_act"))
							.toString().equals("true")) {
						sectionBody.add(new Chunk(source.getFieldValue(nRow,
								source.getFieldIndexByName("otras_ac"))
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
			if (source.getFieldValue(nRow,
					source.getFieldIndexByName("e_temporal")).toString()
					.equals("true")) {
				if (source.getFieldValue(nRow,
						source.getFieldIndexByName("e_perman")).toString()
						.equals("true")) {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("hay_ot_ing"))
							.toString().equals("true")) {
						sectionBody
								.add(new Chunk(
										"remesas, empleo temporal, empleo permanente y ",
										normalBoldTextFont));
						sectionBody.add(new Chunk(source.getFieldValue(nRow,
								source.getFieldIndexByName("otros_ing"))
								.toString().toLowerCase(), normalBoldTextFont));
					} else {
						sectionBody.add(new Chunk(
								"remesas, empleo temporal y empleo permanente",
								normalBoldTextFont));
					}

				} else {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("hay_ot_ing"))
							.toString().equals("true")) {
						sectionBody.add(new Chunk(
								"remesas, empleo temporal y ",
								normalBoldTextFont));
						sectionBody.add(new Chunk(source.getFieldValue(nRow,
								source.getFieldIndexByName("otros_ing"))
								.toString().toLowerCase(), normalBoldTextFont));

					} else {
						sectionBody.add(new Chunk("remesas y empleo temporal",
								normalBoldTextFont));
					}

				}

			} else {
				if (source.getFieldValue(nRow,
						source.getFieldIndexByName("e_perman")).toString()
						.equals("true")) {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("hay_ot_ing"))
							.toString().equals("true")) {
						sectionBody.add(new Chunk(
								"remesas, empleo permanente y ",
								normalBoldTextFont));
						sectionBody.add(new Chunk(source.getFieldValue(nRow,
								source.getFieldIndexByName("otros_ing"))
								.toString().toLowerCase(), normalBoldTextFont));
					} else {
						sectionBody.add(new Chunk(
								"remesas y empleo permanente",
								normalBoldTextFont));
					}

				} else {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("hay_ot_ing"))
							.toString().equals("true")) {
						sectionBody.add(new Chunk("remesas y ",
								normalBoldTextFont));
						sectionBody.add(new Chunk(source.getFieldValue(nRow,
								source.getFieldIndexByName("otros_ing"))
								.toString().toLowerCase(), normalBoldTextFont));
					} else {
						sectionBody
								.add(new Chunk("remesas", normalBoldTextFont));
					}

				}

			}

		} else {
			if (source.getFieldValue(nRow,
					source.getFieldIndexByName("e_temporal")).toString()
					.equals("true")) {
				if (source.getFieldValue(nRow,
						source.getFieldIndexByName("e_perman")).toString()
						.equals("true")) {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("hay_ot_ing"))
							.toString().equals("true")) {
						sectionBody.add(new Chunk(
								"empleo temporal, empleo permanente y ",
								normalBoldTextFont));
						sectionBody.add(new Chunk(source.getFieldValue(nRow,
								source.getFieldIndexByName("otros_ing"))
								.toString().toLowerCase(), normalBoldTextFont));
					} else {
						sectionBody.add(new Chunk(
								"empleo temporal y empleo permanente",
								normalBoldTextFont));
					}

				} else {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("hay_ot_ing"))
							.toString().equals("true")) {
						sectionBody.add(new Chunk("empleo temporal y ",
								normalBoldTextFont));
						sectionBody.add(new Chunk(source.getFieldValue(nRow,
								source.getFieldIndexByName("otros_ing"))
								.toString().toLowerCase(), normalBoldTextFont));
					} else {
						sectionBody.add(new Chunk("empleo temporal",
								normalBoldTextFont));
					}

				}

			} else {
				if (source.getFieldValue(nRow,
						source.getFieldIndexByName("e_perman")).toString()
						.equals("true")) {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("hay_ot_ing"))
							.toString().equals("true")) {
						sectionBody.add(new Chunk("empleo permanente y ",
								normalBoldTextFont));
						sectionBody.add(new Chunk(source.getFieldValue(nRow,
								source.getFieldIndexByName("otros_ing"))
								.toString().toLowerCase(), normalBoldTextFont));
					} else {
						sectionBody.add(new Chunk("empleo permanente",
								normalBoldTextFont));
					}
				} else {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("hay_ot_ing"))
							.toString().equals("true")) {
						sectionBody.add(new Chunk(source.getFieldValue(nRow,
								source.getFieldIndexByName("otros_ing"))
								.toString().toLowerCase(), normalBoldTextFont));
					} else {
						sectionBody.add(new Chunk("ning�n sitio",
								normalBoldTextFont));
					}

				}

			}

		}
		cell = new RtfCell(new Phrase("�Vivienda propiedad de la familia?",
				normalBoldTextFont));
		table.addCell(cell);
		if (source
				.getFieldValue(nRow, source.getFieldIndexByName("estatus_vi"))
				.toString().toLowerCase().equals("propia")) {
			cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		sectionBody.add(new Chunk(".\n\n\tLa vivienda de la familia es ",
				normalTextFont));
		if (!source.getFieldValue(nRow,
				source.getFieldIndexByName("estatus_vi")).toString()
				.toLowerCase().contains("selecciona una opci�n")) {
			if (source.getFieldValue(nRow,
					source.getFieldIndexByName("estatus_vi")).toString()
					.toLowerCase().equals("otro")) {
				sectionBody.add(new Chunk(source.getFieldValue(nRow,
						source.getFieldIndexByName("ot_stat_vi")).toString()
						.toLowerCase()));
			} else {
				sectionBody.add(new Chunk(source.getFieldValue(nRow,
						source.getFieldIndexByName("estatus_vi")).toString()
						.toLowerCase()));
			}
		} else {
			sectionBody
					.add(new Chunk(" [no se ha seleccionado ninguna opci�n]"));
		}
		if (source
				.getFieldValue(nRow, source.getFieldIndexByName("estatus_vi"))
				.toString().toLowerCase().equals("propia")) {
			if (source.getFieldValue(nRow,
					source.getFieldIndexByName("pro_viv")).toString()
					.toLowerCase().equals("true")) {
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
		table = new Table(2);
		cell = new RtfCell(new Phrase("Ubicaci�n (coordenadas)",
				normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
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

		document.add(table);

		// Materials table
		table = new Table(2);
		cell = new RtfCell(new Phrase("MATERIALES DE LA VIVIENDA",
				tableTitleTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		cell.setColspan(2);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Material de las paredes",
				normalBoldTextFont));
		table.addCell(cell);
		if (!source
				.getFieldValue(nRow, source.getFieldIndexByName("mat_pared"))
				.toString().toLowerCase().contains("selecciona una opci�n")) {
			if (source.getFieldValue(nRow,
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
			cell = new RtfCell(new Chunk("[----]"));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Material del techo", normalBoldTextFont));
		table.addCell(cell);
		if (!source
				.getFieldValue(nRow, source.getFieldIndexByName("mat_techo"))
				.toString().toLowerCase().contains("selecciona una opci�n")) {
			if (source.getFieldValue(nRow,
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
			cell = new RtfCell(new Chunk("[----]"));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Material del piso", normalBoldTextFont));
		table.addCell(cell);
		if (!source.getFieldValue(nRow, source.getFieldIndexByName("mat_piso"))
				.toString().toLowerCase().contains("selecciona una opci�n")) {
			if (source.getFieldValue(nRow,
					source.getFieldIndexByName("mat_piso")).toString()
					.toLowerCase().equals("otro")) {
				cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(
						nRow, source.getFieldIndexByName("ot_mat_pi"))
						.toString(), normalTextFont)));
			} else {
				cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(
						nRow, source.getFieldIndexByName("mat_piso"))
						.toString(), normalTextFont)));
			}
		} else {
			cell = new RtfCell(new Chunk("[----]"));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		document.add(table);

		// Public services table
		table = new Table(2);
		cell = new RtfCell(new Phrase("SERVICIO", tableTitleTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("PRESENTE EN LA VIVIENDA",
				tableTitleTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Luz el�ctrica", normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (source.getFieldValue(nRow, source.getFieldIndexByName("luz_elec"))
				.toString().equals("true")) {
			cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Agua potable", normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (source.getFieldValue(nRow, source.getFieldIndexByName("agua_pot"))
				.toString().equals("true")) {
			cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Alcantarillado", normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (source.getFieldValue(nRow, source.getFieldIndexByName("alcantar"))
				.toString().equals("true")) {
			cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Telefon�a fija", normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (source.getFieldValue(nRow, source.getFieldIndexByName("telefono"))
				.toString().equals("true")) {
			cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Alumbrado p�blico", normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (source.getFieldValue(nRow, source.getFieldIndexByName("alum_publ"))
				.toString().equals("true")) {
			cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		document.add(table);

		// Materials table
		table = new Table(2);
		cell = new RtfCell(new Phrase(
				"INFRAESTRUCTURAS B�SICAS DE LA VIVIENDA", tableTitleTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		cell.setColspan(2);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Letrina", normalBoldTextFont));
		table.addCell(cell);
		if (source.getFieldValue(nRow, source.getFieldIndexByName("letrina"))
				.toString().toLowerCase().equals("true")) {
			cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Cocina mejorada", normalBoldTextFont));
		table.addCell(cell);
		if (source.getFieldValue(nRow, source.getFieldIndexByName("coc_mejor"))
				.toString().toLowerCase().equals("true")) {
			cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Filtro de aguas grises",
				normalBoldTextFont));
		table.addCell(cell);
		if (source.getFieldValue(nRow, source.getFieldIndexByName("filtro_ag"))
				.toString().toLowerCase().equals("true")) {
			cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase(
				"Sistemas de almacenamiento de grano b�sicos",
				normalBoldTextFont));
		table.addCell(cell);

		if (source.getFieldValue(nRow, source.getFieldIndexByName("silos"))
				.toString().equals("true")) {
			if (source.getFieldValue(nRow,
					source.getFieldIndexByName("trojas_mej")).toString()
					.equals("true")) {
				if (source.getFieldValue(nRow,
						source.getFieldIndexByName("sacos")).toString().equals(
						"true")) {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("ramadas")).toString()
							.equals("true")) {
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
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("ramadas")).toString()
							.equals("true")) {
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
				if (source.getFieldValue(nRow,
						source.getFieldIndexByName("sacos")).toString().equals(
						"true")) {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("ramadas")).toString()
							.equals("true")) {
						cell = new RtfCell(new Chunk(
								"Silos met�licos, sacos y ramadas",
								normalTextFont));
					} else {
						cell = new RtfCell(new Chunk("Silos met�licos y sacos",
								normalTextFont));
					}

				} else {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("ramadas")).toString()
							.equals("true")) {
						cell = new RtfCell(new Chunk(
								"Silos met�licos y ramadas", normalTextFont));
					} else {
						cell = new RtfCell(new Chunk("Silos met�licos",
								normalTextFont));
					}

				}

			}

		} else {
			if (source.getFieldValue(nRow,
					source.getFieldIndexByName("trojas_mej")).toString()
					.equals("true")) {
				if (source.getFieldValue(nRow,
						source.getFieldIndexByName("sacos")).toString().equals(
						"true")) {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("ramadas")).toString()
							.equals("true")) {
						cell = new RtfCell(new Chunk(
								"Trojas mejoradas, sacos y ramadas",
								normalTextFont));
					} else {
						cell = new RtfCell(new Chunk(
								"Trojas mejoradas y sacos", normalTextFont));
					}

				} else {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("ramadas")).toString()
							.equals("true")) {
						cell = new RtfCell(new Chunk(
								"Trojas mejoradas y ramadas", normalTextFont));
					} else {
						cell = new RtfCell(new Chunk("Trojas mejoradas",
								normalTextFont));
					}

				}

			} else {
				if (source.getFieldValue(nRow,
						source.getFieldIndexByName("sacos")).toString().equals(
						"true")) {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("ramadas")).toString()
							.equals("true")) {
						cell = new RtfCell(new Chunk("Sacos y ramadas",
								normalTextFont));
					} else {
						cell = new RtfCell(new Chunk("Sacos", normalTextFont));
					}
				} else {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("ramadas")).toString()
							.equals("true")) {
						cell = new RtfCell(new Chunk("Ramadas", normalTextFont));
					} else {
						cell = new RtfCell(new Chunk("Ninguno", normalTextFont));
					}

				}

			}

		}

		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		document.add(table);
	}

	private void createSection2() throws DocumentException, ReadDriverException {

		// Section 1
		Paragraph sectionTitle = new Paragraph(
				"\n\n2. DATOS DE LA FINCA E INFRAESTRUCTURA PRODUCTIVA\n",
				sectionFont);
		document.add(sectionTitle);
		Paragraph sectionBody = new Paragraph();
		sectionBody.add(new Chunk(
				"\tLa finca de la familia productora tiene una ",
				normalTextFont));
		sectionBody.add(new Chunk("superficie total ", normalBoldTextFont));
		sectionBody.add(new Chunk("de "));
		sectionBody.add(new Chunk(plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("area_tot")).toString(),
				normalBoldTextFont));
		sectionBody.add(new Chunk(" manzanas, siendo la ", normalTextFont));
		sectionBody
				.add(new Chunk("superficie cultivable ", normalBoldTextFont));
		sectionBody.add(new Chunk("de "));
		sectionBody.add(new Chunk(plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("area_cul")).toString(),
				normalBoldTextFont));
		sectionBody.add(new Chunk(
				" manzanas. La finca pertenece a la familia por "));
		if (plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("legal_par")).toString()
				.toLowerCase().equals("otro")) {
			sectionBody.add(new Chunk(plotSource.getFieldValue(nPlotRow,
					plotSource.getFieldIndexByName("ot_legal_p")).toString(),
					normalTextFont));
		} else {
			sectionBody.add(new Chunk(plotSource.getFieldValue(nPlotRow,
					plotSource.getFieldIndexByName("legal_par")).toString(),
					normalTextFont));
		}
		sectionBody.add(new Chunk("."));
		sectionBody.setAlignment(Element.ALIGN_JUSTIFIED);

		document.add(sectionBody);

		// Subsection
		sectionTitle = new Paragraph("\n\nCARACTER�STICAS DE LA PARCELA:\n",
				subsectionFont);
		document.add(sectionTitle);

		sectionBody = new Paragraph();
		sectionBody.add(new Chunk("\tLa finca tiene una pendiente media del ",
				normalTextFont));
		sectionBody.add(new Chunk(plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("pendiente")).toString()));
		sectionBody.add(new Chunk(", el suelo es principalmente "));
		if (plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("tip_suelo")).toString()
				.toLowerCase().equals("otro")) {
			sectionBody.add(new Chunk(plotSource.getFieldValue(nPlotRow,
					plotSource.getFieldIndexByName("ot_tip_su")).toString()
					.toLowerCase()));
		} else {
			sectionBody.add(new Chunk(plotSource.getFieldValue(nPlotRow,
					plotSource.getFieldIndexByName("tip_suelo")).toString()
					.toLowerCase()));
		}
		sectionBody.add(new Chunk(" y se encuentra en un nivel "));
		sectionBody.add(new Chunk(plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("deg_suelo")).toString()));
		sectionBody.add(new Chunk(" de degradaci�n.\n\t"));

		if (plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("cerca")).toString().equals(
				"true")) {
			if (plotSource.getFieldValue(nPlotRow,
					plotSource.getFieldIndexByName("b_vivas")).toString()
					.equals("true")) {
				if (plotSource.getFieldValue(nPlotRow,
						plotSource.getFieldIndexByName("b_muertas")).toString()
						.equals("true")) {
					if (plotSource.getFieldValue(nPlotRow,
							plotSource.getFieldIndexByName("hay_ot_cer"))
							.toString().equals("true")) {
						sectionBody
								.add(new Chunk(
										"La parcela cuenta adem�s con barreras vivas, barreras muertas y ",
										normalTextFont));
						sectionBody.add(new Chunk(plotSource.getFieldValue(
								nPlotRow,
								plotSource.getFieldIndexByName("ot_cerca"))
								.toString().toLowerCase(), normalTextFont));
						sectionBody.add(new Chunk(" como cercado.\n\n",
								normalTextFont));
					} else {
						sectionBody
								.add(new Chunk(
										"La parcela cuenta adem�s con barreras vivas y barreras muertas como cercado.\n\n",
										normalTextFont));
					}

				} else {
					if (plotSource.getFieldValue(nPlotRow,
							plotSource.getFieldIndexByName("hay_ot_cer"))
							.toString().equals("true")) {
						sectionBody
								.add(new Chunk(
										"La parcela cuenta adem�s con barreras vivas y ",
										normalTextFont));
						sectionBody.add(new Chunk(plotSource.getFieldValue(
								nPlotRow,
								plotSource.getFieldIndexByName("ot_cerca"))
								.toString().toLowerCase(), normalTextFont));
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
				if (plotSource.getFieldValue(nPlotRow,
						plotSource.getFieldIndexByName("b_muertas")).toString()
						.equals("true")) {
					if (plotSource.getFieldValue(nPlotRow,
							plotSource.getFieldIndexByName("hay_ot_cer"))
							.toString().equals("true")) {
						sectionBody
								.add(new Chunk(
										"La parcela cuenta adem�s con barreras muertas y ",
										normalTextFont));
						sectionBody.add(new Chunk(plotSource.getFieldValue(
								nPlotRow,
								plotSource.getFieldIndexByName("ot_cerca"))
								.toString().toLowerCase(), normalTextFont));
						sectionBody.add(new Chunk(" como cercado.\n\n",
								normalTextFont));
					} else {
						sectionBody
								.add(new Chunk(
										"La parcela cuenta adem�s con barreras muertas como cercado.\n\n",
										normalTextFont));
					}

				} else {
					if (plotSource.getFieldValue(nPlotRow,
							plotSource.getFieldIndexByName("hay_ot_cer"))
							.toString().equals("true")) {
						sectionBody
								.add(new Chunk("La parcela cuenta adem�s con ",
										normalTextFont));
						sectionBody.add(new Chunk(plotSource.getFieldValue(
								nPlotRow,
								plotSource.getFieldIndexByName("ot_cerca"))
								.toString().toLowerCase(), normalTextFont));
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

		if (!plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("poz_pro")).toString().equals(
				"true")) {
			sectionBody.add(new Chunk(" no dispone de fuentes de riego, ",
					normalTextFont));
		} else {
			if (plotSource.getFieldValue(nPlotRow,
					plotSource.getFieldIndexByName("rio")).toString().equals(
					"true")) {
				if (plotSource.getFieldValue(nPlotRow,
						plotSource.getFieldIndexByName("nacimiento"))
						.toString().equals("true")) {
					if (plotSource.getFieldValue(nPlotRow,
							plotSource.getFieldIndexByName("poz_com"))
							.toString().equals("true")) {
						if (plotSource.getFieldValue(nPlotRow,
								plotSource.getFieldIndexByName("reserv"))
								.toString().equals("true")) {
							if (plotSource.getFieldValue(nPlotRow,
									plotSource.getFieldIndexByName("poz_pro"))
									.toString().equals("true")) {
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
							if (plotSource.getFieldValue(nPlotRow,
									plotSource.getFieldIndexByName("poz_pro"))
									.toString().equals("true")) {
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
						if (plotSource.getFieldValue(nPlotRow,
								plotSource.getFieldIndexByName("reserv"))
								.toString().equals("true")) {
							if (plotSource.getFieldValue(nPlotRow,
									plotSource.getFieldIndexByName("poz_pro"))
									.toString().equals("true")) {
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
							if (plotSource.getFieldValue(nPlotRow,
									plotSource.getFieldIndexByName("poz_pro"))
									.toString().equals("true")) {
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
					if (plotSource.getFieldValue(nPlotRow,
							plotSource.getFieldIndexByName("poz_com"))
							.toString().equals("true")) {
						if (plotSource.getFieldValue(nPlotRow,
								plotSource.getFieldIndexByName("reserv"))
								.toString().equals("true")) {
							if (plotSource.getFieldValue(nPlotRow,
									plotSource.getFieldIndexByName("poz_pro"))
									.toString().equals("true")) {
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
							if (plotSource.getFieldValue(nPlotRow,
									plotSource.getFieldIndexByName("poz_pro"))
									.toString().equals("true")) {
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
						if (plotSource.getFieldValue(nPlotRow,
								plotSource.getFieldIndexByName("reserv"))
								.toString().equals("true")) {
							if (plotSource.getFieldValue(nPlotRow,
									plotSource.getFieldIndexByName("poz_pro"))
									.toString().equals("true")) {
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
							if (plotSource.getFieldValue(nPlotRow,
									plotSource.getFieldIndexByName("poz_pro"))
									.toString().equals("true")) {
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
				if (plotSource.getFieldValue(nPlotRow,
						plotSource.getFieldIndexByName("nacimiento"))
						.toString().equals("true")) {
					if (plotSource.getFieldValue(nPlotRow,
							plotSource.getFieldIndexByName("poz_com"))
							.toString().equals("true")) {
						if (plotSource.getFieldValue(nPlotRow,
								plotSource.getFieldIndexByName("reserv"))
								.toString().equals("true")) {
							if (plotSource.getFieldValue(nPlotRow,
									plotSource.getFieldIndexByName("poz_pro"))
									.toString().equals("true")) {
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
							if (plotSource.getFieldValue(nPlotRow,
									plotSource.getFieldIndexByName("poz_pro"))
									.toString().equals("true")) {
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
						if (plotSource.getFieldValue(nPlotRow,
								plotSource.getFieldIndexByName("reserv"))
								.toString().equals("true")) {
							if (plotSource.getFieldValue(nPlotRow,
									plotSource.getFieldIndexByName("poz_pro"))
									.toString().equals("true")) {
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
							if (plotSource.getFieldValue(nPlotRow,
									plotSource.getFieldIndexByName("poz_pro"))
									.toString().equals("true")) {
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
					if (plotSource.getFieldValue(nPlotRow,
							plotSource.getFieldIndexByName("poz_com"))
							.toString().equals("true")) {
						if (plotSource.getFieldValue(nPlotRow,
								plotSource.getFieldIndexByName("reserv"))
								.toString().equals("true")) {
							if (plotSource.getFieldValue(nPlotRow,
									plotSource.getFieldIndexByName("poz_pro"))
									.toString().equals("true")) {
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
							if (plotSource.getFieldValue(nPlotRow,
									plotSource.getFieldIndexByName("poz_pro"))
									.toString().equals("true")) {
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
						if (plotSource.getFieldValue(nPlotRow,
								plotSource.getFieldIndexByName("reserv"))
								.toString().equals("true")) {
							if (plotSource.getFieldValue(nPlotRow,
									plotSource.getFieldIndexByName("poz_pro"))
									.toString().equals("true")) {
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
							if (plotSource.getFieldValue(nPlotRow,
									plotSource.getFieldIndexByName("poz_pro"))
									.toString().equals("true")) {
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

		if (plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("fuente_co")).toString().equals(
				"true")) {
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
		sectionBody.add(new Chunk(plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("d_fue_tan")).toString(),
				normalTextFont));
		sectionBody
				.add(new Chunk(
						" m., mientras que la distancia desde el tanque al huerto es de ",
						normalTextFont));
		sectionBody.add(new Chunk(plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("d_tan_hue")).toString(),
				normalTextFont));
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

		// Base organizations table
		Table table = new Table(3);
		RtfCell cell = new RtfCell(new Phrase("Tipo", tableTitleTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Presente en la finca",
				tableTitleTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Descripci�n", tableTitleTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Pr�cticas conservacionistas",
				normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("prac_conse")).toString()
				.equals("true")) {
			cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase(plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("c_conse")).toString(),
				normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Abono org�nico", normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("uso_aborg")).toString().equals(
				"true")) {
			cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase(plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("c_aborg")).toString(),
				normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Insecticidas org�nicos",
				normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("insect_org")).toString()
				.equals("true")) {
			cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase(plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("c_insect")).toString(),
				normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Plaguicidas qu�micos",
				normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("uso_quim")).toString().equals(
				"true")) {
			cell = new RtfCell(new Phrase("S�", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase(plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("c_quim")).toString(),
				normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
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

		table = new Table(3);
		cell = new RtfCell(new Phrase("Cultivo", tableTitleTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("�rea cultivada", tableTitleTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Volumen producido", tableTitleTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(Color.LIGHT_GRAY);
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
				darkColor = !darkColor;
				IRowEdited row = dbfSource.getRow(i);
				if (row.getAttribute(indexes.get("cod_com")).toString().equals(
						plotSource.getFieldValue(nPlotRow,
								plotSource.getFieldIndexByName("cod_com"))
								.toString())) {
					hasRows = true;
					cell = new RtfCell(new Phrase(row.getAttribute(
							indexes.get("tipo_cul")).toString(),
							normalBoldTextFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					cell = new RtfCell(
							new Phrase(row.getAttribute(indexes.get("area"))
									.toString(), normalBoldTextFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					cell = new RtfCell(new Phrase(row.getAttribute(
							indexes.get("vol_proc")).toString(),
							normalBoldTextFont));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
				}

			}
			if (!hasRows) {
				darkColor = !darkColor;
				cell = new RtfCell(new Phrase(""));
				table.addCell(cell);
				cell = new RtfCell(new Phrase(""));
				table.addCell(cell);
				cell = new RtfCell(new Phrase(""));
				table.addCell(cell);
			}
		} else {
			darkColor = !darkColor;
			cell = new RtfCell(new Phrase(""));
			table.addCell(cell);
			cell = new RtfCell(new Phrase(""));
			table.addCell(cell);
			cell = new RtfCell(new Phrase(""));
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

		// Base organizations table
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
		table.addCell(cell);
		cell = new RtfCell(new Phrase(plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("p_riego")).toString(),
				normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Huerto familiar", normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase(plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("p_huerto")).toString(),
				normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Cocina mejorada", normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase(plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("p_coc_mejo")).toString(),
				normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Filtro para aguas grises",
				normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase(plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("p_filtroag")).toString(),
				normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Construcci�n de gallinero",
				normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase(plotSource.getFieldValue(nPlotRow,
				plotSource.getFieldIndexByName("p_galline")).toString(),
				normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		document.add(table);

	}

}// Class

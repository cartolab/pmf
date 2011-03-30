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
			12, RtfFont.STYLE_ITALIC);
	private final RtfFont tableTitleTextFont = new RtfFont("Century Gothic",
			11, RtfFont.STYLE_BOLD);
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
		cell = new RtfCell(new Phrase("Luz eléctrica", normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (comSource.getFieldValue(nComRow,
				comSource.getFieldIndexByName("luz_elec")).toString().equals(
				"true")) {
			cell = new RtfCell(new Phrase("Sí", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Agua potable", normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (comSource.getFieldValue(nComRow,
				comSource.getFieldIndexByName("agua_pot")).toString().equals(
				"true")) {
			cell = new RtfCell(new Phrase("Sí", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Alcantarillado", normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (comSource.getFieldValue(nComRow,
				comSource.getFieldIndexByName("alcantar")).toString().equals(
				"true")) {
			cell = new RtfCell(new Phrase("Sí", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Telefonía fija", normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (comSource.getFieldValue(nComRow,
				comSource.getFieldIndexByName("tfn_fijo")).toString().equals(
				"true")) {
			cell = new RtfCell(new Phrase("Sí", normalItalicTextFont));
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
			cell = new RtfCell(new Phrase("Sí", normalItalicTextFont));
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
						" años de edad, en el momento de la realización de este plan, con número de identificación ",
						normalTextFont));
		sectionBody.add(new Chunk(source.getFieldValue(nRow,
				source.getFieldIndexByName("nid_produ")).toString(),
				normalBoldTextFont));
		sectionBody.add(new Chunk(" y dirección en "));
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
		cell = new RtfCell(new Phrase("Hombres mayores de 5 años",
				normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(nRow,
				source.getFieldIndexByName("n_hombr")).toString(),
				normalTextFont)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Mujeres mayores de 5 años",
				normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(nRow,
				source.getFieldIndexByName("n_mujer")).toString(),
				normalTextFont)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Niños menores de 5 años",
				normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(nRow,
				source.getFieldIndexByName("n_ninhos")).toString(),
				normalTextFont)));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Niñas menores de 5 años",
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
		sectionBody.add(new Chunk("principales actividades económicas ",
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
								"la agricultura, la ganadería, el comercio y ",
								normalBoldTextFont));
						sectionBody.add(new Chunk(source.getFieldValue(nRow,
								source.getFieldIndexByName("otras_ac"))
								.toString(), normalBoldTextFont));
					} else {
						sectionBody.add(new Chunk(
								"la agricultura, la ganadería y el comercio",
								normalBoldTextFont));
					}

				} else {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("hay_ot_act"))
							.toString().equals("true")) {
						sectionBody.add(new Chunk(
								"la agricultura, la ganadería y ",
								normalBoldTextFont));
						sectionBody.add(new Chunk(source.getFieldValue(nRow,
								source.getFieldIndexByName("otras_ac"))
								.toString(), normalBoldTextFont));

					} else {
						sectionBody.add(new Chunk(
								"la agricultura y la ganadería",
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
								"la ganadería, el comercio y ",
								normalBoldTextFont));
						sectionBody.add(new Chunk(source.getFieldValue(nRow,
								source.getFieldIndexByName("otras_ac"))
								.toString(), normalBoldTextFont));
					} else {
						sectionBody.add(new Chunk("la ganadería y el comercio",
								normalBoldTextFont));
					}

				} else {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("hay_ot_act"))
							.toString().equals("true")) {
						sectionBody.add(new Chunk("la ganadería y ",
								normalBoldTextFont));
						sectionBody.add(new Chunk(source.getFieldValue(nRow,
								source.getFieldIndexByName("otras_ac"))
								.toString(), normalBoldTextFont));
					} else {
						sectionBody.add(new Chunk("la ganadería",
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
								.toString(), normalBoldTextFont));
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
								.toString(), normalBoldTextFont));

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
								.toString(), normalBoldTextFont));
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
								.toString(), normalBoldTextFont));
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
								.toString(), normalBoldTextFont));
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
								.toString(), normalBoldTextFont));
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
								.toString(), normalBoldTextFont));
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
								.toString(), normalBoldTextFont));
					} else {
						sectionBody.add(new Chunk("ningún sitio",
								normalBoldTextFont));
					}

				}

			}

		}
		sectionBody.add(new Chunk(
				".\n\n\tLa vivienda de la familia se caracteriza por:",
				normalTextFont));
		sectionBody.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(sectionBody);

		// Coordinates table
		table = new Table(2);
		cell = new RtfCell(new Phrase("Ubicación (coordenadas)",
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
		cell = new RtfCell(new Phrase("¿Vivienda propiedad de la familia?",
				normalBoldTextFont));
		table.addCell(cell);
		if (source
				.getFieldValue(nRow, source.getFieldIndexByName("estatus_vi"))
				.toString().toLowerCase().equals("propia")) {
			cell = new RtfCell(new Phrase("Sí", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Material de las paredes",
				normalBoldTextFont));
		table.addCell(cell);
		if (source.getFieldValue(nRow, source.getFieldIndexByName("mat_pared"))
				.toString().toLowerCase().equals("otro")) {
			cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(nRow,
					source.getFieldIndexByName("ot_mat_pa")).toString(),
					normalTextFont)));
		} else {
			cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(nRow,
					source.getFieldIndexByName("mat_pared")).toString(),
					normalTextFont)));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Material del techo", normalBoldTextFont));
		table.addCell(cell);
		if (source.getFieldValue(nRow, source.getFieldIndexByName("mat_techo"))
				.toString().toLowerCase().equals("otro")) {
			cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(nRow,
					source.getFieldIndexByName("ot_mat_te")).toString(),
					normalTextFont)));
		} else {
			cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(nRow,
					source.getFieldIndexByName("mat_techo")).toString(),
					normalTextFont)));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Material del piso", normalBoldTextFont));
		table.addCell(cell);
		if (source.getFieldValue(nRow, source.getFieldIndexByName("mat_piso"))
				.toString().toLowerCase().equals("otro")) {
			cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(nRow,
					source.getFieldIndexByName("ot_mat_pi")).toString(),
					normalTextFont)));
		} else {
			cell = new RtfCell(new Phrase(new Chunk(source.getFieldValue(nRow,
					source.getFieldIndexByName("mat_piso")).toString(),
					normalTextFont)));
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
		cell = new RtfCell(new Phrase("Luz eléctrica", normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (source.getFieldValue(nRow, source.getFieldIndexByName("luz_elec"))
				.toString().equals("true")) {
			cell = new RtfCell(new Phrase("Sí", normalItalicTextFont));
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
			cell = new RtfCell(new Phrase("Sí", normalItalicTextFont));
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
			cell = new RtfCell(new Phrase("Sí", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Telefonía fija", normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (source.getFieldValue(nRow, source.getFieldIndexByName("telefono"))
				.toString().equals("true")) {
			cell = new RtfCell(new Phrase("Sí", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Alumbrado público", normalBoldTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		if (source.getFieldValue(nRow, source.getFieldIndexByName("alum_publ"))
				.toString().equals("true")) {
			cell = new RtfCell(new Phrase("Sí", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		document.add(table);

		// Materials table
		table = new Table(2);
		cell = new RtfCell(new Phrase(
				"INFRAESTRUCTURAS BÁSICAS DE LA VIVIENDA", tableTitleTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBackgroundColor(Color.LIGHT_GRAY);
		cell.setColspan(2);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Letrina", normalBoldTextFont));
		table.addCell(cell);
		if (source.getFieldValue(nRow, source.getFieldIndexByName("letrina"))
				.toString().toLowerCase().equals("true")) {
			cell = new RtfCell(new Phrase("Sí", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase("Cocina mejorada", normalBoldTextFont));
		table.addCell(cell);
		if (source.getFieldValue(nRow, source.getFieldIndexByName("coc_mejor"))
				.toString().toLowerCase().equals("true")) {
			cell = new RtfCell(new Phrase("Sí", normalItalicTextFont));
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
			cell = new RtfCell(new Phrase("Sí", normalItalicTextFont));
		} else {
			cell = new RtfCell(new Phrase("No", normalItalicTextFont));
		}
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		cell = new RtfCell(new Phrase(
				"Sistemas de almacenamiento de grano básicos",
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
										"Silos metálicos, trojas mejoradas, sacos y ramadas",
										normalTextFont));
					} else {
						cell = new RtfCell(new Chunk(
								"Silos metálicos, trojas mejoradas y sacos",
								normalTextFont));
					}

				} else {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("ramadas")).toString()
							.equals("true")) {
						cell = new RtfCell(new Chunk(
								"Silos metálicos, trojas mejoradas y ramadas",
								normalTextFont));

					} else {
						cell = new RtfCell(new Chunk(
								"Silos metálicos y trojas mejoradas",
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
								"Silos metálicos, sacos y ramadas",
								normalTextFont));
					} else {
						cell = new RtfCell(new Chunk("Silos metálicos y sacos",
								normalTextFont));
					}

				} else {
					if (source.getFieldValue(nRow,
							source.getFieldIndexByName("ramadas")).toString()
							.equals("true")) {
						cell = new RtfCell(new Chunk(
								"Silos metálicos y ramadas", normalTextFont));
					} else {
						cell = new RtfCell(new Chunk("Silos metálicos",
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

}// Class

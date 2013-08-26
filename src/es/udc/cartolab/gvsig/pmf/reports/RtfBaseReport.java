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

import java.awt.Color;

import org.apache.commons.lang3.ArrayUtils;

import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.style.RtfFont;
import com.lowagie.text.rtf.table.RtfCell;

import es.udc.cartolab.gvsig.pmf.utils.PmfConstants;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public abstract class RtfBaseReport {

    protected int nRow;
    protected String fileName;
    protected boolean darkColor = true;
    protected SelectableDataSource source;
    protected final DBSession session = DBSession.getCurrentSession();

    protected final Document document = new Document();

    // FONT STYLES
    protected final RtfFont titleFont = new RtfFont("Century Gothic", 24,
	    RtfFont.STYLE_BOLD);
    protected final RtfFont subtitleFont = new RtfFont("Century Gothic", 24,
	    RtfFont.STYLE_NONE);
    protected final RtfFont normalBoldTextFont = new RtfFont("Century Gothic",
	    10, RtfFont.STYLE_BOLD);
    protected final RtfFont normalTextFont = new RtfFont("Century Gothic", 10,
	    RtfFont.STYLE_NONE);
    protected final RtfFont normalItalicTextFont = new RtfFont(
	    "Century Gothic", 10, RtfFont.STYLE_ITALIC);
    protected final RtfFont tableTitleTextFont = new RtfFont("Century Gothic",
	    9, RtfFont.STYLE_NONE);
    protected final RtfFont sectionFont = new RtfFont("Century Gothic", 14,
	    RtfFont.STYLE_BOLD);
    protected final RtfFont subsectionFont = new RtfFont("Century Gothic", 11,
	    RtfFont.STYLE_BOLD);

    protected void setCellColor(RtfCell cell) {
	if (darkColor) {
	    cell.setBackgroundColor(Color.LIGHT_GRAY);
	} else {
	    cell.setBackgroundColor(new Color(242, 242, 242));
	}
    }

    protected List getFieldsList(String tableName, String foreignKeyName,
	    String foreignKeyValue, String[] fieldNames,
	    String[] boolFieldNames, String[] fieldHeaders) {

	List list = new List();
	list.setIndentationLeft(20);
	list.setSymbolIndent(15);
	list.setListSymbol(new Chunk("\u2022", normalBoldTextFont));

	try {
	    String[][] rows = session.getTable(tableName,
		    PmfConstants.DATA_SCHEMA,
		    ArrayUtils.addAll(fieldNames, boolFieldNames), "WHERE "
			    + foreignKeyName + " = '" + foreignKeyValue + "'",
		    new String[0], false);
	    for (String[] row : rows) {
		int len = fieldNames.length;
		Phrase phrase;
		for (int i = 0; i < len; i++) {
		    phrase = new Phrase();
		    phrase.add(new Chunk(fieldHeaders[i], normalBoldTextFont));
		    phrase.add(new Chunk(row[i], normalTextFont));
		    list.add(new ListItem(phrase));
		}
		for (int i = len, leng = (boolFieldNames.length + len); i < leng; i++) {
		    phrase = new Phrase();
		    phrase.add(new Chunk(fieldHeaders[i], normalBoldTextFont));
		    if (row[i].equals("t")) {
			phrase.add(new Chunk("Sí", normalItalicTextFont));
		    } else {
			phrase.add(new Chunk("No", normalItalicTextFont));
		    }
		    list.add(new ListItem(phrase));
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return list;
    }

    protected Table getFieldsTable(String tableName, String foreignKeyName,
	    String foreignKeyValue, String[] fieldNames,
	    String[] boolFieldNames, String[] fieldHeaders)
	    throws DocumentException {

	Table table = new Table(fieldNames.length + boolFieldNames.length);
	darkColor = true;

	for (String header : fieldHeaders) {
	    RtfCell cell = new RtfCell(new Phrase(header, tableTitleTextFont));
	    setCellColor(cell);
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(cell);
	}
	table.endHeaders();
	boolean hasRows = false;
	RtfCell cell;
	try {
	    String[][] rows = session.getTable(tableName,
		    PmfConstants.DATA_SCHEMA,
		    ArrayUtils.addAll(fieldNames, boolFieldNames), "WHERE "
			    + foreignKeyName + " = '" + foreignKeyValue + "'",
		    new String[0], false);
	    hasRows = (rows.length > 0);
	    for (String[] row : rows) {
		int len = fieldNames.length;
		darkColor = !darkColor;
		for (int i = 0; i < len; i++) {
		    cell = new RtfCell(new Phrase(row[i], normalBoldTextFont));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    setCellColor(cell);
		    table.addCell(cell);
		}
		for (int i = len, leng = (boolFieldNames.length + len); i < leng; i++) {
		    hasRows = true;
		    if (row[i].equals("t")) {
			cell = new RtfCell(new Phrase("Sí", normalBoldTextFont));
		    } else {
			cell = new RtfCell(new Phrase("No", normalBoldTextFont));
		    }
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    setCellColor(cell);
		    table.addCell(cell);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	if (!hasRows) {
	    darkColor = !darkColor;
	    for (String header : fieldHeaders) {
		cell = new RtfCell(new Phrase("", normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		setCellColor(cell);
		table.addCell(cell);
	    }
	}
	return table;
    }

    protected Table getFieldsTable(String tableName, String foreignKeyName,
	    String[] foreignKeyValues, String[] fieldNames,
	    String[] boolFieldNames, String[] fieldHeaders)
	    throws DocumentException {

	Table table = new Table(fieldNames.length + boolFieldNames.length);
	darkColor = true;

	for (String header : fieldHeaders) {
	    RtfCell cell = new RtfCell(new Phrase(header, tableTitleTextFont));
	    setCellColor(cell);
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(cell);
	}
	table.endHeaders();
	boolean hasRows = false;
	RtfCell cell;
	try {
	    String whereClause = "WHERE (";
	    for (String value : foreignKeyValues) {
		whereClause += foreignKeyName + " = '" + value + "' OR ";
	    }
	    if (whereClause.endsWith(" OR ")) {
		whereClause = whereClause
			.substring(0, whereClause.length() - 4);
	    }
	    whereClause += ")";
	    String[][] rows = session.getTable(tableName,
		    PmfConstants.DATA_SCHEMA,
		    ArrayUtils.addAll(fieldNames, boolFieldNames), whereClause,
		    new String[0], false);
	    hasRows = (rows.length > 0);
	    for (String[] row : rows) {
		int len = fieldNames.length;
		darkColor = !darkColor;
		for (int i = 0; i < len; i++) {
		    cell = new RtfCell(new Phrase(row[i], normalBoldTextFont));
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    setCellColor(cell);
		    table.addCell(cell);
		}
		for (int i = len, leng = (boolFieldNames.length + len); i < leng; i++) {
		    hasRows = true;
		    if (row[i].equals("t")) {
			cell = new RtfCell(new Phrase("Sí", normalBoldTextFont));
		    } else {
			cell = new RtfCell(new Phrase("No", normalBoldTextFont));
		    }
		    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    setCellColor(cell);
		    table.addCell(cell);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	if (!hasRows) {
	    darkColor = !darkColor;
	    for (String header : fieldHeaders) {
		cell = new RtfCell(new Phrase("", normalTextFont));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		setCellColor(cell);
		table.addCell(cell);
	    }
	}
	return table;
    }

}// Class

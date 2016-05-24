package es.icarto.gvsig.importer;

import java.io.File;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class XLS {

    private Sheet sheet;
    private Collator collator;
    private int headerLine = FIRST_NOT_EMPTY;
    private int realHeaderRowNumber;

    public XLS(File file) throws InvalidFormatException, IOException {
	collator = Collator.getInstance();
	collator.setStrength(Collator.PRIMARY);
	Workbook wb = WorkbookFactory.create(file);
	int sheetIdx = wb.getActiveSheetIndex();
	if (sheetIdx != 0) {
	    // addWarning("La �ltima hoja empleada no es la primera del libro. Compruebe que esto es correcto");
	}
	sheet = wb.getSheetAt(sheetIdx);
    }

    public void read() {

    }

    private void headeradd(String v, int i) {

    }

    // First not empty | number of line | not header

    public final static int FIRST_NOT_EMPTY = -1;
    public final static int NO_HEADER = -2;

    /**
     * Negative values for "i" different that FIRST_NOT_EMPTY or NO_HEADER
     * throws an IllegalArgumentException If "i" is different that
     * FIRST_NOT_EMPTY or NO_HEADER that number will be used as the first valid
     * row in the sheet and it will contain the header
     * 
     * @param i
     */
    public void setHeaderLine(int i) {
	if ((i < 0) && (i != FIRST_NOT_EMPTY && i != NO_HEADER)) {
	    throw new IllegalArgumentException();
	}
	headerLine = i;
    }

    public Header getHeader() {
	return null;
    }

    public List<SimpleHeaderField> getSimpleHeader() {
	Row row;
	if (headerLine == FIRST_NOT_EMPTY) {
	    realHeaderRowNumber = sheet.getFirstRowNum();
	    row = sheet.getRow(realHeaderRowNumber);
	} else if (headerLine == NO_HEADER) {
	    realHeaderRowNumber = -1;
	    return null;
	} else {
	    realHeaderRowNumber = headerLine;
	    row = sheet.getRow(headerLine);
	    if (row == null) {
		throw new IllegalArgumentException(
			"Esta l�nea est� vac�a, no puede ser la cabecera del fichero");
	    }
	}

	List<SimpleHeaderField> list = new ArrayList<SimpleHeaderField>();
	for (Cell cell : row) {
	    int pos = cell.getColumnIndex();
	    String name = cell.getStringCellValue();
	    SimpleHeaderField sh = new SimpleHeaderField(name, pos);
	    list.add(sh);
	}

	// sheet.getFirstRowNum()
	// sheet.getLastRowNum()
	//
	// sheet.getRow(arg0)
	//
	// sheet.iterator()
	// sheet.rowIterator()

	return list;

    }

    public DefaultTableModel getValues() {
	DefaultTableModel table = new DefaultTableModel();

	// TODO
	table.addColumn("id");
	table.addColumn("x");
	table.addColumn("y");

	for (int i = realHeaderRowNumber + 1; i <= sheet.getLastRowNum(); i++) {

	    Row row = sheet.getRow(i);
	    if (row == null) {
		continue;
	    }

	    Object rowData[] = new Object[3];
	    for (Cell cell : row) {
		int pos = cell.getColumnIndex();
		String val = XLSUtils.getValueAsString(cell);
		rowData[pos] = val;
	    }
	    table.addRow(rowData);
	}

	return table;
    }
}

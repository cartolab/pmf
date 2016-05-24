package es.icarto.gvsig.importer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;

import es.udc.cartolab.gvsig.navtable.format.DoubleFormatNT;

public class XLSUtils {
    private static final DataFormatter dataFormatter = new DataFormatter();

    public static String getValueAsString(Cell cell) {
	if (cell == null) {
	    return "";
	}
	switch (cell.getCellType()) {
	case Cell.CELL_TYPE_STRING:
	    return cell.getRichStringCellValue().getString();
	case Cell.CELL_TYPE_NUMERIC:
	    if (DateUtil.isCellDateFormatted(cell)) {
		// Date date = cell.getDateCellValue();
		// return DateFormatNT.getDateFormat().format(date);
		return dataFormatter.formatCellValue(cell);
	    } else {
		double numericCellValue = cell.getNumericCellValue();
		return DoubleFormatNT.getEditingFormat().format(
			numericCellValue);
	    }
	case Cell.CELL_TYPE_BOOLEAN:
	    return cell.getBooleanCellValue() ? "S�" : "No";
	case Cell.CELL_TYPE_FORMULA:
	    return cell.getCellFormula();
	case Cell.CELL_TYPE_BLANK:
	    return "";
	default:
	    return "";
	}
    }

}

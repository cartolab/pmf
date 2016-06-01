package es.icarto.gvsig.importer;

import javax.swing.table.DefaultTableModel;

import com.iver.cit.gvsig.fmap.core.IGeometry;

import es.icarto.gvsig.commons.utils.Field;

@SuppressWarnings("serial")
public class ImporterTM extends DefaultTableModel {
    public ImporterTM() {
	addColumn("Código");
    }

    @Override
    public boolean isCellEditable(int row, int column) {
	if (column == 0) {
	    return false;
	}
	return super.isCellEditable(row, column);
    }

    public void setCode(Object aValue, int row) {
	super.setValueAt(aValue, row, 0);
    }

    public void setTarget(Object aValue, int row) {
	int tablenameIdx = findColumn("tablename");
	super.setValueAt(aValue, row, tablenameIdx);
    }

    public void setGeom(IGeometry aValue, int row) {
	int geomIdx = findColumn("geom");
	super.setValueAt(aValue, row, geomIdx);
    }

    public IGeometry getGeom(int row) {
	int geomIdx = findColumn("geom");
	return (IGeometry) getValueAt(row, geomIdx);
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
	int tablenameIdx = findColumn("tablename");
	if (column == tablenameIdx) {
	    if (aValue == null) {
		return;
	    }
	    Target target = (Target) ((Field) aValue).getValue();
	    String code = target.calculateCode(this, row);
	    setCode(code, row);
	}
	super.setValueAt(aValue, row, column);
    }

    /**
     * Excludes the row "row"
     */
    public String maxCodeValue(String columname, Field field, int row) {
	int idx = findColumn(columname);
	int codeIdx = 0;
	String maxValue = null;
	for (int i = 0; i < getRowCount(); i++) {
	    if (i == row) {
		continue;
	    }
	    Object t = getValueAt(i, idx);
	    if ((t != null) && t.equals(field)) {
		Object value = getValueAt(i, codeIdx);
		if (value == null) {
		    continue;
		}
		if (maxValue == null) {
		    maxValue = value.toString();
		} else {
		    if (value.toString().compareTo(maxValue) > 0) {
			maxValue = value.toString();
		    }
		}
	    }
	}
	return maxValue;
    }
}

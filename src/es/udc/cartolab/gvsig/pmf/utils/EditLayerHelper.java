package es.udc.cartolab.gvsig.pmf.utils;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.hardcode.gdbms.engine.values.Value;
import com.hardcode.gdbms.engine.values.ValueFactory;
import com.iver.cit.gvsig.exceptions.expansionfile.ExpansionFileWriteException;
import com.iver.cit.gvsig.exceptions.validate.ValidateRowException;
import com.iver.cit.gvsig.exceptions.visitors.StopWriterVisitorException;
import com.iver.cit.gvsig.fmap.core.DefaultFeature;
import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.iver.cit.gvsig.fmap.core.IRow;
import com.iver.cit.gvsig.fmap.edition.EditionEvent;
import com.iver.cit.gvsig.fmap.edition.IEditableSource;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;

import es.udc.cartolab.gvsig.navtable.ToggleEditing;

public class EditLayerHelper {

    private final FLyrVect layer;
    private final SelectableDataSource recordset;

    public EditLayerHelper(FLyrVect layer) throws ReadDriverException {
	this.layer = layer;
	this.recordset = layer.getRecordset();
    }

    public int[] getIndexes(String... fieldNames) throws ReadDriverException {
	int[] indexes = new int[fieldNames.length];
	for (int i = 0; i < fieldNames.length; i++) {
	    indexes[i] = recordset.getFieldIndexByName(fieldNames[i]);
	}
	return indexes;
    }

    public Value[] getValues(String... strValues) {
	Value[] values = new Value[strValues.length];
	for (int i = 0; i < strValues.length; i++) {
	    values[i] = ValueFactory.createValue(strValues[i]);
	}
	return values;
    }

    public IRow getRow(IGeometry geom, int[] indexes, Value[] values)
	    throws ReadDriverException {

	final int fieldCount = recordset.getFieldCount();
	Value[] attributes = new Value[fieldCount];
	for (int i = 0; i < fieldCount; i++) {
	    attributes[i] = ValueFactory.createNullValue();
	}
	for (int i = 0; i < indexes.length; i++) {
	    attributes[indexes[i]] = values[i];
	}

	return new DefaultFeature(geom, attributes);
    }

    public void addRowToLayer(IRow row) throws ExpansionFileWriteException,
	    ReadDriverException, StopWriterVisitorException {

	ToggleEditing te = new ToggleEditing();
	if (!layer.isEditing()) {
	    te.startEditing(layer);
	}

	IEditableSource source = (IEditableSource) layer.getSource();

	// VectorialLayerEdited layerEdited = (VectorialLayerEdited)
	// CADExtension
	// .getEditionManager().getLayerEdited(layer);
	// VectorialEditableAdapter vea = layerEdited.getVEA();
	boolean cancel = true;
	try {
	    source.addRow(row, "", EditionEvent.ALPHANUMERIC);
	    cancel = false;
	} catch (ValidateRowException e) {
	    throw new AssertionError("No rules are set. This can not happen");
	} finally {
	    te.stopEditing(layer, cancel);
	}

    }
}

package es.icarto.gvsig.importer;

import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ImportManager {

    private final Reader reader;
    private final Header header;
    private final Output output;
    private final Ruler ruler;

    public ImportManager(Reader reader, Header header, Output output,
	    Ruler ruler) {
	this.reader = reader;
	this.header = header;
	this.output = output;
	this.ruler = ruler;
    }

    public void readHeader() {
	List<SimpleHeaderField> readHead = reader.getSimpleHeader();
	for (SimpleHeaderField s : readHead) {
	    header.addFromSource(s.name, s.pos);
	    // Si lo devuelto tiene el campo de stop debería parar
	}

    }

    public void processFile() {
	DefaultTableModel table = reader.getValues();
	table.addColumn("tablename");
	table.addColumn("geom");
	table.addColumn("Errores");
	for (int i = 0; i < table.getRowCount(); i++) {
	    String id = table.getValueAt(i, 0).toString();
	    HeaderField field = header.getField("id");
	    ruler.processValue(id, table, i);
	}

	output.process(table);

    }

}

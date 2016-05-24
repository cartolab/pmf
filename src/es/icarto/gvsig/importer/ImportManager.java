package es.icarto.gvsig.importer;

import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ImportManager {

    private Reader reader;
    private Header header;
    private Ruler ruler = new Ruler();
    private Output output = new Output();

    public void setReader(Reader reader) {
	this.reader = reader;
    }

    public void setHeader(Header header) {
	this.header = header;
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

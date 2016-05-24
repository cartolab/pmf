package es.icarto.gvsig.importer;

import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ImportManager {

    private XLS xls;
    private Header header;
    private Ruler ruler = new Ruler();
    private Output output = new Output();

    public void setReader(XLS xls) {
	this.xls = xls;
    }

    public void setHeader(Header header) {
	this.header = header;
    }

    public void readHeader() {
	List<SimpleHeaderField> readHead = xls.getSimpleHeader();
	for (SimpleHeaderField s : readHead) {
	    header.addFromSource(s.name, s.pos);
	    // Si lo devuelto tiene el campo de stop debería parar
	}

    }

    public void processFile() {
	DefaultTableModel table = xls.getValues();
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

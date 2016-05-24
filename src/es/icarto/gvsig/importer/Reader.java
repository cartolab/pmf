package es.icarto.gvsig.importer;

import java.util.List;

import javax.swing.table.DefaultTableModel;

public interface Reader {

    List<SimpleHeaderField> getSimpleHeader();

    DefaultTableModel getValues();

}

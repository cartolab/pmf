package es.udc.cartolab.gvsig.pmf.importer;

import com.iver.cit.gvsig.fmap.core.IGeometry;

import es.icarto.gvsig.importer.ImporterTM;
import es.icarto.gvsig.importer.JDBCTarget;

public class NoTarget extends JDBCTarget {

    public NoTarget() {
    }

    @Override
    public boolean matches(String value) {
	return false;
    }

    // TODO
    // select a.nombre, a.cod_aldea, c.cod_com from comunidades c join
    // aldeas_pmf a on substring(c.cod_com, 0, 6) = cod_aldea and
    // st_contains(a.geom, c.geom)

    @Override
    public boolean process(String value, ImporterTM table, int i) {
	int geomIdx = table.findColumn("geom");
	int xIdx = table.findColumn("x");
	int yIdx = table.findColumn("y");
	table.setTarget(null, i);
	String xStr = table.getValueAt(i, xIdx).toString();
	String yStr = table.getValueAt(i, yIdx).toString();
	IGeometry geom = getGeometry(xStr, yStr);
	table.setValueAt(geom, i, geomIdx);
	table.setCode(null, i);

	return true;
    }

    @Override
    public String calculateCode(ImporterTM table, int i) {
	return null;
    }
}
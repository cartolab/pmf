package es.udc.cartolab.gvsig.pmf.importer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import com.iver.cit.gvsig.exceptions.layers.ReloadLayerException;
import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.importer.Output;
import es.icarto.gvsig.importer.Ruler;
import es.icarto.gvsig.importer.TableInfo;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class PMFOutput implements Output {

    private static final Logger logger = Logger.getLogger(PMFOutput.class);

    private int indexForColumnName(DefaultTableModel table, String columnName) {
	return table.findColumn(columnName);
    }

    public void process(DefaultTableModel table, Ruler ruler) {
	int tablenameIdx = indexForColumnName(table, "tablename");
	int geomIdx = indexForColumnName(table, "geom");
	int errorIdx = indexForColumnName(table, "Errores");

	reorder(table);
	TableInfo dialog = new TableInfo(table, ruler);
	dialog.openDialog();
	if (!dialog.isGood()) {
	    return;
	}

	Connection con = DBSession.getCurrentSession().getJavaConnection();
	Statement statement = null;
	boolean autoCommit;
	try {
	    autoCommit = con.getAutoCommit();
	    con.setAutoCommit(false);
	    statement = con.createStatement();

	    for (int i = 0; i < table.getRowCount(); i++) {
		String tablename = table.getValueAt(i, tablenameIdx).toString();
		if (tablename.isEmpty()) {
		    continue;
		}
		if (table.getValueAt(i, errorIdx) != null) {
		    continue;
		}

		IGeometry geom = (IGeometry) table.getValueAt(i, geomIdx);
		String geomAsWKT = geom.toJTSGeometry().toText();

		String id = table.getValueAt(i, 0).toString();
		String codCom = id.substring(0, 8);

		String sql = "";
		if (tablename.equals("comunidades")) {
		    sql = String
			    .format("INSERT INTO comunidades (cod_com, geom) VALUES ('%s', ST_GeomFromText('%s', 32616))",
				    codCom, geomAsWKT);
		} else if (tablename.equals("informacion_general")) {
		    sql = String
			    .format("INSERT INTO informacion_general (cod_com, cod_viv, geom) VALUES ('%s', '%s', ST_GeomFromText('%s', 32616))",
				    codCom, id, geomAsWKT);
		} else if (tablename.equals("centros_educativos")) {
		    sql = String
			    .format("INSERT INTO centros_educativos (cod_com, cod_cedu, geom) VALUES ('%s', '%s', ST_GeomFromText('%s', 32616))",
				    codCom, id, geomAsWKT);

		} else if (tablename.equals("centros_reuniones")) {
		    sql = String
			    .format("INSERT INTO centros_reuniones (cod_com, cod_creu, geom) VALUES ('%s', '%s', ST_GeomFromText('%s', 32616))",
				    codCom, id, geomAsWKT);

		} else if (tablename.equals("centros_salud")) {
		    sql = String
			    .format("INSERT INTO centros_salud (cod_com, cod_csalud, geom) VALUES ('%s', '%s', ST_GeomFromText('%s', 32616))",
				    codCom, id, geomAsWKT);

		} else if (tablename.equals("fuentes_comunitarias")) {
		    sql = String
			    .format("INSERT INTO fuentes_comunitarias (cod_com, codigo_fc, geom) VALUES ('%s', '%s', ST_GeomFromText('%s', 32616))",
				    codCom, id, geomAsWKT);
		} else if (tablename.equals("limites_parcela")) {
		    sql = String
			    .format("INSERT INTO limites_parcela (cod_parcela, cod_lim_p, geom) VALUES ('%s', '%s', ST_GeomFromText('%s', 32616))",
				    codCom, id, geomAsWKT);
		} else {
		    continue;
		}
		statement.addBatch(sql);
	    }

	    statement.executeBatch();

	    con.commit();
	    con.setAutoCommit(autoCommit);

	    FLyrVect[] layers = new TOCLayerManager().getAllLayers();
	    for (FLyrVect l : layers) {
		try {
		    l.reload();
		} catch (ReloadLayerException e) {
		    logger.error(e.getStackTrace(), e);
		}
	    }
	    JOptionPane.showMessageDialog(null, "Añadidos correctamete");
	} catch (SQLException e) {
	    logger.error(e.getStackTrace(), e);
	    try {
		statement.clearBatch();
		con.rollback();
	    } catch (SQLException e1) {
		logger.error(e1.getStackTrace(), e1);
	    }
	}
    }

    protected void reorder(DefaultTableModel table) {
	int lastComunidadesRow = -1;
	for (int i = 0; i < table.getRowCount(); i++) {
	    String tablename = table.getValueAt(i, 3).toString();
	    if (tablename.equals("comunidades")
		    && (lastComunidadesRow + 1) != i) {
		lastComunidadesRow += 1;
		table.moveRow(i, i, lastComunidadesRow);
	    }
	}

	int lastViviendaRow = lastComunidadesRow;
	for (int i = lastViviendaRow + 1; i < table.getRowCount(); i++) {
	    String tablename = table.getValueAt(i, 3).toString();
	    if (tablename.equals("informacion_general")
		    && (lastViviendaRow + 1) != i) {
		lastViviendaRow += 1;
		table.moveRow(i, i, lastViviendaRow);
	    }
	}
    }

}

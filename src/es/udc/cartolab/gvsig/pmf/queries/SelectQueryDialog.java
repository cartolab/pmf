package es.udc.cartolab.gvsig.pmf.queries;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import com.hardcode.gdbms.engine.data.DataSource;
import com.hardcode.gdbms.engine.data.DataSourceFactory;
import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;
import com.iver.cit.gvsig.project.documents.view.gui.BaseView;

import es.udc.cartolab.gvsig.navtableforms.Utils;
import es.udc.cartolab.gvsig.pmf.commongui.SaveFileDialog;
import es.udc.cartolab.gvsig.pmf.commongui.TwoColumnsPanel;

public class SelectQueryDialog implements ActionListener {
	private TwoColumnsPanel panel = null;

	private final String QUERY1 = "Listado de comunidades";
	private final String QUERY2 = "Listado de productores";
	private final String QUERY3 = "Listado de áreas protegidas";
	private final String QUERY4 = "Listado de fincas";
	private final String QUERY5 = "Listado de fincas y cultivos";
	private final String QUERY6 = "Listado de planificación";

	private final String SELECTQUERY = "_selectQuery";
	private final String SELECTFILE = "_selectFile";
	private BaseView view;

	public SelectQueryDialog(BaseView view) {
		this.view = view;
		panel = new TwoColumnsPanel(this);
		String[] items = { QUERY1, QUERY2, QUERY3, QUERY4, QUERY5, QUERY6 };
		panel.addCB(items, SELECTQUERY);

		panel.addSaveFile(SELECTFILE, "fichero csv", "csv");
		PluginServices.getMDIManager().addWindow(panel);
	}

	private StringBuffer doFilter(String layerName, String fields)
			throws Exception {

		StringBuffer str = new StringBuffer();
		SelectableDataSource recordset = Utils.getFlyrVect(view, layerName)
				.getRecordset();
		String sql = "select " + fields + " from " + recordset.getName() + ";";
		recordset.start();
		DataSource result = recordset.getDataSourceFactory().executeSQL(sql,
				DataSourceFactory.MANUAL_OPENING);

		result.start();
		for (int i = 0; i < result.getRowCount(); i++) {
			for (int j = 0; j < result.getFieldCount(); j++) {
				str.append(result.getFieldValue(i, j).toString() + ";");
			}
			str.append("\n");
		}
		result.stop();

		recordset.stop();

		return str;
	}

	private String query1() {
		StringBuffer str = new StringBuffer("nombre;municip;departa;\n");

		try {
			str.append(doFilter("comunidad", "nombre,municip,departa"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str.toString();
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == panel.getOKButton()) {

			String query = panel.getText(SELECTQUERY);
			String fileName = panel.getText(SELECTFILE);
			if (fileName.length() < 1) {
				return;
			}

			String result = null;

			if (query.equals(QUERY1)) {
				result = query1();
			}
			SaveFileDialog.writeFileToDisk(result, new File(fileName));
		}

		PluginServices.getMDIManager().closeWindow(panel);

	}
}

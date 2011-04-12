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

	private StringBuffer doFilter(DataSourceFactory dsf, String sql)
			throws Exception {

		StringBuffer str = new StringBuffer();

		DataSource result = dsf.executeSQL(sql,
				DataSourceFactory.MANUAL_OPENING);

		result.start();
		for (int i = 0; i < result.getRowCount(); i++) {
			for (int j = 0; j < result.getFieldCount(); j++) {
				str.append(result.getFieldValue(i, j).toString() + ";");
			}
			str.append("\n");
		}
		result.stop();

		return str;
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
			} else if (query.equals(QUERY2)) {
				result = query2();
			} else if (query.equals(QUERY3)) {
				result = query3();
			} else if (query.equals(QUERY4)) {
				result = query4();
			} else if (query.equals(QUERY5)) {
				result = query5();
			} else if (query.equals(QUERY6)) {
				result = query6();
			}
			SaveFileDialog.writeFileToDisk(result, new File(fileName));
		}

		PluginServices.getMDIManager().closeWindow(panel);

	}

	// private final String QUERY6 = "Listado de planificación";
	private String query6() {
		StringBuffer str = new StringBuffer(
				"Nombre del productor/a;Área total de la finca en Mz;Área para cultivos en Mz;Período a establecer sistema de riego;Período a establecer huerto familiar;Período a establecer cocina mejorada;Período a establecer filtro para aguas grises;Período a establecer construcción de gallineros;Disponibilidad de la mano de obra  familiar; cantidad;Disponibilidad de la mano de obra familiar;período;Disponibilidad de la mano de obra contratada; cantidad;Disponibilidad de la mano de obra contratada;período;\n");
		try {
			SelectableDataSource viviendaSDS = Utils.getFlyrVect(view,
					"vivienda").getRecordset();
			String vivienda = viviendaSDS.getName();
			String parcela = Utils.getFlyrVect(view, "parcela").getRecordset()
					.getName();

			String sql = "select " + vivienda + ".nom_produ," + parcela
					+ ".area_tot," + parcela + ".area_cul," + parcela
					+ ".p_riego," + parcela + ".p_huerto," + parcela
					+ ".p_coc_mejo," + parcela + ".p_filtroag," + parcela
					+ ".p_galline," + parcela + ".fam_cant," + parcela
					+ ".fam_per," + parcela + ".con_cant," + parcela
					+ ".con_per" + " from " + vivienda + "," + parcela
					+ " where " + vivienda + ".cod_viv=" + parcela + ".cod_viv"
					+ ";";

			str.append(doFilter(viviendaSDS.getDataSourceFactory(), sql));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str.toString();

	}

	// private final String QUERY5 = "Listado de fincas y cultivos";
	private String query5() {
		// TODO Auto-generated method stub
		return "";
	}

	// private final String QUERY4 = "Listado de fincas";
	private String query4() {
		StringBuffer str = new StringBuffer(
				"Nombre productor/a;Nombre de la comunidad;Nombre del municipio;Área total de la finca en Mz;Área para cultivos en Mz;Tipo de suelo;Nivel de degradación del suelo;Pendiente de la finca;Tiene sistema de riego;Tipo de prácticas conservacionistas;Tipo de abono orgánico;Tipo de insecticidas orgánicos;Tipo de plaguicidas químicas;\n");
		try {
			SelectableDataSource viviendaSDS = Utils.getFlyrVect(view,
					"vivienda").getRecordset();
			String vivienda = viviendaSDS.getName();
			String parcela = Utils.getFlyrVect(view, "parcela").getRecordset()
					.getName();
			String comunidad = Utils.getFlyrVect(view, "comunidad")
					.getRecordset().getName();

			// String sql = "select " + vivienda + ".nom_produ," + comunidad
			// + ".nombre," + comunidad + ".municip," + parcela
			// + ".area_tot," + parcela + ".area_cul," + parcela
			// + ".tip_suelo," + parcela + ".deg_suelo," + parcela
			// + ".pendiente," + vivienda + ".sist_rieg," + parcela
			// + ".c_conse," + parcela + ".c_aborg," + parcela
			// + ".c_insect," + parcela + ".c_quim" + " from " + vivienda
			// + "," + comunidad + "," + parcela + " where " + vivienda
			// + ".cod_viv=" + parcela + ".cod_viv" + " and " + vivienda
			// + ".cod_com=" + comunidad + "cod_com;";

			String sql = "select " + vivienda + ".nom_produ," + parcela
					+ ".area_tot," + parcela + ".area_cul," + parcela
					+ ".tip_suelo," + parcela + ".deg_suelo," + parcela
					+ ".pendiente," + vivienda + ".sist_rieg," + parcela
					+ ".c_conse," + parcela + ".c_aborg," + parcela
					+ ".c_insect," + parcela + ".c_quim" + " from " + vivienda
					+ "," + parcela + " where " + vivienda + ".cod_viv="
					+ parcela + ".cod_viv" + " AND " + vivienda + ".cod_com="
					+ parcela + "cod_com;";
			str.append(doFilter(viviendaSDS.getDataSourceFactory(), sql));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str.toString();
	}

	// private final String QUERY3 = "Listado de áreas protegidas";
	private String query3() {
		// TODO Auto-generated method stub
		return null;
	}

	// private final String QUERY2 = "Listado de productores";
	private String query2() {
		StringBuffer str = new StringBuffer(
				"Nombre productor;Nombre comunidad;Area Cultivos;\n");
		try {
			SelectableDataSource vivienda = Utils.getFlyrVect(view, "vivienda")
					.getRecordset();
			String viviendaName = vivienda.getName();
			String parcelaName = Utils.getFlyrVect(view, "parcela")
					.getRecordset().getName();

			String sql = "select nom_produ, " + viviendaName
					+ ".nom_com, area_cul from " + viviendaName + ","
					+ parcelaName + " where " + viviendaName + ".cod_viv="
					+ parcelaName + ".cod_viv;";
			str.append(doFilter(vivienda.getDataSourceFactory(), sql));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str.toString();
	}

	// private final String QUERY1 = "Listado de comunidades";
	private String query1() {
		StringBuffer str = new StringBuffer("nombre;municipio;departamento;\n");

		try {
			SelectableDataSource comunidad = Utils.getFlyrVect(view,
					"comunidad").getRecordset();
			String sql = "select nombre, municip, departa from "
					+ comunidad.getName() + ";";
			str.append(doFilter(comunidad.getDataSourceFactory(), sql));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str.toString();
	}

}

package es.udc.cartolab.gvsig.pmf.queries;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.hardcode.gdbms.engine.data.DataSourceFactory;
import com.hardcode.gdbms.engine.strategies.FilteredDataSource;
import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;
import com.iver.cit.gvsig.project.documents.view.gui.View;

import es.udc.cartolab.gvsig.navtableforms.Utils;
import es.udc.cartolab.gvsig.pmf.commongui.TwoColumnsPanel;

public class SelectQueryDialog implements ActionListener {
    private TwoColumnsPanel panel = null;

    private class Query1 {
	public String description = "Listado de comunidades";
	View view = null;

	public Query1(View view) {
	    this.view = view;
	}

	public void doIt() throws Exception {
	    SelectableDataSource comunidades = Utils.getFlyrVect(view,
		    "comunidades").getRecordset();
	    String sql = "select nombre, municip, departa from "
		    + comunidades.getName() + ";";
	    comunidades.start();
	    FilteredDataSource result = (FilteredDataSource) comunidades
		    .getDataSourceFactory().executeSQL(sql,
			    DataSourceFactory.MANUAL_OPENING);
	    comunidades.stop();
	}

    }

    private final String QUERY1 = "Listado de comunidades";
    private final String QUERY2 = "Listado de productores";
    private final String QUERY3 = "Listado de áreas protegidas";
    private final String QUERY4 = "Listado de fincas";
    private final String QUERY5 = "Listado de fincas y cultivos";
    private final String QUERY6 = "Listado de planificación";

    private final String SELECTQUERY = "_selectQuery";
    private final String SELECTFILE = "_selectFile";

    public SelectQueryDialog() {
	panel = new TwoColumnsPanel(this);
	String[] items = { QUERY1, QUERY2, QUERY3, QUERY4, QUERY5, QUERY6 };
	panel.addCB(items, SELECTQUERY);

	panel.addSaveFile(SELECTFILE, "fichero csv", "csv");
	PluginServices.getMDIManager().addWindow(panel);
    }

    public void actionPerformed(ActionEvent e) {

	if (e.getSource() == panel.getOKButton()) {

	    long recordPosition = -1;
	    IWindow window = PluginServices.getMDIManager().getActiveWindow();
	    View view = null;
	    if (window instanceof View) {
		view = (View) window;
	    }

	    try {
		Query1 q = new Query1(view);
		q.doIt();
	    } catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }

	    // PMFQuery q = new PMFQuery(panel.getText(SELECTQUERY));
	    System.out.println(panel.getText(SELECTFILE));

	}

	PluginServices.getMDIManager().closeWindow(panel);

    }
}

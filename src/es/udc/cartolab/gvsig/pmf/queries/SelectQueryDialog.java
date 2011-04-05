package es.udc.cartolab.gvsig.pmf.queries;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.iver.andami.PluginServices;

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

    public SelectQueryDialog() {
	panel = new TwoColumnsPanel(this);
	String[] items = { QUERY1, QUERY2, QUERY3, QUERY4, QUERY5, QUERY6 };
	panel.addCB(items, SELECTQUERY);

	panel.addSaveFile(SELECTFILE, "fichero csv", "csv");
	PluginServices.getMDIManager().addWindow(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	System.out.println(panel.getText(SELECTQUERY));
	System.out.println(panel.getText(SELECTFILE));
    }

}

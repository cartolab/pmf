package es.udc.cartolab.gvsig.pmf.queries;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import com.hardcode.gdbms.engine.data.DataSource;
import com.hardcode.gdbms.engine.data.DataSourceFactory;
import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.WindowInfo;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;
import com.iver.cit.gvsig.project.documents.view.gui.BaseView;

import es.udc.cartolab.gvsig.navtableforms.Utils;
import es.udc.cartolab.gvsig.pmf.commongui.SaveFileDialog;

public class SelectQueryDialog extends JPanel implements IWindow,
	ActionListener {

    private static Logger logger = Logger.getLogger("QueriesExtension");
    private WindowInfo windowInfo = null;

    private final String QUERY1 = "Listado de comunidades";
    private final String QUERY2 = "Listado de productores";
    private final String QUERY3 = "Listado de áreas protegidas";
    private final String QUERY4 = "Listado de fincas";
    private final String QUERY5 = "Listado de fincas y cultivos";
    private final String QUERY6 = "Listado de planificación";
    private final String OUTPUT1 = "listadoComunidades";
    private final String OUTPUT2 = "listadoProductores";
    private final String OUTPUT3 = "listadoÁreasProtegidas";
    private final String OUTPUT4 = "listadoFincas";
    private final String OUTPUT5 = "listadoFincasCultivos";
    private final String OUTPUT6 = "listadoPlanificación";

    private final String SELECTQUERY = "_selectQuery";
    private BaseView view;

    private JTextField directoryField = null;
    private JButton dotsButton = null;
    private JComboBox queryCombo = null;
    private JButton cancelButton = null;
    private JButton okButton = null;

    @Override
    public WindowInfo getWindowInfo() {
	if (windowInfo == null) {
	    windowInfo = new WindowInfo(WindowInfo.MODALDIALOG
		    | WindowInfo.RESIZABLE | WindowInfo.PALETTE);
	    windowInfo.setTitle(PluginServices.getText(this, "Queries"));
	    Dimension dim = getPreferredSize();
	    int width, height = 0;
	    if (dim.getHeight() > 550) {
		height = 550;
	    } else {
		height = 60;
	    }
	    if (dim.getWidth() > 650) {
		width = 650;
	    } else {
		width = new Double(dim.getWidth()).intValue() + 15;
	    }
	    windowInfo.setWidth(width);
	    windowInfo.setHeight(height);
	}
	return windowInfo;
    }

    @Override
    public Object getWindowProfile() {
	// TODO Auto-generated method stub
	return null;
    }

    public SelectQueryDialog(BaseView view) {
	super();
	this.view = view;
	try {
	    initialize();
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	}
    }

    private void initialize() throws Exception {
	this.setLayout(new MigLayout("center", "[fill][grow 0][grow 0][fill]",
		"20[fill][fill]30%[fill, bottom]"));
	this.setLayout(new MigLayout());

	String[] items = { QUERY1, QUERY2, QUERY3, QUERY4, QUERY5, QUERY6 };

	this.add(new JLabel(PluginServices.getText(this, SELECTQUERY)));

	queryCombo = new JComboBox(items);

	this.add(queryCombo);

	JLabel outputLabel = new JLabel(PluginServices.getText(this, "Save_in"));
	this.add(outputLabel, "right, bottom, cell 0 2");

	String userHome = System.getProperty("user.home");

	directoryField = new JTextField("");

	this.add(directoryField, "grow, bottom, cell 1 2");
	directoryField.setText(userHome);

	dotsButton = new JButton("...");
	dotsButton.addActionListener(this);
	this.add(dotsButton, "center, bottom, cell 2 2");

	okButton = new JButton();
	okButton.setText(PluginServices.getText(this, "Ok"));
	okButton.addActionListener(this);

	cancelButton = new JButton();
	cancelButton.setText(PluginServices.getText(this, "Cancel"));
	cancelButton.addActionListener(this);

	this.add(okButton, "center, bottom, cell 1 3");
	this.add(cancelButton, "center, bottom, cell 1 3");
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

    private void displayFileChooser() {
	File currentDirectory = new File(directoryField.getText());
	JFileChooser chooser;
	if (currentDirectory.isDirectory()) {
	    chooser = new JFileChooser(currentDirectory);
	} else {
	    chooser = new JFileChooser();
	}
	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	int returnVal = chooser.showOpenDialog(directoryField);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    directoryField.setText(chooser.getSelectedFile().getAbsolutePath());
	}
    }

    public void actionPerformed(ActionEvent e) {

	if (e.getSource() == dotsButton) {
	    displayFileChooser();
	} else if (e.getSource() == okButton) {

	    String query = this.queryCombo.getSelectedItem().toString();
	    String fileName = directoryField.getText();
	    if (fileName.length() < 1) {
		return;
	    }

	    String result = null;

	    if (query.equals(QUERY1)) {
		result = query1();
		fileName += "/" + OUTPUT1 + ".csv";
	    } else if (query.equals(QUERY2)) {
		result = query2();
		fileName += "/" + OUTPUT2 + ".csv";
	    } else if (query.equals(QUERY3)) {
		result = query3();
		fileName += "/" + OUTPUT3 + ".csv";
	    } else if (query.equals(QUERY4)) {
		result = query4();
		fileName += "/" + OUTPUT4 + ".csv";
	    } else if (query.equals(QUERY5)) {
		result = query5();
		fileName += "/" + OUTPUT5 + ".csv";
	    } else if (query.equals(QUERY6)) {
		result = query6();
		fileName += "/" + OUTPUT6 + ".csv";
	    }

	    for (int i = 0; i < 10; i++) {
		result = result.replace("." + ((Integer) i).toString(), ","
			+ ((Integer) i).toString());
	    }

	    SaveFileDialog.writeFileToDisk(result, new File(fileName));

	    PluginServices.getMDIManager().closeWindow(this);
	} else if (e.getSource() == cancelButton) {
	    PluginServices.getMDIManager().closeWindow(this);
	}

    }

    // private final String QUERY6 = "Listado de planificación";
    private String query6() {
	StringBuffer str = new StringBuffer(
		"Nombre del productor/a;Área total de la finca en Mz;Área para cultivos en Mz;Período a establecer sistema de riego;Período a establecer huerto familiar;Período a establecer cocina mejorada;Período a establecer filtro para aguas grises;Período a establecer construcción de gallineros;Disponibilidad de la mano de obra familiar (cantidad);Disponibilidad de la mano de obra familiar (período);Disponibilidad de la mano de obra contratada (cantidad);Disponibilidad de la mano de obra contratada (período);\n");
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
	StringBuffer str = new StringBuffer(
		"Nombre del productor/a;Nombre de la comunidad;Nombre del municipio;Área total de la finca en Mz;Área para cultivos en Mz;Tipo de cultivo;Área de cultivo;\n");
	try {
	    SelectableDataSource viviendaSDS = Utils.getFlyrVect(view,
		    "vivienda").getRecordset();
	    String vivienda = viviendaSDS.getName();
	    String parcela = Utils.getFlyrVect(view, "parcela").getRecordset()
		    .getName();
	    String comunidad = Utils.getFlyrVect(view, "comunidad")
		    .getRecordset().getName();

	    String sql = "select " + vivienda + ".nom_produ," + comunidad
		    + ".nombre," + comunidad + ".municip," + parcela
		    + ".area_tot," + parcela + ".area_cul" + " from "
		    + vivienda + "," + parcela + ", " + comunidad + " where "
		    + vivienda + ".cod_viv=" + parcela + ".cod_viv" + " and "
		    + parcela + ".cod_com=" + comunidad + ".cod_com" + ";";

	    str.append(doFilter(viviendaSDS.getDataSourceFactory(), sql));
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return str.toString();
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

	    String sql = "select " + vivienda + ".nom_produ," + comunidad
		    + ".nombre," + comunidad + ".municip," + parcela
		    + ".area_tot," + parcela + ".area_cul," + parcela
		    + ".tip_suelo," + parcela + ".deg_suelo," + parcela
		    + ".pendiente," + vivienda + ".sist_rieg," + parcela
		    + ".c_conse," + parcela + ".c_aborg," + parcela
		    + ".c_insect," + parcela + ".c_quim" + " from " + vivienda
		    + "," + parcela + "," + comunidad + " where " + vivienda
		    + ".cod_viv=" + parcela + ".cod_viv" + " and " + vivienda
		    + ".cod_com=" + comunidad + ".cod_com;";

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

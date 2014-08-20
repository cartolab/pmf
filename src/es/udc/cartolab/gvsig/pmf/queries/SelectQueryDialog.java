package es.udc.cartolab.gvsig.pmf.queries;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.WindowInfo;

import es.udc.cartolab.gvsig.commons.ui.AbstractIWindow;
import es.udc.cartolab.gvsig.commons.ui.AcceptCancelPanel;
import es.udc.cartolab.gvsig.commons.ui.SaveFileDialog;
import es.udc.cartolab.gvsig.pmf.forms.ComunidadesForm;
import es.udc.cartolab.gvsig.pmf.forms.CultivosForm;
import es.udc.cartolab.gvsig.pmf.forms.InformacionGeneralForm;
import es.udc.cartolab.gvsig.pmf.forms.ParcelasForm;
import es.udc.cartolab.gvsig.pmf.utils.DAO;
import es.udc.cartolab.gvsig.pmf.utils.PmfConstants;
import es.udc.cartolab.gvsig.users.utils.DBSession;

@SuppressWarnings("serial")
public class SelectQueryDialog extends AbstractIWindow implements
	ActionListener {

    private static final Logger logger = Logger
	    .getLogger(SelectQueryDialog.class);

    private static final String QUERY1 = "Listado de comunidades";
    private static final String QUERY2 = "Listado de productores";
    private static final String QUERY3 = "Listado de fincas";
    private static final String QUERY4 = "Listado de fincas y cultivos";
    private static final String QUERY5 = "Listado de planificación";
    private static final String QUERY6 = "Listado agregado de rubros por comunidad";
    private static final String QUERY7 = "Empleos generados por rubro";
    private static final String QUERY8 = "Ingresos y producción familiar";
    private static final String OUTPUT1 = "listadoComunidades";
    private static final String OUTPUT2 = "listadoProductores";
    private static final String OUTPUT3 = "listadoFincas";
    private static final String OUTPUT4 = "listadoFincasCultivos";
    private static final String OUTPUT5 = "listadoPlanificación";
    private static final String OUTPUT6 = "listadoAgregadoRubrosComunidad";
    private static final String OUTPUT7 = "empleos_generados_rubro";
    private static final String OUTPUT8 = "ingresos_produccion_familiar";

    String[] items = { QUERY1, QUERY2, QUERY3, QUERY4, QUERY5, QUERY6, QUERY7,
	    QUERY8 };

    private static final String SELECTQUERY = "_selectQuery";

    private JTextField directoryField = null;
    private JButton dotsButton = null;
    private JComboBox queryCombo = null;

    private final DBSession session = DBSession.getCurrentSession();

    public SelectQueryDialog() {
	super();
	setWindowTitle("Consultas");
	setWindowInfoProperties(WindowInfo.MODALDIALOG);
	addAcceptCancelPanel(this, this);
	initialize();
    }

    private void initialize() {

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

    private void processQuery(String outputPath) {
	if (!outputPath.endsWith(File.separator)) {
	    outputPath = outputPath + File.separator;
	}
	File dir = new File(outputPath);
	if (dir.isDirectory() && dir.canWrite()) {
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
	    } else if (query.equals(QUERY7)) {
		result = query7();
		fileName += "/" + OUTPUT7 + ".csv";
	    } else if (query.equals(QUERY8)) {
		result = query8();
		fileName += "/" + OUTPUT8 + ".csv";
	    }

	    for (int i = 0; i < 10; i++) {
		result = result.replace("." + ((Integer) i).toString(), ","
			+ ((Integer) i).toString());
	    }

	    SaveFileDialog.writeFileToDisk(result, new File(fileName));
	} else {
	    JOptionPane.showMessageDialog(this,
		    "El directorio seleccionado no es válido.", "Error",
		    JOptionPane.ERROR_MESSAGE);
	}
    }

    private String query8() {
	StringBuffer str = new StringBuffer(
		"Código vivienda;Productor/a;Rubro;Producción (ud);Producción (kg);Beneficio;\n");
	String[][] values = new String[0][0];
	try {
	    values = DAO.getIngresosProduccionFamiliar();
	} catch (SQLException e) {
	    logger.error(e.getStackTrace(), e);
	}
	str.append(matrixToString(values));
	return str.toString();
    }

    private String query7() {
	StringBuffer str = new StringBuffer(
		"Comunidad;Rubro;Total empleo;Empleo hombre;Empleo mujer;\n");
	String[][] values = new String[0][0];
	try {
	    values = DAO.getEmpleoComunidad();
	} catch (SQLException e) {
	    logger.error(e.getStackTrace(), e);
	}
	str.append(matrixToString(values));
	return str.toString();
    }

    private String query6() {
	StringBuffer str = new StringBuffer(
		"Código Comunidad;Rubro;Área de producción;Volumen producido (kg);Volumen producido (ud);Ingresos por venta;Consumo familiar;\n");
	String[][] values = new String[0][0];
	try {
	    values = DAO.getRubrosAgregated();
	} catch (SQLException e) {
	    logger.error(e.getStackTrace(), e);
	}
	str.append(matrixToString(values));
	return str.toString();
    }

    // private final String QUERY5 = "Listado de planificación";
    private String query5() {
	StringBuffer str = new StringBuffer(
		"Nombre del productor/a;Área total de la finca en Mz;Área para cultivos en Mz;"
			+ "Período a establecer sistema de riego;Período a establecer huerto familiar;"
			+ "Período a establecer cocina mejorada;Período a establecer filtro para aguas grises;"
			+ "Período a establecer construcción de gallineros;"
			+ "Disponibilidad de la mano de obra familiar (cantidad);"
			+ "Disponibilidad de la mano de obra familiar (período);"
			+ "Disponibilidad de la mano de obra contratada (cantidad);"
			+ "Disponibilidad de la mano de obra contratada (período);\n");
	try {
	    String[] tableNames = { InformacionGeneralForm.NAME,
		    ParcelasForm.NAME };
	    String[] schemas = { PmfConstants.DATA_SCHEMA,
		    PmfConstants.DATA_SCHEMA };
	    String[] joinFields = { "a." + InformacionGeneralForm.PKFIELD,
		    "b." + InformacionGeneralForm.PKFIELD };
	    String[] fields = { "a.nom_produ", "b.area_tot", "b.area_cul",
		    "b.p_riego", "b.p_huerto", "b.p_coc_mejo", "b.p_filtroag",
		    "b.p_galline", "b.fam_cant", "b.fam_per", "b.con_cant",
		    "b.con_per" };
	    String[][] values = session.getTableWithJoin(tableNames, schemas,
		    joinFields, fields, "", new String[0], false);
	    str.append(matrixToString(values));
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return str.toString();

    }

    // private final String QUERY4 = "Listado de fincas y cultivos";
    private String query4() {
	StringBuffer str = new StringBuffer(
		"Nombre del productor/a;Nombre de la comunidad;Nombre del municipio;Área total de la finca en Mz;"
			+ "Área para cultivos en Mz;Tipo de cultivo;Área de cultivo;\n");
	try {
	    String[] tableNames = { InformacionGeneralForm.NAME,
		    ParcelasForm.NAME, ComunidadesForm.NAME, CultivosForm.NAME };
	    String[] schemas = { PmfConstants.DATA_SCHEMA,
		    PmfConstants.DATA_SCHEMA, PmfConstants.DATA_SCHEMA,
		    PmfConstants.DATA_SCHEMA };
	    String[] joinFields = { "a." + InformacionGeneralForm.PKFIELD,
		    "b." + InformacionGeneralForm.PKFIELD,
		    "a." + ComunidadesForm.PKFIELD,
		    "c." + ComunidadesForm.PKFIELD,
		    "a." + InformacionGeneralForm.PKFIELD,
		    "d." + InformacionGeneralForm.PKFIELD };
	    String[] fields = { "a.nom_produ", "c.nombre", "c.municip",
		    "b.area_tot", "b.area_cul", "d.tipo", "d.area" };
	    String[][] values = session.getTableWithJoin(tableNames, schemas,
		    joinFields, fields, "", new String[0], false);
	    str.append(matrixToString(values));
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return str.toString();
    }

    // private final String QUERY3 = "Listado de fincas";
    private String query3() {
	StringBuffer str = new StringBuffer(
		"Nombre productor/a;Nombre de la comunidad;Nombre del municipio;Área total de la finca en Mz;"
			+ "Área para cultivos en Mz;Tipo de suelo;Nivel de degradación del suelo;Pendiente de la finca;"
			+ "Tiene sistema de riego;Tipo de prácticas conservacionistas;Tipo de abono orgánico;"
			+ "Tipo de insecticidas orgánicos;Tipo de plaguicidas químicas;\n");
	try {
	    String[] tableNames = { InformacionGeneralForm.NAME,
		    ParcelasForm.NAME, ComunidadesForm.NAME };
	    String[] schemas = { PmfConstants.DATA_SCHEMA,
		    PmfConstants.DATA_SCHEMA, PmfConstants.DATA_SCHEMA };
	    String[] joinFields = { "a." + InformacionGeneralForm.PKFIELD,
		    "b." + InformacionGeneralForm.PKFIELD,
		    "a." + ComunidadesForm.PKFIELD,
		    "c." + ComunidadesForm.PKFIELD };
	    String[] fields = { "a.nom_produ", "c.nombre", "c.municip",
		    "b.area_tot", "b.area_cul", "b.tip_suelo", "b.deg_suelo",
		    "b.pendiente", "a.sist_rieg", "b.c_conse", "b.c_aborg",
		    "b.c_insect", "b.c_quim" };
	    String[][] values = session.getTableWithJoin(tableNames, schemas,
		    joinFields, fields, "", new String[0], false);
	    str.append(matrixToString(values));
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return str.toString();
    }

    // private final String QUERY2 = "Listado de productores";
    private String query2() {
	StringBuffer str = new StringBuffer(
		"Nombre productor;Nombre comunidad;Area Cultivos;\n");
	try {
	    String[] tableNames = { InformacionGeneralForm.NAME,
		    ParcelasForm.NAME };
	    String[] schemas = { PmfConstants.DATA_SCHEMA,
		    PmfConstants.DATA_SCHEMA };
	    String[] joinFields = { "a." + InformacionGeneralForm.PKFIELD,
		    "b." + InformacionGeneralForm.PKFIELD };
	    String[] fields = { "a.nom_produ", "a.nom_com", "b.area_cul" };
	    String[][] values = session.getTableWithJoin(tableNames, schemas,
		    joinFields, fields, "", new String[0], false);
	    str.append(matrixToString(values));
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return str.toString();
    }

    // private final String QUERY1 = "Listado de comunidades";
    private String query1() {
	StringBuffer str = new StringBuffer("nombre;municipio;departamento;\n");

	try {
	    String[] fields = { "nombre", "municip", "departa" };
	    String[][] values = session.getTable(ComunidadesForm.NAME,
		    PmfConstants.DATA_SCHEMA, fields, "", new String[0], false);
	    str.append(matrixToString(values));
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return str.toString();
    }

    private String matrixToString(String[][] values) {
	String result = "";
	for (String[] row : values) {
	    for (String value : row) {
		result += value + ";";
	    }
	    result += "\n";
	}
	return result;
    }

    public void actionPerformed(ActionEvent e) {

	if (e.getSource() == dotsButton) {
	    displayFileChooser();
	} else if (e.getActionCommand() == AcceptCancelPanel.OK_ACTION_COMMAND) {
	    processQuery(directoryField.getText());
	    PluginServices.getMDIManager().closeWindow(this);
	} else if (e.getActionCommand() == AcceptCancelPanel.CANCEL_ACTION_COMMAND) {
	    PluginServices.getMDIManager().closeWindow(this);
	}

    }

}

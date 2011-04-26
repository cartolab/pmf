package es.udc.cartolab.gvsig.pmf.forms.validation.model;

import java.awt.Container;
import java.util.Map;

import es.udc.cartolab.gvsig.navtableforms.validation.FormModel;

/*
 * Some notes of JValidation framework:
 * 1) it gets the Getters and Setters methods from introspection, so the names of these methods are quite strict.
 *    i.e.:
 *    - variable "idaltrio" has to be: getIdalt and setIdalt as its getter and setter
 *    - variable "idAltrio" has to be: getIdAlt and setIdAlt as its getter and setter
 */
public class ComunidadModel extends FormModel {

	// Private variables needed for framework
	// ********************************************
	private String nombre;
	private String departa;
	private String n_habit;
	private String cod_com;
	private String municip;
	private String n_fam;

	private boolean cent_jard;
	private boolean cent_esc;
	private boolean cent_cc;
	private boolean cent_div;
	private boolean csalud;
	private boolean creunion;
	private boolean luz_elec;
	private boolean alcantar;
	private boolean tfn_fijo;
	private boolean agua_pot;

	// Constructor and initialization methods
	// *************************************************************
	public ComunidadModel(Container c) {
		super(c);
	}

	@Override
	public String getModelName() {
		return "ComunidadModel";
	}

	@Override
	protected void setDefaultValues() {
		setDefaultValuesForIntFields();
		setDefaultValuesForStringFields();
		setDefaultValuesForBooleanFields();
		setDefaultValuesForWidgetMap();
	}

	private void setDefaultValuesForWidgetMap() {
		// int values
		widgetValues.put("n_habit", n_habit);
		widgetValues.put("n_fam", n_fam);

		// string values
		widgetValues.put("nombre", nombre);
		widgetValues.put("departa", departa);
		widgetValues.put("cod_com", cod_com);
		widgetValues.put("municip", municip);

		// boolean values
		widgetValues.put("cent_jard", String.valueOf(cent_jard));
		widgetValues.put("cent_esc", String.valueOf(cent_esc));
		widgetValues.put("cent_cc", String.valueOf(cent_cc));
		widgetValues.put("cent_div", String.valueOf(cent_div));
		widgetValues.put("csalud", String.valueOf(csalud));
		widgetValues.put("creunion", String.valueOf(creunion));
		widgetValues.put("luz_elec", String.valueOf(luz_elec));
		widgetValues.put("alcantar", String.valueOf(alcantar));
		widgetValues.put("tfn_fijo", String.valueOf(tfn_fijo));
		widgetValues.put("agua_pot", String.valueOf(agua_pot));
	}

	private void setDefaultValuesForStringFields() {
		nombre = getGvsigDefaultString();
		departa = getGvsigDefaultString();
		cod_com = getGvsigDefaultString();
		municip = getGvsigDefaultString();
	}

	private void setDefaultValuesForIntFields() {
		// int values
		n_habit = Integer.toString(getGvsigDefaultInt());
		n_fam = Integer.toString(getGvsigDefaultInt());
	}

	private void setDefaultValuesForBooleanFields() {
		cent_jard = getGvsigDefaultBoolean();
		cent_esc = getGvsigDefaultBoolean();
		cent_cc = getGvsigDefaultBoolean();
		cent_div = getGvsigDefaultBoolean();
		csalud = getGvsigDefaultBoolean();
		creunion = getGvsigDefaultBoolean();
		luz_elec = getGvsigDefaultBoolean();
		alcantar = getGvsigDefaultBoolean();
		tfn_fijo = getGvsigDefaultBoolean();
		agua_pot = getGvsigDefaultBoolean();
	}

	// Getters & Setters
	// *************************************************************
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String newValue) {
		String oldValue = getNombre();
		nombre = newValue;
		firePropertyChange(PROPERTIES_MAP.get("NOMBRE"), oldValue, newValue);
	}

	public String getDeparta() {
		return departa;
	}

	public void setDeparta(String newValue) {
		String oldValue = getDeparta();
		departa = newValue;
		firePropertyChange(PROPERTIES_MAP.get("DEPARTA"), oldValue, newValue);
	}

	public String getN_habit() {
		return n_habit;
	}

	public void setN_habit(String newValue) {
		String oldValue = getN_habit();
		n_habit = newValue;
		firePropertyChange(PROPERTIES_MAP.get("N_HABIT"), oldValue, newValue);
	}

	public String getCod_com() {
		return cod_com;
	}

	public void setCod_com(String newValue) {
		String oldValue = getCod_com();
		cod_com = newValue;
		firePropertyChange(PROPERTIES_MAP.get("COD_COM"), oldValue, newValue);
	}

	public String getMunicip() {
		return municip;
	}

	public void setMunicip(String newValue) {
		String oldValue = getMunicip();
		municip = newValue;
		firePropertyChange(PROPERTIES_MAP.get("MUNICIP"), oldValue, newValue);
	}

	public String getN_fam() {
		return n_fam;
	}

	public void setN_fam(String newValue) {
		String oldValue = getN_fam();
		n_fam = newValue;
		firePropertyChange(PROPERTIES_MAP.get("N_FAM"), oldValue, newValue);
	}

	public boolean getCent_jard() {
		return cent_jard;
	}

	public void setCent_jard(boolean newValue) {
		boolean oldValue = getCent_jard();
		cent_jard = newValue;
		firePropertyChange(PROPERTIES_MAP.get("CENT_JARD"), oldValue, newValue);
	}

	public boolean getCent_esc() {
		return cent_esc;
	}

	public void setCent_esc(boolean newValue) {
		boolean oldValue = getCent_esc();
		cent_esc = newValue;
		firePropertyChange(PROPERTIES_MAP.get("CENT_ESC"), oldValue, newValue);
	}

	public boolean getCent_cc() {
		return cent_cc;
	}

	public void setCent_cc(boolean newValue) {
		boolean oldValue = getCent_cc();
		cent_cc = newValue;
		firePropertyChange(PROPERTIES_MAP.get("CENT_CC"), oldValue, newValue);
	}

	public boolean getCent_div() {
		return cent_div;
	}

	public void setCent_div(boolean newValue) {
		boolean oldValue = getCent_div();
		cent_div = newValue;
		firePropertyChange(PROPERTIES_MAP.get("CENT_DIV"), oldValue, newValue);
	}

	public boolean getCsalud() {
		return csalud;
	}

	public void setCsalud(boolean newValue) {
		boolean oldValue = getCsalud();
		csalud = newValue;
		firePropertyChange(PROPERTIES_MAP.get("CSALUD"), oldValue, newValue);
	}

	public boolean getCreunion() {
		return creunion;
	}

	public void setCreunion(boolean newValue) {
		boolean oldValue = getCreunion();
		creunion = newValue;
		firePropertyChange(PROPERTIES_MAP.get("CREUNION"), oldValue, newValue);
	}

	public boolean getLuz_elec() {
		return luz_elec;
	}

	public void setLuz_elec(boolean newValue) {
		boolean oldValue = getLuz_elec();
		luz_elec = newValue;
		firePropertyChange(PROPERTIES_MAP.get("LUZ_ELEC"), oldValue, newValue);
	}

	public boolean getAlcantar() {
		return alcantar;
	}

	public void setAlcantar(boolean newValue) {
		boolean oldValue = getAlcantar();
		alcantar = newValue;
		firePropertyChange(PROPERTIES_MAP.get("ALCANTAR"), oldValue, newValue);
	}

	public boolean getTfn_fijo() {
		return tfn_fijo;
	}

	public void setTfn_fijo(boolean newValue) {
		boolean oldValue = getTfn_fijo();
		tfn_fijo = newValue;
		firePropertyChange(PROPERTIES_MAP.get("TFN_FIJO"), oldValue, newValue);
	}

	public boolean getAgua_pot() {
		return agua_pot;
	}

	public void setAgua_pot(boolean newValue) {
		boolean oldValue = getAgua_pot();
		agua_pot = newValue;
		firePropertyChange(PROPERTIES_MAP.get("AGUA_POT"), oldValue, newValue);
	}

	// Maps
	// **************************************************************************

	// map with widget values
	@Override
	public Map<String, String> getWidgetValues() {
		// int values
		widgetValues.put("n_habit", n_habit);
		widgetValues.put("n_fam", n_fam);

		// string values
		widgetValues.put("nombre", nombre);
		widgetValues.put("departa", departa);
		widgetValues.put("cod_com", cod_com);
		widgetValues.put("municip", municip);

		// boolean values
		widgetValues.put("cent_jard", String.valueOf(cent_jard));
		widgetValues.put("cent_esc", String.valueOf(cent_esc));
		widgetValues.put("cent_cc", String.valueOf(cent_cc));
		widgetValues.put("cent_div", String.valueOf(cent_div));
		widgetValues.put("csalud", String.valueOf(csalud));
		widgetValues.put("creunion", String.valueOf(creunion));
		widgetValues.put("luz_elec", String.valueOf(luz_elec));
		widgetValues.put("alcantar", String.valueOf(alcantar));
		widgetValues.put("tfn_fijo", String.valueOf(tfn_fijo));
		widgetValues.put("agua_pot", String.valueOf(agua_pot));
		return widgetValues;
	}
}

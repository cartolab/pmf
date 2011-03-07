package es.udc.cartolab.gvsig.navtableformsexample.validation;

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

	// Private variables needed for framework ********************************************
	private String nombre;
	private String departa;
	private String n_habit;
	private String cod_com;
	private String municip;
	private String n_fam;
	private String cod_cedu;
	private String cod_csalud;
	private String cod_creu;

	private boolean cent_jard;
	private boolean cent_esc;
	private boolean cent_ccyd;
	private boolean csalud;
	private boolean creunion;
	private boolean luz_elec;
	private boolean alcantar;
	private boolean agua_pot;
	private boolean tfn_fijo;

	// Constructor and initialization methods *************************************************************
	public ComunidadModel(Container c){
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
		setDefaultValuesForWidgetMap();
	}

	private void setDefaultValuesForWidgetMap() {
		//int values
		widgetValues.put("n_habit", n_habit);
		widgetValues.put("n_fam", n_fam);

		// string values
		widgetValues.put("nombre", nombre);
		widgetValues.put("departa", departa);
		widgetValues.put("cod_com", cod_com);
		widgetValues.put("municip", municip);
		widgetValues.put("cod_cedu", cod_cedu);
		widgetValues.put("cod_cedu", cod_cedu);
		widgetValues.put("cod_cedu", cod_cedu);
		widgetValues.put("cod_csalud", cod_csalud);
		widgetValues.put("cod_creu", cod_creu);


	}

	private void setDefaultValuesForStringFields() {
		nombre = getGvsigDefaultString();
		departa = getGvsigDefaultString();
		cod_com = getGvsigDefaultString();
		municip = getGvsigDefaultString();
		cod_cedu = getGvsigDefaultString();
		cod_cedu = getGvsigDefaultString();
		cod_cedu = getGvsigDefaultString();
		cod_csalud = getGvsigDefaultString();
		cod_creu = getGvsigDefaultString();
	}

	private void setDefaultValuesForIntFields() {
		//int values
		n_habit = Integer.toString(getGvsigDefaultInt());
		n_fam = Integer.toString(getGvsigDefaultInt());
	}


	// Getters & Setters *************************************************************
	public String getNombre(){
		return nombre;
	}

	public void setNombre(String newValue) {
		String oldValue = getNombre();
		nombre = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("NOMBRE"), oldValue, newValue);
	}

	public String getDeparta(){
		return departa;
	}

	public void setDeparta(String newValue) {
		String oldValue = getDeparta();
		departa = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("DEPARTA"), oldValue, newValue);
	}

	public String getN_habit(){
		return n_habit;
	}

	public void setN_habit(String newValue) {
		String oldValue = getN_habit();
		n_habit = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("N_HABIT"), oldValue, newValue);
	}

	public String getCod_com(){
		return cod_com;
	}

	public void setCod_com(String newValue) {
		String oldValue = getCod_com();
		cod_com = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("COD_COM"), oldValue, newValue);
	}

	public String getMunicip(){
		return municip;
	}

	public void setMunicip(String newValue) {
		String oldValue = getMunicip();
		municip = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("MUNICIP"), oldValue, newValue);
	}

	public String getN_fam(){
		return n_fam;
	}

	public void setN_fam(String newValue) {
		String oldValue = getN_fam();
		n_fam = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("N_FAM"), oldValue, newValue);
	}

	public String getCod_cedu(){
		return cod_cedu;
	}

	public void setCod_cedu(String newValue) {
		String oldValue = getCod_cedu();
		cod_cedu = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("COD_CEDU"), oldValue, newValue);
	}

	public String getCod_csalud(){
		return cod_csalud;
	}

	public void setCod_csalud(String newValue) {
		String oldValue = getCod_csalud();
		cod_csalud = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("COD_CSALUD"), oldValue, newValue);
	}

	public String getCod_creu(){
		return cod_creu;
	}

	public void setCod_creu(String newValue) {
		String oldValue = getCod_creu();
		cod_creu = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("COD_CREU"), oldValue, newValue);
	}


	public boolean getCent_jard(){
		return cent_jard;
	}

	public void setCent_jard(boolean newValue) {
		boolean oldValue = getCent_jard();
		cent_jard = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("CENT_JARD"), oldValue, newValue);
	}

	public boolean getCent_esc(){
		return cent_esc;
	}

	public void setCent_esc(boolean newValue) {
		boolean oldValue = getCent_esc();
		cent_esc = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("CENT_ESC"), oldValue, newValue);
	}

	public boolean getCent_ccyd(){
		return cent_ccyd;
	}

	public void setCent_ccyd(boolean newValue) {
		boolean oldValue = getCent_ccyd();
		cent_ccyd = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("CENT_CCYD"), oldValue, newValue);
	}

	public boolean getCsalud(){
		return csalud;
	}

	public void setCsalud(boolean newValue) {
		boolean oldValue = getCsalud();
		csalud = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("CSALUD"), oldValue, newValue);
	}

	public boolean getCreunion(){
		return creunion;
	}

	public void setCreunion(boolean newValue) {
		boolean oldValue = getCreunion();
		creunion = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("CREUNION"), oldValue, newValue);
	}

	public boolean getLuz_elec(){
		return luz_elec;
	}

	public void setLuz_elec(boolean newValue) {
		boolean oldValue = getLuz_elec();
		luz_elec = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("LUZ_ELEC"), oldValue, newValue);
	}

	public boolean getAlcantar(){
		return alcantar;
	}

	public void setAlcantar(boolean newValue) {
		boolean oldValue = getAlcantar();
		alcantar = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("ALCANTAR"), oldValue, newValue);
	}

	public boolean getAgua_pot(){
		return agua_pot;
	}

	public void setAgua_pot(boolean newValue) {
		boolean oldValue = getAgua_pot();
		agua_pot = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("AGUA_POT"), oldValue, newValue);
	}

	public boolean getTfn_fijo(){
		return tfn_fijo;
	}

	public void setTfn_fijo(boolean newValue) {
		boolean oldValue = getTfn_fijo();
		tfn_fijo = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("TFN_FIJO"), oldValue, newValue);
	}


	// Maps **************************************************************************

	/*
	//map with non-widget values
	public Map<String, String> getNonwidgetvalues(){
		return nonWidgetValues;
	}

	public String getNonwidgetvalues(String key) {
		return nonWidgetValues.get(key);
	}

	public void setNonwidgetvalues(String key, String newValue){
		nonWidgetValues.put(key, newValue);
	}*/

	//map with widget values
	@Override
	public Map<String, String> getWidgetValues() {
		widgetValues.put("nombre", nombre);
		widgetValues.put("departa", departa);
		widgetValues.put("n_habit", n_habit);
		widgetValues.put("cod_com", cod_com);
		widgetValues.put("municip", municip);
		widgetValues.put("n_fam", n_fam);
		widgetValues.put("cod_cedu", cod_cedu);
		widgetValues.put("cod_cedu", cod_cedu);
		widgetValues.put("cod_cedu", cod_cedu);
		widgetValues.put("cod_csalud", cod_csalud);
		widgetValues.put("cod_creu", cod_creu);
		widgetValues.put("cent_jard", String.valueOf(cent_jard));
		widgetValues.put("cent_esc", String.valueOf(cent_esc));
		widgetValues.put("cent_ccyd", String.valueOf(cent_ccyd));
		widgetValues.put("csalud", String.valueOf(csalud));
		widgetValues.put("creunion", String.valueOf(creunion));
		widgetValues.put("luz_elec", String.valueOf(luz_elec));
		widgetValues.put("alcantar", String.valueOf(alcantar));
		widgetValues.put("agua_pot", String.valueOf(agua_pot));
		widgetValues.put("tfn_fijo", String.valueOf(tfn_fijo));

		return widgetValues;
	}

	// maps for every layer
	/*
	// map with widget values
	@Override
	public Map<String, String> getWidgetValues() {
		return widgetValues;
	}*/
}

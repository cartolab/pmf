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
public class CentroEducativoModel extends FormModel {

	// Private variables needed for framework ********************************************
	private String x;
	private String y;
	private String z;
	private String i_deserc;
	private String n_ninhas;
	private String n_ninhos;
	private String nom_cedu;
	private String cod_com;
	private String cod_cedu;
	private String tipo_cedu;

	private boolean mer_escol;

	// Constructor and initialization methods *************************************************************
	public CentroEducativoModel(Container c){
		super(c);
	}

	@Override
	public String getModelName() {
		return "CentroEducativoModel";
	}

	@Override
	protected void setDefaultValues() {
		setDefaultValuesForIntFields();
		setDefaultValuesForStringFields();
		setDefaultValuesForWidgetMap();
	}

	private void setDefaultValuesForWidgetMap() {
		//int values
		widgetValues.put("n_ninhas", n_ninhas);
		widgetValues.put("n_ninhos", n_ninhos);
		widgetValues.put("x", x);
		widgetValues.put("y", y);
		widgetValues.put("z", z);
		widgetValues.put("i_deserc", i_deserc);

		// string values
		widgetValues.put("nom_cedu", nom_cedu);
		widgetValues.put("cod_com", cod_com);
		widgetValues.put("cod_cedu", cod_cedu);
		widgetValues.put("tipo_cedu", tipo_cedu);


	}

	private void setDefaultValuesForStringFields() {
		nom_cedu = getGvsigDefaultString();
		cod_com = getGvsigDefaultString();
		cod_cedu = getGvsigDefaultString();
		tipo_cedu = getGvsigDefaultString();
	}

	private void setDefaultValuesForIntFields() {
		//int values
		n_ninhas = Integer.toString(getGvsigDefaultInt());
		n_ninhos = Integer.toString(getGvsigDefaultInt());
		x = Integer.toString(getGvsigDefaultInt());
		y = Integer.toString(getGvsigDefaultInt());
		z = Integer.toString(getGvsigDefaultInt());
		i_deserc = Integer.toString(getGvsigDefaultInt());
	}


	// Getters & Setters *************************************************************
	public String getX(){
		return x;
	}

	public void setX(String newValue) {
		String oldValue = getX();
		x = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("X"), oldValue, newValue);
	}

	public String getY(){
		return y;
	}

	public void setY(String newValue) {
		String oldValue = getY();
		y = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("Y"), oldValue, newValue);
	}

	public String getZ(){
		return z;
	}

	public void setZ(String newValue) {
		String oldValue = getZ();
		z = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("Z"), oldValue, newValue);
	}

	public String getI_deserc(){
		return i_deserc;
	}

	public void setI_deserc(String newValue) {
		String oldValue = getI_deserc();
		i_deserc = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("I_DESERC"), oldValue, newValue);
	}

	public String getN_ninhas(){
		return n_ninhas;
	}

	public void setN_ninhas(String newValue) {
		String oldValue = getN_ninhas();
		n_ninhas = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("N_NINHAS"), oldValue, newValue);
	}

	public String getN_ninhos(){
		return n_ninhos;
	}

	public void setN_ninhos(String newValue) {
		String oldValue = getN_ninhos();
		n_ninhos = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("N_NINHOS"), oldValue, newValue);
	}

	public String getNom_cedu(){
		return nom_cedu;
	}

	public void setNom_cedu(String newValue) {
		String oldValue = getNom_cedu();
		nom_cedu = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("NOM_CEDU"), oldValue, newValue);
	}

	public String getCod_com(){
		return cod_com;
	}

	public void setCod_com(String newValue) {
		String oldValue = getCod_com();
		cod_com = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("COD_COM"), oldValue, newValue);
	}

	public String getCod_cedu(){
		return cod_cedu;
	}

	public void setCod_cedu(String newValue) {
		String oldValue = getCod_cedu();
		cod_cedu = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("COD_CEDU"), oldValue, newValue);
	}

	public String getTipo_cedu(){
		return tipo_cedu;
	}

	public void setTipo_cedu(String newValue) {
		String oldValue = getTipo_cedu();
		tipo_cedu = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("TIPO_CEDU"), oldValue, newValue);
	}


	public boolean getMer_escol(){
		return mer_escol;
	}

	public void setMer_escol(boolean newValue) {
		boolean oldValue = getMer_escol();
		mer_escol = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("MER_ESCOL"), oldValue, newValue);
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
		widgetValues.put("x", x);
		widgetValues.put("y", y);
		widgetValues.put("z", z);
		widgetValues.put("i_deserc", i_deserc);
		widgetValues.put("n_ninhas", n_ninhas);
		widgetValues.put("n_ninhos", n_ninhos);
		widgetValues.put("nom_cedu", nom_cedu);
		widgetValues.put("cod_com", cod_com);
		widgetValues.put("cod_cedu", cod_cedu);
		widgetValues.put("tipo_cedu", tipo_cedu);
		widgetValues.put("mer_escol", String.valueOf(mer_escol));

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

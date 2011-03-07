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
public class CentroSaludModel extends FormModel {

	// Private variables needed for framework ********************************************
	private String y;
	private String x;
	private String cod_csalud;
	private String cod_com;
	private String z;
	private String edad_men5;
	private String edad_may5;
	private String nom_csalud;

	private boolean inf_resp;
	private boolean inf_piel;
	private boolean inf_inst;
	private boolean inf_vec;

	// Constructor and initialization methods *************************************************************
	public CentroSaludModel(Container c){
		super(c);
	}

	@Override
	public String getModelName() {
		return "CentroSaludModel";
	}

	@Override
	protected void setDefaultValues() {
		setDefaultValuesForIntFields();
		setDefaultValuesForStringFields();
		setDefaultValuesForWidgetMap();
	}

	private void setDefaultValuesForWidgetMap() {
		//int values
		widgetValues.put("edad_men5", edad_men5);
		widgetValues.put("edad_may5", edad_may5);
		widgetValues.put("y", y);
		widgetValues.put("x", x);
		widgetValues.put("z", z);

		// string values
		widgetValues.put("cod_csalud", cod_csalud);
		widgetValues.put("cod_com", cod_com);
		widgetValues.put("nom_csalud", nom_csalud);


	}

	private void setDefaultValuesForStringFields() {
		cod_csalud = getGvsigDefaultString();
		cod_com = getGvsigDefaultString();
		nom_csalud = getGvsigDefaultString();
	}

	private void setDefaultValuesForIntFields() {
		//int values
		edad_men5 = Integer.toString(getGvsigDefaultInt());
		edad_may5 = Integer.toString(getGvsigDefaultInt());
		y = Integer.toString(getGvsigDefaultInt());
		x = Integer.toString(getGvsigDefaultInt());
		z = Integer.toString(getGvsigDefaultInt());
	}


	// Getters & Setters *************************************************************
	public String getY(){
		return y;
	}

	public void setY(String newValue) {
		String oldValue = getY();
		y = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("Y"), oldValue, newValue);
	}

	public String getX(){
		return x;
	}

	public void setX(String newValue) {
		String oldValue = getX();
		x = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("X"), oldValue, newValue);
	}

	public String getCod_csalud(){
		return cod_csalud;
	}

	public void setCod_csalud(String newValue) {
		String oldValue = getCod_csalud();
		cod_csalud = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("COD_CSALUD"), oldValue, newValue);
	}

	public String getCod_com(){
		return cod_com;
	}

	public void setCod_com(String newValue) {
		String oldValue = getCod_com();
		cod_com = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("COD_COM"), oldValue, newValue);
	}

	public String getZ(){
		return z;
	}

	public void setZ(String newValue) {
		String oldValue = getZ();
		z = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("Z"), oldValue, newValue);
	}

	public String getEdad_men5(){
		return edad_men5;
	}

	public void setEdad_men5(String newValue) {
		String oldValue = getEdad_men5();
		edad_men5 = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("EDAD_MEN5"), oldValue, newValue);
	}

	public String getEdad_may5(){
		return edad_may5;
	}

	public void setEdad_may5(String newValue) {
		String oldValue = getEdad_may5();
		edad_may5 = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("EDAD_MAY5"), oldValue, newValue);
	}

	public String getNom_csalud(){
		return nom_csalud;
	}

	public void setNom_csalud(String newValue) {
		String oldValue = getNom_csalud();
		nom_csalud = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("NOM_CSALUD"), oldValue, newValue);
	}


	public boolean getInf_resp(){
		return inf_resp;
	}

	public void setInf_resp(boolean newValue) {
		boolean oldValue = getInf_resp();
		inf_resp = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("INF_RESP"), oldValue, newValue);
	}

	public boolean getInf_piel(){
		return inf_piel;
	}

	public void setInf_piel(boolean newValue) {
		boolean oldValue = getInf_piel();
		inf_piel = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("INF_PIEL"), oldValue, newValue);
	}

	public boolean getInf_inst(){
		return inf_inst;
	}

	public void setInf_inst(boolean newValue) {
		boolean oldValue = getInf_inst();
		inf_inst = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("INF_INST"), oldValue, newValue);
	}

	public boolean getInf_vec(){
		return inf_vec;
	}

	public void setInf_vec(boolean newValue) {
		boolean oldValue = getInf_vec();
		inf_vec = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("INF_VEC"), oldValue, newValue);
	}


	// Maps **************************************************************************

	/*	//map with non-widget values
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
		widgetValues.put("y", y);
		widgetValues.put("x", x);
		widgetValues.put("cod_csalud", cod_csalud);
		widgetValues.put("cod_com", cod_com);
		widgetValues.put("z", z);
		widgetValues.put("edad_men5", edad_men5);
		widgetValues.put("edad_may5", edad_may5);
		widgetValues.put("nom_csalud", nom_csalud);
		widgetValues.put("inf_resp", String.valueOf(inf_resp));
		widgetValues.put("inf_piel", String.valueOf(inf_piel));
		widgetValues.put("inf_inst", String.valueOf(inf_inst));
		widgetValues.put("inf_vec", String.valueOf(inf_vec));

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

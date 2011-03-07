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
public class CentroComunalModel extends FormModel {

	// Private variables needed for framework ********************************************
	private String x;
	private String y;
	private String cod_creu;
	private String cod_com;
	private String nom_creu;
	private String direccion;
	private String responsa;
	private String z;


	// Constructor and initialization methods *************************************************************
	public CentroComunalModel(Container c){
		super(c);
	}

	@Override
	public String getModelName() {
		return "CentroComunalModel";
	}

	@Override
	protected void setDefaultValues() {
		setDefaultValuesForIntFields();
		setDefaultValuesForStringFields();
		setDefaultValuesForWidgetMap();
	}

	private void setDefaultValuesForWidgetMap() {
		//int values
		widgetValues.put("x", x);
		widgetValues.put("y", y);
		widgetValues.put("z", z);

		// string values
		widgetValues.put("cod_creu", cod_creu);
		widgetValues.put("cod_com", cod_com);
		widgetValues.put("nom_creu", nom_creu);
		widgetValues.put("direccion", direccion);
		widgetValues.put("responsa", responsa);


	}

	private void setDefaultValuesForStringFields() {
		cod_creu = getGvsigDefaultString();
		cod_com = getGvsigDefaultString();
		nom_creu = getGvsigDefaultString();
		direccion = getGvsigDefaultString();
		responsa = getGvsigDefaultString();
	}

	private void setDefaultValuesForIntFields() {
		//int values
		x = Integer.toString(getGvsigDefaultInt());
		y = Integer.toString(getGvsigDefaultInt());
		z = Integer.toString(getGvsigDefaultInt());
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

	public String getCod_creu(){
		return cod_creu;
	}

	public void setCod_creu(String newValue) {
		String oldValue = getCod_creu();
		cod_creu = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("COD_CREU"), oldValue, newValue);
	}

	public String getCod_com(){
		return cod_com;
	}

	public void setCod_com(String newValue) {
		String oldValue = getCod_com();
		cod_com = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("COD_COM"), oldValue, newValue);
	}

	public String getNom_creu(){
		return nom_creu;
	}

	public void setNom_creu(String newValue) {
		String oldValue = getNom_creu();
		nom_creu = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("NOM_CREU"), oldValue, newValue);
	}

	public String getDireccion(){
		return direccion;
	}

	public void setDireccion(String newValue) {
		String oldValue = getDireccion();
		direccion = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("DIRECCION"), oldValue, newValue);
	}

	public String getResponsa(){
		return responsa;
	}

	public void setResponsa(String newValue) {
		String oldValue = getResponsa();
		responsa = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("RESPONSA"), oldValue, newValue);
	}

	public String getZ(){
		return z;
	}

	public void setZ(String newValue) {
		String oldValue = getZ();
		z = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("Z"), oldValue, newValue);
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
		widgetValues.put("cod_creu", cod_creu);
		widgetValues.put("cod_com", cod_com);
		widgetValues.put("nom_creu", nom_creu);
		widgetValues.put("direccion", direccion);
		widgetValues.put("responsa", responsa);
		widgetValues.put("z", z);

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

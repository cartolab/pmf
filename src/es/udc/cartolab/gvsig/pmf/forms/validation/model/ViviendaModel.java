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
public class ViviendaModel extends FormModel {

	// Private variables needed for framework ********************************************
	private String nom_com;
	private String cod_viv;
	private String cod_com;
	private String n_ninhos;
	private String n_ninhas;
	private String n_mujer;
	private String n_hombr;
	private String edad_produ;
	private String n_personas;
	private String direccion;
	private String nid_produ;
	private String n_embaraz;
	private String nom_produ;
	private String otros_ing;
	private String otras_act;
	private String n_migrante;
	private String x;
	private String y;
	private String ot_stat_vi;
	private String ot_mat_pa;
	private String ot_mat_te;
	private String ot_mat_pi;
	private String z;
	private String ot_dep_alm;
	private String cual_ch;
	private String tip_migra;
	private String mig_dest;
	private String estatus_vi;
	private String mat_pared;
	private String mat_techo;
	private String mat_piso;
	private String pro_vivsex;

	private boolean agricult;
	private boolean comercio;
	private boolean hay_ot_act;
	private boolean remesas;
	private boolean hay_ot_ing;
	private boolean e_temporal;
	private boolean ganaderia;
	private boolean e_perman;
	private boolean pro_viv;
	private boolean luz_elec;
	private boolean telefono;
	private boolean agua_pot;
	private boolean alcantar;
	private boolean hay_in_viv;
	private boolean alum_publ;
	private boolean silos;
	private boolean trojas_mej;
	private boolean sacos;
	private boolean coc_mejor;
	private boolean filtro_ag;
	private boolean gallinero;
	private boolean ramadas;
	private boolean letrina;
	private boolean consumo_h;
	private boolean sist_rieg;
	private boolean cap_techo;
	private boolean dep_almac;

	// Constructor and initialization methods *************************************************************
	public ViviendaModel(Container c){
		super(c);
	}

	@Override
	public String getModelName() {
		return "ViviendaModel";
	}

	@Override
	protected void setDefaultValues() {
		setDefaultValuesForIntFields();
		setDefaultValuesForStringFields();
		setDefaultValuesForWidgetMap();
	}

	private void setDefaultValuesForWidgetMap() {
		//int values
		widgetValues.put("n_ninhos", n_ninhos);
		widgetValues.put("n_ninhas", n_ninhas);
		widgetValues.put("n_mujer", n_mujer);
		widgetValues.put("n_hombr", n_hombr);
		widgetValues.put("edad_produ", edad_produ);
		widgetValues.put("n_personas", n_personas);
		widgetValues.put("nid_produ", nid_produ);
		widgetValues.put("n_embaraz", n_embaraz);
		widgetValues.put("n_migrante", n_migrante);
		widgetValues.put("x", x);
		widgetValues.put("y", y);
		widgetValues.put("z", z);

		// string values
		widgetValues.put("nom_com", nom_com);
		widgetValues.put("cod_viv", cod_viv);
		widgetValues.put("cod_com", cod_com);
		widgetValues.put("direccion", direccion);
		widgetValues.put("nom_produ", nom_produ);
		widgetValues.put("otros_ing", otros_ing);
		widgetValues.put("otras_act", otras_act);
		widgetValues.put("ot_stat_vi", ot_stat_vi);
		widgetValues.put("ot_mat_pa", ot_mat_pa);
		widgetValues.put("ot_mat_te", ot_mat_te);
		widgetValues.put("ot_mat_pi", ot_mat_pi);
		widgetValues.put("ot_dep_alm", ot_dep_alm);
		widgetValues.put("cual_ch", cual_ch);
		widgetValues.put("tip_migra", tip_migra);
		widgetValues.put("mig_dest", mig_dest);
		widgetValues.put("estatus_vi", estatus_vi);
		widgetValues.put("mat_pared", mat_pared);
		widgetValues.put("mat_techo", mat_techo);
		widgetValues.put("mat_piso", mat_piso);
		widgetValues.put("pro_vivsex", pro_vivsex);


	}

	private void setDefaultValuesForStringFields() {
		nom_com = getGvsigDefaultString();
		cod_viv = getGvsigDefaultString();
		cod_com = getGvsigDefaultString();
		direccion = getGvsigDefaultString();
		nom_produ = getGvsigDefaultString();
		otros_ing = getGvsigDefaultString();
		otras_act = getGvsigDefaultString();
		ot_stat_vi = getGvsigDefaultString();
		ot_mat_pa = getGvsigDefaultString();
		ot_mat_te = getGvsigDefaultString();
		ot_mat_pi = getGvsigDefaultString();
		ot_dep_alm = getGvsigDefaultString();
		cual_ch = getGvsigDefaultString();
		tip_migra = getGvsigDefaultString();
		mig_dest = getGvsigDefaultString();
		estatus_vi = getGvsigDefaultString();
		mat_pared = getGvsigDefaultString();
		mat_techo = getGvsigDefaultString();
		mat_piso = getGvsigDefaultString();
		pro_vivsex = getGvsigDefaultString();
	}

	private void setDefaultValuesForIntFields() {
		//int values
		n_ninhos = Integer.toString(getGvsigDefaultInt());
		n_ninhas = Integer.toString(getGvsigDefaultInt());
		n_mujer = Integer.toString(getGvsigDefaultInt());
		n_hombr = Integer.toString(getGvsigDefaultInt());
		edad_produ = Integer.toString(getGvsigDefaultInt());
		n_personas = Integer.toString(getGvsigDefaultInt());
		nid_produ = Integer.toString(getGvsigDefaultInt());
		n_embaraz = Integer.toString(getGvsigDefaultInt());
		n_migrante = Integer.toString(getGvsigDefaultInt());
		x = Integer.toString(getGvsigDefaultInt());
		y = Integer.toString(getGvsigDefaultInt());
		z = Integer.toString(getGvsigDefaultInt());
	}


	// Getters & Setters *************************************************************
	public String getNom_com(){
		return nom_com;
	}

	public void setNom_com(String newValue) {
		String oldValue = getNom_com();
		nom_com = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("NOM_COM"), oldValue, newValue);
	}

	public String getCod_viv(){
		return cod_viv;
	}

	public void setCod_viv(String newValue) {
		String oldValue = getCod_viv();
		cod_viv = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("COD_VIV"), oldValue, newValue);
	}

	public String getCod_com(){
		return cod_com;
	}

	public void setCod_com(String newValue) {
		String oldValue = getCod_com();
		cod_com = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("COD_COM"), oldValue, newValue);
	}

	public String getN_ninhos(){
		return n_ninhos;
	}

	public void setN_ninhos(String newValue) {
		String oldValue = getN_ninhos();
		n_ninhos = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("N_NINHOS"), oldValue, newValue);
	}

	public String getN_ninhas(){
		return n_ninhas;
	}

	public void setN_ninhas(String newValue) {
		String oldValue = getN_ninhas();
		n_ninhas = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("N_NINHAS"), oldValue, newValue);
	}

	public String getN_mujer(){
		return n_mujer;
	}

	public void setN_mujer(String newValue) {
		String oldValue = getN_mujer();
		n_mujer = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("N_MUJER"), oldValue, newValue);
	}

	public String getN_hombr(){
		return n_hombr;
	}

	public void setN_hombr(String newValue) {
		String oldValue = getN_hombr();
		n_hombr = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("N_HOMBR"), oldValue, newValue);
	}

	public String getEdad_produ(){
		return edad_produ;
	}

	public void setEdad_produ(String newValue) {
		String oldValue = getEdad_produ();
		edad_produ = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("EDAD_PRODU"), oldValue, newValue);
	}

	public String getN_personas(){
		return n_personas;
	}

	public void setN_personas(String newValue) {
		String oldValue = getN_personas();
		n_personas = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("N_PERSONAS"), oldValue, newValue);
	}

	public String getDireccion(){
		return direccion;
	}

	public void setDireccion(String newValue) {
		String oldValue = getDireccion();
		direccion = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("DIRECCION"), oldValue, newValue);
	}

	public String getNid_produ(){
		return nid_produ;
	}

	public void setNid_produ(String newValue) {
		String oldValue = getNid_produ();
		nid_produ = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("NID_PRODU"), oldValue, newValue);
	}

	public String getN_embaraz(){
		return n_embaraz;
	}

	public void setN_embaraz(String newValue) {
		String oldValue = getN_embaraz();
		n_embaraz = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("N_EMBARAZ"), oldValue, newValue);
	}

	public String getNom_produ(){
		return nom_produ;
	}

	public void setNom_produ(String newValue) {
		String oldValue = getNom_produ();
		nom_produ = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("NOM_PRODU"), oldValue, newValue);
	}

	public String getOtros_ing(){
		return otros_ing;
	}

	public void setOtros_ing(String newValue) {
		String oldValue = getOtros_ing();
		otros_ing = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("OTROS_ING"), oldValue, newValue);
	}

	public String getOtras_act(){
		return otras_act;
	}

	public void setOtras_act(String newValue) {
		String oldValue = getOtras_act();
		otras_act = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("OTRAS_ACT"), oldValue, newValue);
	}

	public String getN_migrante(){
		return n_migrante;
	}

	public void setN_migrante(String newValue) {
		String oldValue = getN_migrante();
		n_migrante = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("N_MIGRANTE"), oldValue, newValue);
	}

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

	public String getOt_stat_vi(){
		return ot_stat_vi;
	}

	public void setOt_stat_vi(String newValue) {
		String oldValue = getOt_stat_vi();
		ot_stat_vi = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("OT_STAT_VI"), oldValue, newValue);
	}

	public String getOt_mat_pa(){
		return ot_mat_pa;
	}

	public void setOt_mat_pa(String newValue) {
		String oldValue = getOt_mat_pa();
		ot_mat_pa = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("OT_MAT_PA"), oldValue, newValue);
	}

	public String getOt_mat_te(){
		return ot_mat_te;
	}

	public void setOt_mat_te(String newValue) {
		String oldValue = getOt_mat_te();
		ot_mat_te = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("OT_MAT_TE"), oldValue, newValue);
	}

	public String getOt_mat_pi(){
		return ot_mat_pi;
	}

	public void setOt_mat_pi(String newValue) {
		String oldValue = getOt_mat_pi();
		ot_mat_pi = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("OT_MAT_PI"), oldValue, newValue);
	}

	public String getZ(){
		return z;
	}

	public void setZ(String newValue) {
		String oldValue = getZ();
		z = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("Z"), oldValue, newValue);
	}

	public String getOt_dep_alm(){
		return ot_dep_alm;
	}

	public void setOt_dep_alm(String newValue) {
		String oldValue = getOt_dep_alm();
		ot_dep_alm = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("OT_DEP_ALM"), oldValue, newValue);
	}

	public String getCual_ch(){
		return cual_ch;
	}

	public void setCual_ch(String newValue) {
		String oldValue = getCual_ch();
		cual_ch = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("CUAL_CH"), oldValue, newValue);
	}

	public String getTip_migra(){
		return tip_migra;
	}

	public void setTip_migra(String newValue) {
		String oldValue = getTip_migra();
		tip_migra = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("TIP_MIGRA"), oldValue, newValue);
	}

	public String getMig_dest(){
		return mig_dest;
	}

	public void setMig_dest(String newValue) {
		String oldValue = getMig_dest();
		mig_dest = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("MIG_DEST"), oldValue, newValue);
	}

	public String getEstatus_vi(){
		return estatus_vi;
	}

	public void setEstatus_vi(String newValue) {
		String oldValue = getEstatus_vi();
		estatus_vi = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("ESTATUS_VI"), oldValue, newValue);
	}

	public String getMat_pared(){
		return mat_pared;
	}

	public void setMat_pared(String newValue) {
		String oldValue = getMat_pared();
		mat_pared = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("MAT_PARED"), oldValue, newValue);
	}

	public String getMat_techo(){
		return mat_techo;
	}

	public void setMat_techo(String newValue) {
		String oldValue = getMat_techo();
		mat_techo = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("MAT_TECHO"), oldValue, newValue);
	}

	public String getMat_piso(){
		return mat_piso;
	}

	public void setMat_piso(String newValue) {
		String oldValue = getMat_piso();
		mat_piso = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("MAT_PISO"), oldValue, newValue);
	}

	public String getPro_vivsex(){
		return pro_vivsex;
	}

	public void setPro_vivsex(String newValue) {
		String oldValue = getPro_vivsex();
		pro_vivsex = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("PRO_VIVSEX"), oldValue, newValue);
	}


	public boolean getAgricult(){
		return agricult;
	}

	public void setAgricult(boolean newValue) {
		boolean oldValue = getAgricult();
		agricult = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("AGRICULT"), oldValue, newValue);
	}

	public boolean getComercio(){
		return comercio;
	}

	public void setComercio(boolean newValue) {
		boolean oldValue = getComercio();
		comercio = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("COMERCIO"), oldValue, newValue);
	}

	public boolean getHay_ot_act(){
		return hay_ot_act;
	}

	public void setHay_ot_act(boolean newValue) {
		boolean oldValue = getHay_ot_act();
		hay_ot_act = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("HAY_OT_ACT"), oldValue, newValue);
	}

	public boolean getRemesas(){
		return remesas;
	}

	public void setRemesas(boolean newValue) {
		boolean oldValue = getRemesas();
		remesas = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("REMESAS"), oldValue, newValue);
	}

	public boolean getHay_ot_ing(){
		return hay_ot_ing;
	}

	public void setHay_ot_ing(boolean newValue) {
		boolean oldValue = getHay_ot_ing();
		hay_ot_ing = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("HAY_OT_ING"), oldValue, newValue);
	}

	public boolean getE_temporal(){
		return e_temporal;
	}

	public void setE_temporal(boolean newValue) {
		boolean oldValue = getE_temporal();
		e_temporal = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("E_TEMPORAL"), oldValue, newValue);
	}

	public boolean getGanaderia(){
		return ganaderia;
	}

	public void setGanaderia(boolean newValue) {
		boolean oldValue = getGanaderia();
		ganaderia = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("GANADERIA"), oldValue, newValue);
	}

	public boolean getE_perman(){
		return e_perman;
	}

	public void setE_perman(boolean newValue) {
		boolean oldValue = getE_perman();
		e_perman = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("E_PERMAN"), oldValue, newValue);
	}

	public boolean getPro_viv(){
		return pro_viv;
	}

	public void setPro_viv(boolean newValue) {
		boolean oldValue = getPro_viv();
		pro_viv = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("PRO_VIV"), oldValue, newValue);
	}

	public boolean getLuz_elec(){
		return luz_elec;
	}

	public void setLuz_elec(boolean newValue) {
		boolean oldValue = getLuz_elec();
		luz_elec = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("LUZ_ELEC"), oldValue, newValue);
	}

	public boolean getTelefono(){
		return telefono;
	}

	public void setTelefono(boolean newValue) {
		boolean oldValue = getTelefono();
		telefono = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("TELEFONO"), oldValue, newValue);
	}

	public boolean getAgua_pot(){
		return agua_pot;
	}

	public void setAgua_pot(boolean newValue) {
		boolean oldValue = getAgua_pot();
		agua_pot = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("AGUA_POT"), oldValue, newValue);
	}

	public boolean getAlcantar(){
		return alcantar;
	}

	public void setAlcantar(boolean newValue) {
		boolean oldValue = getAlcantar();
		alcantar = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("ALCANTAR"), oldValue, newValue);
	}

	public boolean getHay_in_viv(){
		return hay_in_viv;
	}

	public void setHay_in_viv(boolean newValue) {
		boolean oldValue = getHay_in_viv();
		hay_in_viv = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("HAY_IN_VIV"), oldValue, newValue);
	}

	public boolean getAlum_publ(){
		return alum_publ;
	}

	public void setAlum_publ(boolean newValue) {
		boolean oldValue = getAlum_publ();
		alum_publ = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("ALUM_PUBL"), oldValue, newValue);
	}

	public boolean getSilos(){
		return silos;
	}

	public void setSilos(boolean newValue) {
		boolean oldValue = getSilos();
		silos = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("SILOS"), oldValue, newValue);
	}

	public boolean getTrojas_mej(){
		return trojas_mej;
	}

	public void setTrojas_mej(boolean newValue) {
		boolean oldValue = getTrojas_mej();
		trojas_mej = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("TROJAS_MEJ"), oldValue, newValue);
	}

	public boolean getSacos(){
		return sacos;
	}

	public void setSacos(boolean newValue) {
		boolean oldValue = getSacos();
		sacos = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("SACOS"), oldValue, newValue);
	}

	public boolean getCoc_mejor(){
		return coc_mejor;
	}

	public void setCoc_mejor(boolean newValue) {
		boolean oldValue = getCoc_mejor();
		coc_mejor = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("COC_MEJOR"), oldValue, newValue);
	}

	public boolean getFiltro_ag(){
		return filtro_ag;
	}

	public void setFiltro_ag(boolean newValue) {
		boolean oldValue = getFiltro_ag();
		filtro_ag = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("FILTRO_AG"), oldValue, newValue);
	}

	public boolean getGallinero(){
		return gallinero;
	}

	public void setGallinero(boolean newValue) {
		boolean oldValue = getGallinero();
		gallinero = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("GALLINERO"), oldValue, newValue);
	}

	public boolean getRamadas(){
		return ramadas;
	}

	public void setRamadas(boolean newValue) {
		boolean oldValue = getRamadas();
		ramadas = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("RAMADAS"), oldValue, newValue);
	}

	public boolean getLetrina(){
		return letrina;
	}

	public void setLetrina(boolean newValue) {
		boolean oldValue = getLetrina();
		letrina = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("LETRINA"), oldValue, newValue);
	}

	public boolean getConsumo_h(){
		return consumo_h;
	}

	public void setConsumo_h(boolean newValue) {
		boolean oldValue = getConsumo_h();
		consumo_h = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("CONSUMO_H"), oldValue, newValue);
	}

	public boolean getSist_rieg(){
		return sist_rieg;
	}

	public void setSist_rieg(boolean newValue) {
		boolean oldValue = getSist_rieg();
		sist_rieg = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("SIST_RIEG"), oldValue, newValue);
	}

	public boolean getCap_techo(){
		return cap_techo;
	}

	public void setCap_techo(boolean newValue) {
		boolean oldValue = getCap_techo();
		cap_techo = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("CAP_TECHO"), oldValue, newValue);
	}

	public boolean getDep_almac(){
		return dep_almac;
	}

	public void setDep_almac(boolean newValue) {
		boolean oldValue = getDep_almac();
		dep_almac = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("DEP_ALMAC"), oldValue, newValue);
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
	public Map<String, String> getWidgetValues(){
		widgetValues.put("nom_com", nom_com);
		widgetValues.put("cod_viv", cod_viv);
		widgetValues.put("cod_com", cod_com);
		widgetValues.put("n_ninhos", n_ninhos);
		widgetValues.put("n_ninhas", n_ninhas);
		widgetValues.put("n_mujer", n_mujer);
		widgetValues.put("n_hombr", n_hombr);
		widgetValues.put("edad_produ", edad_produ);
		widgetValues.put("n_personas", n_personas);
		widgetValues.put("direccion", direccion);
		widgetValues.put("nid_produ", nid_produ);
		widgetValues.put("n_embaraz", n_embaraz);
		widgetValues.put("nom_produ", nom_produ);
		widgetValues.put("otros_ing", otros_ing);
		widgetValues.put("otras_act", otras_act);
		widgetValues.put("n_migrante", n_migrante);
		widgetValues.put("x", x);
		widgetValues.put("y", y);
		widgetValues.put("ot_stat_vi", ot_stat_vi);
		widgetValues.put("ot_mat_pa", ot_mat_pa);
		widgetValues.put("ot_mat_te", ot_mat_te);
		widgetValues.put("ot_mat_pi", ot_mat_pi);
		widgetValues.put("z", z);
		widgetValues.put("ot_dep_alm", ot_dep_alm);
		widgetValues.put("cual_ch", cual_ch);
		widgetValues.put("tip_migra", tip_migra);
		widgetValues.put("mig_dest", mig_dest);
		widgetValues.put("estatus_vi", estatus_vi);
		widgetValues.put("mat_pared", mat_pared);
		widgetValues.put("mat_techo", mat_techo);
		widgetValues.put("mat_piso", mat_piso);
		widgetValues.put("pro_vivsex", pro_vivsex);
		widgetValues.put("agricult", String.valueOf(agricult));
		widgetValues.put("comercio", String.valueOf(comercio));
		widgetValues.put("hay_ot_act", String.valueOf(hay_ot_act));
		widgetValues.put("remesas", String.valueOf(remesas));
		widgetValues.put("hay_ot_ing", String.valueOf(hay_ot_ing));
		widgetValues.put("e_temporal", String.valueOf(e_temporal));
		widgetValues.put("ganaderia", String.valueOf(ganaderia));
		widgetValues.put("e_perman", String.valueOf(e_perman));
		widgetValues.put("pro_viv", String.valueOf(pro_viv));
		widgetValues.put("luz_elec", String.valueOf(luz_elec));
		widgetValues.put("telefono", String.valueOf(telefono));
		widgetValues.put("agua_pot", String.valueOf(agua_pot));
		widgetValues.put("alcantar", String.valueOf(alcantar));
		widgetValues.put("hay_in_viv", String.valueOf(hay_in_viv));
		widgetValues.put("alum_publ", String.valueOf(alum_publ));
		widgetValues.put("silos", String.valueOf(silos));
		widgetValues.put("trojas_mej", String.valueOf(trojas_mej));
		widgetValues.put("sacos", String.valueOf(sacos));
		widgetValues.put("coc_mejor", String.valueOf(coc_mejor));
		widgetValues.put("filtro_ag", String.valueOf(filtro_ag));
		widgetValues.put("gallinero", String.valueOf(gallinero));
		widgetValues.put("ramadas", String.valueOf(ramadas));
		widgetValues.put("letrina", String.valueOf(letrina));
		widgetValues.put("consumo_h", String.valueOf(consumo_h));
		widgetValues.put("sist_rieg", String.valueOf(sist_rieg));
		widgetValues.put("cap_techo", String.valueOf(cap_techo));
		widgetValues.put("dep_almac", String.valueOf(dep_almac));

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

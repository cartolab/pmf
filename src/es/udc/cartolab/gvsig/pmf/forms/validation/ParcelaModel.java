package es.udc.cartolab.gvsig.pmf.forms.validation;

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
public class ParcelaModel extends FormModel {

	// Private variables needed for framework ********************************************
	private String nom_com;
	private String cod_viv;
	private String cod_com;
	private String area_tot;
	private String codigo_fc;
	private String ot_legal_p;
	private String d_fue_tano;
	private String d_tan_hueo;
	private String area_cul;
	private String otrocspe;
	private String ot_tip_su;
	private String ot_cerca;
	private String otrocan;
	private String c_conse;
	private String c_aborg;
	private String c_insect;
	private String c_quim;
	private String p_riego;
	private String p_huerto;
	private String p_filtroag;
	private String p_galline;
	private String fam_cant;
	private String fam_per;
	private String con_cant;
	private String con_per;
	private String pro_finsex;
	private String legal_par;
	private String d_fue_tan;
	private String d_tan_hue;
	private String tip_suelo;
	private String estatus_vi;
	private String deg_suelo;

	private boolean reserv;
	private boolean nacimiento;
	private boolean rio;
	private boolean poz_pro;
	private boolean fuente_com;
	private boolean poz_com;
	private boolean no_fuen;
	private boolean b_vivas;
	private boolean b_muertas;
	private boolean maiz;
	private boolean yuca;
	private boolean no_cultivos;
	private boolean musacea;
	private boolean hay_ot_sp;
	private boolean arbfor;
	private boolean noarb;
	private boolean no_cul_semi;
	private boolean pasto;
	private boolean arbfru;
	private boolean hortalizas;
	private boolean papaya;
	private boolean hay_ot_cer;
	private boolean prac_conse;
	private boolean uso_aborg;
	private boolean insect_org;
	private boolean uso_quim;
	private boolean maicillo;
	private boolean frijol;
	private boolean hay_ot_cul;
	private boolean cerca;

	// Constructor and initialization methods *************************************************************
	public ParcelaModel(Container c){
		super(c);
	}

	@Override
	public String getModelName() {
		return "ParcelaModel";
	}

	@Override
	protected void setDefaultValues() {
		setDefaultValuesForIntFields();
		setDefaultValuesForStringFields();
		setDefaultValuesForWidgetMap();
	}

	private void setDefaultValuesForWidgetMap() {
		//int values
		widgetValues.put("fam_cant", fam_cant);
		widgetValues.put("con_cant", con_cant);
		widgetValues.put("area_tot", area_tot);
		widgetValues.put("d_fue_tano", d_fue_tano);
		widgetValues.put("d_tan_hueo", d_tan_hueo);
		widgetValues.put("area_cul", area_cul);

		// string values
		widgetValues.put("nom_com", nom_com);
		widgetValues.put("cod_viv", cod_viv);
		widgetValues.put("cod_com", cod_com);
		widgetValues.put("codigo_fc", codigo_fc);
		widgetValues.put("ot_legal_p", ot_legal_p);
		widgetValues.put("otrocspe", otrocspe);
		widgetValues.put("ot_tip_su", ot_tip_su);
		widgetValues.put("ot_cerca", ot_cerca);
		widgetValues.put("otrocan", otrocan);
		widgetValues.put("c_conse", c_conse);
		widgetValues.put("c_aborg", c_aborg);
		widgetValues.put("c_insect", c_insect);
		widgetValues.put("c_quim", c_quim);
		widgetValues.put("p_riego", p_riego);
		widgetValues.put("p_huerto", p_huerto);
		widgetValues.put("p_filtroag", p_filtroag);
		widgetValues.put("p_galline", p_galline);
		widgetValues.put("fam_per", fam_per);
		widgetValues.put("con_per", con_per);
		widgetValues.put("pro_finsex", pro_finsex);
		widgetValues.put("legal_par", legal_par);
		widgetValues.put("d_fue_tan", d_fue_tan);
		widgetValues.put("d_tan_hue", d_tan_hue);
		widgetValues.put("tip_suelo", tip_suelo);
		widgetValues.put("estatus_vi", estatus_vi);
		widgetValues.put("deg_suelo", deg_suelo);


	}

	private void setDefaultValuesForStringFields() {
		nom_com = getGvsigDefaultString();
		cod_viv = getGvsigDefaultString();
		cod_com = getGvsigDefaultString();
		codigo_fc = getGvsigDefaultString();
		ot_legal_p = getGvsigDefaultString();
		otrocspe = getGvsigDefaultString();
		ot_tip_su = getGvsigDefaultString();
		ot_cerca = getGvsigDefaultString();
		otrocan = getGvsigDefaultString();
		c_conse = getGvsigDefaultString();
		c_aborg = getGvsigDefaultString();
		c_insect = getGvsigDefaultString();
		c_quim = getGvsigDefaultString();
		p_riego = getGvsigDefaultString();
		p_huerto = getGvsigDefaultString();
		p_filtroag = getGvsigDefaultString();
		p_galline = getGvsigDefaultString();
		fam_per = getGvsigDefaultString();
		con_per = getGvsigDefaultString();
		pro_finsex = getGvsigDefaultString();
		legal_par = getGvsigDefaultString();
		d_fue_tan = getGvsigDefaultString();
		d_tan_hue = getGvsigDefaultString();
		tip_suelo = getGvsigDefaultString();
		estatus_vi = getGvsigDefaultString();
		deg_suelo = getGvsigDefaultString();
	}

	private void setDefaultValuesForIntFields() {
		//int values
		fam_cant = Integer.toString(getGvsigDefaultInt());
		con_cant = Integer.toString(getGvsigDefaultInt());
		area_tot = Integer.toString(getGvsigDefaultInt());
		d_fue_tano = Integer.toString(getGvsigDefaultInt());
		d_tan_hueo = Integer.toString(getGvsigDefaultInt());
		area_cul = Integer.toString(getGvsigDefaultInt());
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

	public String getArea_tot(){
		return area_tot;
	}

	public void setArea_tot(String newValue) {
		String oldValue = getArea_tot();
		area_tot = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("AREA_TOT"), oldValue, newValue);
	}

	public String getCodigo_fc(){
		return codigo_fc;
	}

	public void setCodigo_fc(String newValue) {
		String oldValue = getCodigo_fc();
		codigo_fc = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("CODIGO_FC"), oldValue, newValue);
	}

	public String getOt_legal_p(){
		return ot_legal_p;
	}

	public void setOt_legal_p(String newValue) {
		String oldValue = getOt_legal_p();
		ot_legal_p = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("OT_LEGAL_P"), oldValue, newValue);
	}

	public String getD_fue_tano(){
		return d_fue_tano;
	}

	public void setD_fue_tano(String newValue) {
		String oldValue = getD_fue_tano();
		d_fue_tano = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("D_FUE_TANO"), oldValue, newValue);
	}

	public String getD_tan_hueo(){
		return d_tan_hueo;
	}

	public void setD_tan_hueo(String newValue) {
		String oldValue = getD_tan_hueo();
		d_tan_hueo = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("D_TAN_HUEO"), oldValue, newValue);
	}

	public String getArea_cul(){
		return area_cul;
	}

	public void setArea_cul(String newValue) {
		String oldValue = getArea_cul();
		area_cul = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("AREA_CUL"), oldValue, newValue);
	}

	public String getOtrocspe(){
		return otrocspe;
	}

	public void setOtrocspe(String newValue) {
		String oldValue = getOtrocspe();
		otrocspe = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("OTROCSPE"), oldValue, newValue);
	}

	public String getOt_tip_su(){
		return ot_tip_su;
	}

	public void setOt_tip_su(String newValue) {
		String oldValue = getOt_tip_su();
		ot_tip_su = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("OT_TIP_SU"), oldValue, newValue);
	}

	public String getOt_cerca(){
		return ot_cerca;
	}

	public void setOt_cerca(String newValue) {
		String oldValue = getOt_cerca();
		ot_cerca = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("OT_CERCA"), oldValue, newValue);
	}

	public String getOtrocan(){
		return otrocan;
	}

	public void setOtrocan(String newValue) {
		String oldValue = getOtrocan();
		otrocan = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("OTROCAN"), oldValue, newValue);
	}

	public String getC_conse(){
		return c_conse;
	}

	public void setC_conse(String newValue) {
		String oldValue = getC_conse();
		c_conse = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("C_CONSE"), oldValue, newValue);
	}

	public String getC_aborg(){
		return c_aborg;
	}

	public void setC_aborg(String newValue) {
		String oldValue = getC_aborg();
		c_aborg = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("C_ABORG"), oldValue, newValue);
	}

	public String getC_insect(){
		return c_insect;
	}

	public void setC_insect(String newValue) {
		String oldValue = getC_insect();
		c_insect = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("C_INSECT"), oldValue, newValue);
	}

	public String getC_quim(){
		return c_quim;
	}

	public void setC_quim(String newValue) {
		String oldValue = getC_quim();
		c_quim = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("C_QUIM"), oldValue, newValue);
	}

	public String getP_riego(){
		return p_riego;
	}

	public void setP_riego(String newValue) {
		String oldValue = getP_riego();
		p_riego = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("P_RIEGO"), oldValue, newValue);
	}

	public String getP_huerto(){
		return p_huerto;
	}

	public void setP_huerto(String newValue) {
		String oldValue = getP_huerto();
		p_huerto = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("P_HUERTO"), oldValue, newValue);
	}

	public String getP_filtroag(){
		return p_filtroag;
	}

	public void setP_filtroag(String newValue) {
		String oldValue = getP_filtroag();
		p_filtroag = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("P_FILTROAG"), oldValue, newValue);
	}

	public String getP_galline(){
		return p_galline;
	}

	public void setP_galline(String newValue) {
		String oldValue = getP_galline();
		p_galline = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("P_GALLINE"), oldValue, newValue);
	}

	public String getFam_cant(){
		return fam_cant;
	}

	public void setFam_cant(String newValue) {
		String oldValue = getFam_cant();
		fam_cant = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("FAM_CANT"), oldValue, newValue);
	}

	public String getFam_per(){
		return fam_per;
	}

	public void setFam_per(String newValue) {
		String oldValue = getFam_per();
		fam_per = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("FAM_PER"), oldValue, newValue);
	}

	public String getCon_cant(){
		return con_cant;
	}

	public void setCon_cant(String newValue) {
		String oldValue = getCon_cant();
		con_cant = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("CON_CANT"), oldValue, newValue);
	}

	public String getCon_per(){
		return con_per;
	}

	public void setCon_per(String newValue) {
		String oldValue = getCon_per();
		con_per = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("CON_PER"), oldValue, newValue);
	}

	public String getPro_finsex(){
		return pro_finsex;
	}

	public void setPro_finsex(String newValue) {
		String oldValue = getPro_finsex();
		pro_finsex = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("PRO_FINSEX"), oldValue, newValue);
	}

	public String getLegal_par(){
		return legal_par;
	}

	public void setLegal_par(String newValue) {
		String oldValue = getLegal_par();
		legal_par = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("LEGAL_PAR"), oldValue, newValue);
	}

	public String getD_fue_tan(){
		return d_fue_tan;
	}

	public void setD_fue_tan(String newValue) {
		String oldValue = getD_fue_tan();
		d_fue_tan = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("D_FUE_TAN"), oldValue, newValue);
	}

	public String getD_tan_hue(){
		return d_tan_hue;
	}

	public void setD_tan_hue(String newValue) {
		String oldValue = getD_tan_hue();
		d_tan_hue = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("D_TAN_HUE"), oldValue, newValue);
	}

	public String getTip_suelo(){
		return tip_suelo;
	}

	public void setTip_suelo(String newValue) {
		String oldValue = getTip_suelo();
		tip_suelo = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("TIP_SUELO"), oldValue, newValue);
	}

	public String getEstatus_vi(){
		return estatus_vi;
	}

	public void setEstatus_vi(String newValue) {
		String oldValue = getEstatus_vi();
		estatus_vi = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("ESTATUS_VI"), oldValue, newValue);
	}

	public String getDeg_suelo(){
		return deg_suelo;
	}

	public void setDeg_suelo(String newValue) {
		String oldValue = getDeg_suelo();
		deg_suelo = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("DEG_SUELO"), oldValue, newValue);
	}


	public boolean getReserv(){
		return reserv;
	}

	public void setReserv(boolean newValue) {
		boolean oldValue = getReserv();
		reserv = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("RESERV"), oldValue, newValue);
	}

	public boolean getNacimiento(){
		return nacimiento;
	}

	public void setNacimiento(boolean newValue) {
		boolean oldValue = getNacimiento();
		nacimiento = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("NACIMIENTO"), oldValue, newValue);
	}

	public boolean getRio(){
		return rio;
	}

	public void setRio(boolean newValue) {
		boolean oldValue = getRio();
		rio = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("RIO"), oldValue, newValue);
	}

	public boolean getPoz_pro(){
		return poz_pro;
	}

	public void setPoz_pro(boolean newValue) {
		boolean oldValue = getPoz_pro();
		poz_pro = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("POZ_PRO"), oldValue, newValue);
	}

	public boolean getFuente_com(){
		return fuente_com;
	}

	public void setFuente_com(boolean newValue) {
		boolean oldValue = getFuente_com();
		fuente_com = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("FUENTE_COM"), oldValue, newValue);
	}

	public boolean getPoz_com(){
		return poz_com;
	}

	public void setPoz_com(boolean newValue) {
		boolean oldValue = getPoz_com();
		poz_com = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("POZ_COM"), oldValue, newValue);
	}

	public boolean getNo_fuen(){
		return no_fuen;
	}

	public void setNo_fuen(boolean newValue) {
		boolean oldValue = getNo_fuen();
		no_fuen = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("NO_FUEN"), oldValue, newValue);
	}

	public boolean getB_vivas(){
		return b_vivas;
	}

	public void setB_vivas(boolean newValue) {
		boolean oldValue = getB_vivas();
		b_vivas = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("B_VIVAS"), oldValue, newValue);
	}

	public boolean getB_muertas(){
		return b_muertas;
	}

	public void setB_muertas(boolean newValue) {
		boolean oldValue = getB_muertas();
		b_muertas = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("B_MUERTAS"), oldValue, newValue);
	}

	public boolean getMaiz(){
		return maiz;
	}

	public void setMaiz(boolean newValue) {
		boolean oldValue = getMaiz();
		maiz = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("MAIZ"), oldValue, newValue);
	}

	public boolean getYuca(){
		return yuca;
	}

	public void setYuca(boolean newValue) {
		boolean oldValue = getYuca();
		yuca = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("YUCA"), oldValue, newValue);
	}

	public boolean getNo_cultivos(){
		return no_cultivos;
	}

	public void setNo_cultivos(boolean newValue) {
		boolean oldValue = getNo_cultivos();
		no_cultivos = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("NO_CULTIVOS"), oldValue, newValue);
	}

	public boolean getMusacea(){
		return musacea;
	}

	public void setMusacea(boolean newValue) {
		boolean oldValue = getMusacea();
		musacea = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("MUSACEA"), oldValue, newValue);
	}

	public boolean getHay_ot_sp(){
		return hay_ot_sp;
	}

	public void setHay_ot_sp(boolean newValue) {
		boolean oldValue = getHay_ot_sp();
		hay_ot_sp = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("HAY_OT_SP"), oldValue, newValue);
	}

	public boolean getArbfor(){
		return arbfor;
	}

	public void setArbfor(boolean newValue) {
		boolean oldValue = getArbfor();
		arbfor = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("ARBFOR"), oldValue, newValue);
	}

	public boolean getNoarb(){
		return noarb;
	}

	public void setNoarb(boolean newValue) {
		boolean oldValue = getNoarb();
		noarb = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("NOARB"), oldValue, newValue);
	}

	public boolean getNo_cul_semi(){
		return no_cul_semi;
	}

	public void setNo_cul_semi(boolean newValue) {
		boolean oldValue = getNo_cul_semi();
		no_cul_semi = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("NO_CUL_SEMI"), oldValue, newValue);
	}

	public boolean getPasto(){
		return pasto;
	}

	public void setPasto(boolean newValue) {
		boolean oldValue = getPasto();
		pasto = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("PASTO"), oldValue, newValue);
	}

	public boolean getArbfru(){
		return arbfru;
	}

	public void setArbfru(boolean newValue) {
		boolean oldValue = getArbfru();
		arbfru = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("ARBFRU"), oldValue, newValue);
	}

	public boolean getHortalizas(){
		return hortalizas;
	}

	public void setHortalizas(boolean newValue) {
		boolean oldValue = getHortalizas();
		hortalizas = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("HORTALIZAS"), oldValue, newValue);
	}

	public boolean getPapaya(){
		return papaya;
	}

	public void setPapaya(boolean newValue) {
		boolean oldValue = getPapaya();
		papaya = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("PAPAYA"), oldValue, newValue);
	}

	public boolean getHay_ot_cer(){
		return hay_ot_cer;
	}

	public void setHay_ot_cer(boolean newValue) {
		boolean oldValue = getHay_ot_cer();
		hay_ot_cer = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("HAY_OT_CER"), oldValue, newValue);
	}

	public boolean getPrac_conse(){
		return prac_conse;
	}

	public void setPrac_conse(boolean newValue) {
		boolean oldValue = getPrac_conse();
		prac_conse = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("PRAC_CONSE"), oldValue, newValue);
	}

	public boolean getUso_aborg(){
		return uso_aborg;
	}

	public void setUso_aborg(boolean newValue) {
		boolean oldValue = getUso_aborg();
		uso_aborg = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("USO_ABORG"), oldValue, newValue);
	}

	public boolean getInsect_org(){
		return insect_org;
	}

	public void setInsect_org(boolean newValue) {
		boolean oldValue = getInsect_org();
		insect_org = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("INSECT_ORG"), oldValue, newValue);
	}

	public boolean getUso_quim(){
		return uso_quim;
	}

	public void setUso_quim(boolean newValue) {
		boolean oldValue = getUso_quim();
		uso_quim = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("USO_QUIM"), oldValue, newValue);
	}

	public boolean getMaicillo(){
		return maicillo;
	}

	public void setMaicillo(boolean newValue) {
		boolean oldValue = getMaicillo();
		maicillo = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("MAICILLO"), oldValue, newValue);
	}

	public boolean getFrijol(){
		return frijol;
	}

	public void setFrijol(boolean newValue) {
		boolean oldValue = getFrijol();
		frijol = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("FRIJOL"), oldValue, newValue);
	}

	public boolean getHay_ot_cul(){
		return hay_ot_cul;
	}

	public void setHay_ot_cul(boolean newValue) {
		boolean oldValue = getHay_ot_cul();
		hay_ot_cul = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("HAY_OT_CUL"), oldValue, newValue);
	}

	public boolean getCerca(){
		return cerca;
	}

	public void setCerca(boolean newValue) {
		boolean oldValue = getCerca();
		cerca = newValue;
		firePropertyChange((String) PROPERTIES_MAP.get("CERCA"), oldValue, newValue);
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
	}

	//map with widget values
	public Map<String, String> getWidgetvalues(){
		widgetValues.put("nom_com", nom_com);
		widgetValues.put("cod_viv", cod_viv);
		widgetValues.put("cod_com", cod_com);
		widgetValues.put("area_tot", area_tot);
		widgetValues.put("codigo_fc", codigo_fc);
		widgetValues.put("ot_legal_p", ot_legal_p);
		widgetValues.put("d_fue_tano", d_fue_tano);
		widgetValues.put("d_tan_hueo", d_tan_hueo);
		widgetValues.put("area_cul", area_cul);
		widgetValues.put("otrocspe", otrocspe);
		widgetValues.put("ot_tip_su", ot_tip_su);
		widgetValues.put("ot_cerca", ot_cerca);
		widgetValues.put("otrocan", otrocan);
		widgetValues.put("c_conse", c_conse);
		widgetValues.put("c_aborg", c_aborg);
		widgetValues.put("c_insect", c_insect);
		widgetValues.put("c_quim", c_quim);
		widgetValues.put("p_riego", p_riego);
		widgetValues.put("p_huerto", p_huerto);
		widgetValues.put("p_filtroag", p_filtroag);
		widgetValues.put("p_galline", p_galline);
		widgetValues.put("fam_cant", fam_cant);
		widgetValues.put("fam_per", fam_per);
		widgetValues.put("con_cant", con_cant);
		widgetValues.put("con_per", con_per);
		widgetValues.put("pro_finsex", pro_finsex);
		widgetValues.put("legal_par", legal_par);
		widgetValues.put("d_fue_tan", d_fue_tan);
		widgetValues.put("d_tan_hue", d_tan_hue);
		widgetValues.put("tip_suelo", tip_suelo);
		widgetValues.put("estatus_vi", estatus_vi);
		widgetValues.put("deg_suelo", deg_suelo);
		widgetValues.put("reserv", String.valueOf(reserv));
		widgetValues.put("nacimiento", String.valueOf(nacimiento));
		widgetValues.put("rio", String.valueOf(rio));
		widgetValues.put("poz_pro", String.valueOf(poz_pro));
		widgetValues.put("fuente_com", String.valueOf(fuente_com));
		widgetValues.put("poz_com", String.valueOf(poz_com));
		widgetValues.put("no_fuen", String.valueOf(no_fuen));
		widgetValues.put("b_vivas", String.valueOf(b_vivas));
		widgetValues.put("b_muertas", String.valueOf(b_muertas));
		widgetValues.put("maiz", String.valueOf(maiz));
		widgetValues.put("yuca", String.valueOf(yuca));
		widgetValues.put("no_cultivos", String.valueOf(no_cultivos));
		widgetValues.put("musacea", String.valueOf(musacea));
		widgetValues.put("hay_ot_sp", String.valueOf(hay_ot_sp));
		widgetValues.put("arbfor", String.valueOf(arbfor));
		widgetValues.put("noarb", String.valueOf(noarb));
		widgetValues.put("no_cul_semi", String.valueOf(no_cul_semi));
		widgetValues.put("pasto", String.valueOf(pasto));
		widgetValues.put("arbfru", String.valueOf(arbfru));
		widgetValues.put("hortalizas", String.valueOf(hortalizas));
		widgetValues.put("papaya", String.valueOf(papaya));
		widgetValues.put("hay_ot_cer", String.valueOf(hay_ot_cer));
		widgetValues.put("prac_conse", String.valueOf(prac_conse));
		widgetValues.put("uso_aborg", String.valueOf(uso_aborg));
		widgetValues.put("insect_org", String.valueOf(insect_org));
		widgetValues.put("uso_quim", String.valueOf(uso_quim));
		widgetValues.put("maicillo", String.valueOf(maicillo));
		widgetValues.put("frijol", String.valueOf(frijol));
		widgetValues.put("hay_ot_cul", String.valueOf(hay_ot_cul));
		widgetValues.put("cerca", String.valueOf(cerca));

		return widgetValues;
	}

        // maps for every layer
	 */
	// map with widget values
	@Override
	public Map<String, String> getWidgetValues() {
		return widgetValues;
	}
}

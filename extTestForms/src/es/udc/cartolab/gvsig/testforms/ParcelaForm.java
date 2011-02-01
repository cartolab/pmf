package es.udc.cartolab.gvsig.testforms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.WindowInfo;
import com.jeta.forms.components.border.TitledBorderBottom;
import com.jeta.forms.components.border.TitledBorderLabel;
import com.jeta.forms.components.border.TitledBorderSide;
import com.jeta.forms.components.line.HorizontalLineComponent;
import com.jeta.forms.components.separator.TitledSeparator;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;


public class ParcelaForm extends JPanel implements IWindow
{
	WindowInfo windowInfo = null;
	JLabel m_cod_com_LB = new JLabel();
	JLabel m_cod_viv_LB = new JLabel();
	JLabel m_nom_com_LB = new JLabel();
	JTextField m_nom_com_TF = new JTextField();
	JTextField m_cod_viv_TF = new JTextField();
	JTextField m_cod_com_TF = new JTextField();
	JLabel m_cedu_form_title = new JLabel();
	JTabbedPane m_jtabbedpane1 = new JTabbedPane();
	JComboBox m_pro_finsex_CB = new JComboBox();
	JTextField m_area_tot_TF_REAL = new JTextField();
	JComboBox m_legal_par_CB = new JComboBox();
	JTable m_jtable1 = new JTable();
	JLabel m_legal_par_LB = new JLabel();
	JLabel m_pro_finsex_LB = new JLabel();
	JLabel m_area_tot_LB = new JLabel();
	JLabel m_n_mujer_LB = new JLabel();
	TitledBorderLabel m_titledborderlabel1 = new TitledBorderLabel();
	JCheckBox m_reserv_CHB = new JCheckBox();
	JCheckBox m_nacimiento_CHB = new JCheckBox();
	JCheckBox m_rio_CHB = new JCheckBox();
	TitledBorderSide m_titledborderside1 = new TitledBorderSide();
	TitledBorderSide m_titledborderside2 = new TitledBorderSide();
	TitledBorderBottom m_titledborderbottom1 = new TitledBorderBottom();
	JCheckBox m_poz_pro_CHB = new JCheckBox();
	JCheckBox m_fuente_com_CHB = new JCheckBox();
	JLabel m_codigo_fc_LB = new JLabel();
	JTextField m_codigo_fc_TF = new JTextField();
	JButton m_codigo_fc_BT = new JButton();
	TitledBorderLabel m_titledborderlabel2 = new TitledBorderLabel();
	JLabel m_d_fue_tan_LB = new JLabel();
	JLabel m_d_fue_tano_LB = new JLabel();
	JTextField m_ot_legal_p_TF = new JTextField();
	HorizontalLineComponent m_horizontallinecomponent1 = new HorizontalLineComponent();
	JLabel m_d_tan_hue_LB = new JLabel();
	JLabel m_d_tan_hueo_LB = new JLabel();
	JTextField m_d_fue_tano_TF_REAL = new JTextField();
	JTextField m_d_tan_hueo_TF_REAL = new JTextField();
	TitledBorderSide m_titledborderside3 = new TitledBorderSide();
	TitledBorderSide m_titledborderside4 = new TitledBorderSide();
	TitledBorderBottom m_titledborderbottom2 = new TitledBorderBottom();
	JLabel m_area_cul_LB = new JLabel();
	JLabel m_ot_legal_p_LB = new JLabel();
	JComboBox m_d_fue_tan_CB = new JComboBox();
	JComboBox m_d_tan_hue_CB = new JComboBox();
	JCheckBox m_poz_com_CHB = new JCheckBox();
	JCheckBox m_no_fuen_CHB = new JCheckBox();
	JTextField m_area_cul_TF_REAL = new JTextField();
	JLabel m_deg_suelo_LB = new JLabel();
	JLabel m_tip_suelo_LB = new JLabel();
	JLabel m_estatus_vi_LB = new JLabel();
	JComboBox m_tip_suelo_CB = new JComboBox();
	JCheckBox m_b_vivas_CHB = new JCheckBox();
	JCheckBox m_b_muertas_CHB = new JCheckBox();
	TitledBorderSide m_titledborderside5 = new TitledBorderSide();
	TitledBorderSide m_titledborderside6 = new TitledBorderSide();
	TitledBorderBottom m_titledborderbottom3 = new TitledBorderBottom();
	TitledBorderLabel m_titledborderlabel3 = new TitledBorderLabel();
	JCheckBox m_maiz_CHB = new JCheckBox();
	JCheckBox m_yuca_CHB = new JCheckBox();
	JCheckBox m_no_cultivos_CHB = new JCheckBox();
	JCheckBox m_musacea_CHB = new JCheckBox();
	JCheckBox m_hay_ot_sp_CHB = new JCheckBox();
	JCheckBox m_arbfor_CHB = new JCheckBox();
	TitledBorderSide m_titledborderside7 = new TitledBorderSide();
	TitledBorderSide m_titledborderside8 = new TitledBorderSide();
	TitledBorderBottom m_titledborderbottom4 = new TitledBorderBottom();
	JCheckBox m_noarb_CHB = new JCheckBox();
	JCheckBox m_no_cul_semi_CHB = new JCheckBox();
	JCheckBox m_pasto_CHB = new JCheckBox();
	JCheckBox m_arbfru_CHB = new JCheckBox();
	JComboBox m_estatus_vi_CB = new JComboBox();
	JCheckBox m_hortalizas_CHB = new JCheckBox();
	JCheckBox m_papaya_CHB = new JCheckBox();
	JCheckBox m_hay_ot_cer_CHB = new JCheckBox();
	JCheckBox m_prac_conse_CHB = new JCheckBox();
	JCheckBox m_uso_aborg_CHB = new JCheckBox();
	JCheckBox m_insect_org_CHB = new JCheckBox();
	JCheckBox m_uso_quim_CHB = new JCheckBox();
	JComboBox m_deg_suelo_CB = new JComboBox();
	JTextField m_otrocspe_TF = new JTextField();
	JLabel m_ot_tip_su_LB = new JLabel();
	JTextField m_ot_tip_su_TF = new JTextField();
	JTextField m_ot_cerca_TF = new JTextField();
	JCheckBox m_maicillo_CHB = new JCheckBox();
	JCheckBox m_frijol_CHB = new JCheckBox();
	JCheckBox m_hay_ot_cul_CHB = new JCheckBox();
	JTextField m_otrocan_TF = new JTextField();
	JCheckBox m_cerca_CHB = new JCheckBox();
	TitledBorderLabel m_titledborderlabel4 = new TitledBorderLabel();
	JTable m_jtable2 = new JTable();
	JTextField m_c_conse_TF = new JTextField();
	JTextField m_c_aborg_TF = new JTextField();
	JTextField m_c_insect_TF = new JTextField();
	JTextField m_c_quim_TF = new JTextField();
	JLabel m_p_riego_LB = new JLabel();
	JTextField m_p_riego_TF = new JTextField();
	JTextField m_p_huerto_TF = new JTextField();
	JTextField m_p_filtroag_TF = new JTextField();
	JLabel m_p_filtroag_LB = new JLabel();
	JLabel m_p_huerto_LB = new JLabel();
	JTextField m_p_galline_TF = new JTextField();
	JLabel m_p_galline_LB = new JLabel();
	TitledBorderLabel m_titledborderlabel5 = new TitledBorderLabel();
	JLabel m_fam_cant_LB = new JLabel();
	JTextField m_fam_cant_TF_INT = new JTextField();
	JLabel m_fam_per_LB = new JLabel();
	JTextField m_fam_per_TF = new JTextField();
	TitledBorderSide m_titledborderside9 = new TitledBorderSide();
	TitledBorderSide m_titledborderside10 = new TitledBorderSide();
	TitledBorderBottom m_titledborderbottom5 = new TitledBorderBottom();
	TitledBorderLabel m_titledborderlabel6 = new TitledBorderLabel();
	JLabel m_con_cant_LB = new JLabel();
	JTextField m_con_cant_TF_INT = new JTextField();
	JLabel m_con_per_LB = new JLabel();
	JTextField m_con_per_TF = new JTextField();
	TitledBorderSide m_titledborderside11 = new TitledBorderSide();
	TitledBorderSide m_titledborderside12 = new TitledBorderSide();
	TitledBorderBottom m_titledborderbottom6 = new TitledBorderBottom();

	/**
	 * Default constructor
	 */
	 public ParcelaForm()
	{
		initializePanel();
	}

	/**
	 * Adds fill components to empty cells in the first row and first column of the grid.
	 * This ensures that the grid spacing will be the same as shown in the designer.
	 * @param cols an array of column indices in the first row where fill components should be added.
	 * @param rows an array of row indices in the first column where fill components should be added.
	 */
	 void addFillComponents( Container panel, int[] cols, int[] rows )
	 {
		 Dimension filler = new Dimension(10,10);

		 boolean filled_cell_11 = false;
		 CellConstraints cc = new CellConstraints();
		 if ( cols.length > 0 && rows.length > 0 )
		 {
			 if ( cols[0] == 1 && rows[0] == 1 )
			 {
				 /** add a rigid area  */
				 panel.add( Box.createRigidArea( filler ), cc.xy(1,1) );
				 filled_cell_11 = true;
			 }
		 }

		 for( int index = 0; index < cols.length; index++ )
		 {
			 if ( cols[index] == 1 && filled_cell_11 )
			 {
				 continue;
			 }
			 panel.add( Box.createRigidArea( filler ), cc.xy(cols[index],1) );
		 }

		 for( int index = 0; index < rows.length; index++ )
		 {
			 if ( rows[index] == 1 && filled_cell_11 )
			 {
				 continue;
			 }
			 panel.add( Box.createRigidArea( filler ), cc.xy(1,rows[index]) );
		 }

	 }

	 /**
	  * Helper method to load an image file from the CLASSPATH
	  * @param imageName the package and name of the file to load relative to the CLASSPATH
	  * @return an ImageIcon instance with the specified image file
	  * @throws IllegalArgumentException if the image resource cannot be loaded.
	  */
	 public ImageIcon loadImage( String imageName )
	 {
		 try
		 {
			 ClassLoader classloader = getClass().getClassLoader();
			 java.net.URL url = classloader.getResource( imageName );
			 if ( url != null )
			 {
				 ImageIcon icon = new ImageIcon( url );
				 return icon;
			 }
		 }
		 catch( Exception e )
		 {
			 e.printStackTrace();
		 }
		 throw new IllegalArgumentException( "Unable to load image: " + imageName );
	 }

	 /**
	  * Method for recalculating the component orientation for
	  * right-to-left Locales.
	  * @param orientation the component orientation to be applied
	  */
	 @Override
	 public void applyComponentOrientation( ComponentOrientation orientation )
	 {
		 // Not yet implemented...
		 // I18NUtils.applyComponentOrientation(this, orientation);
		 super.applyComponentOrientation(orientation);
	 }

	 public JPanel createPanel()
	 {
		 JPanel jpanel1 = new JPanel();
		 FormLayout formlayout1 = new FormLayout("FILL:4DLU:NONE,FILL:8DLU:NONE,FILL:184PX:NONE,FILL:4DLU:NONE,FILL:110PX:NONE,FILL:8DLU:NONE,FILL:12PX:NONE,FILL:35PX:NONE,FILL:4DLU:NONE,FILL:69DLU:NONE,FILL:4DLU:NONE,FILL:DEFAULT:NONE,FILL:65PX:NONE,FILL:8DLU:NONE,FILL:4DLU:NONE","CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:4DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:4DLU:NONE");
		 CellConstraints cc = new CellConstraints();
		 jpanel1.setLayout(formlayout1);

		 m_cod_com_LB.setName("cod_com.LB");
		 m_cod_com_LB.setText("Código de la comunidad:");
		 jpanel1.add(m_cod_com_LB,cc.xywh(7,8,4,1));

		 m_cod_viv_LB.setName("cod_viv.LB");
		 m_cod_viv_LB.setText("Código de la vivienda:");
		 jpanel1.add(m_cod_viv_LB,cc.xy(3,10));

		 m_nom_com_LB.setName("nom_com.LB");
		 m_nom_com_LB.setText("Nombre de la comunidad:");
		 jpanel1.add(m_nom_com_LB,cc.xy(3,8));

		 TitledSeparator titledseparator1 = new TitledSeparator();
		 titledseparator1.setText("Datos generales");
		 jpanel1.add(titledseparator1,cc.xywh(3,6,11,1));

		 m_nom_com_TF.setName("nom_com.TF");
		 jpanel1.add(m_nom_com_TF,cc.xy(5,8));

		 m_cod_viv_TF.setName("cod_viv.TF");
		 jpanel1.add(m_cod_viv_TF,cc.xy(5,10));

		 m_cod_com_TF.setName("cod_com.TF");
		 jpanel1.add(m_cod_com_TF,cc.xywh(12,8,2,1));

		 m_cedu_form_title.setFocusable(false);
		 m_cedu_form_title.setFont(new Font("Dialog",Font.BOLD,16));
		 m_cedu_form_title.setForeground(new Color(49,106,196));
		 m_cedu_form_title.setName("cedu_form_title");
		 m_cedu_form_title.setRequestFocusEnabled(false);
		 m_cedu_form_title.setText("Parcela");
		 m_cedu_form_title.setVerifyInputWhenFocusTarget(false);
		 jpanel1.add(m_cedu_form_title,cc.xywh(3,2,3,1));

		 LineBorder lineborder1 = new LineBorder(new Color(0,0,0),1,false);
		 m_jtabbedpane1.setBorder(lineborder1);
		 m_jtabbedpane1.addTab("Características generales",null,createPanel1());
		 m_jtabbedpane1.addTab("Datos sobre cultivos",null,createPanel2());
		 m_jtabbedpane1.addTab("Actividades",null,createPanel3());
		 jpanel1.add(m_jtabbedpane1,cc.xywh(3,13,11,1));

		 addFillComponents(jpanel1,new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15 },new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15 });
		 return jpanel1;
	 }

	 public JPanel createPanel1()
	 {
		 JPanel jpanel1 = new JPanel();
		 FormLayout formlayout1 = new FormLayout("FILL:8DLU:NONE,FILL:4DLU:NONE,FILL:32DLU:NONE,FILL:32PX:NONE,FILL:4DLU:NONE,FILL:18PX:NONE,FILL:14DLU:NONE,FILL:4DLU:NONE,FILL:68PX:NONE,FILL:4DLU:NONE,FILL:12DLU:NONE,FILL:4DLU:NONE,FILL:40PX:NONE,FILL:4DLU:NONE,FILL:17DLU:NONE,FILL:8DLU:NONE,FILL:4DLU:NONE,FILL:4DLU:NONE,FILL:27PX:NONE,FILL:4DLU:NONE,FILL:46PX:NONE,FILL:4DLU:NONE,FILL:33PX:NONE,FILL:22PX:NONE,FILL:17DLU:NONE,FILL:8DLU:NONE","CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:4DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE");
		 CellConstraints cc = new CellConstraints();
		 jpanel1.setLayout(formlayout1);

		 m_pro_finsex_CB.setName("pro_finsex.CB");
		 m_pro_finsex_CB.addItem("Seleccione una opción...");
		 m_pro_finsex_CB.addItem("Un hombre");
		 m_pro_finsex_CB.addItem("Una mujer");
		 jpanel1.add(m_pro_finsex_CB,cc.xywh(9,9,11,1));

		 m_area_tot_TF_REAL.setName("area_tot.TF.REAL");
		 jpanel1.add(m_area_tot_TF_REAL,cc.xywh(6,11,5,1));

		 m_legal_par_CB.setName("legal_par.CB");
		 m_legal_par_CB.addItem("Seleccione una opción...");
		 m_legal_par_CB.addItem("Dominio pleno");
		 m_legal_par_CB.addItem("Dominio útil");
		 m_legal_par_CB.addItem("Herencia");
		 m_legal_par_CB.addItem("Otro");
		 jpanel1.add(m_legal_par_CB,cc.xywh(9,5,11,1));

		 JScrollPane jscrollpane1 = new JScrollPane();
		 jscrollpane1.setViewportView(m_jtable1);
		 jscrollpane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		 jscrollpane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		 jpanel1.add(jscrollpane1,cc.xywh(5,15,19,1));

		 TitledSeparator titledseparator1 = new TitledSeparator();
		 titledseparator1.setText("Localización de la parcela");
		 jpanel1.add(titledseparator1,cc.xywh(2,2,24,1));

		 m_legal_par_LB.setName("legal_par.LB");
		 m_legal_par_LB.setText("Tipo de dominio:");
		 jpanel1.add(m_legal_par_LB,cc.xywh(2,5,6,1));

		 m_pro_finsex_LB.setName("pro_finsex.LB");
		 m_pro_finsex_LB.setText("Pertenece a:");
		 jpanel1.add(m_pro_finsex_LB,cc.xywh(2,9,6,1));

		 m_area_tot_LB.setName("area_tot.LB");
		 m_area_tot_LB.setText("Área total (Mz):");
		 jpanel1.add(m_area_tot_LB,cc.xywh(2,11,3,1));

		 m_n_mujer_LB.setName("n_mujer.LB");
		 m_n_mujer_LB.setText("Área por cultivo:");
		 jpanel1.add(m_n_mujer_LB,cc.xywh(2,13,6,1));

		 TitledSeparator titledseparator2 = new TitledSeparator();
		 titledseparator2.setText("Recursos hídricos");
		 jpanel1.add(titledseparator2,cc.xywh(2,18,24,1));

		 m_titledborderlabel1.setText("Tipo de fuente para riego");
		 jpanel1.add(m_titledborderlabel1,cc.xywh(2,21,24,1));

		 m_reserv_CHB.setActionCommand("Ganadería");
		 m_reserv_CHB.setName("reserv.CHB");
		 m_reserv_CHB.setText("Reservorio");
		 jpanel1.add(m_reserv_CHB,cc.xywh(3,23,5,1));

		 m_nacimiento_CHB.setActionCommand("Ganadería");
		 m_nacimiento_CHB.setName("nacimiento.CHB");
		 m_nacimiento_CHB.setText("Nacimiento");
		 jpanel1.add(m_nacimiento_CHB,cc.xywh(9,23,7,1));

		 m_rio_CHB.setActionCommand("Ganadería");
		 m_rio_CHB.setName("rio.CHB");
		 m_rio_CHB.setText("Río");
		 jpanel1.add(m_rio_CHB,cc.xywh(3,25,5,1));

		 m_titledborderside1.setOrientation(TitledBorderSide.RIGHT);
		 jpanel1.add(m_titledborderside1,cc.xywh(26,21,1,7));

		 jpanel1.add(m_titledborderside2,cc.xywh(1,21,1,7));

		 jpanel1.add(m_titledborderbottom1,cc.xywh(2,27,24,1));

		 m_poz_pro_CHB.setActionCommand("Ganadería");
		 m_poz_pro_CHB.setName("poz_pro.CHB");
		 m_poz_pro_CHB.setText("Pozo propio");
		 jpanel1.add(m_poz_pro_CHB,cc.xywh(9,25,7,1));

		 m_fuente_com_CHB.setActionCommand("Ganadería");
		 m_fuente_com_CHB.setName("fuente_com.CHB");
		 m_fuente_com_CHB.setText("Existe fuente de agua común para varios productores");
		 jpanel1.add(m_fuente_com_CHB,cc.xywh(2,29,23,1));

		 m_codigo_fc_LB.setName("codigo_fc.LB");
		 m_codigo_fc_LB.setText("Código:");
		 m_codigo_fc_LB.setHorizontalAlignment(JLabel.RIGHT);
		 jpanel1.add(m_codigo_fc_LB,cc.xywh(6,31,4,1));

		 m_codigo_fc_TF.setName("codigo_fc.TF");
		 jpanel1.add(m_codigo_fc_TF,cc.xywh(11,31,6,1));

		 m_codigo_fc_BT.setActionCommand("Información");
		 m_codigo_fc_BT.setName("codigo_fc.BT");
		 m_codigo_fc_BT.setText("Información");
		 jpanel1.add(m_codigo_fc_BT,cc.xywh(19,31,6,1));

		 m_titledborderlabel2.setText("Distancias");
		 jpanel1.add(m_titledborderlabel2,cc.xywh(2,33,24,1));

		 m_d_fue_tan_LB.setFont(new Font("Dialog",Font.PLAIN,12));
		 m_d_fue_tan_LB.setName("d_fue_tan.LB");
		 m_d_fue_tan_LB.setText("De la fuente al tanque (m):");
		 jpanel1.add(m_d_fue_tan_LB,cc.xywh(3,35,9,1));

		 m_d_fue_tano_LB.setName("d_fue_tano.LB");
		 m_d_fue_tano_LB.setText("Especificar otra:");
		 m_d_fue_tano_LB.setHorizontalAlignment(JLabel.RIGHT);
		 jpanel1.add(m_d_fue_tano_LB,cc.xywh(13,37,7,1));

		 m_ot_legal_p_TF.setName("ot_legal_p.TF");
		 jpanel1.add(m_ot_legal_p_TF,cc.xywh(21,7,5,1));

		 jpanel1.add(m_horizontallinecomponent1,cc.xywh(3,39,22,1));

		 m_d_tan_hue_LB.setFont(new Font("Dialog",Font.PLAIN,12));
		 m_d_tan_hue_LB.setName("d_tan_hue.LB");
		 m_d_tan_hue_LB.setText("Del tanque al huerto (m):");
		 jpanel1.add(m_d_tan_hue_LB,cc.xywh(3,41,9,1));

		 m_d_tan_hueo_LB.setName("d_tan_hueo.LB");
		 m_d_tan_hueo_LB.setText("Especificar otra:");
		 m_d_tan_hueo_LB.setHorizontalAlignment(JLabel.RIGHT);
		 jpanel1.add(m_d_tan_hueo_LB,cc.xywh(13,43,7,1));

		 m_d_fue_tano_TF_REAL.setName("d_fue_tano.TF.REAL");
		 jpanel1.add(m_d_fue_tano_TF_REAL,cc.xywh(21,37,4,1));

		 m_d_tan_hueo_TF_REAL.setName("d_tan_hueo.TF.REAL");
		 jpanel1.add(m_d_tan_hueo_TF_REAL,cc.xywh(21,43,4,1));

		 m_titledborderside3.setOrientation(TitledBorderSide.RIGHT);
		 jpanel1.add(m_titledborderside3,cc.xywh(26,33,1,13));

		 jpanel1.add(m_titledborderside4,cc.xywh(1,33,1,13));

		 jpanel1.add(m_titledborderbottom2,cc.xywh(2,45,24,1));

		 m_area_cul_LB.setName("area_cul.LB");
		 m_area_cul_LB.setText("Área total para cultivos (Mz):");
		 m_area_cul_LB.setHorizontalAlignment(JLabel.RIGHT);
		 jpanel1.add(m_area_cul_LB,cc.xywh(12,11,10,1));

		 m_ot_legal_p_LB.setName("ot_legal_p.LB");
		 m_ot_legal_p_LB.setText("Especificar otro:");
		 m_ot_legal_p_LB.setHorizontalAlignment(JLabel.RIGHT);
		 jpanel1.add(m_ot_legal_p_LB,cc.xywh(12,7,8,1));

		 m_d_fue_tan_CB.setName("d_fue_tan.CB");
		 m_d_fue_tan_CB.addItem("Seleccione una opción...");
		 m_d_fue_tan_CB.addItem("15");
		 m_d_fue_tan_CB.addItem("20");
		 m_d_fue_tan_CB.addItem("30");
		 m_d_fue_tan_CB.addItem("40");
		 m_d_fue_tan_CB.addItem("50");
		 m_d_fue_tan_CB.addItem("100");
		 m_d_fue_tan_CB.addItem("Otra");
		 jpanel1.add(m_d_fue_tan_CB,cc.xywh(13,35,11,1));

		 m_d_tan_hue_CB.setName("d_tan_hue.CB");
		 m_d_tan_hue_CB.addItem("Seleccione una opción...");
		 m_d_tan_hue_CB.addItem("5");
		 m_d_tan_hue_CB.addItem("10");
		 m_d_tan_hue_CB.addItem("15");
		 m_d_tan_hue_CB.addItem("20");
		 m_d_tan_hue_CB.addItem("25");
		 m_d_tan_hue_CB.addItem("30");
		 m_d_tan_hue_CB.addItem("Otra");
		 jpanel1.add(m_d_tan_hue_CB,cc.xywh(13,41,11,1));

		 m_poz_com_CHB.setActionCommand("Ganadería");
		 m_poz_com_CHB.setName("poz_com.CHB");
		 m_poz_com_CHB.setText("Pozo comunitario");
		 jpanel1.add(m_poz_com_CHB,cc.xywh(16,23,9,1));

		 m_no_fuen_CHB.setActionCommand("Ganadería");
		 m_no_fuen_CHB.setName("no_fuen.CHB");
		 m_no_fuen_CHB.setText("No tiene fuente");
		 jpanel1.add(m_no_fuen_CHB,cc.xywh(16,25,9,1));

		 m_area_cul_TF_REAL.setName("area_cul.TF.REAL");
		 jpanel1.add(m_area_cul_TF_REAL,cc.xywh(23,11,3,1));

		 addFillComponents(jpanel1,new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26 },new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,22,23,24,25,26,27,28,29,30,31,32,34,35,36,37,38,39,40,41,42,43,44,45,46 });
		 return jpanel1;
	 }

	 public JPanel createPanel2()
	 {
		 JPanel jpanel1 = new JPanel();
		 FormLayout formlayout1 = new FormLayout("FILL:8DLU:NONE,FILL:4DLU:NONE,FILL:14PX:NONE,FILL:47PX:NONE,FILL:4DLU:NONE,FILL:4DLU:NONE,FILL:4DLU:NONE,FILL:55PX:NONE,FILL:4DLU:NONE,FILL:8PX:NONE,FILL:4DLU:NONE,FILL:51PX:NONE,FILL:4DLU:NONE,FILL:33PX:NONE,FILL:25PX:NONE,FILL:4DLU:NONE,FILL:10PX:NONE,FILL:38PX:NONE,FILL:20DLU:NONE,FILL:4DLU:NONE,FILL:12PX:NONE,FILL:4DLU:NONE,FILL:46PX:NONE,FILL:4DLU:NONE,FILL:78PX:NONE,FILL:2DLU:NONE,FILL:8DLU:NONE","CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE");
		 CellConstraints cc = new CellConstraints();
		 jpanel1.setLayout(formlayout1);

		 TitledSeparator titledseparator1 = new TitledSeparator();
		 titledseparator1.setText("Características del suelo");
		 jpanel1.add(titledseparator1,cc.xywh(2,2,25,1));

		 m_deg_suelo_LB.setName("deg_suelo.LB");
		 m_deg_suelo_LB.setText("Nivel de degradación del suelo:");
		 jpanel1.add(m_deg_suelo_LB,cc.xywh(2,5,11,1));

		 m_tip_suelo_LB.setName("tip_suelo.LB");
		 m_tip_suelo_LB.setText("Tipo del suelo:");
		 jpanel1.add(m_tip_suelo_LB,cc.xywh(2,7,7,1));

		 m_estatus_vi_LB.setName("estatus_vi.LB");
		 m_estatus_vi_LB.setText("Pendiente del suelo:");
		 jpanel1.add(m_estatus_vi_LB,cc.xywh(2,11,9,1));

		 m_tip_suelo_CB.setName("tip_suelo.CB");
		 m_tip_suelo_CB.addItem("Seleccione una opción...");
		 m_tip_suelo_CB.addItem("Limoso");
		 m_tip_suelo_CB.addItem("Franco - arcilloso");
		 m_tip_suelo_CB.addItem("Franco - limoso");
		 m_tip_suelo_CB.addItem("Otro");
		 jpanel1.add(m_tip_suelo_CB,cc.xywh(10,7,10,1));

		 m_b_vivas_CHB.setActionCommand("Barreras vivas");
		 m_b_vivas_CHB.setName("b_vivas.CHB");
		 m_b_vivas_CHB.setText("Barreras vivas");
		 jpanel1.add(m_b_vivas_CHB,cc.xywh(3,15,6,1));

		 m_b_muertas_CHB.setActionCommand("Barreras vivas");
		 m_b_muertas_CHB.setName("b_muertas.CHB");
		 m_b_muertas_CHB.setText("Barreras muertas");
		 m_b_muertas_CHB.setHorizontalAlignment(JCheckBox.CENTER);
		 jpanel1.add(m_b_muertas_CHB,cc.xywh(10,15,9,1));

		 m_titledborderside5.setOrientation(TitledBorderSide.RIGHT);
		 jpanel1.add(m_titledborderside5,cc.xywh(27,13,1,5));

		 jpanel1.add(m_titledborderside6,cc.xywh(1,13,1,5));

		 jpanel1.add(m_titledborderbottom3,cc.xywh(2,17,25,1));

		 m_titledborderlabel3.setText("Utilización actual de la parcela");
		 jpanel1.add(m_titledborderlabel3,cc.xywh(2,19,25,1));

		 TitledSeparator titledseparator2 = new TitledSeparator();
		 titledseparator2.setText("Cultivos anuales");
		 jpanel1.add(titledseparator2,cc.xywh(3,22,23,1));

		 m_maiz_CHB.setActionCommand("Barreras vivas");
		 m_maiz_CHB.setName("maiz.CHB");
		 m_maiz_CHB.setText("Maíz");
		 jpanel1.add(m_maiz_CHB,cc.xywh(3,24,3,1));

		 m_yuca_CHB.setActionCommand("Barreras vivas");
		 m_yuca_CHB.setName("yuca.CHB");
		 m_yuca_CHB.setText("Yuca");
		 jpanel1.add(m_yuca_CHB,cc.xywh(18,24,4,1));

		 m_no_cultivos_CHB.setActionCommand("Barreras vivas");
		 m_no_cultivos_CHB.setName("no_cultivos.CHB");
		 m_no_cultivos_CHB.setText("Sin cultivos");
		 jpanel1.add(m_no_cultivos_CHB,cc.xywh(3,26,6,1));

		 TitledSeparator titledseparator3 = new TitledSeparator();
		 titledseparator3.setText("Cultivos semi perennes");
		 jpanel1.add(titledseparator3,cc.xywh(3,29,23,1));

		 m_musacea_CHB.setActionCommand("Barreras vivas");
		 m_musacea_CHB.setName("musacea.CHB");
		 m_musacea_CHB.setText("Musáceas");
		 jpanel1.add(m_musacea_CHB,cc.xywh(3,31,6,1));

		 m_hay_ot_sp_CHB.setActionCommand("Barreras vivas");
		 m_hay_ot_sp_CHB.setName("hay_ot_sp.CHB");
		 m_hay_ot_sp_CHB.setText("Otros:");
		 jpanel1.add(m_hay_ot_sp_CHB,cc.xywh(3,33,4,1));

		 TitledSeparator titledseparator4 = new TitledSeparator();
		 titledseparator4.setText("Cultivos permanentes");
		 jpanel1.add(titledseparator4,cc.xywh(3,36,23,1));

		 m_arbfor_CHB.setActionCommand("Barreras vivas");
		 m_arbfor_CHB.setName("arbfor.CHB");
		 m_arbfor_CHB.setText("Árboles forestales");
		 jpanel1.add(m_arbfor_CHB,cc.xywh(3,38,9,1));

		 jpanel1.add(m_titledborderside7,cc.xywh(1,19,1,27));

		 m_titledborderside8.setOrientation(TitledBorderSide.RIGHT);
		 jpanel1.add(m_titledborderside8,cc.xywh(27,19,1,27));

		 jpanel1.add(m_titledborderbottom4,cc.xywh(2,45,25,1));

		 TitledSeparator titledseparator5 = new TitledSeparator();
		 titledseparator5.setText("Volumen de los cultivos");
		 jpanel1.add(titledseparator5,cc.xywh(3,41,23,1));

		 m_noarb_CHB.setActionCommand("Barreras vivas");
		 m_noarb_CHB.setName("noarb.CHB");
		 m_noarb_CHB.setText("Sin árboles");
		 m_noarb_CHB.setHorizontalAlignment(JCheckBox.LEFT);
		 jpanel1.add(m_noarb_CHB,cc.xywh(23,38,3,1));

		 m_no_cul_semi_CHB.setActionCommand("Barreras vivas");
		 m_no_cul_semi_CHB.setName("no_cul_semi.CHB");
		 m_no_cul_semi_CHB.setText("Sin cultivos");
		 jpanel1.add(m_no_cul_semi_CHB,cc.xywh(23,31,3,1));

		 m_pasto_CHB.setActionCommand("Barreras vivas");
		 m_pasto_CHB.setName("pasto.CHB");
		 m_pasto_CHB.setText("Pastos");
		 jpanel1.add(m_pasto_CHB,cc.xywh(18,31,4,1));

		 m_arbfru_CHB.setActionCommand("Barreras vivas");
		 m_arbfru_CHB.setName("arbfru.CHB");
		 m_arbfru_CHB.setText("Árboles frutales");
		 m_arbfru_CHB.setHorizontalAlignment(JCheckBox.CENTER);
		 jpanel1.add(m_arbfru_CHB,cc.xywh(14,38,6,1));

		 m_estatus_vi_CB.setName("estatus_vi.CB");
		 m_estatus_vi_CB.addItem("Seleccione una opción...");
		 m_estatus_vi_CB.addItem("0 - 7 %");
		 m_estatus_vi_CB.addItem("8 - 14 %");
		 m_estatus_vi_CB.addItem("15 - 21 %");
		 m_estatus_vi_CB.addItem("22 - 28 %");
		 m_estatus_vi_CB.addItem("29 - 35 %");
		 m_estatus_vi_CB.addItem("36 - 42 %");
		 m_estatus_vi_CB.addItem("> 42 %");
		 jpanel1.add(m_estatus_vi_CB,cc.xywh(12,11,10,1));

		 m_hortalizas_CHB.setActionCommand("Barreras vivas");
		 m_hortalizas_CHB.setName("hortalizas.CHB");
		 m_hortalizas_CHB.setText("Hortalizas");
		 m_hortalizas_CHB.setHorizontalAlignment(JCheckBox.LEFT);
		 jpanel1.add(m_hortalizas_CHB,cc.xywh(23,24,3,1));

		 m_papaya_CHB.setActionCommand("Barreras vivas");
		 m_papaya_CHB.setName("papaya.CHB");
		 m_papaya_CHB.setText("Papaya");
		 jpanel1.add(m_papaya_CHB,cc.xywh(11,31,6,1));

		 m_hay_ot_cer_CHB.setActionCommand("Barreras vivas");
		 m_hay_ot_cer_CHB.setName("hay_ot_cer.CHB");
		 m_hay_ot_cer_CHB.setText("Otros:");
		 m_hay_ot_cer_CHB.setHorizontalAlignment(JCheckBox.RIGHT);
		 jpanel1.add(m_hay_ot_cer_CHB,cc.xywh(19,15,5,1));

		 TitledSeparator titledseparator6 = new TitledSeparator();
		 titledseparator6.setText("Manejo del medio ambiente");
		 jpanel1.add(titledseparator6,cc.xywh(2,48,25,1));

		 m_prac_conse_CHB.setActionCommand("Ganadería");
		 m_prac_conse_CHB.setName("prac_conse.CHB");
		 m_prac_conse_CHB.setText("Uso de las siguientes prácticas de conservación de suelos:");
		 jpanel1.add(m_prac_conse_CHB,cc.xywh(2,51,20,1));

		 m_uso_aborg_CHB.setActionCommand("Ganadería");
		 m_uso_aborg_CHB.setName("uso_aborg.CHB");
		 m_uso_aborg_CHB.setText("Uso de los siguientes abonos orgánicos:");
		 jpanel1.add(m_uso_aborg_CHB,cc.xywh(2,53,20,1));

		 m_insect_org_CHB.setActionCommand("Ganadería");
		 m_insect_org_CHB.setName("insect_org.CHB");
		 m_insect_org_CHB.setText("Uso de los siguientes insecticidas orgánicos:");
		 jpanel1.add(m_insect_org_CHB,cc.xywh(2,55,20,1));

		 m_uso_quim_CHB.setActionCommand("Ganadería");
		 m_uso_quim_CHB.setName("uso_quim.CHB");
		 m_uso_quim_CHB.setText("Uso de los siguientes plaguicidas químicos:");
		 jpanel1.add(m_uso_quim_CHB,cc.xywh(2,57,20,1));

		 m_deg_suelo_CB.setName("deg_suelo.CB");
		 m_deg_suelo_CB.addItem("Seleccione una opción...");
		 m_deg_suelo_CB.addItem("Alto");
		 m_deg_suelo_CB.addItem("Medio");
		 m_deg_suelo_CB.addItem("Bajo");
		 jpanel1.add(m_deg_suelo_CB,cc.xywh(14,5,10,1));

		 m_otrocspe_TF.setName("otrocspe.TF");
		 jpanel1.add(m_otrocspe_TF,cc.xywh(8,33,5,1));

		 m_ot_tip_su_LB.setName("ot_tip_su.LB");
		 m_ot_tip_su_LB.setText("Especificar otro:");
		 m_ot_tip_su_LB.setHorizontalAlignment(JLabel.RIGHT);
		 jpanel1.add(m_ot_tip_su_LB,cc.xywh(14,9,6,1));

		 m_ot_tip_su_TF.setName("ot_tip_su.TF");
		 jpanel1.add(m_ot_tip_su_TF,cc.xywh(21,9,5,1));

		 m_ot_cerca_TF.setName("ot_cerca.TF");
		 jpanel1.add(m_ot_cerca_TF,cc.xy(25,15));

		 m_maicillo_CHB.setActionCommand("Barreras vivas");
		 m_maicillo_CHB.setName("maicillo.CHB");
		 m_maicillo_CHB.setText("Maicillo");
		 jpanel1.add(m_maicillo_CHB,cc.xywh(12,24,5,1));

		 m_frijol_CHB.setActionCommand("Barreras vivas");
		 m_frijol_CHB.setName("frijol.CHB");
		 m_frijol_CHB.setText("Frijol");
		 m_frijol_CHB.setHorizontalAlignment(JCheckBox.CENTER);
		 jpanel1.add(m_frijol_CHB,cc.xywh(8,24,3,1));

		 m_hay_ot_cul_CHB.setActionCommand("Barreras vivas");
		 m_hay_ot_cul_CHB.setName("hay_ot_cul.CHB");
		 m_hay_ot_cul_CHB.setText("Otros:");
		 jpanel1.add(m_hay_ot_cul_CHB,cc.xywh(12,26,3,1));

		 m_otrocan_TF.setName("otrocan.TF");
		 jpanel1.add(m_otrocan_TF,cc.xywh(15,26,5,1));

		 m_cerca_CHB.setName("cerca.CHB");
		 m_cerca_CHB.setHorizontalAlignment(JCheckBox.RIGHT);
		 jpanel1.add(m_cerca_CHB,cc.xywh(2,13,2,2));

		 m_titledborderlabel4.setText("Parcela cercada");
		 jpanel1.add(m_titledborderlabel4,cc.xywh(4,13,23,1));

		 JScrollPane jscrollpane1 = new JScrollPane();
		 jscrollpane1.setViewportView(m_jtable2);
		 jscrollpane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		 jscrollpane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		 jpanel1.add(jscrollpane1,cc.xywh(3,43,23,1));

		 m_c_conse_TF.setName("c_conse.TF");
		 jpanel1.add(m_c_conse_TF,cc.xywh(23,51,3,1));

		 m_c_aborg_TF.setName("c_aborg.TF");
		 jpanel1.add(m_c_aborg_TF,cc.xywh(23,53,3,1));

		 m_c_insect_TF.setName("c_insect.TF");
		 jpanel1.add(m_c_insect_TF,cc.xywh(23,55,3,1));

		 m_c_quim_TF.setName("c_quim.TF");
		 jpanel1.add(m_c_quim_TF,cc.xywh(23,57,3,1));

		 addFillComponents(jpanel1,new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27 },new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,14,15,16,17,18,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58 });
		 return jpanel1;
	 }

	 public JPanel createPanel3()
	 {
		 JPanel jpanel1 = new JPanel();
		 FormLayout formlayout1 = new FormLayout("FILL:8DLU:NONE,FILL:4DLU:NONE,FILL:83PX:NONE,FILL:4DLU:NONE,FILL:47PX:NONE,FILL:4DLU:NONE,FILL:33PX:NONE,FILL:17PX:NONE,FILL:4DLU:NONE,FILL:35PX:NONE,FILL:8DLU:NONE,FILL:40PX:NONE,FILL:18DLU:NONE,FILL:4DLU:NONE,FILL:90PX:NONE,FILL:4DLU:NONE,FILL:8DLU:NONE,FILL:4DLU:NONE,FILL:39PX:NONE,FILL:23DLU:NONE,FILL:8DLU:NONE","CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE");
		 CellConstraints cc = new CellConstraints();
		 jpanel1.setLayout(formlayout1);

		 TitledSeparator titledseparator1 = new TitledSeparator();
		 titledseparator1.setText("Planificación de actividades");
		 jpanel1.add(titledseparator1,cc.xywh(2,2,19,1));

		 m_p_riego_LB.setName("p_riego.LB");
		 m_p_riego_LB.setText("Sistema de riego:");
		 jpanel1.add(m_p_riego_LB,cc.xywh(2,5,4,1));

		 m_p_riego_TF.setName("p_riego.TF");
		 jpanel1.add(m_p_riego_TF,cc.xywh(7,5,4,1));

		 m_p_huerto_TF.setName("p_huerto.TF");
		 jpanel1.add(m_p_huerto_TF,cc.xywh(10,7,3,1));

		 m_p_filtroag_TF.setName("p_filtroag.TF");
		 jpanel1.add(m_p_filtroag_TF,cc.xywh(17,5,4,1));

		 m_p_filtroag_LB.setName("p_filtroag.LB");
		 m_p_filtroag_LB.setText("Filtro de aguas grises:");
		 m_p_filtroag_LB.setHorizontalAlignment(JLabel.RIGHT);
		 jpanel1.add(m_p_filtroag_LB,cc.xywh(12,5,4,1));

		 m_p_huerto_LB.setName("p_huerto.LB");
		 m_p_huerto_LB.setText("Establecimiento del huerto:");
		 jpanel1.add(m_p_huerto_LB,cc.xywh(2,7,7,1));

		 m_p_galline_TF.setName("p_galline.TF");
		 jpanel1.add(m_p_galline_TF,cc.xywh(10,9,3,1));

		 m_p_galline_LB.setName("p_galline.LB");
		 m_p_galline_LB.setText("Construcción de gallineros:");
		 jpanel1.add(m_p_galline_LB,cc.xywh(2,9,7,1));

		 TitledSeparator titledseparator2 = new TitledSeparator();
		 titledseparator2.setText("Disponibilidad de mano de obra:");
		 jpanel1.add(titledseparator2,cc.xywh(2,12,19,1));

		 m_titledborderlabel5.setText("Familiar");
		 jpanel1.add(m_titledborderlabel5,cc.xywh(2,15,15,1));

		 m_fam_cant_LB.setName("fam_cant.LB");
		 m_fam_cant_LB.setText("Cantidad:");
		 jpanel1.add(m_fam_cant_LB,cc.xy(3,17));

		 m_fam_cant_TF_INT.setName("fam_cant.TF.INT");
		 jpanel1.add(m_fam_cant_TF_INT,cc.xywh(5,17,3,1));

		 m_fam_per_LB.setName("fam_per.LB");
		 m_fam_per_LB.setText("Período del año:");
		 m_fam_per_LB.setHorizontalAlignment(JLabel.RIGHT);
		 jpanel1.add(m_fam_per_LB,cc.xywh(9,17,5,1));

		 m_fam_per_TF.setName("fam_per.TF");
		 jpanel1.add(m_fam_per_TF,cc.xy(15,17));

		 m_titledborderside9.setOrientation(TitledBorderSide.RIGHT);
		 jpanel1.add(m_titledborderside9,cc.xywh(17,15,1,5));

		 jpanel1.add(m_titledborderside10,cc.xywh(1,15,1,5));

		 jpanel1.add(m_titledborderbottom5,cc.xywh(2,19,15,1));

		 m_titledborderlabel6.setText("Contratada");
		 jpanel1.add(m_titledborderlabel6,cc.xywh(2,21,15,1));

		 m_con_cant_LB.setName("con_cant.LB");
		 m_con_cant_LB.setText("Cantidad:");
		 jpanel1.add(m_con_cant_LB,cc.xy(3,23));

		 m_con_cant_TF_INT.setName("con_cant.TF.INT");
		 jpanel1.add(m_con_cant_TF_INT,cc.xywh(5,23,3,1));

		 m_con_per_LB.setName("con_per.LB");
		 m_con_per_LB.setText("Período del año:");
		 m_con_per_LB.setHorizontalAlignment(JLabel.RIGHT);
		 jpanel1.add(m_con_per_LB,cc.xywh(9,23,5,1));

		 m_con_per_TF.setName("con_per.TF");
		 jpanel1.add(m_con_per_TF,cc.xy(15,23));

		 m_titledborderside11.setOrientation(TitledBorderSide.RIGHT);
		 jpanel1.add(m_titledborderside11,cc.xywh(17,21,1,5));

		 jpanel1.add(m_titledborderside12,cc.xywh(1,21,1,5));

		 jpanel1.add(m_titledborderbottom6,cc.xywh(2,25,15,1));

		 addFillComponents(jpanel1,new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21 },new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,16,17,18,19,20,22,23,24,25,26 });
		 return jpanel1;
	 }

	 /**
	  * Initializer
	  */
	 protected void initializePanel()     {
		 setLayout(new BorderLayout());
		 JScrollPane scrollPane = new JScrollPane(createPanel());
		 add(scrollPane, BorderLayout.CENTER);
	 }

	 public WindowInfo getWindowInfo() {
		 if (windowInfo == null){
			 windowInfo = new WindowInfo(WindowInfo.RESIZABLE | WindowInfo.PALETTE);
			 Dimension dim = getPreferredSize();
			 int width,heigth = 0;
			 if (dim.getHeight()>500){
				 heigth = 500;
			 }else{
				 heigth = new Double(dim.getHeight()).intValue();
			 }
			 //if(dim.getWidth()>500){
			 //width = 500;
			 //}else{
			 width = new Double(dim.getWidth()).intValue() + 20;
			 //}
			 windowInfo.setWidth(width);
			 windowInfo.setHeight(heigth);
		 }
		 return windowInfo;
	 }

	 public Object getWindowProfile() {
		 // TODO Auto-generated method stub
		 return null;
	 }


}

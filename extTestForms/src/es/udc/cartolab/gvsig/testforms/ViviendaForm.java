package es.udc.cartolab.gvsig.testforms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
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


public class ViviendaForm extends JPanel implements IWindow
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
	JLabel m_edad_produ_LB = new JLabel();
	JLabel m_nom_produ_LB = new JLabel();
	JLabel m_n_personas_LB = new JLabel();
	JLabel m_n_ninhas_LB = new JLabel();
	JLabel m_n_ninhos_LB = new JLabel();
	JTextField m_n_ninhos_TF_INT = new JTextField();
	JTextField m_n_ninhas_TF_INT = new JTextField();
	JLabel m_nid_produ_LB = new JLabel();
	JLabel m_n_mujer_LB = new JLabel();
	JTextField m_n_mujer_TF_INT = new JTextField();
	JLabel m_n_hombr_LB = new JLabel();
	JTextField m_n_hombr_TF_INT = new JTextField();
	JLabel m_n_embaraz_LB = new JLabel();
	JLabel m_direccion_LB = new JLabel();
	JTextField m_edad_produ_TF_INT = new JTextField();
	JCheckBox m_agricult_CHB = new JCheckBox();
	JTextField m_n_personas_TF_INT = new JTextField();
	JTextField m_direccion_TF = new JTextField();
	JTextField m_nid_produ_TF_INT = new JTextField();
	JTextField m_n_embaraz_TF_INT = new JTextField();
	JTextField m_nom_produ_TF = new JTextField();
	JCheckBox m_comercio_CHB = new JCheckBox();
	JCheckBox m_hay_ot_act_CHB = new JCheckBox();
	TitledBorderSide m_titledborderside1 = new TitledBorderSide();
	TitledBorderSide m_titledborderside2 = new TitledBorderSide();
	TitledBorderBottom m_titledborderbottom1 = new TitledBorderBottom();
	TitledBorderLabel m_titledborderlabel1 = new TitledBorderLabel();
	TitledBorderSide m_titledborderside3 = new TitledBorderSide();
	TitledBorderSide m_titledborderside4 = new TitledBorderSide();
	JCheckBox m_remesas_CHB = new JCheckBox();
	JCheckBox m_hay_ot_ing_CHB = new JCheckBox();
	JTextField m_otros_ing_TF = new JTextField();
	TitledBorderBottom m_titledborderbottom2 = new TitledBorderBottom();
	JTextField m_otras_act_TF = new JTextField();
	JCheckBox m_e_temporal_CHB = new JCheckBox();
	TitledBorderLabel m_titledborderlabel2 = new TitledBorderLabel();
	JLabel m_tip_migra_LB = new JLabel();
	JComboBox m_tip_migra_CB = new JComboBox();
	JLabel m_n_migrante_LB = new JLabel();
	JTextField m_n_migrante_TF_INT = new JTextField();
	JLabel m_mig_dest_LB = new JLabel();
	JCheckBox m_ganaderia_CHB = new JCheckBox();
	JComboBox m_mig_dest_CB = new JComboBox();
	TitledBorderBottom m_titledborderbottom3 = new TitledBorderBottom();
	TitledBorderSide m_titledborderside5 = new TitledBorderSide();
	TitledBorderSide m_titledborderside6 = new TitledBorderSide();
	TitledBorderLabel m_titledborderlabel3 = new TitledBorderLabel();
	JCheckBox m_e_perman_CHB = new JCheckBox();
	TitledBorderLabel m_titledborderlabel4 = new TitledBorderLabel();
	JLabel m_coordx_v_LB = new JLabel();
	JTextField m_coordx_v_TF = new JTextField();
	JLabel m_coordy_v_LB = new JLabel();
	JTextField m_coordy_v_TF = new JTextField();
	JLabel m_coordz_v_LB = new JLabel();
	TitledBorderSide m_titledborderside7 = new TitledBorderSide();
	TitledBorderBottom m_titledborderbottom4 = new TitledBorderBottom();
	JCheckBox m_pro_viv_CHB = new JCheckBox();
	TitledBorderLabel m_titledborderlabel5 = new TitledBorderLabel();
	TitledBorderSide m_titledborderside8 = new TitledBorderSide();
	TitledBorderSide m_titledborderside9 = new TitledBorderSide();
	TitledBorderBottom m_titledborderbottom5 = new TitledBorderBottom();
	TitledBorderLabel m_titledborderlabel6 = new TitledBorderLabel();
	JCheckBox m_luz_elec_CHB = new JCheckBox();
	JCheckBox m_telefono_CHB = new JCheckBox();
	JCheckBox m_agua_pot_CHB = new JCheckBox();
	JCheckBox m_alcantar_CHB = new JCheckBox();
	TitledBorderSide m_titledborderside10 = new TitledBorderSide();
	TitledBorderSide m_titledborderside11 = new TitledBorderSide();
	TitledBorderBottom m_titledborderbottom6 = new TitledBorderBottom();
	JCheckBox m_hay_in_viv_CHB = new JCheckBox();
	JComboBox m_estatus_vi_CB = new JComboBox();
	JTextField m_ot_stat_vi_TF = new JTextField();
	JLabel m_estatus_vi_LB = new JLabel();
	JComboBox m_mat_pared_CB = new JComboBox();
	JLabel m_ot_stat_vi_LB = new JLabel();
	JLabel m_ot_mat_pa_LB = new JLabel();
	JTextField m_ot_mat_pa_TF = new JTextField();
	HorizontalLineComponent m_horizontallinecomponent1 = new HorizontalLineComponent();
	JLabel m_mat_techo_LB = new JLabel();
	JComboBox m_mat_techo_CB = new JComboBox();
	JTextField m_ot_mat_te_TF = new JTextField();
	JLabel m_ot_mat_te_LB = new JLabel();
	HorizontalLineComponent m_horizontallinecomponent2 = new HorizontalLineComponent();
	JLabel m_mat_pared_LB = new JLabel();
	JLabel m_mat_piso_LB = new JLabel();
	JComboBox m_mat_piso_CB = new JComboBox();
	JLabel m_ot_mat_pi_LB = new JLabel();
	JTextField m_ot_mat_pi_TF = new JTextField();
	JComboBox m_pro_vivsex_CB = new JComboBox();
	TitledBorderSide m_titledborderside12 = new TitledBorderSide();
	JTextField m_coordz_v_TF = new JTextField();
	JCheckBox m_consumo_h_CHB = new JCheckBox();
	TitledBorderLabel m_titledborderlabel7 = new TitledBorderLabel();
	TitledBorderSide m_titledborderside13 = new TitledBorderSide();
	TitledBorderBottom m_titledborderbottom7 = new TitledBorderBottom();
	JCheckBox m_silos_CHB = new JCheckBox();
	JCheckBox m_trojas_mej_CHB = new JCheckBox();
	JCheckBox m_sacos_CHB = new JCheckBox();
	JCheckBox m_letrina_CHB = new JCheckBox();
	JCheckBox m_coc_mejor_CHB = new JCheckBox();
	JCheckBox m_filtro_ag_CHB = new JCheckBox();
	JCheckBox m_sist_rieg_CHB = new JCheckBox();
	JCheckBox m_dep_almac_CHB = new JCheckBox();
	JTextField m_ot_dep_alm_TF = new JTextField();
	JCheckBox m_cap_techo_CHB = new JCheckBox();
	JCheckBox m_gallinero_CHB = new JCheckBox();
	TitledBorderSide m_titledborderside14 = new TitledBorderSide();
	JCheckBox m_ramadas_CHB = new JCheckBox();
	JTextField m_cual_ch_TF = new JTextField();

	/**
	 * Default constructor
	 */
	 public ViviendaForm()
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
		 FormLayout formlayout1 = new FormLayout("FILL:4DLU:NONE,FILL:8DLU:NONE,FILL:185PX:NONE,FILL:4DLU:NONE,FILL:108PX:NONE,FILL:8DLU:NONE,FILL:12PX:NONE,FILL:35PX:NONE,FILL:4DLU:NONE,FILL:67DLU:NONE,FILL:4DLU:NONE,FILL:DEFAULT:NONE,FILL:75PX:NONE,FILL:8DLU:NONE,FILL:4DLU:NONE","CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:4DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:4DLU:NONE");
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
		 m_cedu_form_title.setText("Vivienda");
		 m_cedu_form_title.setVerifyInputWhenFocusTarget(false);
		 jpanel1.add(m_cedu_form_title,cc.xywh(3,2,3,1));

		 LineBorder lineborder1 = new LineBorder(new Color(0,0,0),1,false);
		 m_jtabbedpane1.setBorder(lineborder1);
		 m_jtabbedpane1.addTab("Familia",null,createPanel1());
		 m_jtabbedpane1.addTab("Vivienda y servicios públicos",null,createPanel2());
		 m_jtabbedpane1.addTab("Infraestructuras",null,createPanel3());
		 jpanel1.add(m_jtabbedpane1,cc.xywh(3,13,11,1));

		 addFillComponents(jpanel1,new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15 },new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15 });
		 return jpanel1;
	 }

	 public JPanel createPanel1()
	 {
		 JPanel jpanel1 = new JPanel();
		 FormLayout formlayout1 = new FormLayout("FILL:8DLU:NONE,FILL:4DLU:NONE,FILL:58PX:NONE,FILL:4DLU:NONE,FILL:10DLU:NONE,FILL:4DLU:NONE,FILL:18PX:NONE,FILL:4DLU:NONE,FILL:4DLU:NONE,FILL:42PX:NONE,FILL:4DLU:NONE,FILL:5PX:NONE,FILL:4DLU:NONE,FILL:34PX:NONE,FILL:4DLU:NONE,FILL:17DLU:NONE,FILL:14DLU:NONE,FILL:10PX:NONE,FILL:4DLU:NONE,FILL:4DLU:NONE,FILL:32PX:NONE,FILL:4DLU:NONE,FILL:22PX:NONE,FILL:4DLU:NONE,FILL:48PX:NONE,FILL:4DLU:NONE,FILL:21PX:NONE,FILL:4DLU:NONE,FILL:79PX:NONE,FILL:8DLU:NONE","CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE");
		 CellConstraints cc = new CellConstraints();
		 jpanel1.setLayout(formlayout1);

		 m_edad_produ_LB.setName("edad_produ.LB");
		 m_edad_produ_LB.setText("Edad:");
		 jpanel1.add(m_edad_produ_LB,cc.xywh(2,6,2,1));

		 m_nom_produ_LB.setName("nom_produ.LB");
		 m_nom_produ_LB.setText("Nombre del productor/a:");
		 jpanel1.add(m_nom_produ_LB,cc.xywh(2,4,11,1));

		 TitledSeparator titledseparator1 = new TitledSeparator();
		 titledseparator1.setText("Datos generales");
		 jpanel1.add(titledseparator1,cc.xywh(2,2,28,1));

		 m_n_personas_LB.setName("n_personas.LB");
		 m_n_personas_LB.setText("Nº de personas en su hogar actualmente:");
		 jpanel1.add(m_n_personas_LB,cc.xywh(2,8,17,1));

		 m_n_ninhas_LB.setName("n_ninhas.LB");
		 m_n_ninhas_LB.setText("Nº niñas < 5 años:");
		 jpanel1.add(m_n_ninhas_LB,cc.xywh(2,10,9,1));

		 m_n_ninhos_LB.setName("n_ninhos.LB");
		 m_n_ninhos_LB.setText("Nº niños < 5 años:");
		 jpanel1.add(m_n_ninhos_LB,cc.xywh(18,10,10,1));

		 m_n_ninhos_TF_INT.setName("n_ninhos.TF.INT");
		 jpanel1.add(m_n_ninhos_TF_INT,cc.xy(29,10));

		 m_n_ninhas_TF_INT.setName("n_ninhas.TF.INT");
		 jpanel1.add(m_n_ninhas_TF_INT,cc.xywh(12,10,5,1));

		 m_nid_produ_LB.setName("nid_produ.LB");
		 m_nid_produ_LB.setText("Nº de identificación:");
		 jpanel1.add(m_nid_produ_LB,cc.xywh(14,6,10,1));

		 m_n_mujer_LB.setName("n_mujer.LB");
		 m_n_mujer_LB.setText("Nº mujeres > 5 años:");
		 jpanel1.add(m_n_mujer_LB,cc.xywh(2,12,9,1));

		 m_n_mujer_TF_INT.setName("n_mujer.TF.INT");
		 jpanel1.add(m_n_mujer_TF_INT,cc.xywh(12,12,5,1));

		 m_n_hombr_LB.setName("n_hombr.LB");
		 m_n_hombr_LB.setText("Nº hombres > 5 años:");
		 jpanel1.add(m_n_hombr_LB,cc.xywh(18,12,10,1));

		 m_n_hombr_TF_INT.setName("n_hombr.TF.INT");
		 jpanel1.add(m_n_hombr_TF_INT,cc.xy(29,12));

		 m_n_embaraz_LB.setName("n_embaraz.LB");
		 m_n_embaraz_LB.setText("Nº mujeres embarazadas:");
		 jpanel1.add(m_n_embaraz_LB,cc.xywh(2,14,11,1));

		 m_direccion_LB.setName("direccion.LB");
		 m_direccion_LB.setText("Dirección:");
		 jpanel1.add(m_direccion_LB,cc.xywh(2,16,4,1));

		 m_edad_produ_TF_INT.setName("edad_produ.TF.INT");
		 jpanel1.add(m_edad_produ_TF_INT,cc.xywh(5,6,8,1));

		 m_agricult_CHB.setActionCommand("Agricultura");
		 m_agricult_CHB.setName("agricult.CHB");
		 m_agricult_CHB.setText("Agricultura");
		 jpanel1.add(m_agricult_CHB,cc.xywh(3,20,5,1));

		 m_n_personas_TF_INT.setName("n_personas.TF.INT");
		 jpanel1.add(m_n_personas_TF_INT,cc.xywh(20,8,10,1));

		 m_direccion_TF.setName("direccion.TF");
		 jpanel1.add(m_direccion_TF,cc.xywh(7,16,23,1));

		 m_nid_produ_TF_INT.setName("nid_produ.TF.INT");
		 jpanel1.add(m_nid_produ_TF_INT,cc.xywh(25,6,5,1));

		 m_n_embaraz_TF_INT.setName("n_embaraz.TF.INT");
		 jpanel1.add(m_n_embaraz_TF_INT,cc.xywh(14,14,3,1));

		 m_nom_produ_TF.setName("nom_produ.TF");
		 jpanel1.add(m_nom_produ_TF,cc.xywh(14,4,16,1));

		 m_comercio_CHB.setActionCommand("Ganadería");
		 m_comercio_CHB.setName("comercio.CHB");
		 m_comercio_CHB.setText("Comercio");
		 jpanel1.add(m_comercio_CHB,cc.xywh(16,20,6,1));

		 m_hay_ot_act_CHB.setActionCommand("Ganadería");
		 m_hay_ot_act_CHB.setName("hay_ot_act.CHB");
		 m_hay_ot_act_CHB.setText("Otras:");
		 m_hay_ot_act_CHB.setHorizontalAlignment(JCheckBox.RIGHT);
		 jpanel1.add(m_hay_ot_act_CHB,cc.xywh(23,20,3,1));

		 m_titledborderside1.setOrientation(TitledBorderSide.RIGHT);
		 jpanel1.add(m_titledborderside1,cc.xywh(30,18,1,5));

		 jpanel1.add(m_titledborderside2,cc.xywh(1,18,1,5));

		 jpanel1.add(m_titledborderbottom1,cc.xywh(2,22,28,1));

		 m_titledborderlabel1.setText("Tipo de ingresos");
		 jpanel1.add(m_titledborderlabel1,cc.xywh(2,24,28,1));

		 jpanel1.add(m_titledborderside3,cc.xywh(1,24,1,7));

		 m_titledborderside4.setOrientation(TitledBorderSide.RIGHT);
		 jpanel1.add(m_titledborderside4,cc.xywh(30,24,1,7));

		 m_remesas_CHB.setActionCommand("Agricultura");
		 m_remesas_CHB.setName("remesas.CHB");
		 m_remesas_CHB.setText("Remesas");
		 jpanel1.add(m_remesas_CHB,cc.xywh(3,26,6,1));

		 m_hay_ot_ing_CHB.setActionCommand("Ganadería");
		 m_hay_ot_ing_CHB.setName("hay_ot_ing.CHB");
		 m_hay_ot_ing_CHB.setText("Otros:");
		 jpanel1.add(m_hay_ot_ing_CHB,cc.xywh(3,28,3,1));

		 m_otros_ing_TF.setName("otros_ing.TF");
		 jpanel1.add(m_otros_ing_TF,cc.xywh(7,28,10,1));

		 jpanel1.add(m_titledborderbottom2,cc.xywh(2,30,28,1));

		 m_otras_act_TF.setName("otras_act.TF");
		 jpanel1.add(m_otras_act_TF,cc.xywh(27,20,3,1));

		 m_e_temporal_CHB.setActionCommand("Ganadería");
		 m_e_temporal_CHB.setName("e_temporal.CHB");
		 m_e_temporal_CHB.setText("Empleo temporal");
		 jpanel1.add(m_e_temporal_CHB,cc.xywh(11,26,11,1));

		 m_titledborderlabel2.setText("Datos de migración");
		 jpanel1.add(m_titledborderlabel2,cc.xywh(2,32,28,1));

		 m_tip_migra_LB.setName("tip_migra.LB");
		 m_tip_migra_LB.setText("Tipo:");
		 jpanel1.add(m_tip_migra_LB,cc.xy(3,34));

		 m_tip_migra_CB.setName("tip_migra.CB");
		 m_tip_migra_CB.addItem("Seleccione una opción...");
		 m_tip_migra_CB.addItem("Temporal");
		 m_tip_migra_CB.addItem("Permanente");
		 jpanel1.add(m_tip_migra_CB,cc.xywh(5,34,12,1));

		 m_n_migrante_LB.setName("n_migrante.LB");
		 m_n_migrante_LB.setText("Nº de individuos:");
		 m_n_migrante_LB.setHorizontalAlignment(JLabel.RIGHT);
		 jpanel1.add(m_n_migrante_LB,cc.xywh(18,34,8,1));

		 m_n_migrante_TF_INT.setName("n_migrante.TF.INT");
		 jpanel1.add(m_n_migrante_TF_INT,cc.xywh(27,34,3,1));

		 m_mig_dest_LB.setName("mig_dest.LB");
		 m_mig_dest_LB.setText("Tipo de destino:");
		 jpanel1.add(m_mig_dest_LB,cc.xywh(3,36,6,1));

		 m_ganaderia_CHB.setActionCommand("Ganadería");
		 m_ganaderia_CHB.setName("ganaderia.CHB");
		 m_ganaderia_CHB.setText("Ganadería");
		 jpanel1.add(m_ganaderia_CHB,cc.xywh(9,20,6,1));

		 m_mig_dest_CB.setName("mig_dest.CB");
		 m_mig_dest_CB.addItem("Seleccione una opción...");
		 m_mig_dest_CB.addItem("Nacional");
		 m_mig_dest_CB.addItem("Internacional");
		 jpanel1.add(m_mig_dest_CB,cc.xywh(10,36,12,1));

		 jpanel1.add(m_titledborderbottom3,cc.xywh(2,38,28,1));

		 m_titledborderside5.setOrientation(TitledBorderSide.RIGHT);
		 jpanel1.add(m_titledborderside5,cc.xywh(30,32,1,7));

		 jpanel1.add(m_titledborderside6,cc.xywh(1,32,1,7));

		 m_titledborderlabel3.setText("Principales actividades económicas");
		 jpanel1.add(m_titledborderlabel3,cc.xywh(2,18,28,1));

		 m_e_perman_CHB.setActionCommand("Ganadería");
		 m_e_perman_CHB.setName("e_perman.CHB");
		 m_e_perman_CHB.setText("Empleo permanente");
		 jpanel1.add(m_e_perman_CHB,cc.xywh(23,26,7,1));

		 addFillComponents(jpanel1,new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30 },new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,19,20,21,22,23,25,26,27,28,29,30,31,33,34,35,36,37,38,39 });
		 return jpanel1;
	 }

	 public JPanel createPanel2()
	 {
		 JPanel jpanel1 = new JPanel();
		 FormLayout formlayout1 = new FormLayout("FILL:8DLU:NONE,FILL:4DLU:NONE,FILL:24PX:NONE,FILL:54PX:NONE,FILL:8DLU:NONE,FILL:24PX:NONE,FILL:4DLU:NONE,FILL:38PX:NONE,FILL:4DLU:NONE,FILL:9PX:NONE,FILL:8DLU:NONE,FILL:24PX:NONE,FILL:4DLU:NONE,FILL:8DLU:NONE,FILL:40PX:NONE,FILL:4DLU:NONE,FILL:8DLU:NONE,FILL:4DLU:NONE,FILL:8DLU:NONE,FILL:56PX:NONE,FILL:8DLU:NONE,FILL:28PX:NONE,FILL:4DLU:NONE,FILL:44PX:NONE,FILL:59PX:NONE,FILL:4DLU:NONE,FILL:8DLU:NONE","CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:4DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:4DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:4DLU:NONE");
		 CellConstraints cc = new CellConstraints();
		 jpanel1.setLayout(formlayout1);

		 m_titledborderlabel4.setText("Coordenadas");
		 jpanel1.add(m_titledborderlabel4,cc.xywh(2,11,15,1));

		 m_coordx_v_LB.setName("coordx_v.LB");
		 m_coordx_v_LB.setText("X:");
		 jpanel1.add(m_coordx_v_LB,cc.xy(3,13));

		 m_coordx_v_TF.setName("coordx_v.TF");
		 jpanel1.add(m_coordx_v_TF,cc.xy(4,13));

		 m_coordy_v_LB.setName("coordy_v.LB");
		 m_coordy_v_LB.setText("Y:");
		 jpanel1.add(m_coordy_v_LB,cc.xy(6,13));

		 m_coordy_v_TF.setName("coordy_v.TF");
		 jpanel1.add(m_coordy_v_TF,cc.xywh(8,13,3,1));

		 m_coordz_v_LB.setName("coordz_v.LB");
		 m_coordz_v_LB.setText("Z:");
		 jpanel1.add(m_coordz_v_LB,cc.xy(12,13));

		 jpanel1.add(m_titledborderside7,cc.xywh(1,11,1,5));

		 jpanel1.add(m_titledborderbottom4,cc.xywh(2,15,15,1));

		 m_pro_viv_CHB.setActionCommand("La vivienda es propiedad legal de:");
		 m_pro_viv_CHB.setName("pro_viv.CHB");
		 m_pro_viv_CHB.setText("La vivienda es propiedad legal de:");
		 m_pro_viv_CHB.setHorizontalAlignment(JCheckBox.RIGHT);
		 jpanel1.add(m_pro_viv_CHB,cc.xywh(2,9,16,1));

		 m_titledborderlabel5.setText("Materiales de construcción");
		 jpanel1.add(m_titledborderlabel5,cc.xywh(2,17,25,1));

		 jpanel1.add(m_titledborderside8,cc.xywh(1,17,1,18));

		 m_titledborderside9.setOrientation(TitledBorderSide.RIGHT);
		 jpanel1.add(m_titledborderside9,cc.xywh(27,17,1,18));

		 jpanel1.add(m_titledborderbottom5,cc.xywh(2,34,25,1));

		 m_titledborderlabel6.setText("Servicios públicos disponibles");
		 jpanel1.add(m_titledborderlabel6,cc.xywh(2,36,19,1));

		 m_luz_elec_CHB.setActionCommand("Electricidad");
		 m_luz_elec_CHB.setName("luz_elec.CHB");
		 m_luz_elec_CHB.setText("Electricidad");
		 jpanel1.add(m_luz_elec_CHB,cc.xywh(3,38,4,1));

		 m_telefono_CHB.setActionCommand("Electricidad");
		 m_telefono_CHB.setName("telefono.CHB");
		 m_telefono_CHB.setText("Red de telefonía celular o fija");
		 jpanel1.add(m_telefono_CHB,cc.xywh(8,38,13,1));

		 m_agua_pot_CHB.setActionCommand("Electricidad");
		 m_agua_pot_CHB.setName("agua_pot.CHB");
		 m_agua_pot_CHB.setText("Agua potable");
		 jpanel1.add(m_agua_pot_CHB,cc.xywh(3,40,4,1));

		 m_alcantar_CHB.setActionCommand("Electricidad");
		 m_alcantar_CHB.setName("alcantar.CHB");
		 m_alcantar_CHB.setText("Alcantarillado");
		 jpanel1.add(m_alcantar_CHB,cc.xywh(8,40,12,1));

		 m_titledborderside10.setOrientation(TitledBorderSide.RIGHT);
		 jpanel1.add(m_titledborderside10,cc.xywh(21,36,1,7));

		 jpanel1.add(m_titledborderside11,cc.xywh(1,36,1,7));

		 jpanel1.add(m_titledborderbottom6,cc.xywh(2,42,19,1));

		 m_hay_in_viv_CHB.setActionCommand("Electricidad");
		 m_hay_in_viv_CHB.setName("hay_in_viv.CHB");
		 m_hay_in_viv_CHB.setText("Existe posibilidad de inundación en la vivienda");
		 jpanel1.add(m_hay_in_viv_CHB,cc.xywh(3,44,19,1));

		 TitledSeparator titledseparator1 = new TitledSeparator();
		 titledseparator1.setText("Datos generales");
		 jpanel1.add(titledseparator1,cc.xywh(2,2,25,1));

		 m_estatus_vi_CB.setName("estatus_vi.CB");
		 m_estatus_vi_CB.addItem("Seleccione una opción...");
		 m_estatus_vi_CB.addItem("Propia");
		 m_estatus_vi_CB.addItem("Alquilada");
		 m_estatus_vi_CB.addItem("Otro");
		 jpanel1.add(m_estatus_vi_CB,cc.xywh(10,4,11,1));

		 m_ot_stat_vi_TF.setName("ot_stat_vi.TF");
		 jpanel1.add(m_ot_stat_vi_TF,cc.xywh(19,6,8,1));

		 m_estatus_vi_LB.setName("estatus_vi.LB");
		 m_estatus_vi_LB.setText("Estatus de la vivienda:");
		 jpanel1.add(m_estatus_vi_LB,cc.xywh(2,4,7,1));

		 m_mat_pared_CB.setName("mat_pared.CB");
		 m_mat_pared_CB.addItem("Seleccione una opción...");
		 m_mat_pared_CB.addItem("Bahareque");
		 m_mat_pared_CB.addItem("Adobe");
		 m_mat_pared_CB.addItem("Ladrillo");
		 m_mat_pared_CB.addItem("Otro");
		 jpanel1.add(m_mat_pared_CB,cc.xywh(6,19,10,1));

		 m_ot_stat_vi_LB.setName("ot_stat_vi.LB");
		 m_ot_stat_vi_LB.setText("Especificar otro:");
		 m_ot_stat_vi_LB.setHorizontalAlignment(JLabel.RIGHT);
		 jpanel1.add(m_ot_stat_vi_LB,cc.xywh(10,6,8,1));

		 m_ot_mat_pa_LB.setName("ot_mat_pa.LB");
		 m_ot_mat_pa_LB.setText("Especificar otro:");
		 m_ot_mat_pa_LB.setHorizontalAlignment(JLabel.RIGHT);
		 jpanel1.add(m_ot_mat_pa_LB,cc.xywh(10,21,8,1));

		 m_ot_mat_pa_TF.setName("ot_mat_pa.TF");
		 jpanel1.add(m_ot_mat_pa_TF,cc.xywh(19,21,7,1));

		 jpanel1.add(m_horizontallinecomponent1,cc.xywh(3,23,23,1));

		 m_mat_techo_LB.setFont(new Font("Dialog",Font.PLAIN,12));
		 m_mat_techo_LB.setName("mat_techo.LB");
		 m_mat_techo_LB.setText("Techo:");
		 jpanel1.add(m_mat_techo_LB,cc.xywh(3,25,2,1));

		 m_mat_techo_CB.setName("mat_techo.CB");
		 m_mat_techo_CB.addItem("Seleccione una opción...");
		 m_mat_techo_CB.addItem("Teja");
		 m_mat_techo_CB.addItem("Alucin");
		 m_mat_techo_CB.addItem("Manaca");
		 m_mat_techo_CB.addItem("Otro");
		 jpanel1.add(m_mat_techo_CB,cc.xywh(6,25,10,1));

		 m_ot_mat_te_TF.setName("ot_mat_te.TF");
		 jpanel1.add(m_ot_mat_te_TF,cc.xywh(19,26,7,1));

		 m_ot_mat_te_LB.setName("ot_mat_te.LB");
		 m_ot_mat_te_LB.setText("Especificar otro:");
		 m_ot_mat_te_LB.setHorizontalAlignment(JLabel.RIGHT);
		 jpanel1.add(m_ot_mat_te_LB,cc.xywh(10,26,8,1));

		 jpanel1.add(m_horizontallinecomponent2,cc.xywh(3,28,23,1));

		 m_mat_pared_LB.setFont(new Font("Dialog",Font.PLAIN,12));
		 m_mat_pared_LB.setName("mat_pared.LB");
		 m_mat_pared_LB.setText("Paredes:");
		 jpanel1.add(m_mat_pared_LB,cc.xywh(3,19,2,1));

		 m_mat_piso_LB.setFont(new Font("Dialog",Font.PLAIN,12));
		 m_mat_piso_LB.setName("mat_piso.LB");
		 m_mat_piso_LB.setText("Piso:");
		 jpanel1.add(m_mat_piso_LB,cc.xywh(3,30,2,1));

		 m_mat_piso_CB.setName("mat_piso.CB");
		 m_mat_piso_CB.addItem("Seleccione una opción...");
		 m_mat_piso_CB.addItem("Mosaico");
		 m_mat_piso_CB.addItem("Tierra");
		 m_mat_piso_CB.addItem("Cemento");
		 m_mat_piso_CB.addItem("Otro");
		 jpanel1.add(m_mat_piso_CB,cc.xywh(6,30,10,1));

		 m_ot_mat_pi_LB.setName("ot_mat_pi.LB");
		 m_ot_mat_pi_LB.setText("Especificar otro:");
		 m_ot_mat_pi_LB.setHorizontalAlignment(JLabel.RIGHT);
		 jpanel1.add(m_ot_mat_pi_LB,cc.xywh(10,32,8,1));

		 m_ot_mat_pi_TF.setName("ot_mat_pi.TF");
		 jpanel1.add(m_ot_mat_pi_TF,cc.xywh(19,32,7,1));

		 m_pro_vivsex_CB.setName("pro_vivsex.CB");
		 m_pro_vivsex_CB.addItem("Seleccione una opción...");
		 m_pro_vivsex_CB.addItem("Un hombre");
		 m_pro_vivsex_CB.addItem("Una mujer");
		 jpanel1.add(m_pro_vivsex_CB,cc.xywh(19,9,7,1));

		 m_titledborderside12.setOrientation(TitledBorderSide.RIGHT);
		 jpanel1.add(m_titledborderside12,cc.xywh(17,11,1,5));

		 m_coordz_v_TF.setName("coordz_v.TF");
		 jpanel1.add(m_coordz_v_TF,cc.xywh(14,13,2,1));

		 addFillComponents(jpanel1,new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27 },new int[]{ 1,2,3,4,5,6,7,8,9,10,12,13,14,15,16,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,37,38,39,40,41,42,43,44,45,46,47 });
		 return jpanel1;
	 }

	 public JPanel createPanel3()
	 {
		 JPanel jpanel1 = new JPanel();
		 FormLayout formlayout1 = new FormLayout("FILL:8DLU:NONE,FILL:4DLU:NONE,FILL:167PX:NONE,FILL:4DLU:NONE,FILL:160PX:NONE,FILL:4DLU:NONE,FILL:89PX:NONE,FILL:4DLU:NONE,FILL:71PX:NONE,FILL:14DLU:NONE,FILL:8DLU:NONE","CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE");
		 CellConstraints cc = new CellConstraints();
		 jpanel1.setLayout(formlayout1);

		 TitledSeparator titledseparator1 = new TitledSeparator();
		 titledseparator1.setText("Infraestructuras básicas");
		 jpanel1.add(titledseparator1,cc.xywh(2,2,9,1));

		 m_consumo_h_CHB.setActionCommand("El agua recibe el siguiente tratamiento de purificación:");
		 m_consumo_h_CHB.setName("consumo_h.CHB");
		 m_consumo_h_CHB.setText("El agua recibe el siguiente tratamiento de purificación:");
		 jpanel1.add(m_consumo_h_CHB,cc.xywh(3,6,5,1));

		 TitledSeparator titledseparator2 = new TitledSeparator();
		 titledseparator2.setText("Infraestructuras productivas");
		 jpanel1.add(titledseparator2,cc.xywh(2,9,9,1));

		 m_titledborderlabel7.setText("Sistema de almacenamiento de granos básico");
		 jpanel1.add(m_titledborderlabel7,cc.xywh(2,11,9,1));

		 jpanel1.add(m_titledborderside13,cc.xywh(1,11,1,5));

		 jpanel1.add(m_titledborderbottom7,cc.xywh(2,15,9,1));

		 m_silos_CHB.setActionCommand("Silos metálicos");
		 m_silos_CHB.setName("silos.CHB");
		 m_silos_CHB.setText("Silos metálicos");
		 jpanel1.add(m_silos_CHB,cc.xy(3,13));

		 m_trojas_mej_CHB.setActionCommand("Silos metálicos");
		 m_trojas_mej_CHB.setName("trojas_mej.CHB");
		 m_trojas_mej_CHB.setText("Trojas mejoradas");
		 m_trojas_mej_CHB.setHorizontalAlignment(JCheckBox.LEFT);
		 jpanel1.add(m_trojas_mej_CHB,cc.xywh(5,13,2,1));

		 m_sacos_CHB.setActionCommand("Silos metálicos");
		 m_sacos_CHB.setName("sacos.CHB");
		 m_sacos_CHB.setText("Sacos");
		 m_sacos_CHB.setHorizontalAlignment(JCheckBox.LEFT);
		 jpanel1.add(m_sacos_CHB,cc.xy(7,13));

		 m_letrina_CHB.setActionCommand("Letrinas");
		 m_letrina_CHB.setName("letrina.CHB");
		 m_letrina_CHB.setText("Letrinas");
		 jpanel1.add(m_letrina_CHB,cc.xy(3,4));

		 m_coc_mejor_CHB.setActionCommand("Silos metálicos");
		 m_coc_mejor_CHB.setName("coc_mejor.CHB");
		 m_coc_mejor_CHB.setText("Cocina mejorada");
		 jpanel1.add(m_coc_mejor_CHB,cc.xy(5,4));

		 m_filtro_ag_CHB.setActionCommand("Silos metálicos");
		 m_filtro_ag_CHB.setName("filtro_ag.CHB");
		 m_filtro_ag_CHB.setText("Filtros de aguas grises");
		 jpanel1.add(m_filtro_ag_CHB,cc.xywh(7,4,4,1));

		 m_sist_rieg_CHB.setActionCommand("Letrinas");
		 m_sist_rieg_CHB.setName("sist_rieg.CHB");
		 m_sist_rieg_CHB.setText("Sistema de riego");
		 jpanel1.add(m_sist_rieg_CHB,cc.xy(3,17));

		 m_dep_almac_CHB.setActionCommand("Letrinas");
		 m_dep_almac_CHB.setName("dep_almac.CHB");
		 m_dep_almac_CHB.setText("Dep. para el almac. de agua para uso doméstico del tipo:");
		 jpanel1.add(m_dep_almac_CHB,cc.xywh(3,21,5,1));

		 m_ot_dep_alm_TF.setName("ot_dep_alm.TF");
		 jpanel1.add(m_ot_dep_alm_TF,cc.xywh(9,21,2,1));

		 m_cap_techo_CHB.setActionCommand("Letrinas");
		 m_cap_techo_CHB.setName("cap_techo.CHB");
		 m_cap_techo_CHB.setText("Condiciones para captación de agua de lluvia");
		 jpanel1.add(m_cap_techo_CHB,cc.xywh(3,19,5,1));

		 m_gallinero_CHB.setActionCommand("Letrinas");
		 m_gallinero_CHB.setName("gallinero.CHB");
		 m_gallinero_CHB.setText("Gallineros");
		 m_gallinero_CHB.setHorizontalAlignment(JCheckBox.LEFT);
		 jpanel1.add(m_gallinero_CHB,cc.xy(5,17));

		 m_titledborderside14.setOrientation(TitledBorderSide.RIGHT);
		 jpanel1.add(m_titledborderside14,cc.xywh(11,11,1,5));

		 m_ramadas_CHB.setActionCommand("Silos metálicos");
		 m_ramadas_CHB.setName("ramadas.CHB");
		 m_ramadas_CHB.setText("Ramadas");
		 jpanel1.add(m_ramadas_CHB,cc.xywh(8,13,3,1));

		 m_cual_ch_TF.setName("cual_ch.TF");
		 jpanel1.add(m_cual_ch_TF,cc.xywh(9,6,2,1));

		 addFillComponents(jpanel1,new int[]{ 1,2,3,4,5,6,7,8,9,10,11 },new int[]{ 1,2,3,4,5,6,7,8,9,10,12,13,14,15,16,17,18,19,20,21,22 });
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

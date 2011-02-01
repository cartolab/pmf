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
import javax.swing.JTable;
import javax.swing.JTextField;

import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.WindowInfo;
import com.jeta.forms.components.separator.TitledSeparator;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;


public class ComunidadForm extends JPanel implements IWindow
{
	WindowInfo windowInfo = null;
	JLabel m_cod_com_LB = new JLabel();
	JLabel m_municip_LB = new JLabel();
	JLabel m_n_fam_LB = new JLabel();
	JLabel m_departa_LB = new JLabel();
	JLabel m_nombre_LB = new JLabel();
	JLabel m_n_habit_LB = new JLabel();
	JLabel m_cod_cedu_LB = new JLabel();
	JButton m_jbutton1 = new JButton();
	JComboBox m_cod_cedu_CB = new JComboBox();
	JTextField m_nombre_TF = new JTextField();
	JTextField m_departa_TF = new JTextField();
	JTextField m_n_habit_TF_INT = new JTextField();
	JLabel m_cod_cedu_LB1 = new JLabel();
	JComboBox m_cod_cedu_CB1 = new JComboBox();
	JButton m_jbutton2 = new JButton();
	JTextField m_cod_com_TF = new JTextField();
	JTextField m_municip_TF = new JTextField();
	JTextField m_n_fam_TF_INT = new JTextField();
	JLabel m_cod_cedu_LB2 = new JLabel();
	JComboBox m_cod_cedu_CB2 = new JComboBox();
	JButton m_jbutton3 = new JButton();
	JCheckBox m_cent_jard_CHB = new JCheckBox();
	JCheckBox m_cent_esc_CHB = new JCheckBox();
	JCheckBox m_cent_ccyd_CHB = new JCheckBox();
	JCheckBox m_csalud_CHB = new JCheckBox();
	JLabel m_cod_csalud_LB = new JLabel();
	JComboBox m_cod_csalud_CB = new JComboBox();
	JButton m_jbutton4 = new JButton();
	JCheckBox m_creunion_CHB = new JCheckBox();
	JLabel m_cod_creu_LB = new JLabel();
	JComboBox m_cod_creu_CB = new JComboBox();
	JButton m_jbutton5 = new JButton();
	JCheckBox m_luz_elec_CHB = new JCheckBox();
	JCheckBox m_alcantar_CHB = new JCheckBox();
	JCheckBox m_agua_pot_CHB = new JCheckBox();
	JTable m_jtable1 = new JTable();
	JTable m_jtable2 = new JTable();
	JLabel m_cedu_form_title = new JLabel();
	JCheckBox m_tfn_fijo_CHB = new JCheckBox();

	/**
	 * Default constructor
	 */
	 public ComunidadForm()
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
		 FormLayout formlayout1 = new FormLayout("FILL:4DLU:NONE,FILL:8DLU:NONE,FILL:43PX:NONE,FILL:50DLU:NONE,FILL:4DLU:NONE,FILL:80PX:NONE,FILL:44PX:NONE,FILL:8DLU:NONE,FILL:12PX:NONE,FILL:48PX:NONE,FILL:4DLU:NONE,FILL:33DLU:NONE,FILL:4DLU:NONE,FILL:DEFAULT:NONE,FILL:78PX:NONE,FILL:45PX:NONE,FILL:8DLU:NONE,FILL:4DLU:NONE","CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE");
		 CellConstraints cc = new CellConstraints();
		 jpanel1.setLayout(formlayout1);

		 m_cod_com_LB.setName("cod_com.LB");
		 m_cod_com_LB.setText("Código:");
		 jpanel1.add(m_cod_com_LB,cc.xywh(9,8,4,1));

		 m_municip_LB.setName("municip.LB");
		 m_municip_LB.setText("Municipio:");
		 jpanel1.add(m_municip_LB,cc.xywh(9,10,4,1));

		 m_n_fam_LB.setName("n_fam.LB");
		 m_n_fam_LB.setText("Nº de familias:");
		 jpanel1.add(m_n_fam_LB,cc.xywh(9,12,4,1));

		 m_departa_LB.setName("departa.LB");
		 m_departa_LB.setText("Departamento:");
		 jpanel1.add(m_departa_LB,cc.xywh(3,10,2,1));

		 m_nombre_LB.setName("nombre.LB");
		 m_nombre_LB.setText("Nombre:");
		 jpanel1.add(m_nombre_LB,cc.xywh(3,8,2,1));

		 m_n_habit_LB.setName("n_habit.LB");
		 m_n_habit_LB.setText("Nº de habitantes:");
		 jpanel1.add(m_n_habit_LB,cc.xywh(3,12,2,1));

		 TitledSeparator titledseparator1 = new TitledSeparator();
		 titledseparator1.setText("Datos generales");
		 jpanel1.add(titledseparator1,cc.xywh(3,6,14,1));

		 TitledSeparator titledseparator2 = new TitledSeparator();
		 titledseparator2.setText("Educación");
		 jpanel1.add(titledseparator2,cc.xywh(3,15,14,1));

		 m_cod_cedu_LB.setName("cod_cedu.LB");
		 m_cod_cedu_LB.setText("Código:");
		 m_cod_cedu_LB.setHorizontalAlignment(JLabel.RIGHT);
		 jpanel1.add(m_cod_cedu_LB,cc.xy(4,19));

		 m_jbutton1.setActionCommand("Información");
		 m_jbutton1.setText("Información");
		 jpanel1.add(m_jbutton1,cc.xywh(12,19,4,1));

		 m_cod_cedu_CB.setName("cod_cedu.CB");
		 m_cod_cedu_CB.addItem("Seleccione una opción...");
		 jpanel1.add(m_cod_cedu_CB,cc.xywh(6,19,5,1));

		 m_nombre_TF.setName("nombre.TF");
		 jpanel1.add(m_nombre_TF,cc.xywh(6,8,2,1));

		 m_departa_TF.setName("departa.TF");
		 jpanel1.add(m_departa_TF,cc.xywh(6,10,2,1));

		 m_n_habit_TF_INT.setName("n_habit.TF.INT");
		 jpanel1.add(m_n_habit_TF_INT,cc.xywh(6,12,2,1));

		 m_cod_cedu_LB1.setName("cod_cedu.LB");
		 m_cod_cedu_LB1.setText("Código:");
		 m_cod_cedu_LB1.setHorizontalAlignment(JLabel.RIGHT);
		 jpanel1.add(m_cod_cedu_LB1,cc.xy(4,23));

		 m_cod_cedu_CB1.setName("cod_cedu.CB");
		 m_cod_cedu_CB1.addItem("Seleccione una opción...");
		 jpanel1.add(m_cod_cedu_CB1,cc.xywh(6,23,5,1));

		 m_jbutton2.setActionCommand("Información");
		 m_jbutton2.setText("Información");
		 jpanel1.add(m_jbutton2,cc.xywh(12,23,4,1));

		 m_cod_com_TF.setName("cod_com.TF");
		 jpanel1.add(m_cod_com_TF,cc.xywh(14,8,3,1));

		 m_municip_TF.setName("municip.TF");
		 jpanel1.add(m_municip_TF,cc.xywh(14,10,3,1));

		 m_n_fam_TF_INT.setName("n_fam.TF.INT");
		 jpanel1.add(m_n_fam_TF_INT,cc.xywh(14,12,3,1));

		 m_cod_cedu_LB2.setName("cod_cedu.LB");
		 m_cod_cedu_LB2.setText("Código:");
		 m_cod_cedu_LB2.setHorizontalAlignment(JLabel.RIGHT);
		 jpanel1.add(m_cod_cedu_LB2,cc.xy(4,27));

		 m_cod_cedu_CB2.setName("cod_cedu.CB");
		 m_cod_cedu_CB2.addItem("Seleccione una opción...");
		 jpanel1.add(m_cod_cedu_CB2,cc.xywh(6,27,5,1));

		 m_jbutton3.setActionCommand("Información");
		 m_jbutton3.setText("Información");
		 jpanel1.add(m_jbutton3,cc.xywh(12,27,4,1));

		 m_cent_jard_CHB.setActionCommand("Existe centro educativo tipo jardín de niños.");
		 m_cent_jard_CHB.setName("cent_jard.CHB");
		 m_cent_jard_CHB.setText("Existe centro educativo tipo jardín de niños");
		 jpanel1.add(m_cent_jard_CHB,cc.xywh(3,17,8,1));

		 m_cent_esc_CHB.setActionCommand("Existe centro educativo escolar.");
		 m_cent_esc_CHB.setName("cent_esc.CHB");
		 m_cent_esc_CHB.setText("Existe centro educativo escolar");
		 jpanel1.add(m_cent_esc_CHB,cc.xywh(3,21,5,1));

		 m_cent_ccyd_CHB.setActionCommand("Existe centro educativo a nivel de ciclo común y diversificado.");
		 m_cent_ccyd_CHB.setName("cent_ccyd.CHB");
		 m_cent_ccyd_CHB.setText("Existe centro educativo a nivel de ciclo común y diversificado");
		 jpanel1.add(m_cent_ccyd_CHB,cc.xywh(3,25,13,1));

		 TitledSeparator titledseparator3 = new TitledSeparator();
		 titledseparator3.setText("Salud");
		 jpanel1.add(titledseparator3,cc.xywh(3,30,14,1));

		 m_csalud_CHB.setActionCommand("Existe centro educativo a nivel de ciclo común y diversificado.");
		 m_csalud_CHB.setName("csalud.CHB");
		 m_csalud_CHB.setText("Existe centro de salud en la comunidad");
		 jpanel1.add(m_csalud_CHB,cc.xywh(3,32,7,1));

		 m_cod_csalud_LB.setName("cod_csalud.LB");
		 m_cod_csalud_LB.setText("Código:");
		 m_cod_csalud_LB.setHorizontalAlignment(JLabel.RIGHT);
		 jpanel1.add(m_cod_csalud_LB,cc.xy(4,34));

		 m_cod_csalud_CB.setName("cod_csalud.CB");
		 m_cod_csalud_CB.addItem("Seleccione una opción...");
		 jpanel1.add(m_cod_csalud_CB,cc.xywh(6,34,5,1));

		 m_jbutton4.setActionCommand("Información");
		 m_jbutton4.setText("Información");
		 jpanel1.add(m_jbutton4,cc.xywh(12,34,4,1));

		 TitledSeparator titledseparator4 = new TitledSeparator();
		 titledseparator4.setText("Centro de reuniones");
		 jpanel1.add(titledseparator4,cc.xywh(3,37,14,1));

		 m_creunion_CHB.setActionCommand("Existe centro educativo a nivel de ciclo común y diversificado.");
		 m_creunion_CHB.setName("creunion.CHB");
		 m_creunion_CHB.setText("Existe centro de reuniones en la comunidad");
		 jpanel1.add(m_creunion_CHB,cc.xywh(3,39,11,1));

		 m_cod_creu_LB.setName("cod_creu.LB");
		 m_cod_creu_LB.setText("Código:");
		 m_cod_creu_LB.setHorizontalAlignment(JLabel.RIGHT);
		 jpanel1.add(m_cod_creu_LB,cc.xy(4,41));

		 m_cod_creu_CB.setName("cod_creu.CB");
		 m_cod_creu_CB.addItem("Seleccione una opción...");
		 jpanel1.add(m_cod_creu_CB,cc.xywh(6,41,5,1));

		 m_jbutton5.setActionCommand("Información");
		 m_jbutton5.setText("Información");
		 jpanel1.add(m_jbutton5,cc.xywh(12,41,4,1));

		 TitledSeparator titledseparator5 = new TitledSeparator();
		 titledseparator5.setText("Servicios públicos presentes");
		 jpanel1.add(titledseparator5,cc.xywh(3,44,14,1));

		 m_luz_elec_CHB.setActionCommand("Existe centro educativo a nivel de ciclo común y diversificado.");
		 m_luz_elec_CHB.setName("luz_elec.CHB");
		 m_luz_elec_CHB.setText("Luz eléctrica");
		 jpanel1.add(m_luz_elec_CHB,cc.xywh(3,46,3,1));

		 m_alcantar_CHB.setActionCommand("Existe centro educativo a nivel de ciclo común y diversificado.");
		 m_alcantar_CHB.setName("alcantar.CHB");
		 m_alcantar_CHB.setText("Alcantarillado");
		 jpanel1.add(m_alcantar_CHB,cc.xywh(6,46,2,1));

		 m_agua_pot_CHB.setActionCommand("Existe centro educativo a nivel de ciclo común y diversificado.");
		 m_agua_pot_CHB.setName("agua_pot.CHB");
		 m_agua_pot_CHB.setText("Agua potable");
		 jpanel1.add(m_agua_pot_CHB,cc.xywh(15,46,2,1));

		 TitledSeparator titledseparator6 = new TitledSeparator();
		 titledseparator6.setText("Organización base");
		 jpanel1.add(titledseparator6,cc.xywh(3,49,14,1));

		 JScrollPane jscrollpane1 = new JScrollPane();
		 jscrollpane1.setViewportView(m_jtable1);
		 jscrollpane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		 jscrollpane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		 jpanel1.add(jscrollpane1,cc.xywh(3,51,14,1));

		 TitledSeparator titledseparator7 = new TitledSeparator();
		 titledseparator7.setText("Presencia institucional");
		 jpanel1.add(titledseparator7,cc.xywh(3,54,14,1));

		 JScrollPane jscrollpane2 = new JScrollPane();
		 jscrollpane2.setViewportView(m_jtable2);
		 jscrollpane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		 jscrollpane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		 jpanel1.add(jscrollpane2,cc.xywh(3,56,14,1));

		 m_cedu_form_title.setFocusable(false);
		 m_cedu_form_title.setFont(new Font("Dialog",Font.BOLD,16));
		 m_cedu_form_title.setForeground(new Color(49,106,196));
		 m_cedu_form_title.setName("cedu_form_title");
		 m_cedu_form_title.setRequestFocusEnabled(false);
		 m_cedu_form_title.setText("Comunidad");
		 m_cedu_form_title.setVerifyInputWhenFocusTarget(false);
		 jpanel1.add(m_cedu_form_title,cc.xywh(3,2,4,1));

		 m_tfn_fijo_CHB.setActionCommand("Existe centro educativo a nivel de ciclo común y diversificado.");
		 m_tfn_fijo_CHB.setName("tfn_fijo.CHB");
		 m_tfn_fijo_CHB.setText("Telefonía");
		 jpanel1.add(m_tfn_fijo_CHB,cc.xywh(10,46,3,1));

		 addFillComponents(jpanel1,new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18 },new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57 });
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

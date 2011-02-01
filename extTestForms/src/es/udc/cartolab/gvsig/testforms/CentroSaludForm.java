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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.WindowInfo;
import com.jeta.forms.components.border.TitledBorderBottom;
import com.jeta.forms.components.border.TitledBorderLabel;
import com.jeta.forms.components.border.TitledBorderSide;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;


public class CentroSaludForm extends JPanel implements IWindow
{
	WindowInfo windowInfo = null;
	JLabel m_cod_csalud_LB = new JLabel();
	JLabel m_cod_com_LB = new JLabel();
	TitledBorderLabel m_titledborderlabel1 = new TitledBorderLabel();
	TitledBorderSide m_titledborderside1 = new TitledBorderSide();
	TitledBorderBottom m_titledborderbottom1 = new TitledBorderBottom();
	JLabel m_cedu_form_title = new JLabel();
	JLabel m_coordx_cs_LB = new JLabel();
	TitledBorderLabel m_titledborderlabel2 = new TitledBorderLabel();
	JLabel m_coordy_cs_LB = new JLabel();
	JTextField m_coordy_cs_TF_REAL = new JTextField();
	TitledBorderSide m_titledborderside2 = new TitledBorderSide();
	TitledBorderSide m_titledborderside3 = new TitledBorderSide();
	TitledBorderBottom m_titledborderbottom2 = new TitledBorderBottom();
	JTextField m_coordx_cs_TF_REAL = new JTextField();
	TitledBorderLabel m_titledborderlabel3 = new TitledBorderLabel();
	TitledBorderSide m_titledborderside4 = new TitledBorderSide();
	TitledBorderSide m_titledborderside5 = new TitledBorderSide();
	JLabel m_edad_men5_LB = new JLabel();
	JLabel m_edad_may5_LB = new JLabel();
	TitledBorderBottom m_titledborderbottom3 = new TitledBorderBottom();
	JCheckBox m_inf_resp_CHB = new JCheckBox();
	JCheckBox m_inf_piel_CHB = new JCheckBox();
	JCheckBox m_inf_inst_CHB = new JCheckBox();
	JCheckBox m_inf_vec_CHB = new JCheckBox();
	JTextField m_cod_csalud_TF = new JTextField();
	JTextField m_cod_com_TF = new JTextField();
	JLabel m_coordz_cs_LB = new JLabel();
	JTextField m_coordz_cs_TF_REAL = new JTextField();
	TitledBorderSide m_titledborderside6 = new TitledBorderSide();
	JTextField m_edad_men5_TF_INT = new JTextField();
	JTextField m_edad_may5_TF_INT = new JTextField();
	JLabel m_nom_csalud_LB = new JLabel();
	JTextField m_nom_csalud_TF = new JTextField();

	/**
	 * Default constructor
	 */
	 public CentroSaludForm()
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
		 FormLayout formlayout1 = new FormLayout("FILL:4DLU:NONE,FILL:8DLU:NONE,FILL:4DLU:NONE,FILL:21PX:NONE,FILL:4DLU:NONE,FILL:40PX:NONE,FILL:14PX:NONE,FILL:8DLU:NONE,FILL:20PX:NONE,FILL:4DLU:NONE,FILL:31DLU:NONE,FILL:DEFAULT:NONE,FILL:4DLU:NONE,FILL:9PX:NONE,FILL:10PX:NONE,FILL:4DLU:NONE,FILL:32PX:NONE,FILL:8PX:NONE,FILL:8DLU:NONE,FILL:4DLU:NONE,FILL:8DLU:NONE,FILL:50PX:NONE,FILL:8DLU:NONE,FILL:4DLU:NONE","CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE");
		 CellConstraints cc = new CellConstraints();
		 jpanel1.setLayout(formlayout1);

		 m_cod_csalud_LB.setName("cod_csalud.LB");
		 m_cod_csalud_LB.setText("Código del centro de salud:");
		 jpanel1.add(m_cod_csalud_LB,cc.xywh(3,6,10,1));

		 m_cod_com_LB.setName("cod_com.LB");
		 m_cod_com_LB.setText("Código de la comunidad:");
		 jpanel1.add(m_cod_com_LB,cc.xywh(3,8,10,1));

		 m_titledborderlabel1.setText("Infecciones tratadas");
		 jpanel1.add(m_titledborderlabel1,cc.xywh(3,12,16,1));

		 jpanel1.add(m_titledborderside1,cc.xywh(2,12,1,11));

		 jpanel1.add(m_titledborderbottom1,cc.xywh(3,22,16,1));

		 m_cedu_form_title.setFocusable(false);
		 m_cedu_form_title.setFont(new Font("Dialog",Font.BOLD,16));
		 m_cedu_form_title.setForeground(new Color(49,106,196));
		 m_cedu_form_title.setName("cedu_form_title");
		 m_cedu_form_title.setRequestFocusEnabled(false);
		 m_cedu_form_title.setText("Centro de salud");
		 m_cedu_form_title.setVerifyInputWhenFocusTarget(false);
		 jpanel1.add(m_cedu_form_title,cc.xywh(3,2,20,1));

		 m_coordx_cs_LB.setName("coordx_cs.LB");
		 m_coordx_cs_LB.setText("X:");
		 jpanel1.add(m_coordx_cs_LB,cc.xy(4,35));

		 m_titledborderlabel2.setText("Coordenadas");
		 jpanel1.add(m_titledborderlabel2,cc.xywh(3,34,18,1));

		 m_coordy_cs_LB.setName("coordy_cs.LB");
		 m_coordy_cs_LB.setText("Y:");
		 jpanel1.add(m_coordy_cs_LB,cc.xy(9,35));

		 m_coordy_cs_TF_REAL.setName("coordy_cs.TF.REAL");
		 jpanel1.add(m_coordy_cs_TF_REAL,cc.xy(11,35));

		 m_titledborderside2.setOrientation(TitledBorderSide.RIGHT);
		 jpanel1.add(m_titledborderside2,cc.xywh(21,34,1,4));

		 jpanel1.add(m_titledborderside3,cc.xywh(2,34,1,4));

		 jpanel1.add(m_titledborderbottom2,cc.xywh(3,37,18,1));

		 m_coordx_cs_TF_REAL.setName("coordx_cs.TF.REAL");
		 jpanel1.add(m_coordx_cs_TF_REAL,cc.xywh(6,35,2,1));

		 m_titledborderlabel3.setText("Afluencia de pacientes por edad");
		 jpanel1.add(m_titledborderlabel3,cc.xywh(3,24,16,1));

		 jpanel1.add(m_titledborderside4,cc.xywh(2,24,1,7));

		 m_titledborderside5.setOrientation(TitledBorderSide.RIGHT);
		 jpanel1.add(m_titledborderside5,cc.xywh(19,24,1,7));

		 m_edad_men5_LB.setName("edad_men5.LB");
		 m_edad_men5_LB.setText("0 - 5 años:");
		 jpanel1.add(m_edad_men5_LB,cc.xywh(4,26,4,1));

		 m_edad_may5_LB.setName("edad_may5.LB");
		 m_edad_may5_LB.setText("> 5 años:");
		 jpanel1.add(m_edad_may5_LB,cc.xywh(4,28,4,1));

		 jpanel1.add(m_titledborderbottom3,cc.xywh(3,30,16,1));

		 m_inf_resp_CHB.setActionCommand("Programa de merienda");
		 m_inf_resp_CHB.setName("inf_resp.CHB");
		 m_inf_resp_CHB.setText("Infecciones respiratorias agudas");
		 jpanel1.add(m_inf_resp_CHB,cc.xywh(4,14,14,1));

		 m_inf_piel_CHB.setActionCommand("Programa de merienda");
		 m_inf_piel_CHB.setName("inf_piel.CHB");
		 m_inf_piel_CHB.setText("Infecciones de la piel");
		 jpanel1.add(m_inf_piel_CHB,cc.xywh(4,16,14,1));

		 m_inf_inst_CHB.setActionCommand("Programa de merienda");
		 m_inf_inst_CHB.setName("inf_inst.CHB");
		 m_inf_inst_CHB.setText("Infecciones intestinales");
		 jpanel1.add(m_inf_inst_CHB,cc.xywh(4,18,14,1));

		 m_inf_vec_CHB.setActionCommand("Programa de merienda");
		 m_inf_vec_CHB.setName("inf_vec.CHB");
		 m_inf_vec_CHB.setText("Infecciones vectoriales");
		 jpanel1.add(m_inf_vec_CHB,cc.xywh(4,20,14,1));

		 m_cod_csalud_TF.setName("cod_csalud.TF");
		 jpanel1.add(m_cod_csalud_TF,cc.xywh(14,6,9,1));

		 m_cod_com_TF.setName("cod_com.TF");
		 jpanel1.add(m_cod_com_TF,cc.xywh(14,8,9,1));

		 m_coordz_cs_LB.setName("coordz_cs.LB");
		 m_coordz_cs_LB.setText("Z:");
		 jpanel1.add(m_coordz_cs_LB,cc.xywh(13,35,3,1));

		 m_coordz_cs_TF_REAL.setName("coordz_cs.TF.REAL");
		 jpanel1.add(m_coordz_cs_TF_REAL,cc.xywh(17,35,3,1));

		 m_titledborderside6.setOrientation(TitledBorderSide.RIGHT);
		 jpanel1.add(m_titledborderside6,cc.xywh(19,12,1,11));

		 m_edad_men5_TF_INT.setName("edad_men5.TF.INT");
		 jpanel1.add(m_edad_men5_TF_INT,cc.xywh(8,26,10,1));

		 m_edad_may5_TF_INT.setName("edad_may5.TF.INT");
		 jpanel1.add(m_edad_may5_TF_INT,cc.xywh(8,28,10,1));

		 m_nom_csalud_LB.setName("nom_csalud.LB");
		 m_nom_csalud_LB.setText("Nombre del centro de salud:");
		 jpanel1.add(m_nom_csalud_LB,cc.xywh(3,10,10,1));

		 m_nom_csalud_TF.setName("nom_csalud.TF");
		 jpanel1.add(m_nom_csalud_TF,cc.xywh(14,10,9,1));

		 addFillComponents(jpanel1,new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24 },new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38 });
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

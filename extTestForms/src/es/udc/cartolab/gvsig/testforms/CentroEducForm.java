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
import javax.swing.JTextField;

import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.WindowInfo;
import com.jeta.forms.components.border.TitledBorderBottom;
import com.jeta.forms.components.border.TitledBorderLabel;
import com.jeta.forms.components.border.TitledBorderSide;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;


public class CentroEducForm extends JPanel implements IWindow
{
	WindowInfo windowInfo = null;
	JTextField m_coordx_ce_TF_REAL = new JTextField();
	TitledBorderSide m_titledborderside1 = new TitledBorderSide();
	JLabel m_coordx_ce_LB = new JLabel();
	JLabel m_coordy_ce_LB = new JLabel();
	JTextField m_coordy_ce_TF_REAL = new JTextField();
	TitledBorderSide m_titledborderside2 = new TitledBorderSide();
	TitledBorderBottom m_titledborderbottom1 = new TitledBorderBottom();
	TitledBorderLabel m_titledborderlabel1 = new TitledBorderLabel();
	JTextField m_coordz_ce_TF_REAL = new JTextField();
	JLabel m_cod_cedu_LB = new JLabel();
	JLabel m_cod_com_LB = new JLabel();
	JLabel m_nom_cedu_LB = new JLabel();
	JLabel m_tipo_cedu_LB = new JLabel();
	JLabel m_n_ninhos_LB = new JLabel();
	JLabel m_n_ninhas_LB = new JLabel();
	JLabel m_i_deserc_LB = new JLabel();
	JLabel m_mer_escol_LB = new JLabel();
	JCheckBox m_mer_escol_CHB = new JCheckBox();
	JTextField m_i_deserc_TF_REAL = new JTextField();
	JTextField m_n_ninhas_TF_INT = new JTextField();
	JTextField m_n_ninhos_TF_INT = new JTextField();
	JComboBox m_tipo_cedu_CB = new JComboBox();
	JTextField m_nom_cedu_TF = new JTextField();
	JTextField m_cod_com_TF = new JTextField();
	JTextField m_cod_cedu_TF = new JTextField();
	JLabel m_coordz_ce_LB = new JLabel();

	/**
	 * Default constructor
	 */
	 public CentroEducForm()
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
		 FormLayout formlayout1 = new FormLayout("FILL:4DLU:NONE,FILL:8DLU:NONE,FILL:4DLU:NONE,FILL:23PX:NONE,FILL:4DLU:NONE,FILL:54PX:NONE,FILL:8DLU:NONE,FILL:24PX:NONE,FILL:4DLU:NONE,FILL:54PX:NONE,FILL:8DLU:NONE,FILL:4DLU:NONE,FILL:10DLU:NONE,FILL:4DLU:NONE,FILL:54PX:NONE,FILL:4DLU:NONE,FILL:8DLU:NONE,FILL:143PX:NONE,FILL:8DLU:NONE,FILL:4DLU:NONE","CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE");
		 CellConstraints cc = new CellConstraints();
		 jpanel1.setLayout(formlayout1);

		 m_coordx_ce_TF_REAL.setName("coordx_ce.TF.REAL");
		 jpanel1.add(m_coordx_ce_TF_REAL,cc.xy(6,26));

		 jpanel1.add(m_titledborderside1,cc.xywh(2,24,1,5));

		 m_coordx_ce_LB.setName("coordx_ce.LB");
		 m_coordx_ce_LB.setText("X:");
		 jpanel1.add(m_coordx_ce_LB,cc.xy(4,26));

		 m_coordy_ce_LB.setName("coordy_ce.LB");
		 m_coordy_ce_LB.setText("Y:");
		 jpanel1.add(m_coordy_ce_LB,cc.xy(8,26));

		 m_coordy_ce_TF_REAL.setName("coordy_ce.TF.REAL");
		 jpanel1.add(m_coordy_ce_TF_REAL,cc.xy(10,26));

		 m_titledborderside2.setOrientation(TitledBorderSide.RIGHT);
		 jpanel1.add(m_titledborderside2,cc.xywh(17,24,1,5));

		 jpanel1.add(m_titledborderbottom1,cc.xywh(3,28,14,1));

		 m_titledborderlabel1.setText("Coordenadas");
		 jpanel1.add(m_titledborderlabel1,cc.xywh(3,24,14,1));

		 m_coordz_ce_TF_REAL.setName("coordz_ce.TF.REAL");
		 jpanel1.add(m_coordz_ce_TF_REAL,cc.xy(15,26));

		 m_cod_cedu_LB.setName("cod_cedu.LB");
		 m_cod_cedu_LB.setText("Código del centro educativo:");
		 jpanel1.add(m_cod_cedu_LB,cc.xywh(3,6,9,1));

		 m_cod_com_LB.setName("cod_com.LB");
		 m_cod_com_LB.setText("Código de la comunidad:");
		 jpanel1.add(m_cod_com_LB,cc.xywh(3,8,9,1));

		 m_nom_cedu_LB.setName("nom_cedu.LB");
		 m_nom_cedu_LB.setText("Nombre del centro educativo:");
		 jpanel1.add(m_nom_cedu_LB,cc.xywh(3,10,9,1));

		 m_tipo_cedu_LB.setName("tipo_cedu.LB");
		 m_tipo_cedu_LB.setText("Tipo de centro educativo:");
		 jpanel1.add(m_tipo_cedu_LB,cc.xywh(3,12,9,1));

		 m_n_ninhos_LB.setName("n_ninhos.LB");
		 m_n_ninhos_LB.setText("Nº de niños:");
		 jpanel1.add(m_n_ninhos_LB,cc.xywh(3,14,9,1));

		 m_n_ninhas_LB.setName("n_ninhas.LB");
		 m_n_ninhas_LB.setText("Nº de niñas:");
		 jpanel1.add(m_n_ninhas_LB,cc.xywh(3,16,9,1));

		 m_i_deserc_LB.setName("i_deserc.LB");
		 m_i_deserc_LB.setText("Índice de deserción:");
		 jpanel1.add(m_i_deserc_LB,cc.xywh(3,18,9,1));

		 m_mer_escol_LB.setName("mer_escol.LB");
		 m_mer_escol_LB.setText("Programa de merienda:");
		 jpanel1.add(m_mer_escol_LB,cc.xywh(3,20,9,1));

		 JLabel jlabel1 = new JLabel();
		 jlabel1.setFocusable(false);
		 jlabel1.setFont(new Font("Dialog",Font.BOLD,16));
		 jlabel1.setForeground(new Color(49,106,196));
		 jlabel1.setRequestFocusEnabled(false);
		 jlabel1.setText("Centro educativo");
		 jlabel1.setVerifyInputWhenFocusTarget(false);
		 jpanel1.add(jlabel1,cc.xywh(3,2,16,1));

		 m_mer_escol_CHB.setActionCommand("Programa de merienda");
		 m_mer_escol_CHB.setName("mer_escol.CHB");
		 jpanel1.add(m_mer_escol_CHB,cc.xywh(13,20,6,1));

		 m_i_deserc_TF_REAL.setName("i_deserc.TF.REAL");
		 jpanel1.add(m_i_deserc_TF_REAL,cc.xywh(13,18,6,1));

		 m_n_ninhas_TF_INT.setName("n_ninhas.TF.INT");
		 jpanel1.add(m_n_ninhas_TF_INT,cc.xywh(13,16,6,1));

		 m_n_ninhos_TF_INT.setName("n_ninhos.TF.INT");
		 jpanel1.add(m_n_ninhos_TF_INT,cc.xywh(13,14,6,1));

		 m_tipo_cedu_CB.setName("tipo_cedu.CB");
		 m_tipo_cedu_CB.addItem("Seleccione una opción...");
		 m_tipo_cedu_CB.addItem("Jardín de infancia");
		 m_tipo_cedu_CB.addItem("Centro educativo escolar");
		 m_tipo_cedu_CB.addItem("Centro educativo común");
		 m_tipo_cedu_CB.addItem("Centro educativo diversificado");
		 jpanel1.add(m_tipo_cedu_CB,cc.xywh(13,12,6,1));

		 m_nom_cedu_TF.setName("nom_cedu.TF");
		 jpanel1.add(m_nom_cedu_TF,cc.xywh(13,10,6,1));

		 m_cod_com_TF.setName("cod_com.TF");
		 jpanel1.add(m_cod_com_TF,cc.xywh(13,8,6,1));

		 m_cod_cedu_TF.setName("cod_cedu.TF");
		 jpanel1.add(m_cod_cedu_TF,cc.xywh(13,6,6,1));

		 m_coordz_ce_LB.setName("coordz_ce.LB");
		 m_coordz_ce_LB.setText("Z:");
		 jpanel1.add(m_coordz_ce_LB,cc.xywh(12,26,2,1));

		 addFillComponents(jpanel1,new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20 },new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29 });
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

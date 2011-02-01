package es.udc.cartolab.gvsig.testforms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.ImageIcon;
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


public class CentroComForm extends JPanel implements IWindow
{
	WindowInfo windowInfo = null;
	JTextField m_coordx_cr_TF_REAL = new JTextField();
	TitledBorderSide m_titledborderside1 = new TitledBorderSide();
	JLabel m_coordx_cr_LB = new JLabel();
	JLabel m_coordy_cr_LB = new JLabel();
	JTextField m_coordy_cr_TF_REAL = new JTextField();
	JTextField m_cod_creu_TF = new JTextField();
	JTextField m_cod_com_TF = new JTextField();
	JTextField m_nom_creu_TF = new JTextField();
	JTextField m_direccion_TF = new JTextField();
	JTextField m_responsa_TF = new JTextField();
	TitledBorderSide m_titledborderside2 = new TitledBorderSide();
	TitledBorderBottom m_titledborderbottom1 = new TitledBorderBottom();
	TitledBorderLabel m_titledborderlabel1 = new TitledBorderLabel();
	JLabel m_cod_creu_LB = new JLabel();
	JLabel m_cod_com_LB = new JLabel();
	JLabel m_nom_creu_LB = new JLabel();
	JLabel m_direccion_LB = new JLabel();
	JLabel m_responsa_LB = new JLabel();
	JLabel m_coordz_cr_LB = new JLabel();
	JTextField m_coordz_cr_TF_REAL = new JTextField();

	/**
	 * Default constructor
	 */
	public CentroComForm()
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
		FormLayout formlayout1 = new FormLayout("FILL:4DLU:NONE,FILL:8DLU:NONE,FILL:4DLU:NONE,FILL:29PX:NONE,FILL:4DLU:NONE,FILL:54PX:NONE,FILL:8DLU:NONE,FILL:27PX:NONE,FILL:4DLU:NONE,FILL:54PX:NONE,FILL:8DLU:NONE,FILL:27PX:NONE,FILL:4DLU:NONE,FILL:4DLU:NONE,FILL:15PX:NONE,FILL:4DLU:NONE,FILL:25PX:NONE,FILL:4DLU:NONE,FILL:8DLU:NONE,FILL:143PX:NONE,FILL:DEFAULT:NONE,FILL:4DLU:NONE","CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:DEFAULT:NONE,CENTER:4DLU:NONE,CENTER:DEFAULT:NONE,CENTER:2DLU:NONE,CENTER:2DLU:NONE,CENTER:4DLU:NONE");
		CellConstraints cc = new CellConstraints();
		jpanel1.setLayout(formlayout1);

		m_coordx_cr_TF_REAL.setName("coordx_cr.TF.REAL");
		jpanel1.add(m_coordx_cr_TF_REAL,cc.xy(6,20));

		jpanel1.add(m_titledborderside1,cc.xywh(2,18,1,5));

		m_coordx_cr_LB.setName("coordx_cr.LB");
		m_coordx_cr_LB.setText("X:");
		jpanel1.add(m_coordx_cr_LB,cc.xy(4,20));

		m_coordy_cr_LB.setName("coordy_cr.LB");
		m_coordy_cr_LB.setText("Y:");
		jpanel1.add(m_coordy_cr_LB,cc.xy(8,20));

		m_coordy_cr_TF_REAL.setName("coordy_cr.TF.REAL");
		jpanel1.add(m_coordy_cr_TF_REAL,cc.xy(10,20));

		m_cod_creu_TF.setName("cod_creu.TF");
		jpanel1.add(m_cod_creu_TF,cc.xywh(14,6,7,1));

		m_cod_com_TF.setName("cod_com.TF");
		jpanel1.add(m_cod_com_TF,cc.xywh(14,8,7,1));

		m_nom_creu_TF.setName("nom_creu.TF");
		jpanel1.add(m_nom_creu_TF,cc.xywh(14,10,7,1));

		m_direccion_TF.setName("direccion.TF");
		jpanel1.add(m_direccion_TF,cc.xywh(14,12,7,1));

		m_responsa_TF.setName("responsa.TF");
		jpanel1.add(m_responsa_TF,cc.xywh(14,14,7,1));

		m_titledborderside2.setOrientation(TitledBorderSide.RIGHT);
		jpanel1.add(m_titledborderside2,cc.xywh(19,18,1,5));

		jpanel1.add(m_titledborderbottom1,cc.xywh(3,22,16,1));

		m_titledborderlabel1.setText("Coordenadas");
		jpanel1.add(m_titledborderlabel1,cc.xywh(3,18,16,1));

		m_cod_creu_LB.setName("cod_creu.LB");
		m_cod_creu_LB.setText("Código del centro comunal:");
		jpanel1.add(m_cod_creu_LB,cc.xywh(3,6,10,1));

		m_cod_com_LB.setName("cod_com.LB");
		m_cod_com_LB.setText("Código de la comunidad:");
		jpanel1.add(m_cod_com_LB,cc.xywh(3,8,10,1));

		m_nom_creu_LB.setName("nom_creu.LB");
		m_nom_creu_LB.setText("Nombre del centro comunal:");
		jpanel1.add(m_nom_creu_LB,cc.xywh(3,10,10,1));

		m_direccion_LB.setName("direccion.LB");
		m_direccion_LB.setText("Dirección:");
		jpanel1.add(m_direccion_LB,cc.xywh(3,12,10,1));

		m_responsa_LB.setName("responsa.LB");
		m_responsa_LB.setText("Responsable:");
		jpanel1.add(m_responsa_LB,cc.xywh(3,14,10,1));

		JLabel jlabel1 = new JLabel();
		jlabel1.setFocusable(false);
		jlabel1.setFont(new Font("Dialog",Font.BOLD,16));
		jlabel1.setForeground(new Color(49,106,196));
		jlabel1.setRequestFocusEnabled(false);
		jlabel1.setText("Centro comunal de reuniones");
		jlabel1.setVerifyInputWhenFocusTarget(false);
		jpanel1.add(jlabel1,cc.xywh(3,2,18,1));

		m_coordz_cr_LB.setName("coordz_cr.LB");
		m_coordz_cr_LB.setText("Z:");
		jpanel1.add(m_coordz_cr_LB,cc.xy(12,20));

		m_coordz_cr_TF_REAL.setName("coordz_cr.TF.REAL");
		jpanel1.add(m_coordz_cr_TF_REAL,cc.xywh(14,20,4,1));

		addFillComponents(jpanel1,new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22 },new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23 });
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

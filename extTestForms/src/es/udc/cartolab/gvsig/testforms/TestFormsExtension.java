package es.udc.cartolab.gvsig.testforms;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.About;
import com.iver.cit.gvsig.gui.panels.FPanelAbout;

/**
 * Tests the forms created with abeille
 *
 */

public final class TestFormsExtension extends Extension {

	/* (non-Javadoc)
	 * @see com.iver.andami.plugins.IExtension#execute(java.lang.String)
	 */
	public void execute(String actionCommand) {

		IWindow dialog = null;
		if (actionCommand.equals("CentroSalud")) {
			dialog = new CentroSaludForm();
		} else if (actionCommand.equals("CentroEduc")) {
			dialog = new CentroEducForm();
		} else if (actionCommand.equals("CentroCom")) {
			dialog = new CentroComForm();
		} else if (actionCommand.equals("Comunidad")) {
			dialog = new ComunidadForm();
		} else if (actionCommand.equals("Parcela")) {
			dialog = new ParcelaForm();
		} else if (actionCommand.equals("Vivienda")) {
			dialog = new ViviendaForm();
		}

		PluginServices.getMDIManager().addWindow(dialog);

	}

	/* (non-Javadoc)
	 * @see com.iver.andami.plugins.IExtension#initialize()
	 */
	public void initialize() {
		About about=(About)PluginServices.getExtension(About.class);
		FPanelAbout panelAbout=about.getAboutPanel();
		java.net.URL aboutURL = this.getClass().getResource("/about.htm");
		panelAbout.addAboutUrl("Test Forms", aboutURL);
	}

	public boolean isEnabled() {
		return true;
	}

	public boolean isVisible() {
		return true;
	}

}

package es.udc.cartolab.gvsig.pmf.importer;

import javax.swing.JMenuItem;

public interface ImporterContextualMenu {

    boolean isVisible();

    JMenuItem getMenuItem();

    boolean isEnabled();

}

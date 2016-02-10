package es.udc.cartolab.gvsig.pmf;

import es.icarto.gvsig.commons.AbstractExtension;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class ImporterExtension extends AbstractExtension {

    @Override
    public void execute(String actionCommand) {
	System.out.println("Hello world!");
    }

    @Override
    public boolean isEnabled() {
	return DBSession.isActive();
    }

}

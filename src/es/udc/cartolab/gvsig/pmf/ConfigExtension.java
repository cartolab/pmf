package es.udc.cartolab.gvsig.pmf;

import com.iver.andami.plugins.Extension;

import es.udc.cartolab.gvsig.pmf.utils.PmfConstants;
import es.udc.cartolab.gvsig.tools.CopyFeaturesExtension;

public class ConfigExtension extends Extension {

    @Override
    public void initialize() {
    }

    @Override
    public void postInitialize() {
	// We configure the CopyFeatures dialog to open in a predefined folder
	CopyFeaturesExtension.setDefaultPath(PmfConstants.GPS_MATCHING_FILES);
    }

    @Override
    public void execute(String actionCommand) {
    }

    @Override
    public boolean isEnabled() {
	return false;
    }

    @Override
    public boolean isVisible() {
	return false;
    }

}

package es.udc.cartolab.gvsig.pmf.forms.validation.binding;

import com.jgoodies.binding.beans.Model;
import com.jgoodies.validation.Validator;

import es.udc.cartolab.gvsig.navtableforms.validation.FormBinding;
import es.udc.cartolab.gvsig.pmf.forms.validation.model.CentroEducativoModel;
import es.udc.cartolab.gvsig.pmf.forms.validation.validator.CentroEducativoValidator;

/**
 * Provides all models to bind the view to its domain model, an instance of
 * {@linkCentroEducativoModel}.
 * 
 */
public class CentroEducativoBinding extends FormBinding<CentroEducativoModel> {

    // Instance Creation ******************************************************

    public CentroEducativoBinding(Model model) {
	super(model);
    }

    @Override
    protected Validator getValidator() {
	return new CentroEducativoValidator();
    }

}

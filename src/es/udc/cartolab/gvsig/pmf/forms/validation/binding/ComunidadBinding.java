package es.udc.cartolab.gvsig.pmf.forms.validation.binding;

import com.jgoodies.binding.beans.Model;
import com.jgoodies.validation.Validator;

import es.udc.cartolab.gvsig.navtableforms.validation.FormBinding;
import es.udc.cartolab.gvsig.pmf.forms.validation.model.ComunidadModel;
import es.udc.cartolab.gvsig.pmf.forms.validation.validator.ComunidadValidator;

/**
 * Provides all models to bind the view to its domain model, an instance of
 * {@linkComunidadModel}.
 * 
 */
public class ComunidadBinding extends FormBinding<ComunidadModel> {

    // Instance Creation ******************************************************

    public ComunidadBinding(Model model) {
	super(model);
    }

    @Override
    protected Validator getValidator() {
	return new ComunidadValidator();
    }

}

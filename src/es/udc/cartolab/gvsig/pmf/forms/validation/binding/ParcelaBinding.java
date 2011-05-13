package es.udc.cartolab.gvsig.pmf.forms.validation.binding;

import com.jgoodies.binding.beans.Model;
import com.jgoodies.validation.Validator;

import es.udc.cartolab.gvsig.navtableforms.validation.FormBinding;
import es.udc.cartolab.gvsig.pmf.forms.validation.model.ParcelaModel;
import es.udc.cartolab.gvsig.pmf.forms.validation.validator.ParcelaValidator;

/**
 * Provides all models to bind the view to its domain model, an instance of
 * {@linkParcelaModel}.
 * 
 */
public class ParcelaBinding extends FormBinding<ParcelaModel> {

    // Instance Creation ******************************************************

    public ParcelaBinding(Model model) {
	super(model);
    }

    @Override
    protected Validator getValidator() {
	return new ParcelaValidator();
    }

}

package es.udc.cartolab.gvsig.navtableformsexample.validation;

import com.jgoodies.binding.beans.Model;
import com.jgoodies.validation.Validator;

import es.udc.cartolab.gvsig.navtableforms.validation.FormBinding;

/**
 * Provides all models to bind the view to its domain model,
 * an instance of {@linkComunidadModel}.
 *
 */
public class  ComunidadBinding extends FormBinding<ComunidadModel> {

	// Instance Creation ******************************************************

	public ComunidadBinding(Model model) {
		super(model);
	}

	@Override
	protected Validator getValidator() {
		return new ComunidadValidator();
	}

}

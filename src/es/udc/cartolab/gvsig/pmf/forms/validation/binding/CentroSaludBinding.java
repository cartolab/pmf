package es.udc.cartolab.gvsig.pmf.forms.validation.binding;

import com.jgoodies.binding.beans.Model;
import com.jgoodies.validation.Validator;

import es.udc.cartolab.gvsig.navtableforms.validation.FormBinding;
import es.udc.cartolab.gvsig.pmf.forms.validation.model.CentroSaludModel;
import es.udc.cartolab.gvsig.pmf.validation.validator.CentroSaludValidator;

/**
 * Provides all models to bind the view to its domain model,
 * an instance of {@linkCentroSaludModel}.
 *
 */
public class  CentroSaludBinding extends FormBinding<CentroSaludModel> {

	// Instance Creation ******************************************************

	public CentroSaludBinding(Model model) {
		super(model);
	}

	@Override
	protected Validator getValidator() {
		return new CentroSaludValidator();
	}

}

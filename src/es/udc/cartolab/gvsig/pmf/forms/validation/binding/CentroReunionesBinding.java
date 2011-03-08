package es.udc.cartolab.gvsig.pmf.forms.validation.binding;

import com.jgoodies.binding.beans.Model;
import com.jgoodies.validation.Validator;

import es.udc.cartolab.gvsig.navtableforms.validation.FormBinding;
import es.udc.cartolab.gvsig.pmf.forms.validation.model.CentroReunionesModel;
import es.udc.cartolab.gvsig.pmf.forms.validation.validator.CentroReunionesValidator;

/**
 * Provides all models to bind the view to its domain model,
 * an instance of {@linkCentroComunalModel}.
 *
 */
public class  CentroReunionesBinding extends FormBinding<CentroReunionesModel> {

	// Instance Creation ******************************************************

	public CentroReunionesBinding(Model model) {
		super(model);
	}

	@Override
	protected Validator getValidator() {
		return new CentroReunionesValidator();
	}

}

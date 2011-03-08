package es.udc.cartolab.gvsig.pmf.forms.validation;

import com.jgoodies.binding.beans.Model;
import com.jgoodies.validation.Validator;

import es.udc.cartolab.gvsig.navtableforms.validation.FormBinding;
import es.udc.cartolab.gvsig.navtableformsexample.validation.CentroComunalModel;
import es.udc.cartolab.gvsig.navtableformsexample.validation.CentroComunalValidator;

/**
 * Provides all models to bind the view to its domain model,
 * an instance of {@linkCentroComunalModel}.
 *
 */
public class  CentroComunalBinding extends FormBinding<CentroComunalModel> {

	// Instance Creation ******************************************************

	public CentroComunalBinding(Model model) {
		super(model);
	}

	@Override
	protected Validator getValidator() {
		return new CentroComunalValidator();
	}

}

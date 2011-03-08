package es.udc.cartolab.gvsig.pmf.forms.validation;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

import es.udc.cartolab.gvsig.navtableformsexample.validation.ComunidadModel;

public final class ComunidadValidator implements Validator<ComunidadModel> {


	// Validation *************************************************************

	/**
	 * Validates this Validators Order and returns the result
	 * as an instance of {@link ValidationResult}.
	 *
	 * @param order   the Order to be validated
	 * @return the ValidationResult of the order validation
	 */
	public ValidationResult validate(ComunidadModel comunidadModel) {
		PropertyValidationSupport support =
			new PropertyValidationSupport(comunidadModel, "ComunidadModel");

		// Check numerics and no black fields ************************************
		//n_habit
		if (!ValidationUtils.isNumeric(comunidadModel.getN_habit())) {
			support.addError("n_habit", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(comunidadModel.getN_habit())) {
			support.addError("n_habit", "es obligatorio");
		}

		//n_fam
		if (!ValidationUtils.isNumeric(comunidadModel.getN_fam())) {
			support.addError("n_fam", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(comunidadModel.getN_fam())) {
			support.addError("n_fam", "es obligatorio");
		}


		// Check reals and no blank fields ***************************************

		return support.getResult();
	}

}

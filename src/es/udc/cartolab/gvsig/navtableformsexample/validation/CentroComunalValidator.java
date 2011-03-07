package es.udc.cartolab.gvsig.navtableformsexample.validation;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

import es.udc.cartolab.gvsig.navtableforms.validation.ValidationCheckingUtils;

public final class CentroComunalValidator implements Validator<CentroComunalModel> {


	// Validation *************************************************************

	/**
	 * Validates this Validators Order and returns the result
	 * as an instance of {@link ValidationResult}.
	 *
	 * @param order   the Order to be validated
	 * @return the ValidationResult of the order validation
	 */
	public ValidationResult validate(CentroComunalModel centroComunalModel) {
		PropertyValidationSupport support =
			new PropertyValidationSupport(centroComunalModel, "CentroComunalModel");

		// Check numerics and no black fields ************************************

		// Check reals and no blank fields ***************************************
		//x
		if (!ValidationCheckingUtils.isReal(centroComunalModel.getX())) {
			support.addError("x", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(centroComunalModel.getX())) {
			support.addError("x", "es obligatorio");
		}

		//y
		if (!ValidationCheckingUtils.isReal(centroComunalModel.getY())) {
			support.addError("y", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(centroComunalModel.getY())) {
			support.addError("y", "es obligatorio");
		}

		//z
		if (!ValidationCheckingUtils.isReal(centroComunalModel.getZ())) {
			support.addError("z", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(centroComunalModel.getZ())) {
			support.addError("z", "es obligatorio");
		}


		return support.getResult();
	}

}

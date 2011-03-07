package es.udc.cartolab.gvsig.navtableformsexample.validation;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

import es.udc.cartolab.gvsig.navtableforms.validation.ValidationCheckingUtils;

public final class CentroEducativoValidator implements Validator<CentroEducativoModel> {


	// Validation *************************************************************

	/**
	 * Validates this Validators Order and returns the result
	 * as an instance of {@link ValidationResult}.
	 *
	 * @param order   the Order to be validated
	 * @return the ValidationResult of the order validation
	 */
	public ValidationResult validate(CentroEducativoModel centroEducativoModel) {
		PropertyValidationSupport support =
			new PropertyValidationSupport(centroEducativoModel, "CentroEducativoModel");

		// Check numerics and no black fields ************************************
		//n_ninhas
		if (!ValidationUtils.isNumeric(centroEducativoModel.getN_ninhas())) {
			support.addError("n_ninhas", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(centroEducativoModel.getN_ninhas())) {
			support.addError("n_ninhas", "es obligatorio");
		}

		//n_ninhos
		if (!ValidationUtils.isNumeric(centroEducativoModel.getN_ninhos())) {
			support.addError("n_ninhos", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(centroEducativoModel.getN_ninhos())) {
			support.addError("n_ninhos", "es obligatorio");
		}


		// Check reals and no blank fields ***************************************
		//x
		if (!ValidationCheckingUtils.isReal(centroEducativoModel.getX())) {
			support.addError("x", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(centroEducativoModel.getX())) {
			support.addError("x", "es obligatorio");
		}

		//y
		if (!ValidationCheckingUtils.isReal(centroEducativoModel.getY())) {
			support.addError("y", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(centroEducativoModel.getY())) {
			support.addError("y", "es obligatorio");
		}

		//z
		if (!ValidationCheckingUtils.isReal(centroEducativoModel.getZ())) {
			support.addError("z", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(centroEducativoModel.getZ())) {
			support.addError("z", "es obligatorio");
		}

		//i_deserc
		if (!ValidationCheckingUtils.isReal(centroEducativoModel.getI_deserc())) {
			support.addError("i_deserc", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(centroEducativoModel.getI_deserc())) {
			support.addError("i_deserc", "es obligatorio");
		}


		return support.getResult();
	}

}

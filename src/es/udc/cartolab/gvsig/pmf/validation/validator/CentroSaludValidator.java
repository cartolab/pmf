package es.udc.cartolab.gvsig.pmf.validation.validator;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;
import es.udc.cartolab.gvsig.arqueoponte.validation.ValidationCheckingUtils
import es.udc.cartolab.gvsig.pmf.forms.validation.model.CentroSaludModel;

public final class CentroSaludValidator implements Validator<CentroSaludModel> {


	// Validation *************************************************************

	/**
	 * Validates this Validators Order and returns the result
	 * as an instance of {@link ValidationResult}.
	 *
	 * @param order   the Order to be validated
	 * @return the ValidationResult of the order validation
	 */
	public ValidationResult validate(CentroSaludModel centroSaludModel) {
		PropertyValidationSupport support =
			new PropertyValidationSupport(centroSaludModel, "CentroSaludModel");

		// Check numerics and no black fields ************************************

		// Check reals and no blank fields ***************************************

		return support.getResult();
	}

}

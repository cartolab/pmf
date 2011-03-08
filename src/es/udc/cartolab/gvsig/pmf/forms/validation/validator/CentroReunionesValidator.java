package es.udc.cartolab.gvsig.pmf.forms.validation.validator;

import com.iver.andami.PluginServices;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

import es.udc.cartolab.gvsig.navtableforms.validation.ValidationCheckingUtils;
import es.udc.cartolab.gvsig.pmf.forms.validation.model.CentroReunionesModel;

public final class CentroReunionesValidator implements Validator<CentroReunionesModel> {


	// Validation *************************************************************

	/**
	 * Validates this Validators Order and returns the result
	 * as an instance of {@link ValidationResult}.
	 *
	 * @param order   the Order to be validated
	 * @return the ValidationResult of the order validation
	 */
	public ValidationResult validate(CentroReunionesModel centroReunionesModel) {
		PropertyValidationSupport support =
			new PropertyValidationSupport(centroReunionesModel, "CentroReunionesModel");

		if (ValidationUtils.isBlank(centroReunionesModel.getCod_creu())) {
			support.addError("cod_creu",PluginServices.getText(this, "needed_is_pk"));
		}
		// Check numerics and no black fields ************************************

		// Check reals and no blank fields ***************************************
		//x
		if (!ValidationCheckingUtils.isReal(centroReunionesModel.getX())) {
			support.addError("x", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(centroReunionesModel.getX())) {
			support.addError("x", "es obligatorio");
		}

		//y
		if (!ValidationCheckingUtils.isReal(centroReunionesModel.getY())) {
			support.addError("y", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(centroReunionesModel.getY())) {
			support.addError("y", "es obligatorio");
		}

		//z
		if (!ValidationCheckingUtils.isReal(centroReunionesModel.getZ())) {
			support.addError("z", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(centroReunionesModel.getZ())) {
			support.addError("z", "es obligatorio");
		}


		return support.getResult();
	}

}

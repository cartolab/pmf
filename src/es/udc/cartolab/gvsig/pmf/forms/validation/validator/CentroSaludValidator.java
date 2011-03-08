package es.udc.cartolab.gvsig.pmf.forms.validation.validator;

import com.iver.andami.PluginServices;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

import es.udc.cartolab.gvsig.navtableforms.validation.ValidationCheckingUtils;
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

		// numUniEst (Check PrimaryKey + numeric)
		if (ValidationUtils.isBlank(centroSaludModel.getCod_csalud())) {
			support.addError("cod_csalud",PluginServices.getText(this, "needed_is_pk"));
		}
		// Check numerics and no black fields ************************************
		//edad_men5
		if (!ValidationUtils.isNumeric(centroSaludModel.getEdad_men5())) {
			support.addError("edad_men5", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(centroSaludModel.getEdad_men5())) {
			support.addError("edad_men5", "es obligatorio");
		}

		//edad_may5
		if (!ValidationUtils.isNumeric(centroSaludModel.getEdad_may5())) {
			support.addError("edad_may5", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(centroSaludModel.getEdad_may5())) {
			support.addError("edad_may5", "es obligatorio");
		}


		// Check reals and no blank fields ***************************************
		//y
		if (!ValidationCheckingUtils.isReal(centroSaludModel.getY())) {
			support.addError("y", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(centroSaludModel.getY())) {
			support.addError("y", "es obligatorio");
		}

		//x
		if (!ValidationCheckingUtils.isReal(centroSaludModel.getX())) {
			support.addError("x", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(centroSaludModel.getX())) {
			support.addError("x", "es obligatorio");
		}

		//z
		if (!ValidationCheckingUtils.isReal(centroSaludModel.getZ())) {
			support.addError("z", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(centroSaludModel.getZ())) {
			support.addError("z", "es obligatorio");
		}


		return support.getResult();
	}

}

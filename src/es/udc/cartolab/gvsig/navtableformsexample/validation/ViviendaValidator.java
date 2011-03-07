package es.udc.cartolab.gvsig.navtableformsexample.validation;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

import es.udc.cartolab.gvsig.navtableforms.validation.ValidationCheckingUtils;

public final class ViviendaValidator implements Validator<ViviendaModel> {


	// Validation *************************************************************

	/**
	 * Validates this Validators Order and returns the result
	 * as an instance of {@link ValidationResult}.
	 *
	 * @param order   the Order to be validated
	 * @return the ValidationResult of the order validation
	 */
	public ValidationResult validate(ViviendaModel viviendaModel) {
		PropertyValidationSupport support =
			new PropertyValidationSupport(viviendaModel, "ViviendaModel");

		// Check numerics and no black fields ************************************
		//n_ninhos
		if (!ValidationUtils.isNumeric(viviendaModel.getN_ninhos())) {
			support.addError("n_ninhos", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(viviendaModel.getN_ninhos())) {
			support.addError("n_ninhos", "es obligatorio");
		}

		//n_ninhas
		if (!ValidationUtils.isNumeric(viviendaModel.getN_ninhas())) {
			support.addError("n_ninhas", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(viviendaModel.getN_ninhas())) {
			support.addError("n_ninhas", "es obligatorio");
		}

		//n_mujer
		if (!ValidationUtils.isNumeric(viviendaModel.getN_mujer())) {
			support.addError("n_mujer", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(viviendaModel.getN_mujer())) {
			support.addError("n_mujer", "es obligatorio");
		}

		//n_hombr
		if (!ValidationUtils.isNumeric(viviendaModel.getN_hombr())) {
			support.addError("n_hombr", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(viviendaModel.getN_hombr())) {
			support.addError("n_hombr", "es obligatorio");
		}

		//edad_produ
		if (!ValidationUtils.isNumeric(viviendaModel.getEdad_produ())) {
			support.addError("edad_produ", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(viviendaModel.getEdad_produ())) {
			support.addError("edad_produ", "es obligatorio");
		}

		//n_personas
		if (!ValidationUtils.isNumeric(viviendaModel.getN_personas())) {
			support.addError("n_personas", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(viviendaModel.getN_personas())) {
			support.addError("n_personas", "es obligatorio");
		}

		//nid_produ
		if (!ValidationUtils.isNumeric(viviendaModel.getNid_produ())) {
			support.addError("nid_produ", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(viviendaModel.getNid_produ())) {
			support.addError("nid_produ", "es obligatorio");
		}

		//n_embaraz
		if (!ValidationUtils.isNumeric(viviendaModel.getN_embaraz())) {
			support.addError("n_embaraz", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(viviendaModel.getN_embaraz())) {
			support.addError("n_embaraz", "es obligatorio");
		}

		//n_migrante
		if (!ValidationUtils.isNumeric(viviendaModel.getN_migrante())) {
			support.addError("n_migrante", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(viviendaModel.getN_migrante())) {
			support.addError("n_migrante", "es obligatorio");
		}


		// Check reals and no blank fields ***************************************
		//x
		if (!ValidationCheckingUtils.isReal(viviendaModel.getX())) {
			support.addError("x", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(viviendaModel.getX())) {
			support.addError("x", "es obligatorio");
		}

		//y
		if (!ValidationCheckingUtils.isReal(viviendaModel.getY())) {
			support.addError("y", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(viviendaModel.getY())) {
			support.addError("y", "es obligatorio");
		}

		//z
		if (!ValidationCheckingUtils.isReal(viviendaModel.getZ())) {
			support.addError("z", "debe ser un numero");
		}
		else if (ValidationUtils.isBlank(viviendaModel.getZ())) {
			support.addError("z", "es obligatorio");
		}


		return support.getResult();
	}

}

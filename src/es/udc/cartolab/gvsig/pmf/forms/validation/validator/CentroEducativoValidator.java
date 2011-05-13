package es.udc.cartolab.gvsig.pmf.forms.validation.validator;

import com.iver.andami.PluginServices;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

import es.udc.cartolab.gvsig.navtableforms.validation.ValidationCheckingUtils;
import es.udc.cartolab.gvsig.pmf.forms.validation.model.CentroEducativoModel;

public final class CentroEducativoValidator implements
	Validator<CentroEducativoModel> {

    // Validation *************************************************************

    /**
     * Validates this Validators Order and returns the result as an instance of
     * {@link ValidationResult}.
     * 
     * @param order
     *            the Order to be validated
     * @return the ValidationResult of the order validation
     */
    public ValidationResult validate(CentroEducativoModel centroEducativoModel) {
	PropertyValidationSupport support = new PropertyValidationSupport(
		centroEducativoModel, "CentroEducativoModel");

	if (ValidationUtils.isBlank(centroEducativoModel.getCod_cedu())) {
	    support.addError("cod_cedu", PluginServices.getText(this,
		    "needed_is_pk"));
	}
	// Check numerics and no black fields
	// ************************************
	// n_ninhas
	if (!ValidationUtils.isNumeric(centroEducativoModel.getN_ninhas())) {
	    support.addError("n_ninhas", "debe ser un numero entero");
	} else if (ValidationUtils.isBlank(centroEducativoModel.getN_ninhas())) {
	    support.addError("n_ninhas", "es obligatorio");
	}

	// n_ninhos
	if (!ValidationUtils.isNumeric(centroEducativoModel.getN_ninhos())) {
	    support.addError("n_ninhos", "debe ser un numero entero");
	} else if (ValidationUtils.isBlank(centroEducativoModel.getN_ninhos())) {
	    support.addError("n_ninhos", "es obligatorio");
	}

	// Check reals and no blank fields
	// ***************************************
	// x
	if (!ValidationCheckingUtils.isReal(centroEducativoModel.getX())) {
	    support.addError("x", "debe ser un numero");
	} else if (ValidationUtils.isBlank(centroEducativoModel.getX())) {
	    support.addError("x", "es obligatorio");
	}

	// y
	if (!ValidationCheckingUtils.isReal(centroEducativoModel.getY())) {
	    support.addError("y", "debe ser un numero");
	} else if (ValidationUtils.isBlank(centroEducativoModel.getY())) {
	    support.addError("y", "es obligatorio");
	}

	// z
	if (!ValidationCheckingUtils.isReal(centroEducativoModel.getZ())) {
	    support.addError("z", "debe ser un numero");
	} else if (ValidationUtils.isBlank(centroEducativoModel.getZ())) {
	    support.addError("z", "es obligatorio");
	}

	// i_deserc
	if (!ValidationCheckingUtils.isReal(centroEducativoModel.getI_deserc())) {
	    support.addError("i_deserc", "debe ser un numero");
	} else if (ValidationUtils.isBlank(centroEducativoModel.getI_deserc())) {
	    support.addError("i_deserc", "es obligatorio");
	} else {
	    double n = Double.parseDouble(centroEducativoModel.getI_deserc());
	    if ((n < 0) || (n > 100)) {
		support.addError("i_deserc", "debe estar entre 0 y 100");
	    }
	}

	return support.getResult();
    }

}

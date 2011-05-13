package es.udc.cartolab.gvsig.pmf.forms.validation.validator;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

import es.udc.cartolab.gvsig.navtableforms.validation.ValidationCheckingUtils;
import es.udc.cartolab.gvsig.pmf.forms.validation.model.ParcelaModel;

public final class ParcelaValidator implements Validator<ParcelaModel> {

    // Validation *************************************************************

    /**
     * Validates this Validators Order and returns the result as an instance of
     * {@link ValidationResult}.
     * 
     * @param order
     *            the Order to be validated
     * @return the ValidationResult of the order validation
     */
    public ValidationResult validate(ParcelaModel parcelaModel) {
	PropertyValidationSupport support = new PropertyValidationSupport(
		parcelaModel, "ParcelaModel");

	// Check numerics and no black fields
	// ************************************
	// fam_cant
	if (!ValidationUtils.isNumeric(parcelaModel.getFam_cant())) {
	    support.addError("fam_cant", "debe ser un numero");
	} else if (ValidationUtils.isBlank(parcelaModel.getFam_cant())) {
	    support.addError("fam_cant", "es obligatorio");
	}

	// con_cant
	if (!ValidationUtils.isNumeric(parcelaModel.getCon_cant())) {
	    support.addError("con_cant", "debe ser un numero");
	} else if (ValidationUtils.isBlank(parcelaModel.getCon_cant())) {
	    support.addError("con_cant", "es obligatorio");
	}

	// Check reals and no blank fields
	// ***************************************

	// d_fue_tano
	if (!ValidationUtils.isNumeric(parcelaModel.getD_fue_tan())) {
	    support.addError("d_fue_tan", "debe ser un numero");
	} else if (ValidationUtils.isBlank(parcelaModel.getD_fue_tan())) {
	    support.addError("d_fue_tan", "es obligatorio");
	}

	// d_tan_hueo
	if (!ValidationUtils.isNumeric(parcelaModel.getD_tan_hue())) {
	    support.addError("d_tan_hue", "debe ser un numero");
	} else if (ValidationUtils.isBlank(parcelaModel.getD_tan_hue())) {
	    support.addError("d_tan_hue", "es obligatorio");
	}

	// area_tot
	String areaTot = parcelaModel.getArea_tot();
	String areaCul = parcelaModel.getArea_cul();

	if (!ValidationCheckingUtils.isReal(areaTot)) {
	    support.addError("area_tot", "debe ser un numero");
	} else if (ValidationUtils.isBlank(areaTot)) {
	    support.addError("area_tot", "es obligatorio");
	}

	// area_cul
	if (!ValidationCheckingUtils.isReal(areaCul)) {
	    support.addError("area_cul", "debe ser un numero");
	} else if (ValidationCheckingUtils.isLowerThan(areaCul, "0")
		|| ValidationCheckingUtils.isBiggerThan(areaCul, areaTot)) {
	    support.addError("area_cul", "debe estar entre 0 y el Área total.");
	}

	return support.getResult();
    }

}

package es.udc.cartolab.gvsig.navtableformsexample.validation;

import com.jgoodies.binding.beans.Model;
import com.jgoodies.validation.Validator;

import es.udc.cartolab.gvsig.navtableforms.validation.FormBinding;

/**
 * Provides all models to bind the view to its domain model,
 * an instance of {@linkViviendaModel}.
 *
 */
public class  ViviendaBinding extends FormBinding<ViviendaModel> {

       // Instance Creation ******************************************************

       public ViviendaBinding(Model model) {
              super(model);
              }

              @Override
              protected Validator getValidator() {
                        return new ViviendaValidator();
                        }

}

package es.udc.cartolab.gvsig.pmf.forms;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class CentrosSaludForm extends BasicAbstractForm {

    public static final String NAME = "centros_salud";
    public static final String PKFIELD = "cod_csalud";

    public CentrosSaludForm(FLyrVect layer) {
	super(layer);
    }

    @Override
    protected String getPrimaryKeyValue() {
	return getFormController().getValue(PKFIELD);
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }
}

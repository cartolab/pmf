package es.udc.cartolab.gvsig.pmf.forms;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class CentrosReunionesForm extends BasicAbstractForm {

    public static final String NAME = "centros_reuniones";
    public static final String PKFIELD = "cod_creu";

    public CentrosReunionesForm(FLyrVect layer) {
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

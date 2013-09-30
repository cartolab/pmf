package es.udc.cartolab.gvsig.pmf.forms;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class FuentesComunitariasForm extends BasicAbstractForm {

    public static final String NAME = "fuentes_comunitarias";
    public static final String PKFIELD = "codigo_fc";
    public static String[] colNames = { "codigo_fc" };
    public static String[] colAlias = { "Código" };

    public FuentesComunitariasForm(FLyrVect layer) {
	super(layer);
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }
}

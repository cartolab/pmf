package es.udc.cartolab.gvsig.pmf.forms;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class LimitesParcelaForm extends BasicAbstractForm {

    public static final String NAME = "limites_parcela";
    public static String[] colNames = { "" };
    public static String[] colAlias = { "" };

    public LimitesParcelaForm(FLyrVect layer) {
	super(layer);
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }
}

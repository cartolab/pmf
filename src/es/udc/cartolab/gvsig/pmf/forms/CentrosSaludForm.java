package es.udc.cartolab.gvsig.pmf.forms;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class CentrosSaludForm extends BasicAbstractForm {

    public static final String NAME = "centros_salud";
    public static final String PKFIELD = "cod_csalud";
    public static String[] colNames = { "cod_csalud", "nom_csalud" };
    public static String[] colAlias = { "Código", "Nombre" };

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

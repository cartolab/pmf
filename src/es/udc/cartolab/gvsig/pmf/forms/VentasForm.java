package es.udc.cartolab.gvsig.pmf.forms;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class VentasForm extends BasicAbstractForm {

    public static final String NAME = "ventas";
    public static String[] colNames = {
"nombre", "precio", "volumen", "total"
    };
    public static String[] colAlias = {
"Comprador", "Precio", "Volumen", "Total"
    };
    public VentasForm(FLyrVect layer) {
	super(layer);
    }
    

    @Override
    protected String getBasicName() {
	return NAME;
    }
}

package es.udc.cartolab.gvsig.pmf.forms;

import es.icarto.gvsig.navtableforms.CommonMethodsForTestDBForms;
import es.udc.cartolab.gvsig.pmf.utils.PmfConstants;

public class TestParcelasForm extends CommonMethodsForTestDBForms {

    @Override
    protected String getSchema() {
	return PmfConstants.DATA_SCHEMA;
    }

    @Override
    protected String getTableName() {
	return ParcelasForm.NAME;
    }

}

package es.udc.cartolab.gvsig.pmf.reports;

import java.util.Comparator;
import java.util.HashMap;

public class RecordsComparator implements Comparator<HashMap<String, Object>> {

    private String field;
    private boolean ascendant;

    public RecordsComparator(String field, boolean ascendant) {
	this.field = field;
	this.ascendant = ascendant;
    }

    public int compare(HashMap<String, Object> row1,
	    HashMap<String, Object> row2) {

	String val1 = (String) row1.get(field);
	String val2 = (String) row2.get(field);

	if (ascendant) {
	    return val1.compareTo(val2);
	} else {
	    return val2.compareTo(val1);
	}

    }

}

package es.icarto.gvsig.importer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetUp {

    public static List<HeaderField> setup() {
	List<HeaderField> fields;
	HeaderField hName = new HeaderField("id");
	hName.setNotDefinedField(false);
	Set<String> s1 = new HashSet<String>();
	s1.add("name");
	s1.add("id");
	hName.setRuleNames(s1);

	HeaderField hLat = new HeaderField("x");
	hLat.setNotDefinedField(false);
	Set<String> s2 = new HashSet<String>();
	s2.add("lat");
	s2.add("x");
	hLat.setRuleNames(s2);

	HeaderField hLng = new HeaderField("y");
	hLng.setNotDefinedField(false);
	Set<String> s3 = new HashSet<String>();
	s3.add("lng");
	s3.add("y");
	s3.add("lon");
	s3.add("long");
	hLng.setRuleNames(s3);

	fields = new ArrayList<HeaderField>(3);
	fields.add(hName);
	fields.add(hLat);
	fields.add(hLng);
	return fields;
    }

}

package es.icarto.gvsig.importer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import es.udc.cartolab.gvsig.pmf.importer.CentroTarget;
import es.udc.cartolab.gvsig.pmf.importer.ComunidadTarget;
import es.udc.cartolab.gvsig.pmf.importer.PMFRuler;

public class TestReglas {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testComunidad() {
	Target target = new ComunidadTarget();

	assertTrue(target.matches("12345678"));
	assertFalse(target.matches("1234567812345678"));
	assertFalse(target.matches(" 1234567"));
    }

    @Test
    public void testVivienda() {
	Pattern vivPattern = Pattern.compile("^(\\d{8})vi\\d{3}$",
		Pattern.CASE_INSENSITIVE);
	Target vivTarget = new CentroTarget("informacion_general", "cod_viv",
		vivPattern, "vi", "%03d");

	assertFalse(vivTarget.matches("12345678"));
	assertTrue(vivTarget.matches("12345678vI123"));

	// assertEquals("12345678", matcher.group(1));
    }

    @Test
    public void testParcela() {
	Matcher matcher = null;
	Pattern pattern = PMFRuler.parcelaPattern;

	matcher = pattern.matcher("12345678");
	assertFalse(matcher.find());

	matcher = pattern.matcher("17090401vi001p01");
	assertTrue(matcher.find());
	assertEquals("17090401vi001p01", matcher.group());
	assertEquals("17090401vi001", matcher.group(1));
	assertEquals("17090401", matcher.group(2));

    }

    @Test
    public void testVertice() {

	Matcher matcher = null;
	Pattern pattern = PMFRuler.verticePattern;

	matcher = pattern.matcher("12345678");
	assertFalse(matcher.find());

	matcher = pattern.matcher("17090401vi001p01v01");
	assertTrue(matcher.find());
	assertEquals("17090401vi001p01v01", matcher.group(0));
	assertEquals("17090401vi001p01", matcher.group(1));
	assertEquals("17090401vi001", matcher.group(2));
	assertEquals("17090401", matcher.group(3));
    }

}

package es.icarto.gvsig.importer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class TestReglas {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testComunidad() {
	Matcher matcher = null;
	Pattern pattern = Ruler.comunidadPattern;

	matcher = pattern.matcher("12345678");
	assertTrue(matcher.find());

	matcher = pattern.matcher("1234567812345678");
	assertFalse(matcher.find());

	matcher = pattern.matcher(" 1234567");
	assertFalse(matcher.find());
    }

    @Test
    public void testVivienda() {
	Matcher matcher = null;
	Pattern pattern = Ruler.viviendaPattern;

	matcher = pattern.matcher("12345678");
	assertFalse(matcher.find());

	matcher = pattern.matcher("12345678vI123");
	assertTrue(matcher.find());
	assertEquals("12345678", matcher.group(1));
    }

    @Test
    public void testParcela() {
	Matcher matcher = null;
	Pattern pattern = Ruler.parcelaPattern;

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
	Pattern pattern = Ruler.verticePattern;

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

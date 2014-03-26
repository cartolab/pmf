package es.udc.cartolab.testutils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

    public static String driversPath = "../_fwAndami/gvSIG/extensiones/com.iver.cit.gvsig/drivers";

    static {
	Properties p = new Properties();
	FileInputStream in;
	try {
	    in = new FileInputStream("test.properties");
	    p.load(in);
	    in.close();
	    driversPath = p.getProperty("driversPath");
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}

package es.udc.cartolab.testutils;

import java.io.File;

import com.iver.cit.gvsig.fmap.layers.LayerFactory;

import es.udc.cartolab.cit.gvsig.fmap.drivers.jdbc.spatialite.SpatiaLiteDriver;

public class Drivers {

	public static void initgvSIGDrivers(String driversPath) {
		// TestProperties.driversPath.replace("/drivers", "/lib/")
		new SpatiaLiteDriver();
		throwIfPathNotExists(driversPath);
		initgvSIGReadDrivers(driversPath);
		initgvSIGWriteDrivers(driversPath);

	}

	private static void throwIfPathNotExists(String driversPath) {
		if (!new File(driversPath).exists()) {
			throw new RuntimeException("Can't find drivers path: "
					+ driversPath);
		}
	}

	public static void initgvSIGReadDrivers(String driversPath) {
		LayerFactory.setDriversPath(driversPath);
		if (LayerFactory.getDM().getDriverNames().length < 1) {
			throw new RuntimeException("Can't find drivers in path: "
					+ driversPath);
		}

	}

	public static void initgvSIGWriteDrivers(String driversPath) {
		LayerFactory.setWritersPath(driversPath);
		if (LayerFactory.getWM().getWriterNames().length < 1) {
			throw new RuntimeException("Can't find writers in path: "
					+ driversPath);
		}
	}

}

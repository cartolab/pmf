package es.udc.cartolab.gvsig.pmf.utils;

import java.io.File;

import com.iver.andami.Launcher;

public interface PmfConstants {

    public static final String DATA_SCHEMA = "pmf";
    public static final String GPS_MATCHING_FILES = Launcher.getAppHomeDir()
	    + File.separator + "gps-matching-files";
    public static final String GENERAL_MAP = "PMF";
    public static final String SQLITE_FILE = "pmf.sqlite";
    public static final String TEMPLATE_FILE = "pmf.gvt";

}

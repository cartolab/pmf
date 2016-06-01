package es.icarto.gvsig.importer;

import es.icarto.gvsig.commons.utils.Field;

public interface Target {

    boolean matches(String value);

    boolean process(String value, ImporterTM table, int i);

    Field getField();

}
package es.icarto.gvsig.importer;

/**
 * Define el espacio territorial en el que se ubica un elemento. Por ejemplo la
 * geometría de una comunidad debe estar dentro de la geometría de la aldea que
 * indique su código
 *
 *
 */
public class Region {

    private String tablename;
    private String nameName;
    private String pkName;

    public Region(String tablename, String pkName, String nameName) {
	this.tablename = tablename;
	this.pkName = pkName;
	this.nameName = nameName;
    }

}

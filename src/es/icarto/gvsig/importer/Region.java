package es.icarto.gvsig.importer;

/**
 * Define el espacio territorial en el que se ubica un elemento. Por ejemplo la
 * geometr�a de una comunidad debe estar dentro de la geometr�a de la aldea que
 * indique su c�digo
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

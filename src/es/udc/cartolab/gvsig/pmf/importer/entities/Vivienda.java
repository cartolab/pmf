package es.udc.cartolab.gvsig.pmf.importer.entities;

import java.sql.Connection;

import es.icarto.gvsig.importer.Entity;
import es.icarto.gvsig.importer.EntityFactory;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class Vivienda extends Entity {

    public final static String tablename = "informacion_general";
    public final static String pkname = "cod_viv";
    // propietario/a de la vivienda
    public final static String descname = "nom_produ";

    @Override
    public String tablename() {
	return tablename;
    }

    @Override
    public String pkname() {
	return pkname;
    }

    @Override
    public String descname() {
	return descname;
    }

    public static EntityFactory<Vivienda> f() {
	Connection con = DBSession.getCurrentSession().getJavaConnection();
	return new EntityFactory<Vivienda>(con, Vivienda.class);
    }

}

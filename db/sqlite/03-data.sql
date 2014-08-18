
CREATE TABLE comunidades (
       gid INTEGER PRIMARY KEY,
       cod_com VARCHAR
	       UNIQUE
	       NOT NULL,
       nombre VARCHAR,
       municip VARCHAR,
       departa VARCHAR,
       n_habit INTEGER,
       n_fam INTEGER,
       luz_elec VARCHAR(5) DEFAULT 'false',
       agua_pot VARCHAR(5) DEFAULT 'false',
       alcantar VARCHAR(5) DEFAULT 'false',
       tfn_fijo VARCHAR(5) DEFAULT 'false',
       x FLOAT,
       y FLOAT,
       z FLOAT

);

SELECT addgeometrycolumn('comunidades', 'geom', 32616, 'POINT', 2);
SELECT CreateSpatialIndex('comunidades','geom');

CREATE TABLE centros_educativos (
       gid INTEGER PRIMARY KEY,
       cod_com VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_com)
	       ON UPDATE CASCADE ON DELETE CASCADE,
       cod_cedu VARCHAR
	       UNIQUE
	       NOT NULL,
       nom_cedu VARCHAR,
       tipo_cedu VARCHAR
	       REFERENCES tipo_cedu(item),
       n_ninhos INTEGER,
       n_ninhas INTEGER,
       i_deserc FLOAT,
       n_docentes INTEGER,
       mer_escol VARCHAR(5) DEFAULT 'false',
       x FLOAT,
       y FLOAT,
       z FLOAT

);

SELECT addgeometrycolumn('centros_educativos', 'geom', 32616, 'POINT', 2);
SELECT CreateSpatialIndex('centros_educativos','geom');

CREATE TABLE centros_salud (
       gid INTEGER PRIMARY KEY,
       cod_com VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_com)
	       ON UPDATE CASCADE ON DELETE CASCADE,
       cod_csalud VARCHAR
	       UNIQUE
	       NOT NULL,
       nom_csalud VARCHAR,
       inf_resp VARCHAR(5) DEFAULT 'false',
       inf_piel VARCHAR(5) DEFAULT 'false',
       inf_inst VARCHAR(5) DEFAULT 'false',
       inf_vec VARCHAR(5) DEFAULT 'false',
       edad_men5 INTEGER,
       edad_may5 INTEGER,
       x FLOAT,
       y FLOAT,
       z FLOAT

);

SELECT addgeometrycolumn('centros_salud', 'geom', 32616, 'POINT', 2);
SELECT CreateSpatialIndex('centros_salud','geom');

CREATE TABLE centros_reuniones (
       gid INTEGER PRIMARY KEY,
       cod_com VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_com)
	       ON UPDATE CASCADE ON DELETE CASCADE,
       cod_creu VARCHAR
	       UNIQUE
	       NOT NULL,
       nom_creu VARCHAR,
       direccion VARCHAR,
       responsa VARCHAR,
       x FLOAT,
       y FLOAT,
       z FLOAT

);

SELECT addgeometrycolumn('centros_reuniones', 'geom', 32616, 'POINT', 2);
SELECT CreateSpatialIndex('centros_reuniones','geom');

CREATE TABLE informacion_general (
       gid INTEGER PRIMARY KEY,
       cod_com VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_com)
	       ON UPDATE CASCADE ON DELETE CASCADE,
       nom_com VARCHAR,
       cod_viv VARCHAR
	       UNIQUE
	       NOT NULL,
       nom_produ VARCHAR,
       edad_produ INTEGER,
       nid_produ VARCHAR,
       n_personas INTEGER,
       n_ninhas INTEGER,
       n_ninhos INTEGER,
       n_mujer INTEGER,
       n_hombr INTEGER,
       n_embaraz INTEGER,
       direccion VARCHAR,
       agricult VARCHAR(5) DEFAULT 'false',
       ganaderia VARCHAR(5) DEFAULT 'false',
       comercio VARCHAR(5) DEFAULT 'false',
       hay_ot_act VARCHAR(5) DEFAULT 'false',
       otras_act VARCHAR,
       remesas VARCHAR(5) DEFAULT 'false',
       e_temporal VARCHAR(5) DEFAULT 'false',
       e_perman VARCHAR(5) DEFAULT 'false',
       hay_ot_ing VARCHAR(5) DEFAULT 'false',
       otros_ing VARCHAR,
       tip_migra VARCHAR
	       REFERENCES tip_migra(item),
       n_migrante INTEGER,
       mig_dest VARCHAR
	       REFERENCES mig_dest(item),
       estatus_vi VARCHAR
	       REFERENCES estatus_vi(item),
       ot_stat_vi VARCHAR,
       legal_vi VARCHAR
	       REFERENCES legal_vi(item),
       ot_legal_vi VARCHAR,
       pro_viv VARCHAR(5) DEFAULT 'false',
       pro_vivsex VARCHAR
	       REFERENCES pro_vivsex(item),
       x FLOAT,
       y FLOAT,
       z FLOAT,
       mat_pared VARCHAR
	       REFERENCES mat_pared(item),
       ot_mat_pa VARCHAR,
       mat_techo VARCHAR
	       REFERENCES mat_techo(item),
       ot_mat_te VARCHAR,
       mat_piso VARCHAR
	       REFERENCES mat_piso(item),
       ot_mat_pi VARCHAR,
       luz_elec VARCHAR(5) DEFAULT 'false',
       agua_pot VARCHAR(5) DEFAULT 'false',
       telefono VARCHAR(5) DEFAULT 'false',
       alum_publ VARCHAR(5) DEFAULT 'false',
       alcantar VARCHAR(5) DEFAULT 'false',
       hay_in_viv VARCHAR(5) DEFAULT 'false',
       letrina VARCHAR(5) DEFAULT 'false',
       coc_mejor VARCHAR(5) DEFAULT 'false',
       filtro_ag VARCHAR(5) DEFAULT 'false',
       consumo_h VARCHAR(5) DEFAULT 'false',
       cual_ch VARCHAR,
       silos VARCHAR(5) DEFAULT 'false',
       trojas_mej VARCHAR(5) DEFAULT 'false',
       sacos VARCHAR(5) DEFAULT 'false',
       ramadas VARCHAR(5) DEFAULT 'false',
       sist_rieg VARCHAR(5) DEFAULT 'false',
       cap_techo VARCHAR(5) DEFAULT 'false',
       gallinero VARCHAR(5) DEFAULT 'false',
       dep_almac VARCHAR(5) DEFAULT 'false',
       ot_dep_alm VARCHAR,
       cons_mar_hay VARCHAR(5) DEFAULT 'false',
       cons_mar_tipo VARCHAR,
       pesca_hay VARCHAR(5) DEFAULT 'false',
       pesca_lugar VARCHAR,
       pesca_venta_quien VARCHAR
	       REFERENCES pesca_venta_quien(item),
       pesca_venta_quien_otros VARCHAR,
       lancha VARCHAR(5) DEFAULT 'false',
       cayuco VARCHAR(5) DEFAULT 'false',
       neveras VARCHAR(5) DEFAULT 'false',
       frizeres VARCHAR(5) DEFAULT 'false',
       pailas VARCHAR(5) DEFAULT 'false',
       redes VARCHAR(5) DEFAULT 'false',
       otra_logistica VARCHAR(5) DEFAULT 'false',
       otra_logistica_cual VARCHAR,
       disp_desec VARCHAR
	       REFERENCES disp_desec(item),
       disp_desec_cual VARCHAR

);

SELECT addgeometrycolumn('informacion_general', 'geom', 32616, 'POINT', 2);
SELECT CreateSpatialIndex('informacion_general','geom');

CREATE TABLE pesca_capturas (
       gid INTEGER PRIMARY KEY,
       cod_viv VARCHAR
	       NOT NULL
	       REFERENCES informacion_general(cod_viv)
	       ON UPDATE CASCADE ON DELETE CASCADE,
       especie VARCHAR
	       REFERENCES especie(item),
       especie_otra VARCHAR,
       cantidad NUMERIC(6,2),
       precio NUMERIC(6,2)

);


CREATE TABLE fuentes_comunitarias (
       gid INTEGER PRIMARY KEY,
       cod_com VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_com)
	       ON UPDATE CASCADE ON DELETE CASCADE,
       codigo_fc VARCHAR
	       UNIQUE
	       NOT NULL,
       x FLOAT,
       y FLOAT,
       z FLOAT

);

SELECT addgeometrycolumn('fuentes_comunitarias', 'geom', 32616, 'POINT', 2);
SELECT CreateSpatialIndex('fuentes_comunitarias','geom');

CREATE TABLE organizaciones_base (
       gid INTEGER PRIMARY KEY,
       cod_com VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_com)
	       ON UPDATE CASCADE ON DELETE CASCADE,
       org_exist VARCHAR
	       UNIQUE
	       NOT NULL,
       func VARCHAR,
       resp VARCHAR

);


CREATE TABLE presencia_institucional (
       gid INTEGER PRIMARY KEY,
       cod_com VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_com)
	       ON UPDATE CASCADE ON DELETE CASCADE,
       org_exist VARCHAR
	       UNIQUE
	       NOT NULL,
       func VARCHAR,
       resp VARCHAR

);


CREATE TABLE parcelas (
       gid INTEGER PRIMARY KEY,
       cod_com VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_com)
	       ON UPDATE CASCADE ON DELETE CASCADE,
       nom_com VARCHAR,
       cod_viv VARCHAR
	       NOT NULL
	       REFERENCES informacion_general(cod_viv)
	       ON UPDATE CASCADE ON DELETE CASCADE,
       cod_parcela VARCHAR
	       UNIQUE
	       NOT NULL,
       legal_par VARCHAR
	       REFERENCES legal_par(item),
       ot_legal_p VARCHAR,
       pro_finsex VARCHAR
	       REFERENCES pro_finsex(item),
       area_tot FLOAT,
       area_cul FLOAT,
       rio VARCHAR(5) DEFAULT 'false',
       nacimiento VARCHAR(5) DEFAULT 'false',
       poz_com VARCHAR(5) DEFAULT 'false',
       reserv VARCHAR(5) DEFAULT 'false',
       poz_pro VARCHAR(5) DEFAULT 'false',
       no_fuen VARCHAR(5) DEFAULT 'false',
       existe_fc VARCHAR(5) DEFAULT 'false',
       codigo_fc VARCHAR
       		 REFERENCES fuentes_comunitarias(codigo_fc),
       d_fue_tan FLOAT,
       d_tan_hue FLOAT,
       hay_in_par VARCHAR(5) DEFAULT 'false',
       prac_conse VARCHAR(5) DEFAULT 'false',
       c_conse VARCHAR,
       uso_aborg VARCHAR(5) DEFAULT 'false',
       c_aborg VARCHAR,
       insect_org VARCHAR(5) DEFAULT 'false',
       c_insect VARCHAR,
       uso_quim VARCHAR(5) DEFAULT 'false',
       c_quim VARCHAR,
       deg_suelo VARCHAR
	       REFERENCES deg_suelo(item),
       tip_suelo VARCHAR
	       REFERENCES tip_suelo(item),
       ot_tip_su VARCHAR,
       pendiente VARCHAR
	       REFERENCES pendiente(item),
       cerca VARCHAR(5) DEFAULT 'false',
       b_vivas VARCHAR(5) DEFAULT 'false',
       b_muertas VARCHAR(5) DEFAULT 'false',
       hay_ot_cer VARCHAR(5) DEFAULT 'false',
       ot_cerca VARCHAR,
       no_cultivo VARCHAR(5) DEFAULT 'false',
       maiz VARCHAR(5) DEFAULT 'false',
       frijol VARCHAR(5) DEFAULT 'false',
       maicillo VARCHAR(5) DEFAULT 'false',
       hortalizas VARCHAR(5) DEFAULT 'false',
       yuca VARCHAR(5) DEFAULT 'false',
       hay_ot_cul VARCHAR(5) DEFAULT 'false',
       otrocan VARCHAR,
       no_cul_sem VARCHAR(5) DEFAULT 'false',
       musacea VARCHAR(5) DEFAULT 'false',
       papaya VARCHAR(5) DEFAULT 'false',
       pasto VARCHAR(5) DEFAULT 'false',
       hay_ot_sp VARCHAR(5) DEFAULT 'false',
       otrocspe VARCHAR,
       noarb VARCHAR(5) DEFAULT 'false',
       arbfru VARCHAR(5) DEFAULT 'false',
       arbfor VARCHAR(5) DEFAULT 'false',
       p_riego VARCHAR,
       p_huerto VARCHAR,
       p_coc_mejo VARCHAR,
       p_filtroag VARCHAR,
       p_galline VARCHAR,
       fam_cant INTEGER,
       fam_per VARCHAR,
       con_cant INTEGER,
       con_per VARCHAR

);

SELECT addgeometrycolumn('parcelas', 'geom', 32616, 'MULTIPOLYGON', 2);
SELECT CreateSpatialIndex('parcelas','geom');

CREATE TABLE cultivos (
       gid INTEGER PRIMARY KEY,
       cod_parcela VARCHAR
	       NOT NULL
	       REFERENCES parcelas(cod_parcela)
	       ON UPDATE CASCADE ON DELETE CASCADE,
       cultivo VARCHAR
	       REFERENCES rubro(item),
       area FLOAT,
       vol_prod FLOAT,
       vol_con FLOAT

);


CREATE TABLE balances (
-- cod_parcela + rubro + f_siembra forman la pk
       gid INTEGER PRIMARY KEY,
       cod_parcela VARCHAR
	       NOT NULL
	       REFERENCES parcelas(cod_parcela)
	       ON UPDATE CASCADE ON DELETE CASCADE,
       cod_balance VARCHAR
               NOT NULL
               UNIQUE,
       tipo_rubro VARCHAR
	       REFERENCES tipo_rubro(item),
       rubro VARCHAR
               NOT NULL
	       REFERENCES rubro(item),
       f_siembra Date
               NOT NULL,
       f_cosecha Date,
       volumen_prod_ud FLOAT,
       volumen_prod_kg FLOAT,
       area_prod FLOAT,
       rendimiento_prod FLOAT,
       mano_obra FLOAT,
       establecimiento FLOAT,
       manejo FLOAT,
       cosecha FLOAT,
       mano_obra_otros FLOAT,
       insumos FLOAT,
       equipo FLOAT,
       material FLOAT,
       otros FLOAT,
       transporte FLOAT,
       alquiler FLOAT,
       otros_otros FLOAT,
       coste_total FLOAT,
       empleo_temp_total INTEGER,
       empleo_temp_hombre INTEGER,
       empleo_temp_mujer INTEGER,
       venta_total FLOAT,
       consumo_familiar FLOAT,
       beneficio FLOAT

);


CREATE TABLE ventas (
       gid INTEGER PRIMARY KEY,
       cod_balance VARCHAR
	       REFERENCES balances(cod_balance),
       cod_comprador VARCHAR,
       precio FLOAT,
       volumen FLOAT,
       total FLOAT

);

CREATE TABLE limites_parcela (
       gid INTEGER PRIMARY KEY,
       cod_parcela VARCHAR
	       NOT NULL,
       cod_lim_p VARCHAR
	       UNIQUE
	       NOT NULL,
       x FLOAT,
       y FLOAT,
       z FLOAT

);

SELECT addgeometrycolumn('limites_parcela', 'geom', 32616, 'POINT', 2);
SELECT CreateSpatialIndex('limites_parcela','geom');


CREATE TABLE compradores (
       gid INTEGER PRIMARY KEY,
       cod_comprador VARCHAR
	       NOT NULL
	       UNIQUE,
       nombre VARCHAR
       	      NOT NULL,
       contacto VARCHAR,
       lugar_venta VARCHAR,
       x FLOAT,
       y FLOAT,
       z FLOAT,
       compra_total FLOAT

);

SELECT addgeometrycolumn('compradores', 'geom', 32616, 'POINT', 2);
SELECT CreateSpatialIndex('compradores','geom');


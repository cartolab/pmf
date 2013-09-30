RESET search_path;

CREATE TABLE pmf.comunidades (
       gid SERIAL PRIMARY KEY,
       cod_com VARCHAR
	       UNIQUE
	       NOT NULL,
       nombre VARCHAR,
       municip VARCHAR,
       departa VARCHAR,
       n_habit INTEGER,
       n_fam INTEGER,
       luz_elec BOOLEAN,
       agua_pot BOOLEAN,
       alcantar BOOLEAN,
       tfn_fijo BOOLEAN,
       x FLOAT,
       y FLOAT,
       z FLOAT

);

SELECT addgeometrycolumn('pmf', 'comunidades', 'geom', 32616, 'POINT', 2);
CREATE INDEX pmf_comunidades_geom ON pmf.comunidades USING GIST(geom);

ALTER TABLE pmf.comunidades OWNER TO pmf;

CREATE TABLE pmf.centros_educativos (
       gid SERIAL PRIMARY KEY,
       cod_com VARCHAR
	       NOT NULL
	       REFERENCES pmf.comunidades(cod_com),
       cod_cedu VARCHAR
	       UNIQUE
	       NOT NULL,
       nom_cedu VARCHAR,
       tipo_cedu VARCHAR
	       REFERENCES dominios.tipo_cedu(item),
       n_ninhos INTEGER,
       n_ninhas INTEGER,
       n_docentes INTEGER,
       i_deserc FLOAT,
       mer_escol BOOLEAN,
       x FLOAT,
       y FLOAT,
       z FLOAT

);

SELECT addgeometrycolumn('pmf', 'centros_educativos', 'geom', 32616, 'POINT', 2);
CREATE INDEX pmf_centros_educativos_geom ON pmf.centros_educativos USING GIST(geom);

ALTER TABLE pmf.centros_educativos OWNER TO pmf;

CREATE TABLE pmf.centros_salud (
       gid SERIAL PRIMARY KEY,
       cod_com VARCHAR
	       NOT NULL
	       REFERENCES pmf.comunidades(cod_com),
       cod_csalud VARCHAR
	       UNIQUE
	       NOT NULL,
       nom_csalud VARCHAR,
       inf_resp BOOLEAN,
       inf_piel BOOLEAN,
       inf_inst BOOLEAN,
       inf_vec BOOLEAN,
       edad_men5 INTEGER,
       edad_may5 INTEGER,
       x FLOAT,
       y FLOAT,
       z FLOAT

);

SELECT addgeometrycolumn('pmf', 'centros_salud', 'geom', 32616, 'POINT', 2);
CREATE INDEX pmf_centros_salud_geom ON pmf.centros_salud USING GIST(geom);

ALTER TABLE pmf.centros_salud OWNER TO pmf;

CREATE TABLE pmf.centros_reuniones (
       gid SERIAL PRIMARY KEY,
       cod_com VARCHAR
	       NOT NULL
	       REFERENCES pmf.comunidades(cod_com),
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

SELECT addgeometrycolumn('pmf', 'centros_reuniones', 'geom', 32616, 'POINT', 2);
CREATE INDEX pmf_centros_reuniones_geom ON pmf.centros_reuniones USING GIST(geom);

ALTER TABLE pmf.centros_reuniones OWNER TO pmf;

CREATE TABLE pmf.organizaciones_base (
       gid SERIAL PRIMARY KEY,
       cod_com VARCHAR
	       NOT NULL
	       REFERENCES pmf.comunidades(cod_com),
       org_exist VARCHAR
	       UNIQUE
	       NOT NULL,
       func VARCHAR,
       resp VARCHAR

);


ALTER TABLE pmf.organizaciones_base OWNER TO pmf;

CREATE TABLE pmf.presencia_institucional (
       gid SERIAL PRIMARY KEY,
       cod_com VARCHAR
	       NOT NULL
	       REFERENCES pmf.comunidades(cod_com),
       org_exist VARCHAR
	       UNIQUE
	       NOT NULL,
       func VARCHAR,
       resp VARCHAR

);


ALTER TABLE pmf.presencia_institucional OWNER TO pmf;

CREATE TABLE pmf.informacion_general (
       gid SERIAL PRIMARY KEY,
       cod_com VARCHAR
	       NOT NULL,
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
       agricult BOOLEAN,
       ganaderia BOOLEAN,
       comercio BOOLEAN,
       hay_ot_act BOOLEAN,
       otras_act VARCHAR,
       remesas BOOLEAN,
       e_temporal BOOLEAN,
       e_perman BOOLEAN,
       hay_ot_ing BOOLEAN,
       otros_ing VARCHAR,
       tip_migra VARCHAR
	       REFERENCES dominios.tip_migra(item),
       n_migrante INTEGER,
       mig_dest VARCHAR
	       REFERENCES dominios.mig_dest(item),
       estatus_vi VARCHAR
	       REFERENCES dominios.estatus_vi(item),
       ot_stat_vi VARCHAR,
       legal_vi VARCHAR
               REFERENCES dominios.legal_par(item),
       ot_legal_vi VARCHAR,
       pro_viv BOOLEAN,
       pro_vivsex VARCHAR
	       REFERENCES dominios.pro_vivsex(item),
       x FLOAT,
       y FLOAT,
       z FLOAT,
       mat_techo VARCHAR
	       REFERENCES dominios.mat_techo(item),
       ot_mat_te VARCHAR,
       mat_pared VARCHAR
	       REFERENCES dominios.mat_pared(item),
       ot_mat_pa VARCHAR,
       mat_piso VARCHAR
	       REFERENCES dominios.mat_piso(item),
       ot_mat_pi VARCHAR,
       luz_elec BOOLEAN,
       agua_pot BOOLEAN,
       telefono BOOLEAN,
       alum_publ BOOLEAN,
       alcantar BOOLEAN,
       hay_in_viv BOOLEAN,
       letrina BOOLEAN,
       coc_mejor BOOLEAN,
       filtro_ag BOOLEAN,
       consumo_h BOOLEAN,
       cual_ch VARCHAR,
       silos BOOLEAN,
       trojas_mej BOOLEAN,
       sacos BOOLEAN,
       ramadas BOOLEAN,
       sist_rieg BOOLEAN,
       cap_techo BOOLEAN,
       gallinero BOOLEAN,
       dep_almac BOOLEAN,
       ot_dep_alm VARCHAR,
       cons_mar_hay BOOLEAN,
       cons_mar_tipo VARCHAR,
       pesca_hay BOOLEAN,
       pesca_lugar VARCHAR,
       pesca_venta_quien VARCHAR
	       REFERENCES dominios.pesca_venta_quien(item),
       pesca_venta_quien_otros VARCHAR,
       lancha BOOLEAN,
       cayuco BOOLEAN,
       neveras BOOLEAN,
       frizeres BOOLEAN,
       pailas BOOLEAN,
       redes BOOLEAN,
       otra_logistica BOOLEAN,
       otra_logistica_cual VARCHAR,
       disp_desec VARCHAR
	       REFERENCES dominios.disp_desec(item),
       disp_desec_cual VARCHAR

);

SELECT addgeometrycolumn('pmf', 'informacion_general', 'geom', 32616, 'POINT', 2);
CREATE INDEX pmf_informacion_general_geom ON pmf.informacion_general USING GIST(geom);

ALTER TABLE pmf.informacion_general OWNER TO pmf;

CREATE TABLE pmf.pesca_capturas (
       gid SERIAL PRIMARY KEY,
       cod_viv VARCHAR
	       NOT NULL
	       REFERENCES pmf.informacion_general(cod_viv),
       especie VARCHAR
	       REFERENCES dominios.especie(item),
       especie_otra VARCHAR,
       cantidad NUMERIC(5,2),
       precio NUMERIC(5,2)

);


ALTER TABLE pmf.pesca_capturas OWNER TO pmf;

CREATE TABLE pmf.parcelas (
       gid SERIAL PRIMARY KEY,
       cod_com VARCHAR
	       NOT NULL,
       nom_com VARCHAR,
       cod_viv VARCHAR
	       UNIQUE
	       NOT NULL
	       REFERENCES pmf.informacion_general(cod_viv),,
       legal_par VARCHAR
	       REFERENCES dominios.legal_par(item),
       ot_legal_p VARCHAR,
       pro_finsex VARCHAR
	       REFERENCES dominios.pro_finsex(item),
       area_tot FLOAT,
       area_cul FLOAT,
       rio BOOLEAN,
       nacimiento BOOLEAN,
       poz_com BOOLEAN,
       reserv BOOLEAN,
       poz_pro BOOLEAN,
       no_fuen BOOLEAN,
       existe_fc BOOLEAN,
       codigo_fc VARCHAR,
       d_fue_tan FLOAT,
       d_tan_hue FLOAT,
       hay_in_par BOOLEAN,
       prac_conse BOOLEAN,
       c_conse VARCHAR,
       uso_aborg BOOLEAN,
       c_aborg VARCHAR,
       insect_org BOOLEAN,
       c_insect VARCHAR,
       uso_quim BOOLEAN,
       c_quim VARCHAR,
       deg_suelo VARCHAR
	       REFERENCES dominios.deg_suelo(item),
       tip_suelo VARCHAR
	       REFERENCES dominios.tip_suelo(item),
       ot_tip_su VARCHAR,
       pendiente VARCHAR
	       REFERENCES dominios.pendiente(item),
       cerca BOOLEAN,
       b_vivas BOOLEAN,
       b_muertas BOOLEAN,
       hay_ot_cer BOOLEAN,
       ot_cerca VARCHAR,
       no_cultivo BOOLEAN,
       maiz BOOLEAN,
       frijol BOOLEAN,
       maicillo BOOLEAN,
       hortalizas BOOLEAN,
       yuca BOOLEAN,
       hay_ot_cul BOOLEAN,
       otrocan VARCHAR,
       no_cul_sem BOOLEAN,
       musacea BOOLEAN,
       papaya BOOLEAN,
       pasto BOOLEAN,
       hay_ot_sp BOOLEAN,
       otrocspe VARCHAR,
       noarb BOOLEAN,
       arbfru BOOLEAN,
       arbfor BOOLEAN,
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

SELECT addgeometrycolumn('pmf', 'parcelas', 'geom', 32616, 'MULTIPOLYGON', 2);
CREATE INDEX pmf_parcelas_geom ON pmf.parcelas USING GIST(geom);

ALTER TABLE pmf.parcelas OWNER TO pmf;

CREATE TABLE pmf.cultivos (
       gid SERIAL PRIMARY KEY,
       cod_viv VARCHAR
	       NOT NULL
	       REFERENCES pmf.parcelas(cod_viv),
       tipo VARCHAR
	       REFERENCES dominios.cultivo_tipo(item),
       area FLOAT,
       vol_prod FLOAT,
       vol_con FLOAT

);


ALTER TABLE pmf.cultivos OWNER TO pmf;

CREATE TABLE pmf.fuentes_comunitarias (
       gid SERIAL PRIMARY KEY,
       codigo_fc VARCHAR
	       UNIQUE
	       NOT NULL,
       x FLOAT,
       y FLOAT,
       z FLOAT

);

SELECT addgeometrycolumn('pmf', 'fuentes_comunitarias', 'geom', 32616, 'POINT', 2);
CREATE INDEX pmf_fuentes_comunitarias_geom ON pmf.fuentes_comunitarias USING GIST(geom);

ALTER TABLE pmf.fuentes_comunitarias OWNER TO pmf;

CREATE TABLE pmf.limites_parcela (
       gid SERIAL PRIMARY KEY,
       cod_viv VARCHAR
	       NOT NULL,
       cod_lim_p VARCHAR
	       UNIQUE
	       NOT NULL,
       x FLOAT,
       y FLOAT,
       z FLOAT

);

SELECT addgeometrycolumn('pmf', 'limites_parcela', 'geom', 32616, 'POINT', 2);
CREATE INDEX pmf_limites_parcela_geom ON pmf.limites_parcela USING GIST(geom);

ALTER TABLE pmf.limites_parcela OWNER TO pmf;

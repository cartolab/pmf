

CREATE TABLE dominios.tipo_cedu (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipo_cedu (item) VALUES
       (' '),
       ('Jardín de niños'),
       ('Centro educativo escolar'),
       ('Centro educativo de ciclo común'),
       ('Centro educativo diversificado');

ALTER TABLE dominios.tipo_cedu OWNER TO pmf;


CREATE TABLE dominios.tip_migra (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tip_migra (item) VALUES
       (' '),
       ('Temporal'),
       ('Permanente');

ALTER TABLE dominios.tip_migra OWNER TO pmf;


CREATE TABLE dominios.mig_dest (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.mig_dest (item) VALUES
       (' '),
       ('Nacional'),
       ('Internacional');

ALTER TABLE dominios.mig_dest OWNER TO pmf;


CREATE TABLE dominios.estatus_vi (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.estatus_vi (item) VALUES
       (' '),
       ('Propia'),
       ('Alquilada'),
       ('Otras');

ALTER TABLE dominios.estatus_vi OWNER TO pmf;


CREATE TABLE dominios.pro_vivsex (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.pro_vivsex (item) VALUES
       (' '),
       ('Hombre'),
       ('Mujer');

ALTER TABLE dominios.pro_vivsex OWNER TO pmf;


CREATE TABLE dominios.mat_techo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.mat_techo (item) VALUES
       (' '),
       ('Bahareque'),
       ('Adobe'),
       ('Ladrillo'),
       ('Otros');

ALTER TABLE dominios.mat_techo OWNER TO pmf;


CREATE TABLE dominios.mat_pared (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.mat_pared (item) VALUES
       (' '),
       ('Teja'),
       ('Alucin'),
       ('Manaca'),
       ('Otros');

ALTER TABLE dominios.mat_pared OWNER TO pmf;


CREATE TABLE dominios.mat_piso (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.mat_piso (item) VALUES
       (' '),
       ('Mosaico'),
       ('Tierra'),
       ('Cemento'),
       ('Otros');

ALTER TABLE dominios.mat_piso OWNER TO pmf;


CREATE TABLE dominios.pesca_venta_quien (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.pesca_venta_quien (item) VALUES
       (' '),
       ('Intermediarios'),
       ('Comunidades'),
       ('Mercados locales'),
       ('Otros');

ALTER TABLE dominios.pesca_venta_quien OWNER TO pmf;


CREATE TABLE dominios.disp_desec (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.disp_desec (item) VALUES
       (' '),
       ('Quema'),
       ('Enterramiento'),
       ('Reciclaje'),
       ('Al mar'),
       ('Otros');

ALTER TABLE dominios.disp_desec OWNER TO pmf;


CREATE TABLE dominios.especie (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.especie (item) VALUES
       (' '),
       ('Pescado rojo'),
       ('Pescado blanco'),
       ('Camarón'),
       ('Cangrejos'),
       ('Rayas'),
       ('Otros');

ALTER TABLE dominios.especie OWNER TO pmf;


CREATE TABLE dominios.legal_par (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.legal_par (item) VALUES
       (' '),
       ('Dominio pleno'),
       ('Dominio útil'),
       ('Herencia'),
       ('Otros');

ALTER TABLE dominios.legal_par OWNER TO pmf;


CREATE TABLE dominios.pro_finsex (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.pro_finsex (item) VALUES
       (' '),
       ('Hombre'),
       ('Mujer');

ALTER TABLE dominios.pro_finsex OWNER TO pmf;


CREATE TABLE dominios.deg_suelo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.deg_suelo (item) VALUES
       (' '),
       ('Alta'),
       ('Media'),
       ('Baja');

ALTER TABLE dominios.deg_suelo OWNER TO pmf;


CREATE TABLE dominios.tip_suelo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tip_suelo (item) VALUES
       (' '),
       ('Franco-Arenoso'),
       ('Franco-Limoso'),
       ('Franco-Arcilloso'),
       ('Otros');

ALTER TABLE dominios.tip_suelo OWNER TO pmf;


CREATE TABLE dominios.pendiente (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.pendiente (item) VALUES
       (' '),
       ('0-7%'),
       ('8-14%'),
       ('15-21%'),
       ('22-28%'),
       ('29-35%'),
       ('36-42%'),
       ('>42%');

ALTER TABLE dominios.pendiente OWNER TO pmf;


CREATE TABLE dominios.cultivo_tipo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.cultivo_tipo (item) VALUES
       (' '),
       ('Maíz'),
       ('Frijol'),
       ('Maicillo'),
       ('Hortalizas'),
       ('Yuca'),
       ('Otros anuales'),
       ('Musáceas'),
       ('Papaya'),
       ('Pastos'),
       ('Otros semipermanentes');

ALTER TABLE dominios.cultivo_tipo OWNER TO pmf;

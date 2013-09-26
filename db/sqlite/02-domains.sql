CREATE TABLE tipo_cedu (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipo_cedu (item) SELECT
       (' ') UNION SELECT
       ('Kinder') UNION SELECT
       ('Centro escolar') UNION SELECT
       ('Centro de educación básica') UNION SELECT
       ('Colegio');



CREATE TABLE tip_migra (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tip_migra (item) SELECT
       (' ') UNION SELECT
       ('Temporal') UNION SELECT
       ('Permanente');



CREATE TABLE mig_dest (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO mig_dest (item) SELECT
       (' ') UNION SELECT
       ('Nacional') UNION SELECT
       ('Internacional');



CREATE TABLE estatus_vi (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO estatus_vi (item) SELECT
       (' ') UNION SELECT
       ('Propia') UNION SELECT
       ('Alquilada') UNION SELECT
       ('Otras');



CREATE TABLE pro_vivsex (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO pro_vivsex (item) SELECT
       (' ') UNION SELECT
       ('Hombre') UNION SELECT
       ('Mujer');



CREATE TABLE mat_techo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO mat_techo (item) SELECT
       (' ') UNION SELECT
       ('Teja') UNION SELECT
       ('Alucin') UNION SELECT
       ('Manaca') UNION SELECT
       ('Otros');



CREATE TABLE mat_pared (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO mat_pared (item) SELECT
       (' ') UNION SELECT
       ('Bahareque') UNION SELECT
       ('Adobe') UNION SELECT
       ('Ladrillo') UNION SELECT
       ('Otros');



CREATE TABLE mat_piso (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO mat_piso (item) SELECT
       (' ') UNION SELECT
       ('Mosaico') UNION SELECT
       ('Tierra') UNION SELECT
       ('Cemento') UNION SELECT
       ('Otros');



CREATE TABLE pesca_venta_quien (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO pesca_venta_quien (item) SELECT
       (' ') UNION SELECT
       ('Intermediarios') UNION SELECT
       ('Comunidades') UNION SELECT
       ('Mercados locales') UNION SELECT
       ('Otros');



CREATE TABLE disp_desec (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO disp_desec (item) SELECT
       (' ') UNION SELECT
       ('Quema') UNION SELECT
       ('Enterramiento') UNION SELECT
       ('Reciclaje') UNION SELECT
       ('Al mar') UNION SELECT
       ('Otros');



CREATE TABLE especie (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO especie (item) SELECT
       (' ') UNION SELECT
       ('Pescado rojo') UNION SELECT
       ('Pescado blanco') UNION SELECT
       ('Camarón') UNION SELECT
       ('Cangrejos') UNION SELECT
       ('Rayas') UNION SELECT
       ('Otra');



CREATE TABLE legal_par (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO legal_par (item) SELECT
       (' ') UNION SELECT
       ('Dominio pleno') UNION SELECT
       ('Dominio útil') UNION SELECT
       ('Herencia') UNION SELECT
       ('En proceso de adquisición') UNION SELECT
       ('Otro');



CREATE TABLE pro_finsex (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO pro_finsex (item) SELECT
       (' ') UNION SELECT
       ('Hombre') UNION SELECT
       ('Mujer');



CREATE TABLE deg_suelo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO deg_suelo (item) SELECT
       (' ') UNION SELECT
       ('Alta') UNION SELECT
       ('Media') UNION SELECT
       ('Baja');



CREATE TABLE tip_suelo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tip_suelo (item) SELECT
       (' ') UNION SELECT
       ('Franco-Arenoso') UNION SELECT
       ('Franco-Limoso') UNION SELECT
       ('Franco-Arcilloso') UNION SELECT
       ('Otros');



CREATE TABLE pendiente (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO pendiente (item) SELECT
       (' ') UNION SELECT
       ('0-7%') UNION SELECT
       ('8-14%') UNION SELECT
       ('15-21%') UNION SELECT
       ('22-28%') UNION SELECT
       ('29-35%') UNION SELECT
       ('36-42%') UNION SELECT
       ('>42%');



CREATE TABLE cultivo_tipo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO cultivo_tipo (item) SELECT
       (' ') UNION SELECT
       ('Maíz') UNION SELECT
       ('Frijol') UNION SELECT
       ('Maicillo') UNION SELECT
       ('Hortalizas') UNION SELECT
       ('Yuca') UNION SELECT
       ('Otros anuales') UNION SELECT
       ('Musáceas') UNION SELECT
       ('Papaya') UNION SELECT
       ('Pastos') UNION SELECT
       ('Otros semipermanentes');

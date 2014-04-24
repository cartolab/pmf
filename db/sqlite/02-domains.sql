

CREATE TABLE tipo_cedu (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipo_cedu (item) VALUES
       (' '),
       ('Kinder'),
       ('Centro educativo básico'),
       ('Centro educativo escolar'),
       ('Centro educativo de ciclo común'),
       ('Centro educativo diversificado');



CREATE TABLE tip_migra (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tip_migra (item) VALUES
       (' '),
       ('Temporal'),
       ('Permanente');



CREATE TABLE mig_dest (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO mig_dest (item) VALUES
       (' '),
       ('Nacional'),
       ('Internacional');



CREATE TABLE estatus_vi (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO estatus_vi (item) VALUES
       (' '),
       ('Propia'),
       ('Alquilada'),
       ('Otro');



CREATE TABLE legal_vi (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO legal_vi (item) VALUES
       (' '),
       ('Dominio pleno'),
       ('Dominio útil'),
       ('Herencia'),
       ('Otro'),
       ('En proceso de adquisición');



CREATE TABLE pro_vivsex (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO pro_vivsex (item) VALUES
       (' '),
       ('Hombre'),
       ('Mujer');



CREATE TABLE mat_pared (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO mat_pared (item) VALUES
       (' '),
       ('Bahareque'),
       ('Adobe'),
       ('Ladrillo'),
       ('Otros');



CREATE TABLE mat_techo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO mat_techo (item) VALUES
       (' '),
       ('Teja'),
       ('Alucin'),
       ('Manaca'),
       ('Otros');



CREATE TABLE mat_piso (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO mat_piso (item) VALUES
       (' '),
       ('Mosaico'),
       ('Tierra'),
       ('Cemento'),
       ('Otros');



CREATE TABLE pesca_venta_quien (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO pesca_venta_quien (item) VALUES
       (' '),
       ('Intermediarios'),
       ('Comunidades'),
       ('Mercados locales'),
       ('Otros');



CREATE TABLE disp_desec (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO disp_desec (item) VALUES
       (' '),
       ('Quema'),
       ('Enterramiento'),
       ('Reciclaje'),
       ('Al mar'),
       ('Otro');



CREATE TABLE especie (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO especie (item) VALUES
       (' '),
       ('Pescado rojo'),
       ('Pescado blanco'),
       ('Camarón'),
       ('Cangrejos'),
       ('Rayas'),
       ('Otra');



CREATE TABLE legal_par (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO legal_par (item) VALUES
       (' '),
       ('Dominio pleno'),
       ('Dominio útil'),
       ('Herencia'),
       ('Otro'),
       ('En proceso de adquisición');



CREATE TABLE pro_finsex (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO pro_finsex (item) VALUES
       (' '),
       ('Hombre'),
       ('Mujer');



CREATE TABLE deg_suelo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO deg_suelo (item) VALUES
       (' '),
       ('Alta'),
       ('Media'),
       ('Baja');



CREATE TABLE tip_suelo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tip_suelo (item) VALUES
       (' '),
       ('Franco-Arenoso'),
       ('Franco-Limoso'),
       ('Franco-Arcilloso'),
       ('Otros');



CREATE TABLE pendiente (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO pendiente (item) VALUES
       (' '),
       ('0-7%'),
       ('8-14%'),
       ('15-21%'),
       ('22-28%'),
       ('29-35%'),
       ('36-42%'),
       ('>42%');



CREATE TABLE cultivo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO cultivo (item) VALUES
       (' '),
       ('Aves Criollas'),
       ('Ayote'),
       ('Camote'),
       ('Chile'),
       ('Limón'),
       ('Frijol'),
       ('Guayaba'),
       ('Maicillo'),
       ('Maíz'),
       ('Marañón'),
       ('Miel'),
       ('Musáceas'),
       ('Papaya'),
       ('Pastos'),
       ('Sandía'),
       ('Yuca'),
       ('Hortalizas'),
       ('Otros anuales'),
       ('Otros semipermanentes'),
       ('Otros');



CREATE TABLE tipo_cultivo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipo_cultivo (item) VALUES
       (' '),
       ('Granos básicos'),
       ('Hortícola'),
       ('Frutales'),
       ('Otros');


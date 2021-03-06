

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
       ('Aluzinc'),
       ('Manaca'),
       ('Teja y Aluzinc'),
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

CREATE TABLE tipo_rubro (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipo_rubro (item) VALUES
       (' '),
       ('Granos básicos'),
       ('Hortícola'),
       ('Frutales'),
       ('Pecuario'),
       ('Otros');

CREATE TABLE rubro (
       item VARCHAR
	       PRIMARY KEY,
       tipo_rubro VARCHAR
	       REFERENCES tipo_rubro(item)

);

INSERT INTO rubro (item, tipo_rubro) VALUES
       (' ', ' '),
       ('Aves Criollas', 'Pecuario'),
       ('Ayote', 'Hortícola'),
       ('Cabras', 'Pecuario'),
       ('Camote', 'Hortícola'),
       ('Cerdos', 'Pecuario'),
       ('Chile', 'Hortícola'),
       ('Lácteos', 'Pecuario'),
       ('Limón', 'Frutales'),
       ('Frijol', 'Granos básicos'),
       ('Guayaba', 'Frutales'),
       ('Maicillo', 'Granos básicos'),
       ('Maíz', 'Granos básicos'),
       ('Marañón', 'Frutales'),
       ('Miel', 'Pecuario'),
       ('Musáceas', 'Frutales'),
       ('Papaya', 'Frutales'),
       ('Pastos', 'Otros'),
       ('Sandía', 'Hortícola'),
       ('Yuca', 'Hortícola'),
       ('Hortalizas', 'Otros'),
       ('Huevos', 'Pecuario'),
       ('Otros anuales', 'Otros'),
       ('Otros semipermanentes', 'Otros'),
       ('Otros', 'Otros'),
       ('Vacas', 'Pecuario');

CREATE TABLE ingresos_generales_familiares (
       item VARCHAR
       	    PRIMARY KEY
);

INSERT INTO ingresos_generales_familiares (item) VALUES
       (' '),
       ('1000 - 2000'),
       ('2001 - 3000'),
       ('3001 - 4000'),
       ('4001 - 5000'),
       ('5001 - 6000'),
       ('6001 - 7000'),
       ('> 7000');

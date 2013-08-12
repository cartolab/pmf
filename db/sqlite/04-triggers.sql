DROP TRIGGER IF EXISTS comunidades_compute_fields_insert_trigger;
CREATE TRIGGER comunidades_compute_fields_insert_trigger
AFTER INSERT ON comunidades
FOR EACH ROW BEGIN
	UPDATE comunidades SET x = X(NEW.geom), y = Y(NEW.geom) WHERE gid = NEW.gid AND NEW.geom IS NOT NULL;
END;

DROP TRIGGER IF EXISTS comunidades_compute_fields_update_trigger;
CREATE TRIGGER comunidades_compute_fields_update_trigger
AFTER UPDATE ON comunidades
FOR EACH ROW BEGIN
	UPDATE comunidades SET x = X(NEW.geom), y = Y(NEW.geom) WHERE gid = NEW.gid AND NEW.geom IS NOT NULL;
END;



DROP TRIGGER IF EXISTS centros_educativos_compute_fields_insert_trigger;
CREATE TRIGGER centros_educativos_compute_fields_insert_trigger
AFTER INSERT ON centros_educativos
FOR EACH ROW BEGIN
	UPDATE centros_educativos SET x = X(NEW.geom), y = Y(NEW.geom) WHERE gid = NEW.gid AND NEW.geom IS NOT NULL;
END;

DROP TRIGGER IF EXISTS centros_educativos_compute_fields_update_trigger;
CREATE TRIGGER centros_educativos_compute_fields_update_trigger
AFTER UPDATE ON centros_educativos
FOR EACH ROW BEGIN
	UPDATE centros_educativos SET x = X(NEW.geom), y = Y(NEW.geom) WHERE gid = NEW.gid AND NEW.geom IS NOT NULL;
END;



DROP TRIGGER IF EXISTS centros_salud_compute_fields_insert_trigger;
CREATE TRIGGER centros_salud_compute_fields_insert_trigger
AFTER INSERT ON centros_salud
FOR EACH ROW BEGIN
	UPDATE centros_salud SET x = X(NEW.geom), y = Y(NEW.geom) WHERE gid = NEW.gid AND NEW.geom IS NOT NULL;
END;

DROP TRIGGER IF EXISTS centros_salud_compute_fields_update_trigger;
CREATE TRIGGER centros_salud_compute_fields_update_trigger
AFTER UPDATE ON centros_salud
FOR EACH ROW BEGIN
	UPDATE centros_salud SET x = X(NEW.geom), y = Y(NEW.geom) WHERE gid = NEW.gid AND NEW.geom IS NOT NULL;
END;



DROP TRIGGER IF EXISTS centros_reuniones_compute_fields_insert_trigger;
CREATE TRIGGER centros_reuniones_compute_fields_insert_trigger
AFTER INSERT ON centros_reuniones
FOR EACH ROW BEGIN
	UPDATE centros_reuniones SET x = X(NEW.geom), y = Y(NEW.geom) WHERE gid = NEW.gid AND NEW.geom IS NOT NULL;
END;

DROP TRIGGER IF EXISTS centros_reuniones_compute_fields_update_trigger;
CREATE TRIGGER centros_reuniones_compute_fields_update_trigger
AFTER UPDATE ON centros_reuniones
FOR EACH ROW BEGIN
	UPDATE centros_reuniones SET x = X(NEW.geom), y = Y(NEW.geom) WHERE gid = NEW.gid AND NEW.geom IS NOT NULL;
END;



DROP TRIGGER IF EXISTS viviendas_compute_fields_insert_trigger;
CREATE TRIGGER viviendas_compute_fields_insert_trigger
AFTER INSERT ON viviendas
FOR EACH ROW BEGIN
	UPDATE viviendas SET x = X(NEW.geom), y = Y(NEW.geom) WHERE gid = NEW.gid AND NEW.geom IS NOT NULL;
	UPDATE viviendas SET nom_com = (SELECT nom_com FROM comunidades WHERE cod_com = NEW.cod_com) WHERE gid = NEW.gid AND cod_com IS NOT NULL;
END;

DROP TRIGGER IF EXISTS viviendas_compute_fields_update_trigger;
CREATE TRIGGER viviendas_compute_fields_update_trigger
AFTER UPDATE ON viviendas
FOR EACH ROW BEGIN
	UPDATE viviendas SET x = X(NEW.geom), y = Y(NEW.geom) WHERE gid = NEW.gid AND NEW.geom IS NOT NULL;
	UPDATE viviendas SET nom_com = (SELECT nom_com FROM comunidades WHERE cod_com = NEW.cod_com) WHERE gid = NEW.gid AND cod_com IS NOT NULL;
END;



DROP TRIGGER IF EXISTS parcelas_compute_fields_insert_trigger;
CREATE TRIGGER parcelas_compute_fields_insert_trigger
AFTER INSERT ON parcelas
FOR EACH ROW BEGIN
	UPDATE parcelas SET nom_com = (SELECT nom_com FROM comunidades WHERE cod_com = NEW.cod_com) WHERE gid = NEW.gid AND cod_com IS NOT NULL;
END;

DROP TRIGGER IF EXISTS parcelas_compute_fields_update_trigger;
CREATE TRIGGER parcelas_compute_fields_update_trigger
AFTER UPDATE ON parcelas
FOR EACH ROW BEGIN
	UPDATE parcelas SET nom_com = (SELECT nom_com FROM comunidades WHERE cod_com = NEW.cod_com) WHERE gid = NEW.gid AND cod_com IS NOT NULL;
END;



DROP TRIGGER IF EXISTS fuentes_comunitarias_compute_fields_insert_trigger;
CREATE TRIGGER fuentes_comunitarias_compute_fields_insert_trigger
AFTER INSERT ON fuentes_comunitarias
FOR EACH ROW BEGIN
	UPDATE fuentes_comunitarias SET x = X(NEW.geom), y = Y(NEW.geom) WHERE gid = NEW.gid AND NEW.geom IS NOT NULL;
END;

DROP TRIGGER IF EXISTS fuentes_comunitarias_compute_fields_update_trigger;
CREATE TRIGGER fuentes_comunitarias_compute_fields_update_trigger
AFTER UPDATE ON fuentes_comunitarias
FOR EACH ROW BEGIN
	UPDATE fuentes_comunitarias SET x = X(NEW.geom), y = Y(NEW.geom) WHERE gid = NEW.gid AND NEW.geom IS NOT NULL;
END;



DROP TRIGGER IF EXISTS limites_parcela_comunitarias_compute_fields_insert_trigger;
CREATE TRIGGER limites_parcela_compute_fields_insert_trigger
AFTER INSERT ON limites_parcela
FOR EACH ROW BEGIN
	UPDATE limites_parcela SET x = X(NEW.geom), y = Y(NEW.geom) WHERE gid = NEW.gid AND NEW.geom IS NOT NULL;
END;

DROP TRIGGER IF EXISTS limites_parcela_compute_fields_update_trigger;
CREATE TRIGGER limites_parcela_compute_fields_update_trigger
AFTER UPDATE ON limites_parcela
FOR EACH ROW BEGIN
	UPDATE limites_parcela SET x = X(NEW.geom), y = Y(NEW.geom) WHERE gid = NEW.gid AND NEW.geom IS NOT NULL;
END;

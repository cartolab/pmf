CREATE OR REPLACE FUNCTION pmf.comunidades_compute_fields_trigger() RETURNS TRIGGER AS $comunidades_compute_fields_trigger$
    BEGIN
	IF (NEW.geom IS NOT NULL) THEN NEW.x = st_x(NEW.geom); NEW.y = st_y(NEW.geom); END IF;
    RETURN NEW;
    END;
$comunidades_compute_fields_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS comunidades_compute_fields_trigger ON pmf.comunidades;
CREATE TRIGGER comunidades_compute_fields_trigger
AFTER INSERT OR UPDATE OR DELETE ON pmf.comunidades
    FOR EACH ROW EXECUTE PROCEDURE pmf.comunidades_compute_fields_trigger();



CREATE OR REPLACE FUNCTION pmf.centros_educativos_compute_fields_trigger() RETURNS TRIGGER AS $centros_educativos_compute_fields_trigger$
    BEGIN
	IF (NEW.geom IS NOT NULL) THEN NEW.x = st_x(NEW.geom); NEW.y = st_y(NEW.geom); END IF;
    RETURN NEW;
    END;
$centros_educativos_compute_fields_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS centros_educativos_compute_fields_trigger ON pmf.centros_educativos;
CREATE TRIGGER centros_educativos_compute_fields_trigger
AFTER INSERT OR UPDATE OR DELETE ON pmf.centros_educativos
    FOR EACH ROW EXECUTE PROCEDURE pmf.centros_educativos_compute_fields_trigger();



CREATE OR REPLACE FUNCTION pmf.centros_salud_compute_fields_trigger() RETURNS TRIGGER AS $centros_salud_compute_fields_trigger$
    BEGIN
	IF (NEW.geom IS NOT NULL) THEN NEW.x = st_x(NEW.geom); NEW.y = st_y(NEW.geom); END IF;
    RETURN NEW;
    END;
$centros_salud_compute_fields_trigger$ LANGUAGE plpgsql;


DROP TRIGGER IF EXISTS centros_salud_compute_fields_trigger ON pmf.centros_salud;
CREATE TRIGGER centros_salud_compute_fields_trigger
AFTER INSERT OR UPDATE OR DELETE ON pmf.centros_salud
    FOR EACH ROW EXECUTE PROCEDURE pmf.centros_salud_compute_fields_trigger();



CREATE OR REPLACE FUNCTION pmf.centros_reuniones_compute_fields_trigger() RETURNS TRIGGER AS $centros_reuniones_compute_fields_trigger$
    BEGIN
	IF (NEW.geom IS NOT NULL) THEN NEW.x = st_x(NEW.geom); NEW.y = st_y(NEW.geom); END IF;
    RETURN NEW;
    END;
$centros_reuniones_compute_fields_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS centros_reuniones_compute_fields_trigger ON pmf.centros_reuniones;
CREATE TRIGGER centros_reuniones_compute_fields_trigger
AFTER INSERT OR UPDATE OR DELETE ON pmf.centros_reuniones
    FOR EACH ROW EXECUTE PROCEDURE pmf.centros_reuniones_compute_fields_trigger();



CREATE OR REPLACE FUNCTION pmf.viviendas_compute_fields_trigger() RETURNS TRIGGER AS $viviendas_compute_fields_trigger$
    BEGIN
	SELECT nombre INTO NEW.nom_com FROM pmf.comunidades WHERE cod_com = NEW.cod_com;
	IF (NEW.geom IS NOT NULL) THEN NEW.x = st_x(NEW.geom); NEW.y = st_y(NEW.geom); END IF;
    RETURN NEW;
    END;
$viviendas_compute_fields_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS viviendas_compute_fields_trigger ON pmf.viviendas;
CREATE TRIGGER viviendas_compute_fields_trigger
AFTER INSERT OR UPDATE OR DELETE ON pmf.viviendas
    FOR EACH ROW EXECUTE PROCEDURE pmf.viviendas_compute_fields_trigger();



CREATE OR REPLACE FUNCTION pmf.parcelas_compute_fields_trigger() RETURNS TRIGGER AS $parcelas_compute_fields_trigger$
    BEGIN
	SELECT nombre INTO NEW.nom_com FROM pmf.comunidades WHERE cod_com = NEW.cod_com;
    RETURN NEW;
    END;
$parcelas_compute_fields_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS parcelas_compute_fields_trigger ON pmf.parcelas;
CREATE TRIGGER parcelas_compute_fields_trigger
AFTER INSERT OR UPDATE OR DELETE ON pmf.parcelas
    FOR EACH ROW EXECUTE PROCEDURE pmf.parcelas_compute_fields_trigger();



CREATE OR REPLACE FUNCTION pmf.fuentes_comunitarias_compute_fields_trigger() RETURNS TRIGGER AS $fuentes_comunitarias_compute_fields_trigger$
    BEGIN
	IF (NEW.geom IS NOT NULL) THEN NEW.x = st_x(NEW.geom); NEW.y = st_y(NEW.geom); END IF;
    RETURN NEW;
    END;
$fuentes_comunitarias_compute_fields_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS fuentes_comunitarias_compute_fields_trigger ON pmf.fuentes_comunitarias;
CREATE TRIGGER fuentes_comunitarias_compute_fields_trigger
AFTER INSERT OR UPDATE OR DELETE ON pmf.fuentes_comunitarias
    FOR EACH ROW EXECUTE PROCEDURE pmf.fuentes_comunitarias_compute_fields_trigger();



CREATE OR REPLACE FUNCTION pmf.limites_parcela_compute_fields_trigger() RETURNS TRIGGER AS $limites_parcela_compute_fields_trigger$
    BEGIN
	IF (NEW.geom IS NOT NULL) THEN NEW.x = st_x(NEW.geom); NEW.y = st_y(NEW.geom); END IF;
    RETURN NEW;
    END;
$limites_parcela_compute_fields_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS limites_parcela_compute_fields_trigger ON pmf.limites_parcela;
CREATE TRIGGER limites_parcela_compute_fields_trigger
AFTER INSERT OR UPDATE OR DELETE ON pmf.limites_parcela
    FOR EACH ROW EXECUTE PROCEDURE pmf.limites_parcela_compute_fields_trigger();

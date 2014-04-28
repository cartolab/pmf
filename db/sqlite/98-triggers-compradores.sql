DROP TRIGGER IF EXISTS compradores_compute_fields_insert_trigger;
CREATE TRIGGER compradores_compute_fields_insert_trigger
AFTER INSERT ON compradores
FOR EACH ROW BEGIN
	UPDATE compradores SET
	       compra_total = (SELECT sum(ventas.total) FROM ventas WHERE ventas.cod_comprador = NEW.cod_comprador)
	WHERE gid = NEW.gid;

END;

DROP TRIGGER IF EXISTS compradores_compute_fields_update_trigger;
CREATE TRIGGER compradores_compute_fields_update_trigger
AFTER UPDATE ON compradores
FOR EACH ROW BEGIN
	UPDATE compradores SET
	       compra_total = (SELECT sum(ventas.total) FROM ventas WHERE ventas.cod_comprador = NEW.cod_comprador)
	WHERE gid = NEW.gid;

END;

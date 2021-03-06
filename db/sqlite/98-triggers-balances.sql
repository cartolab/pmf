DROP TRIGGER IF EXISTS balances_compute_fields_insert_trigger;
CREATE TRIGGER balances_compute_fields_insert_trigger
AFTER INSERT ON balances
FOR EACH ROW BEGIN
	UPDATE balances SET
	       cod_balance = NEW.cod_parcela || NEW.rubro || NEW.f_siembra,
	       -- gvsig doesn't send null when update
	       volumen_prod_kg = CASE WHEN NEW.rubro IN ('Aves Criollas', 'Huevos', 'Vacas', 'Cerdos', 'Cabras') THEN NULL ELSE NEW.volumen_prod_kg END,
	       volumen_prod_ud = CASE WHEN NEW.rubro IN ('Aves Criollas', 'Huevos', 'Vacas', 'Cerdos', 'Cabras') THEN NEW.volumen_prod_ud ELSE NULL END,
	       rendimiento_prod = 1.0 * (CASE WHEN NEW.rubro IN ('Aves Criollas', 'Huevos', 'Vacas', 'Cerdos', 'Cabras') THEN NEW.volumen_prod_ud ELSE NEW.volumen_prod_kg END) / NEW.area_prod,
	       mano_obra = NEW.establecimiento + NEW.manejo + NEW.cosecha + NEW.mano_obra_otros,
	       otros = NEW.transporte + NEW.alquiler + NEW.otros_otros,
	       empleo_temp_total = NEW.empleo_temp_hombre + NEW.empleo_temp_mujer
	WHERE gid = NEW.gid;

	UPDATE balances SET
	       coste_total = mano_obra + NEW.insumos + NEW.equipo + NEW.material + otros,
	       venta_total = (SELECT sum(ventas.total) FROM ventas WHERE ventas.cod_balance = balances.cod_balance)
	WHERE gid = NEW.gid;

	UPDATE balances SET
	       beneficio = venta_total - coste_total
	WHERE gid = NEW.gid;
END;


DROP TRIGGER IF EXISTS balances_compute_fields_update_trigger;
CREATE TRIGGER balances_compute_fields_update_trigger
AFTER UPDATE ON balances
FOR EACH ROW BEGIN
	UPDATE balances SET
	       cod_balance = NEW.cod_parcela || NEW.rubro || NEW.f_siembra,
	       -- gvsig doesn't send null when update
	       volumen_prod_kg = CASE WHEN NEW.rubro IN ('Aves Criollas', 'Huevos', 'Vacas', 'Cerdos', 'Cabras') THEN NULL ELSE NEW.volumen_prod_kg END,
	       volumen_prod_ud = CASE WHEN NEW.rubro IN ('Aves Criollas', 'Huevos', 'Vacas', 'Cerdos', 'Cabras') THEN NEW.volumen_prod_ud ELSE NULL END,
	       rendimiento_prod = 1.0 * (CASE WHEN NEW.rubro IN ('Aves Criollas', 'Huevos', 'Vacas', 'Cerdos', 'Cabras') THEN NEW.volumen_prod_ud ELSE NEW.volumen_prod_kg END) / NEW.area_prod,
	       mano_obra = NEW.establecimiento + NEW.manejo + NEW.cosecha + NEW.mano_obra_otros,
	       otros = NEW.transporte + NEW.alquiler + NEW.otros_otros,
	       empleo_temp_total = NEW.empleo_temp_hombre + NEW.empleo_temp_mujer
	WHERE gid = NEW.gid;

	UPDATE balances SET
	       coste_total = mano_obra + NEW.insumos + NEW.equipo + NEW.material + otros,
	       venta_total = (SELECT sum(ventas.total) FROM ventas WHERE ventas.cod_balance = balances.cod_balance)
	WHERE gid = NEW.gid;

	UPDATE balances SET
	       beneficio = venta_total - coste_total
	WHERE gid = NEW.gid;
END;

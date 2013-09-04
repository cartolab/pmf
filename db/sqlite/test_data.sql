INSERT INTO comunidades VALUES (1, '1', 'test1', '1', '1', 10, 10, 'false', 'false', 'false', 'false', NULL, NULL, NULL, GeomFromText('POINT (30 10)', 32616));
INSERT INTO comunidades VALUES (2, '2', 'test2', '1', '1', 10, 10, 'false', 'false', 'false', 'false', NULL, NULL, NULL, GeomFromText('POINT (80 60)', 32616));


INSERT INTO centros_educativos VALUES (1, '1', '1', 'test', 'Centro educativo escolar', 10, 5, NULL, 'false', NULL, NULL, NULL, GeomFromText('POINT (35 15)', 32616));


INSERT INTO centros_reuniones VALUES (1, '1', '1', 'test', 'test', 'test', NULL, NULL, NULL, GeomFromText('POINT (32 12)', 32616));


INSERT INTO centros_salud VALUES (1, '1', '1', 'test', 'true', 'false', 'true', 'false', NULL, NULL, NULL, NULL, NULL, GeomFromText('POINT (37 8)', 32616));


INSERT INTO organizaciones_base VALUES (1, '1', 'test', 'test', 'test');


INSERT INTO viviendas VALUES (1, '1', 'test1', '1', 'test', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, GeomFromText('POINT (28 14)', 32616));
INSERT INTO viviendas VALUES (2, '1', 'test1', '2', 'test', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, GeomFromText('POINT (32 14)', 32616));


INSERT INTO parcelas VALUES (1, '1', 'test1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, GeomFromText('POLYGON ((5 5), (10 5), (10 10), (5 10))', 32616));
INSERT INTO parcelas VALUES (2, '1', 'test1', '2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, GeomFromText('POLYGON ((15 15), (20 15), (20 20), (20 15))', 32616));


INSERT INTO cultivos VALUES (1, '1', 'Frijol', 1, 1, 1);


INSERT INTO presencia_institucional VALUES (1, '1', 'test1', 'test1', 'test1');
INSERT INTO presencia_institucional VALUES (2, '1', 'test2', 'test2', 'test2');


INSERT INTO limites_parcela VALUES (1, 'test', '1', 0, 0, 0, GeomFromText('POINT (50 30)', 32616));
INSERT INTO limites_parcela VALUES (2, 'test', '2', 0, 0, 0, GeomFromText('POINT (60 20)', 32616));


INSERT INTO fuentes_comunitarias VALUES (1, 'fc1', 0, 0, 0, GeomFromText('POINT (30 40)', 32616));
INSERT INTO fuentes_comunitarias VALUES (2, 'fc2', 0, 0, 0, GeomFromText('POINT (50 40)', 32616));

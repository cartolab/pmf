CREATE TABLE _map (
    mapa VARCHAR(255) NOT NULL,
    nombre_capa VARCHAR(255) NOT NULL,
    nombre_tabla VARCHAR(255),
    posicion integer DEFAULT 0 NOT NULL,
    visible VARCHAR(5) DEFAULT 'false',
    max_escala VARCHAR(50),
    min_escala VARCHAR(50),
    grupo VARCHAR,
    schema VARCHAR,
    localizador VARCHAR(5) DEFAULT 'false',
    CONSTRAINT _map_pkey PRIMARY KEY (mapa, nombre_capa)
);


CREATE TABLE _map_overview (
    mapa VARCHAR NOT NULL,
    nombre_capa VARCHAR NOT NULL,
    schema VARCHAR,
    posicion integer,
    nombre_tabla VARCHAR,
    CONSTRAINT _map_overview_pkey PRIMARY KEY (mapa, nombre_capa)
);


CREATE TABLE _map_overview_style (
    nombre_capa VARCHAR NOT NULL,
    nombre_estilo VARCHAR NOT NULL,
    tipo VARCHAR(3),
    definicion xml,
    CONSTRAINT _map_overview_style_pkey PRIMARY KEY (nombre_capa, nombre_estilo)
);


CREATE TABLE _map_style (
    nombre_capa VARCHAR NOT NULL,
    nombre_estilo VARCHAR NOT NULL,
    type VARCHAR(3),
    definicion xml,
    CONSTRAINT _map_style_pkey PRIMARY KEY (nombre_capa, nombre_estilo)
);



INSERT INTO _map VALUES ('PMF', 'centros_educativos', 'centros_educativos', 1, 'true', NULL, NULL, '', 'pmf', 'false');
INSERT INTO _map VALUES ('PMF', 'centros_reuniones', 'centros_reuniones', 2, 'true', NULL, NULL, '', 'pmf', 'false');
INSERT INTO _map VALUES ('PMF', 'centros_salud', 'centros_salud', 3, 'true', NULL, NULL, '', 'pmf', 'false');
INSERT INTO _map VALUES ('PMF', 'comunidades', 'comunidades', 4, 'true', NULL, NULL, '', 'pmf', 'false');
INSERT INTO _map VALUES ('PMF', 'parcelas', 'parcelas', 5, 'true', NULL, NULL, '', 'pmf', 'false');
INSERT INTO _map VALUES ('PMF', 'viviendas', 'viviendas', 6, 'true', NULL, NULL, '', 'pmf', 'false');
INSERT INTO _map VALUES ('PMF', 'limites_parcela', 'limites_parcela', 7, 'true', NULL, NULL, '', 'pmf', 'false');
INSERT INTO _map VALUES ('PMF', 'fuentes_comunitarias', 'fuentes_comunitarias', 8, 'true', NULL, NULL, '', 'pmf', 'false');


INSERT INTO _map_style VALUES ('fuentes_comunitarias', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="0,0,255,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="10.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="5"/>
    </xml-tag>
</xml-tag>
');
INSERT INTO _map_style VALUES ('limites_parcela', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="133,131,131,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="10.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="2"/>
    </xml-tag>
</xml-tag>
');
INSERT INTO _map_style VALUES ('viviendas', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="187,171,182,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="11.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="3"/>
    </xml-tag>
</xml-tag>
');
INSERT INTO _map_style VALUES ('parcelas', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="102,102,255,128"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value=""/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="1"/>
        <property key="unit" value="2"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="2"/>
            <property key="referenceSystem" value="1"/>
            <property key="color" value="102,102,255,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
</xml-tag>
');
INSERT INTO _map_style VALUES ('comunidades', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc" value="Default"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="255,0,0,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="8.0"/>
        <property key="outline" value="true"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="1"/>
        <property key="markerStyle" value="0"/>
        <property key="outlineColor" value="9,23,32,255"/>
        <property key="outlineSize" value="0.0"/>
    </xml-tag>
</xml-tag>
');
INSERT INTO _map_style VALUES ('centros_salud', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="0,153,0,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="8.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="1"/>
    </xml-tag>
</xml-tag>
');
INSERT INTO _map_style VALUES ('centros_reuniones', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="255,153,153,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="8.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="1"/>
    </xml-tag>
</xml-tag>
');
INSERT INTO _map_style VALUES ('centros_educativos', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="255,153,51,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="8.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="1"/>
    </xml-tag>
</xml-tag>
');

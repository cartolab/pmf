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



INSERT INTO _map VALUES
       ('PMF', 'compradores', 'compradores', 26, 'true', NULL, NULL, '', 'pmf', NULL),
       ('PMF', 'centros_educativos', 'centros_educativos', 25, 'true', NULL, NULL, '', 'pmf', NULL),
       ('PMF', 'centros_reuniones', 'centros_reuniones', 24, 'true', NULL, NULL, '', 'pmf', NULL),
       ('PMF', 'centros_salud', 'centros_salud', 23, 'true', NULL, NULL, '', 'pmf', NULL),
       ('PMF', 'comunidades', 'comunidades', 22, 'true', NULL, NULL, '', 'pmf', NULL),
       ('PMF', 'parcelas', 'parcelas', 21, 'true', NULL, NULL, '', 'pmf', NULL),
       ('PMF', 'informacion_general', 'informacion_general', 20, 'true', NULL, NULL, '', 'pmf', NULL),
       ('PMF', 'limites_parcela', 'limites_parcela', 19, 'true', NULL, NULL, '', 'pmf', NULL),
       ('PMF', 'fuentes_comunitarias', 'fuentes_comunitarias', 18, 'true', NULL, NULL, '', 'pmf', NULL),
       ('PMF', 'precipitacion1', 'precipitacion1', 17, 'true', NULL, NULL, 'Análisis OT', 'pmf', NULL),
       ('PMF', 'precipitacion', 'precipitacion', 16, 'true', NULL, NULL, 'Análisis OT', 'pmf', NULL),
       ('PMF', 'interes_turistico', 'interes_turistico', 15, 'true', NULL, NULL, 'Análisis OT', 'pmf', NULL),
       ('PMF', 'infraestructuras_economicas', 'infraestructuras_economicas', 14, 'true', NULL, NULL, 'Análisis OT', 'pmf', NULL),
       ('PMF', 'rios', 'rios', 13, 'true', NULL, NULL, 'C. Base', 'pmf', NULL),
       ('PMF', 'edificaciones', 'edificaciones', 12, 'true', NULL, NULL, 'C. Base', 'pmf', NULL),
       ('PMF', 'caminos', 'caminos', 11, 'true', NULL, NULL, 'C. Base', 'pmf', NULL),
       ('PMF', 'curvas', 'curvas', 10, 'true', NULL, NULL, 'C. Base', 'pmf', NULL),
       ('PMF', 'usos_suelo', 'usos_suelo', 9, 'true', NULL, NULL, 'Análisis OT área', 'pmf', NULL),
       ('PMF', 'sequia', 'sequia', 8, 'true', NULL, NULL, 'Análisis OT área', 'pmf', NULL),
       ('PMF', 'industrial', 'industrial', 7, 'true', NULL, NULL, 'Análisis OT área', 'pmf', NULL),
       ('PMF', 'sedimentos', 'sedimentos', 6, 'true', NULL, NULL, 'Análisis OT área', 'pmf', NULL),
       ('PMF', 'deforestacion', 'deforestacion', 5, 'true', NULL, NULL, 'Análisis OT área', 'pmf', NULL),
       ('PMF', 'riesgos', 'riesgos', 4, 'true', NULL, NULL, 'Análisis OT área', 'pmf', NULL),
       ('PMF', 'países vecinos', 'paises_vecinos', 3, 'true', NULL, NULL, 'C. Base área', 'pmf', NULL),
       ('PMF', 'municipios', 'municipioshn', 2, 'true', NULL, NULL, 'C. Base área', 'pmf', NULL),
       ('PMF', 'cuenca', 'cuenca', 1, 'true', NULL, NULL, 'C. Base área', 'pmf', NULL);


INSERT INTO _map_style VALUES
('informacion_general', 'PMF', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="157,157,157,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="4.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="2"/>
        <property key="referenceSystem" value="1"/>
        <property key="markerStyle" value="0"/>
    </xml-tag>
</xml-tag>
') ,
('parcelas', 'PMF', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="234,19,230,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc"/>
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
            <property key="color" value="128,128,128,255"/>
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
') ,
('comunidades', 'PMF', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="0,242,22,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="4.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="2"/>
        <property key="referenceSystem" value="1"/>
        <property key="markerStyle" value="0"/>
    </xml-tag>
</xml-tag>
') ,
('centros_salud', 'PMF', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="236,184,10,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="4.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="2"/>
        <property key="referenceSystem" value="1"/>
        <property key="markerStyle" value="0"/>
    </xml-tag>
</xml-tag>
') ,
('centros_reuniones', 'PMF', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="122,172,182,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="4.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="2"/>
        <property key="referenceSystem" value="1"/>
        <property key="markerStyle" value="0"/>
    </xml-tag>
</xml-tag>
') ,
('centros_educativos', 'PMF', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="123,141,120,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="4.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="2"/>
        <property key="referenceSystem" value="1"/>
        <property key="markerStyle" value="0"/>
    </xml-tag>
</xml-tag>
') ,
('infraestructuras_economicas', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="102,102,102,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="5.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="0"/>
    </xml-tag>
</xml-tag>
') ,
('rios', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="color" value="236,221,232,255"/>
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
') ,
('edificaciones', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc" value="Default"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="204,204,204,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="5.0"/>
        <property key="outline" value="true"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="1"/>
        <property key="outlineColor" value="45,45,38,255"/>
        <property key="outlineSize" value="0.0"/>
    </xml-tag>
</xml-tag>
') ,
('caminos', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value="Default"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="color" value="153,153,153,255"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
            <property key="desc"/>
            <property key="lineWidth" value="1.0"/>
            <property key="dashPhase" value="0.0"/>
            <property key="endCap" value="2"/>
            <property key="lineJoin" value="0"/>
            <property key="miterLimit" value="10.0"/>
            <property key="offset" value="0.0"/>
            <property key="unit" value="-1"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
') ,
('curvas', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value="Default"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="color" value="255,153,153,255"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
            <property key="desc"/>
            <property key="lineWidth" value="1.0"/>
            <property key="dashPhase" value="0.0"/>
            <property key="endCap" value="2"/>
            <property key="lineJoin" value="0"/>
            <property key="miterLimit" value="10.0"/>
            <property key="offset" value="0.0"/>
            <property key="unit" value="-1"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
') ,
('usos_suelo', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.VectorialUniqueValueLegend"/>
    <property key="fieldNames" value="uso"/>
    <property key="fieldTypes" value="12"/>
    <property key="ownOrder" value="false"/>
    <property key="orders" value=""/>
    <property key="colorScheme" value="0\,0\,0\,255 ,0\,84\,0\,255 ,0\,84\,0\,255 ,0\,168\,0\,255 ,0\,168\,0\,255 ,0\,255\,0\,255 ,0\,255\,0\,255 ,0\,255\,84\,255 ,0\,255\,84\,255 ,0\,255\,168\,255 ,0\,255\,168\,255 ,0\,255\,255\,255 ,0\,255\,255\,255 ,0\,0\,255\,255 ,0\,0\,255\,255 ,128\,0\,255\,255 ,128\,0\,255\,255 ,255\,0\,220\,255 ,255\,0\,220\,255 ,255\,0\,180\,255 ,255\,0\,180\,255 ,255\,0\,128\,255 ,255\,0\,128\,255 ,255\,0\,64\,255 ,255\,0\,64\,255 ,255\,0\,0\,255 ,255\,0\,0\,255 ,220\,190\,190\,255 ,223\,190\,190\,255 ,220\,220\,220\,255 ,220\,220\,220\,255 ,255\,255\,255\,255 ,255\,255\,255\,255"/>
    <property key="labelfield"/>
    <property key="labelFieldHeight"/>
    <property key="labelFieldRotation"/>
    <property key="useDefaultSymbol" value="false"/>
    <property key="numKeys" value="13"/>
    <property key="tipoValueKeys" value="com.hardcode.gdbms.engine.values.NullValue"/>
    <property key="keys" value="Acuicultura ,Agricultura ,Bosque Latifoliado ,Bosque de Mangle ,Estero ,Eucaliptos ,Industrial ,Laguna ,Matorrales ,Pecuario ,Salinera ,Suelos Desnudos ,Urbano"/>
    <property key="values" value="Acuicultura ,Agricultura ,Bosque Latifoliado ,Bosque de Mangle ,Estero ,Eucaliptos ,Industrial ,Laguna ,Matorrales ,Pecuario ,Salinera ,Suelos Desnudos ,Urbano"/>
    <property key="typeKeys" value="-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1"/>
    <property key="typeValues" value="-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="60,235,235,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Por defecto"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
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
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="0,255,255,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Acuicultura"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
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
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="0,255,0,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Agricultura"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
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
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="0,0,255,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Bosque Latifoliado"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
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
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="220,220,220,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Bosque de Mangle"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
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
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="0,168,0,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Estero"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
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
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="0,255,84,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Eucaliptos"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
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
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="0,255,168,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Industrial"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
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
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="255,0,0,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Laguna"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
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
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="0,255,168,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Matorrales"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
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
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="220,190,190,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Pecuario"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
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
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="255,0,180,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Salinera"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
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
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="0,84,0,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Suelos Desnudos"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
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
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="0,255,255,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Urbano"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
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
') ,
('sequia', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="244,244,111,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value=""/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="153,102,0,255"/>
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
') ,
('industrial', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="153,153,153,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value=""/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
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
') ,
('sedimentos', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="204,204,204,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value=""/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
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
') ,
('deforestacion', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="255,153,102,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value=""/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
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
') ,
('riesgos', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.VectorialUniqueValueLegend"/>
    <property key="fieldNames" value="Tipo"/>
    <property key="fieldTypes" value="12"/>
    <property key="ownOrder" value="false"/>
    <property key="orders" value=""/>
    <property key="colorScheme" value="0\,0\,0\,255 ,0\,84\,0\,255 ,0\,84\,0\,255 ,0\,168\,0\,255 ,0\,168\,0\,255 ,0\,255\,0\,255 ,0\,255\,0\,255 ,0\,255\,84\,255 ,0\,255\,84\,255 ,0\,255\,168\,255 ,0\,255\,168\,255 ,0\,255\,255\,255 ,0\,255\,255\,255 ,0\,0\,255\,255 ,0\,0\,255\,255 ,128\,0\,255\,255 ,128\,0\,255\,255 ,255\,0\,220\,255 ,255\,0\,220\,255 ,255\,0\,180\,255 ,255\,0\,180\,255 ,255\,0\,128\,255 ,255\,0\,128\,255 ,255\,0\,64\,255 ,255\,0\,64\,255 ,255\,0\,0\,255 ,255\,0\,0\,255 ,220\,190\,190\,255 ,223\,190\,190\,255 ,220\,220\,220\,255 ,220\,220\,220\,255 ,255\,255\,255\,255 ,255\,255\,255\,255"/>
    <property key="labelfield"/>
    <property key="labelFieldHeight"/>
    <property key="labelFieldRotation"/>
    <property key="useDefaultSymbol" value="false"/>
    <property key="numKeys" value="3"/>
    <property key="tipoValueKeys" value="com.hardcode.gdbms.engine.values.NullValue"/>
    <property key="keys" value="Deslizamiento ,Incendios ,Inundacion"/>
    <property key="values" value="Deslizamiento ,Incendios ,Inundacion"/>
    <property key="typeKeys" value="-1 ,-1 ,-1"/>
    <property key="typeValues" value="-1 ,-1 ,-1"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="60,235,235,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Por defecto"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
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
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="96,75,44,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Deslizamiento"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
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
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="255,153,0,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Incendios"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
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
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="51,51,255,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Inundacion"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
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
') ,
('cuenca', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="204,255,204,135"/>
        <property key="hasFill" value="true"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="102,102,0,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="3.0"/>
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
') ,
('municipios', 'PMF', 'gvl', '<?xml version="1.0" encoding="UTF-8"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <property key="labelFieldName"/>
    <property key="labelHeightFieldName"/>
    <property key="labelRotationFieldName"/>
    <property key="followHeaderEncoding" value="true"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.v02.FSymbol"/>
        <property key="m_symbolType" value="4"/>
        <property key="m_Style" value="1"/>
        <property key="m_useOutline" value="true"/>
        <property key="m_Color" value="255,255,204,205"/>
        <property key="m_outlineColor" value="0,0,0,205"/>
        <property key="m_bUseFontSize" value="true"/>
        <property key="m_bDrawShape" value="true"/>
        <property key="m_Size" value="2"/>
        <property key="m_Rotation" value="0"/>
        <property key="m_LinePattern" value="0"/>
        <property key="m_stroke" value="1.0"/>
        <property key="m_bUseSize" value="false"/>
        <property key="m_AlingVert" value="0"/>
        <property key="m_AlingHoriz" value="0"/>
        <property key="m_Descrip"/>
        <property key="rgb" value="-13882079"/>
    </xml-tag>
</xml-tag>
') ,
('paises_vecinos', 'PMF', 'gvl', '<?xml version="1.0" encoding="UTF-8"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <property key="labelFieldName" value="DEFINICION"/>
    <property key="labelHeightFieldName"/>
    <property key="labelRotationFieldName"/>
    <property key="followHeaderEncoding" value="true"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.v02.FSymbol"/>
        <property key="m_symbolType" value="4"/>
        <property key="m_Style" value="1"/>
        <property key="m_useOutline" value="true"/>
        <property key="m_Color" value="204,255,204,255"/>
        <property key="m_outlineColor" value="7,19,6,255"/>
        <property key="fontname" value="SansSerif"/>
        <property key="fontstyle" value="0"/>
        <property key="m_FontSize" value="14.0"/>
        <property key="m_FontColor" value="0,0,0,255"/>
        <property key="m_bUseFontSize" value="true"/>
        <property key="m_bDrawShape" value="true"/>
        <property key="m_Size" value="2"/>
        <property key="m_Rotation" value="0"/>
        <property key="m_LinePattern" value="0"/>
        <property key="m_stroke" value="1.0"/>
        <property key="m_bUseSize" value="false"/>
        <property key="m_AlingVert" value="0"/>
        <property key="m_AlingHoriz" value="0"/>
        <property key="m_Descrip"/>
        <property key="rgb" value="-16313594"/>
    </xml-tag>
</xml-tag>
') ,
('interes_turistico', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="0,102,102,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="6.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="0"/>
    </xml-tag>
</xml-tag>
') ,
('precipitacion', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="26,222,242,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="4.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="0"/>
    </xml-tag>
</xml-tag>
') ,
('precipitacion1', 'PMF', 'gvl', '<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="120,122,182,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="4.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="0"/>
    </xml-tag>
</xml-tag>
');

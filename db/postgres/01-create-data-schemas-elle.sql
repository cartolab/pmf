CREATE SCHEMA pmf;
ALTER SCHEMA pmf OWNER TO pmf;

CREATE SCHEMA dominios;
ALTER SCHEMA dominios OWNER TO pmf;

CREATE SCHEMA elle;
ALTER SCHEMA elle OWNER TO pmf;

SET search_path = elle, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;


CREATE TABLE _map (
    mapa character varying(255) NOT NULL,
    nombre_capa character varying(255) NOT NULL,
    nombre_tabla character varying(255),
    posicion integer DEFAULT 0 NOT NULL,
    visible boolean,
    max_escala character varying(50),
    min_escala character varying(50),
    grupo character varying,
    schema character varying,
    localizador boolean
);


ALTER TABLE elle._map OWNER TO pmf;


CREATE TABLE _map_overview (
    mapa character varying NOT NULL,
    nombre_capa character varying NOT NULL,
    schema character varying,
    posicion integer,
    nombre_tabla character varying
);


ALTER TABLE elle._map_overview OWNER TO pmf;


CREATE TABLE _map_overview_style (
    nombre_capa character varying NOT NULL,
    nombre_estilo character varying NOT NULL,
    tipo character varying(3),
    definicion xml
);


ALTER TABLE elle._map_overview_style OWNER TO pmf;


CREATE TABLE _map_style (
    nombre_capa character varying NOT NULL,
    nombre_estilo character varying NOT NULL,
    type character varying(3),
    definicion xml
);


ALTER TABLE elle._map_style OWNER TO pmf;


INSERT INTO _map (mapa, nombre_capa, nombre_tabla, posicion, visible, max_escala, min_escala, grupo, schema, localizador) VALUES ('PMF', 'centros_educativos', 'centros_educativos', 1, true, NULL, NULL, '', 'pmf', NULL);
INSERT INTO _map (mapa, nombre_capa, nombre_tabla, posicion, visible, max_escala, min_escala, grupo, schema, localizador) VALUES ('PMF', 'centros_reuniones', 'centros_reuniones', 2, true, NULL, NULL, '', 'pmf', NULL);
INSERT INTO _map (mapa, nombre_capa, nombre_tabla, posicion, visible, max_escala, min_escala, grupo, schema, localizador) VALUES ('PMF', 'centros_salud', 'centros_salud', 3, true, NULL, NULL, '', 'pmf', NULL);
INSERT INTO _map (mapa, nombre_capa, nombre_tabla, posicion, visible, max_escala, min_escala, grupo, schema, localizador) VALUES ('PMF', 'comunidades', 'comunidades', 4, true, NULL, NULL, '', 'pmf', NULL);
INSERT INTO _map (mapa, nombre_capa, nombre_tabla, posicion, visible, max_escala, min_escala, grupo, schema, localizador) VALUES ('PMF', 'parcelas', 'parcelas', 5, true, NULL, NULL, '', 'pmf', NULL);
INSERT INTO _map (mapa, nombre_capa, nombre_tabla, posicion, visible, max_escala, min_escala, grupo, schema, localizador) VALUES ('PMF', 'viviendas', 'viviendas', 6, true, NULL, NULL, '', 'pmf', NULL);
INSERT INTO _map (mapa, nombre_capa, nombre_tabla, posicion, visible, max_escala, min_escala, grupo, schema, localizador) VALUES ('PMF', 'fuentes_comunitarias', 'fuentes_comunitarias', 7, true, NULL, NULL, '', 'pmf', NULL);
INSERT INTO _map (mapa, nombre_capa, nombre_tabla, posicion, visible, max_escala, min_escala, grupo, schema, localizador) VALUES ('PMF', 'limites_parcela', 'limites_parcela', 8, true, NULL, NULL, '', 'pmf', NULL);



INSERT INTO _map_style (nombre_capa, nombre_estilo, type, definicion) VALUES ('viviendas', 'PMF', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
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
');
INSERT INTO _map_style (nombre_capa, nombre_estilo, type, definicion) VALUES ('parcelas', 'PMF', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
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
');
INSERT INTO _map_style (nombre_capa, nombre_estilo, type, definicion) VALUES ('comunidades', 'PMF', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
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
');
INSERT INTO _map_style (nombre_capa, nombre_estilo, type, definicion) VALUES ('centros_salud', 'PMF', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
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
');
INSERT INTO _map_style (nombre_capa, nombre_estilo, type, definicion) VALUES ('centros_reuniones', 'PMF', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
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
');
INSERT INTO _map_style (nombre_capa, nombre_estilo, type, definicion) VALUES ('centros_educativos', 'PMF', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
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
');



ALTER TABLE ONLY _map_overview
    ADD CONSTRAINT _map_overview_pkey PRIMARY KEY (mapa, nombre_capa);


ALTER TABLE ONLY _map_overview_style
    ADD CONSTRAINT _map_overview_style_pkey PRIMARY KEY (nombre_capa, nombre_estilo);


ALTER TABLE ONLY _map
    ADD CONSTRAINT _map_pkey PRIMARY KEY (mapa, nombre_capa);


ALTER TABLE ONLY _map_style
    ADD CONSTRAINT _map_style_pkey PRIMARY KEY (nombre_capa, nombre_estilo);


REVOKE ALL ON TABLE _map FROM PUBLIC;
REVOKE ALL ON TABLE _map FROM pmf;
GRANT ALL ON TABLE _map TO pmf;
GRANT SELECT ON TABLE _map TO PUBLIC;


REVOKE ALL ON TABLE _map_overview FROM PUBLIC;
REVOKE ALL ON TABLE _map_overview FROM pmf;
GRANT ALL ON TABLE _map_overview TO pmf;
GRANT SELECT ON TABLE _map_overview TO PUBLIC;


REVOKE ALL ON TABLE _map_style FROM PUBLIC;
REVOKE ALL ON TABLE _map_style FROM pmf;
GRANT ALL ON TABLE _map_style TO pmf;
GRANT SELECT ON TABLE _map_style TO PUBLIC;



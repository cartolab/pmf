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
    label xml,
    CONSTRAINT _map_overview_style_pkey PRIMARY KEY (nombre_capa, nombre_estilo)
);


CREATE TABLE _map_style (
    nombre_capa VARCHAR NOT NULL,
    nombre_estilo VARCHAR NOT NULL,
    type VARCHAR(3),
    definicion xml,
    label xml,
    CONSTRAINT _map_style_pkey PRIMARY KEY (nombre_capa, nombre_estilo)
);
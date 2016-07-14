#!/bin/bash

DATABASE="$1"
BASE_TABLES='viario_pmf curvas_nivel_10m_pmf rios_pmf caserios_comunidades_pmf oceano paises_limitrofes'
BASE_DATA=/tmp/98-base-data-pmf.sql

LIMITES_TABLES='departamentos municipios_vc aldeas_pmf'
LIMITES_DATA=/tmp/97-limites-administrativos-data-pmf.sql



process_data() {
    OUTFILE=$BASE_DATA # edit here
    TABLES=$BASE_TABLES # edit here
echo 'BEGIN TRANSACTION;' > $OUTFILE
echo '' >> $OUTFILE

for t in $TABLES ; do
    COLS=`sqlite3 $DATABASE "pragma table_info(${t})" | grep -v '|gid|' | grep -vi '|pk_uid|' | grep -vi '|shape_leng|' | grep -vi '|ogc_fid|' | cut -d'|' -f2`
    SELECT_COLS=`echo $COLS | sed -e 's/ /,/g'`
    INSERT_COLS=`echo $SELECT_COLS | sed -e 's/GEOMETRY/geom/'` # solo una vez
    INSERT_TABLE=$t

    if [[ ${t} == 'viario_pmf' ]] ; then
	INSERT_TABLE='carreteras'
    fi
    if [[ ${t} == 'curvas_nivel_10m_pmf' ]] ; then
	INSERT_TABLE='curvas_nivel_10m'
    fi
    if [[ ${t} == 'rios_pmf' ]] ; then
	INSERT_TABLE='rios'
    fi
    if [[ ${t} == 'caserios_comunidades_pmf' ]] ; then
	INSERT_TABLE='caserios_comunidades'
    fi
    if [[ ${t} == 'paises_limitrofes' ]] ; then
	INSERT_TABLE='paises_limitrofes'
    fi

    ## solo una vez
    if [[ ${t} == 'departamentos' ]] ; then
	SELECT_COLS="cod, depto, geometry"
	INSERT_COLS="cdpto, dpto, geom"
    fi
    if [[ ${t} == 'municipios_vc' ]] ; then
	SELECT_COLS="cod_muni, nombre, geometry"
	INSERT_COLS="cod_munic, munic, geom"
	INSERT_TABLE="municipios"
    fi
    if [[ ${t} == 'aldeas_pmf' ]] ; then
	SELECT_COLS="cod_aldea, nombre, geometry"
	INSERT_COLS="cod_canton, canton, geom"
	INSERT_TABLE="cantones"
    fi
    if [[ ${t} == 'oceano' ]] ; then
	continue
    fi
    # echo $COLS
    # echo $SELECT_COLS
    # echo $INSERT_COLS
    echo -e ".mode insert ${t}\nselect ${SELECT_COLS} from ${t};\n" | sqlite3 $DATABASE | sed "s/^INSERT INTO ${t}/INSERT INTO ${INSERT_TABLE} (${INSERT_COLS})/" >> $OUTFILE
done
echo '' >> $OUTFILE
echo 'COMMIT;' >> $OUTFILE
}

process_data

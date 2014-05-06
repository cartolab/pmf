#!/bin/sh

DB_PATH=../portable/common/cfg/pmf.sqlite

if [ -f "$DB_PATH" ] ; then
    rm $DB_PATH
fi

DEBUG=0
if [ $1 == "--debug" ] ; then
    DEBUG=1
fi
# Al usar el comando spatialite en lugar de sqlite no es necesario inicializar a mano el metadata
# spatialite -bail $DB_PATH "SELECT InitSpatialMetaData();"

for file in `ls ./sqlite/*.sql` ; do
    echo $file
    spatialite -bail $DB_PATH < $file
done

[ $DEBUG == 1 ] && spatialite -bail $DB_PATH < ../data-test/99-test-data.sql

data=./C_Base/

layer=aldeas_PMF
echo -e "\nProcesando $layer"
ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt PROMOTE_TO_MULTI $DB_PATH ${data}${layer}.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512

layer=areas_protegidas_2011_VC
echo -e "\nProcesando $layer"
ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt PROMOTE_TO_MULTI $DB_PATH ${data}${layer}.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512

layer=caserios_comunidades_PMF
echo -e "\nProcesando $layer"
ogr2ogr -append -progress -skipfailures -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt PROMOTE_TO_MULTI $DB_PATH ${data}${layer}.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512

layer=curvas_nivel_10m_PMF
echo -e "\nProcesando $layer"
ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt PROMOTE_TO_MULTI $DB_PATH ${data}${layer}.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512

layer=departamentos
echo -e "\nProcesando $layer"
ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt PROMOTE_TO_MULTI $DB_PATH ${data}${layer}.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512

layer=equipamientos_VC
echo -e "\nProcesando $layer"
ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt PROMOTE_TO_MULTI $DB_PATH ${data}${layer}.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512

layer=honduras
echo -e "\nProcesando $layer"
ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt PROMOTE_TO_MULTI $DB_PATH ${data}${layer}.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512

layer=municipios_VC
echo -e "\nProcesando $layer"
ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt PROMOTE_TO_MULTI $DB_PATH ${data}${layer}.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512

layer=oceano
echo -e "\nProcesando $layer"
ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt PROMOTE_TO_MULTI $DB_PATH ${data}${layer}.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512

layer=paises_limitrofes
echo -e "\nProcesando $layer"
ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt PROMOTE_TO_MULTI $DB_PATH ${data}${layer}.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512

layer=puertos_aeropuertos_VC
echo -e "\nProcesando $layer"
ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt PROMOTE_TO_MULTI $DB_PATH ${data}${layer}.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512

layer=paises_limitrofes
echo -e "\nProcesando $layer"
ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt PROMOTE_TO_MULTI $DB_PATH ${data}${layer}.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512

layer=rios_PMF
echo -e "\nProcesando $layer"
ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt PROMOTE_TO_MULTI $DB_PATH ${data}${layer}.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512

layer=usos_suelo_2003_PMF
echo -e "\nProcesando $layer"
ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt PROMOTE_TO_MULTI $DB_PATH ${data}${layer}.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512

layer=viario_PMF
echo -e "\nProcesando $layer"
ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt PROMOTE_TO_MULTI $DB_PATH ${data}${layer}.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512

layer=aldeas_PMF
echo -e "\nProcesando $layer"
ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt PROMOTE_TO_MULTI $DB_PATH ${data}${layer}.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512

sqlite3 $DB_PATH "VACUUM;"
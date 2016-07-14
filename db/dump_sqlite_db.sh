#!/bin/bash

# http://stackoverflow.com/questions/75675/how-do-i-dump-the-data-of-some-sqlite3-tables/37296788
# http://stackoverflow.com/questions/4199850/sqlite-export-with-column-names

TABLES='comunidades centros_educativos centros_salud centros_reuniones informacion_general pesca_capturas fuentes_comunitarias organizaciones_base presencia_institucional parcelas cultivos balances ventas limites_parcela compradores'

echo 'BEGIN TRANSACTION;' > /tmp/backup.sql
echo '' >> /tmp/backup.sql



for t in $TABLES ; do
    COLS=`sqlite3 $1 "pragma table_info(${t})" | grep -v '|gid|' | cut -d'|' -f2`
    SELECT_COLS=`echo $COLS | sed -e 's/ /,/g'`
    INSERT_COLS=$SELECT_COLS

    if [[ ${t} == 'preferencias_disenho' ]] ; then
	# No hay forma, racional, de desactivar triggers en sqlite y insertar en alternativas crea automaticamente entradas en preferencias
	# por lo que habría que hacer updates en lugar de inserts.
	echo 'DELETE FROM preferencias_disenho;' >> /tmp/backup.sql
    fi
    if [[ ${t} == 'presupuesto' ]] ; then
	echo 'DELETE FROM presupuesto;' >> /tmp/backup.sql
    fi

    if [[ ${t} == 'fuentes_implicadas' ]] ; then
	continue
    fi

    echo -e ".mode insert ${t}\nselect ${SELECT_COLS} from ${t};\n" | sqlite3 $1 | sed "s/^INSERT INTO ${t}/INSERT INTO ${t} (${INSERT_COLS})/" >> /tmp/backup.sql
done

echo '' >> /tmp/backup.sql
echo 'COMMIT;' >> /tmp/backup.sql

sed -i 's/Alucin/Aluzinc/' /tmp/backup.sql


# spatialite -bail fonsagua.sqlite < /tmp/backup.sql

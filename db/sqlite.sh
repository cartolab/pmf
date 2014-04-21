
DB_PATH=../pmf.sqlite

if [ -f "$DB_PATH" ] ; then
    rm $DB_PATH
fi

# Al usar el comando spatialite en lugar de sqlite no es necesario inicializar a mano el metadata
# spatialite -bail $DB_PATH "SELECT InitSpatialMetaData();"

for file in `ls ./sqlite/*.sql` ; do
    echo $file
    spatialite -bail $DB_PATH < $file
done

sqlite3 $DB_PATH "VACUUM;"
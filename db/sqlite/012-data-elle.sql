INSERT INTO "_map" VALUES('PMF','comunidades','comunidades',23,'1','50000.0',NULL,'Comunidad','',NULL);
INSERT INTO "_map" VALUES('PMF','centros_educativos','centros_educativos',22,'1','50000.0',NULL,'Comunidad','',NULL);
INSERT INTO "_map" VALUES('PMF','centros_reuniones','centros_reuniones',21,'1','50000.0',NULL,'Comunidad','',NULL);
INSERT INTO "_map" VALUES('PMF','centros_salud','centros_salud',20,'1','50000.0',NULL,'Comunidad','',NULL);
INSERT INTO "_map" VALUES('PMF','fuentes_comunitarias','fuentes_comunitarias',19,'1','50000.0',NULL,'Comunidad','',NULL);
INSERT INTO "_map" VALUES('PMF','informacion_general','informacion_general',18,'1','50000.0',NULL,'Actividad_productiva','',NULL);
INSERT INTO "_map" VALUES('PMF','compradores','compradores',17,'1','50000.0',NULL,'Actividad_productiva','',NULL);
INSERT INTO "_map" VALUES('PMF','limites_parcela','limites_parcela',16,'1','50000.0',NULL,'Actividad_productiva','',NULL);
INSERT INTO "_map" VALUES('PMF','parcelas','parcelas',15,'1','50000.0',NULL,'Actividad_productiva','',NULL);
INSERT INTO "_map" VALUES('PMF','carreteras','carreteras',13,'1','50000.0',NULL,'Infraestructuras','',NULL);
INSERT INTO "_map" VALUES('PMF','curvas_nivel_10m','curvas_nivel_10m',12,'1','30000.0',NULL,'Medio_fisico','',NULL);
INSERT INTO "_map" VALUES('PMF','rios','rios',11,'1','50000.0',NULL,'Medio_fisico','',NULL);
INSERT INTO "_map" VALUES('PMF','caserios_comunidades','caserios_comunidades',8,'1','30000.0',NULL,'Limites_administrativos','',NULL);
INSERT INTO "_map" VALUES('PMF','aldeas','cantones',6,'1','75000.0',NULL,'Limites_administrativos','',NULL);
INSERT INTO "_map" VALUES('PMF','municipios','municipios',5,'1','300000.0',NULL,'Limites_administrativos','',NULL);
INSERT INTO "_map" VALUES('PMF','departamentos','departamentos',4,'1',NULL,'200001.0','Limites_administrativos','',NULL);
INSERT INTO "_map" VALUES('PMF','paises_limitrofes','paises_limitrofes',3,'1',NULL,NULL,'Limites_administrativos','',NULL);
INSERT INTO "_map" VALUES('PMF','oceano','oceano',2,'1',NULL,NULL,'Limites_administrativos','',NULL);


INSERT INTO "_map_style" VALUES('comunidades','PMF','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="size" value="20.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="1"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,102,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="20.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="1"/>
            <property key="markerStyle" value="0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,255,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="-2.0"/>
            <property key="offsetY" value="3.0"/>
            <property key="size" value="8.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="1"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="153,153,153,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="-2.0"/>
            <property key="offsetY" value="-4.0"/>
            <property key="size" value="10.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="1"/>
            <property key="markerStyle" value="5"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="153,153,153,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="-4.0"/>
            <property key="offsetY" value="3.0"/>
            <property key="size" value="2.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="1"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,255,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="5.0"/>
            <property key="offsetY" value="2.0"/>
            <property key="size" value="4.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="1"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="153,153,153,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="5.0"/>
            <property key="offsetY" value="-2.0"/>
            <property key="size" value="6.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="1"/>
            <property key="markerStyle" value="5"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.styling.labeling.AttrInTableLabelingStrategy"/>
    <property key="labelingStrategy" value="labelingStrategy"/>
    <property key="TextField" value="nombre"/>
    <property key="fontSize" value="8"/>
    <property key="fontName" value="Arial"/>
    <property key="fontStyle" value="1"/>
    <property key="useFixedSize" value="true"/>
    <property key="useFixedColor" value="true"/>
    <property key="fixedColor" value="0,51,51,255"/>
    <property key="fixedSize" value="9.0"/>
    <property key="Unit" value="-1"/>
    <property key="referenceSystem" value="0"/>
</xml-tag>
');
INSERT INTO "_map_style" VALUES('centros_educativos','PMF','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="size" value="20.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="102,0,102,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="20.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="org.gvsig.symbology.fmap.symbols.CharacterMarkerSymbol"/>
            <property key="color" value="255,255,255,255"/>
            <property key="font" value="Agency FB"/>
            <property key="fontStyle" value="0"/>
            <property key="size" value="19.0"/>
            <property key="symbolCode" value="23"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="xOffset" value="0.0"/>
            <property key="yOffset" value="3.0"/>
            <property key="rotation" value="1.5707963267948966"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="org.gvsig.symbology.fmap.symbols.CharacterMarkerSymbol"/>
            <property key="color" value="255,255,255,255"/>
            <property key="font" value="Agency FB"/>
            <property key="fontStyle" value="0"/>
            <property key="size" value="9.0"/>
            <property key="symbolCode" value="97"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="xOffset" value="-2.0"/>
            <property key="yOffset" value="0.0"/>
            <property key="rotation" value="0.0"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="org.gvsig.symbology.fmap.symbols.CharacterMarkerSymbol"/>
            <property key="color" value="255,255,255,255"/>
            <property key="font" value="Agency FB"/>
            <property key="fontStyle" value="0"/>
            <property key="size" value="9.0"/>
            <property key="symbolCode" value="98"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="xOffset" value="2.0"/>
            <property key="yOffset" value="0.0"/>
            <property key="rotation" value="0.0"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="org.gvsig.symbology.fmap.symbols.CharacterMarkerSymbol"/>
            <property key="color" value="255,255,255,255"/>
            <property key="font" value="Agency FB"/>
            <property key="fontStyle" value="0"/>
            <property key="size" value="9.0"/>
            <property key="symbolCode" value="99"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="xOffset" value="6.0"/>
            <property key="yOffset" value="0.0"/>
            <property key="rotation" value="0.0"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.styling.labeling.AttrInTableLabelingStrategy"/>
    <property key="labelingStrategy" value="labelingStrategy"/>
    <property key="TextField" value="nom_cedu"/>
    <property key="fontSize" value="8"/>
    <property key="fontName" value="Arial"/>
    <property key="fontStyle" value="0"/>
    <property key="useFixedSize" value="true"/>
    <property key="useFixedColor" value="true"/>
    <property key="fixedColor" value="51,0,51,255"/>
    <property key="fixedSize" value="9.0"/>
    <property key="Unit" value="-1"/>
    <property key="referenceSystem" value="0"/>
</xml-tag>
');
INSERT INTO "_map_style" VALUES('centros_reuniones','PMF','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="size" value="20.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="204,102,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="20.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,255,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="-4.0"/>
            <property key="offsetY" value="-3.0"/>
            <property key="size" value="6.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,255,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="4.0"/>
            <property key="offsetY" value="-4.0"/>
            <property key="size" value="6.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="org.gvsig.symbology.fmap.symbols.CharacterMarkerSymbol"/>
            <property key="color" value="255,255,255,255"/>
            <property key="font" value="Agency FB"/>
            <property key="fontStyle" value="0"/>
            <property key="size" value="12.0"/>
            <property key="symbolCode" value="0"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="xOffset" value="-3.0"/>
            <property key="yOffset" value="4.0"/>
            <property key="rotation" value="0.0"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="org.gvsig.symbology.fmap.symbols.CharacterMarkerSymbol"/>
            <property key="color" value="255,255,255,255"/>
            <property key="font" value="Agency FB"/>
            <property key="fontStyle" value="0"/>
            <property key="size" value="12.0"/>
            <property key="symbolCode" value="0"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="xOffset" value="5.0"/>
            <property key="yOffset" value="4.0"/>
            <property key="rotation" value="0.0"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="org.gvsig.symbology.fmap.symbols.CharacterMarkerSymbol"/>
            <property key="color" value="255,255,255,255"/>
            <property key="font" value="Agency FB"/>
            <property key="fontStyle" value="0"/>
            <property key="size" value="12.0"/>
            <property key="symbolCode" value="47"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="xOffset" value="5.0"/>
            <property key="yOffset" value="4.0"/>
            <property key="rotation" value="0.0"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="org.gvsig.symbology.fmap.symbols.CharacterMarkerSymbol"/>
            <property key="color" value="255,255,255,255"/>
            <property key="font" value="Agency FB"/>
            <property key="fontStyle" value="0"/>
            <property key="size" value="12.0"/>
            <property key="symbolCode" value="47"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="xOffset" value="-5.0"/>
            <property key="yOffset" value="4.0"/>
            <property key="rotation" value="0.0"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.styling.labeling.AttrInTableLabelingStrategy"/>
    <property key="labelingStrategy" value="labelingStrategy"/>
    <property key="TextField" value="nom_creu"/>
    <property key="fontSize" value="8"/>
    <property key="fontName" value="Arial"/>
    <property key="fontStyle" value="0"/>
    <property key="useFixedSize" value="true"/>
    <property key="useFixedColor" value="true"/>
    <property key="fixedColor" value="102,51,0,255"/>
    <property key="fixedSize" value="9.0"/>
    <property key="Unit" value="-1"/>
    <property key="referenceSystem" value="0"/>
</xml-tag>
');
INSERT INTO "_map_style" VALUES('centros_salud','PMF','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="size" value="20.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="204,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="20.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,255,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="4.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="4.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,255,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="4.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,255,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="-4.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="4.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,255,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="4.0"/>
            <property key="size" value="4.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,255,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="-4.0"/>
            <property key="size" value="4.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.styling.labeling.AttrInTableLabelingStrategy"/>
    <property key="labelingStrategy" value="labelingStrategy"/>
    <property key="TextField" value="nom_csalud"/>
    <property key="fontSize" value="8"/>
    <property key="fontName" value="Arial"/>
    <property key="fontStyle" value="0"/>
    <property key="useFixedSize" value="true"/>
    <property key="useFixedColor" value="true"/>
    <property key="fixedColor" value="102,0,51,255"/>
    <property key="fixedSize" value="9.0"/>
    <property key="Unit" value="-1"/>
    <property key="referenceSystem" value="0"/>
</xml-tag>
');
INSERT INTO "_map_style" VALUES('fuentes_comunitarias','PMF','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="size" value="20.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,0,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="20.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="org.gvsig.symbology.fmap.symbols.CharacterMarkerSymbol"/>
            <property key="color" value="255,255,255,255"/>
            <property key="font" value="Agency FB"/>
            <property key="fontStyle" value="0"/>
            <property key="size" value="12.0"/>
            <property key="symbolCode" value="31"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="xOffset" value="0.0"/>
            <property key="yOffset" value="2.0"/>
            <property key="rotation" value="0.0"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="org.gvsig.symbology.fmap.symbols.CharacterMarkerSymbol"/>
            <property key="color" value="255,255,255,255"/>
            <property key="font" value="Agency FB"/>
            <property key="fontStyle" value="0"/>
            <property key="size" value="12.0"/>
            <property key="symbolCode" value="61"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="xOffset" value="3.0"/>
            <property key="yOffset" value="-3.0"/>
            <property key="rotation" value="0.0"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="org.gvsig.symbology.fmap.symbols.CharacterMarkerSymbol"/>
            <property key="color" value="255,255,255,255"/>
            <property key="font" value="Agency FB"/>
            <property key="fontStyle" value="0"/>
            <property key="size" value="12.0"/>
            <property key="symbolCode" value="42"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="xOffset" value="6.0"/>
            <property key="yOffset" value="0.0"/>
            <property key="rotation" value="0.0"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.styling.labeling.AttrInTableLabelingStrategy"/>
    <property key="labelingStrategy" value="labelingStrategy"/>
    <property key="TextField" value="gid"/>
    <property key="fontSize" value="8"/>
    <property key="fontName" value="Tahoma"/>
    <property key="fontStyle" value="0"/>
    <property key="useFixedSize" value="true"/>
    <property key="useFixedColor" value="true"/>
    <property key="fixedColor" value="0,0,0,255"/>
    <property key="fixedSize" value="10.0"/>
    <property key="Unit" value="-1"/>
    <property key="referenceSystem" value="0"/>
</xml-tag>
');
INSERT INTO "_map_style" VALUES('informacion_general','PMF','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="size" value="20.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="187,171,182,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="20.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,255,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="11.0"/>
            <property key="outline" value="true"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="3"/>
            <property key="outlineColor" value="0,0,0,255"/>
            <property key="outlineSize" value="0.0"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
','');
INSERT INTO "_map_style" VALUES('compradores','PMF','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="size" value="20.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="102,51,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="20.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="102,51,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="-3.0"/>
            <property key="size" value="6.0"/>
            <property key="outline" value="true"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
            <property key="outlineColor" value="255,255,255,255"/>
            <property key="outlineSize" value="0.0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,255,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="3.0"/>
            <property key="offsetY" value="1.0"/>
            <property key="size" value="7.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,255,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="-3.0"/>
            <property key="offsetY" value="1.0"/>
            <property key="size" value="7.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.styling.labeling.AttrInTableLabelingStrategy"/>
    <property key="labelingStrategy" value="labelingStrategy"/>
    <property key="TextField" value="nombre"/>
    <property key="fontSize" value="8"/>
    <property key="fontName" value="Arial"/>
    <property key="fontStyle" value="0"/>
    <property key="useFixedSize" value="true"/>
    <property key="useFixedColor" value="true"/>
    <property key="fixedColor" value="51,0,0,255"/>
    <property key="fixedSize" value="9.0"/>
    <property key="Unit" value="-1"/>
    <property key="referenceSystem" value="0"/>
</xml-tag>
');
INSERT INTO "_map_style" VALUES('limites_parcela','PMF','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="size" value="16.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,255,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="8.0"/>
            <property key="outline" value="true"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="0"/>
            <property key="outlineColor" value="0,0,0,255"/>
            <property key="outlineSize" value="0.0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="153,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="16.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="4"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
','');
INSERT INTO "_map_style" VALUES('parcelas','PMF','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="0,102,0,116"/>
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
            <property key="referenceSystem" value="1"/>
            <property key="color" value="51,102,0,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="dashArray" value="2.0,6.0,10.0,6.0,"/>
                <property key="lineWidth" value="2.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="1"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="-0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
</xml-tag>
','');

INSERT INTO "_map_style" VALUES('carreteras','PMF','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="color" value="255,153,153,255"/>
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
','');
INSERT INTO "_map_style" VALUES('curvas_nivel_10m','PMF','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.VectorialUniqueValueLegend"/>
    <property key="fieldNames" value="tipo"/>
    <property key="fieldTypes" value="12"/>
    <property key="ownOrder" value="false"/>
    <property key="orders" value=""/>
    <property key="colorScheme" value="12\,122\,12\,255 ,14\,124\,12\,255 ,17\,126\,13\,255 ,19\,129\,14\,255 ,22\,131\,14\,255 ,25\,133\,15\,255 ,28\,135\,16\,255 ,31\,137\,17\,255 ,34\,140\,17\,255 ,37\,142\,18\,255 ,40\,144\,19\,255 ,43\,146\,20\,255 ,46\,148\,20\,255 ,49\,151\,21\,255 ,53\,153\,22\,255 ,56\,155\,23\,255 ,59\,157\,24\,255 ,63\,159\,25\,255 ,66\,162\,25\,255 ,70\,164\,26\,255 ,74\,166\,27\,255 ,77\,168\,28\,255 ,81\,171\,29\,255 ,85\,173\,30\,255 ,89\,175\,31\,255 ,93\,177\,32\,255 ,96\,179\,33\,255 ,100\,182\,34\,255 ,104\,184\,35\,255 ,109\,186\,36\,255 ,113\,188\,37\,255 ,117\,190\,38\,255 ,121\,193\,39\,255 ,125\,195\,41\,255 ,130\,197\,42\,255 ,134\,199\,43\,255 ,138\,201\,44\,255 ,143\,204\,45\,255 ,147\,206\,46\,255 ,152\,208\,47\,255 ,156\,210\,49\,255 ,161\,213\,50\,255 ,166\,215\,51\,255 ,170\,217\,52\,255 ,175\,219\,54\,255 ,180\,221\,55\,255 ,185\,224\,56\,255 ,189\,226\,58\,255 ,194\,228\,59\,255 ,199\,230\,60\,255 ,204\,232\,62\,255 ,209\,235\,63\,255 ,214\,237\,64\,255 ,219\,239\,66\,255 ,224\,241\,67\,255 ,229\,243\,69\,255 ,234\,246\,70\,255 ,239\,248\,72\,255 ,244\,250\,73\,255 ,249\,252\,74\,255 ,255\,255\,76\,255 ,251\,248\,74\,255 ,247\,241\,72\,255 ,243\,235\,70\,255 ,240\,228\,68\,255 ,236\,222\,66\,255 ,232\,216\,64\,255 ,229\,209\,63\,255 ,225\,203\,61\,255 ,221\,197\,59\,255 ,218\,191\,57\,255 ,214\,185\,56\,255 ,210\,179\,54\,255 ,206\,173\,52\,255 ,203\,167\,51\,255 ,199\,161\,49\,255 ,195\,156\,47\,255 ,192\,150\,46\,255 ,188\,145\,44\,255 ,184\,139\,43\,255 ,181\,134\,41\,255 ,177\,129\,40\,255 ,173\,124\,38\,255 ,169\,119\,37\,255 ,166\,114\,35\,255 ,162\,109\,34\,255 ,158\,104\,33\,255 ,155\,99\,31\,255 ,151\,95\,30\,255 ,147\,90\,29\,255 ,144\,86\,28\,255 ,140\,81\,26\,255 ,136\,77\,25\,255 ,132\,73\,24\,255 ,129\,69\,23\,255 ,125\,65\,22\,255 ,121\,61\,21\,255 ,118\,57\,20\,255 ,114\,54\,19\,255 ,110\,50\,18\,255"/>
    <property key="labelfield"/>
    <property key="labelFieldHeight"/>
    <property key="labelFieldRotation"/>
    <property key="useDefaultSymbol" value="false"/>
    <property key="numKeys" value="2"/>
    <property key="tipoValueKeys" value="com.hardcode.gdbms.engine.values.NullValue"/>
    <property key="keys" value="maestra ,normal"/>
    <property key="values" value="maestra ,normal"/>
    <property key="typeKeys" value="-1 ,-1"/>
    <property key="typeValues" value="-1 ,-1"/>
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
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value="maestra"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="color" value="153,102,0,255"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
            <property key="desc"/>
            <property key="lineWidth" value="0.5"/>
            <property key="dashPhase" value="0.0"/>
            <property key="endCap" value="0"/>
            <property key="lineJoin" value="2"/>
            <property key="miterLimit" value="10.0"/>
            <property key="offset" value="0.0"/>
            <property key="unit" value="0"/>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value="normal"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="color" value="255,153,102,255"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
            <property key="desc"/>
            <property key="lineWidth" value="0.2"/>
            <property key="dashPhase" value="0.0"/>
            <property key="endCap" value="0"/>
            <property key="lineJoin" value="2"/>
            <property key="miterLimit" value="10.0"/>
            <property key="offset" value="0.0"/>
            <property key="unit" value="0"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
','');
INSERT INTO "_map_style" VALUES('rios','PMF','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="color" value="153,204,255,255"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
            <property key="desc"/>
            <property key="lineWidth" value="0.5"/>
            <property key="dashPhase" value="0.0"/>
            <property key="endCap" value="0"/>
            <property key="lineJoin" value="2"/>
            <property key="miterLimit" value="10.0"/>
            <property key="offset" value="0.0"/>
            <property key="unit" value="0"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
','');


INSERT INTO "_map_style" VALUES('caserios_comunidades','PMF','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="size" value="10.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,255,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="10.0"/>
            <property key="outline" value="true"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="0"/>
            <property key="outlineColor" value="0,0,0,255"/>
            <property key="outlineSize" value="0.0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="102,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="7.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="0"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.styling.labeling.AttrInTableLabelingStrategy"/>
    <property key="labelingStrategy" value="labelingStrategy"/>
    <property key="TextField" value="caserio"/>
    <property key="fontSize" value="8"/>
    <property key="fontName" value="Arial"/>
    <property key="fontStyle" value="0"/>
    <property key="useFixedSize" value="true"/>
    <property key="useFixedColor" value="true"/>
    <property key="fixedColor" value="51,0,0,255"/>
    <property key="fixedSize" value="8.0"/>
    <property key="Unit" value="-1"/>
    <property key="referenceSystem" value="0"/>
</xml-tag>
');

INSERT INTO "_map_style" VALUES('aldeas','PMF','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="133,136,136,255"/>
        <property key="hasFill" value="false"/>
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
            <property key="color" value="128,128,128,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="dashArray" value="3.0,2.0,"/>
                <property key="lineWidth" value="0.5"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="-0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
</xml-tag>
','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.styling.labeling.AttrInTableLabelingStrategy"/>
    <property key="labelingStrategy" value="labelingStrategy"/>
    <property key="TextField" value="nombre"/>
    <property key="fontSize" value="8"/>
    <property key="fontName" value="Arial"/>
    <property key="fontStyle" value="0"/>
    <property key="useFixedSize" value="true"/>
    <property key="useFixedColor" value="true"/>
    <property key="fixedColor" value="102,102,0,255"/>
    <property key="fixedSize" value="9.0"/>
    <property key="Unit" value="-1"/>
    <property key="referenceSystem" value="0"/>
</xml-tag>
');
INSERT INTO "_map_style" VALUES('municipios','PMF','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="255,255,204,255"/>
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
            <property key="color" value="51,51,0,255"/>
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
','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.styling.labeling.AttrInTableLabelingStrategy"/>
    <property key="labelingStrategy" value="labelingStrategy"/>
    <property key="TextField" value="nombre"/>
    <property key="fontSize" value="8"/>
    <property key="fontName" value="Arial"/>
    <property key="fontStyle" value="1"/>
    <property key="useFixedSize" value="true"/>
    <property key="useFixedColor" value="true"/>
    <property key="fixedColor" value="51,51,0,255"/>
    <property key="fixedSize" value="10.0"/>
    <property key="Unit" value="-1"/>
    <property key="referenceSystem" value="0"/>
</xml-tag>
');
INSERT INTO "_map_style" VALUES('departamentos','PMF','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="115,178,115,255"/>
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
            <property key="color" value="255,255,255,255"/>
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
','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.styling.labeling.AttrInTableLabelingStrategy"/>
    <property key="labelingStrategy" value="labelingStrategy"/>
    <property key="TextField" value="depto"/>
    <property key="fontSize" value="8"/>
    <property key="fontName" value="Arial"/>
    <property key="fontStyle" value="1"/>
    <property key="useFixedSize" value="true"/>
    <property key="useFixedColor" value="true"/>
    <property key="fixedColor" value="0,51,0,255"/>
    <property key="fixedSize" value="10.0"/>
    <property key="Unit" value="-1"/>
    <property key="referenceSystem" value="0"/>
</xml-tag>
');
INSERT INTO "_map_style" VALUES('paises_limitrofes','PMF','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="255,220,180,255"/>
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
            <property key="color" value="102,51,0,255"/>
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
','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.styling.labeling.AttrInTableLabelingStrategy"/>
    <property key="labelingStrategy" value="labelingStrategy"/>
    <property key="TextField" value="pais"/>
    <property key="fontSize" value="8"/>
    <property key="fontName" value="Arial"/>
    <property key="fontStyle" value="1"/>
    <property key="useFixedSize" value="true"/>
    <property key="useFixedColor" value="true"/>
    <property key="fixedColor" value="102,102,102,255"/>
    <property key="fixedSize" value="10.0"/>
    <property key="Unit" value="-1"/>
    <property key="referenceSystem" value="0"/>
</xml-tag>
');
INSERT INTO "_map_style" VALUES('oceano','PMF','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="153,204,255,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="false"/>
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
','');

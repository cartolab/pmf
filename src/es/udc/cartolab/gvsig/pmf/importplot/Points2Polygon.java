package es.udc.cartolab.gvsig.pmf.importplot;

import com.iver.cit.gvsig.fmap.core.IFeature;
import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.iver.cit.gvsig.fmap.core.v02.FConverter;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.ReadableVectorial;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;

public class Points2Polygon {

    public static IGeometry convexHull(FLyrVect pointLayer) throws Exception {

	ReadableVectorial readableVectorial = pointLayer.getSource();
	Geometry[] jtsPointGeoms = new Geometry[readableVectorial
		.getShapeCount()];

	for (int i = 0; i < readableVectorial.getShapeCount(); i++) {
	    IFeature iFeature = readableVectorial.getFeature(i);
	    IGeometry gvPointGeom = iFeature.getGeometry();
	    jtsPointGeoms[i] = gvPointGeom.toJTSGeometry();
	}

	GeometryFactory geomFactory = new GeometryFactory();
	GeometryCollection geomCollection = geomFactory
		.createGeometryCollection(jtsPointGeoms);

	Geometry convexHull = geomCollection.convexHull();

	IGeometry gvPolygonGeom = FConverter.jts_to_igeometry(convexHull);

	return gvPolygonGeom;

    }
}
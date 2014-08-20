package es.udc.cartolab.gvsig.pmf.importplot;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.cresques.cts.ICoordTrans;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.cit.gvsig.fmap.core.IFeature;
import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.iver.cit.gvsig.fmap.core.v02.FConverter;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.ReadableVectorial;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryCollection;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;

public class Points2Polygon {

    public static IGeometry convexHull(FLyrVect pointLayer)
	    throws ReadDriverException {

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

    // TODO: Index of the field and the filter is hardcoded. This method sould
    // rececive an interface that has all the information about the filter
    /**
     * @throws InvalidInputDataException
     */
    public static IGeometry toPolygon(FLyrVect pointLayer, String filter)
	    throws ReadDriverException, InvalidInputDataException {

	ICoordTrans ct = null;
	if (pointLayer.getProjection() != pointLayer.getMapContext()
		.getProjection()) {
	    ct = pointLayer.getProjection().getCT(
		    pointLayer.getMapContext().getProjection());
	}

	ReadableVectorial readableVectorial = pointLayer.getSource();
	// Geometry[] jtsPointGeoms = new Geometry[readableVectorial
	// .getShapeCount()];

	SortedMap<String, Geometry> jtsPointGeoms = new TreeMap<String, Geometry>();
	String realFilter = filter.toUpperCase() + "V";

	for (int i = 0; i < readableVectorial.getShapeCount(); i++) {
	    IFeature iFeature = readableVectorial.getFeature(i);
	    String attribute = iFeature.getAttribute(0).toString();
	    String key = attribute.toUpperCase();
	    if (key.startsWith(realFilter)) {
		IGeometry gvPointGeom = iFeature.getGeometry();
		if (ct != null) {
		    gvPointGeom.reProject(ct);
		}
		jtsPointGeoms.put(key, gvPointGeom.toJTSGeometry());
	    }
	}

	if (jtsPointGeoms.size() < 4) {
	    throw new InvalidInputDataException(
		    String.format(
			    "El número de vértices de la parcela\n %s \n debe ser igual o superior a cuatro y sólo tiene %d",
			    filter, jtsPointGeoms.size()));
	}

	GeometryFactory fact = new GeometryFactory();
	List<Coordinate> a = new ArrayList<Coordinate>();

	for (Geometry g : jtsPointGeoms.values()) {
	    a.add(g.getCoordinate());
	}

	// To close the ring
	a.add(a.get(0));

	LinearRing linear = fact.createLinearRing(a.toArray(new Coordinate[0]));
	Polygon poly = new Polygon(linear, null, fact);

	IGeometry gvPolygonGeom = FConverter.jts_to_igeometry(poly);

	return gvPolygonGeom;
    }
}
package es.udc.cartolab.gvsig.pmf.importer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import org.cresques.cts.IProjection;

import com.iver.cit.gvsig.fmap.ViewPort;
import com.iver.cit.gvsig.fmap.core.FPoint2D;
import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.iver.cit.gvsig.fmap.core.SymbologyFactory;
import com.iver.cit.gvsig.fmap.core.symbols.ISymbol;
import com.iver.cit.gvsig.fmap.core.symbols.SimpleTextSymbol;
import com.iver.cit.gvsig.fmap.core.v02.FConverter;
import com.iver.cit.gvsig.fmap.crs.CRSFactory;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.utiles.swing.threads.Cancellable;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;

import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;

@SuppressWarnings("serial")
public class PlotPanel extends JPanel {

    private static final IProjection CRS = CRSFactory.getCRS("EPSG:32616");
    private Map<String, IGeometry> points;
    private IGeometry p;

    public PlotPanel(Map<String, IGeometry> points) {
	this.points = points;
	p = points2polygon(points);
    }

    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;
	ViewPort viewPort = new ViewPort(CRS);
	viewPort.setImageSize(new Dimension(250, 250));
	viewPort.setExtent(p.getBounds2D());

	ISymbol symbol = SymbologyFactory.createDefaultFillSymbol();
	TOCLayerManager toc = new TOCLayerManager();
	FLyrVect layer = toc.getLayerByName("parcelas");
	if (layer != null) {
	    symbol = layer.getLegend().getDefaultSymbol();
	}

	p.draw(g2, viewPort, symbol);
	drawLabel(g2, points, viewPort);
    }

    @Override
    public Dimension getPreferredSize() {
	return new Dimension(500, 500);
    }

    public void repaintIt(Map<String, IGeometry> pointsReordered) {
	this.p = points2polygon(pointsReordered);
	this.invalidate();
	this.repaint();
    }

    private IGeometry points2polygon(Map<String, IGeometry> pointGeoms) {

	GeometryFactory fact = new GeometryFactory();
	List<Coordinate> a = new ArrayList<Coordinate>();

	for (String s : pointGeoms.keySet()) {
	    Geometry g = pointGeoms.get(s).toJTSGeometry();
	    a.add(g.getCoordinate());
	}

	// To close the ring
	a.add(a.get(0));

	LinearRing linear = fact.createLinearRing(a.toArray(new Coordinate[0]));
	Polygon poly = new Polygon(linear, null, fact);

	IGeometry gvPolygonGeom = FConverter.jts_to_igeometry(poly);

	return gvPolygonGeom;
    }

    private void drawLabel(Graphics2D g, Map<String, IGeometry> points,
	    ViewPort viewPort) {
	SimpleTextSymbol sym = new SimpleTextSymbol();
	// sym.setFont(getFont());
	// sym.setTextColor(color);
	// sym.setUnit(unit);
	// sym.setReferenceSystem(referenceSystem);
	// sym.setDrawWithHalo(isWithHalo());
	// if (isWithHalo()) {
	// sym.setHaloWidth(getHaloWidth());
	// sym.setHaloColor(getHaloColor());
	// }

	// double size = sym.getFont().getSize();
	// double _dpi = 10;
	// size = CartographicSupportToolkit.getCartographicLength(this, size,
	// viewPort, _dpi);
	double size = 12;
	sym.setFontSize(size);
	// sym.setRotation(rotation);
	for (String id : points.keySet()) {
	    // FLabel[] aux = geom.createLabels(0, true);

	    sym.setText(id);
	    // FPoint2D p = new FPoint2D(aux[j].getOrig());
	    FPoint2D shp = (FPoint2D) points.get(id).getInternalShape();
	    FPoint2D point = (FPoint2D) shp.cloneFShape();
	    // p = geom.getInternalShape()
	    point.transform(viewPort.getAffineTransform());

	    sym.draw(g, null, point, new Cancellable() {
		@Override
		public void setCanceled(boolean canceled) {
		}

		@Override
		public boolean isCanceled() {
		    return false;
		}
	    });
	}
    }

    public IGeometry getPoly() {
	return p;
    }

}

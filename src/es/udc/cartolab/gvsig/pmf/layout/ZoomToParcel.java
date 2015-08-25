package es.udc.cartolab.gvsig.pmf.layout;

import java.awt.geom.Rectangle2D;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.hardcode.gdbms.driver.exceptions.InitializeDriverException;
import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.hardcode.gdbms.engine.values.Value;
import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.fmap.MapControl;
import com.iver.cit.gvsig.fmap.ViewPort;
import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.iver.cit.gvsig.fmap.layers.CancelationException;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.LayerDrawEvent;
import com.iver.cit.gvsig.fmap.layers.LayerDrawingListener;
import com.iver.cit.gvsig.fmap.layers.ReadableVectorial;
import com.iver.cit.gvsig.fmap.layers.layerOperations.AlphanumericData;
import com.iver.cit.gvsig.project.documents.view.gui.View;

import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.pmf.forms.ParcelasForm;

public class ZoomToParcel implements LayerDrawingListener {

    private static final Logger logger = Logger.getLogger(ZoomToParcel.class);

    public static final int SCALE = 500;

    private MapControl mapControl;

    private Runnable runnable;
    private boolean drawed;
    private boolean zoomed;

    public ZoomToParcel() {
	View view = (View) PluginServices.getMDIManager().getActiveWindow();
	this.mapControl = view.getMapControl();
    }

    public ZoomToParcel(MapControl mapControl) {
	this.mapControl = mapControl;
    }

    public Rectangle2D zoom(String housingCode) {
	Rectangle2D plotsGeom = getGeometry(housingCode);
	Rectangle2D rectangle = zoomTo(plotsGeom);
	mapControl.getMapContext().setScaleView(SCALE);
	return rectangle;
    }

    private Rectangle2D getGeometry(String housingCode) {
	FLyrVect layer = getEnvelopeConstantLayer();

	Rectangle2D plotsBound = null;
	if (layer instanceof AlphanumericData) {
	    ReadableVectorial source = (layer).getSource();
	    try {
		int attIndex = layer.getRecordset().getFieldIndexByName(
			ParcelasForm.CODVIV);
		source.start();
		int nrows = source.getShapeCount();
		for (int i = 0; i < nrows; i++) {
		    Value attribute = source.getFeature(i).getAttribute(
			    attIndex);
		    if (attribute.toString().equals(housingCode)) {
			IGeometry plotGeom = source.getShape(i);
			/*
			 * fix to avoid zoom problems when layer and view
			 * projections aren't the same.
			 */
			if ((layer.getCoordTrans() != null)
				&& (plotGeom != null)) {
			    plotGeom.reProject(layer.getCoordTrans());
			}
			if (plotsBound == null) {
			    plotsBound = plotGeom.getBounds2D();
			} else {
			    plotsBound.add(plotGeom.getBounds2D());
			}
		    }
		}
	    } catch (InitializeDriverException e) {
		logger.error(e.getStackTrace(), e);
	    } catch (ReadDriverException e) {
		logger.error(e.getStackTrace(), e);
	    } finally {
		try {
		    source.stop();
		} catch (ReadDriverException e) {
		    logger.error(e.getStackTrace(), e);
		}
	    }
	}
	return plotsBound;
    }

    private FLyrVect getEnvelopeConstantLayer() {
	return new TOCLayerManager(mapControl)
		.getLayerByName(ParcelasForm.NAME);
    }

    private Rectangle2D zoomTo(Rectangle2D rectangle) {
	if (rectangle.getWidth() < 200) {
	    rectangle.setFrameFromCenter(rectangle.getCenterX(),
		    rectangle.getCenterY(), rectangle.getCenterX() + 100,
		    rectangle.getCenterY() + 100);
	}
	if (rectangle != null) {
	    ViewPort viewPort = mapControl.getMapContext().getViewPort();
	    viewPort.setExtent(rectangle);
	    viewPort.refreshExtent();
	}
	this.zoomed = true;
	return rectangle;
    }

    public void onFinishZoom(Runnable runnable) {
	mapControl.getMapContext().addLayerDrawingListener(this);
	this.runnable = runnable;
	this.drawed = false;

    }

    @Override
    public void beforeLayerDraw(LayerDrawEvent e) throws CancelationException {
    }

    @Override
    public void afterLayerDraw(LayerDrawEvent e) throws CancelationException {
    }

    @Override
    public void beforeGraphicLayerDraw(LayerDrawEvent e)
	    throws CancelationException {
    }

    @Override
    public void afterLayerGraphicDraw(LayerDrawEvent e)
	    throws CancelationException {
	mapControl.getMapContext().removeLayerDrawListener(this);
	if ((zoomed) && (!drawed)) {
	    drawed = true;
	    SwingUtilities.invokeLater(runnable);
	}
    }

    public Rectangle2D getExtent() {
	return null;
    }

}

// Capa y posición
//
// Geometría
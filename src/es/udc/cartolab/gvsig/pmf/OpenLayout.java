package es.udc.cartolab.gvsig.pmf;

import java.awt.geom.Rectangle2D;
import java.io.File;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.cit.gvsig.fmap.MapContext;
import com.iver.cit.gvsig.fmap.layers.CancelationException;
import com.iver.cit.gvsig.fmap.layers.LayerDrawEvent;
import com.iver.cit.gvsig.fmap.layers.LayerDrawingListener;
import com.iver.cit.gvsig.project.documents.layout.fframes.FFrameView;
import com.iver.cit.gvsig.project.documents.layout.fframes.IFFrame;
import com.iver.cit.gvsig.project.documents.layout.gui.Layout;
import com.iver.cit.gvsig.project.documents.view.gui.View;
import com.iver.utiles.FileUtils;

import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.udc.cartolab.gvsig.pmf.layout.ExternalError;
import es.udc.cartolab.gvsig.pmf.layout.LayoutWrapper;
import es.udc.cartolab.gvsig.pmf.utils.PmfConstants;

public class OpenLayout extends Extension implements LayerDrawingListener {

    private static String templatePath = FileUtils.getAppHomeDir()
	    + PmfConstants.TEMPLATE_FILE;
    private LayoutWrapper lw;
    private MapContext mapContext;
    private boolean zoomed;
    private boolean drawed;

    private static final Logger logger = Logger.getLogger(OpenLayout.class);
    private Rectangle2D rectangle;

    @Override
    public void initialize() {
    }

    @Override
    public void execute(String actionCommand) {
	zoomed = false;
	drawed = false;
	SelectHousingLayoutDialog dialog = new SelectHousingLayoutDialog();
	PluginServices.getMDIManager().addCentredWindow(dialog);
	String housingCode = dialog.getHousingCode();
	View view = (View) PluginServices.getMDIManager().getActiveWindow();
	mapContext = view.getMapControl().getMapContext();
	lw = new LayoutWrapper(mapContext);
	mapContext.addLayerDrawingListener(this);
	rectangle = lw.zoomTo(housingCode);
	zoomed = true;
    }

    @Override
    public boolean isEnabled() {
	return FormFactory.allLayersLoadedRegistered();
    }

    @Override
    public boolean isVisible() {
	return true;
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
	mapContext.removeLayerDrawListener(this);
	if ((zoomed) && (!drawed)) {
	    drawed = true;
	    SwingUtilities.invokeLater(new Runnable() {

		@Override
		public void run() {
		    try {
			Layout layout = lw.getLayoutFromTemplate(new File(
				templatePath));

			// layout.commandRefresh();
			// layout.commandRepaint();
			lw.openLayoutWindow(layout, "PMF");
			IFFrame[] fFrames = layout.getLayoutContext()
				.getFFrames();
			for (IFFrame fFrame : fFrames) {
			    if (fFrame instanceof FFrameView) {
				FFrameView frameView = (FFrameView) fFrame;
				frameView.setNewExtent(rectangle);
				// frameView.setLinked(false);
				// frameView.refresh();
			    }
			}
		    } catch (ExternalError ex) {
			logger.error(ex.getStackTrace(), ex);
		    }
		}
	    });
	}
    }

}

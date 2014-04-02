package es.udc.cartolab.gvsig.pmf.layout;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

import org.apache.log4j.Logger;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

import com.hardcode.gdbms.driver.exceptions.InitializeDriverException;
import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.hardcode.gdbms.engine.values.Value;
import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.ProjectExtension;
import com.iver.cit.gvsig.fmap.MapContext;
import com.iver.cit.gvsig.fmap.ViewPort;
import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.ReadableVectorial;
import com.iver.cit.gvsig.fmap.layers.layerOperations.AlphanumericData;
import com.iver.cit.gvsig.project.Project;
import com.iver.cit.gvsig.project.ProjectFactory;
import com.iver.cit.gvsig.project.documents.exceptions.OpenException;
import com.iver.cit.gvsig.project.documents.layout.ProjectMap;
import com.iver.cit.gvsig.project.documents.layout.gui.Layout;
import com.iver.utiles.XMLEntity;
import com.iver.utiles.xml.XMLEncodingUtils;
import com.iver.utiles.xmlEntity.generate.XmlTag;

import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.pmf.SelectPlotLayoutDialog;

public class LayoutWrapper {
    private static final int SCALE = 500;

    private static Logger logger = Logger
	    .getLogger(SelectPlotLayoutDialog.class);

    private MapContext mapContext;

    public LayoutWrapper(MapContext mapContext) {
	this.mapContext = mapContext;
    }

    /**
     * 
     * The paths to the logos and north sign in pmf.gvt should be relative to
     * andami.jar
     * 
     * @param file
     *            file that contains the gvsig layout template
     * @return
     * @return a Layout object of the serialized file template
     * @throws AssertionError
     *             if file doesn't exists
     * @throws ExternalError
     *             if there is any kind of error processing file
     */
    public Layout getLayoutFromTemplate(File file) {
	Project project = ((ProjectExtension) PluginServices
		.getExtension(ProjectExtension.class)).getProject();
	FileInputStream is = null;
	Reader reader = null;
	Layout layout = null;
	try {
	    is = new FileInputStream(file);
	    reader = XMLEncodingUtils.getReader(is);
	    XmlTag tag = (XmlTag) XmlTag.unmarshal(reader);
	    XMLEntity xml = new XMLEntity(tag);
	    layout = Layout.createLayout(xml, project);
	} catch (FileNotFoundException e) {
	    throw new AssertionError(
		    "File not found - This should never happen");
	} catch (MarshalException e) {
	    logger.error(e.getStackTrace(), e);
	} catch (ValidationException e) {
	    logger.error(e.getStackTrace(), e);
	} catch (OpenException e) {
	    e.printStackTrace();
	    logger.error(e.getMessage());
	} finally {
	    try {
		if (reader != null) {
		    reader.close();
		}
		if (is != null) {
		    is.close();
		}
	    } catch (IOException e) {
		logger.error(e.getStackTrace(), e);
	    }
	}
	return layout;
    }

    /**
     * Adds the layout to the project documents and opens the layout window
     * 
     * @param layout
     *            the layout to open
     * @param layoutName
     *            the identifier of the layout, and also the name of the window
     */
    public void openLayoutWindow(Layout layout, String layoutName) {
	Project project = ((ProjectExtension) PluginServices
		.getExtension(ProjectExtension.class)).getProject();
	ProjectMap pmap = ProjectFactory.createMap(layoutName);
	pmap.setModel(layout);
	pmap.getModel().setProjectMap(pmap);
	project.addDocument(pmap);
	PluginServices.getMDIManager().addWindow(layout);
    }

    private void centerLayout(Layout layout) {
	layout.getLayoutControl().getLayoutZooms();
	layout.getLayoutControl().getLayoutFunctions();
    }

    public Rectangle2D zoomTo(String plotCode) {
	IGeometry plotGeom = getGeometry(plotCode);
	Rectangle2D rectangle = zoomTo(plotGeom);
	mapContext.setScaleView(SCALE);
	return rectangle;
    }

    private IGeometry getGeometry(String plotCode) {
	FLyrVect layer = new TOCLayerManager().getLayerByName("parcelas");
	if (layer instanceof AlphanumericData) {
	    ReadableVectorial source = (layer).getSource();
	    try {
		source.start();
		int nrows = source.getShapeCount();
		for (int i = 0; i < nrows; i++) {
		    Value attribute = source.getFeature(i).getAttribute(3);
		    if (attribute.toString().equals(plotCode)) {
			IGeometry plotGeom = source.getShape(i);
			/*
			 * fix to avoid zoom problems when layer and view
			 * projections aren't the same.
			 */
			if ((layer.getCoordTrans() != null)
				&& (plotGeom != null)) {
			    plotGeom.reProject(layer.getCoordTrans());
			}
			return plotGeom;
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
	return null;
    }

    private Rectangle2D zoomTo(IGeometry plotGeom) {
	Rectangle2D rectangle = null;
	rectangle = plotGeom.getBounds2D();
	if (rectangle.getWidth() < 200) {
	    rectangle.setFrameFromCenter(rectangle.getCenterX(),
		    rectangle.getCenterY(), rectangle.getCenterX() + 100,
		    rectangle.getCenterY() + 100);
	}
	if (rectangle != null) {
	    ViewPort viewPort = mapContext.getViewPort();
	    viewPort.setExtent(rectangle);
	    viewPort.refreshExtent();
	}
	return rectangle;
    }
}

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

import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.ProjectExtension;
import com.iver.cit.gvsig.fmap.MapContext;
import com.iver.cit.gvsig.project.Project;
import com.iver.cit.gvsig.project.ProjectFactory;
import com.iver.cit.gvsig.project.documents.exceptions.OpenException;
import com.iver.cit.gvsig.project.documents.layout.ProjectMap;
import com.iver.cit.gvsig.project.documents.layout.fframes.FFrameView;
import com.iver.cit.gvsig.project.documents.layout.fframes.IFFrame;
import com.iver.cit.gvsig.project.documents.layout.gui.Layout;
import com.iver.utiles.XMLEntity;
import com.iver.utiles.xml.XMLEncodingUtils;
import com.iver.utiles.xmlEntity.generate.XmlTag;

import es.udc.cartolab.gvsig.pmf.SelectHousingLayoutDialog;

public class LayoutWrapper {

    private static Logger logger = Logger
	    .getLogger(SelectHousingLayoutDialog.class);

    private MapContext mapContext;
    private String templatePath;

    public LayoutWrapper(MapContext mapContext, String templatePath) {
	this.mapContext = mapContext;
	this.templatePath = templatePath;
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

    public Rectangle2D zoomTo(String housingCode) {
	final ZoomToParcel zoom = new ZoomToParcel();
	zoom.onFinishZoom(new Runnable() {

	    @Override
	    public void run() {
		try {
		    Layout layout = getLayoutFromTemplate(new File(templatePath));

		    layout.commandRefresh();
		    layout.commandRepaint();
		    openLayoutWindow(layout, "PMF");
		    IFFrame[] fFrames = layout.getLayoutContext().getFFrames();
		    for (IFFrame fFrame : fFrames) {
			if (fFrame instanceof FFrameView) {
			    FFrameView frameView = (FFrameView) fFrame;
			    frameView.setNewExtent(mapContext
				    .getGraphicsLayer().getFullExtent());
			    frameView.setLinked(false);
			    frameView.setScale(ZoomToParcel.SCALE);
			    frameView.refresh();
			}
		    }
		} catch (ExternalError ex) {
		    logger.error(ex.getStackTrace(), ex);
		}
	    }
	});
	return zoom.zoom(housingCode);
    }
}

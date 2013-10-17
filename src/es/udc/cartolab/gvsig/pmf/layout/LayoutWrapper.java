package es.udc.cartolab.gvsig.pmf.layout;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

import com.hardcode.gdbms.driver.exceptions.InitializeDriverException;
import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.ProjectExtension;
import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.ReadableVectorial;
import com.iver.cit.gvsig.fmap.layers.layerOperations.AlphanumericData;
import com.iver.cit.gvsig.project.Project;
import com.iver.cit.gvsig.project.ProjectFactory;
import com.iver.cit.gvsig.project.documents.exceptions.OpenException;
import com.iver.cit.gvsig.project.documents.layout.ProjectMap;
import com.iver.cit.gvsig.project.documents.layout.gui.Layout;
import com.iver.cit.gvsig.project.documents.view.gui.View;
import com.iver.utiles.XMLEntity;
import com.iver.utiles.xml.XMLEncodingUtils;
import com.iver.utiles.xmlEntity.generate.XmlTag;

import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;

public class LayoutWrapper {
    private static final int SCALE = 500;

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
	Layout layout;
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
	    throw new ExternalError("Error leyendo fichero de template", e);
	} catch (ValidationException e) {
	    throw new ExternalError("Error leyendo fichero de template", e);
	} catch (OpenException e) {
	    throw new ExternalError("Error leyendo fichero de template", e);
	} finally {
	    try {
		if (reader != null) {
		    reader.close();
		}
		if (is != null) {
		    is.close();
		}
	    } catch (IOException e) {
		throw new ExternalError("Error cerrando fichero de template", e);
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

    public void centerLayout(Layout layout) {
	layout.getLayoutControl().getLayoutZooms();
	layout.getLayoutControl().getLayoutFunctions();
    }

    public void foo() {
	View view = (View) PluginServices.getMDIManager().getActiveWindow();
	zoom();
	view.getMapControl().getMapContext().setScaleView(SCALE);
    }

    public void zoom() {
	Rectangle2D rectangle = null;
	int pos = 0;
	FLyrVect layer = new TOCLayerManager().getLayerByName("parcelas");
	if (layer instanceof AlphanumericData) {
	    try {
		IGeometry g;
		ReadableVectorial source = (layer).getSource();
		source.start();
		g = source.getShape(pos);
		source.stop();

		if (g != null) {
		    /*
		     * fix to avoid zoom problems when layer and view
		     * projections aren't the same.
		     */
		    if (layer.getCoordTrans() != null) {
			g.reProject(layer.getCoordTrans());
		    }
		    rectangle = g.getBounds2D();
		    if (rectangle.getWidth() < 200) {
			rectangle.setFrameFromCenter(rectangle.getCenterX(),
				rectangle.getCenterY(),
				rectangle.getCenterX() + 100,
				rectangle.getCenterY() + 100);
		    }
		    if (rectangle != null) {
			layer.getMapContext().getViewPort()
				.setExtent(rectangle);
		    }
		} else {
		    System.out.println("algo");
		}
	    } catch (InitializeDriverException e) {
		System.out.println("algo");
	    } catch (ReadDriverException e) {
		System.out.println("algo");
	    }
	}
    }

}

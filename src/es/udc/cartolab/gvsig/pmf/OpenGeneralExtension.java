package es.udc.cartolab.gvsig.pmf;

import java.awt.geom.Rectangle2D;
import java.io.File;

import org.apache.log4j.Logger;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.ProjectExtension;
import com.iver.cit.gvsig.exceptions.expansionfile.ExpansionFileReadException;
import com.iver.cit.gvsig.fmap.MapContext;
import com.iver.cit.gvsig.fmap.drivers.DBException;
import com.iver.cit.gvsig.fmap.layers.FLayer;
import com.iver.cit.gvsig.project.Project;
import com.iver.cit.gvsig.project.documents.ProjectDocument;
import com.iver.cit.gvsig.project.documents.ProjectDocumentFactory;
import com.iver.cit.gvsig.project.documents.view.ProjectViewFactory;
import com.iver.cit.gvsig.project.documents.view.gui.View;
import com.iver.utiles.FileUtils;

import es.icarto.gvsig.commons.AbstractExtension;
import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.udc.cartolab.gvsig.elle.utils.ELLEMap;
import es.udc.cartolab.gvsig.elle.utils.LoadLegend;
import es.udc.cartolab.gvsig.elle.utils.MapDAO;
import es.udc.cartolab.gvsig.pmf.forms.ComunidadesForm;
import es.udc.cartolab.gvsig.pmf.utils.PmfConstants;
import es.udc.cartolab.gvsig.users.utils.DBSession;
import es.udc.cartolab.gvsig.users.utils.DBSessionSpatiaLite;

public class OpenGeneralExtension extends AbstractExtension {

    private static final Logger logger = Logger
	    .getLogger(OpenGeneralExtension.class);

    private String sqliteFilePath = FileUtils.getAppHomeDir()
	    + PmfConstants.SQLITE_FILE;

    private View createViewIfNeeded() {

	// TODO: fpuga: Check what happens when exists a view in the project but
	// is not active
	IWindow iWindow = PluginServices.getMDIManager().getActiveWindow();
	View view = null;

	if (iWindow instanceof View) {
	    view = (View) iWindow;
	} else {

	    Project project = ((ProjectExtension) PluginServices
		    .getExtension(ProjectExtension.class)).getProject();
	    ProjectDocumentFactory viewFactory = Project
		    .getProjectDocumentFactory(ProjectViewFactory.registerName);
	    ProjectDocument projectDocument = viewFactory.create(project);
	    projectDocument.setName("PMF");
	    project.addDocument(projectDocument);
	    view = (View) projectDocument.createWindow();
	    view.getWindowInfo().setMaximized(true);
	    PluginServices.getMDIManager().addWindow(view);
	}
	return view;
    }

    private static void zoomToLayer(final View view, final String layername) {
	try {
	    MapContext mapContext = view.getMapControl().getMapContext();
	    final FLayer layer = mapContext.getLayers().getLayer(layername);
	    if (layer == null) {
		return;
	    }
	    Rectangle2D fullExtent = layer.getFullExtent();

	    if (isDefaultExtent(fullExtent)
		    || (hasWidthInKmLessThan(fullExtent, 2))) {
		return;
	    }
	    mapContext.getViewPort().setExtent(fullExtent);
	} catch (ExpansionFileReadException e) {
	    e.printStackTrace();
	} catch (ReadDriverException e) {
	    e.printStackTrace();
	}
    }

    private static boolean hasWidthInKmLessThan(Rectangle2D extent, float km) {
	return extent.getWidth() < km * 1000;
    }

    public static boolean isDefaultExtent(Rectangle2D fullExtent) {
	return fullExtent.equals(new Rectangle2D.Double(1, 1, 10, 10));
    }

    @Override
    public void execute(String actionCommand) {

	try {
	    if (DBSession.getCurrentSession() == null) {
		DBSessionSpatiaLite.createConnection(sqliteFilePath);
	    }

	    MapDAO mapDAO = MapDAO.getInstance();
	    PluginServices.getMDIManager().setWaitCursor();

	    final View view = createViewIfNeeded();
	    ELLEMap map = mapDAO.getMap(view, PmfConstants.GENERAL_MAP,
		    LoadLegend.DB_LEGEND, PmfConstants.GENERAL_MAP);
	    map.load(view.getProjection());
	    zoomToLayer(view, ComunidadesForm.NAME);

	} catch (DBException e) {
	    logger.error(e.getStackTrace(), e);
	} catch (Exception e) {
	    logger.error(e.getStackTrace(), e);
	} finally {
	    PluginServices.getMDIManager().restoreCursor();
	}
    }

    @Override
    public boolean isEnabled() {
	boolean enabled = (DBSession.getCurrentSession() != null);
	enabled = enabled || new File(sqliteFilePath).canRead();
	return (enabled && !FormFactory.allLayersLoadedRegistered());
    }

}

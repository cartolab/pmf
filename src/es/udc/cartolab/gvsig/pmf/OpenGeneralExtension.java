package es.udc.cartolab.gvsig.pmf;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.andami.plugins.ExtensionDecorator;
import com.iver.andami.plugins.IExtension;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.ProjectExtension;
import com.iver.cit.gvsig.exceptions.expansionfile.ExpansionFileReadException;
import com.iver.cit.gvsig.fmap.MapContext;
import com.iver.cit.gvsig.project.Project;
import com.iver.cit.gvsig.project.documents.ProjectDocument;
import com.iver.cit.gvsig.project.documents.ProjectDocumentFactory;
import com.iver.cit.gvsig.project.documents.view.ProjectViewFactory;
import com.iver.cit.gvsig.project.documents.view.gui.View;
import com.iver.utiles.FileUtils;

import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.udc.cartolab.gvsig.elle.DeleteAllLegendsExtension;
import es.udc.cartolab.gvsig.elle.DeleteMapExtension;
import es.udc.cartolab.gvsig.elle.LoadAllLegendsExtension;
import es.udc.cartolab.gvsig.elle.LoadMapExtension;
import es.udc.cartolab.gvsig.elle.SaveAllLegendsExtension;
import es.udc.cartolab.gvsig.elle.SaveMapExtension;
import es.udc.cartolab.gvsig.elle.utils.ELLEMap;
import es.udc.cartolab.gvsig.elle.utils.LoadLegend;
import es.udc.cartolab.gvsig.elle.utils.MapDAO;
import es.udc.cartolab.gvsig.pmf.forms.ComunidadesForm;
import es.udc.cartolab.gvsig.pmf.utils.PmfConstants;
import es.udc.cartolab.gvsig.users.PostGISDBConnectionExtension;
import es.udc.cartolab.gvsig.users.SpatiaLiteDBConnectionExtension;
import es.udc.cartolab.gvsig.users.utils.DBSession;
import es.udc.cartolab.gvsig.users.utils.DBSessionSpatiaLite;

public class OpenGeneralExtension extends Extension {

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
	    Rectangle2D fullExtent = mapContext.getLayers().getLayer(layername)
		    .getFullExtent();
	    mapContext.getViewPort().setExtent(fullExtent);
	} catch (ExpansionFileReadException e) {
	    e.printStackTrace();
	} catch (ReadDriverException e) {
	    e.printStackTrace();
	}
    }

    protected void registerIcons() {
	PluginServices.getIconTheme().registerDefault(
		"load-layers-launcher-icon",
		this.getClass().getClassLoader()
			.getResource("images/load-layers.png"));
    }

    @Override
    public void initialize() {
	registerIcons();
    }

    @Override
    public void postInitialize() {

	// Hide extensions that we only use as libraries and not as extensions
	// to be "clicked" by the users
	List<Class<? extends IExtension>> l = new ArrayList<Class<? extends IExtension>>();
	l.add(SpatiaLiteDBConnectionExtension.class);
	l.add(PostGISDBConnectionExtension.class);
	l.add(DeleteAllLegendsExtension.class);
	l.add(DeleteMapExtension.class);
	l.add(LoadAllLegendsExtension.class);
	l.add(LoadMapExtension.class);
	l.add(SaveAllLegendsExtension.class);
	l.add(SaveMapExtension.class);

	for (Class<? extends IExtension> c : l) {
	    PluginServices.getDecoratedExtension(c).setVisibility(
		    ExtensionDecorator.ALWAYS_INVISIBLE);
	}

    }

    @Override
    public void execute(String actionCommand) {
	MapDAO mapDAO = MapDAO.getInstance();
	try {

	    PluginServices.getMDIManager().setWaitCursor();

	    if (DBSession.getCurrentSession() == null) {
		DBSessionSpatiaLite.createConnection(sqliteFilePath);
	    }

	    final View view = createViewIfNeeded();
	    ELLEMap map = mapDAO.getMap(view, PmfConstants.GENERAL_MAP,
		    LoadLegend.DB_LEGEND, PmfConstants.GENERAL_MAP);
	    map.load(view.getProjection());
	    zoomToLayer(view, ComunidadesForm.NAME);

	    PluginServices.getMDIManager().restoreCursor();
	} catch (Exception e) {
	    // TODO: catch error
	    e.printStackTrace();
	}
    }

    @Override
    public boolean isEnabled() {
	boolean enabled = (DBSession.getCurrentSession() != null);
	enabled = enabled || new File(sqliteFilePath).canRead();
	return (enabled && !FormFactory.allLayersLoadedRegistered());
    }

    @Override
    public boolean isVisible() {
	return true;
    }

}

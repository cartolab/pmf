package es.udc.cartolab.gvsig.loadData;

import java.io.File;
import java.util.ArrayList;

import org.cresques.cts.IProjection;

import com.iver.andami.PluginServices;
import com.iver.andami.messages.NotificationManager;
import com.iver.cit.gvsig.ProjectExtension;
import com.iver.cit.gvsig.fmap.crs.CRSFactory;
import com.iver.cit.gvsig.project.Project;
import com.iver.cit.gvsig.project.ProjectFactory;
import com.iver.cit.gvsig.project.documents.ProjectDocument;
import com.iver.cit.gvsig.project.documents.table.ProjectTable;
import com.iver.cit.gvsig.project.documents.table.gui.Table;
import com.iver.cit.gvsig.project.documents.view.ProjectView;
import com.iver.cit.gvsig.project.documents.view.gui.View;

/**
 * Static methods to create and open projects
 * 
 * @author Francisco Puga <fpuga@cartolab.es>
 */
public class LoadProject {

    /**
     * Blank Project Creator.
     * 
     * Function that creates a blank project with the passed IProjection.
     * 
     * @param  IProject the projection we want to create the project with.
     * @return a blank project.
     */
    private static Project createBlankProject(IProjection projection, String projectName) {
	ProjectDocument.initializeNUMS();
	PluginServices.getMDIManager().closeAllWindows();
	Project proj = ProjectFactory.createProject();
	proj.setName(projectName);
	proj.setProjection(projection);
	Project.setDefaultProjection(projection);
	ProjectExtension pExt = (ProjectExtension) PluginServices.getExtension(ProjectExtension.class);
	pExt.getProjectFrame().setProject(proj);
	pExt.showProjectWindow();
	PluginServices.getMainFrame().setTitle(projectName);
	pExt.setProject(proj);

	proj.setModified(false);
	return proj;
    }

    public static void createProject(File f, String projectionCode, String projectName) {

	IProjection projection = CRSFactory.getCRS(projectionCode);
	Project proj = createBlankProject(projection, projectName);

	LoadTables.loadTables(proj);

	ProjectView doc = ProjectFactory.createView(null);
	doc.setName(projectName);
	doc.setProject(proj, 0);
	proj.addDocument(doc);

	// se abre la vista en una ventana
	View nv = new View();
	nv.initialize();
	nv.setModel(doc);
	nv.setProjection(projection);
	nv.getWindowInfo().setMaximizable(true);
	nv.getWindowInfo().setMaximized(true);
	PluginServices.getMDIManager().addWindow(nv);


	LoadLayers.loadBaseCartographyLayers(nv, projection);
	LoadLayers.loadLayers(nv, projection);
	LoadLegend.loadAllStyles(nv);


	ProjectExtension.setPath(f.getAbsolutePath());
	ProjectExtension pExt = (ProjectExtension) PluginServices.getExtension(ProjectExtension.class);
	pExt.setProject(proj);

	if (!pExt.writeProject(f, proj, true)) {
	    NotificationManager.addWarning("_projectNotSaved");
	}
    }

    public static void loadProject(File f, String projectName) {
	View nv = new View();
	ProjectView pv = loadProjectView(f);
	if (pv != null) {
	    nv.initialize();
	    nv.setModel(pv);
	    //PluginServices.getMainFrame().setTitle(projectName);
	    PluginServices.getMDIManager().closeWindow(nv);
	    nv.getWindowInfo().setMaximizable(true);
	    PluginServices.getMDIManager().addWindow(nv);
	    nv.getWindowInfo().setMaximized(true);
	}
    }

    private static ProjectView loadProjectView(File f) {

	PluginServices.getMDIManager().closeAllWindows();
	ProjectExtension pExt = (ProjectExtension) PluginServices.getExtension(ProjectExtension.class);
	Project pro = pExt.readProject(f);

	if (pro != null) {
	    // ProjectExtension.setPath(path);
	    pExt.setProject(pro);
	    ProjectExtension.setPath(f.getAbsolutePath());
	    // try {
	    // pro.restoreWindowProperties();
	    // } catch (Exception e) {
	    // System.out.println("Error catched in order to avoid alarming the user...");
	    // }
	    pExt.showProjectWindow();
	    pExt.getProjectFrame().refreshControls();
	    ArrayList ar = pro.getDocuments();
	    ProjectView pv = null;
	    for (int i = 0; i < ar.size(); i++) {
		if (ar.get(i) instanceof ProjectView) {
		    pv = (ProjectView) ar.get(i);
		} else if (ar.get(i) instanceof ProjectTable) {
		    ProjectTable projectTable = (ProjectTable) ar.get(i);
		    Table t = new Table();
		    t.setModel(projectTable);
		    PluginServices.getMDIManager().addWindow(t);
		}
	    }
	    return pv;
	} else {
	    return null;
	}
    }
}

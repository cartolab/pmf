package es.udc.cartolab.gvsig.loadData;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hardcode.gdbms.engine.data.DataSource;
import com.hardcode.gdbms.engine.data.DataSourceFactory;
import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.fmap.edition.EditableAdapter;
import com.iver.cit.gvsig.fmap.layers.LayerFactory;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;
import com.iver.cit.gvsig.project.Project;
import com.iver.cit.gvsig.project.ProjectFactory;
import com.iver.cit.gvsig.project.documents.table.ProjectTable;
import com.iver.cit.gvsig.project.documents.table.gui.Table;

import es.udc.cartolab.gvsig.loadData.preferences.LoadDataConfigDialog;

/**
 * Load Tables Class
 * 
 * Class used for loading only the tables data.
 * 
 * @author Jorge López Fernández <jlopez@cartolab.es>
 */

public abstract class LoadTables {

	/**
	 * Dbf Files Retriever
	 * 
	 * Function which returns a list with all the dbf files inside
	 * a directory.
	 * 
	 * @param folder The folder we want to look for the dbf files into.
	 * @param recursive Parameter for telling the function whether we want to
	 * search in subdirectories or only in the first level.
	 * @return A list with all the dbf files as File objects.
	 */
	public static List<File> getDbfFiles(File folder, boolean recursive) {

		List<File> list = new ArrayList<File>();
		if (folder.isDirectory()) {

			FileFilter dbfFilter = new FileFilter() {
				public boolean accept(File file) {
					if (file.isFile() && !file.isDirectory()) {
						String filename = file.getName();
						return (filename.toLowerCase().endsWith(".dbf"));
					}
					return false;
				};
			};

			File[] fileNames = folder.listFiles(dbfFilter);
			for (File file : fileNames) {
				list.add(new File(file.getPath()));
			}
			if (recursive) {

				FileFilter subDirFilter = new FileFilter() {
					public boolean accept(File file) {
						return (file.isDirectory());
					};
				};

				File[] subDirNames = folder.listFiles(subDirFilter);
				for (File file : subDirNames) {
					list.addAll(getDbfFiles(file, true));
				}
			}
			return list;
		}
		return null;
	}

	/**
	 * Tables Loader
	 * 
	 * Function which loads all the tables defined by the dbf files onto
	 * a project. A default path is assumed for the dbf files.
	 * 
	 * @param project The Project we want to load the dbf files onto.
	 */
	public static void loadTables(Project project){

		String dbfDir  = LoadDataConfigDialog.getConfigPath() + "DBF" + File.separator;
		loadTables(project, dbfDir);

	}



	/**
	 * Tables Loader
	 * 
	 * Function which loads all the tables defined by the dbf files inside
	 * the path provided onto a view and a projection.
	 * 
	 * @param project The Project we want to load the dbf files onto.
	 * @param tablesPath The folder where the shp files are located.
	 */
	public static void loadTables(Project project, String tablesPath){

		List<File> files = getDbfFiles(new File(tablesPath), false);
		File file;

		if (files != null) {
			Iterator<File> iter = files.iterator();
			while (iter.hasNext()) {
				file = iter.next();
				String name = file.getName();
				LayerFactory.getDataSourceFactory().addFileDataSource(
						"gdbms dbf driver", name,
						file.getAbsolutePath());
				DataSource dataSource;
				try {
					dataSource = LayerFactory.getDataSourceFactory()
					.createRandomDataSource(name,
							DataSourceFactory.AUTOMATIC_OPENING);
					SelectableDataSource sds = new SelectableDataSource(
							dataSource);
					EditableAdapter auxea = new EditableAdapter();
					auxea.setOriginalDataSource(sds);
					ProjectTable projectTable = ProjectFactory.createTable(
							name, auxea);
					Table t = new Table();
					t.setModel(projectTable);
					project.addDocument(projectTable);
					PluginServices.getMDIManager().addWindow(t);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}

package es.udc.cartolab.gvsig.loadData;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.cresques.cts.IProjection;

import com.hardcode.driverManager.DriverLoadException;
import com.iver.cit.gvsig.fmap.drivers.VectorialFileDriver;
import com.iver.cit.gvsig.fmap.layers.FLayer;
import com.iver.cit.gvsig.fmap.layers.LayerFactory;
import com.iver.cit.gvsig.project.documents.view.gui.View;

import es.udc.cartolab.gvsig.loadData.preferences.LoadDataConfigDialog;

/**
 * Load Layers Class
 * 
 * Class used for loading only the layers data.
 * 
 * @author Jorge López Fernández <jlopez@cartolab.es>
 * @author uve
 */

public abstract class LoadLayers {

	/**
	 * Shp Files Retriever
	 * 
	 * Function which returns a list with all the shp files inside
	 * a directory.
	 * 
	 * @param folder The folder we want to look for the shp files into.
	 * @param recursive Parameter for telling the function whether we want to
	 * search in subdirectories or only in the first level.
	 * @return A list with all the shp files as File objects.
	 */
	public static List<File> getShpFiles(File folder, boolean recursive) {

		List<File> list = new ArrayList<File>();
		if (folder.isDirectory()) {

			FileFilter shpFilter = new FileFilter() {
				public boolean accept(File file) {
					if (file.isFile() && !file.isDirectory()) {
						String filename = file.getName();
						return (filename.endsWith(".shp"));
					}
					return false;
				};
			};

			File[] fileNames = folder.listFiles(shpFilter);
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
					list.addAll(getShpFiles(file, true));
				}
			}
			return list;
		}
		return null;
	}

	/**
	 * Layers Loader
	 * 
	 * Function which loads all the layers defined by the shp files onto
	 * a view and a projection. A default path is assumed for the shp files.
	 * 
	 * @param view The View we want to load the shp files onto.
	 * @param projection The IProjection we want to load the shp files onto.
	 */
	public static void loadLayers(View view, IProjection projection){
		String layersPath  = LoadDataConfigDialog.getConfigPath() + "SHP" + File.separator;
		loadLayers(view, projection, layersPath);
	}
	
	public static void loadBaseCartographyLayers(View view, IProjection projection) {
		String layersPath  = LoadDataConfigDialog.getConfigPath() + "Cartografia" + File.separator;
		loadLayers(view, projection, layersPath);
	}

	/**
	 * Layers Loader
	 * 
	 * Function which loads all the layers defined by the shp files inside
	 * the path provided onto a view and a projection.
	 * 
	 * @param view The View we want to load the shp files onto.
	 * @param projection The IProjection we want to load the shp files onto.
	 * @param layersPath The folder where the shp files are located.
	 */
	public static void loadLayers(View view, IProjection projection,
			String layersPath) {

		File f = new File(layersPath);
		List<File> files = getShpFiles(f, false);
		File file;

		if (files != null) {
			Iterator<File> iter = files.iterator();
			while (iter.hasNext()) {
				file = iter.next();
				String layerName = file.getName().substring(
						0,
						file.getName().toLowerCase()
						.lastIndexOf(".shp"));
				FLayer layer = null;
				try {
					layer = LayerFactory.createLayer(layerName,
							(VectorialFileDriver) LayerFactory.getDM()
							.getDriver("gvSIG shp driver"),
							file, projection);
				} catch (DriverLoadException e) {
					e.printStackTrace();
				}
				view.getMapControl().getMapContext().getLayers().
				addLayer(layer);


			}
		}

	}

}

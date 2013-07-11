package es.udc.cartolab.gvsig.loadData;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.fmap.layers.FLayer;
import com.iver.cit.gvsig.fmap.layers.FLayers;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.rendering.LegendFactory;
import com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend;
import com.iver.cit.gvsig.fmap.rendering.VectorialUniqueValueLegend;
import com.iver.cit.gvsig.project.documents.view.gui.View;
import com.iver.utiles.XMLEntity;
import com.iver.utiles.xmlEntity.generate.XmlTag;

import es.udc.cartolab.gvsig.loadData.preferences.LoadDataConfigDialog;

/**
 * Load Legend Class
 * 
 * Class used for loading only the style data.
 * 
 * @author Jorge López Fernández <jlopez@cartolab.es>
 * @author uve
 */
public abstract class LoadLegend {

	/**
	 * Legend Setter
	 * 
	 * Function that applies the legend loaded from a file onto a layer. If we don't pass a filename,
	 * then it will assume that the file has the same name as the layer.
	 * 
	 * @param  lyr the layer we want to apply the legend onto.
	 * @param  legendFilename the file we want to read the legend from (optional parameter).
	 */
	public static void setLegend(FLyrVect lyr, String legendFilename) {

		if (lyr == null) {
			System.out.println("[LoadLegend] La capa es null: " + lyr
					+ " legend: " + legendFilename);
			return;
		}
		if (legendFilename == null || !legendFilename.endsWith(".gvl")) {
			legendFilename = lyr.getName() + ".gvl";
		}
		legendFilename = legendFilename.toLowerCase();
		try {
			String stylePath = LoadDataConfigDialog.getConfigPath() + "STYLES" + File.separator;
			File styleFile = new File(stylePath + legendFilename);
			if (styleFile.exists()) {
				InputStreamReader reader = new InputStreamReader(new FileInputStream(styleFile), "UTF-8");
				XmlTag tag = (XmlTag) XmlTag.unmarshal(reader);
				XMLEntity xml = new XMLEntity(tag);
				SingleSymbolLegend legend = (SingleSymbolLegend) LegendFactory.createFromXML(xml);
				lyr.setLegend(legend);
			} else {
				System.out.println("No existe el style: "
						+ styleFile.getAbsolutePath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Legend Setter
	 * 
	 * Commodity function which calls the Legend Setter one, accepting only the layer.
	 * 
	 * @param  lyr the layer we want to apply the legend onto.
	 */
	public static void setLegend(FLyrVect lyr) {
		setLegend(lyr, null);
	}

	/**
	 * Multiple Legend Setter
	 * 
	 * Function that uses the previous one for setting the legend on all the layers
	 * of a view.
	 * 
	 * @param  view the view whose layers we want to get applied their legends.
	 */
	public static void loadAllStyles(View view) {
		FLayers layers = view.getMapControl().getMapContext().getLayers();

		for (int i = 0; i < layers.getLayersCount(); i++) {
			FLayer lyr = layers.getLayer(i);
			if (lyr instanceof FLayers) {
				FLayers layers2 = (FLayers) lyr;
				for (int j = 0; j < layers2.getLayersCount(); j++) {
					FLayer lyr2 = layers2.getLayer(j);
					if (lyr2 instanceof FLyrVect) {
						setLegend((FLyrVect) lyr2);
					}
				}
			}
			if (lyr instanceof FLyrVect) {
				setLegend((FLyrVect) lyr);
			}
		}

	}

	/**
	 * Legend Setter
	 * 
	 * Commodity function which calls the Legend Setter one which accepts a
	 * layer, accepting only the layer name.
	 * 
	 * @param  layerName the name of the layer we want to apply the legend onto.
	 */
	public static void setLegend(String layerName) {

		View view = (View) PluginServices.getMDIManager().getActiveWindow();
		FLyrVect layer = (FLyrVect) view.getModel().getMapContext().getLayers()
		.getLayer(layerName);
		setLegend(layer);

	}

}

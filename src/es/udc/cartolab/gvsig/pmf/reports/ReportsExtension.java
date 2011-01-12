package es.udc.cartolab.gvsig.pmf.reports;

import java.util.HashMap;

import com.iver.andami.plugins.Extension;

public class ReportsExtension extends Extension {

	@Override
	public void execute(String actionCommand) {

		HashMap<String,Boolean> orderings = new HashMap<String,Boolean>();
		orderings.put("LimiteN", true);

		if (ReportsGenerator.generateReport("comunidades.shp", orderings, "/home/jlopez/ultraSimple.jrxml")) {
			System.out.println("Report generated!");
		} else {
			System.out.println("Some error happened while generating the report...");
		}

	}

	@Override
	public void initialize() {

	}


	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

}

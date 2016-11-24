package es.udc.cartolab.gvsig.pmf.importer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import com.iver.cit.gvsig.fmap.ViewPort;
import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.iver.cit.gvsig.fmap.core.SymbologyFactory;
import com.iver.cit.gvsig.fmap.core.symbols.ISymbol;
import com.iver.cit.gvsig.fmap.crs.CRSFactory;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.commons.gui.AbstractIWindow;
import es.icarto.gvsig.commons.gui.OkCancelPanel;
import es.icarto.gvsig.commons.gui.WidgetFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;

@SuppressWarnings("serial")
public class ConfirmCroquisDialog extends AbstractIWindow implements
	ActionListener {

    private OkCancelPanel btPanel;
    private boolean okClickedWithoutError;

    public ConfirmCroquisDialog(final IGeometry p) {
	super(new MigLayout("fill, insets 10"));
	setWindowTitle("Confirme que la parcela es correcta");
	// final int wProps = WindowInfo.MODELESSDIALOG | WindowInfo.RESIZABLE
	// | WindowInfo.PALETTE;
	// setWindowInfoProperties(wProps);
	btPanel = WidgetFactory.okCancelPanel(this, this, this);
	final Shape internalShape = p.cloneGeometry().getInternalShape();

	JPanel panel = new JPanel() {

	    @Override
	    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		ViewPort viewPort = new ViewPort(
			CRSFactory.getCRS("EPSG:32616"));
		viewPort.setImageSize(new Dimension(250, 250));
		viewPort.setExtent(internalShape.getBounds2D());

		ISymbol symbol = SymbologyFactory.createDefaultFillSymbol();
		TOCLayerManager toc = new TOCLayerManager();
		FLyrVect layer = toc.getLayerByName("parcelas");
		if (layer != null) {
		    symbol = layer.getLegend().getDefaultSymbol();
		}

		// symbol.setHasFill(true);
		// symbol.setHasOutline(true);
		// Color c = new Color(0f, (float) (102.0 / 256.0), 0f, 0f);
		// symbol.setFillColor(c);
		// SimpleLineSymbol outline = new SimpleLineSymbol();
		// outline.setLineWidth(2);
		// outline.setAlpha(0);
		// SimpleLineStyle lineStyle = new SimpleLineStyle();
		// lineStyle.setLineWidth(2);
		// lineStyle.setStroke(new St);
		// outline.setLineStyle(lineStyle);
		// symbol.setOutline(outline);

		p.draw(g2, viewPort, symbol);

		// LabelClass label = new LabelClass();
		// label.setTexts(new String[] { "foo" });
		//
		// label.draw(g2, llm, shp);

		// g2.translate(internalShape.getBounds().getMinX(),
		// internalShape
		// .getBounds().getMaxY());
		// g2.setColor(Color.red);
		// g2.fill(internalShape);
		// g2.translate(internalShape.getBounds().getMinX(),
		// internalShape
		// .getBounds().getMaxY());
	    }

	    @Override
	    public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	    }
	    // @Override
	    // public void paint(Graphics g) {
	    // Graphics2D g2 = (Graphics2D) g;
	    // g2.setPaint(Color.red);
	    // g2.fill(internalShape);
	    // }
	};

	this.add(panel, "push, grow");

    }

    @Override
    protected JButton getDefaultButton() {
	return btPanel.getOkButton();
    }

    @Override
    protected Component getDefaultFocusComponent() {
	return btPanel.getOkButton();
    }

    /**
     * @return true when there is no okClickedWithoutError and the user press ok
     */
    public boolean isGood() {
	return okClickedWithoutError;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	okClickedWithoutError = false;
	if (e.getActionCommand().equals(OkCancelPanel.OK_ACTION_COMMAND)) {
	    okClickedWithoutError = true;
	}
	closeDialog();
    }

}

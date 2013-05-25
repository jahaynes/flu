package influence;

import helper.AbstractModelViewFactory;
import helper.ElementType;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import view.ElementView;

public class InfluenceView extends ElementView {

	private Set<Integer> connectedStocks;
	private List<AnchorView> anchorPoints = new ArrayList<AnchorView>();
	
	public InfluenceView(String name) {
		super(name);
	}
	
	public void setConnectedStocks(Set<Integer> connectedStocks) {
		this.connectedStocks = connectedStocks;
		rebuildAnchors();
		updateAnchors();
	}
	
	public static void updateAnchorsForAll() {
		Iterator<Integer> influences = Influence.getValidIds();
		
		while(influences.hasNext()) {
			InfluenceView a = (InfluenceView)(AbstractModelViewFactory.getView(ElementType.INFLUENCE, influences.next()));
			a.updateAnchors();
		}
		
	}
	
	public void rebuildAnchors() {
		anchorPoints.clear();
		for(Integer stockId : connectedStocks) {
			anchorPoints.add(new AnchorView(
					this.position, 
					AbstractModelViewFactory.getView(ElementType.STOCK, stockId)
					));
		}
	}
	
	public void updateAnchors() {
		for(AnchorView anchor : anchorPoints) {
			anchor.update();
		}
	}

	@Override
	public void paint(Graphics g) {		
		super.paint(g);
		for(AnchorView anchor : anchorPoints) {
			anchor.paint(g);
		}
	}
	
	public void paintConnections(Graphics g) {
		if(connectedStocks != null) {
			for(Integer stockId : connectedStocks) {
				Point stockViewCenter  = AbstractModelViewFactory.getView(ElementType.STOCK, stockId).getPosition();				
				g.drawLine(getPosition().x, getPosition().y, stockViewCenter.x, stockViewCenter.y);
			}	
		}		
	}
	
	/*public void checkRedraw(List<Integer> movedStocks) {
		for(Integer moved : movedStocks) {
			if(connectedStocks.contains(moved)) {
				updateNearestAnchors();
				break;
			}
		}
	}*/
}

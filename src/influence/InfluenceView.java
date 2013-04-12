package influence;

import helper.AbstractModelViewFactory;
import helper.ElementType;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Set;
import view.ElementView;

public class InfluenceView extends ElementView {

	private Set<Integer> connectedStocks;
	
	public InfluenceView(String name) {
		super(name);
	}
	
	public void setConnectedStocks(Set<Integer> connectedStocks) {
		this.connectedStocks = connectedStocks;
	}
	
	public void paintConnections(Graphics g) {
		if(connectedStocks != null) {
			for(Integer stockId : connectedStocks) {
				Point stockViewCenter  = AbstractModelViewFactory.getView(ElementType.STOCK, stockId).getPosition();				
				g.drawLine(getPosition().x, getPosition().y, stockViewCenter.x, stockViewCenter.y);
			}	
		}
	}

	public void updateNearestAnchors() {
		//TODO: on move, recalc nearest anchors
	}
}

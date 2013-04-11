package influence;

import helper.AbstractModelViewFactory;
import helper.ElementType;
import helper.InfluenceModelViewFactory;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Set;
import view.ElementView;

public class InfluenceView extends ElementView {

	private int ownerId;
	
	public InfluenceView(String name, int ownerId) {
		super(name);
		this.ownerId = ownerId;
	}
	
	public void paintConnections(Graphics g) {
		//Get connections from owner TODO: maybe push them instead of pull them
		Set<Integer> connectedStocks = InfluenceModelViewFactory.getInstance().get(ownerId).getConnectedStockIds();
		for(Integer stockId : connectedStocks) {
			Point stockViewCenter  = AbstractModelViewFactory.getView(ElementType.STOCK, stockId).getPosition();				
			g.drawLine(getPosition().x, getPosition().y, stockViewCenter.x, stockViewCenter.y);
		}	
	}

}

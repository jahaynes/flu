package influence;

import helper.AbstractModelViewFactory;
import helper.ElementType;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import view.ElementView;

public class InfluenceView extends ElementView {

	private Set<Integer> connectedStocks;
	private List<Point> anchorPoints = new ArrayList<Point>();
	
	public InfluenceView(String name) {
		super(name);
	}
	
	public void setConnectedStocks(Set<Integer> connectedStocks) {
		this.connectedStocks = connectedStocks;
		updateNearestAnchors();
	}
	
	@Override
	public void paint(Graphics g) {		
		super.paint(g);
	/*	for(Point anchorPoint : anchorPoints) {
			g.setColor(Color.green);
			g.fillOval(anchorPoint.x-4, anchorPoint.y-4, 8, 8);
		}*/
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
	
	public static void updateAnchorsForAll() {
	/*	Iterator<Integer> influenceIds = Influence.getValidIds();
		while (influenceIds.hasNext()) {	
			InfluenceModelViewFactory.getInstance().getView(influenceIds.next()).updateNearestAnchors();	
		}*/
	}
	
	public void updateNearestAnchors() {
		anchorPoints.clear();	
		for(int targetStock : connectedStocks) {
			
			ElementView targetView = AbstractModelViewFactory.getView(ElementType.STOCK, targetStock);
			
			float y1 = targetView.getPosition().y;
			float x1 = targetView.getPosition().x;

			//Calculate own line first (y = mx + b)
			//m = (y-y1) / (x-x1)
			
			float m = (y1 - position.y) / (x1 - position.x);
			float b = y1 - m * x1; 
			float mInv = 1.0f / m;
			
			Point[] possiblePoints = new Point[4];
			
			int top = targetView.top();
			int bottom = targetView.bottom();
			int left = targetView.left();
			int right = targetView.right();
			
			possiblePoints[0] = new Point((int)((top - b) * mInv), top);
			possiblePoints[1] = new Point((int)((bottom - b) * mInv), bottom);
			possiblePoints[2] = new Point(left, (int)(m*left + b));
			possiblePoints[3] = new Point(right, (int)(m*right + b));

			//Find the one point that is both NEAREST and IS ON THE TARGET BOX
			Point bestPoint = possiblePoints[0];
			for(int i=1;i<=3;i++) {
				if(left-2 < possiblePoints[i].x && possiblePoints[i].x < right+2		//Is within X of BOX
				&& top-2 < possiblePoints[i].y && possiblePoints[i].y < bottom+2 		//is within Y of BOX
				&& closerToThisThan(possiblePoints[i], bestPoint)) {					//Closer than previous best point (manhattan)
					bestPoint = possiblePoints[i];
				}
			}
			anchorPoints.add(bestPoint);
		}	
	}

	private boolean closerToThisThan(Point point, Point bestPoint) {
		int manDist1 = Math.abs(point.x - position.x) + Math.abs(point.y - position.y);
		int manDist2 = Math.abs(bestPoint.x - position.x) + Math.abs(bestPoint.y - position.y);
		return manDist1 < manDist2;
	}
}

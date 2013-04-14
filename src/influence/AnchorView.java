package influence;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import view.ElementView;

public class AnchorView {

	//TODO: maybe migrate all line-drawing code into this class
	private Point sourcePoint;
	private ElementView destView;
	
	private Point[] graphicalPoint = new Point[4];
	
	public AnchorView(Point sourcePoint, ElementView destView) {
		this.sourcePoint = sourcePoint;
		this.destView = destView;
		update();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.green);
		for(Point p : graphicalPoint) {
			if(p != null) {
				g.fillOval(p.x-4, p.y-4, 8, 8);
			}
		}
	}
	
	public void update() {
		int x0 = sourcePoint.x;
		int y0 = sourcePoint.y;
		
		float dx = destView.getPosition().x - x0;
		float dy = destView.getPosition().y - y0;
		
		float testLeft = (destView.left() - x0) / dx;
		float testRight = (destView.right() - x0) / dx;
		float testTop = (destView.top() - y0) / dy;
		float testBottom = (destView.bottom() - y0) / dy;
		
		//Equation is [x] = [x0] + t[dx]
		graphicalPoint[0] = new Point((int)(x0 + testLeft * dx), (int)(y0 + testLeft * dy));	
		graphicalPoint[1] = new Point((int)(x0 + testRight * dx), (int)(y0 + testRight * dy));	
		graphicalPoint[2] = new Point((int)(x0 + testTop * dx), (int)(y0 + testTop * dy));	
		graphicalPoint[3] = new Point((int)(x0 + testBottom * dx), (int)(y0 + testBottom * dy));	
	}
	
}

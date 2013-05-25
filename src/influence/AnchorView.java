package influence;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import view.ElementView;

public class AnchorView {

	//TODO: maybe migrate all line-drawing code into this class
	private Point sourcePoint;
	private ElementView destView;
	
	private Point graphicalPoint = new Point(-1,-1);
	private Point[] possiblePoints = new Point[4];
	
	public AnchorView(Point sourcePoint, ElementView destView) {
		this.sourcePoint = sourcePoint;
		this.destView = destView;
		for(int i=0;i<4;i++) {
			possiblePoints[i] = new Point(-1,-1);
		}
		update();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.green);
		g.fillOval(graphicalPoint.x-4, graphicalPoint.y-4, 8, 8);
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
		
		//System.out.println(testLeft);
		//System.out.println(testRight);
		//System.out.println(testTop);
		//System.out.println(testBottom);
		
		testLeft = (testLeft >= 1.0f) ? 0.0f : testLeft;
		testRight = (testRight >= 1.0f) ? 0.0f : testRight;
		testTop = (testTop >= 1.0f) ? 0.0f : testTop;
		testBottom = (testBottom >= 1.0f) ? 0.0f : testBottom;
		
		//Equation is [x] = [x0] + t[dx]
		possiblePoints[0].setLocation((int)(x0 + testLeft * dx), (int)(y0 + testLeft * dy));
		possiblePoints[1].setLocation((int)(x0 + testRight * dx), (int)(y0 + testRight * dy));	
		possiblePoints[2].setLocation((int)(x0 + testTop * dx), (int)(y0 + testTop * dy));	
		possiblePoints[3].setLocation((int)(x0 + testBottom * dx), (int)(y0 + testBottom * dy));	
		
		for(Point p : possiblePoints) {
			if(
			   (p.x == destView.left() || p.x == destView.right())
		    && (p.y == destView.top() || p.y == destView.bottom())
		    ) {
				graphicalPoint = p;
				break;
			}
		}
	}
}

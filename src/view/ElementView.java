package view;

import influence.Influence;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import stock.Stock;
import decorations.Colours;
import decorations.Margins;
import helper.InfluenceModelViewFactory;
import helper.StockModelViewFactory;

public class ElementView extends Draggable {
		
	private static final BufferedImage backBuffer = new BufferedImage(320, 240, BufferedImage.TYPE_BYTE_GRAY);
	
	private String drawName;	
	private int _width;
	private int _height;
			
	public ElementView(String name) {
		position = new Point(100,100);
		setName(name);
	}
	
	public void setName(String name) {
		_width = backBuffer.getGraphics().getFontMetrics().stringWidth(name);
		_height = backBuffer.getGraphics().getFontMetrics().getHeight();
		this.drawName = name;
	}
			
	public void setPosition(Point position) {
		this.position = position;
	}
			
	public void paint(Graphics g) {				
		g.setColor(Colours.stockColor);
		g.fillRect(left(), top(), width(), height());		
		g.setColor(Colours.textColor);
		g.drawString(drawName, left() + Margins.STOCKVIEWTEXTMARGINLEFT, top() + Margins.STOCKVIEWTEXTMARGINTOP);
		
		//Mark out the centre for debugging
		//g.setColor(Color.red);
		//g.fillOval(position.x-4, position.y-4, 8, 8);
		
	}
	
	public static void paintAll(Graphics g) {	
		
		//First draw all connections
		Iterator<Integer> influenceIds = Influence.getValidIds();
		while (influenceIds.hasNext()) {
			int id = influenceIds.next();		
			InfluenceModelViewFactory.getInstance().getView(id).paintConnections(g);	
		}
		
		//First draw all stocks
		Iterator<Integer> stockIds = Stock.getValidIds();
		while (stockIds.hasNext()) {
			int id = stockIds.next();
			StockModelViewFactory.getInstance().getView(id).paint(g);	
		}	
	
		//Then draw all influences (and their anchors)
		influenceIds = Influence.getValidIds();
		while (influenceIds.hasNext()) {
			int id = influenceIds.next();		
			InfluenceModelViewFactory.getInstance().getView(id).paint(g);	
		}
	}
	
	public boolean mouseIn(Point mouse) {	
		return mouse.x >= left() 
			&& mouse.x <= right()
			&& mouse.y >= top()
			&& mouse.y <= bottom();
	}
		
	public static List<Integer> stockViewsUnderMouse(Point mouseClick) {
		List<Integer> clicked = new ArrayList<Integer>();	
		Iterator<Integer> ids = Stock.getValidIds();
		while (ids.hasNext()) {
			Integer next = ids.next();
			if( StockModelViewFactory.getInstance().getView(next).mouseIn(mouseClick) ) {
				clicked.add(next);
			}			
		}
		return clicked;
	}
	
	public static List<Integer> influenceViewsUnderMouse(Point mouseClick) {
		List<Integer> clicked = new ArrayList<Integer>();	
		Iterator<Integer> ids = Influence.getValidIds();
		while (ids.hasNext()) {
			Integer next = ids.next();
			if( InfluenceModelViewFactory.getInstance().getView(next).mouseIn(mouseClick) ) {
				clicked.add(next);
			}			
		}
		return clicked;
	}
	
	public int left() {
		return position.x - (_width >> 1) - Margins.STOCKVIEWLEFTPADDING;
	}
	
	public int top() {
		return position.y - (_height >> 1) - Margins.STOCKVIEWTOPPADDING;
	}
	
	public int right() {
		return left() + width();
	}
	
	public int bottom() {
		return top() + height();
	}
	
	public int width() {
		return _width + (Margins.STOCKVIEWLEFTPADDING << 1);
	}
	
	public int height() {
		return _height + (Margins.STOCKVIEWTOPPADDING << 1);
	}
	
	public Point getPosition() {
		return position;
	}
	
	//TODO
	/*public Point getClosestBorderPointTo(Point other) {
		
		Point[] possiblePoints = new Point[4];
		
		
		
		
		
	}*/
	
}

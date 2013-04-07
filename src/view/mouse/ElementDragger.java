package view.mouse;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import explorer.Explorer;
import stock.StockView;
import view.Canvas;
import helper.StockModelViewFactory;

public class ElementDragger implements MouseListener, MouseMotionListener {

	private static ElementDragger instance = null;
	private final Canvas owner;
	
	private StockView held = null;
	private Point pressedPoint = null;
	
	public static synchronized ElementDragger getInstance(final Canvas owner) {
		if(instance == null) {
			instance = new ElementDragger(owner);
		}
		return instance;
	}
	
	private ElementDragger(final Canvas owner) {
		this.owner = owner;
	}
	
	@Override
	public void mouseDragged(MouseEvent m) {
		assert pressedPoint != null;
		if(held != null) {
			
			int dx = m.getX() - pressedPoint.x;
			int dy = m.getY() - pressedPoint.y;
				
			held.addToHeldPosition(dx, dy);						
			owner.repaint(); // TODO optimize this		
		}	
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	
	}

	@Override
	public void mouseClicked(MouseEvent m) {
		//TODO clean up
		boolean doubleClicked = m.getClickCount() == 2;
		held = null;
		
		pressedPoint = m.getPoint();
		List<Integer> pressedIds = StockView.stockViewsUnderMouse(pressedPoint);
		
		if(pressedIds.size() > 0) {
			Integer firstPressed = pressedIds.get(0);
			Explorer.getInstance().setSelectedStock(firstPressed, doubleClicked);	
		} else { //Nothing clicked.  clear explorer
			Explorer.getInstance().clearSelected();
		}
	}

	@Override
	public void mousePressed(MouseEvent m) {
		held = null;
		pressedPoint = m.getPoint();
		List<Integer> pressedIds = StockView.stockViewsUnderMouse(pressedPoint);
		
		if(pressedIds.size() > 0) {
			Integer firstPressed = pressedIds.get(0);
			held = StockModelViewFactory.getView(firstPressed);	
			held.setHoldPosition();
			Explorer.getInstance().setSelectedStock(firstPressed, false);
		} else {
			Explorer.getInstance().clearSelected();
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if(held != null) {
			//Do release event for held
			
			held = null;
		}
	}	
}
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import javax.swing.JPanel;
import aux.StockModelViewFactory;

@SuppressWarnings("serial")
public class Canvas extends JPanel {
		
	public Canvas() {
		ElementDragger ed = ElementDragger.getInstance(this);
		this.addMouseListener(ed);
		this.addMouseMotionListener(ed);
		this.decorate();
	}

	private void decorate() {
		this.setBackground(new Color(176, 191, 200));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		StockView.paintAll(g);
	}

}

class ElementDragger implements MouseListener, MouseMotionListener {

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
		//Double click only
		if(m.getClickCount() == 2) {
			
			StockModelViewFactory.rollBackAllNames();
			
			held = null;
			List<Integer> pressedIds = StockView.stockViewsUnderMouse(m.getPoint());
			
			if(pressedIds.size() > 0) {
				Explorer.getInstance().setSelectedStock((pressedIds.get(0)));	
			}	
		}
	}

	@Override
	public void mousePressed(MouseEvent m) {
		held = null;
		pressedPoint = m.getPoint();
		List<Integer> pressedIds = StockView.stockViewsUnderMouse(pressedPoint);
		
		if(pressedIds.size() > 0) {
			held = StockModelViewFactory.getView(pressedIds.get(0));	
			held.setHoldPosition();
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

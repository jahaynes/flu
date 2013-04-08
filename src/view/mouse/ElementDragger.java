package view.mouse;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import commands.CommandHistory;
import commands.DuplicateStockCommand;
import explorer.Explorer;
import stock.StockView;
import view.Canvas;
import view.keyboard.Keyboard;
import helper.StockModelViewFactory;

public class ElementDragger implements MouseListener, MouseMotionListener {

	private static ElementDragger instance = null;
	private final Canvas owner;
	
	private StockView held = null;
	private Point pressedPoint = null;
	private DragType currentDragType = DragType.NOTHING;
	
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

	private enum DragType {
		NOTHING,
		MOVE,
		CLONE,
		CONNECT	
	}
	
	//TODO: As well as checking for start-end within each ctrl/shift/normal, try mixing and matching
	
	@Override
	public void mousePressed(MouseEvent m) {
		
		held = null;
		pressedPoint = m.getPoint();
		List<Integer> pressedIds = StockView.stockViewsUnderMouse(pressedPoint);
		if(pressedIds.size() > 0) {
			int selectedInt = pressedIds.get(0);
			if(Keyboard.isCtrlDown()) {
				startClone(m, selectedInt);
			} else if (Keyboard.isShiftDown()) {
				startConnection(m, selectedInt);
			} else {
				startMove(m, selectedInt);			
			}	
		} else {
			Explorer.getInstance().clearSelected();
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent m) {
		
		switch(currentDragType) {
		case CLONE:
			break;
		case CONNECT:
			break;
		case MOVE:
			break;
		default:
			break;	
		}
		
		currentDragType = DragType.NOTHING;
		Canvas.getInstance().repaint();
	}	
	
	private void startConnection(MouseEvent m, int selectedId) {
		currentDragType = DragType.CONNECT;
	}
	
	private void startClone(MouseEvent m, int selectedId) {	
		currentDragType = DragType.CLONE;
		DuplicateStockCommand command = new DuplicateStockCommand(selectedId);
		CommandHistory.getInstance().pushCommand(command);
		command.execute();
		int newId = command.getNewId();
		startMove(m, newId);
	}
	
	private void startMove(MouseEvent m, int selectedId) {
		currentDragType = DragType.MOVE;
		held = StockModelViewFactory.getView(selectedId);	
		held.setHoldPosition();
		Explorer.getInstance().setSelectedStock(selectedId, false);
	}
		
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
}
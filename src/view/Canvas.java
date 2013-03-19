package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import aux.StockModelViewFactory;

@SuppressWarnings("serial")
public class Canvas extends JPanel {
		
	public Canvas() {
		this.addMouseListener(ElementDragger.getInstance(this));
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
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent m) {
		for (Integer id : StockView.stockViewsUnderMouse(m.getPoint())) {
			StockModelViewFactory.remove(id);
		}
		owner.repaint(); // TODO optimise this
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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}	
}

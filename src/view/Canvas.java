package view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import view.mouse.ElementDragger;

@SuppressWarnings("serial")
public class Canvas extends JPanel {
		
	private static Canvas instance;
	
	public static synchronized Canvas getInstance() {
		if(instance == null) {
			instance = new Canvas();
		}
		return instance;
	}
	
	private Canvas() {
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
		ElementView.paintAll(g);
	}

}



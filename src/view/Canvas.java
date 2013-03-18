package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel {

	public Canvas() {
		this.decorate();
	}
	
	private void decorate() {
		this.setBackground(new Color(176, 191, 200));
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.red);
		
		g.fillOval(300, 200, 20, 30);
		
	}
	
	
}

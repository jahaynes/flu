package view;

import java.awt.Color;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel {

	public Canvas() {
		this.decorate();
	}
	
	private void decorate() {
		this.setBackground(new Color(176, 191, 200));
	}
	

}

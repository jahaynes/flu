package view;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ControlBar extends JPanel {

	public ControlBar() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.attachChildren();
		this.decorate();
	}
	
	private void attachChildren() {
		add(new JButton("Time Options"));
		add(new JLabel("Current Time: "));
	}
	
	private void decorate() {
		this.setBackground(new Color(191, 176, 200));
	}

}
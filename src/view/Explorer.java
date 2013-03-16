package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

@SuppressWarnings("serial")
public class Explorer extends JPanel {

	public Explorer() {
		this.prepareLayout();
		this.attachChildren();
		this.decorate();
	}
	
	private void prepareLayout() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}
	
	private void attachChildren() {
		this.add(new JLabel("Flow Explorer"));		
		addSmallRigidArea(this);
		
		this.add(new JLabel("Name"));
		this.add(new JTextField());
		addLargeRigidArea(this);
		
		this.add(new JLabel("Expression"));
		this.add(new JTextArea());
		addSmallRigidArea(this);
		
		this.add(new JLabel("Current Value"));
		this.add(new JTextField());
		addSmallRigidArea(this);
		
		this.add(new JLabel("Stop Condition"));
		this.add(new JTextField());
		addLargeRigidArea(this);
		
	}
	
	private static void addSmallRigidArea(JPanel c) {
		c.add(Box.createRigidArea(new Dimension(0, 20)));
	}
	
	private static void addLargeRigidArea(JPanel c) {
		c.add(Box.createRigidArea(new Dimension(0, 60)));
	}
	
	private void decorate() {
		this.setBackground(new Color(213, 221, 226));
	}

}

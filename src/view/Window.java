package view;

import javax.swing.JFrame;

import view.keyboard.Keyboard;
import explorer.Explorer;
import java.awt.BorderLayout;
import java.awt.KeyboardFocusManager;

@SuppressWarnings("serial")
public class Window extends JFrame {

	public Window(String name) {
		super(name);
		this.prepareLayout();
		this.attachChildren();
		this.decorate();		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new Keyboard());	
		this.setVisible(true);
	}
	
	private void prepareLayout() {
		setLayout(new BorderLayout());
	}
	
	private void attachChildren() {
		add(new Menu(), BorderLayout.NORTH);
		add(Canvas.getInstance(), BorderLayout.CENTER);
		add(Explorer.getInstance().getView(), BorderLayout.EAST);	
		add(new ControlBar(), BorderLayout.SOUTH);
	}
	
	private void decorate() {
		this.setSize(800,600);
	}
}

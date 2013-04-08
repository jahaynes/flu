package view.keyboard;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import commands.CommandHistory;

public class Keyboard implements KeyEventDispatcher, KeyListener {

	private static boolean ctrlHeld;
		
	public Keyboard() {
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		//If this was ctrl being pushed now
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) {		
			if (e.getID() == KeyEvent.KEY_PRESSED) {
				ctrlHeld = true;
			} else if (e.getID() == KeyEvent.KEY_RELEASED) {
				ctrlHeld = false;
			}		
		}
		
		//Check for UNDO (Ctrl-Z)
		//TODO: probably could do this better than 90
		if( ctrlHeld && e.getKeyCode() == 90 && e.getID() == KeyEvent.KEY_RELEASED) {
			CommandHistory.getInstance().undo();
			return true;
		}
		
		//If ctrl is held down, absorb this event
		return ctrlHeld;
	}	
	
	public static boolean isCtrlDown() {
		return ctrlHeld;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {	
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
	
}

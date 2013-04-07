package view.keyboard;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class Keyboard implements KeyEventDispatcher {

	private static boolean ctrlHeld;
	
	public Keyboard() {
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		//Track ctrl held down
		ctrlHeld = e.getKeyCode() == KeyEvent.VK_CONTROL;
		if (ctrlHeld) {		
			if (e.getID() == KeyEvent.KEY_PRESSED) {
				ctrlHeld = true;
			} else if (e.getID() == KeyEvent.KEY_RELEASED) {
				ctrlHeld = false;
			}		
		}	
		
		//If ctrl is held down, absorb this event
		return ctrlHeld;
	}	
	
	public static boolean isCtrlDown() {
		return ctrlHeld;
	}
	
}

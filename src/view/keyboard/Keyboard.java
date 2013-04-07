package view.keyboard;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import commands.CommandHistory;

public class Keyboard implements KeyEventDispatcher {

	private static boolean ctrlHeld;
	
	private static long keyCount = 0;
	
	public Keyboard() {
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		
		System.out.println("Keycount: " +keyCount++);
		
		//Track ctrl held down
		boolean ctrlHeldLocal = e.getKeyCode() == KeyEvent.VK_CONTROL;
	
		//Check for UNDO (Ctrl-Z)
		if( ctrlHeld && e.getKeyChar() == 'z' ) {
			System.exit(1);
			CommandHistory.getInstance().undo();
		}
		
		if (ctrlHeldLocal) {		
			
			if (e.getID() == KeyEvent.KEY_PRESSED) {
				ctrlHeld = true;
			} else if (e.getID() == KeyEvent.KEY_RELEASED) {
				ctrlHeld = false;
			}		
		}	
		
		//If ctrl is held down, absorb this event
		return ctrlHeldLocal;
	}	
	
	public static boolean isCtrlDown() {
		return ctrlHeld;
	}
	
}

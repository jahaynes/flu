package view.keyboard;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import commands.CommandHistory;

public class Keyboard implements KeyEventDispatcher {

	private static boolean ctrlHeld;
	private static boolean altHeld;
		
	public Keyboard() {
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
	}

	private static void doSpecialKeyPress(KeyEvent e) {
		
		if(ctrlHeld && altHeld) {
			
		} else if (ctrlHeld) {
			
		} else if (altHeld){
			
		} else {
			//Should not be here
		}
		
		System.out.print( (ctrlHeld ? "ctrl-" : "") + (altHeld ? "alt-" : "") + new Character(e.getKeyChar()) + "\n");
	}
		
	@Override
	public boolean dispatchKeyEvent(KeyEvent k) {
		//If this was ctrl being pushed now
		if (k.getKeyCode() == KeyEvent.VK_CONTROL) {		
			if (k.getID() == KeyEvent.KEY_PRESSED) {
				ctrlHeld = true;
			} else if (k.getID() == KeyEvent.KEY_RELEASED) {
				ctrlHeld = false;
			}		
		}
		
		if (k.getKeyCode() == KeyEvent.VK_ALT) {		
			if (k.getID() == KeyEvent.KEY_PRESSED) {
				altHeld = true;
			} else if (k.getID() == KeyEvent.KEY_RELEASED) {
				altHeld = false;
			}		
		}
		
		//Check for UNDO (Ctrl-Z)
		//TODO: probably could do this better than 90
		if( ctrlHeld && k.getKeyCode() == 90 && k.getID() == KeyEvent.KEY_RELEASED) {
			CommandHistory.getInstance().undo();
			return true;
		}
		
		if((ctrlHeld || altHeld) && k.getID() == KeyEvent.KEY_TYPED) {
			doSpecialKeyPress(k);
			return true;
		} else {
			return false;
		}
	}	
	
	public static boolean isCtrlDown() {
		return ctrlHeld;
	}
	
	public static boolean isAltDown() {
		return altHeld;
	}
}

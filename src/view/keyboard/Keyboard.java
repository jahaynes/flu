package view.keyboard;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import commands.CommandHistory;

public class Keyboard implements KeyEventDispatcher {

	private static boolean ctrlHeld;
	private static boolean altHeld;
	private static boolean shiftHeld;
		
	public Keyboard() {
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
	}

	private static boolean trySpecialKeyPress(KeyEvent e) {
		
		//Ctrl Only
		if(ctrlHeld && !shiftHeld && !altHeld) {
			if (e.getKeyCode() == 90) {
				CommandHistory.getInstance().undo();
				return true;
			}
		}
		
		//Shift Only
		if(!ctrlHeld && shiftHeld && !altHeld) {
			
		}
		
		//Alt Only
		if(!ctrlHeld && !shiftHeld && altHeld) {
			//Probably don't use this, as it is a linux key
		}
				
		System.out.print( (ctrlHeld ? "ctrl-" : "") + (altHeld ? "alt-" : "") + new Character(e.getKeyChar()) + "\n");
		return false;
	}
		
	@Override
	public boolean dispatchKeyEvent(KeyEvent k) {

		int keyCode = k.getKeyCode();
		int keyID = k.getID();
		
		switch(keyCode) {
			case KeyEvent.VK_CONTROL:
				if (keyID == KeyEvent.KEY_PRESSED) 
					ctrlHeld = true;
				else if (keyID == KeyEvent.KEY_RELEASED) 
					ctrlHeld = false;
				break;
			case KeyEvent.VK_SHIFT:
				if (keyID == KeyEvent.KEY_PRESSED) 
					shiftHeld = true;
				else if (keyID == KeyEvent.KEY_RELEASED) 
					shiftHeld = false;
				break;
			case KeyEvent.VK_ALT:
				if (keyID == KeyEvent.KEY_PRESSED) 
					altHeld = true;
				else if (keyID == KeyEvent.KEY_RELEASED) 
					altHeld = false;
				break;
		}
								
		//If at least one of ctrlHeld, shiftHeld, or alHeld are pressed, try for special key
		if(k.getID() == KeyEvent.KEY_RELEASED) {
			return (ctrlHeld || shiftHeld || altHeld) && trySpecialKeyPress(k);
		} 
		
		return false;
	}	
	
	public static boolean isCtrlDown() {
		return ctrlHeld;
	}
	
	public static boolean isShiftDown() {
		return shiftHeld;
	}	
	
	public static boolean isAltDown() {
		return altHeld;
	}	
}

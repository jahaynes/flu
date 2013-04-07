package commands;

import java.util.Stack;

public class CommandHistory  {

	private Stack<Command> history = new Stack<Command>();
		
	private static CommandHistory instance;
	public static synchronized CommandHistory getInstance() {
		if(instance == null) {
			instance = new CommandHistory();
		}
		return instance;
	}
	
	public void undo() {
		if(! history.isEmpty()) {
			history.pop().rollback();
		}
	}
}

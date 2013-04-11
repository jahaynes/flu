package commands;

import helper.AbstractModelViewFactory;
import helper.ElementType;
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
	
	public void doCommand(Command command) {
		command.execute();
		history.push(command);
	}
	
	public void pushCommandWithoutExecute(Command command) {
		history.push(command);
	}
	
	//TODO: put rollback-only stuff in here like MOVE
	//public void alreadyDidCommand()
	
	public void undo() {
		if(! history.isEmpty()) {
			history.pop().rollback();
			System.out.println("Rolled back");
		}
	}

	public void changeName(ElementType elementType, int stockId, String newName) {	
		String trimmedName = newName.trim();
		if (AbstractModelViewFactory.isNameAcceptable(trimmedName)) {
			Command changeName = new NameChangeCommand(
					elementType,
					stockId, 
					newName);
			doCommand(changeName);
		}	
	}
}

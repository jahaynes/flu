package commands;

import helper.StockModelViewFactory;

import java.util.Stack;

import view.Canvas;

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

	public void changeStockName(int stockId, String newName) {	
		String trimmedName = newName.trim();
		if (StockModelViewFactory.isNameAcceptable(trimmedName)) {
			Command changeName = new NameChangeCommand(
					stockId, 
					newName);
			changeName.execute();
			history.push(changeName);
		}	
	}
}

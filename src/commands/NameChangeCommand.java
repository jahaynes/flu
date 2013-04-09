package commands;

import explorer.Explorer;
import view.Canvas;
import helper.ModelViewFactory;

public class NameChangeCommand implements Command {
	
	private int elementId;
	private String oldName;
	private String newName;
	
	public NameChangeCommand(int elementId, String newName) {
		this.oldName = ModelViewFactory.getInstance().get(elementId).getName();
		this.elementId = elementId;
		this.newName = newName;
	}
	
	@Override
	public void execute() {
		ModelViewFactory.getInstance().get(elementId).setName(newName);
		ModelViewFactory.getInstance().getView(elementId).setName(newName);		
		Canvas.getInstance().repaint();
	}

	@Override
	public void rollback() {
	/*	ModelViewFactory.getInstance().get(elementId).setName(oldName);
		ModelViewFactory.getInstance().getView(elementId).setName(oldName);
		
		//If currently exploring the name-changed stock, redisplay it in non-edit mode
		if (Explorer.getInstance().getSelectedElement() == elementId) {
			Explorer.getInstance().setSelectedStock(elementId, false);
		}
		
		Canvas.getInstance().repaint();*/
	}

}
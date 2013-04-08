package commands;

import explorer.Explorer;
import view.Canvas;
import helper.ModelViewFactory;

public class NameChangeCommand implements Command {
	
	private int stockId;
	private String oldName;
	private String newName;
	
	public NameChangeCommand(int stockId, String newName) {
		this.oldName = ModelViewFactory.getStock(stockId).getName();
		this.stockId = stockId;
		this.newName = newName;
	}
	
	@Override
	public void execute() {
		ModelViewFactory.getStock(stockId).setName(newName);
		ModelViewFactory.getView(stockId).setName(newName);		
		Canvas.getInstance().repaint();
	}

	@Override
	public void rollback() {
		ModelViewFactory.getStock(stockId).setName(oldName);
		ModelViewFactory.getView(stockId).setName(oldName);
		
		//If currently exploring the name-changed stock, redisplay it in non-edit mode
		if (Explorer.getInstance().getSelected() == stockId) {
			Explorer.getInstance().setSelectedStock(stockId, false);
		}
		
		Canvas.getInstance().repaint();
	}

}
package commands;

import explorer.Explorer;
import view.Canvas;
import helper.StockModelViewFactory;

public class NameChangeCommand implements Command {
	
	private int stockId;
	private String oldName;
	private String newName;
	
	public NameChangeCommand(int stockId, String newName) {
		this.oldName = StockModelViewFactory.getStock(stockId).getName();
		this.stockId = stockId;
		this.newName = newName;
	}
	
	@Override
	public void execute() {
		StockModelViewFactory.getStock(stockId).setName(newName);
		StockModelViewFactory.getView(stockId).setName(newName);		
		Canvas.getInstance().repaint();
	}

	@Override
	public void rollback() {
		StockModelViewFactory.getStock(stockId).setName(oldName);
		StockModelViewFactory.getView(stockId).setName(oldName);
		
		//If currently exploring the name-changed stock, redisplay it in non-edit mode
		if (Explorer.getInstance().getSelected() == stockId) {
			Explorer.getInstance().setSelectedStock(stockId, false);
		}
		
		Canvas.getInstance().repaint();
	}

}
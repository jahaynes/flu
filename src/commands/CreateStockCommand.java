package commands;

import view.Canvas;
import helper.StockModelViewFactory;

public class CreateStockCommand implements Command {

	int createdId = -1;
	
	@Override
	public void execute() {
		createdId = StockModelViewFactory.create();
		Canvas.getInstance().repaint();
	}

	@Override
	public void rollback() {
		StockModelViewFactory.remove(createdId);
		Canvas.getInstance().repaint();
	}
	
	public int getNewId() {
		return createdId;
	}

}

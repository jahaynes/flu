package commands;

import helper.StockModelViewFactory;

import java.awt.Point;

import decorations.Margins;

import stock.StockView;
import view.Canvas;

public class DuplicateStockCommand extends CreateStockCommand {

	private int sourceId = -1;
	
	public DuplicateStockCommand(int sourceId) {
		this.sourceId = sourceId;
	}
	
	@Override
	public void execute() {
		//Currently cloning just takes the left,top from the original, nothing else
		StockView sourceView = StockModelViewFactory.getView(sourceId);
		int left = sourceView.left();		
		int top = sourceView.top();	
		createdId = StockModelViewFactory.create();
		StockModelViewFactory.getView(createdId).setPosition(new Point(left-Margins.STOCKVIEWLEFTMARGIN, top-Margins.STOCKVIEWTOPMARGIN));	
		System.out.println("Cloned " + sourceId + " to " + createdId);
		Canvas.getInstance().repaint();
	}
	
	//Inherits rollback
}

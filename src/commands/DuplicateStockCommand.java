package commands;

import helper.ElementType;
import helper.InfluenceModelViewFactory;
import helper.StockModelViewFactory;

import java.awt.Point;
import decorations.Margins;
import view.Canvas;
import view.ElementView;

public class DuplicateStockCommand extends CreateCommand {

	private int sourceId = -1;
	
	public DuplicateStockCommand(int sourceId) {
		super(ElementType.STOCK);
		this.sourceId = sourceId;
	}
	
	@Override
	public void execute() {
		//Currently cloning just takes the left,top from the original, nothing else
		ElementView sourceView = StockModelViewFactory.getInstance().getView(sourceId);
		int left = sourceView.left();		
		int top = sourceView.top();	
		createdId = StockModelViewFactory.getInstance().create();
		StockModelViewFactory.getInstance().getView(createdId).setPosition(new Point(left-Margins.STOCKVIEWLEFTMARGIN, top-Margins.STOCKVIEWTOPMARGIN));	
		System.out.println("Cloned " + sourceId + " to " + createdId);
		Canvas.getInstance().repaint();
	}
	
	//Inherits rollback
}

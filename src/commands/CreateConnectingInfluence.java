package commands;

import java.awt.Point;
import decorations.Margins;
import helper.ModelViewFactory;

public class CreateConnectingInfluence implements Command {
	
	private int stock1;
	private int stock2;
	private int left;
	private int top;
	
	public CreateConnectingInfluence(int stock1, int stock2) {
		this.stock1 = stock1;
		this.stock2 = stock2;		
		left = (ModelViewFactory.getInstance().getView(stock1).left() + ModelViewFactory.getInstance().getView(stock2).left() >> 1)  - Margins.STOCKVIEWLEFTMARGIN;
		top = (ModelViewFactory.getInstance().getView(stock1).top() + ModelViewFactory.getInstance().getView(stock2).top() >> 1) - Margins.STOCKVIEWTOPMARGIN;
	}
	
	@Override
	public void execute() {
		int influenceId = ModelViewFactory.getInstance().createInfluence();
		System.out.println("Connected: " + stock1 + " to " + stock2 + " as influenceid " + influenceId);
		ModelViewFactory.getInstance().getInfluenceView(influenceId).setPosition(new Point(left,top));
	}

	@Override
	public void rollback() {
		System.out.println("Disconnected: " + stock1 + " from " + stock2);
	}
}

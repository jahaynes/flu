package commands;

import java.awt.Point;

import view.Canvas;
import helper.InfluenceModelViewFactory;
import helper.StockModelViewFactory;

public class CreateConnectingInfluence implements Command {
	
	private int stock1;
	private int stock2;
	private int x;
	private int y;
	
	private int influenceId;
	
	public CreateConnectingInfluence(int stock1, int stock2) {
		this.stock1 = stock1;
		this.stock2 = stock2;	
		
		StockModelViewFactory sf = StockModelViewFactory.getInstance();
		
		x = (sf.getView(stock1).getPosition().x + sf.getView(stock2).getPosition().x) >> 1;
		y = (sf.getView(stock1).getPosition().y + sf.getView(stock2).getPosition().y) >> 1;
	}
	
	@Override
	public void execute() {
		influenceId = InfluenceModelViewFactory.getInstance().create();
		InfluenceModelViewFactory.getInstance().getView(influenceId).setPosition(new Point(x,y));
		InfluenceModelViewFactory.getInstance().get(influenceId).connectToStock(stock1);
		InfluenceModelViewFactory.getInstance().get(influenceId).connectToStock(stock2);
		System.out.println("Connected: " + stock1 + " to " + stock2 + " as influenceid " + influenceId);
		Canvas.getInstance().repaint();		
	}

	@Override
	public void rollback() {
		InfluenceModelViewFactory.getInstance().get(influenceId).disconnectFromStock(stock1);
		InfluenceModelViewFactory.getInstance().get(influenceId).disconnectFromStock(stock2);
		InfluenceModelViewFactory.getInstance().remove(influenceId);
		System.out.println("Disconnected: " + stock1 + " from " + stock2);
		Canvas.getInstance().repaint();		
	}
}

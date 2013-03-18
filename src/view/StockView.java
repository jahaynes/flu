package view;

import java.awt.Color;
import java.awt.Graphics;
import model.Stock;

public class StockView {

	private int stockId;
	
	public StockView() {
		
	}
	
	
	public void paint(Graphics g) {
		
		String drawName = Stock.getStock(stockId).getName();
		
		g.setColor(Color.blue);
		g.drawString(drawName, 100, 150);
		
	}
	
	
	
	
	
	
	
	
}

package main;
import helper.StockModelViewFactory;
import view.Window;

public class Main {

	public static void main(String[] args) {
		
		new Window("flu");
		
		//Test data
		StockModelViewFactory.create("CATS IN BASKET");
		StockModelViewFactory.create("PUPPIES IN BASKET");
		StockModelViewFactory.create("BLANKETS IN BASKET");
	}

}

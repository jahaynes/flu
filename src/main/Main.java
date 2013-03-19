package main;
import aux.StockModelViewFactory;
import view.Window;

public class Main {

	public static void main(String[] args) {
		
		new Window("flu");
		
		//Test data
		StockModelViewFactory.create("CATS IN BASKET");
	}

}

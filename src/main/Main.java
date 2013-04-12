package main;
import helper.StockModelViewFactory;
import view.Window;

public class Main {

	public static void main(String[] args) {
		
		new Window("flu");
		
		//Test data
		StockModelViewFactory.getInstance().create();
		StockModelViewFactory.getInstance().create();
		StockModelViewFactory.getInstance().create();
	}

}

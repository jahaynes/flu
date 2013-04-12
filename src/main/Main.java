package main;
import helper.StockModelViewFactory;
import view.Window;

public class Main {

	public static void main(String[] args) {
		
		new Window("flu");
		
		//TODO: be able to zoom out from current 'module' into larger space (recursively)
		//each module will be represented by an 'entry state' and 'exit state' (amount and unit) which can be used in higher level modelling
		//additions to the module will flow into entry, and subtractions will leave the exit
		
		//Test data
		StockModelViewFactory.getInstance().create();
		StockModelViewFactory.getInstance().create();
		StockModelViewFactory.getInstance().create();
	}

}

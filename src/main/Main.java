package main;
import helper.ModelViewFactory;
import view.Window;

public class Main {

	public static void main(String[] args) {
		
		new Window("flu");
		
		//Test data
		ModelViewFactory.createStock();
		ModelViewFactory.createStock();
		ModelViewFactory.createStock();
	}

}

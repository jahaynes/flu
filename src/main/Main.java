package main;
import model.Stock;
import view.Window;

public class Main {

	public static void main(String[] args) {
		
		//Test data
		Stock.createStock("CATS IN BASKET");
		
		new Window("flu");
	}

}

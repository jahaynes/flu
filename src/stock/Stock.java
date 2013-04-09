package stock;

import java.util.Iterator;
import helper.Element;
import helper.StockModelViewFactory;

public class Stock extends Element {
			
	public static Iterator<Integer> getValidIds() {
		return StockModelViewFactory.getInstance().getIterator();
	}
	
	/* Simple dummy class to provide something for the GUI */
	public Stock(String name) {
		this.name = name;
	}
}

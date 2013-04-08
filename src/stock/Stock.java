package stock;

import java.util.*;
import helper.ModelViewFactory;

public class Stock {
			
	public static Iterator<Integer> getValidIds() {
		return ModelViewFactory.getIterator();
	}
	
	private String name;
	
	/* Simple dummy class to provide something for the GUI */
	public Stock(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}

package model;

import java.util.*;
import helper.StockModelViewFactory;

public class Stock {
		
	//public static void create(String name) {
	//	StockModelViewFactory.create(name);
	//}
	
	//public static Stock getStock(int id) {
	//	return StockModelViewFactory.getStock(id);
	//}
	
	//public static StockView getView(int id) {
	//	return StockModelViewFactory.getView(id);
	//}
	
	public static Iterator<Integer> getValidIds() {
		return StockModelViewFactory.getIterator();
	}
	
	/* Simple dummy class to provide something for the GUI */
	

	private String name;
	
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

package helper;

import stock.Stock;

public class StockModelViewFactory extends AbstractModelViewFactory {
	
	private static StockModelViewFactory instance;
	public static synchronized StockModelViewFactory getInstance() {
		if(instance == null) {
			instance = new StockModelViewFactory();
		}
		return instance;
	}
	
	private StockModelViewFactory() {
	}
					
	public Stock get(Integer id) {
		return (Stock)super.get(id);
	}
					
}
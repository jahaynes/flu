package helper;

import stock.Stock;
import view.ElementView;

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

	public int create() {
		int nextId = super.create();
		String name = "STOCK " + nextId;
		allElements.set(nextId, new Stock(name));
		allViews.set(nextId, new ElementView(name));
		usedIds.add(nextId);
		return nextId;
	}
	
}
package helper;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;
import stock.Stock;
import stock.StockView;

public class StockModelViewFactory{
	
	/* Id */
	private static SortedSet<Integer> usedIds = new TreeSet<Integer>();
	private static List<Integer> freeIds = new LinkedList<Integer>();
	
	/* All */
	private static List<Stock> allStocks = new Vector<Stock>();
	private static List<StockView> allViews = new Vector<StockView>();
	
	private static Integer consumeId() {			
		final int nextId;			
		if(freeIds.size() > 0) {
			nextId = freeIds.remove(0);
		} else { 
			if (usedIds.size() > 0) {
				nextId = usedIds.last() + 1;
			} else {
				nextId = 0;
			}
		}
		usedIds.add(nextId);
		return nextId;
	}

	public static synchronized void create(String name) {
		final Integer nextId = consumeId();
		
		// Expand the storage to accommodate the direct reference
		while(allStocks.size() < nextId + 1) {
			allStocks.add(null);
			allViews.add(null);
		}
		
		allStocks.set(nextId, new Stock(name));
		allViews.set(nextId, new StockView(nextId, name));
		usedIds.add(nextId);
	}
	
	public static synchronized Stock getStock(Integer id) {
		assert usedIds.contains(id);
		return allStocks.get(id);
	}
	
	public static synchronized StockView getView(Integer id) {
		assert usedIds.contains(id);
		return allViews.get(id);
	}
	
	public static synchronized void remove(Integer id) {
		allStocks.set(id, null);
		allViews.set(id, null);
		usedIds.remove(id);
		freeIds.add(id);
	}

	public static boolean tryConfirmName(Integer id, String name) {	
		String trimmedName = name.trim();
		boolean nameIsAcceptable = isNameAcceptable(trimmedName);
		if(nameIsAcceptable) {
			allStocks.get(id).setName(trimmedName);
			allViews.get(id).setName(trimmedName);	
		}		
		return nameIsAcceptable;
	}
		
	private static boolean isNameAcceptable(String name) {
		//For now, just check the name doesnt already exist
		for(Stock s : allStocks) {
			if(s.getName().toUpperCase().trim().equals(name.toUpperCase().trim())) {
				return false;
			}
		}
		return true;
	}
	
	public static Iterator<Integer> getIterator() {
		return usedIds.iterator();
	}
			
}
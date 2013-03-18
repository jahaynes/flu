package model;

import java.util.*;

class ModelViewFactory<M, V> {
		
	/* Id */
	private SortedSet<Integer> usedIds = new TreeSet<Integer>();
	private List<Integer> freeIds = new LinkedList<Integer>();
	
	/* All */
	private List<M> allStocks = new Vector<M>();
	private List<V> allViews = new Vector<V>();
	
	private Integer consumeId() {			
		int nextId;			
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

	public synchronized void create(String name) {
		Integer nextId = consumeId();
		
		// Expand the storage to accommodate the direct reference
		while(allStocks.size() < nextId + 1) {
			allStocks.add(null);
		}
		
		allStocks.set(nextId, new M(nextId, name));
		usedIds.add(nextId);
	}
	
	public synchronized M get(Integer id) {
		assert usedIds.contains(id);
		return allStocks.get(id);
	}
	
	public synchronized void remove(Integer id) {
		allStocks.set(id, null);
		usedIds.remove(id);
		freeIds.add(id);
	}
}

public class Stock {
	
	public static void createStock(String name) {
		StockFactory.create(name);
	}
	
	public static Stock getStock(int id) {
		return StockFactory.get(id);
	}
	
	
	/* Simple dummy class to provide something for the GUI */
	
	private int stockId;
	private String name;
	
	private Stock(int stockId, String name) {
		this.stockId = stockId;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}

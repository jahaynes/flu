package helper;

import influence.Influence;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;
import stock.Stock;
import view.ElementView;

public class ModelViewFactory{
	
	/* Id */
	private static final SortedSet<Integer> usedIds = new TreeSet<Integer>();
	private static final List<Integer> freeIds = new LinkedList<Integer>();
	
	/* All */
	private static final List<Stock> allStocks = new Vector<Stock>();
	private static final List<Influence> allInfluences = new Vector<Influence>();	
	private static final List<ElementView> allViews = new Vector<ElementView>();
	
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

	public static synchronized int createInfluence() {
		final int nextId = consumeId();
		String name = "INFLUENCE " + nextId;
		
		// Expand the storage to accommodate the direct reference
		while(allInfluences.size() < nextId + 1) {
			allInfluences.add(null);
		}
		
		while(allViews.size() < nextId + 1) {
			allViews.add(null);
		}
		
		allInfluences.set(nextId, new Influence(name));
		allViews.set(nextId, new ElementView(nextId, name));
		usedIds.add(nextId);
		return nextId;
	}
	
	public static synchronized int createStock() {
		final int nextId = consumeId();
		String name = "STOCK " + nextId;
		
		// Expand the storage to accommodate the direct reference
		while(allStocks.size() < nextId + 1) {
			allStocks.add(null);	
		}
		
		while(allViews.size() < nextId + 1) {
			allViews.add(null);
		}
		
		allStocks.set(nextId, new Stock(name));
		allViews.set(nextId, new ElementView(nextId, name));
		usedIds.add(nextId);
		return nextId;
	}

	public static synchronized Stock getStock(Integer id) {
		assert usedIds.contains(id);
		return allStocks.get(id);
	}
	
	public static synchronized ElementView getView(Integer id) {
		assert usedIds.contains(id);
		return allViews.get(id);
	}
	
	public static synchronized void remove(Integer id) {
		allStocks.set(id, null);
		allViews.set(id, null);
		usedIds.remove(id);
		freeIds.add(id);
	}
	
	public static boolean isNameAcceptable(String name) {
		//For now, just check the name doesn't already exist
		for(Stock s : allStocks) {
			if(s != null && s.getName().toUpperCase().trim().equals(name.toUpperCase().trim())) {
				return false;
			}
		}
		return true;
	}
	
	public static Iterator<Integer> getIterator() {
		return usedIds.iterator();
	}

	public static String getName(int selectedIndex) {
		if(allStocks.get(selectedIndex) == null) {
			return allInfluences.get(selectedIndex).getName();
		} else {
			return allStocks.get(selectedIndex).getName();
		}
	}
			
}
package helper;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import stock.Stock;
import view.ElementView;

public class AbstractFactory {

	protected static final SortedSet<Integer> usedIds = new TreeSet<Integer>();
	protected static final List<Integer> freeIds = new LinkedList<Integer>();
	protected static final List<Element> allElements = new Vector<Element>();
	protected static final List<ElementView> allViews = new Vector<ElementView>();
	
	protected Integer consumeId() {			
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
	
	public synchronized int create() {
		final int nextId = consumeId();
		String name = "STOCK " + nextId;
		
		// Expand the storage to accommodate the direct reference
		while(allElements.size() < nextId + 1) {
			allElements.add(null);	
		}
		
		while(allViews.size() < nextId + 1) {
			allViews.add(null);
		}
		
		allElements.set(nextId, new Stock(name));
		allViews.set(nextId, new ElementView(nextId, name));
		usedIds.add(nextId);
		return nextId;
	}
	
	public synchronized ElementView getView(Integer id) {
		assert usedIds.contains(id);
		return allViews.get(id);
	}

	public synchronized Element get(Integer id) {
		assert allElements.contains(id);
		return allElements.get(id);
	}
	
	public synchronized void remove(Integer id) {
		allElements.set(id, null);
		allViews.set(id, null);
		usedIds.remove(id);
		freeIds.add(id);
	}
	
	public Iterator<Integer> getIterator() {
		return usedIds.iterator();
	}
	
	public String getName(int selectedIndex) {
		return allElements.get(selectedIndex).getName();
	}
	
}

package helper;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import view.ElementView;

public class AbstractModelViewFactory {

	protected final SortedSet<Integer> usedIds = new TreeSet<Integer>();
	protected final List<Integer> freeIds = new LinkedList<Integer>();
	protected final List<Element> allElements = new Vector<Element>();
	protected final List<ElementView> allViews = new Vector<ElementView>();
	
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
	
	protected synchronized int create() {
		final int consumedId = consumeId();
		// Expand the storage to accommodate the direct reference
		while(allElements.size() < consumedId + 1) {
			allElements.add(null);	
		}
		while(allViews.size() < consumedId + 1) {
			allViews.add(null);
		}
		return consumedId;
	}
	
	protected Element get(Integer id) {
		assert allElements.contains(id);
		return allElements.get(id);
	}
	
	public ElementView getView(Integer id) {
		assert usedIds.contains(id);
		return allViews.get(id);
	} 
	
	public static ElementView getView(ElementType elementType, Integer id) {
		switch(elementType) {
		case INFLUENCE:
			return InfluenceModelViewFactory.getInstance().getView(id);
		case STOCK:
			return StockModelViewFactory.getInstance().getView(id);
		default:
			//TODO: better error case
			throw new NotImplementedException();	
		}
	}
	
	public void remove(Integer id) {
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
		
	public static boolean isNameAcceptable(String name) {
		Iterator<Integer> stockIterator = StockModelViewFactory.getInstance().getIterator();
		while(stockIterator.hasNext()) {
			Element e = StockModelViewFactory.getInstance().get(stockIterator.next());
			if(e.getName().toUpperCase().trim().equals(name.toUpperCase().trim())) {
				return false;
			}
		}
		Iterator<Integer> influenceIterator = InfluenceModelViewFactory.getInstance().getIterator();
		while(influenceIterator.hasNext()) {
			Element e = InfluenceModelViewFactory.getInstance().get(influenceIterator.next());
			if(e.getName().toUpperCase().trim().equals(name.toUpperCase().trim())) {
				return false;
			}
		}
		return true;
	}	
}

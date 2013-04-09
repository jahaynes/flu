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

public class ModelViewFactory extends AbstractFactory {
	
	private static ModelViewFactory instance;
	public static synchronized ModelViewFactory getInstance() {
		if(instance == null) {
			instance = new ModelViewFactory();
		}
		return instance;
	}
	
	private ModelViewFactory() {
		
	}
		
	private static final SortedSet<Integer> usedInfluenceIds = new TreeSet<Integer>();
	private static final List<Integer> freeInfluenceIds = new LinkedList<Integer>();
	private static final List<Influence> allInfluences = new Vector<Influence>();	
	private static final List<ElementView> allInfluenceViews = new Vector<ElementView>();
	
	private Integer consumeInfluenceId() {			
		final int nextId;			
		if(freeInfluenceIds.size() > 0) {
			nextId = freeInfluenceIds.remove(0);
		} else { 
			if (usedInfluenceIds.size() > 0) {
				nextId = usedInfluenceIds.last() + 1;
			} else {
				nextId = 0;
			}
		}
		usedInfluenceIds.add(nextId);
		return nextId;
	}
	
	public synchronized int createInfluence() {
		final int nextId = consumeInfluenceId();
		String name = "INFLUENCE " + nextId;
		
		// Expand the storage to accommodate the direct reference
		while(allInfluences.size() < nextId + 1) {
			allInfluences.add(null);
		}
		
		while(allInfluenceViews.size() < nextId + 1) {
			allInfluenceViews.add(null);
		}
		
		allInfluences.set(nextId, new Influence(name));
		allInfluenceViews.set(nextId, new ElementView(nextId, name));
		usedInfluenceIds.add(nextId);
		return nextId;
	}
			
	public synchronized ElementView getInfluenceView(Integer id) {
		assert usedInfluenceIds.contains(id);
		return allInfluenceViews.get(id);
	}
			
	public synchronized void removeInfluence(Integer id) {
		allInfluences.set(id, null);
		allInfluenceViews.set(id, null);
		usedInfluenceIds.remove(id);
		freeInfluenceIds.add(id);
	}
	
	public boolean isNameAcceptable(String name) {
		//For now, just check the name doesn't already exist
		//TODO: check out the direct cast below
		for(Element e : allElements) {
			Stock s = (Stock)e;
			if(s != null && s.getName().toUpperCase().trim().equals(name.toUpperCase().trim())) {
				return false;
			}
		}
		
		for(Influence i : allInfluences) {
			if(i != null && i.getName().toUpperCase().trim().equals(name.toUpperCase().trim())) {
				return false;
			}
		}
		
		return true;
	}
		
	public Iterator<Integer> getInfluenceIterator() {
		return usedInfluenceIds.iterator();
	}
	
	public String getInfluenceName(int selectedIndex) {
		return allInfluences.get(selectedIndex).getName();
	} 
			
}
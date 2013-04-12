package influence;

import helper.Element;
import helper.InfluenceModelViewFactory;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Influence extends Element {
		
	private Set<Integer> connectedStockIds;
		
	public static Iterator<Integer> getValidIds() {
		return InfluenceModelViewFactory.getInstance().getIterator();
	}
	
	/* Simple dummy class to provide something for the GUI */
	public Influence(String name) {
		this.name = name;
		connectedStockIds = new TreeSet<Integer>();
	}
	
	public void connectToStock(int stockId) {
		connectedStockIds.add(stockId);
	}
	
	public void disconnectFromStock(int stockId) {
		connectedStockIds.remove(stockId);
	}
	
	public Set<Integer> getConnectedStockIds() {
		return connectedStockIds;
	}
	
}

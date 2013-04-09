package influence;

import helper.Element;
import helper.InfluenceModelViewFactory;
import java.util.Iterator;

public class Influence extends Element {
		
	public static Iterator<Integer> getValidIds() {
		return InfluenceModelViewFactory.getInstance().getIterator();
	}
	
	/* Simple dummy class to provide something for the GUI */
	public Influence(String name) {
		this.name = name;
	}

}

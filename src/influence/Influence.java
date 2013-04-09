package influence;

import helper.Element;
import helper.ModelViewFactory;
import java.util.Iterator;

public class Influence extends Element {

	public static Iterator<Integer> getValidIds() {
		return ModelViewFactory.getInstance().getInfluenceIterator();
	}
	
	private String name;
	
	/* Simple dummy class to provide something for the GUI */
	public Influence(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}

package helper;

import view.ElementView;
import influence.Influence;

public class InfluenceModelViewFactory extends AbstractModelViewFactory {
	
	private static InfluenceModelViewFactory instance;
	public static synchronized InfluenceModelViewFactory getInstance() {
		if(instance == null) {
			instance = new InfluenceModelViewFactory();
		}
		return instance;
	}
	
	private InfluenceModelViewFactory() {
	}
						
	public Influence get(Integer id) {
		return (Influence)super.get(id);
	}			
	
	public int create() {
		int nextId = super.create();
		String name = "INFLUENCE " + nextId;
		allElements.set(nextId, new Influence(name));
		allViews.set(nextId, new ElementView(nextId, name));
		usedIds.add(nextId);
		return nextId;
	}
	
}
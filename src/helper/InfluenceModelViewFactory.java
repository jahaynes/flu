package helper;

import influence.Influence;
import influence.InfluenceView;

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
	
	public InfluenceView getView(Integer id) {
		assert usedIds.contains(id);
		return (InfluenceView)allViews.get(id);
	} 
		
	public int create() {
		int nextId = super.create();
		String name = "INFLUENCE " + nextId;
		allElements.set(nextId, new Influence(name));
		allViews.set(nextId, new InfluenceView(name, nextId));
		usedIds.add(nextId);
		return nextId;
	}
	
}
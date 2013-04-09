package helper;

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
	
}
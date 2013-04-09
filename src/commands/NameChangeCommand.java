package commands;

import explorer.Explorer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import view.Canvas;
import helper.ElementType;
import helper.InfluenceModelViewFactory;
import helper.StockModelViewFactory;

public class NameChangeCommand implements Command {
	
	private ElementType elementType;
	private int elementId;
	private String oldName;
	private String newName;
	
	public NameChangeCommand(ElementType elementType, int elementId, String newName) {
		this.elementType = elementType;
		this.elementId = elementId;
		this.newName = newName;
	}
	
	@Override
	public void execute() {
		switch(elementType) {
		case INFLUENCE:
			this.oldName = InfluenceModelViewFactory.getInstance().get(elementId).getName();
			InfluenceModelViewFactory.getInstance().get(elementId).setName(newName);
			InfluenceModelViewFactory.getInstance().getView(elementId).setName(newName);
			break;
		case STOCK:
			this.oldName = StockModelViewFactory.getInstance().get(elementId).getName();
			StockModelViewFactory.getInstance().get(elementId).setName(newName);
			StockModelViewFactory.getInstance().getView(elementId).setName(newName);
			break;
		default:
			//TODO: better error case
			throw new NotImplementedException();
		}				
		Canvas.getInstance().repaint();
	}

	@Override
	public void rollback() {
		
		switch(elementType) {
		case INFLUENCE:
			InfluenceModelViewFactory.getInstance().get(elementId).setName(oldName);
			InfluenceModelViewFactory.getInstance().getView(elementId).setName(oldName);
			break;
		case STOCK:
			StockModelViewFactory.getInstance().get(elementId).setName(oldName);
			StockModelViewFactory.getInstance().getView(elementId).setName(oldName);
			break;
		default:
			//TODO: better error case
			throw new NotImplementedException();
		}		
				
		//If currently exploring the name-changed stock, redisplay it in non-edit mode
		if (Explorer.getInstance().getSelected() == elementId &&
				Explorer.getInstance().getSelectedType() == elementType) {
			Explorer.getInstance().setSelected(elementType, elementId, false);
		}
		
		Canvas.getInstance().repaint();
	}

}
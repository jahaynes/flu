package commands;

import view.Canvas;
import helper.ElementType;
import helper.InfluenceModelViewFactory;
import helper.StockModelViewFactory;

public class CreateCommand implements Command {

	protected int createdId = -1;
	private final ElementType elementType;
	
	public CreateCommand(ElementType elementType) {
		this.elementType = elementType;
	}
	
	@Override
	public void execute() {
		switch(elementType) {
		case INFLUENCE:
			createdId = InfluenceModelViewFactory.getInstance().create();
			break;
		case STOCK:
			createdId = StockModelViewFactory.getInstance().create();
			break;
		default:
			break;
		}		
		Canvas.getInstance().repaint();
	}

	@Override
	public void rollback() {
		switch(elementType) {
		case INFLUENCE:
			InfluenceModelViewFactory.getInstance().remove(createdId);
			break;
		case STOCK:
			StockModelViewFactory.getInstance().remove(createdId);
			break;
		default:
			break;
		}
		Canvas.getInstance().repaint();
	}
	
	public int getNewId() {
		return createdId;
	}

}

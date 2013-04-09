package commands;

import view.Canvas;
import helper.ElementType;
import helper.ModelViewFactory;

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
			createdId = ModelViewFactory.getInstance().createInfluence();
			break;
		case STOCK:
			createdId = ModelViewFactory.getInstance().create();
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
			ModelViewFactory.getInstance().removeInfluence(createdId);
			break;
		case STOCK:
			ModelViewFactory.getInstance().remove(createdId);
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

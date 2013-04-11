package commands;

import java.awt.Point;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import view.Canvas;

import helper.AbstractModelViewFactory;
import helper.ElementType;

public class MoveViewCommand implements Command {

	private ElementType elementType;
	private int elementId;
	private Point oldPosition;
	
	public MoveViewCommand(ElementType elementType, int elementId, Point oldPosition) {
		this.elementType = elementType;
		this.elementId = elementId;
		this.oldPosition = oldPosition;
	}

	@Override
	public void execute() {
		//this shouldn't be executed; just for rollback purposes;
		throw new NotImplementedException();
	}

	@Override
	public void rollback() {
		AbstractModelViewFactory.getView(elementType, elementId).setPosition(oldPosition);
		Canvas.getInstance().repaint();
	}
	
}

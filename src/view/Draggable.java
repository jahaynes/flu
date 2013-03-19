package view;

import java.awt.Point;

public class Draggable {

	protected Point position;
	private int heldX;
	private int heldY;
	
	public void setHoldPosition() {
		this.heldX = position.x ;
		this.heldY = position.y;
	}
	
	public void addToHeldPosition(int dx, int dy) {
		this.position.x = heldX + dx;
		this.position.y = heldY + dy;
	}
	
}

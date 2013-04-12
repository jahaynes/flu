package view;

import java.awt.Point;
import java.util.List;

public class Draggable {

	protected Point position;
	private int heldX;
	private int heldY;
		
	public void setHoldPosition() {
		this.heldX = position.x ;
		this.heldY = position.y;
	}
	
	public void addToHeldPosition(int dx, int dy, List<Point> snapPositions) {
		int newX = heldX + dx;
		int newY = heldY + dy;		
		//Find nearest snap point (in each dimension separately)
		if(snapPositions != null && !snapPositions.isEmpty()) {
			int nearestX = snapPositions.get(0).x;
			int nearestDistX = Math.abs(newX - nearestX);
			for(int i=1;i<snapPositions.size();i++) {
				int newDistX = Math.abs(newX - snapPositions.get(i).x);
				if(newDistX < nearestDistX) {
					nearestX = snapPositions.get(i).x;
					nearestDistX = newDistX;
				}
			}
			int nearestY = snapPositions.get(0).y;
			int nearestDistY = Math.abs(newY - nearestY);
			for(int i=1;i<snapPositions.size();i++) {
				int newDistY = Math.abs(newY - snapPositions.get(i).y);
				if(newDistY < nearestDistY) {
					nearestY = snapPositions.get(i).y;
					nearestDistY = newDistY;
				}
			}
			if(Math.abs(nearestX - newX) < 8) {
				newX = nearestX;
			}
			if(Math.abs(nearestY - newY) < 8) {
				newY = nearestY;
			}				
		}
		
		//If not trying to snap, just move as normal
		this.position.x = newX;
		this.position.y = newY;	
	}
}

package entity;
import java.awt.Point;


import map.MapGenerator;

public class Box extends Entity {
	private static final int FREE_SPACE = 0;
	private static final int OBSTACLE = 1;
	private static final int BOX = 2;
	private static final int GOAL = 3;
	private static final int PLAYER = 4;
	
	public Box(Point startingLoc) {
		super(startingLoc);
	}

	// let the box update the map when it moves
	
	/**
	 * Makes a move and updates map accordingly
	 */
	public boolean move(Move m, MapGenerator map) {
		Point newBoxLoc = null;
		if (m.isUndo()) {
			newBoxLoc = m.getSavedEntityPoint();
		} else {
			m.setEntityMoved(this);
			m.setSavedEntityPoint(new Point(this.loc));
			newBoxLoc = m.getNewPoint(this.loc);
		}
		map.updateMap(BOX, newBoxLoc.x, newBoxLoc.y);
		if (map.getGoalLocs().contains(this.loc)) 
			map.updateMap(GOAL, this.loc.x, this.loc.y);
		else
			map.updateMap(FREE_SPACE, this.loc.x, this.loc.y);
		this.loc.setLocation(newBoxLoc);
	return true;
	}
	
}

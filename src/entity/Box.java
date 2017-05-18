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
	public boolean move(Move m, MapGenerator map) {
		Point newBoxLoc = m.getNewPoint(this.loc);
		map.updateMap(BOX, (int) newBoxLoc.getX(), (int) newBoxLoc.getY());
		if (map.getGoalLocs().contains(this.loc)) 
			map.updateMap(GOAL, (int) this.loc.getX(), (int) this.loc.getY());
		else
			map.updateMap(FREE_SPACE, (int) this.loc.getX(), (int) this.loc.getY());
		this.loc.setLocation(newBoxLoc);
		m.setEntityMoved(this);
	return true;
	}
	
}

package entity;
import java.awt.Point;

import game.Game;
import map.MapGenerator;

public class Box extends Entity {
	public Box(Point startingLoc) {
		super(startingLoc);
	}

	// let the box update the map when it moves
	public boolean move(Move m, MapGenerator map) {
		Point newBoxLoc = null;
		if (m.isUndo()) {
			newBoxLoc = m.getSavedEntityPoint();
		} else {
			m.setEntityMoved(this);
			m.setSavedEntityPoint(new Point(this.loc));
			newBoxLoc = m.getNewPoint(this.loc);
		}
		map.updateMap(Game.BOX, newBoxLoc.x, newBoxLoc.y);
		if (map.getGoalLocs().contains(this.loc)) 
			map.updateMap(Game.GOAL, this.loc.x, this.loc.y);
		else
			map.updateMap(Game.FREE_SPACE, this.loc.x, this.loc.y);
		this.loc.setLocation(newBoxLoc);
	return true;
	}
	
}

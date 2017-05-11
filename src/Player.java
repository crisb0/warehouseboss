import java.awt.Point;

public class Player extends Entity {

	private static final int FREE_SPACE = 0;
	private static final int OBSTACLE = 1;
	private static final int BOX = 2;
	private static final int GOAL = 3;
	private static final int PLAYER = 4;
	
	public Player(Point startingLoc) {
		super(startingLoc);
	}

	/**
	 * returns true player is moved. Also updates map[][] and box loc
	 * @param dir
	 * @param map
	 * @return
	 */
	public boolean move(Move m, Map map) {
		Point newLoc = m.getNewPoint(this.loc); // new player location
		// undo move
		if (m.isUndo()) {
			map.updateMap(PLAYER,(int) newLoc.getX(), (int) newLoc.getY());
			if (map.getGoalLocs().contains(this.loc))	
				map.updateMap(GOAL, (int) this.loc.getX(), (int) this.loc.getY());
			else 
				map.updateMap(FREE_SPACE, (int) this.loc.getX(), (int) this.loc.getY());
			Entity e = m.getEntityMoved(); 
			if (e != null && e instanceof Box) { // if a box was pushed with this move
				e.move(m, map);
			}
			this.loc = newLoc;
			return true;
		// normal move
		} else {
			if (!this.isBetween(0, map.getMap().length, (int) newLoc.getX()) ||
					!this.isBetween(0, map.getMap().length, (int)newLoc.getY())) return false;
			
			if (map.isFreeSpace(newLoc) || map.isGoal(newLoc)) { // if free space, player moves there
				if (map.getGoalLocs().contains(this.loc))	
					map.updateMap(GOAL, (int) this.loc.getX(), (int) this.loc.getY());
				else 
					map.updateMap(FREE_SPACE, (int) this.loc.getX(), (int) this.loc.getY());
				map.updateMap(PLAYER,(int) newLoc.getX(), (int)newLoc.getY());
				this.loc = newLoc;
				return true;
				
			} else if (map.isBox(newLoc)) { //if box, check if point next to box is free or goal then move there
				Point newBoxLoc = m.getNewPoint(newLoc);
				if (!this.isBetween(0, map.getMap().length, (int) newBoxLoc.getX()) ||
						!this.isBetween(0, map.getMap().length, (int) newBoxLoc.getY())) 
					return false;
				
				if (map.isFreeSpace(newBoxLoc) || map.isGoal(newBoxLoc)) {
					Box b = map.getBox(newLoc);
					b.move(m, map);
					if (map.getGoalLocs().contains(this.loc))	
						map.updateMap(GOAL, (int) this.loc.getX(), (int) this.loc.getY());
					else 
						map.updateMap(FREE_SPACE, (int) this.loc.getX(), (int) this.loc.getY());
					map.updateMap(PLAYER, (int) newLoc.getX(), (int) newLoc.getY());
					this.loc = newLoc;
					return true;
				}
			}
		}
		return false;
	}
}

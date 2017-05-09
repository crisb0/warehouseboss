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
	 * @param m
	 * @return
	 */
	public boolean movePlayer(char dir, Map m) {
		Point newLoc = this.getNewPoint(dir, this.loc);
		
		if (!this.isBetween(0, m.getMap().length, (int) newLoc.getX()) ||
				!this.isBetween(0, m.getMap().length, (int)newLoc.getY())) return false;
		
		if (m.isFreeSpace(newLoc)) { // if free space, player moves there
			m.updateMap(FREE_SPACE, (int) this.loc.getX(), (int) this.loc.getY());
//			System.out.println(this.loc.getX().)
			m.updateMap(PLAYER,(int) newLoc.getX(), (int)newLoc.getY());
			this.loc = newLoc;
		}	
		else if (m.isBox(newLoc)) { //if box, check if point next to box is free or goal then move there
			Point boxLoc = this.getNewPoint(dir, newLoc);
			if (!this.isBetween(0, m.getMap().length, (int) boxLoc.getX()) ||
					!this.isBetween(0, m.getMap().length, (int) boxLoc.getY())) return false;
			if (m.isFreeSpace(boxLoc)) {
				Box b = m.getBox(newLoc);
				b.moveBox(dir);
				m.updateMap(FREE_SPACE, (int) this.loc.getX(), (int) this.loc.getY());
				m.updateMap(PLAYER, (int) newLoc.getX(), (int) newLoc.getY());
				m.updateMap(BOX, (int)boxLoc.getX(), (int)boxLoc.getY());
				this.loc = newLoc;
			}
		}

		return false;
	}


}

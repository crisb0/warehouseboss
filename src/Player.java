import java.awt.Point;

public class Player extends Entity {

	public Player(Point startingLoc) {
		super(startingLoc);
	}

	public boolean movePlayer(char dir, Map m) {
		Point newLoc = this.loc;
		switch (dir) { // which point to move to?
		case 'w':
			// check if space to move is occupied
			newLoc.setLocation(this.loc.getX(), this.loc.getY() - 1);
			break;
		case 'a':
			newLoc.setLocation(this.loc.getX() - 1, this.loc.getY());
			break;
		case 's':
			newLoc.setLocation(this.loc.getX(), this.loc.getY() + 1);
			break;
		case 'd':
			newLoc.setLocation(this.loc.getX() + 1, this.loc.getY());
			break;
		}

		if (m.isFreeSpace(newLoc)) { // if free space, player moves there
			this.loc = newLoc;
			//after this, remebmer to update map!!!
		}	
		else if (m.isBox(newLoc)) { //if box, check if point next to box is free or goal then move there
			Point boxLoc = newLoc;
			switch (dir) {
			case 'w':
				boxLoc.setLocation(newLoc.getX(), newLoc.getY()-1);
				break;
			case 'a':
				boxLoc.setLocation(newLoc.getX()-1, newLoc.getY());
				break;
			case 's':
				boxLoc.setLocation(newLoc.getX(), newLoc.getY()+1);
				break;
			case 'd':
				boxLoc.setLocation(newLoc.getX()+1, newLoc.getY());
				break;
			}
			if (m.isFreeSpace(boxLoc)) {
				//get box object
				
				//move box from newLoc into boxLoc
				//move player into newLoc
			}
		}

		return false;
	}

}

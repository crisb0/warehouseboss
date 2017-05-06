import java.awt.Point;


public class Player extends Entity {
	
	public Player(Point curr) {
		this.loc = curr;
	}

//	public setPlayerLoc(Point p) {
//		if (freeSpace(p))this.setLoc(p);
//	}

	public boolean move(char dir) {
		switch (dir) {
			case 'w':
				//check if space to move is occupied
				this.loc.setLocation(this.loc.getX(), this.loc.getY()-1);
				break;
			case 'a':
				this.loc.setLocation(this.loc.getX()-1, this.loc.getY());
				break;
			case 's':
				this.loc.setLocation(this.loc.getX(), this.loc.getY()+1);
				break;
			case 'd': 
				this.loc.setLocation(this.loc.getX()+1, this.loc.getY());
				break;
		}
		
		return false;
	}


}

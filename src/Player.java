import java.awt.Point;


public class Player implements Entity {
	private Point loc;
	
	public Player(Point curr) {
		this.loc = curr;
	}

	public Point getLoc() {
		return this.loc;
	}

	public boolean move(char dir) {
		switch (dir) {
			case 'w':
				//check if space to move is occupied
				this.loc.setLocation(this.loc.getX(), this.loc.getY()+1);
				break;
			case 'a':
				this.loc.setLocation(this.loc.getX(), this.loc.getY());
				break;
			case 's':
				this.loc.setLocation(this.loc.getX(), this.loc.getY());
				break;
			case 'd': 
				this.loc.setLocation(this.loc.getX(), this.loc.getY());
				break;
		}
		
		return false;
	}


}

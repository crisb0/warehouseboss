import java.awt.Point;


public abstract class Entity {
	protected Point loc;
	
	public Entity(Point startingLoc) {
		this.loc = startingLoc;
	}
		
	public Point getLoc() {
		return this.loc;
	}
	
//	/**
//	 * 
//	 * @param dir 'w' 'a' 's' 'd' for up, left, down, right movement
//	 * @return false if unable to move -- obstacle in the way
//	 */
//	public boolean move(char dir) {
//		switch (dir) {
//			case 'w':
//				//check if space to move is occupied
//				this.loc.setLocation(this.loc.getX(), this.loc.getY()-1);
//				break;
//			case 'a':
//				this.loc.setLocation(this.loc.getX()-1, this.loc.getY());
//				break;
//			case 's':
//				this.loc.setLocation(this.loc.getX(), this.loc.getY()+1);
//				break;
//			case 'd': 
//				this.loc.setLocation(this.loc.getX()+1, this.loc.getY());
//				break;
//		}
//		
//		return false;
//	}
}

import java.awt.Point;

public abstract class Entity {
	protected Point loc;

	public Entity(Point startingLoc) {
		this.loc = startingLoc;
	}

	public Point getLoc() {
		return this.loc;
	}

	 /**
	 *
	 * @param dir 'w' 'a' 's' 'd' for up, left, down, right movement
	 * @param p is the curr Point to be moved dir
	 * @return 
	 */
	 public Point getNewPoint(char dir, Point p) {
		 Point curr = new Point(p);
		 switch (dir) {
		 case 'w':
		 curr.setLocation(curr.getX(), curr.getY()-1);
		 break;
		 case 'a':
		 curr.setLocation(curr.getX()-1, curr.getY());
		 break;
		 case 's':
		 curr.setLocation(curr.getX(), curr.getY()+1);
		 break;
		 case 'd':
		 curr.setLocation(curr.getX()+1, curr.getY());
		 break;
		 }
		 return curr;
	 }
	 
	 public boolean isBetween(int a, int b, int c) {
		    return b >= a ? c >= a && c <= b : c > b && c < a;
		}
}

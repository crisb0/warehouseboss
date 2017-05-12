import java.awt.Point;

public abstract class Entity {
	protected Point loc;

	public Entity(Point startingLoc) {
		this.loc = startingLoc;
	}
	
	public abstract boolean move(Move m, Map map);
	
	//public abstract int getType();
	
	public Point getLoc() {
		return new Point(this.loc);
	}
	
	public boolean isBetween(int a, int b, int c) {
		return b >= a ? c >= a && c <= b : c > b && c < a;
	}
}

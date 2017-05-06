import java.awt.Point;


public abstract class Entity {
	protected Point loc;
		
	public Point getLoc() {
		return this.loc;
	}
	
	protected void setLoc(Point newLoc) {
		this.loc.setLocation(newLoc);
	}
}

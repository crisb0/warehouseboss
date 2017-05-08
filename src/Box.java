import java.awt.Point;

public class Box extends Entity {

	public Box(Point startingLoc) {
		super(startingLoc);
	}


	public boolean moveBox(char dir) {
		Point newP = this.getNewPoint(dir, this.loc);
		this.loc.setLocation(newP);

		return false;
	}
}

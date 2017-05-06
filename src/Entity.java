import java.awt.Point;


public interface Entity {
	
	public Point getLoc();
	
	public boolean move(char dir);
}

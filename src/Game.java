import java.awt.Point;
// generates puzzle map, reads user input to play game and processes
public class Game {
	private Player p1;
	private int map[][];
	
	private static final int FREE_SPACE = 0;
	private static final int OBSTACLE = 1;
	private static final int BOX = 2;
	private static final int GOAL = 3;
	
	public Game(MapGenerator map) {
		this.p1 = new Player(map.getstartLocPlayer());
		this.map = map.getGrid();
	}
	
	public static void main(String[] args) {
	}

	/**
	 * 
	 * @return true if Point(x,y) is free -- no obstacle or entity currently occupying space
	 */
	public boolean freeSpace(Point p) {
		if (map[(int) p.getX()][(int) p.getY()] == FREE_SPACE) return true;
		return false;
	}
}

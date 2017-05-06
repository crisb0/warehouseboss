import java.awt.Point;
// generates puzzle map, reads user input to play game and processes
public class Game {
	private Player p1;
	private int map[][];
	
	public Game(MapGenerator map) {
		this.p1 = new Player(map.getPlayerLocation());
		this.map = map.getGrid();
	}
	
	public static void main(String[] args) {
	}

	/**
	 * 
	 * @return true if Point(x,y) is free -- no obstacle or entity currently occupying space
	 */
	public boolean checkFreeSpace() {
		return false;
	}
}

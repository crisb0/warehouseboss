import java.awt.EventQueue;
import java.awt.Point;

// generates puzzle map, reads user input to play game and processes
public class Game {
	private Player p1;
	private Map map;

	public static final int FREE_SPACE = 0;
	public static final int OBSTACLE = 1;
	public static final int BOX = 2;
	public static final int GOAL = 3;
	public static final int PLAYER = 4;

	public Game(Map m) {
		this.p1 = m.getPlayer();
		this.map = m;
	}
	
	public int[][] getGrid(){
		return this.map.getMap();
	}
	
	public Map getMap(){
		return this.map;
	}
	
	public Player getPlayer(){
		return this.p1;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainUI mainUI = new MainUI();
				mainUI.setVisible(true);
			}
		});
		
		MapGenerator map = new MapGenerator(null);
//		map.generate();
		Map m = new Map(map);
		Game g = new Game(m);
		
//		System.out.println("hello" + g.p1.getLoc().getX() + ", " + g.p1.getLoc().getY());
//		m.printGrid();
//		g.p1.movePlayer('s', m);
//		m.printGrid();
//		System.out.println(g.p1.getLoc().getX() + ", " + g.p1.getLoc().getY());
		
 	}

	public enum Difficulty {
		EASY, NORMAL, HARD, WOBCKE
	}
}

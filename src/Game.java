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
 	}

	public enum Difficulty {
		EASY, NORMAL, HARD, WOBCKE
	}
}

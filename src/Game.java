import java.awt.EventQueue;
import java.awt.Point;

// generates puzzle map, reads user input to play game and processes
public class Game {
	private Player p1;
	private Map map;

	private static final int FREE_SPACE = 0;
	private static final int OBSTACLE = 1;
	private static final int BOX = 2;
	private static final int GOAL = 3;
	private static final int PLAYER = 4;

	public Game(Map m) {
		this.p1 = new Player(null);
		this.map = m;
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
		Map m = new Map(map);

	}

	public enum Difficulty {
		EASY, NORMAL, HARD, WOBCKE
	}
}

import java.awt.EventQueue;
import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Deque;

// generates puzzle map, reads user input to play game and processes
public class Game {
	private Player p;
	private Map map;
	private Deque<Move> prevMoves;
	public static final int FREE_SPACE = 0;
	public static final int OBSTACLE = 1;
	public static final int BOX = 2;
	public static final int GOAL = 3;
	public static final int PLAYER = 4;

	public Game(Map m) {
		this.p = m.getPlayer();
		this.map = m;
		this.prevMoves = new ArrayDeque<Move>();
	}
	
	public int[][] getGrid(){
		return this.map.getMap();
	}
	
	public Map getMap(){
		return this.map;
	}
	
	public Player getPlayer(){
		return this.p;
	}
	
	public boolean move(char dir) {
		Move newMove = new Move(dir);
		if(p.move(newMove, map)) {
			prevMoves.push(newMove);
			return true;
		}
		return false;
		
	}
	
	public boolean undoMove() {
		if(!prevMoves.isEmpty()) {
			Move undoMove = prevMoves.pop();
			undoMove.setUndo();
			p.move(undoMove, map);
			return true;
		}
	return false;
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

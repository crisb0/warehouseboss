package game;
import java.util.ArrayDeque;
import java.util.Deque;

import entity.Move;
import entity.Player;
import map.Map;

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
	
	public enum Difficulty {
		EASY, NORMAL, HARD, NIGHTMARE
	}
}

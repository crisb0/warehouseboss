package game;
import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import entity.Box;
import entity.Move;
import entity.Player;
import map.Map;
import map.MapGenerator;
import map.TutorialMap;

// generates puzzle map, reads user input to play game and processes
public class Game {
	private Player p;
	private MapGenerator map;
//	private boxLocs
	private Deque<Move> prevMoves;
	
	private int width;
	private int height;
	
	public static final int FREE_SPACE = 0;
	public static final int OBSTACLE = 1;
	public static final int BOX = 2;
	public static final int GOAL = 3;
	public static final int PLAYER = 4;

//	public Game(Map m) {
//		this.p = m.getPlayer();
//		this.map = m;
//		this.prevMoves = new ArrayDeque<Move>();
//	}
	
	public Game(MapGenerator m) {
//		System.out.println("hello1");
		this.p = m.getPlayer();
//		System.out.println(p.getLoc().getX() + " " + p.getLoc().getY());
		this.map = m;
		this.prevMoves = new ArrayDeque<Move>();
		this.findMaxLengths();
	}
	
	
	/**
	 * We go through the whole grid to calculate the maximum
	 * width and height of the grid.
	 */
	public void findMaxLengths(){
		int[][] grid = this.getGrid();
		this.width = grid.length;
		this.height = 0;
		
		for(int x = 0; x < this.width; x++){
			if(grid[x].length > this.height){
				this.height = grid[x].length;
			}
		}
	}
	
	public void addBox(int x, int y){
		this.map.addBox(x, y);
	}
	
	public int getMaxWidth(){
		return this.width;
	}
	
	public int getMaxHeight(){
		return this.height;
	}
	
	public int[][] getGrid(){
		return this.map.getGrid();
	}
	
	public MapGenerator getMap(){
		return this.map;
	}
	
	public Player getPlayer(){
		return this.p;
	}
	
	public List<Point> getBoxLocs() {
		ArrayList<Box> boxes = (ArrayList<Box>) this.map.getBoxLocs();
		ArrayList<Point> points = new ArrayList<>();
		
		for(Box b : boxes){
			points.add(b.getLoc());
		}
		
		return points;
	}
	
	public List<Point> getGoalLocs() {
		return this.map.getGoalLocs();
	}
	
	public boolean move(char dir) {
		Move newMove = new Move(dir);
		if (p.move(newMove, map)) {
			if (newMove.getEntityMoved() != null) {
				prevMoves.push(newMove);
			}
			return true;
		}
		return false;
		
	}
	
	public void updateMap(int code, int x, int y) {
		this.map.updateMap(code, x, y);
	}
	
	public boolean undoMove() {
		if (!prevMoves.isEmpty()) {
			Move undoMove = prevMoves.pop();
			undoMove.setUndo();
			p.move(undoMove, map);
			return true;
		}
		return false;
	}
	
	public boolean canUndoMoves(){
		return !prevMoves.isEmpty();
	}
	
	public void purgeUndos(){
		this.prevMoves.clear();
	}
	
	public enum Difficulty {
		EASY, NORMAL, HARD, NIGHTMARE
	}
}

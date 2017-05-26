package game;
import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import entity.Box;
import entity.Move;
import entity.Player;
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
	
	public Game(MapGenerator m) {
		this.p = m.getPlayer();
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
	
	/**
	 * adds box to list of boxes
	 * @param x - x co-ordinate
	 * @param y - y co-ordinate
	 */
	public void addBox(int x, int y){
		this.map.addBox(x, y);
	}
	/**
	 * returns max height of grid
	 * @return
	 */
	public int getMaxWidth(){
		return this.width;
	}
	/**
	 * returns max height of grid
	 * @return
	 */
	public int getMaxHeight(){
		return this.height;
	}
	/**
	 * gets grid
	 * @return
	 */
	public int[][] getGrid(){
		return this.map.getGrid();
	}
	/**
	 * gets map
	 * @return
	 */
	public MapGenerator getMap(){
		return this.map;
	}
	/**
	 * returns player
	 * @return
	 */
	public Player getPlayer(){
		return this.p;
	}
	/**
	 * returns a list of points which represent the coordinates of all box locations
	 * @return
	 */
	public List<Point> getBoxLocs() {
		ArrayList<Box> boxes = (ArrayList<Box>) this.map.getBoxLocs();
		ArrayList<Point> points = new ArrayList<>();
		
		for(Box b : boxes){
			points.add(b.getLoc());
		}
		
		return points;
	}
	/**
	 * returns a list of goal locations
	 * @return
	 */
	public List<Point> getGoalLocs() {
		return this.map.getGoalLocs();
	}
	/**
	 * Takes in direction in which player wants to move in 
	 * adds this to a list of moves
	 * returns true if we can move
	 * returns false if we cannot
	 * @param dir
	 * @return
	 */
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
	/**
	 * Updates map to be new entity
	 * 
	 * @param code - integer representation of new entity
	 * @param x - x co-ordinate
	 * @param y - y co-ordinate
	 */
	public void updateMap(int code, int x, int y) {
		this.map.updateMap(code, x, y);
	}
	/**
	 * undos a move
	 * 
	 * @return true if undo is carried out
	 * @return false if undo cannot be done
	 */
	public boolean undoMove() {
		if (!prevMoves.isEmpty()) {
			Move undoMove = prevMoves.pop();
			undoMove.setUndo();
			p.move(undoMove, map);
			return true;
		}
		return false;
	}
	
	/**
	 * checks if we can even undo a move
	 * @return true if prevMoves is not empty
	 * @return false if prevMoves is empty
	 */
	public boolean canUndoMoves(){
		return !prevMoves.isEmpty();
	}
	/**
	 * clears moves done before
	 */
	public void purgeUndos(){
		this.prevMoves.clear();
	}
	
	public enum Difficulty {
		EASY, NORMAL, HARD, NIGHTMARE
	}
}

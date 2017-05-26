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

/**
 * This is the main game. It holds most of the global variables that are to be
 * used. It also acts the bridge between the user interface and backend map
 * generation.
 * 
 * @author <insert zid>
 */
public class Game {
	private Player p;
	private MapGenerator map;
	private Deque<Move> prevMoves;
	
	private int width;
	private int height;
	
	public static final int FREE_SPACE = 0;
	public static final int OBSTACLE = 1;
	public static final int BOX = 2;
	public static final int GOAL = 3;
	public static final int PLAYER = 4;
	
	/*
	 * This for thread signaling. If the player leaves the game
	 * we will signal this to be false and close all threads.
	 * (For map generation)
	 */
	public static boolean isLoading = false;
	
	/**
	 * Construct the game and set the initial parameters
	 * @param m The class that will generate the level
	 */
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
	 * Add a box object to the level
	 * @precondition x is within the grid
	 * @precondition y is within the grid
	 * @param x The x coordinate
	 * @param y The y coordinate
	 */
	public void addBox(int x, int y){
		this.map.addBox(x, y);
	}
	
	/**
	 *
	 * @return The width of the grid
	 */
	public int getMaxWidth(){
		return this.width;
	}
	
	/**
	 * 
	 * @return The maximum height of the grid
	 */
	public int getMaxHeight(){
		return this.height;
	}
	
	/**
	 * 
	 * @return The whole grid object
	 */
	public int[][] getGrid(){
		return this.map.getGrid();
	}
	
	/**
	 * 
	 * @return The map generation object
	 */
	public MapGenerator getMap(){
		return this.map;
	}
	
	/**
	 * 
	 * @return The player object
	 */
	public Player getPlayer(){
		return this.p;
	}
	
	/**
	 * 
	 * @return All of the location of the boxes
	 */
	public List<Point> getBoxLocs() {
		/*
		 * Since the box locations are casted as box objects. We will
		 * need to extract out the Point objects from the boxes first
		 * before returning it.
		 */
		ArrayList<Box> boxes = (ArrayList<Box>) this.map.getBoxLocs();
		ArrayList<Point> points = new ArrayList<>();
		
		for(Box b : boxes){
			points.add(b.getLoc());
		}
		
		return points;
	}
	
	/**
	 * 
	 * @return The location of all of the goals
	 */
	public List<Point> getGoalLocs() {
		return this.map.getGoalLocs();
	}
	
	/**
	 * Register the player's move in the map and change the
	 * map state according to the new player position.
	 * 
	 * If the new player position is invalid, there will be
	 * no chance and thus, the function will return false to
	 * indicate it was an invalid move.
	 * 
	 * @param dir The direction in which the player moves
	 * @return A flag indicating if the move was valid or not
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
	 * Change the level by changing a section of the grid
	 * 
	 * @precondition Code is a valid code
	 * @precondition x is within the grid
	 * @precondition y is within the grid
	 * @param code The type of object to change the section to
	 * @param x The x coordinate
	 * @param y The y coordinate
	 */
	public void updateMap(int code, int x, int y) {
		this.map.updateMap(code, x, y);
	}
	
	/**
	 * Returns the map to the previous state
	 * 
	 * @return A flag indicating whether the undo was
	 * successful or not.
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
	 *
	 * @return A flag indicating whether the player is able
	 * to undo a move.
	 */
	public boolean canUndoMoves(){
		return !prevMoves.isEmpty();
	}
	
	/**
	 * Clear all previous player states from memory
	 */
	public void purgeUndos(){
		this.prevMoves.clear();
	}
	
	/**
	 * This is used to tell the game which difficulty it is
	 *
	 */
	public enum Difficulty {
		EASY, NORMAL, HARD, NIGHTMARE
	}
}

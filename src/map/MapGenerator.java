package map;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import entity.Block;
import entity.Box;
import entity.Player;

public class MapGenerator {
	
	private static final int FREE_SPACE = 0;
	private static final int OBSTACLE = 1;
	private static final int BOX = 2;
	private static final int GOAL = 3;
	private static final int PLAYER = 4;
	
	private static final int DIMENSIONS = 8;
	private Block[][] puzzle = new Block[DIMENSIONS][DIMENSIONS];
	private Point playerLocation;
	private Player player;
	private List<Box> boxLocs;
	private List<Point> goalLocs;
	
	public MapGenerator(){
		CreateMap cm  = new CreateMap();
		CreateSolution cs = new CreateSolution(cm.getPuzzle(), cm.getGoalLocs());
		// OR
//		CreateSolution cs = new CreateSolution(cm.preDefMap(), cm.getGoalLocs());
		this.puzzle = cs.getPuzzle();
		this.playerLocation = cs.getPlayer();
		this.boxLocs = cs.getBoxLocs();
		this.goalLocs = cs.getGoalLocs();
		this.player = new Player(playerLocation);
		/*FindSolution fs = new FindSolution(cm.getPuzzle(), cm.getGoalLocs());
		this.puzzle = fs.getPuzzle();
		this.playerLocation = fs.getPlayerLocation();
		this.boxLocs = fs.getBoxLocs();
		this.goalLocs = fs.getGoalLocs();
		this.player = new Player(playerLocation);*/
	}

	public void displayPuzzle() {
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				System.out.print(this.puzzle[j][i].getType());
			}
			System.out.println();
		}
	}

	public int[][] getGrid() {
		int[][] grid = new int[8][8];
		for(int i = 0; i < this.puzzle.length; i++){
			for(int j = 0; j < this.puzzle[0].length; j++){
				if(this.puzzle[i][j].getType() != 9){
					grid[i][j] = this.puzzle[i][j].getType();
				} else {
					grid[i][j] = 0;
				}
			}
		}
		return grid;
	}
	
	/**
	 * updates Map[x][y] to equal CODE(0,1,2,3,4)
	 */
	public void updateMap(int code, int x, int y) {
//		this.getGrid()[x][y] = code;
		this.puzzle[x][y] = new Block(code, x, y);
	}

	public Box getBox(Point p) {
		Box b = null;
		if (this.getGrid()[(int) p.getX()][(int) p.getY()] == BOX) {
			for (Iterator<Box> i = boxLocs.iterator(); i.hasNext();) {
				Box x = i.next();
				if (x.getLoc().getX() == p.getX() && x.getLoc().getY() == p.getY()) {
					b = x;
				}
			}
		}
		return b;
	}

	/**
	 * 
	 * @return true if Point(x,y) is free -- no obstacle or entity currently
	 *         occupying space
	 */
	public boolean isFreeSpace(Point p) {
		try {if (this.getGrid()[(int) p.getX()][(int) p.getY()] == FREE_SPACE)
			return true;
		}
		catch (ArrayIndexOutOfBoundsException e) {}
		return false;
	}

	/**
	 * 
	 * @param p
	 * @return true if point p is a box
	 */
	public boolean isBox(Point p) {
		try {
		if (this.getGrid()[(int) p.getX()][(int) p.getY()] == BOX)
			return true;
		}
		catch (ArrayIndexOutOfBoundsException e) {}
		return false;
	}

	/**
	 * 
	 * @param p
	 * @returns true if point p is a goal
	 */
	public boolean isGoal(Point p) {
		try {
		if (this.getGrid()[(int) p.getX()][(int) p.getY()] == GOAL)
			return true;
		}
		catch (ArrayIndexOutOfBoundsException e) {}
		return false;
	}

	public Point getStartingPlayerLoc() {
		return this.playerLocation;
	}

	public List<Box> getBoxLocs() {
		return this.boxLocs;
	}

	public List<Point> getGoalLocs() {
		return this.goalLocs;
	}

	public Player getPlayer() {
		return player;
	}

}

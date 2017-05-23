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
	
	protected static final int FREE_SPACE = 0;
	protected static final int OBSTACLE = 1;
	protected static final int BOX = 2;
	protected static final int GOAL = 3;
	protected static final int PLAYER = 4;
	
	private static final int DIMENSIONS = 8;
	private Block[][] puzzle = new Block[DIMENSIONS][DIMENSIONS];
	private Point playerLocation;
	private List<Box> boxLocs;
	private List<Point> goalLocs;
	private Player player;
	
	public MapGenerator(String diff){
		int cost = 0;
		int i =0;
		int intDiff = getIntDiff(diff);
		while(cost <= intDiff){
			CreateMap cm  = new CreateMap();
			//FindSolution fs = new FindSolution(cm.getPuzzle(), cm.getGoalLocs());
			//FindSolution fs = new FindSolution(cm.preDefMap(), cm.getGoalLocs());
			CreateSolution cs = new CreateSolution(cm.getPuzzle(), cm.getGoalLocs());
			this.puzzle = cs.getPuzzle();
			this.playerLocation = cs.getPlayer();
			this.boxLocs = cs.getBoxLocs();
			this.goalLocs = cs.getGoalLocs();
			this.player = new Player((playerLocation));
			cost = cs.getCost();
			System.out.println(i);
			i++;
		}
			
	}

	private int getIntDiff(String diff) {
		if(diff.equals("EASY")){
			return 5;
		} else if(diff.equals("HARD")){
			return 15;
		} else {
			return 10;
		}
	}

	public void displayPuzzle() {
		for(int i = 0; i < DIMENSIONS; i++){
			for(int j = 0; j < DIMENSIONS; j++){
				System.out.print(this.puzzle[j][i].getType());
			}
			System.out.println();
		}
	}

	public int[][] getGrid() {
		int[][] grid = new int[DIMENSIONS][DIMENSIONS];
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				if(this.puzzle[i][j].getType() != 9){
					grid[i][j] = this.puzzle[i][j].getType();
				} else {
					grid[i][j] = 0;
				}
			}
		}
		return grid;
	}
	
	public void addBox(int x, int y){
		this.boxLocs.add(new Box(new Point(x,y)));
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

	public boolean isPlayer(Point p) {
		try {
		if (this.getGrid()[(int) p.getX()][(int) p.getY()] == PLAYER)
			return true;
		}
		catch (ArrayIndexOutOfBoundsException e) {}
		return false;
	}
}
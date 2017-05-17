package map;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entity.Block;
import entity.Box;

public class MapGenerator {
	
	private Block[][] puzzleB = new Block[10][10];
//	private StartingMap s = new StartingMap();
//	private ArrayList<Template> ts = new ArrayList<Template>();
//	private ArrayList<Block> goals = new ArrayList<Block>();
//	private ArrayList<Block> boxes = new ArrayList<Block>();
	private Point playerLocation;
	private List<Box> boxLocs;
	private List<Point> goalLocs;
	
	public MapGenerator(){
		CreateMap cm  = new CreateMap();
		FindSolution fs = new FindSolution(cm.getPuzzleB(), cm.getGoalLocs());
		this.puzzleB = fs.getPuzzle();
		this.playerLocation = fs.getPlayerLocation();
		this.boxLocs = fs.getBoxLocs();
		this.goalLocs = fs.getGoalLocs();
	}
	
//	public static void main (String[] args) {
//		MapGenerator m = new MapGenerator();
//		m.displayPuzzle();
//	}
	
	public void displayPuzzle() {
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				System.out.print(this.puzzleB[j][i].getType());
			}
			System.out.println();
		}
	}

	public int[][] getGrid() {
		int[][] grid = new int[10][10];
		for(int i = 0; i < this.puzzleB.length; i++){
			for(int j = 0; j < this.puzzleB[0].length; j++){
				if(this.puzzleB[i][j].getType() != 9){
					grid[i][j] = this.puzzleB[i][j].getType();
				} else {
					grid[i][j] = 0;
				}
			}
		}
		return grid;
	}

	public Point getStartingPlayerLoc() {
		// TODO Auto-generated method stub
		return this.playerLocation;
	}

	public List<Box> getBoxLocs() {
		// TODO Auto-generated method stub
		return this.boxLocs;
	}

	public List<Point> getGoalLocs() {
		// TODO Auto-generated method stub
		return this.goalLocs;
	}
}


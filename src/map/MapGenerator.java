package map;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entity.Block;
import entity.Box;

public class MapGenerator {
	
	private static final int DIMENSIONS = 10;
	private Block[][] puzzle = new Block[DIMENSIONS][DIMENSIONS];
	private Point playerLocation;
	private List<Box> boxLocs;
	private List<Point> goalLocs;
	
	public MapGenerator(){
		CreateMap cm  = new CreateMap();
		FindSolution fs = new FindSolution(cm.getPuzzle(), cm.getGoalLocs());
		this.puzzle = fs.getPuzzle();
		this.playerLocation = fs.getPlayerLocation();
		this.boxLocs = fs.getBoxLocs();
		this.goalLocs = fs.getGoalLocs();
	}

	public void displayPuzzle() {
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				System.out.print(this.puzzle[j][i].getType());
			}
			System.out.println();
		}
	}

	public int[][] getGrid() {
		int[][] grid = new int[10][10];
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

	public Point getStartingPlayerLoc() {
		return this.playerLocation;
	}

	public List<Box> getBoxLocs() {
		return this.boxLocs;
	}

	public List<Point> getGoalLocs() {
		return this.goalLocs;
	}
}

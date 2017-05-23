package map;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entity.Block;
import entity.Box;

public class MapGenerator {
	
	private static final int DIMENSIONS = 8;
	private Block[][] puzzle = new Block[DIMENSIONS][DIMENSIONS];
	private Point playerLocation;
	private List<Box> boxLocs;
	private List<Point> goalLocs;
	
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
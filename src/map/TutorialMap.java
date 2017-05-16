package map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Box;

public class TutorialMap extends MapGenerator{
	
	private String preDefMap;
	
	public TutorialMap(){
		this.preDefMap = "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" + 
						 "1 1 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 1\n" + 
						 "1 0 2 0 1 0 0 0 0 0 0 0 0 0 0 0 0 1\n" + 
						 "1 0 1 1 1 0 0 1 1 1 1 1 1 1 0 0 0 1\n" +
						 "1 0 0 0 1 0 0 0 0 0 1 0 0 0 0 0 0 1\n" +
						 "1 0 0 0 1 0 0 0 3 0 1 0 3 0 0 0 0 1\n" +
						 "1 0 0 0 1 0 0 0 0 0 1 0 0 0 0 0 0 1\n" +
						 "1 0 4 0 1 0 0 0 0 0 0 0 0 0 0 0 0 1\n" +
						 "1 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 1\n" +
						 "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n";
	}
	
	@Override
	public int[][] getGrid() {
		int y = 0;
		int[][] grid = new int[18][10];
		
		Scanner scanner = new Scanner(this.preDefMap);
		while (scanner.hasNextLine()) {
			String[] mapCode = scanner.nextLine().split("\\s+");
			
			for(int x = 0; x < mapCode.length; x++){
				grid[x][y] = Integer.parseInt(mapCode[x]);
			}
			
			y++;
		}
		scanner.close();
		
		return grid;
	}
	
	@Override
	public Point getStartingPlayerLoc() {
		return new Point(2, 7);
	}
	
	@Override
	public List<Box> getBoxLocs() {
		ArrayList<Box> boxes = new ArrayList<>();
		boxes.add(new Box(new Point(2, 2)));
		return boxes;
	}
	
	@Override
	public List<Point> getGoalLocs() {
		ArrayList<Point> goals = new ArrayList<>();
		goals.add(new Point(12, 5));
		goals.add(new Point(8, 5));
		return goals;
	}

}

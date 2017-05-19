package map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entity.Block;
import entity.Box;
import entity.Player;

public class TutorialMap extends MapGenerator {
	
	private String preDefMap;
	private int[][] grid;
	
	public TutorialMap(){
		this.preDefMap = "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n" + 
						 "1 1 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 1\n" + 
						 "1 0 2 0 1 0 0 0 0 0 0 0 0 0 0 0 0 1\n" + 
						 "1 0 1 1 1 0 0 1 1 1 1 1 1 1 0 0 0 1\n" +
						 "1 0 0 0 1 0 0 0 0 0 1 0 0 0 0 0 0 1\n" +
						 "1 0 0 0 1 0 0 0 3 0 1 0 3 0 0 0 0 1\n" +
						 "1 0 0 0 1 0 0 0 0 0 1 0 0 0 0 0 0 1\n" +
						 "1 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 1\n" +
						 "1 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 1\n" +
						 "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1\n";
		
		this.grid = new int[18][10];
		
		this.setUpTutorial();
		
		this.boxLocs.add(new Box(new Point(2, 2)));
		this.goalLocs.clear();
		this.goalLocs.add(new Point(12, 5));
		this.goalLocs.add(new Point(8, 5));
		this.playerLocation = new Point(2, 7);
		this.player = new Player(this.playerLocation);
	}
	
	protected void setUpTutorial(){
		int y = 0;
		
		Scanner scanner = new Scanner(this.preDefMap);
		while (scanner.hasNextLine()) {
			String[] mapCode = scanner.nextLine().split("\\s+");
			
			for(int x = 0; x < mapCode.length; x++){
				this.grid[x][y] = Integer.parseInt(mapCode[x]);
			}
			
			y++;
		}
		scanner.close();
	}
	
	@Override
	public int[][] getGrid() {
		return this.grid;
	}
	
	@Override
	public void updateMap(int code, int x, int y) {
		this.grid[x][y] = code;
	}
}

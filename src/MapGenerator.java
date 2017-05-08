import java.awt.Point;
import java.util.List;
import java.util.Random;

public class MapGenerator {
	private Point playerLocation; // this sits in grid
	private List<Box> boxLocs; // this sits in grid
	// private String solution;
	private int[][] grid;
	private static final int FREE_SPACE = 0;
	private static final int OBSTACLE = 1;
	private static final int BOX = 2;
	private static final int GOAL = 3;
	private static final int PLAYER = 4;

	// vers 0.0.1: return predetermined grid
	public MapGenerator(Game.Difficulty gameDiff) {
		/*
		 * DUMMY GRID
		 */
		// Random rand = new Random();
		this.playerLocation = new Point();
		// this should be this.grid = generate();
		// Should we have it as this.grid = generate(gameDiff)? - Alen
		this.grid = new int[10][10];

	}

	public int[][] generate() {
		this.playerLocation.setLocation(1, 3);
		Point b1 = new Point();
		Point g1 = new Point();
		b1.setLocation(5, 3);
		g1.setLocation(8, 8);
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (i == 0 || i == grid.length - 1) {
					grid[i][j] = OBSTACLE;
				} else if (j == 0 || j == grid.length - 1) {
					grid[i][j] = OBSTACLE;
				} else if (i == playerLocation.getX() && j == playerLocation.getY()) {
					grid[i][j] = PLAYER;
				} else if (i == b1.getX() && j == b1.getY()) {
					grid[i][j] = BOX;
				} else if (i == g1.getX() && j == g1.getY()) {
					grid[i][j] = GOAL;
				}
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		return grid;
	}

	public Point getPlayerLocation() {
		return null;
	}

	public int[][] getGrid() {
		return null;
	}
}
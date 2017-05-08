import java.awt.Point;
import java.util.List;

public class Map {
	private int[][] map;
	private List<Box> boxLocs;
	
	private static final int FREE_SPACE = 0;
	private static final int OBSTACLE = 1;
	private static final int BOX = 2;
	private static final int GOAL = 3;

	public Map(MapGenerator grid) {
		this.map = grid.getGrid();
		this.boxLocs = grid.getBoxLocs();
		// this.length = grid.length();
	}

	public int[][] getMap() {
		return this.map;
	}
	

	public Box getBox(Point p) {
		//check if point has box
		Box b = null;
		if (this.map[(int) p.getX()][(int) p.getY()] == BOX) {
			//get object box
//			b = boxLocs.get(p);
			
		}
		return b;
	}

	/**
	 * 
	 * @return true if Point(x,y) is free -- no obstacle or entity currently
	 *         occupying space
	 */
	public boolean isFreeSpace(Point p) {
		if (this.map[(int) p.getX()][(int) p.getY()] == FREE_SPACE)
			return true;
		return false;
	}

	/**
	 * 
	 * @param p
	 * @return true if point p is a box
	 */
	public boolean isBox(Point p) {
		if (this.map[(int) p.getX()][(int) p.getY()] == BOX)
			return true;
		return false;
	}

	/**
	 * 
	 * @param p
	 * @returns true if point p is a goal
	 */
	public boolean isGoal(Point p) {
		if (this.map[(int) p.getX()][(int) p.getY()] == GOAL)
			return true;
		return false;
	}

	public Point firstFreeSpace() {
		Point p = new Point();
		int i = 0, j = 0;
		while (i < this.map.length) {
			while (j < this.map.length) {
				if (this.map[i][j] == FREE_SPACE) {
					p.setLocation(i, j);
				}
				j++;
			}
			i++;
		}
		return p;
	}
}

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Map {
	private int[][] map;
	private List<Box> boxLocs;
	
	private static final int FREE_SPACE = 0;
	private static final int OBSTACLE = 1;
	private static final int BOX = 2;
	private static final int GOAL = 3;
	private static final int PLAYER = 4;

	public Map(MapGenerator grid) {
		this.map = grid.getGrid();
		this.boxLocs = grid.getBoxLocs();
//		this.boxLocs = new ArrayList<>();
	}
	
	public int[][] getMap() {
		return this.map;
	}
	
	public List<Box> getBoxLocs() {
		return this.boxLocs;
	}
	
	/**
	 * updates Map[x][y] to equal CODE(0,1,2,3,4)
	 */
	public void updateMap(int code, int x, int y) {
		this.map[x][y] = code;
	}

	public Box getBox(Point p) {
		//check if point has box
		Box b = null;
		if (this.map[(int) p.getX()][(int) p.getY()] == BOX) {
			//get object box
//			b = boxLocs.get(p);
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
		try {if (this.map[(int) p.getX()][(int) p.getY()] == FREE_SPACE)
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
		if (this.map[(int) p.getX()][(int) p.getY()] == BOX)
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
		if (this.map[(int) p.getX()][(int) p.getY()] == GOAL)
			return true;
		}
		catch (ArrayIndexOutOfBoundsException e) {}
		return false;
	}

}

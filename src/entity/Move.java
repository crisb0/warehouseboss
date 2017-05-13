package entity;
import java.awt.Point;

public class Move {
	private char dir;
	private boolean undo;
	private Entity entityMoved;
	
	public Move(char dir) {
		this.dir = dir;
		undo = false;
		entityMoved = null;
	}
	
	public boolean isUndo() {
		return undo;
	}
	
	public Entity getEntityMoved() {
		return entityMoved;
	}
	
	
	public void setEntityMoved(Entity e) {
		entityMoved = e;
	}
	
	public void setUndo() {
		undo = true;
	}
	
	/**
	 *
	 * @param p is the curr Point to be moved in the direction specified in the Move object
	 * @return 
	 */
	public Point getNewPoint(Point p) {
		Point curr = new Point(p);
		if (undo) { // move in opposite direction if undo flag is set
			switch (this.dir) {
			 	case 'w':
			 			curr.setLocation(curr.getX(), curr.getY()+1);
			 			break;
			 	case 'a':
			 			curr.setLocation(curr.getX()+1, curr.getY());
			 			break;
			 	case 's':
			 			curr.setLocation(curr.getX(), curr.getY()-1);
			 			break;
			 	case 'd':
			 			curr.setLocation(curr.getX()-1, curr.getY());
			 			break;
			 }
		} else {
			switch (this.dir) {
			 	case 'w':
			 			curr.setLocation(curr.getX(), curr.getY()-1);
			 			break;
			 	case 'a':
			 			curr.setLocation(curr.getX()-1, curr.getY());
			 			break;
			 	case 's':
			 			curr.setLocation(curr.getX(), curr.getY()+1);
			 			break;
			 	case 'd':
			 			curr.setLocation(curr.getX()+1, curr.getY());
			 			break;
			}
		}
		return curr;
	}
	
	
}

import java.awt.Point;

public class State {
	private State prevState;
	private Point currBoxLoc;
	private Point currPlayerLoc;
	private int direction;
  
  public State(State prevState, Point currBoxLoc, Point currPlayerLoc, int direction) {
    this.prevState = prevState;
    this.currBoxLoc = currBoxLoc;
    this.currPlayerLoc = currPlayerLoc;
  }
  
}

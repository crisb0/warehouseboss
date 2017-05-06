import java.awt.Point;

public class State {
  State prevState;
  Point currBoxLoc;
  Point currPlayerLoc;
  int direction;
  
  public State(State prevState, Point currBoxLoc, Point currPlayerLoc, int direction) {
    this.prevState = prevState;
    this.currBoxLoc = currBoxLoc;
    this.currPlayerLoc = currPlayerLoc;
  }
  
}

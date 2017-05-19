package entity;
import java.awt.Point;
import java.util.ArrayList;

public class State implements Comparable<State> {
	
	private int f;
	private int g;
	private int h;
	private Point end;
	ArrayList<Block> path = new ArrayList<Block>();
	
	public State(Point end) {
		this.end = end;
	}

	@Override
	public int compareTo(State s) {
		if(this.f > s.getF()){
			return 1;
		} else if (this.f < s.getF()){
			return -1;
		}
		return 0;
	}
	

	public int getF() {
		return this.f;
	}


	public boolean isEndState() {
		if(this.path.get(this.path.size()-1).getI() == (int) this.end.getX() && this.path.get(this.path.size()-1).getJ() == (int) this.end.getY()){
			return true;
		}
		return false;
	}


	public ArrayList<State> expandState() {
		ArrayList<State> newStates = new ArrayList<State>();
		ArrayList<Block> connectingBlocks = this.path.get(this.path.size()-1).connectingBlocks();
		for(int i = 0; i < connectingBlocks.size(); i++){
			State newState = new State(this.end);
			newState.addBlocks(this.path);
			int initSize = newState.getPath().size();
			newState.addBlock(connectingBlocks.get(i));
			int finalSize = newState.getPath().size();
			if(initSize != finalSize){
				newStates.add(newState);
			}
			
		}
		return newStates;
	}

	public ArrayList<Block> getPath(){
		return this.path;
	}

	private void addBlocks(ArrayList<Block> path) {
		for(int i = 0; i < path.size(); i++){
			this.path.add(path.get(i));
		}
		calculateF();
	}


	private void calculateF() {
		this.f = calculateG() + calculateH();
	}

	private int calculateG() {
		this.g = this.path.size();
		return this.g;
	}

	private int calculateH() {
		Block last = this.path.get(this.path.size()-1);
		this.h = (int) (Math.abs(last.getI() - this.end.getX())+ Math.abs(last.getJ() - this.end.getY()));
		return this.h;
	}


	public void addBlock(Block start) {
		if(!this.path.contains(start)){
			this.path.add(start);
			calculateF();
		}
	}


	public void printState() {
		this.path.get(0).print();
	}	
}


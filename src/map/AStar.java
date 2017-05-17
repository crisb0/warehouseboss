package map;
import java.awt.Point;
import java.util.ArrayList;
import java.util.PriorityQueue;
import entity.Block;
import entity.State;

// The AStar class runs a A* search on the Map given edges and required jobs
// using a priority queue of States called 'queue'
// Every time an element form queue is expanded 'n' is incremented by 1
public class AStar {

	private Block start;
	private Point end;
	private PriorityQueue<State> queue = new PriorityQueue<State>();
	
	public AStar(Block start, Point end) {
		this.start = start;
		this.end = end;
	}

	// Performs the A* search
	public boolean aStarSearch() {
		// Create initial States of paths starting from Sydney
		createInitialStates();
		
		// Get the best State from priority queue and expand it and add it to queue
		// and keep repeating until the best state reaches the goal. 
		while(!this.queue.isEmpty()){
			State bestState = this.queue.poll();
			if(!bestState.isEndState()){
				expandState(bestState);	
			} else {
				return true;
			}
		}
		return false;
	}
	
	private void expandState(State bestState) {
		ArrayList<State> newStates = bestState.expandState();
		for(int i = 0; i < newStates.size(); i++){
			this.queue.add(newStates.get(i));
		}
	}

	private void createInitialStates(){
		State newState = new State(this.end);
		newState.addBlock(this.start);
		this.queue.add(newState);
	}
}


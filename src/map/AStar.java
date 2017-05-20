package map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import entity.Block;
import entity.Box;
import entity.State;

public class AStar {
	
	private Block[][] map;
	private Block[][] currMap;
	private List<Box> boxes;
	private Point player;
	private Block end;
	private PriorityQueue<State> queue = new PriorityQueue<State>();
	
	public AStar(Block[][] map, List<Box> boxes, Point player, Block b) {
		this.map = map;
		this.boxes = boxes;
		this.player = player;
		this.end = b;
	}
	
	public boolean aStarSearch(){
		makeCurrState();
		canGoTo();
		return search();
	}

	
	public boolean search() {
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
		State newState = new State(this.player);
		newState.addBlock(this.end);
		this.queue.add(newState);
	}
	
	
	public void canGoTo(){
		for(int i = 0; i < this.currMap.length; i++){
			for(int j = 0; j < this.currMap[0].length; j++){
				if(this.currMap[i][j].getType() != 1){
					canGoToV(i, j);
					canGoToH(i, j);
				}
			}
		}
	}
	
	// Checks horizontally
	private void canGoToH(int i, int j) {
		if(j >= 1 || j+1 <= this.currMap[0].length-1){
			if(this.currMap[i][j+1].getType() != 1){ 
				this.currMap[i][j].addCanGoTo(this.currMap[i][j+1]);
			}
			if(this.currMap[i][j-1].getType() != 1){
				this.currMap[i][j].addCanGoTo(this.currMap[i][j-1]);
			}
		}
	}

	// Check vertically
	private void canGoToV(int i, int j) {
		if(i >= 1 || i+1 <= this.currMap.length-1){
			if(this.currMap[i+1][j].getType() != 1){ 
				this.currMap[i][j].addCanGoTo(this.currMap[i+1][j]);
			}
			if(this.currMap[i-1][j].getType() != 1){
				this.currMap[i][j].addCanGoTo(this.currMap[i-1][j]);
			}
		}
	}

	private void makeCurrState() {
		this.currMap = new Block[this.map.length][this.map[0].length];
		for(int i = 0; i < this.currMap.length; i++){
			for(int j = 0; j < this.currMap[0].length; j++){
				this.currMap[i][j] = new Block(this.map[i][j].getType(), i ,j);
				if(this.currMap[i][j].getType() == 3){
					this.currMap[i][j].setType(0);
				}
			}
		}
		
		for(Box b : this.boxes){
			Point p = b.getLoc();			
			int Bx = (int) p.getX();
			int By = (int) p.getY();
			this.currMap[Bx][By].setType(1);
		}	
	}

}

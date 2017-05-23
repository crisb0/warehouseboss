package map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import entity.Block;
import entity.Box;

public class CreateSolution {
	
	private Block[][] map;
	private List<Point> goals;
	private List<Box> boxes;
	private Point player;
	private ArrayList<Block> starting = new ArrayList<Block>();
	private Queue<GameState> queue = new LinkedList<GameState>();
	private Hashtable<Double, Integer> states = new Hashtable<Double, Integer>();
	private int hicost = 0;
	private int stateID = 0;
	
	public CreateSolution(Block[][] preDefMap, List<Point> goalLocs) {
		this.map = preDefMap;
		this.goals = goalLocs;
		findStartingPoints();
		createInitialStates();
		int i = 0;
		while(!this.queue.isEmpty() && i < 999){
			expandState();
			i++;
			System.out.println(i);
		}	
		
		/*Set<Integer> keys = states.keySet();
	    for(Integer key: keys){
	    	System.out.println("Value of "+key+" is: "+states.get(key));
	    }*/
		System.out.println(this.hicost + " " + this.stateID);
		createState();
		
	}

	public int getCost(){
		return this.hicost;
	}

	private void createState() {
		if(stateID == 0){
			return;
		}
		String stringID = ""+this.stateID;
		String[] split = stringID.split("");
		int Px = Integer.parseInt(split[0]);
		int Py = Integer.parseInt(split[1]);
		this.player = new Point(Px, Py);
		this.map[Px][Py].setType(4);
		int B1x = Integer.parseInt(split[2]);
		int B1y = Integer.parseInt(split[3]);
		Point b1 = new Point(B1x, B1y);
		Box box1 = new Box(b1);
		this.map[B1x][B1y].setType(2);
		int B2x = Integer.parseInt(split[4]);
		int B2y = Integer.parseInt(split[5]);
		Point b2 = new Point(B2x, B2y);
		Box box2 = new Box(b2);
		this.map[B2x][B2y].setType(2);
		int B3x = Integer.parseInt(split[6]);
		int B3y = Integer.parseInt(split[7]);
		Point b3 = new Point(B3x, B3y);
		Box box3 = new Box(b3);
		this.map[B3x][B3y].setType(2);
		this.boxes = new ArrayList<>();
		this.boxes.add(box1);
		this.boxes.add(box2);
		this.boxes.add(box3);
	}
	
	public Point getPlayer(){
		return this.player;
	}
	
	public List<Box> getBoxLocs(){
		return this.boxes;
	}
	
	public List<Point> getGoalLocs(){
		return this.goals;
	}
	
	public Block[][] getPuzzle(){
		return this.map;
	}

	private void expandState() {
		GameState currState = this.queue.poll();
		currState.expandState();
		ArrayList<GameState> possibleStates = currState.getPossibleStates();
		if(!this.states.containsKey(currState.getStateCode())){
			this.states.put(currState.getStateCode(), currState.getCost());
			this.queue.add(currState);
			if(currState.getCost() > this.hicost){
				this.hicost = currState.getCost();
				this.stateID = currState.getID();
			}
		} 
		
		for(GameState gs : possibleStates){
			if(!this.states.containsKey(gs.getStateCode())){
				this.queue.add(gs);
				this.states.put(gs.getStateCode(), gs.getCost());
				
				if(gs.getCost() > this.hicost){
					this.hicost = gs.getCost();
					this.stateID = gs.getID();
				}
			} 
		}
	}


	private void createInitialStates() {
		for(Block b: this.starting){
			GameState newState = new GameState(b);
			newState.setInitialState(this.map, this.goals);
			this.states.put(newState.getStateCode(), newState.getCost());
			this.queue.add(newState);
			this.states.put(newState.getStateCode(), newState.getCost());
			if(newState.getCost() > this.hicost){
				this.hicost = newState.getCost();
				this.stateID = newState.getID();
			}
		}
	}

	private void findStartingPoints() {
		blankMap();
		for(Point p : this.goals){
			int x = (int) p.getX();
			int y = (int) p.getY();
			ArrayList<Block> startingBlocks = startingPointsForGoal(this.map[x][y]);
			for(Block b: startingBlocks){
				this.starting.add(b);
			}
		}
	}
	
	private void blankMap() {
		for(int i = 0; i < this.map.length; i++){
			for(int j = 0; j < this.map[0].length; j++){
				if(this.map[i][j].getType() == 9){
					this.map[i][j].setType(0);
				}
			}
		}
	}


	private ArrayList<Block> startingPointsForGoal(Block block) {
		ArrayList<Block> s = new ArrayList<Block>();
		int i = block.getI();
		int j = block.getJ();
		if(j-1 >= 1 && this.map[i][j-1].getType() != 1 && this.map[i][j-2].getType() != 1){
			s.add(this.map[i][j-1]);
		} 
		if(j+1 <= this.map[0].length-2 && this.map[i][j+1].getType() != 1 && this.map[i][j+2].getType() != 1){
			s.add(this.map[i][j+1]);
		} 
		if(i-1 >= 1 && this.map[i-1][j].getType() != 1 && this.map[i-2][j].getType() != 1){
			s.add(this.map[i-1][j]);
		} 
		if(i+1 <= this.map.length-2 && this.map[i+1][j].getType() != 1 && this.map[i+2][j].getType() != 1){
			s.add(this.map[i+1][j]);
		}
		return s;
	}

}

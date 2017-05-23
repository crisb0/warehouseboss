package map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import entity.Block;
import entity.Box;

public class GameState {
	
	private Block[][] map;
	private List<Point> goals;
	private List<Box> boxes = new ArrayList<>();
	private Point player;
	private int ID;
	private int cost = 0;
	private double stateCode;
	
	public GameState(Block b) {
		int x = b.getI();
		int y = b.getJ();
		this.player = new Point(x, y);
	}

	public int getCost(){
		return this.cost;
	}
	
	public void setCost(int cost){
		this.cost = cost;
	}

	public void setInitialState(Block[][] map, List<Point> goals){
		setMap(map);
		setGoals(goals);
		setBoxes();
		calculateID();
		//printState();
	}
	
	public void setState(Block[][] map, List<Box> boxes){
		setMap(map);
		for(Box b : boxes){
			this.boxes.add(b);
		}
		calculateID();
	}

	private void calculateID() {
		String strID = "";
		int Px = (int) this.player.getX();
		int Py = (int) this.player.getY();
		strID = strID+Px+Py;
		for(Box b: this.boxes){
			int Bx = (int) b.getLoc().getX();
			int By = (int) b.getLoc().getY();
			strID = strID+Bx+By;
		}
		this.ID = Integer.parseInt(strID);
		calculateStateCode();
	}
	
	private void calculateStateCode() {
		int playerCode = (int) this.player.getX();
		playerCode = playerCode * 10;
		playerCode = playerCode + (int) this.player.getY();
		ArrayList<Integer> boxCodes = new ArrayList<Integer>();
		for(Box b: this.boxes){
			int boxCode = (int) b.getLoc().getX();
			boxCode = boxCode * 10;
			boxCode = boxCode + (int) b.getLoc().getY();
			boxCodes.add(boxCode);
		}
		
		int product = 1;
		int sum = 0;
		for(int code: boxCodes){
			product = product*code;
			sum = sum + code;
		}
		
		String strCode = "";
		strCode = strCode + playerCode + product + sum;
		this.stateCode = Double.parseDouble(strCode);
	}

	public double getStateCode(){
		return this.stateCode;
	}


	public void setMap(Block[][] map){
		this.map = map;
	}
	
	public void setGoals(List<Point> goals){
		this.goals = goals;
	}
	
	private void setBoxes() {
		for(Point p : this.goals){
			Box b = new Box(p);
			this.boxes.add(b);
		}
	}
	
	
	public void printState() {
		System.out.println("ID: " + this.ID);
		for(int i = 0; i < this.map.length ; i++){
			for(int j = 0; j < this.map.length; j++){
				if(j == this.player.getX() && i == this.player.getY()){
					System.out.print("P");
				} else if(this.boxes.get(0).getLoc().getX() == j && this.boxes.get(0).getLoc().getY() == i){
					System.out.print("B");
				} else if(this.boxes.get(1).getLoc().getX() == j && this.boxes.get(1).getLoc().getY() == i){
					System.out.print("B");
				} else if(this.boxes.get(2).getLoc().getX() == j && this.boxes.get(2).getLoc().getY() == i){
					System.out.print("B");
				} else {
					System.out.print(this.map[j][i].getType());
				}
			}
			System.out.println();
		}
	}
	
	
	public void expandState() {
		//printState();
		Box b = findBox();
		pullBox(b);
		calculateID();
		//printState();
		//System.out.println();
		this.cost++;
	}


	private Box findBox() {
		int Px = (int) this.player.getX();
		int Py = (int) this.player.getY();
		for(Box b: this.boxes){
			Point p = b.getLoc();
			int Bx = (int) p.getX();
			int By = (int) p.getY();
			if(Px == Bx || Py == By){
				if(Py - By == 1){
					if(this.map[Px][Py+1].getType() == 0){
						if(!isABox(this.map[Px][Py+1])){
							this.boxes.remove(b);
							return b;
						}
					}
				} else if (Py - By == -1){
					if(this.map[Px][Py-1].getType() == 0){
						if(!isABox(this.map[Px][Py-1])){
							this.boxes.remove(b);
							return b;
						}
					}
				} else if (Px - Bx == 1){
					if(this.map[Px+1][Py].getType() == 0){
						if(!isABox(this.map[Px+1][Py])){
							this.boxes.remove(b);
							return b;
						}
					}
				} else if (Px - Bx == -1){
					if(this.map[Px-1][Py].getType() == 0){
						if(!isABox(this.map[Px-1][Py])){
							this.boxes.remove(b);
							return b;
						}
					}
				}
			}
		}
		return null;
	}

	private void pullBox(Box b) {
		if(b == null){
			return;
		}
		int Px = 0;
		int Py = 0;
		int Bx = 0;
		int By = 0;
		if(this.player.getX() == b.getLoc().getX()){
			Px = (int) this.player.getX();
			Bx = (int) b.getLoc().getX();
			if(this.player.getY() < b.getLoc().getY()){
				Py = (int) this.player.getY() - 1;
				By = (int) b.getLoc().getY() - 1;
			} else {
				Py = (int) this.player.getY() + 1;
				By = (int) b.getLoc().getY() + 1;
			}
		} else {
			Py = (int) this.player.getY();
			By = (int) b.getLoc().getY();
			if(this.player.getX() < b.getLoc().getX()){
				Px = (int) this.player.getX() - 1;
				Bx = (int) b.getLoc().getX() - 1;
			} else {
				Px = (int) this.player.getX() + 1;
				Bx = (int) b.getLoc().getX() + 1;
			}
		}	
		Point p = new Point(Px, Py);
		Box newBox = new Box(new Point(Bx, By));
		this.player = p;
		this.boxes.add(newBox);
	}

	public int getID(){
		return this.ID;
	}


	public ArrayList<GameState> getPossibleStates() {
		ArrayList<Block> starting = findStartingPoints();
		//System.out.println("Starting " + starting.size());
		ArrayList<GameState> newStates = new ArrayList<GameState>();
		for(Block b: starting){
			AStar search = new AStar(this.map, this.boxes, this.player, b);
			if(search.aStarSearch()){
				//System.out.println(this.player.x + " " + this.player.y + " " + b.getI() + " " + b.getJ());
				//create new state and add it to starting;
				GameState newState = new GameState(b);
				newState.setState(this.map, this.boxes);
				newState.setCost(this.cost);
				newStates.add(newState);
			}
		}
		//System.out.println("New States: " + newStates.size());
		return newStates;
	}	
	
	private ArrayList<Block> findStartingPoints() {
		ArrayList<Block> starting = new ArrayList<Block>();
		for(Box b: this.boxes){
			int x = (int) b.getLoc().getX();
			int y = (int) b.getLoc().getY();
			ArrayList<Block> startingBlocks = startingPointsForBox(this.map[x][y]);
			for(Block block: startingBlocks){
				if(!isABox(block)){
					starting.add(block);
				}
			}
		}
		return starting;
	}

	private boolean isABox(Block b) {
		for(Box box:this.boxes){
			Point p = box.getLoc();
			int Bx = (int) p.getX();
			int By = (int) p.getY();
			if(Bx == b.getI() && By == b.getJ()){
				return true;
			}
		}
		return false;
	}

	private ArrayList<Block> startingPointsForBox(Block block) {
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

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapGenerator {
	
	private Block[][] puzzleB = new Block[8][8];
	private StartingMap s = new StartingMap();
	private ArrayList<Template> ts = new ArrayList<Template>();
	private ArrayList<Block> goals = new ArrayList<Block>();
	private Point playerLocation;
	private List<Box> boxLocs;
	private List<Point> goalLocs;
	
	public MapGenerator(){
		this.playerLocation = new Point();
		this.boxLocs = new ArrayList<>();
		this.goalLocs = new ArrayList<>();
		this.puzzleB = this.s.getBlockMap();
		createTemplates();
		addTemplate();
		checkNoGoal();
		addGoal();
		canGoTo();
		placeBlock(calculateGoalRange());
		addPlayer();
	}
	

	private void addPlayer() {
		this.playerLocation.setLocation(1,1);
	}


	private void placeBlock(ArrayList<Block> goalRange) {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(goalRange.size());
		goalRange.get(randomInt).setType(2);
		Point b = new Point();
		b.setLocation(goalRange.get(randomInt).getI(), goalRange.get(randomInt).getJ());
		Box box = new Box(b);
		this.boxLocs.add(box);
	}


	public void createTemplates(){
		Template t1 = new Template(1);
		Template t2 = new Template(2);
		Template t3 = new Template(3);
		this.ts.add(t1);
		this.ts.add(t2);
		this.ts.add(t3);
	}
	
	// add templates to prototype
	public void addTemplate(){
		for(int s = 0; s < this.ts.size(); s++){
			Template currT = this.ts.get(s);
			int h = currT.getTemp().length;
			int w = currT.getTemp()[0].length;
			if(s == 0){
				Random randomGenerator = new Random();
				int x = randomGenerator.nextInt(this.puzzleB.length-3-h)+1;
				int y = randomGenerator.nextInt(this.puzzleB[0].length-3-w)+1;
				for(int i = 0; i < h; i++){
					for(int j = 0; j < w; j++){
						this.puzzleB[x+i][y+j].setType(currT.getBlockTemp()[i][j].getType());
					}
				}
			} else if (s == 1) {
				Random randomGenerator = new Random();
				int x = randomGenerator.nextInt(this.puzzleB.length-3-h)+1;
				int y = randomGenerator.nextInt(this.puzzleB[0].length-3-w)+1;
				for(int i = 0; i < h; i++){
					for(int j = 0; j < w; j++){
						this.puzzleB[x+i][y+j].setType(currT.getBlockTemp()[i][j].getType());
					}
				}
			} else {
				Random randomGenerator = new Random();
				int x = randomGenerator.nextInt(this.puzzleB.length-3-h)+1;
				int y = randomGenerator.nextInt(this.puzzleB[0].length-3-w)+1;
				for(int i = 0; i < h; i++){
					for(int j = 0; j < w; j++){
						this.puzzleB[x+i][y+j].setType(currT.getBlockTemp()[i][j].getType());
					}
				}
			}
		}
	}
	
	// check goal-avoid checks places in map where goal can't be reached
	public void checkNoGoal(){
		for(int i = 0; i < this.puzzleB.length; i++){
			for(int j = 0; j < this.puzzleB[0].length; j++){
				if(this.puzzleB[i][j].getType() == 0){
					if(!noGoal(i, j)){
						this.puzzleB[i][j].setType(9);
					}
				}
			}
		}
	}
	
	private boolean noGoal(int i, int j) {
		if(j-1 >= 1 && this.puzzleB[i][j-1].getType() ==  0 && this.puzzleB[i][j-2].getType() == 0){
			return true;
		} else if(j+1 <= this.puzzleB[0].length-2 && this.puzzleB[i][j+1].getType() == 0 && this.puzzleB[i][j+2].getType() == 0){
			return true;
		} else if(i-1 >= 1 && this.puzzleB[i-1][j].getType() == 0 && this.puzzleB[i-2][j].getType() == 0){
			return true;
		} else if(i+1 <= this.puzzleB.length-2 && this.puzzleB[i+1][j].getType() == 0 && this.puzzleB[i+2][j].getType() == 0){
			return true;
		}
		return false;
	}

	// add goal
	public void addGoal(){
		//add goals to places where puzzle[i][j] != 3 randomly 
		//I've hard coded the goal for now to check if other programs are working
		/*this.puzzleB[2][5].setType(3);
		this.goals.add(this.puzzleB[2][5]);
		this.puzzleB[3][6].setType(3);
		this.goals.add(this.puzzleB[3][6]);
		this.puzzleB[4][2].setType(3);
		this.goals.add(this.puzzleB[4][2]);*/
		int accepted = 0;
		while(accepted == 0){
			Random randomGenerator = new Random();
			int i = randomGenerator.nextInt(8);
			int j = randomGenerator.nextInt(8);
			System.out.println(i + " " + j);
			if(this.puzzleB[i][j].getType() == 0){
				this.puzzleB[i][j].setType(3);
				this.goals.add(this.puzzleB[i][j]);
				Point g = new Point();
				g.setLocation(i, j);
				this.goalLocs.add(g);
				accepted = 1;
			}		
		}
	}
	
	// check goal_range
	public ArrayList<Block> calculateGoalRange(){
		ArrayList<Block> blocks = new ArrayList<Block>();
		for(int i = 0; i < this.puzzleB.length; i++){
			for(int j = 0; j < this.puzzleB[0].length; j++){
				if(this.puzzleB[i][j].getType() != 1){
					AStar a = new AStar(this.puzzleB[i][j], this.goals.get(0));
					if(a.aStarSearch()){
						if(this.puzzleB[i][j].getType() != 3){
							//this.puzzleB[i][j].setType(5);
						}
						blocks.add(this.puzzleB[i][j]);
					}	
				}
			}
		}
		
		return blocks;
	}
	
	//Checks where to go from current block
	public void canGoTo(){
		for(int i = 0; i < this.puzzleB.length; i++){
			for(int j = 0; j < this.puzzleB[0].length; j++){
				if(this.puzzleB[i][j].getType() != 1){
					canGoToV(i, j);
					canGoToH(i, j);
				}
			}
		}
	}
	
	// Checks horizontally
	private void canGoToH(int i, int j) {
		if(j >= 1 || j+1 <= this.puzzleB[0].length-1){
			if(this.puzzleB[i][j+1].getType() != 1 && this.puzzleB[i][j-1].getType() != 1){
				this.puzzleB[i][j].addCanGoTo(this.puzzleB[i][j+1]);
				this.puzzleB[i][j].addCanGoTo(this.puzzleB[i][j-1]);
			}
		}
	}

	// Check vertically
	private void canGoToV(int i, int j) {
		if(i >= 1 || i+1 <= this.puzzleB.length-1){
			if(this.puzzleB[i+1][j].getType() != 1 && this.puzzleB[i-1][j].getType() != 1){
				this.puzzleB[i][j].addCanGoTo(this.puzzleB[i+1][j]);
				this.puzzleB[i][j].addCanGoTo(this.puzzleB[i-1][j]);
			}
		}
	}

	// place object
	
	// place player
	
	
	public void displayPuzzle() {
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				System.out.print(this.puzzleB[i][j].getType());
			}
			System.out.println();
		}
	}

	public Player getPlayer() {
		this.playerLocation.setLocation(1,1);
		Player p = new Player(this.playerLocation);		
		return p;
	}


	public List<Box> getBoxLocs() {
		// TODO Auto-generated method stub
		return this.boxLocs;
	}


	public int[][] getGrid() {
		int[][] grid = new int[8][8];
		for(int i = 0; i < this.puzzleB.length; i++){
			for(int j = 0; j < this.puzzleB[0].length; j++){
				if(this.puzzleB[i][j].getType() != 9){
					grid[i][j] = this.puzzleB[i][j].getType();
				} else if(i == 1 && j == 1){
					grid[i][j] = 4;
				}
			}
		}
		return grid;
	}


	public List<Point> getGoalLocs() {
		// TODO Auto-generated method stub
		return this.goalLocs;
	}

}

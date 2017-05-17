package map;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import entity.Block;
import entity.Box;

public class FindSolution {

	private Block[][] puzzle;// = new Block[DIMESTIONS][DIMESTIONS];	
	private Point playerLocation;
	private List<Box> boxLocs;
	private List<Point> goalLocs;
	
	public FindSolution(Block[][] puzzle, List<Point> goalLocs){
		this.puzzle = puzzle;
		this.goalLocs = goalLocs;
		this.boxLocs = new ArrayList<>();
		this.playerLocation = new Point();
		canGoTo();
		for(int i = 0; i < this.goalLocs.size(); i++){
			Point currGoal = this.goalLocs.get(i);
			placeBox(calculateGoalRange(currGoal));
		}
			
		addPlayer();
	}
	
	private boolean isSolvable() {
		return true;
	}

	private void addPlayer() {
		Random randomGenerator = new Random();
		int i = randomGenerator.nextInt(this.puzzle.length-1);
		int j = randomGenerator.nextInt(this.puzzle.length-1);

		if(this.puzzle[i][j].getType() == 0){
			this.playerLocation.setLocation(i,j);
			this.puzzle[i][j].setType(4);
		} else {
			addPlayer();
		}
	}
	
	private void placeBox(ArrayList<Block> goalRange) {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(goalRange.size());
		goalRange.get(randomInt).setType(2);
		
		Point b = new Point();
		b.setLocation(goalRange.get(randomInt).getI(), goalRange.get(randomInt).getJ());
		Box box = new Box(b);
		this.boxLocs.add(box);
	}
	
	// check goal_range
	public ArrayList<Block> calculateGoalRange(Point index){
		ArrayList<Block> blocks = new ArrayList<Block>();
		for(int i = 0; i < this.puzzle.length; i++){
			for(int j = 0; j < this.puzzle[0].length; j++){
				if(this.puzzle[i][j].getType() != 1){
					AStar a = new AStar(this.puzzle[i][j], index);
					if(a.aStarSearch()){
						if(this.puzzle[i][j].getType() != 3){
							//this.puzzle[i][j].setType(5);
						}
						blocks.add(this.puzzle[i][j]);
					}	
				}
			}
		}
		System.out.println(blocks.size() + " Sixe mofo");
		return blocks;
	}
		
	//Checks where to go from current block
	public void canGoTo(){
		for(int i = 0; i < this.puzzle.length; i++){
			for(int j = 0; j < this.puzzle[0].length; j++){
				if(this.puzzle[i][j].getType() != 1){
					canGoToV(i, j);
					canGoToH(i, j);
				}
			}
		}
	}
		
	// Checks horizontally
	private void canGoToH(int i, int j) {
		if(j >= 1 || j+1 <= this.puzzle[0].length-1){
			if(this.puzzle[i][j+1].getType() != 1 && this.puzzle[i][j-1].getType() != 1){
				this.puzzle[i][j].addCanGoTo(this.puzzle[i][j+1]);
				this.puzzle[i][j].addCanGoTo(this.puzzle[i][j-1]);
			}
		}
	}

	// Check vertically
	private void canGoToV(int i, int j) {
		if(i >= 1 || i+1 <= this.puzzle.length-1){
			if(this.puzzle[i+1][j].getType() != 1 && this.puzzle[i-1][j].getType() != 1){
				this.puzzle[i][j].addCanGoTo(this.puzzle[i+1][j]);
				this.puzzle[i][j].addCanGoTo(this.puzzle[i-1][j]);
			}
		}
	}

	public Block[][] getPuzzle() {
		return this.puzzle;
	}

	public Point getPlayerLocation() {
		return this.playerLocation;
	}

	public List<Box> getBoxLocs() {
		return this.boxLocs;
	}

	public List<Point> getGoalLocs() {
		return this.goalLocs;
	}
}

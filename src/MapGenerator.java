import java.util.ArrayList;

public class MapGenerator {
	
	private Block[][] puzzleB = new Block[8][8];
	private int[][] puzzle = new int[8][8];
	private StartingMap s = new StartingMap();
	private ArrayList<Template> ts = new ArrayList<Template>();
	
	public static void main (String[] args) {
		MapGenerator m = new MapGenerator();
		m.displayPuzzle();
	}
	
	public MapGenerator(){
		this.puzzle = this.s.getMap();
		this.puzzleB = this.s.getBlockMap();
		createTemplates();
		addTemplate();
		checkNoGoal();
		addGoal();
		canGoTo();
	}
	
	// Checks the vertical and horizontal lines a box can travel in given position
	private void canGoTo() {
		for(int i = 0; i < this.puzzleB.length; i++){
			for(int j = 0; j < this.puzzleB[0].length; j++){
				//this.puzzleB[i][j].
			}
		}
		
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
			//System.out.println(h + " " + w);
			if(s == 0){
				for(int i = 0; i < h; i++){
					for(int j = 0; j < w; j++){
						this.puzzleB[1+i][1+j].setType(currT.getTemp()[i][j]);
					}
				}
			} else if (s == 1) {
				for(int i = 0; i < h; i++){
					for(int j = 0; j < w; j++){
						this.puzzleB[1+i][5+j].setType(currT.getTemp()[i][j]);
					}
				}
			} else {
				for(int i = 0; i < h; i++){
					for(int j = 0; j < w; j++){
						this.puzzleB[3+i][4+j].setType(currT.getTemp()[i][j]);
					}
				}
			}
		}
	}
	
	// check goal-avoid
	public void checkNoGoal(){
		for(int i = 0; i < this.puzzleB.length; i++){
			for(int j = 0; j < this.puzzleB[0].length; j++){
				if(this.puzzleB[i][j].getType() == 0){
					if(!noGoal(i, j)){
						this.puzzleB[i][j].setType(3);
					}
				}
			}
		}
	}
	
	private boolean noGoal(int i, int j) {
		/* puzzle[i][j-1] puzzle[i][j-2]
		 * puzzle[i][j+1] puzzle[i][j+2]
		 * puzzle[i-1][j] puzzle[i-2][j]
		 * puzzle[i+1][j] puzzle[i+2][j]
		 */
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
		//Goal A
		this.puzzleB[2][5].setType(4);
		this.puzzleB[3][6].setType(4);
		this.puzzleB[4][2].setType(4);
	}
	// check corner_goal
	
	// check goal_range
	public void calculateGoalRange(){
		
	}
	
	private void calculateBoxMovementFromEachPath(){
		
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

}

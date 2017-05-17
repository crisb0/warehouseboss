package map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entity.Block;

public class CreateMap {
	private static final int DIMENSIONS = 10;
	private Block[][] puzzle = new Block[DIMENSIONS][DIMENSIONS];
	private StartingMap s = new StartingMap();
	private ArrayList<Template> ts = new ArrayList<Template>();
	
	private List<Point> goalLocs;
	
	public CreateMap() {
		this.goalLocs = new ArrayList<>();
		this.puzzle = this.s.getBlockMap();
		createTemplates();
		addTemplate();
		checkNoGoal();
		addGoal();
	}
	public void createTemplates(){
		//randomly generate a number between 1 and 10
		//create a template with this number
		//add this template to ts
		//total of 4 templates
		for (int i = 0; i < 6; i++) {
			Random rand = new Random();
			int randNum = rand.nextInt(11)+1;
			Template t = new Template(randNum);
			this.ts.add(t);
		}
	}
	
	// add templates to prototype
	public void addTemplate(){
		//lil problem we should make templates that when integrated, will not create something like that
		//https://puu.sh/vQsmF/7faa808af9.png
		//can we work on this tgr tomorrow? @ christine
		//add template to each corner
		int x = 1;
		int y = 1;
		for(int s = 0; s < this.ts.size(); s++){
			Template currT = this.ts.get(s);
			int h = currT.getBlockTemp().length;
			int w = currT.getBlockTemp()[0].length;
			Random randomGenerator = new Random();
			System.out.println(x + " " + y);
			System.out.println(h+ " " + w);
			if (x >= this.puzzle.length-3) {
				x = 1;
				y+=3;
				
			}
			if ((y >= (this.puzzle[0].length-3)) && (x >= (this.puzzle.length-4))) {
				System.out.println("break");
				continue;
			}
			System.out.println(x + " " + y + "\n");
			for(int i = 0; i < h; i++){
				if ((x+i) == this.puzzle.length-1) {
					break;
				}
				for(int j = 0; j < w; j++){
					if ((y+j) == this.puzzle[0].length-1) {
						break;
					}
					this.puzzle[x+i][y+j].setType(currT.getBlockTemp()[i][j].getType());
				}
			}
			x += h;
		}
	}
	
	public void checkNoGoal(){
		for(int i = 0; i < this.puzzle.length; i++){
			for(int j = 0; j < this.puzzle[0].length; j++){
				if(this.puzzle[i][j].getType() == 0){
					if(!noGoal(i, j)){
						this.puzzle[i][j].setType(9);
					}
				}
			}
		}
	}
	
	private boolean noGoal(int i, int j) {
		if(j-1 >= 1 && this.puzzle[i][j-1].getType() ==  0 && this.puzzle[i][j-2].getType() == 0){
			return true;
		} else if(j+1 <= this.puzzle[0].length-2 && this.puzzle[i][j+1].getType() == 0 && this.puzzle[i][j+2].getType() == 0){
			return true;
		} else if(i-1 >= 1 && this.puzzle[i-1][j].getType() == 0 && this.puzzle[i-2][j].getType() == 0){
			return true;
		} else if(i+1 <= this.puzzle.length-2 && this.puzzle[i+1][j].getType() == 0 && this.puzzle[i+2][j].getType() == 0){
			return true;
		}
		return false;
	}

	// add goal
	public void addGoal(){
		//add goals to places where puzzle[i][j] != 3 randomly 
		//I've hard coded the goal for now to check if other programs are working
		/*this.puzzle[2][5].setType(3);
		this.goals.add(this.puzzle[2][5]);
		this.puzzle[3][6].setType(3);
		this.goals.add(this.puzzle[3][6]);
		this.puzzle[4][2].setType(3);
		this.goals.add(this.puzzle[4][2]);*/
		for(int f = 0; f < 3; f++){
			int accepted = 0;
			while(accepted == 0){
				Random randomGenerator = new Random();
				int i = randomGenerator.nextInt(10);
				int j = randomGenerator.nextInt(10);
				if(this.puzzle[i][j].getType() == 0){
					this.puzzle[i][j].setType(3);
					Point p = new Point(i,j);
					p.setLocation(i, j);
					this.goalLocs.add(p);
					accepted = 1;
				}		
			}
		}
		
	}
	
	public Block[][] getPuzzle() {
		return puzzle;
	}


	public List<Point> getGoalLocs() {
		return goalLocs;
	}
}

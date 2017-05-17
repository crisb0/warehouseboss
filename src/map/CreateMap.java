package map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entity.Block;

public class CreateMap {
	private Block[][] puzzleB = new Block[10][10];
	private StartingMap s = new StartingMap();
	private ArrayList<Template> ts = new ArrayList<Template>();
//	private ArrayList<Block> goals = new ArrayList<Block>();
	
	private List<Point> goalLocs;
	
	public CreateMap() {
		this.goalLocs = new ArrayList<>();
		this.puzzleB = this.s.getBlockMap();
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
//			System.out.println(randNum);
//			int randNum = 11;
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
			if (x >= this.puzzleB.length-3) {
				x = 1;
//				if ((y >= this.puzzleB[0].length-3)) {
					y+=3;
//				}
				
			}
			if ((y >= (this.puzzleB[0].length-3)) && (x >= (this.puzzleB.length-4))) {
				System.out.println("break");
				continue;
			}
			System.out.println(x + " " + y + "\n");
//			int x=1;
//			int y=1;
//			if(s == 0){
////				x = randomGenerator.nextInt(this.puzzleB.length-2-h)+1;
////				y = randomGenerator.nextInt(this.puzzleB[0].length-2-w)+1;
////				x = 1;
////				y = 1;
////				System.out.println(x+" "+y);
//			} else if (s == 1) {
////				x = randomGenerator.nextInt(this.puzzleB.length-2-h)+1;
////				y = randomGenerator.nextInt(this.puzzleB[0].length-2-w)+1;
////				x = this.puzzleB.length-h-1;
////				y = 1;
////				System.out.println(x+" "+y);
//			} else if (s == 2) {
////				x = randomGenerator.nextInt(this.puzzleB.length-2-h)+1;
////				y = randomGenerator.nextInt(this.puzzleB[0].length-2-w)+1;
////				x = this.puzzleB.length-h-1;
////				y = this.puzzleB[0].length-w-1;
////				System.out.println(x+" "+y);
//			} else if (s == 3){
////				x = randomGenerator.nextInt(this.puzzleB.length-2-h)+1;
////				y = randomGenerator.nextInt(this.puzzleB[0].length-2-w)+1;
////				x = 1;
////				y = this.puzzleB[0].length-w-1;
////				System.out.println(x+" "+y);
//			} else if (s == 4) 
			for(int i = 0; i < h; i++){
				if ((x+i) == this.puzzleB.length-1) {
					break;
				}
				for(int j = 0; j < w; j++){
					if ((y+j) == this.puzzleB[0].length-1) {
						break;
					}
					this.puzzleB[x+i][y+j].setType(currT.getBlockTemp()[i][j].getType());
				}
			}
			x += h;
//			y += w;
		}
		
	}
	
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
		for(int f = 0; f < 3; f++){
			int accepted = 0;
			while(accepted == 0){
				Random randomGenerator = new Random();
				int i = randomGenerator.nextInt(10);
				int j = randomGenerator.nextInt(10);
				//System.out.println(i + " " + j);
				if(this.puzzleB[i][j].getType() == 0){
					this.puzzleB[i][j].setType(3);
					Point p = new Point(i,j);
//					this.goalLocs.add(this.puzzleB[i][j]);
					this.goalLocs.add(p);
					Point g = new Point();
					g.setLocation(i, j);
					this.goalLocs.add(g);
					accepted = 1;
				}		
			}
		}
	}
	
	public Block[][] getPuzzleB() {
		return puzzleB;
	}
	public StartingMap getS() {
		return s;
	}
	public ArrayList<Template> getTs() {
		return ts;
	}
//	public ArrayList<Block> getGoals() {
//		return goals;
//	}
	public List<Point> getGoalLocs() {
		return goalLocs;
	}
}

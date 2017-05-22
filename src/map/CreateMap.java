package map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import entity.Block;

public class CreateMap {
	private static final int DIMENSIONS = 8;
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
		//randomly generate a number between 1 and 8
		//create a template with this number
		//add this template to ts
		//total of 4 templates
//		for (int i = 1; i < 4; i++) {
//			Random rand = new Random();
//			int randNum = rand.nextInt(11)+1;
//			Template t = new Template(i);
//			this.ts.add(t);
//		}
		for (int i = 0; i < 6; i++) {
			Random rand = new Random();
			int randNum = rand.nextInt(12)+1;
			Template t = new Template(randNum);
			this.ts.add(t);
		}
		System.out.println();
	}
	
	// add templates to prototype
	public void addTemplate(){
		int x = 1;
		int y = 1;
		int prevW = 0;
		for(int s = 0; s < this.ts.size(); s++){
			Template currT = this.ts.get(s);
			int h = currT.getBlockTemp().length;
			int w = currT.getBlockTemp()[0].length;
//			Random randomGenerator = new Random();
//			System.out.println(x + " " + y);
//			System.out.println(h+ " " + w);
			//if x-axis index out of bounds
			if (x+1 >= this.puzzle.length) {
				x=1;
				//if y-axis will be out of bounds
				if (y+prevW+1 >= this.puzzle[0].length) {
					
				} else {
					y+=prevW;
				}
			}
			//if both x and y out of bounds at same time
			if (y >= this.puzzle[0].length && x >= this.puzzle.length) {
				x =1;
				y = 1;
			}
//			System.out.println(x + " " + y + "\n");
			for(int i = 0; i < h; i++){
				if ((x+i) == this.puzzle.length-1) {
					break;
//					x = 1;
				}
				for(int j = 0; j < w; j++){
					if (((y+j) == this.puzzle[0].length-1)) {
						break;
//						y = 1;
					}
					this.puzzle[x+i][y+j].setType(currT.getBlockTemp()[i][j].getType());
				}
			}
			x += h;
			prevW = w;
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
				int i = randomGenerator.nextInt(8);
				int j = randomGenerator.nextInt(8);
				if(this.puzzle[i][j].getType() == 0){
					this.puzzle[i][j].setType(3);
					Point p = new Point(i,j);
//					p.setLocation(i, j);
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
	
	public Block[][] preDefMap(){
		this.goalLocs = new ArrayList<>();
		String map = "1 1 1 1 1 1 1 1\n" +
					 "1 0 0 1 1 0 0 1\n" +
					 "1 0 0 0 0 0 0 1\n" +
					 "1 0 0 1 0 0 0 1\n" +
					 "1 0 0 9 0 1 0 1\n" +
					 "1 1 1 1 0 9 0 1\n" +
					 "1 1 1 1 1 1 1 1\n" +
					 "1 1 1 1 1 1 1 1";
		
		Block[][] grid = new Block[DIMENSIONS][DIMENSIONS];
		int x = 0;
		int y = 0;
		Scanner scanner = new Scanner(map);
		while (scanner.hasNextLine()) {
			String[] mapCode = scanner.nextLine().split("\\s+");
			for(x = 0; x < mapCode.length; x++){
				grid[x][y] = new Block(Integer.parseInt(mapCode[x]), x, y);
			}
			
			y++;
		}
		System.out.println(x + " " + y);
//		System.out.println(grid[0].length);
		scanner.close();
		for(int t = 0; t < 3; t++){
			int valid = 0;
			while(valid == 0){
				Random rand = new Random();
				int i = rand.nextInt(grid.length);
				int j = rand.nextInt(grid[0].length);
//				System.out.println(i + " " + j);
				if(grid[i][j].getType() == 0){
					valid = 1;
					Point g1 = new Point(i, j);
					g1.setLocation(i, j);
					this.goalLocs.add(g1);
					grid[i][j].setType(3);
				}
			}
		}
		
		/*
		Point g2 = new Point(6, 3);
		g2.setLocation(6, 3);
		this.goalLocs.add(g2);
		
		Point g3 = new Point(2, 4);
		g3.setLocation(2, 4);
		this.goalLocs.add(g3);
		*/
		return grid;
	}
}

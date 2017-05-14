package map;
import entity.Block;

public class Template {
	private int[][] t;
	private Block[][] b;
	
	public Template(int i){
		if(i == 1){
			template1();
		} else if (i == 2){
			template2();
		} else if (i == 3){
			template3();
		} else if (i == 4){
			template4();
		}
	}
	
	public void template1(){
		int[][] temp = new int[4][4];
		this.t = temp;
		temp[1][1] = 1;
		temp[3][3] = 1;
		b = new Block[4][4];
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				b[i][j] = new Block(0, i, j);
			}
		}
		b[1][1] = new Block(1,1,1);
		b[3][3] = new Block(1,3,3);
	}
	
	public void template2(){
		int[][] temp = new int[4][4];
		temp[0][1] = 1;
		temp[0][2] = 1;
		temp[0][3] = 1;
		this.t = temp;
		b = new Block[4][4];
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				b[i][j] = new Block(0, i, j);
			}
		}
		b[0][1] = new Block(1, 0, 1);
		b[0][2] = new Block(1, 0, 2);
		b[0][3] = new Block(1, 0, 2);
		
	}
	
	public void template3(){
		int[][] temp = new int[4][4];
		temp[2][1] = 1;
		temp[2][0] = 1;
		this.t = temp;
		b = new Block[4][4];
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				b[i][j] = new Block(0, i, j);
			}
		}
		b[2][1] = new Block(1, 2, 1);
		b[2][0] = new Block(1, 2, 0);
	}
	
	public void template4() {
		int[][] temp = new int[3][4];
		temp[1][0] = 1;
		temp[2][0] = 1;
		temp[2][1] = 1;
		this.t= temp;
		b = new Block[3][4];
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++) {
				b[i][j] = new Block(0,i,j);
			}
		}
		b[0][1] = new Block(1,0,1);
		b[0][2] = new Block(1,0,2);
		b[1][2] = new Block(1,1,2);
	}
	
	/*
	 * 0 0 1
	 * 1 0 0
	 * 1 0 0
	 */
	public void template5() {
		int[][] temp = new int[3][3];
		temp[2][0] = 1;
		temp[1][0] = 1;
		temp[2][0] = 1;
		this.t = temp;
		b = new Block[3][3];
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				b[i][j] = new Block(0,i,j);
			}
		}
	}
	
	/*
	 * 1 1 0 0
	 * 0 0 0 0
	 * 0 0 0 0
	 * 0 0 0 0
	 */
	public void template6() {
		int[][] temp = new int[4][4];
		temp[0][0] = 1;
		temp[1][0] = 1;
		this.t = temp;
		b = new Block[4][4];
		for (int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				b[i][j] = new Block(0,i,j);
			}
		}
	}
	
	/*
	 * 0 0 0 1
	 * 1 0 0 0
	 * 1 0 0 0
	 */
	public void template7() {
		int[][] temp = new int[4][3];
		temp[3][0] = 1;
		temp[1][1] = 1;
		temp[2][2] = 1;
		this.t = temp;
		b = new Block[4][3];
		for (int i = 0; i < 4; i++) {
			for(int j = 0; j < 3; j++) {
				b[i][j] = new Block(0,i,j);
			}
		}
	}
	
	/*
	 * 0 0 0 0
	 * 0 0 0 0
	 * 0 0 0 1 
	 * 1 0 0 1
	 */
	public void template8() {
		int[][] temp = new int[4][4];
		temp[3][2] = 1;
		temp[0][3] = 1;
		temp[3][3] = 1;
		this.t = temp;
		b = new Block[4][4];
		for (int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				b[i][j] = new Block(0,i,j);
			}
		}
	}
	
	/*
	 * 0 0 0 
	 * 0 0 0
	 * 0 0 0
	 * 1 1 0
	 */
	public void template9() {
		int[][] temp = new int[3][4];
		temp[0][3] = 1;
		temp[1][3] = 1;
		this.t = temp;
		b = new Block[3][4];
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++) {
				b[i][j] = new Block(0,i,j);
			}
		}
	}
	
	/*
	 * 0 0 0 0
	 * 0 0 0 0
	 * 0 1 1 0
	 * 0 0 0 0
	 */
	public void template10() {
		int[][] temp = new int[4][4];
		temp[1][2] = 1;
		temp[2][2] = 1;
		this.t = temp;
		b = new Block[4][4];
		for (int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				b[i][j] = new Block(0,i,j);
			}
		}
	}
	
	/*
	 * 0 0 0 0
	 * 1 1 0 0
	 * 0 0 0 0
	 * 0 0 0 0
	 */
	public void template11() {
		int[][] temp = new int[4][4];
		temp[0][1] = 1;
		temp[1][1] = 1;
		this.t = temp;
		b = new Block[4][4];
		for (int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				b[i][j] = new Block(0,i,j);
			}
		}
	}
	
	public int[][] getTemp(){
		return this.t;
	}
	
	public Block[][] getBlockTemp(){
		return this.b;
	}
}

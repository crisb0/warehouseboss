package map;
import entity.Block;

public class Template {
	private int[][] t;
	private Block[][] b;
	
	public Template(int i){
		//find better way of doing this instead of a whole lot of else ifs
		if(i == 0){
			template1();
		} else if (i == 1){
			template2();
		} else if (i == 2){
			template3();
		} else if (i == 4){
			template4();
		} else if (i == 5){
			template5();
		} else if (i == 6){
			template6();
		} else if (i == 7){
			template7();
		} else if (i == 8){
			template8();
		} else if (i == 9){
			template9();
		} else if (i == 10){
			template10();
		} else if (i == 11){
			template11();
		} else if (i == 12){
			template4();
		} 
	}
	
	public void template1(){
		/*b = new Block[2][4];
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				b[i][j] = new Block(0, i, j);
			}
		}
		b[0][0] = new Block(1,0,0);
		b[1][1] = new Block(1,1,1);
		b[3][3] = new Block(1,3,3);
		b[3][2] = new Block(1,3,2);*/
		b = new Block[2][3];
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 3; j++){
				b[i][j] = new Block(0, i ,j);
			}
		}
		
	}
	
	public void template2(){
		/*b = new Block[4][4];
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				b[i][j] = new Block(0, i, j);
			}
		}
		b[0][1] = new Block(1, 0, 1);
		b[0][2] = new Block(1, 0, 2);
		b[0][3] = new Block(1, 0, 2);
		b[1][3] = new Block(1,1,3);
		b[2][3] = new Block(1,2,3);*/
		b = new Block[2][2];
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 2; j++){
				b[i][j] = new Block(0, i ,j);
			}
		}
	}
	
	public void template3(){
		/*b = new Block[4][4];
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				b[i][j] = new Block(0, i, j);
			}
		}
		b[2][1] = new Block(1, 2, 1);
		b[2][0] = new Block(1, 2, 0);
		b[0][1] = new Block(1,0,1);*/
		b = new Block[3][3];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				b[i][j] = new Block(0, i ,j);
			}
			b[1][1] = new Block(1, 1, 1);
		}
	}
	
	public void template4() {
		b = new Block[4][4];
		for (int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				b[i][j] = new Block(0,i,j);
			}
		}
		b[1][0] = new Block(1,0,1);
		b[2][0] = new Block(1,0,2);
		b[2][3] = new Block(1,1,2);
		b[3][3] = new Block(1,3,3);
	}
	
	/*
	 * 0 0 1
	 * 1 0 0
	 * 1 0 0
	 */
	public void template5() {
		b = new Block[5][5];
		for (int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				b[i][j] = new Block(0,i,j);
			}
		}
		b[0][0] = new Block(1,0,0);
		b[0][1] = new Block(1,0,1);
		b[0][2] = new Block(1,0,2);
		b[3][0] = new Block(1,3,0);
		b[1][1] = new Block(1,1,1);
		b[1][2] = new Block(1,1,2);
	}
	
	/*
	 * 1 1 0 0
	 * 0 0 0 0
	 * 0 0 0 0
	 * 0 0 0 0
	 */
	public void template6() {
		b = new Block[4][4];
		for (int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				b[i][j] = new Block(0,i,j);
			}
		}
		b[0][0] = new Block(1,0,0);
		b[1][0] = new Block(1,1,0);
	}
	
	/*
	 * 0 0 0 1
	 * 1 0 0 0
	 * 1 0 0 0
	 */
	public void template7() {
		b = new Block[4][4];
		for (int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				b[i][j] = new Block(0,i,j);
			}
		}
		b[0][2] = new Block(1,0,2);
		b[3][0] = new Block(1,3,0);
		b[0][1] = new Block(1,0,1);
	}
	
	/*
	 * 0 0 0 0
	 * 0 0 0 0
	 * 0 0 0 1 
	 * 1 0 0 1
	 */
	public void template8() {
		b = new Block[4][4];
		for (int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				b[i][j] = new Block(0,i,j);
			}
		}
		b[3][2] = new Block(1, 3, 2);
		b[0][3] = new Block(1,0,3);
		b[3][3] = new Block(1,3,3);
	}
	//template needs to be big enough so that if all 4 of them are chosen,
	//there are still gaps to go trhough
	//pre change: https://puu.sh/vQscA/42d2c9b8f2.png
	//to maek this grid, i simply added this template 4 times to 'ts' in MapGenerator.java -> createTemplate()
	//to ensure this doesn't happen, i tried rotating it to achieve: https://puu.sh/vQsgU/e1dacd227e.png
	//which is slightly better!!
	//i may redesign this later!!!
		
	/* christine's template:
	 * 0 0 0 
	 * 0 0 0
	 * 0 0 0
	 * 1 1 0
	 */
	/* charley's template: 
	 * 0 0 0 0
	 * 0 0 0 0
	 * 1 1 0 0
	 */
	public void template9() {
		b = new Block[4][3];
		for (int i = 0; i < 4; i++) {
			for(int j = 0; j < 3; j++) {
				b[i][j] = new Block(0,i,j);
			}
		}
		b[0][2] = new Block(1,0,2);
		b[1][2] = new Block(1,1,2);
	}
	
	/*
	 * 0 0 0 0
	 * 0 1 0 0
	 * 0 1 1 0
	 * 0 0 0 0
	 */
	public void template10() {
		b = new Block[4][4];
		for (int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				b[i][j] = new Block(0,i,j);
			}
		}
		b[1][2] = new Block(1,1,2);
		b[2][2] = new Block(1,2,2);
		b[1][1] = new Block(1,1,1);
	}
	
	/*
	 * 0 0 0 0
	 * 1 1 0 0
	 * 0 0 0 0
	 * 0 0 0 0
	 */
	public void template11() {
		b = new Block[4][4];
		for (int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				b[i][j] = new Block(0,i,j);
			}
		}
		b[0][1] = new Block (1,0,1);
		b[1][1] = new Block(1,1,1);
		b[2][2] = new Block(1,2,2);
	}
	
	public Block[][] getBlockTemp(){
		return this.b;
	}
}

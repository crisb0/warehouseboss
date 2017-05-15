public class Template {
	private int[][] t;
	private Block[][] b;
	
	public Template(int i){
		//find better way of doing this instead of a whole lot of else ifs
		if(i == 1){
			template1();
		} else if (i == 2){
			template2();
		} else if (i == 3){
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
		temp[0][3] = 1;
		this.t= temp;
		b = new Block[3][4];
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++) {
				b[i][j] = new Block(0,i,j);
			}
		}
		b[1][0] = new Block(1,0,1);
		b[2][0] = new Block(1,0,2);
		b[2][3] = new Block(1,1,2);
	}
	
	/*
	 * 0 0 1
	 * 1 0 0
	 * 1 0 0
	 */
	public void template5() {
		int[][] temp = new int[3][3];
		temp[2][0] = 1;
		temp[0][1] = 1;
		temp[0][2] = 1;
		this.t = temp;
		b = new Block[3][3];
		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				b[i][j] = new Block(0,i,j);
			}
		}
		b[2][0] = new Block(1,0,0);
		b[0][1] = new Block(1,0,1);
		b[0][2] = new Block(1,0,2);
		
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
		b[0][0] = new Block(1,0,0);
		b[1][0] = new Block(1,1,0);
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
		b[3][0] = new Block(1,3,0);
		b[1][1] = new Block(1, 1, 1);
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
		int[][] temp = new int[4][3];
		temp[0][2] = 1;
		temp[1][2] = 1;
		this.t = temp;
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
		b[1][2] = new Block(1,1,2);
		b[2][2] = new Block(1,2,2);
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
		b[0][1] = new Block (1,0,1);
		b[1][1] = new Block(1,1,1);
	}
	
	public int[][] getTemp(){
		return this.t;
	}
	
	public Block[][] getBlockTemp(){
		return this.b;
	}
}
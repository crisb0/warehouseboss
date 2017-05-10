
public class Template {
	private int[][] t;
	private Block[][] b;
	
	public Template(int i){
		if(i == 1){
			template1();
		} else if (i == 2){
			template2();
		} else {
			template3();
		}
	}
	
	public void template1(){
		int[][] temp = new int[3][2];
		this.t = temp;
		b = new Block[3][2];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 2; j++){
				b[i][j] = new Block(0, i, j);
			}
		}
	}
	
	public void template2(){
		int[][] temp = new int[2][2];
		this.t = temp;
		b = new Block[3][2];
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 2; j++){
				b[i][j] = new Block(0, i, j);
			}
		}
		
	}
	
	public void template3(){
		int[][] temp = new int[3][3];
		temp[1][1] = 1;
		this.t = temp;
		b = new Block[3][3];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				b[i][j] = new Block(0, i, j);
			}
		}
		b[1][1] = new Block(1, 1, 1);
	}
	
	public int[][] getTemp(){
		return this.t;
	}
	
	public Block[][] getBlockTemp(){
		return this.b;
	}
}

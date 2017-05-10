public class StartingMap {

	private int[][] prototype = new int[8][8];
	private Block[][] blocks = new Block[8][8];
	
	public StartingMap(){
		createPrototype();
	}
	
	public void createPrototype(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(i <= 1 || i >= 5){
					this.prototype[i][j] = 1;
					this.blocks[i][j] = new Block(1);
				} else {
					if(j <= 1 || j >= 6){
						this.prototype[i][j] = 1;
						this.blocks[i][j] = new Block(1);
					} else {
						this.prototype[i][j] = 0;
						this.blocks[i][j] = new Block(0);
					}
				}
			}
		}
		this.prototype[3][3] = 1;
		this.blocks[3][3].setWall();
		this.prototype[3][4] = 1;
		this.blocks[3][4].setWall();
	}
	
	public int[][] getMap(){
		return this.prototype;
	}
	
	public Block[][] getBlockMap(){
		return this.blocks;
	}	
	
}

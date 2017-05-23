package map;
import entity.Block;

public class StartingMap {
	
	private static final int DIMENSIONS = 8;
	private Block[][] blocks = new Block[DIMENSIONS][DIMENSIONS];
	
	public StartingMap(){
		createPrototype();
	}
	
	public void createPrototype(){
		for(int i = 0; i < DIMENSIONS; i++){
			for(int j = 0; j < DIMENSIONS; j++){
				if(i <= 1 || i >= 5){
					this.blocks[i][j] = new Block(1, i ,j);
				} else {
					if(j <= 1 || j >= 6){
						this.blocks[i][j] = new Block(1, i ,j);
					} else {
						this.blocks[i][j] = new Block(0, i ,j);
					}
				}
			}
		}
		this.blocks[3][3].setType(1);
		this.blocks[3][4].setType(1);
	}
	
	public Block[][] getBlockMap(){
		return this.blocks;
	}	
}

import java.util.ArrayList;

public class Block {
	private int type; 
	ArrayList<Block> canGoTo = new ArrayList<Block>();
	
	public Block(int i){
		this.type = i;
	}
	
	public void setWall(){
		this.type = 1;
	}
	public void setPath(){
		this.type = 0;
	}
	public int getType(){
		return this.type;
	}

	public void setType(int i) {

		this.type = i;
		
	}
}

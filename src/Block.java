import java.util.ArrayList;

public class Block {
	private int type;
	private int i; 
	private int j; 
	private ArrayList<Block> canGoTo = new ArrayList<Block>();
	
	public Block(int type, int i, int j){
		this.type = type;
		this.i = i;
		this.j = j;
	}
	
	public int getType(){
		return this.type;
	}

	public void setType(int i) {
		this.type = i;
	}

	public void print() {
		System.out.println(this.i + " " + this.j);
	}

	public int getJ() {
		return this.j;
	}

	public int getI() {
		return this.i;
	}

	public void addCanGoTo(Block block) {
		this.canGoTo.add(block);
	}

	public ArrayList<Block> connectingBlocks() {
		return this.canGoTo;
	}
		
}

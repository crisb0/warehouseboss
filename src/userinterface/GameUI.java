package userinterface;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import game.Game;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import map.Map;
import map.MapGenerator;

/**
 * This is the main game class. It holds both the game
 * loop (Well technically only for animation) and the
 * handing of UI elements.
 * 
 * This class can also be extended to provide other usability
 * such as making a tutorial for the player. This has being
 * done so in the tutorial ui class.
 * 
 * @author z5115782
 *
 */
public class GameUI extends AnimationTimer{
	protected final int ANIM_DOWN = 0;
	protected final int ANIM_LEFT = 1;
	protected final int ANIM_UP = 2;
	protected final int ANIM_RIGHT = 3;
	protected final int ANIM_NONE = -1;
	
	protected int spriteSize;
	protected int tileSize;
	
	protected Game game;
	protected Game.Difficulty diff;
	
	protected Image imgWall;
	protected Image imgPlayer;
	protected Image imgBox;
	protected Image imgBoxOnGoal;
	protected Image imgGoal;
	
	protected boolean animating;
	protected double xAnimOffset;
	protected double yAnimOffset;
	protected double animStepsX;
	protected double animStepsY;
	protected int maxAnimCounter;
	protected int animCounter;
	protected int animDir;
	protected Point prevPPos;
	
	@FXML protected Canvas mainCanvas;
	@FXML protected Button backBtn;
	@FXML protected Button undoBtn;
	@FXML protected Label lblDiff;
	@FXML protected Label lblUndos;
	@FXML protected Label lblUndosLeft;
	
	/**
	 * This function is called after all of the FXML elements are loaded into
	 * the class. As a result, we are able to set up the FXML elements. The
	 * initial variable states are also called in this function for convenience
	 * as the class functionality doesn't start in the constructor.
	 */
	@FXML
	public void initialize(){
		this.animating = false;
		this.xAnimOffset = 0;
		this.yAnimOffset = 0;
		this.animStepsX = 0;
		this.animStepsY = 0;
		this.animCounter = 0;
		this.animDir = 0;
		this.maxAnimCounter = 20;
		
		this.tileSize = 48;
		/*
		 * The sprite size refers to the size of the sprites draw in
		 * the player's sprite sheet.
		 */
		this.spriteSize = 48;
		
		this.loadResources();
	}
	
	/**
	 * Used to set the difficulty of the map to be generated. The map generation
	 * code has being bundled into this function as this function can only be
	 * called after the initialize function is called.
	 * @param diff
	 */
	public void setDifficulty(Game.Difficulty diff){
		this.diff = diff;
		this.lblDiff.setText(diff.toString());
		
		MapGenerator mp = new MapGenerator();
		Map nm = new Map(mp);
		this.game = new Game(nm);
		
		this.displayMap();
	}
	
	/**
	 * Loads all of the resources needed to render out the map.
	 */
	protected void loadResources(){
		this.imgWall = new Image("/Images/wall.png");
		this.imgPlayer = new Image("/Images/player.png");
		this.imgBox = new Image("/Images/crate.png");
		this.imgBoxOnGoal = new Image("/Images/crate-on-goal.png");
		this.imgGoal = new Image("/Images/goal.png");
	}
	
	/**
	 * Loads up the mainCanva's drawing area and then calls all of
	 * the rendering functions, parsing the drawing area into them.
	 * 
	 * This reduces the number of times the mainCanva's drawing area
	 * have to be called.
	 */
	protected void displayMap(){
		GraphicsContext gc = mainCanvas.getGraphicsContext2D();
		this.clearMap(gc);
		this.drawMap(gc);
		this.drawPlayer(gc);
	}
	
	/**
	 * Clears anything drawn to the canvas.
	 * @param gc mainCanva's drawing area
	 */
	protected void clearMap(GraphicsContext gc){
		gc.clearRect(0, 0, 
				this.game.getMaxWidth() * this.tileSize,
				this.game.getMaxHeight() * this.tileSize);
	}
	
	/**
	 * Draws the whole level to the canvas.
	 * @param gc mainCanva's drawing area
	 */
	protected void drawMap(GraphicsContext gc){
		int[][] grid = this.game.getGrid();
		
		for(int x = 0; x < grid.length; x++){
			for(int y = 0; y < grid[x].length; y++){
				if(grid[x][y] == Game.OBSTACLE){
					gc.drawImage(this.imgWall, 
							x * this.tileSize, y * this.tileSize, 
							this.tileSize, this.tileSize);
					continue;
				}
				
				if(grid[x][y] == Game.BOX){
					boolean onGoal = false;
					
					ArrayList<Point> goalPoints = (ArrayList<Point>) this.game.getGoalLocs();
					
					for(Point gPt : goalPoints){
						if(gPt.getX() == x && gPt.getY() == y){
							onGoal = true;
							break;
						}
					}
					
					if(onGoal){
						gc.drawImage(this.imgBoxOnGoal, 
								x * this.tileSize, y * this.tileSize, 
								this.tileSize, this.tileSize);
					} else {
						gc.drawImage(this.imgBox, 
								x * this.tileSize, y * this.tileSize, 
								this.tileSize, this.tileSize);
					}
					continue;
				}
				
				if(grid[x][y] == Game.GOAL){
					gc.drawImage(this.imgGoal, 
							x * this.tileSize, y * this.tileSize, 
							this.tileSize, this.tileSize);
					continue;
				}
			}
		}
	}
	
	/**
	 * Animates the player onto the main canvas.
	 * @param gc mainCanva's drawing area
	 */
	protected void drawPlayer(GraphicsContext gc){
		if(this.animDir != ANIM_NONE){
			Point pPost = this.game.getPlayer().getLoc();
			
			/*
			 * ((this.animCounter/5) % 4) slows the animation down
			 * so that the player sprite only changes its image every
			 * 5 frames. The modulus of 4 prevents the image from
			 * overflowing the sprite sheet
			 */
			gc.drawImage(this.imgPlayer, 
					this.spriteSize * this.animDir, 
					this.spriteSize * ((this.animCounter / 5) % 4),
					this.spriteSize, this.spriteSize, 
					pPost.getX() * this.tileSize + this.xAnimOffset,
					pPost.getY() * this.tileSize + this.yAnimOffset, 
					this.tileSize, this.tileSize);
		} else {
			Point pPost = this.game.getPlayer().getLoc();
			gc.drawImage(this.imgPlayer, 
					this.spriteSize * -this.animDir, 
					this.spriteSize,
					this.spriteSize, this.spriteSize, 
					pPost.getX() * this.tileSize,
					pPost.getY() * this.tileSize, 
					this.tileSize, this.tileSize);
		}
	}
	
	/**
	 * Triggers the animation process.
	 * @param dir This is the code to tell the animation function which
	 * direction should it animate the player going in.
	 *   0 means going down
	 *   1 means going left
	 *   2 means going up
	 *   3 means going right
	 *  -1 is a special code to tell the animation function to not animate.
	 */
	protected void startAnimation(int dir){
		this.animating = true;
		this.animDir = dir;
		
		Point curPPos = this.game.getPlayer().getLoc();
		this.xAnimOffset = (this.prevPPos.getX() - curPPos.getX()) * this.tileSize;
		this.yAnimOffset = (this.prevPPos.getY() - curPPos.getY()) * this.tileSize;
		this.animStepsX = (double)this.xAnimOffset / (double)this.maxAnimCounter;
		this.animStepsY = (double)this.yAnimOffset / (double)this.maxAnimCounter;
		
		this.start();
	}
	
	/**
	 * This function is used when anything is needed to be done
	 * after an animation is finished. The tutorialui class
	 * overrides this class, making it useful.
	 */
	protected void finishAnimation(){
		return;
	}
	
	/**
	 * This function sets up the main menu and then forces the whole
	 * window to go back to the main menu.
	 * @throws IOException When accessing the fxml file to render the whole
	 * menu.
	 */
	protected void goBackMain() throws IOException{
		Parent gameUIRoot = FXMLLoader.load(getClass().getResource("MainUILayout.fxml"));
		Scene gameUIScene = new Scene(gameUIRoot, MainApplication.WIDTH, MainApplication.HEIGHT);
		Stage stage = (Stage) backBtn.getScene().getWindow();
		stage.setScene(gameUIScene);
		stage.show();
	}
	
	/**
	 * This function is used to animate the player movements. It is called
	 * every frame when this class animation has being triggered to start.
	 * 
	 * @param now Never used by the override, read the javafx documentation on
	 * AnimationTimers for information.
	 */
	@Override
	public void handle(long now) {
		if(this.animCounter < this.maxAnimCounter){
			this.animCounter++;
			this.xAnimOffset -= this.animStepsX;
			this.yAnimOffset -= this.animStepsY;
		} else {
			this.animCounter = 0;
			this.animating = false;
			this.xAnimOffset = 0.0f;
			this.yAnimOffset = 0.0f;
			this.finishAnimation();
			this.stop();
		}
		
		this.displayMap();
	}
	
	/**
	 * Handles both the back button and undo button
	 * @param event The type of action used to fire this function
	 */
	@FXML
	protected void onButtonClick(ActionEvent event){
		if(event.getSource() == backBtn){
			try {
				this.goBackMain();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else if (event.getSource() == undoBtn){
			this.game.undoMove();
			this.displayMap();
		}
	}
	
	/**
	 * Handle the player's movement inputs.
	 * @param event The type of action used to fire this function
	 */
	@FXML
	protected void onKeyPress(KeyEvent event){
		if(!this.animating){
			this.prevPPos = this.game.getPlayer().getLoc();
			
			if(event.getCode() == KeyCode.W){
				if(this.game.move('w')) this.startAnimation(ANIM_UP);
			} else if (event.getCode() == KeyCode.A) {
				if(this.game.move('a')) this.startAnimation(ANIM_LEFT);
			} else if (event.getCode() == KeyCode.S) {
				if(this.game.move('s')) this.startAnimation(ANIM_DOWN);
			} else if (event.getCode() == KeyCode.D) {
				if(this.game.move('d')) this.startAnimation(ANIM_RIGHT);
			}
		}
	}
}

package userinterface;

import java.awt.Point;
import java.io.IOException;

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

public class GameUI extends AnimationTimer{
	protected final int SPRITE_SIZE = 48;
	protected final int TILE_SIZE = 48;
	
	protected Game game;
	protected Game.Difficulty diff;
	
	protected Image imgWall;
	protected Image imgPlayer;
	protected Image imgBox;
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
	
	@FXML
	public void initialize(){
		MapGenerator mp = new MapGenerator();
		Map nm = new Map(mp);
		this.game = new Game(nm);
		this.animating = false;
		this.xAnimOffset = 0;
		this.yAnimOffset = 0;
		this.animStepsX = 0;
		this.animStepsY = 0;
		this.animCounter = 0;
		this.animDir = 0;
		this.maxAnimCounter = 20;
		
		this.loadResources();
		this.displayMap();
	}
	
	public void setDifficulty(Game.Difficulty diff){
		this.diff = diff;
		this.lblDiff.setText(diff.toString());
	}
	
	protected void loadResources(){
		this.imgWall = new Image("/Images/wall.png");
		this.imgPlayer = new Image("/Images/player.png");
		this.imgBox = new Image("/Images/crate.png");
		this.imgGoal = new Image("/Images/goal.png");
	}
	
	protected void displayMap(){
		GraphicsContext gc = mainCanvas.getGraphicsContext2D();
		this.clearMap(gc);
		this.drawMap(gc);
		this.drawPlayer(gc);
	}
	
	protected void clearMap(GraphicsContext gc){
		gc.clearRect(0, 0, 500, 500);
	}
	
	protected void drawMap(GraphicsContext gc){
		int[][] grid = this.game.getGrid();
		
		for(int x = 0; x < 10; x++){
			for(int y = 0; y < 10; y++){
				if(grid[x][y] == Game.OBSTACLE){
					gc.drawImage(this.imgWall, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
					continue;
				}
				
				if(grid[x][y] == Game.BOX){
					gc.drawImage(this.imgBox, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
					continue;
				}
				
				if(grid[x][y] == Game.GOAL){
					gc.drawImage(this.imgGoal, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
					continue;
				}
			}
		}
	}
	
	protected void drawPlayer(GraphicsContext gc){
		Point pPost = this.game.getPlayer().getLoc();
		gc.drawImage(this.imgPlayer, 
				SPRITE_SIZE * this.animDir, 
				SPRITE_SIZE * ((this.animCounter/5) % 4),
				SPRITE_SIZE, SPRITE_SIZE, 
				pPost.getX() * TILE_SIZE + this.xAnimOffset,
				pPost.getY() * TILE_SIZE + this.yAnimOffset, 
				TILE_SIZE, TILE_SIZE);
	}
	
	protected void startAnimation(int dir){
		this.animating = true;
		this.animDir = dir;
		
		Point curPPos = this.game.getPlayer().getLoc();
		this.xAnimOffset = (this.prevPPos.getX() - curPPos.getX()) * TILE_SIZE;
		this.yAnimOffset = (this.prevPPos.getY() - curPPos.getY()) * TILE_SIZE;
		this.animStepsX = (double)this.xAnimOffset / (double)this.maxAnimCounter;
		this.animStepsY = (double)this.yAnimOffset / (double)this.maxAnimCounter;
		
		this.start();
	}
	
	protected void finishAnimation(){
		/*
		 * This function is used when anything is needed to be done
		 * after an animation is finished. The tutorialui class
		 * overrides this class, making it useful.
		 */
		return;
	}
	
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
	
	@FXML
	protected void onButtonClick(ActionEvent event) throws IOException{
		if(event.getSource() == backBtn){
			Parent gameUIRoot = FXMLLoader.load(getClass().getResource("MainUILayout.fxml"));
			Scene gameUIScene = new Scene(gameUIRoot, MainApplication.WIDTH, MainApplication.HEIGHT);
			Stage stage = (Stage) backBtn.getScene().getWindow();
			stage.setScene(gameUIScene);
			stage.show();
		} else if (event.getSource() == undoBtn){
			this.game.undoMove();
			this.displayMap();
		}
	}
	
	@FXML
	protected void onKeyPress(KeyEvent event){
		if(!this.animating){
			this.prevPPos = this.game.getPlayer().getLoc();
			
			if(event.getCode() == KeyCode.W){
				if(this.game.move('w')) this.startAnimation(2);
			} else if (event.getCode() == KeyCode.A) {
				if(this.game.move('a')) this.startAnimation(1);
			} else if (event.getCode() == KeyCode.S) {
				if(this.game.move('s')) this.startAnimation(0);
			} else if (event.getCode() == KeyCode.D) {
				if(this.game.move('d')) this.startAnimation(3);
			}
		}
	}
}

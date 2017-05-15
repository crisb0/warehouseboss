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
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import map.Map;
import map.MapGenerator;

public class GameUI extends AnimationTimer{
	private Game game;
	private int tileSize;
	
	private Image imgWall;
	private Image imgPlayer;
	private Image imgBox;
	private Image imgGoal;
	
	private boolean animating;
	private double xAnimOffset;
	private double yAnimOffset;
	private double animStepsX;
	private double animStepsY;
	private int maxAnimCounter;
	private int animCounter;
	private Point prevPPos;
	
	@FXML private Canvas mainCanvas;
	@FXML private Button backBtn;
	@FXML private Button undoBtn;
	
	@FXML
	public void initialize(){
		MapGenerator mp = new MapGenerator();
		Map nm = new Map(mp);
		this.game = new Game(nm);
		this.tileSize = 48;
		this.animating = false;
		this.xAnimOffset = 0;
		this.yAnimOffset = 0;
		this.animStepsX = 0;
		this.animStepsY = 0;
		this.animCounter = 0;
		this.maxAnimCounter = 8;
		
		this.loadResources();
		this.displayMap();
	}
	
	private void loadResources(){
		this.imgWall = new Image("/Images/wall.png");
		this.imgPlayer = new Image("/Images/player.png");
		this.imgBox = new Image("/Images/crate.png");
		this.imgGoal = new Image("/Images/goal.png");
	}
	
	private void displayMap(){
		GraphicsContext gc = mainCanvas.getGraphicsContext2D();
		this.clearMap(gc);
		this.drawMap(gc);
		this.drawPlayer(gc);
	}
	
	private void clearMap(GraphicsContext gc){
		gc.clearRect(0, 0, 500, 500);
	}
	
	private void drawMap(GraphicsContext gc){
		int[][] grid = this.game.getGrid();
		
		for(int x = 0; x < 10; x++){
			for(int y = 0; y < 10; y++){
				if(grid[x][y] == Game.OBSTACLE){
					gc.drawImage(this.imgWall, x * this.tileSize, y * this.tileSize, this.tileSize, this.tileSize);
					continue;
				}
				
				if(grid[x][y] == Game.BOX){
					gc.drawImage(this.imgBox, x * this.tileSize, y * this.tileSize, this.tileSize, this.tileSize);
					continue;
				}
				
				if(grid[x][y] == Game.GOAL){
					gc.drawImage(this.imgGoal, x * this.tileSize, y * this.tileSize, this.tileSize, this.tileSize);
					continue;
				}
			}
		}
	}
	
	private void drawPlayer(GraphicsContext gc){
		Point pPost = this.game.getPlayer().getLoc();
		gc.drawImage(this.imgPlayer, pPost.getX() * this.tileSize + this.xAnimOffset,
				pPost.getY() * this.tileSize + this.yAnimOffset, this.tileSize, this.tileSize);
	}
	
	private void startAnimation(){
		this.animating = true;
		
		Point curPPos = this.game.getPlayer().getLoc();
		this.xAnimOffset = (this.prevPPos.getX() - curPPos.getX()) * this.tileSize;
		this.yAnimOffset = (this.prevPPos.getY() - curPPos.getY()) * this.tileSize;
		this.animStepsX = (double)this.xAnimOffset / (double)this.maxAnimCounter;
		this.animStepsY = (double)this.yAnimOffset / (double)this.maxAnimCounter;
		
		this.start();
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
			this.stop();
		}
		
		this.displayMap();
	}
	
	@FXML
	private void onButtonClick(ActionEvent event) throws IOException{
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
	private void onKeyPress(KeyEvent event){
		if(!this.animating){
			this.prevPPos = this.game.getPlayer().getLoc();
			if(event.getCode() == KeyCode.W){
				if(this.game.move('w')) this.startAnimation();
				
			} else if (event.getCode() == KeyCode.A){
				if(this.game.move('a')) this.startAnimation();
				
			} else if (event.getCode() == KeyCode.S){
				if(this.game.move('s')) this.startAnimation();
				
			} else if (event.getCode() == KeyCode.D){
				if(this.game.move('d')) this.startAnimation();
			}
		}
	}
}

package userinterface;

import java.awt.Point;

import game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import map.Map;
import map.TutorialMap;

public class TutorialUI extends GameUI{
	/*
	 * tutorialStage is what keeps track of the player's progress
	 * into the tutorial. The stage is represented as an integer
	 * and will just increments.
	 * 
	 * -- QUESTION --
	 * Should an enum be used to track all of these stages or should
	 * an integer just be used to keep track of it and just have
	 * the integer's representation be noted down in this comment.
	 * 
	 * The stages are as follow.
	 * 	Stage 0:
	 *    The initial stage in which the initial tutorial message
	 *    will pop up. The popup is linked under tut0.png
	 *
	 *  Stage 1:
	 *    The stage where the instructions are given to the player.
	 *    
	 *  Stage 2:
	 *    In this stage, the player will have to move to a marker
	 *    to get a feel of the game.
	 *    
	 *  Stage 3:
	 *    Once the player enters this stage, they will receive a
	 *    pop up to explain how they are meant to push the box.
	 *  
	 *  <<<STAGES TBA>>>
	 */
	private TutorialStage tutorialStage;
	
	private boolean canMove;
	
	private Image imgTutGoal;
	
	private Point stage2Goal;
	private Point stage4Goal;
	
	@FXML private Rectangle bgCover;
	@FXML private ImageView popUp;
	@FXML private Button contBtn;
	@FXML private Button undoBtn;
	
	/**
	 * This function is called after all of the FXML elements are loaded into
	 * the class. As a result, we are able to set up the FXML elements. The
	 * initial variable states are also called in this function for convenience
	 * as the class purpose doesn't technically start in the constructor.
	 */
	@FXML
	public void initialize(){
		/*
		 * Super() can not be called as we need to replace the random
		 * MapGenerator class with a class that generates a predefined
		 * level to teach the player. Thus, everything needs to be
		 * redefined in this function.
		 */
		TutorialMap mp = new TutorialMap();
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
		this.tutorialStage = TutorialStage.S0_OPENING_MSG;
		this.canMove = false;
		
		/*
		 * Initially, we are going to hide the undo button and then
		 * show it to the player later.
		 */
		this.undoBtn.setVisible(false);
		this.undoBtn.setDisable(true);
		
		/*
		 *  Load up the location of where the player needs to be to
		 *  advance the tutorial.
		 */
		this.stage2Goal = new Point(1,1);
		this.stage4Goal = new Point(3,1);
		
		this.loadResources();
		this.loadTutResources();
		this.displayMap();
	}
	
	/**
	 * The tutorialUI has overridden this for a single purpose.
	 * To add the ability to draw a marker. Super is not called
	 * as the marker must be drawn under the player which means a
	 * complete change in the function calling structure.
	 */
	@Override
	protected void displayMap(){
		GraphicsContext gc = mainCanvas.getGraphicsContext2D();
		this.clearMap(gc);
		this.drawTutGoal(gc);
		this.drawMap(gc);
		this.drawPlayer(gc);
	}
	
	/**
	 * This function loads up any new resources needed for the tutorial.
	 */
	private void loadTutResources(){
		this.imgTutGoal = new Image("/Images/circle-of-life.png");
	}
	
	/**
	 * Drawing the marker to indicate which area the player must go
	 * @param gc The area in the mainCanvas where we can draw
	 */
	private void drawTutGoal(GraphicsContext gc){
		if(this.tutorialStage == TutorialStage.S2_MOVEMENT_TUT){
			gc.drawImage(this.imgTutGoal, 
					this.stage2Goal.getX() * TILE_SIZE, 
					this.stage2Goal.getY() * TILE_SIZE,
					TILE_SIZE, TILE_SIZE);
			
		} else if(this.tutorialStage == TutorialStage.S4_PUSH_TUT){
			gc.drawImage(this.imgTutGoal, 
					this.stage4Goal.getX() * TILE_SIZE, 
					this.stage4Goal.getY() * TILE_SIZE,
					TILE_SIZE, TILE_SIZE);
		}
	}
	
	/**
	 * This function contains the code to hide or show the pop up
	 * where the instructions are given to the player. The function can
	 * be used to show a different image right after it popped up.
	 * 
	 * @param show The variable to indicate whether the pop up should
	 * be shown or not.
	 * @param img The image of the pop up
	 */
	private void showPopUp(boolean show, Image img){
		if(img != null)
			this.popUp.setImage(img);
		
		this.contBtn.setDisable(!show);
		this.contBtn.setVisible(show);
		this.popUp.setDisable(!show);
		this.popUp.setVisible(show);
		this.bgCover.setVisible(show);
		this.canMove = !show;
		this.displayMap();
	}
	
	/**
	 * Just need to add a more controlled version of the keypress.
	 * The functionality is never needed in the main game, thus it
	 * has being implemented here.
	 * 
	 * @param event The key used to fire the function
	 */
	@FXML
	private void controlledKeyPress(KeyEvent event){
		if(this.canMove) super.onKeyPress(event);
	}
	
	/**
	 * The function being useful here. It can be used to detect if the
	 * player is in the right position and thus advance the tutorial.
	 */
	@Override
	protected void finishAnimation(){
		if(this.tutorialStage == TutorialStage.S2_MOVEMENT_TUT){
			Point pLoc = this.game.getPlayer().getLoc();
			
			if(pLoc.getX() == this.stage2Goal.getX() && pLoc.getY() == this.stage2Goal.getY()){
				this.tutorialStage = TutorialStage.S3_PUSH_MSG;
				this.showPopUp(true, new Image("/Images/pop-up/tut2.png"));
			}
			
		} else if (this.tutorialStage == TutorialStage.S4_PUSH_TUT){
			/*
			 * ======================== IMPORTANT ==========================
			 * THIS SHOULD ONLY BE TEMPORARY CODE. This code is a extremely 
			 * cheap as we are assuming the player should be in the position
			 * of the box only because the map design allows for it. This
			 * needs to be changed as it makes changing the tutorial level
			 * a major hassle.
			 * 
			 * To change it, we need the game class to return the location of
			 * all the boxes.
			 */
			Point pLoc = this.game.getPlayer().getLoc();
			
			if(pLoc.getX() == this.stage4Goal.getX() - 1 && pLoc.getY() == this.stage2Goal.getY()){
				this.tutorialStage = TutorialStage.S5_BLOCK_MSG;
				this.showPopUp(true, new Image("/Images/pop-up/tut3.png"));
			}
		}
	}
	
	/**
	 * This function is linked to the contBtn. Whenever it is pressed,
	 * it fires this function.
	 * 
	 * @param event The button used to fire this function
	 */
	@FXML
	private void continueTutorial(ActionEvent event){
		
		/*
		 *  Remember, this is asking which stage it is currently in
		 *  before running the appropriate code.
		 */
		
		switch(this.tutorialStage){
		case S0_OPENING_MSG:
			this.tutorialStage = TutorialStage.S1_MOVEMENT_MSG;
			this.popUp.setImage(new Image("/Images/pop-up/tut1.png"));
			break;
			
		case S1_MOVEMENT_MSG:
			this.tutorialStage = TutorialStage.S2_MOVEMENT_TUT;
			this.showPopUp(false, null);
			break;
			
		case S3_PUSH_MSG:
			this.tutorialStage = TutorialStage.S4_PUSH_TUT;
			this.showPopUp(false, null);
			break;
		
		case S5_BLOCK_MSG:
			this.tutorialStage = TutorialStage.S6_UNDO_MSG;
			this.popUp.setImage(new Image("/Images/pop-up/tut4.png"));
			break;
		default:
			break;
		}
	}
	
	private enum TutorialStage{
		S0_OPENING_MSG, S1_MOVEMENT_MSG, S2_MOVEMENT_TUT, S3_PUSH_MSG, S4_PUSH_TUT,
		S5_BLOCK_MSG, S6_UNDO_MSG
	}
}

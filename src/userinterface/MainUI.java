package userinterface;

import java.io.IOException;

import game.Game;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class handles the main menu and any action needed to make it work.
 * @author RobustProgram
 *
 */
public class MainUI {
	private Timeline backgroundAnim;
	private FadeTransition mainMFadeOut;
	private FadeTransition mainMFadeIn;
	private FadeTransition diffMFadeOut;
	private FadeTransition diffMFadeIn;
	
	@FXML private ImageView backgroundImg;
	@FXML private Button startBtn;
	@FXML private Button tutBtn;
	@FXML private Button exitBtn;
	@FXML private Button easyBtn;
	@FXML private Button normalBtn;
	@FXML private Button hardBtn;
	@FXML private Button returnBtn;
	@FXML private GridPane mainMenu;
	@FXML private GridPane diffMenu;
	
	/**
	 * This function is called after all of the FXML elements are loaded into
	 * the class. As a result, we are able to set up the FXML elements. The
	 * initial variable states are also called in this function for convenience
	 * as the class functionality doesn't start in the constructor.
	 */
	@FXML
	public void initialize(){
		/*
		 * We load up all of the type of fades that are to be used by
		 * the two button menus.
		 */
		this.mainMFadeOut = new FadeTransition(Duration.millis(250));
		this.mainMFadeOut.setNode(mainMenu);
		this.mainMFadeOut.setFromValue(1.0);
		this.mainMFadeOut.setToValue(0.0);
		this.mainMFadeOut.setCycleCount(1);
		this.mainMFadeOut.setAutoReverse(false);
		
		this.mainMFadeIn = new FadeTransition(Duration.millis(250));
		this.mainMFadeIn.setNode(mainMenu);
		this.mainMFadeIn.setFromValue(0.0);
		this.mainMFadeIn.setToValue(1.0);
		this.mainMFadeIn.setCycleCount(1);
		this.mainMFadeIn.setAutoReverse(false);
		
		this.diffMFadeOut = new FadeTransition(Duration.millis(250));
		this.diffMFadeOut.setNode(diffMenu);
		this.diffMFadeOut.setFromValue(1.0);
		this.diffMFadeOut.setToValue(0.0);
		this.diffMFadeOut.setCycleCount(1);
		this.diffMFadeOut.setAutoReverse(false);
		
		this.diffMFadeIn = new FadeTransition(Duration.millis(250));
		this.diffMFadeIn.setNode(diffMenu);
		this.diffMFadeIn.setFromValue(0.0);
		this.diffMFadeIn.setToValue(1.0);
		this.diffMFadeIn.setCycleCount(1);
		this.diffMFadeIn.setAutoReverse(false);
		
		/*
		 * We kickstart the animation that is suppose to run in the background
		 */
		this.backgroundAnim = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(this.backgroundImg.imageProperty(), 
//                		new Image("/Images/s_whboss0.png"))),
                		new Image("/Images/whb1.png"))),
                new KeyFrame(Duration.millis(200), new KeyValue(this.backgroundImg.imageProperty(), 
//                		new Image("/Images/s_whboss1.png"))),
                		new Image("/Images/whb2.png"))),
                new KeyFrame(Duration.millis(400), new KeyValue(this.backgroundImg.imageProperty(), 
//                		new Image("/Images/s_whboss2.png"))),
                		new Image("/Images/whb3.png"))),
                new KeyFrame(Duration.millis(600), new KeyValue(this.backgroundImg.imageProperty(), 
//                		new Image("/Images/s_whboss3.png"))),
                		new Image("/Images/whb4.png"))),
                new KeyFrame(Duration.millis(800), new KeyValue(this.backgroundImg.imageProperty(), 
//                		new Image("/Images/s_whboss4.png"))),
                		new Image("/Images/whb5.png"))),
                new KeyFrame(Duration.millis(1000), new KeyValue(this.backgroundImg.imageProperty(), 
//                		new Image("/Images/s_whboss0.png")))
                		new Image("/Images/whb3.png")))
                );
		this.backgroundAnim.setCycleCount(Timeline.INDEFINITE);
		this.backgroundAnim.play();
	}
	
	/**
	 * Handles the buttons in the main buttons menu
	 * 
	 * @precondition event is not null
	 * @param event The action used to kick start this function
	 * @throws IOException
	 */
	@FXML
	private void onStandardBtnClick(ActionEvent event) throws IOException{
		if(event.getSource() == this.startBtn){
			/*
			 * If the start button is called, we will fade in the
			 * difficulty menu.
			 */
			this.mainMenu.setDisable(true);
			this.diffMenu.setDisable(false);
			this.diffMenu.setVisible(true);
			this.mainMFadeOut.playFromStart();
			this.diffMFadeIn.playFromStart();
			
		} else if (event.getSource() == this.returnBtn){
			/*
			 * If the return button is called, we will fade back to
			 * the main menu
			 */
			this.mainMenu.setDisable(false);
			this.diffMenu.setDisable(true);
			this.mainMFadeIn.playFromStart();
			this.diffMFadeOut.playFromStart();
			
		} else if (event.getSource() == this.tutBtn){
			/*
			 * We must always stop the animation for the background or else
			 * it will cause a memory leak due to the fact that it is set
			 * to run forever and forever.
			 */
			this.backgroundAnim.stop();
			this.backgroundAnim = null;
			Parent tutUIRoot = FXMLLoader.load(getClass().getResource("TutorialUILayout.fxml"));
			Scene tutUIScene = new Scene(tutUIRoot, MainApplication.WIDTH, MainApplication.HEIGHT);
			Stage stage = (Stage) this.startBtn.getScene().getWindow();
			stage.setScene(tutUIScene);
			stage.show();
			
		} else if (event.getSource() == this.exitBtn){
			Platform.exit();
		}
	}
	
	/**
	 * Handles the buttons in the difficulty buttons menu
	 * 
	 * @precondition event is not null
	 * @param event The action used to kick start this function
	 * @throws IOException
	 */
	@FXML
	private void onDiffBtnClick(ActionEvent event) throws IOException{
		/*
		 * We must always stop the animation for the background or else
		 * it will cause a memory leak due to the fact that it is set
		 * to run forever and forever.
		 */
		this.backgroundAnim.stop();
		this.backgroundAnim = null;
		
		FXMLLoader gameUILoader = new FXMLLoader(getClass().getResource("GameUILayout.fxml"));
		Scene gameUIScene = new Scene((Parent) gameUILoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
		GameUI gameUIController = gameUILoader.getController();
		
		if(event.getSource() == this.easyBtn)
			gameUIController.setDifficulty(Game.Difficulty.EASY);
		else if(event.getSource() == this.normalBtn)
			gameUIController.setDifficulty(Game.Difficulty.NORMAL);
		else if(event.getSource() == this.hardBtn)
			gameUIController.setDifficulty(Game.Difficulty.HARD);
		Stage stage = (Stage) this.startBtn.getScene().getWindow();
		stage.setScene(gameUIScene);
		stage.show();
	}
}

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
	@FXML private Button nightmareBtn;
	@FXML private Button returnBtn;
	@FXML private GridPane mainMenu;
	@FXML private GridPane diffMenu;
	
	@FXML
	public void initialize(){
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
		
		this.backgroundAnim = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(this.backgroundImg.imageProperty(), new Image("/Images/s_whboss0.png"))),
                new KeyFrame(Duration.millis(200), new KeyValue(this.backgroundImg.imageProperty(), new Image("/Images/s_whboss1.png"))),
                new KeyFrame(Duration.millis(400), new KeyValue(this.backgroundImg.imageProperty(), new Image("/Images/s_whboss2.png"))),
                new KeyFrame(Duration.millis(600), new KeyValue(this.backgroundImg.imageProperty(), new Image("/Images/s_whboss3.png"))),
                new KeyFrame(Duration.millis(800), new KeyValue(this.backgroundImg.imageProperty(), new Image("/Images/s_whboss4.png")))
                );
		this.backgroundAnim.setCycleCount(Timeline.INDEFINITE);
		this.backgroundAnim.play();
	}
	
	@FXML
	private void onStandardBtnClick(ActionEvent event) throws IOException{
		if(event.getSource() == this.startBtn){
			this.mainMenu.setDisable(true);
			this.diffMenu.setDisable(false);
			this.diffMenu.setVisible(true);
			this.mainMFadeOut.playFromStart();
			this.diffMFadeIn.playFromStart();
			
		} else if (event.getSource() == this.returnBtn){
			this.mainMenu.setDisable(false);
			this.diffMenu.setDisable(true);
			this.mainMFadeIn.playFromStart();
			this.diffMFadeOut.playFromStart();
			
		} else if (event.getSource() == this.tutBtn){
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
	
	@FXML
	private void onDiffBtnClick(ActionEvent event) throws IOException{
		this.backgroundAnim.stop();
		this.backgroundAnim = null;
		
		FXMLLoader gameUILoader = new FXMLLoader(getClass().getResource("GameUILayout.fxml"));
		Scene gameUIScene = new Scene(gameUILoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
		GameUI gameUIController = gameUILoader.getController();
		
		if(event.getSource() == this.easyBtn)
			gameUIController.setDifficulty(Game.Difficulty.EASY);
		else if(event.getSource() == this.normalBtn)
			gameUIController.setDifficulty(Game.Difficulty.NORMAL);
		else if(event.getSource() == this.hardBtn)
			gameUIController.setDifficulty(Game.Difficulty.HARD);
		else if(event.getSource() == this.nightmareBtn)
			gameUIController.setDifficulty(Game.Difficulty.NIGHTMARE);
		
		Stage stage = (Stage) this.startBtn.getScene().getWindow();
		stage.setScene(gameUIScene);
		stage.show();
	}
}

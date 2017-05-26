package userinterface;

import java.io.File;

import game.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main code that handles the javafx windows programming
 * @author z5115782
 */
public class MainApplication extends Application{
	/*
	 * Hard coded values, may need to be changed in the future
	 */
	public static final int WIDTH = 900;
	public static final int HEIGHT = 600;
	
	/**
	 * The function that handles the loading of the initial menu
	 * that is to be shown to the player by overriding the start
	 * method. The start method can be explored in the javafx
	 * documentations.
	 * @param primaryStage The reference to the main window
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent mainUIRoot = FXMLLoader.load(getClass().getResource("MainUILayout.fxml"));
			Scene mainUIScene = new Scene(mainUIRoot, MainApplication.WIDTH, MainApplication.HEIGHT);

			primaryStage.setResizable(false);
			primaryStage.setScene(mainUIScene);
			primaryStage.setTitle("Warehouse Boss");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop(){
		Game.isLoading = false;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

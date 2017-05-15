package userinterface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application{
	public static final int WIDTH = 900;
	public static final int HEIGHT = 600;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent mainUIRoot = FXMLLoader.load(getClass().getResource("MainUILayout.fxml"));
			Scene mainUIScene = new Scene(mainUIRoot, MainApplication.WIDTH, MainApplication.HEIGHT);

			primaryStage.setResizable(false);
			primaryStage.setScene(mainUIScene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

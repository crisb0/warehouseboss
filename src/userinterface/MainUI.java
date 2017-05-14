package userinterface;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainUI extends Application{
	public static final int WIDTH = 900;
	public static final int HEIGHT = 600;
	
	@FXML private ImageView backgroundImg;
	
	@FXML
	public void initialize(){
		Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(backgroundImg.imageProperty(), new Image("/Images/whboss0.png"))),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(backgroundImg.imageProperty(), new Image("/Images/whboss1.png"))),
                new KeyFrame(Duration.seconds(1), new KeyValue(backgroundImg.imageProperty(), new Image("/Images/whboss2.png"))),
                new KeyFrame(Duration.seconds(1.5), new KeyValue(backgroundImg.imageProperty(), new Image("/Images/whboss3.png"))),
                new KeyFrame(Duration.seconds(2), new KeyValue(backgroundImg.imageProperty(), new Image("/Images/whboss4.png")))
                );
		timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainUILayout.fxml"));
			
			Scene scene = new Scene(root, MainUI.WIDTH, MainUI.HEIGHT);

			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

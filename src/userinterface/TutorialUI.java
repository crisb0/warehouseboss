package userinterface;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TutorialUI {
	@FXML private Button backBtn;
	
	@FXML
	private void onButtonClick(ActionEvent event) throws IOException{
		if(event.getSource() == backBtn){
			Parent gameUIRoot = FXMLLoader.load(getClass().getResource("MainUILayout.fxml"));
			Scene gameUIScene = new Scene(gameUIRoot, MainApplication.WIDTH, MainApplication.HEIGHT);
			Stage stage = (Stage) backBtn.getScene().getWindow();
			stage.setScene(gameUIScene);
			stage.show();
		}
	}
}

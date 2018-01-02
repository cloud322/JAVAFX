package jm.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class FXmain2 extends Application {

	@Override
	public void start(Stage pStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/jm/view/FXmain2.fxml"));
		
		pStage.setTitle("MVC 를 이용한 JAVA FX");
		pStage.getIcons().add(new Image("/jm/icons/layers-icon.png"));
		pStage.setScene(new Scene(root,640,480));
		
		pStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

package jm.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class FXmain4Controller {
	
	@FXML BorderPane mainPane;
	
	public void exitapp(ActionEvent ae) {}
	public void openabout(ActionEvent ae) throws IOException 
	{
		String fpath = "/jm/view/FXmain4About.fxml";
		
		Parent root = FXMLLoader.load(getClass().getResource(fpath));
		
		Stage about = new Stage();
		about.setScene(new Scene(root));
		about.setTitle("End program");
		
		//프로그램 정보창을 모달 창으로 띄우기 위해
		//현재 표시된 메인 윈도우 객체를 가저옴
		Stage main =(Stage)mainPane.getScene().getWindow();
		about.initModality(Modality.WINDOW_MODAL);
		about.initOwner(main);
		
		
		
		about.show();
	}
	
	
	
	

}

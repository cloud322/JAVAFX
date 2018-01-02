package jm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FXmain3Controller {
	
	//FXML 에 정의된 컨트롤을 controller 에서 제어하기 위해 맴버변수로 정의
	@FXML TextField uid;
	@FXML PasswordField pwd;
	@FXML Label loginyes;
	@FXML Label loginno;
	
	public void go2login(ActionEvent ae)
	{
		String isLogin = "Login Failed";
		if(uid.getText().equals("cloud") &&  pwd.getText().equals("123456")  )
		{
			System.out.println("logged in");
			isLogin = "Logged in";
			loginyes.setVisible(true);
		}
		else
		{
			System.out.println("failed to log in");
			loginno.setVisible(true);
		}
		//대화상자출력
		Alert dialog = new Alert(Alert.AlertType.WARNING);
		dialog.setTitle(isLogin);
		dialog.setHeaderText(isLogin);
		dialog.setContentText(isLogin);
		//대화상자 출력
		dialog.showAndWait();
			
	}
	
	public void go2reset(ActionEvent ae)
	{
		//입력상자 모든 내용을 지움
		uid.setText("");
		pwd.setText("");
		loginyes.setVisible(false);
		loginno.setVisible(false);
	}

}

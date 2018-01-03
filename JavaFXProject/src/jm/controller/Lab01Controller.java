package jm.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import jm.model.SungJukModel;

public class Lab01Controller implements Initializable {
	//각종 컨트롤이 화면이 표시되기 전에 미리 수정되어야 할 작업이 있다면 
	//Initializable 인터페이스를 구현하고 initialize 라는 메서드 재정의하면된다
	
	//view 에 정이된 컨트롤에 대한 맴버변수 선언
	@FXML private TextField name;
	@FXML private TextField kor;
	@FXML private TextField eng;
	@FXML private TextField mat;
	
	//table View 와 data table 관련 맴버변수
	@FXML private TableView<SungJukModel> sjtable;
	@FXML private TableColumn<SungJukModel, String> sjname;
	@FXML private TableColumn<SungJukModel, String> sjkor;
	@FXML private TableColumn<SungJukModel, String> sjeng;
	@FXML private TableColumn<SungJukModel, String> sjmat;
	@FXML private TableColumn<SungJukModel, String> sjtot;
	@FXML private TableColumn<SungJukModel, String> sjavg;
	@FXML private TableColumn<SungJukModel, String> sjgrd;
	
	//컨트롤러에서 기타 목적으로 선언하는 변수
	private ObservableList<SungJukModel> sjlist = null;
	//observer 패턴이 적용된 list 클래스
	//observer 패턴 : 한 객체의 상태가 바뀌면 그객체에 의존하는 다른 객체들에게 그정보를 알려주게
	//작성하는 방식 
	//하나의 주체를 여러 개체가 구독하는 1:다 형태
	
	private static int sjcnt = 0;
	//전체 학생수 저장용 변수
	
	//추가 버튼클릭시 이벤트처리 메서드
	public void addSungjuk(ActionEvent ae) 
	{
		try{
		SungJukModel sj = new SungJukModel(name.getText(), kor.getText(), eng.getText(), mat.getText());
		sjlist.add(sj);
		
		name.setText("");
		kor.setText("");
		eng.setText("");
		mat.setText("");
		}catch(Exception ex) {showErrorDialog(1);}
	}

	//수정 버튼클릭시 이벤트처리 메서드
	public void modifySungjuk(ActionEvent ae) 
	{
		int num = sjtable.getSelectionModel().getSelectedIndex();
		//수정할 행의 순번index를 알아냄
		SungJukModel sj = new SungJukModel(name.getText(), kor.getText(), eng.getText(), mat.getText());
		//수정된 갑으로 성적 객체를 생성함
		sjlist.set(num, sj);
		//수정된 객체를 지정한 위치에 저장
		
//		sjtable.getSelectionModel().select(num);
//		//특정행을 선택상태로 만듬
		name.setText("");
		kor.setText("");
		eng.setText("");
		mat.setText("");
		
		
	}
	
	//삭제 버튼클릭시 이벤트처리 메서드
	public void removeSungjuk(ActionEvent ae) 
	{
		int num = sjtable.getSelectionModel().getSelectedIndex();
		//삭제한 번호를 알아내서
		sjlist.remove(num);
	}
	

	//마우스 버튼 double클릭시 이벤트처리 메서드
	public void clickSungjuk(MouseEvent me) 
	{
		//마우스 버튼을 2번 클릭하면
		if ( me.getClickCount()==2)
		{
			//현재 선택한 행외 데이터를 성적모델 객체로 읽어옴
			SungJukModel sj = (SungJukModel)sjtable.getSelectionModel().getSelectedItem();
			//선택한 모델 객체로 부터 성적데이터를 추출해서 텍스트필드에 저장
			name.setText(sj.getName());
			kor.setText(sj.getKor());
			eng.setText(sj.getEng());
			mat.setText(sj.getMat());
		
		}
	}
	
	//시스템 오동작으로 인한 오류 처리 대화상자
	private void showErrorDialog(int etype)
	{
		String error ="";
		String errorText = "";
		switch(etype)
		{
		case 1: error="성적추가오류";
		errorText = "성적추가시 오류발생 - 입력데이터 확인";
				break;
		}
		Alert dialog = new Alert(Alert.AlertType.ERROR);
		dialog.setTitle(error);
		dialog.setHeaderText(error);
		dialog.setContentText(errorText);
		dialog.showAndWait();
		
		
	}
	
	

	//Lab01.fxml 이 읽혀지고 load 한후 각종 UI 요소에대한 초기화 작업을 수행
	//또는 기타 초기화 작업 (db파일 연결 종료 등) 수행
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Javafx 빈즈 규약에 따라 작성된 sungjuk 모델과
		//table view 의 각 컬럼과 바인딩 시키는 작업 필요
		//즉 해당 컬럼에 표시될 데이터 종류를 결정
		sjname.setCellValueFactory(new PropertyValueFactory<SungJukModel, String>("name"));
		sjkor.setCellValueFactory(new PropertyValueFactory<SungJukModel, String>("kor"));
		sjeng.setCellValueFactory(new PropertyValueFactory<SungJukModel, String>("eng"));
		sjmat.setCellValueFactory(new PropertyValueFactory<SungJukModel, String>("mat"));
		sjtot.setCellValueFactory(new PropertyValueFactory<SungJukModel, String>("tot"));
		sjavg.setCellValueFactory(new PropertyValueFactory<SungJukModel, String>("avg"));
		sjgrd.setCellValueFactory(new PropertyValueFactory<SungJukModel, String>("grd"));
		
		sjlist = FXCollections.observableArrayList();
		//자바에프엑스에서 지원한는 collection 즁
		//옵저버 패턴이 적용된 arraylist
		
		//observableArrayList 에 성적 데이터 추가
		SungJukModel sj = new SungJukModel("지현", 99+"", 99+"", 99+"");
		sjlist.add(sj);
		
		sj = new SungJukModel("수지", 98+"", 98+"", 98+"");
		sjlist.add(sj);
		
		sj = new SungJukModel("혜교", 99+"", 99+"", 99+"");
		sjlist.add(sj);
		
		//테이블뷰에 생성된 성적데이터 객체를 지정
		sjtable.setItems(sjlist);
		
		sjcnt = sjlist.size();
		
		
	}
}

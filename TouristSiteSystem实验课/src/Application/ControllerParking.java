package Application;

import java.io.IOException;

import Function.DemoParking;
import Function.Manager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerParking {
	private Stage stage;
	static DemoParking p = new DemoParking();
	

    @FXML
    private Button inButton;
    
    @FXML
    private Button outButton;
    
    @FXML
    private Button AllButton;
    
    @FXML
    private Button caroutButton;
    
    @FXML
    private Button carInButton;
    
    @FXML
    private Button carsButton;
    
    @FXML
    private Button backButton;
    
    @FXML
    private Button back1Button;
    
    @FXML
    private Label showcar;
    
    @FXML
    private Label show;
    
    @FXML
    private Label showall;
    
    @FXML
    private TextField carNum;
    
    @FXML
    private TextField numCar;
    
    //���ؽ���ͣ��������
    void loadNewPage1() throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("ͣ��������.fxml"));
    	Parent root = (Parent)loader.load();
    	ControllerParking controller = loader.getController();
    	controller.setStage(stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    	
    }
   
   //�������г�λ�Ľ���
    void loadNewPage3() throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("ͣ������λ.fxml"));
    	Parent root = (Parent)loader.load();
    	ControllerParking controller = loader.getController();
    	controller.setStage(stage);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    	controller.setStage(stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
    }
    //���ص�����Ա����
    void loadNewPage4() throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("����Ա.fxml"));
    	Parent root = (Parent)loader.load();
    	ControllerMangerLoad controller = loader.getController();
    	controller.setStage(stage);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    	controller.setStage(stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
    }
    //���ص�ͣ������������
    void loadNewPage5() throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("ͣ��������.fxml"));
    	Parent root = (Parent)loader.load();
    	ControllerParking controller = loader.getController();
    	controller.setStage(stage);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    	controller.setStage(stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
    }
    
  //�����뿪ͣ��������
    void loadNewPage2() throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("ͣ�����뿪.fxml"));
    	Parent root = (Parent)loader.load();
    	ControllerParking controller = loader.getController();
    	controller.setStage(stage);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    	controller.setStage(stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
    }
    @FXML
    void loadIn(ActionEvent event)throws IOException{
    	loadNewPage1();
    }
    
    @FXML
    void loadOut(ActionEvent event) throws IOException{
    	loadNewPage2();
    }
    
    @FXML
    void loadCar(ActionEvent event)throws IOException{
    	loadNewPage3();
    }
    
    @FXML
    void backMenu(ActionEvent event)throws IOException{
    	loadNewPage4();
    }
    
    @FXML
    void back(ActionEvent event)throws IOException{
    	loadNewPage5();
    }
    //��ʾ����ͣ��������
    @FXML
    void in(ActionEvent event)throws IOException{
    	String num = carNum.getText();
    	showcar.setText(p.in(num));
    	
    	
    }
    //��ʾ�뿪ͣ��������
    @FXML
    void out(ActionEvent event)throws IOException{
    	String num = numCar.getText();
    	show.setText(p.out(num));
    	
    }
    
    //��ʾ���г���Ϣ
    @FXML
    void showAll(ActionEvent event)throws IOException{
    	
    	showall.setText(p.Allprint());
    	System.out.println(p.Allprint());
    	
    }
    
    
    public Stage getStage() {
	    return stage;
    }

    public void setStage(Stage stage) {
	    this.stage = stage;
    }
    
    

}

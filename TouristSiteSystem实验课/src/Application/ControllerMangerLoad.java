package Application;

import java.io.IOException;

import javax.swing.JOptionPane;

import Function.DemoParking;
import Function.Manager;
import Function.ParkingStack;
import Function.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerMangerLoad {

	 Manager m = new Manager();
	 User user = new User(m.edge,m.vexs);
	 DemoParking p ;
	
	
    private Stage stage;
    
    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;
    
    @FXML
    private Button parkingButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField username;

    @FXML
    private Label warning;
    
    @FXML
    private Button editspotButton;
    
    @FXML
    private Button editedgeButton;
    @FXML
    private Button inforButton;
    
    @FXML
    private Button backButton1;
    
    //管理员登录
    @FXML
    void login(ActionEvent event) throws IOException{
    	String userName = username.getText();
    	String passWord = password.getText();
    	if(userName.equals("1234")){
    		if(passWord.equals("1234")){
    			loadNewPage();
    		} else {
    			JOptionPane.showMessageDialog(null, "the password is worng");
    		} 
    	} else{
    		JOptionPane.showMessageDialog(null, "the username is worng");
    	}	
    }
    
    @FXML
    void loadNewPage() throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("管理员.fxml"));
    	Parent root = (Parent)loader.load();
    	ControllerMangerLoad controller = loader.getController();
    	controller.setStage(stage);
    	controller.load(m, user,stage);;;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    	controller.setStage(stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
    }
    
    //返回主菜单界面
    @FXML
    void backMenu(ActionEvent event) throws IOException
    {
    	loadNewPage1();

    }
    
    //返回编辑边的界面
    @FXML
    void loadEdge(ActionEvent event) throws IOException
    {
    	loadNewPage2();

    }
    
    //返回编辑景点的界面
    @FXML
    void loadspot(ActionEvent event) throws IOException
    {
    	loadNewPage3();

    }
    
    //返回显示消息界面
    @FXML
    void loadInfor(ActionEvent event) throws IOException
    {
    	loadNewPage4();

    }
    
   //返回停车场界面
    @FXML
    void loadpark(ActionEvent event) throws IOException
    {
    	loadNewPage5();

    }
    
    void loadNewPage1() throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("界面1.fxml"));
    	Parent root = (Parent)loader.load();
    	ControllerMainMenu controller = loader.getController();
    	controller.setStage(stage);
    	controller.load(m,user, stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    	
    }
    
    void loadNewPage2() throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("编辑边.fxml"));
    	Parent root = (Parent)loader.load();
    	ControllerManger controller = loader.getController();
    	controller.setStage(stage);
    	controller.load(m,user, stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
    }
    
    
    void loadNewPage4() throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("发布信息.fxml"));
    	Parent root = (Parent)loader.load();
    	ControllerManger controller = loader.getController();
    	controller.setStage(stage); 
    	controller.load(m,user, stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
    }
   
    void loadNewPage5() throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("停车场管理.fxml"));
    	Parent root = (Parent)loader.load();
    	ControllerParking controller = loader.getController();
    	controller.setStage(stage);
    	//controller.load(p, stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    	
    }
    
    void loadNewPage3() throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("编辑景点.fxml"));
    	Parent root = (Parent)loader.load();
    	ControllerManger controller = loader.getController();
    	controller.setStage(stage);
    	controller.load(m,user, stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	
    }
    
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    

    public void setManager(Manager m){
    	this.m = m;
    }
    
    public Manager getManager(){
    	return m;
    }
    
    //实现同步变化的功能
     void load(Manager m, User user,Stage stage){
    	 this.m = m;
    	 this.user = user;
    	 this.stage = stage;
     }
    
    
    

}

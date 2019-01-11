package Application;

import java.io.IOException;

import javax.swing.JOptionPane;

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
import Function.Manager;
import Function.User;
public class ControllerManger {

	private Manager m;
	private User user;
	
    private Stage stage;
    
    @FXML
    private Button addEdgeButton;

    @FXML
    private Button removeEdgeButton;
    
    @FXML
    private Button addVertexButton;

    @FXML
    private Button removeVertexButton;

    @FXML
    private TextField startVertex;
    
    @FXML
    private TextField endVertex;
    
    @FXML
    private TextField weight;
    
    @FXML
    private TextField name;
    
    @FXML
    private TextField infor;
    
    @FXML
    private TextField rest;
    
    @FXML
    private TextField popular;
    
    @FXML
    private TextField toliet;
    
    @FXML
    private TextField infors;

    @FXML
    private Label remind;
    
    
    @FXML
    private Button backButton;
    
   
    //加载管理员登录界面
    @FXML
    void backMenu(ActionEvent event) throws IOException
    {
    	loadNewPage1();

    }
    
    public void loadNewPage1() throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("管理员.fxml"));
    	Parent root = (Parent)loader.load();
    	ControllerMangerLoad controller = loader.getController();
    	controller.setStage(stage);
    	controller.load(m, user,stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    	
    }
    
    //实现添加边
    @FXML
    void addEdge(ActionEvent event) {
    	String start = startVertex.getText();
    	String end = endVertex.getText();
    	int Weight = Integer.parseInt(weight.getText());
    	m.addEdge(start,end,Weight);
    	System.out.println(user.outputGraph());
    	System.out.println("333");
    	
    }
    
    //实现删除边
    @FXML
    void removeEdge(ActionEvent event) {
    	String start = startVertex.getText();
    	String end = endVertex.getText();
    	m.removeEdge(start,end);
    	System.out.println(user.outputGraph());
    }
    
    //实现添加景点
    @FXML
    void addVertex(ActionEvent event){
    	String vName = name.getText();
    	String vInfor = infor.getText();
    	String vRest = rest.getText();
    	String vToliet = toliet.getText();
    	double vPopular = Double.parseDouble(popular.getText());
    	m.addVertex(vName, vInfor, vPopular, vRest, vToliet);
    	System.out.println(user.outputGraph());
    	
    }
    
    //实现删除景点
    @FXML
    void removeVertex(ActionEvent event){
    	String vName = name.getText();
    	m.removeVertex(vName);
    	System.out.println(user.outputGraph());
    }
    
    //实现得到查询信息
    @FXML
    void getInfors(ActionEvent event) throws IOException{
    	String vInfors = infors.getText();
    	m.setInfors(vInfors);
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
    
    //实现同步界面变化
     void load(Manager m, User user,Stage stage){
    	 this.m = m;
    	 this.user = user;
    	 this.stage = stage;
     }
    
}

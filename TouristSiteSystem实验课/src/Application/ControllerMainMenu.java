package Application;

import java.io.IOException;

import Function.Manager;
import Function.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControllerMainMenu {
	 Manager m = new Manager();
	 User user = new User(m.edge,m.vexs) ;
	private Stage stage;
	
	@FXML
    private Button mangerButton;
	
	@FXML
    private Button circleRouteButton;
	
	@FXML
    private Button searchInforButton;
	
	@FXML
    private Button spotSortButton;
	
	@FXML
    private Button shortestRouteButton;
	
	@FXML
    private Button createGraphButton;
	
	@FXML
    private Label notification;
	
	//加载登录界面
	@FXML
	void getManger(ActionEvent event)throws IOException{
		loadNewPage1();
	}
	
	//加载导游路线图的界面
	@FXML
	void getcircleRoute(ActionEvent event)throws IOException{
		loadNewPage2();
	}
	
	//加载查询信息的界面
	@FXML
	void searchInfor(ActionEvent event)throws IOException{
		loadNewPage3();
	}
	
	//加载景点排序的界面
	@FXML
	void spotSort(ActionEvent event)throws IOException{
		loadNewPage4();
	}
	
	//加载最小路径界面
	@FXML
	void shortestRoute(ActionEvent event)throws IOException{
		loadNewPage5();
	}
	
	//加载创建图的界面
	@FXML
	void createGraph(ActionEvent event)throws IOException{
		loadNewPage6();
	}
	
	@FXML
	void loadNewPage1() throws IOException
    {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("登录.fxml"));
    	Parent root = (Parent)loader.load();
    	ControllerMangerLoad controller = loader.getController();
    	controller.setStage(stage);
    	controller.load(m, user,stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    	
    }
	
	@FXML
	void loadNewPage2() throws IOException
    {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("导游回路1.fxml"));
    	Parent root = (Parent)loader.load();
    	ControllerFuction controller = loader.getController();
    	controller.setStage(stage);
    	controller.load(m,user, stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    	
    }
	
	@FXML
	void loadNewPage3() throws IOException
    {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("景点查询.fxml"));
    	Parent root = (Parent)loader.load();
    	ControllerFuction controller = loader.getController();
    	controller.setStage(stage);
    	controller.load(m,user,stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    	
    }
	
	@FXML
	void loadNewPage4() throws IOException
    {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("景点排序.fxml"));
    	Parent root = (Parent)loader.load();
    	ControllerFuction controller = loader.getController();
    	controller.setStage(stage);
    	controller.load(m,user,stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    	
    }
	
	@FXML
	void loadNewPage5() throws IOException
    {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("最短路径.fxml"));
    	Parent root = (Parent)loader.load();
    	ControllerFuction controller = loader.getController();
    	controller.setStage(stage);
    	controller.load(m,user,stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    	
    }
	

	@FXML
	void loadNewPage6() throws IOException
    {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("景区景点分布图.fxml"));
    	Parent root = (Parent)loader.load();
    	ControllerFuction controller = loader.getController();
    	controller.setStage(stage);
    	controller.load(m, user,stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    	
    }
	
	//加载显示信息的界面
	@FXML
	void showN() throws IOException{
		notification.setText(m.getInfors());
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
    
    //加载类使其变化同步
     void load(Manager m,User user,Stage stage){
    	 this.m = m;
    	 this.user = user;
    	 this.stage = stage;
     }
}

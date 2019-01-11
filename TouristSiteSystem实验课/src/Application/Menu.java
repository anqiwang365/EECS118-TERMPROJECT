package Application;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import Function.Manager;
import Function.User;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Parent;

public class Menu  extends Application 
{
	Manager m = new Manager();
	User user;
	@Override
	public void start(Stage stage) throws NullPointerException,IOException,InvocationTargetException,RuntimeException{
		try {
			//读取文件
			m.initManager();	
			user = new User(m.edge,m.vexs);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("界面1.fxml"));
			Parent root = (Parent)loader.load();
			ControllerMainMenu controller = (ControllerMainMenu)loader.getController();
			controller.setStage(stage);
			controller.load(m, user, stage);
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("景区管理系统");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}

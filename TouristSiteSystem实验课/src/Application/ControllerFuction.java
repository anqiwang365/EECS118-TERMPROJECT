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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerFuction {

	 Manager m ;
	 User user ;
	
	

	private Stage stage;
	
	@FXML
    private Button routeButton;

    @FXML
    private Button backEdgeButton;
    
    @FXML
    private Button showGraphButton;

    @FXML
    private TextField name;
    
    @FXML
    private TextField startvertex;
    
    @FXML
    private TextField endvertex;
    
    @FXML
    private TextField keyword;
    
	@FXML
    private Label showcircleroute;
	
	@FXML
    private Label graph;
	
	@FXML
    private Label bestpath;
	
	@FXML
    private Label showSpot;
	
	@FXML
    private Label showresult;
	
	
	 @FXML
	 private Button backButton;
	 
	 @FXML
	 private Button searchspotButton;
	 
	 @FXML
	 private Button bestpathButton;
	 
	 //�������·��ͼ
	@FXML
	void circleRoute(ActionEvent event){
		
		showcircleroute.setText(user.getHamiltonCircuit());
    	
	}
	
	//�������˵�
	@FXML
	void backMenu(ActionEvent event) throws IOException
    {
    	loadNewPage1();

    }
    
	//���ط���������
    public void loadNewPage1() throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("����1.fxml"));
    	Parent root = (Parent)loader.load();
    	ControllerMainMenu controller = loader.getController();
    	controller.setStage(stage);
    	controller.load(m, user,stage);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    	
    }
    
    //�����������ķֲ�ͼ
    @FXML
     void showGraph(ActionEvent event) throws IOException{
   
    	user = new User(m.edge,m.vexs);
    	graph.setText(user.outputGraph());
    	System.out.println(user.outputGraph());

    }
	
    //������������㣬�յ㣬������·��
    @FXML
	void showBestPath(ActionEvent event){
    	
    	String start = startvertex.getText();
		String end = endvertex.getText();
		bestpath.setText(user.dijkstra(start, end));
		System.out.println(user.dijkstra(start, end));
	}
	
    
    //�����ѯ�������ϸ��Ϣ
    @FXML
    void showSearchSpot(ActionEvent event) {
    	String vKeyword = keyword.getText();
    	showSpot.setText(user.search(vKeyword));
    	System.out.println(user.search(vKeyword));
    }
	
    //��������������
    @FXML
    void showPopular(ActionEvent event) {
    	showresult.setText(user.sortPopular());
    	System.out.println(user.sortPopular());
    }
    
    //�������Ĳ�·������
    @FXML
    void showDegree(ActionEvent event){
    	showresult.setText(user.sortDegree());
    	System.out.println(user.sortDegree());
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
    
    //���µļ���ԭ����Manger,User,Stage,ʹ�������еĽ���仯ʱͬ��
     void load(Manager m,User user,Stage stage){
    	 this.m = m;
    	 this.user = user;
    	 this.stage = stage;
     }
     

 	public User getUser() {
 		return user;
 	}
 	public void setUser(User user) {
 		this.user = user;
 	}

}

package Function;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Manager {

	public static Vertex[] vexs;//存储景点的数组
	public static Edge[] edge;//存储边的数组
	static int vNum;
	static int eNum;
	static int firstfree=0; //代表景点数组的大小
	static int firstedge=0; //代表边的数组的大小
	static User u = new User();
	public Manager(){
		
	}
	
	/*
	 * 初始化从文件中读取景点，边的信息按块存入景点，边的属性调用add方法
	 */
	public void initManager(){
		vexs=new Vertex[80];//初始化数组
		edge=new Edge[80];//初始化数组
		File file1 = new File("1.txt");//读取文件
		try {
			BufferedReader in = new BufferedReader(new FileReader(file1));
		    String line = null;
			line = in.readLine();
		    while(line!=null){  //添加文件信息到景点的数组中
		    	String[] infors=line.split(",");
		    	addVertex(infors[0],infors[1],Double.parseDouble(infors[2]),infors[3],infors[4]);
		    	line = in.readLine();	
		    }
				in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File file2 = new File("2.txt"); //读取文件
		try {
			BufferedReader in = new BufferedReader(new FileReader(file2));
		    String line = null;
			line = in.readLine();
		    while(line!=null){
		    	String[] infor=line.split(","); //添加文件信息到边的数组中
		    	addEdge(infor[0],infor[1],Integer.parseInt(infor[2]));
		    	line = in.readLine();
		    }
				in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	  /*
	   * 管理员添加节点，输入包括节点的信息
	   */
	  public  void addVertex(String name,String infor,double popular,String rest,String toilet){
		
		  vexs[firstfree++]=new Vertex(name,infor,popular,rest,toilet);
		  u = new User(edge, vexs);
	  }
	  
	  /*
	   * 管理员添加边的信息
	   */
	  public  void addEdge(String start,String end,int weight){
		 
		  edge[firstedge++] = new Edge(start,end,weight);
		  u = new User(edge, vexs);
	  }
	
	 
	  /*
	   * 管理员删除景点******,删除该景点相连的所有边,以及该景点
	   */
	  public void removeVertex(String Vname){
		
		  //遍历删除该景点所连得边
		  for(int j=0;j<firstedge;j++){
			  if(edge[j].getFromVertex().equals(Vname)||edge[j].getToVertex().equals(Vname)){
				 
				  if(j==firstedge-1){
					  edge[j]=null;
				  } else{
					  edge[j]=edge[firstedge-1];
					  edge[firstedge-1]=null;
					  firstedge--;
					//删全边
					  int temp = j;
					  j=temp-1;
				  }
			  }
		  }
		  
		  //删除该景点
		  for(int i=0;i<firstfree;i++){
			  if(vexs[i].getName().equals(Vname)){
				  vexs[i] = vexs[firstfree-1];
				  vexs[firstfree-1]=null;
				  firstfree--;
			  }
		  }
		  u =new User(edge, vexs);
		  
	  }
	  
	  /*
	   * 管理员删除边
	   */
	  public void removeEdge(String startName,String endName){
	    	 
	    	 for(int i=0;i<firstedge;i++){
	    		 if((edge[i].getFromVertex().equals(startName)&&edge[i].getToVertex().equals(endName))||
	    				 edge[i].getFromVertex().equals(endName)&&edge[i].getToVertex().equals(startName)){	 
	    			 edge[i]=edge[firstedge-1];
	    			 edge[firstedge-1]=null;
	    			 firstedge--;
	    		 }
	    	 }
	    	 u= new User(edge, vexs);
	     }
	  
	  /*
	   * 管理员发布消息写入文件
	   */
	  public void setInfors(String infor) throws IOException{
		  File writename = new File("infor.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
		  //writename.createNewFile(); // 创建新文件
		  BufferedWriter out = new BufferedWriter(new FileWriter(writename));
		  out.write(infor); // 
		  out.flush(); // 把缓存区内容压入文件
		  out.close(); // 最后记得关闭文件
	  }
	  
	  /*
	   * 管理员读取信息，从文件中读取
	   */
	  public String getInfors() throws IOException{
		  File file1 = new File("infor.txt");
		  String line = null;
		  try {
				BufferedReader in = new BufferedReader(new FileReader(file1));
				line = in.readLine();
				in.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return line;
	  }

	
}


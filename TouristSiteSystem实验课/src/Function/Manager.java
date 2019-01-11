package Function;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Manager {

	public static Vertex[] vexs;//�洢���������
	public static Edge[] edge;//�洢�ߵ�����
	static int vNum;
	static int eNum;
	static int firstfree=0; //����������Ĵ�С
	static int firstedge=0; //����ߵ�����Ĵ�С
	static User u = new User();
	public Manager(){
		
	}
	
	/*
	 * ��ʼ�����ļ��ж�ȡ���㣬�ߵ���Ϣ������뾰�㣬�ߵ����Ե���add����
	 */
	public void initManager(){
		vexs=new Vertex[80];//��ʼ������
		edge=new Edge[80];//��ʼ������
		File file1 = new File("1.txt");//��ȡ�ļ�
		try {
			BufferedReader in = new BufferedReader(new FileReader(file1));
		    String line = null;
			line = in.readLine();
		    while(line!=null){  //����ļ���Ϣ�������������
		    	String[] infors=line.split(",");
		    	addVertex(infors[0],infors[1],Double.parseDouble(infors[2]),infors[3],infors[4]);
		    	line = in.readLine();	
		    }
				in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File file2 = new File("2.txt"); //��ȡ�ļ�
		try {
			BufferedReader in = new BufferedReader(new FileReader(file2));
		    String line = null;
			line = in.readLine();
		    while(line!=null){
		    	String[] infor=line.split(","); //����ļ���Ϣ���ߵ�������
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
	   * ����Ա��ӽڵ㣬��������ڵ����Ϣ
	   */
	  public  void addVertex(String name,String infor,double popular,String rest,String toilet){
		
		  vexs[firstfree++]=new Vertex(name,infor,popular,rest,toilet);
		  u = new User(edge, vexs);
	  }
	  
	  /*
	   * ����Ա��ӱߵ���Ϣ
	   */
	  public  void addEdge(String start,String end,int weight){
		 
		  edge[firstedge++] = new Edge(start,end,weight);
		  u = new User(edge, vexs);
	  }
	
	 
	  /*
	   * ����Աɾ������******,ɾ���þ������������б�,�Լ��þ���
	   */
	  public void removeVertex(String Vname){
		
		  //����ɾ���þ��������ñ�
		  for(int j=0;j<firstedge;j++){
			  if(edge[j].getFromVertex().equals(Vname)||edge[j].getToVertex().equals(Vname)){
				 
				  if(j==firstedge-1){
					  edge[j]=null;
				  } else{
					  edge[j]=edge[firstedge-1];
					  edge[firstedge-1]=null;
					  firstedge--;
					//ɾȫ��
					  int temp = j;
					  j=temp-1;
				  }
			  }
		  }
		  
		  //ɾ���þ���
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
	   * ����Աɾ����
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
	   * ����Ա������Ϣд���ļ�
	   */
	  public void setInfors(String infor) throws IOException{
		  File writename = new File("infor.txt"); // ���·�������û����Ҫ����һ���µ�output��txt�ļ�
		  //writename.createNewFile(); // �������ļ�
		  BufferedWriter out = new BufferedWriter(new FileWriter(writename));
		  out.write(infor); // 
		  out.flush(); // �ѻ���������ѹ���ļ�
		  out.close(); // ���ǵùر��ļ�
	  }
	  
	  /*
	   * ����Ա��ȡ��Ϣ�����ļ��ж�ȡ
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


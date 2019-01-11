package Function;
import java.util.ArrayList;
import java.util.Iterator;

public class Vertex {

	private String name; //景点的名字
	private String infor; //景点的信息
	private double popular; //景点的受欢迎程度
	private String rest; //是否有休息区
	private String toilet; //是否有厕所
	Edge firstEdge = new Edge(); //邻接链表连接的第一条边
	 
	 
	public Vertex(String name, String infor, double popular, String rest, String toilet) {
		
		this.name = name;
		this.infor = infor;
		this.popular = popular;
		this.rest = rest;
		this.toilet = toilet;
	}

	public Vertex(){}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getInfor() {
		return infor;
	}
	
	public void setInfor(String infor) {
		this.infor = infor;
	}
	
	public double getPopular() {
		return popular;
	}
	
	public void setPopular(double popular) {
		this.popular = popular;
	}
	
	public String getRest() {
		return rest;
	}

	public void setRest(String rest) {
		this.rest = rest;
	}

	public String getToilet() {
		return toilet;
	}

	public void setToilet(String toilet) {
		this.toilet = toilet;
	}

	public Edge getFirstEdge() {
		return firstEdge;
	}

	public void setFirstEdge(Edge firstEdge) {
		this.firstEdge = firstEdge;
	}

	
}

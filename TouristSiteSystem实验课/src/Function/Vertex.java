package Function;
import java.util.ArrayList;
import java.util.Iterator;

public class Vertex {

	private String name; //���������
	private String infor; //�������Ϣ
	private double popular; //������ܻ�ӭ�̶�
	private String rest; //�Ƿ�����Ϣ��
	private String toilet; //�Ƿ��в���
	Edge firstEdge = new Edge(); //�ڽ��������ӵĵ�һ����
	 
	 
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

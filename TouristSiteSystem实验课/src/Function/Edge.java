package Function;



public class Edge {
	private int weight; // �ߵ�Ȩ��
	private String fromVertex; //�ߵ���ʼ��
	private String toVertex; //�ߵ���ֹ��
	int vertex; //����߽ڵ���±�
	Edge nextEdge; // ������һ����
	
	
	public Edge( String fromVertex, String toVertex, int weight) {
		super();
		this.weight = weight;
		this.fromVertex = fromVertex;
		this.toVertex = toVertex;
	}
	
	public Edge(){}
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getFromVertex() {
		return fromVertex;
	}
	public void setFromVertex(String fromVertex) {
		this.fromVertex = fromVertex;
	}
	public String getToVertex() {
		return toVertex;
	}
	public void setToVertex(String toVertex) {
		this.toVertex = toVertex;
	}
	public Edge getNextEdge() {
		return nextEdge;
	}
	public void setNextEdge(Edge nextEdge) {
		this.nextEdge = nextEdge;
	}

	
}


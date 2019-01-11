package Function;



public class Edge {
	private int weight; // 边的权重
	private String fromVertex; //边的起始点
	private String toVertex; //边的终止点
	int vertex; //代表边节点的下标
	Edge nextEdge; // 代表下一条边
	
	
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


package Function;

import java.io.IOException;

public class test {
//	static Manager m = new Manager();
//	
//	static User u = new User(m.edge,m.vexs);
	public static void main(String[] args) throws IOException{
		Manager m = new Manager();
		
		m.initManager();
		User u = new User(m.edge,m.vexs);
//		System.out.println(u.dijkstra("����", "����̨"));
//		System.out.println(u.outputGraph());
//		System.out.println(u.sortDegree());
//		System.out.println(u.search("����"));
		u.getHamiltonCircuit();
//		
//		u.minTree();
//		System.out.println("------------");
//		m.addVertex("̩ɽ", "��ɽ", 80, "true", "true");
//		m.addEdge("̩ɽ", "����", 20);
//		u = new User(Manager.edge,Manager.vexs);
//		System.out.println(u.outputGraph());
//		System.out.println(u.sortDegree());
//		m.removeVertex("����");
//		m.removeVertex("����̨");
		m.removeEdge("��ˮͤ", "�����");
		u = new User(Manager.edge,Manager.vexs);
//		
//		System.out.println(u.outputGraph());
		u.getHamiltonCircuit();
		System.out.println("complete");
//		System.out.println("99");
	}
}

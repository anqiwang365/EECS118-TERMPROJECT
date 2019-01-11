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
//		System.out.println(u.dijkstra("北门", "观云台"));
//		System.out.println(u.outputGraph());
//		System.out.println(u.sortDegree());
//		System.out.println(u.search("北门"));
		u.getHamiltonCircuit();
//		
//		u.minTree();
//		System.out.println("------------");
//		m.addVertex("泰山", "大山", 80, "true", "true");
//		m.addEdge("泰山", "北门", 20);
//		u = new User(Manager.edge,Manager.vexs);
//		System.out.println(u.outputGraph());
//		System.out.println(u.sortDegree());
//		m.removeVertex("北门");
//		m.removeVertex("观云台");
		m.removeEdge("碧水亭", "仙武湖");
		u = new User(Manager.edge,Manager.vexs);
//		
//		System.out.println(u.outputGraph());
		u.getHamiltonCircuit();
		System.out.println("complete");
//		System.out.println("99");
	}
}

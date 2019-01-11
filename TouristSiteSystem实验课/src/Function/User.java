   package Function;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class User {

	private int vNum;// vList数组的大小
	private int eNum;// edges数组的大小
	private Vertex[] vList;// 保存邻接表的头结点即所有的顶点的集合
	private Edge[] eList; // 保存所有的边
	private int[][] edges;// 邻接矩阵用于保存边
	private static final int maxWeight = 32767; // 代表两个点之间没有路
	StringBuilder circleRoute = new StringBuilder(); // 用于输出导游路线图
	int[] temp_path = null;// 用于哈密尔顿回路算法保存step1-step12还没形成回路的路
	boolean finish = false;// 用于哈密尔顿回路算法判断是否结束

	public User() {
		vList = new Vertex[80];
		eList = new Edge[80];
	}

	// 初始化顶点和边的数组
	public User(Edge[] E, Vertex[] V) {
		// 得到有关顶点总数和边的总数
		vNum = Manager.firstfree;
		eNum = Manager.firstedge;
		vList = new Vertex[vNum];
		eList = new Edge[eNum];
		// 初始化邻接矩阵
		// 建立邻接矩阵，初始化邻接矩阵
		edges = new int[vNum][vNum];
		for (int m = 0; m < vNum; m++) {
			for (int n = 0; n < vNum; n++) {
				if (n == m) {
					edges[n][m] = 0;
				} else {
					edges[n][m] = maxWeight;
				}
			}

			// 得到有关顶点信息,初始化
			for (int i = 0; i < vNum; i++) {
				vList[i] = new Vertex();
				vList[i].setName(V[i].getName());
				vList[i].setInfor(V[i].getInfor());
				vList[i].setPopular(V[i].getPopular());
				vList[i].setRest(V[i].getRest());
				vList[i].setToilet(V[i].getToilet());
				vList[i].setFirstEdge(null);
			}

			// 得到有关边的信息
			for (int i = 0; i < eNum; i++) {
				String str1 = E[i].getFromVertex();// Str1 代表此边的起始点的名字
				String str2 = E[i].getToVertex();// str2代表此边的终点的名字
				int v1 = getPosition(str1);// 找到起始点在邻接表头节点的数组中所在的位置，下标表示
				int v2 = getPosition(str2);// 找到终点在邻接表头节点的数组中所在的位置，下标表示
				// 初始化eList[]
				eList[i] = new Edge();
				eList[i].setFromVertex(E[i].getFromVertex());
				eList[i].setToVertex(E[i].getToVertex());
				eList[i].setWeight(E[i].getWeight());
			
				// 初始化node1
				Edge node1 = new Edge();
				node1.setWeight(E[i].getWeight());
				node1.vertex = v1;// 边node1的下标是起始点所代表的下标v1
				this.edges[v1][v2] = E[i].getWeight();
				this.edges[v2][v1] = E[i].getWeight();

				// 将e连接到v2所在链表的末尾
				if (vList[v2].getFirstEdge() == null) {
					vList[v2].setFirstEdge(node1);
				} else {
					linkLast(vList[v2].getFirstEdge(), node1);
				}
				// 建立无向图需要如下
				Edge node2 = new Edge();
				node2.setWeight(E[i].getWeight());
				node2.vertex = v2;
				if (vList[v1].getFirstEdge() == null) {
					vList[v1].setFirstEdge(node2);
				} else {
					linkLast(vList[v1].getFirstEdge(), node2);
				}
			}
		}
	}

	/*
	 * 根据景点名字返回改名字在vList中的下标位置
	 */
	private int getPosition(String str) {

		for (int i = 0; i < vNum; i++) {
			if (vList[i].getName().equals(str)) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * 将node结点连接到list后
	 */
	private void linkLast(Edge list, Edge node) {
		Edge p = list;
		while (p.getNextEdge() != null) {
			p = p.getNextEdge();
		}
		p.setNextEdge(node);
	}

	/*
	 * 以邻接链表和邻接矩阵的形式输出图,返回String类型用于在界面中输出
	 */
	public String outputGraph() {
		
		StringBuilder graph = new StringBuilder();
		for (int m = 0; m < vNum; m++) {
			
			graph.append(" " + vList[m].getName());
		}

		graph.append("\r\n");
		for (int m = 0; m < vNum; m++) {
			graph.append(vList[m].getName());
			for (int n = 0; n < vNum; n++) {
				graph.append(edges[m][n] + " ");
			}
			graph.append("\r\n");
		}
		return graph.toString();
	}

	/*
	 * 查找查询功能
	 */
	public String search(String name) {
		StringBuilder spotInfor = new StringBuilder();
		for (int i = 0; i < vNum; i++) {
			if (vList[i].getName().contains(name) || vList[i].getInfor().contains(name)) {
				spotInfor.append(vList[i].getInfor() + "\n");
				spotInfor.append("休息区:" + vList[i].getRest());
				spotInfor.append("厕所:" + vList[i].getToilet());
			}
		}
		return spotInfor.toString();

	}

	/*
	 * 按照景点的岔路数从低到高排序，冒泡排序
	 */
	public String sortDegree() {
		Vertex temp;
		for (int i = 0; i < vNum - 1; i++) {
			for (int j = 0; j < vNum - 1 - i; j++) {
				int j1 = getDegree(vList[j].getName());// j1代表V[j]点的度
				int j2 = getDegree(vList[j + 1].getName());// j2代表V[j+1]的度
				if (j1 > j2) {
					temp = vList[j];
					vList[j] = vList[j + 1];
					vList[j + 1] = temp;
				}
			}
		}
		StringBuilder names = new StringBuilder();
		for (int i = 0; i < vNum; i++) {
			names.append(vList[i].getName() + getDegree(vList[i].getName()) + "\n");
		}
		return names.toString();

	}

	/*
	 * 按照景点的受欢迎度从低到高排序，冒泡排序
	 */
	public String sortPopular() {
		Vertex temp;
		for (int i = 0; i < vNum - 1; i++) {
			for (int j = 0; j < vNum - 1 - i; j++) {

				if (vList[j].getPopular() > vList[j + 1].getPopular()) {
					temp = vList[j];
					vList[j] = vList[j + 1];
					vList[j + 1] = temp;
				}
			}
		}

		StringBuilder names = new StringBuilder();
		for (int i = 0; i < vNum; i++) {
			names.append(vList[i].getName() + "\n");
		}
		return names.toString();
	}

	/*
	 * 判断景点的度，判断该点和多少条边相连
	 */
	public int getDegree(String name) {
		int degree = 0;
		for (int i = 0; i < eNum; i++) {
			if (eList[i].getFromVertex().equals(name) || eList[i].getToVertex().equals(name)) {
				degree++;
			}
		}
		return degree;
	}

	/*
	 * 输出两景点件间的最短路径和最短距离，使用Dijkstra算法传入起点和终点景点名字 算法思想G={V,E} 
	 * 1.初始时令visited={V0},T=V-S={其余顶点}，T中顶点对应的距离值
	 * 若存在<V0,Vi>，d(V0,Vi)为<V0,Vi>弧上的权值;若不存在<V0,Vi>,d(V0,Vi)为maxWeight 
	 * 2.从T中选取一个与visited中顶点有关联边且权值最小的顶点v，加入到visited中 
	 * 3.对其余T中顶点的距离值进行修改：若加进v作中间顶点，从V0到Vi的距离值缩短，则修改此距离值
	 * 重复上述步骤2、3，直到S中包含所有顶点，即W=Vi为止
	 */
	public String dijkstra(String startName, String endName) {
		StringBuilder adjMatrix = new StringBuilder();
		int v0 = getPosition(startName);// 得到起始点在景点数组中对应的下标
		int v1 = getPosition(endName); // 得到终止点在景点数组中对应的下标
		int[] dist = new int[vNum]; // 记录其余每个点到起点的权值
		int[] prev = new int[vNum]; // 记录经由过的点的集合
		boolean[] visited = new boolean[vNum];// 标记该点是否被访问过

		// 初始化visited,dist,prev数组
		for (int i = 0; i < vNum; i++) {
			// 一开始假定取直达路径最短
			dist[i] = this.edges[v0][i]; // 初始化将v0到其他的距离按序存入dist数组
			visited[i] = false; // 每个点设置为没访问过

			// 直达情况下最后的经由点就是出发点
			if (i != v0 && dist[i] < maxWeight) {
				prev[i] = v0;// 可以直达
			} else {
				prev[i] = -1;// 无法直达
			}
		}

		// 初始时源点v0∈visited集，表示v0 到v0的最短路径已经找到
		visited[v0] = true;
		// 判断是否该点到其余点距离更短，更换最小路径
		int minDist;
		int v = 0;
		for (int i = 0; i < vNum; i++) {

			minDist = maxWeight;
			// 找出最小距离对应的点
			for (int j = 0; j < vNum; j++) {
				// 如果一个景点未被访问,且在dist[]的值是最小的则选取这个景点为下一个访问的景点
				if (!visited[j] && dist[j] < minDist) {
					v = j;// 经过j中转距离更短
					minDist = dist[j];
				}
			}
			visited[v] = true;
			// 顶点v加入visited,由v0到v的最短路径为mindist
			// 假定由v0到v，再由v直达其余各点，更新当前最后一个经由点及距离
			// 即当已经"顶点v0的最短路径"之后，更新"未获取最短路径的顶点的最短路径和前驱顶点"
			for (int j = 0; j < vNum; j++) {
				if (!visited[j] && this.edges[v][j] < maxWeight) {
					if (minDist + this.edges[v][j] <= dist[j]) {
						// 如果经由一个v点到达j点的 最短路径反而要短,就更新
						dist[j] = minDist + this.edges[v][j];
						prev[j] = v;
					}
				}
			}
		}

		// 输出最短路径经过的每个点
		int m = v1;
		while (prev[m] != -1) {
			adjMatrix.append(vList[m].getName() + "——>" + vList[prev[m]].getName() + "\n");// 连接m代表的点和m的前一个点
			m = prev[m];// 往回找路径
		}
		// adjMatrix.append(vList[v0].getName()+"-->"+vList[v1].getName()+" 为
		// "+dist[v1]);
		return adjMatrix.toString();
	}

	/*
	 * 改图生成最小生成树算法
	 */
	public void minTree() {
		int num = vNum; // 顶点个数
		int[] lowcost = new int[num + 1]; // 到新集合的最小权
		int[] closest = new int[num + 1]; // 代表与s集合相连的最小权边的点
		boolean[] s = new boolean[num + 1]; // s[i] == true代表i点在s集合中
		s[0] = true; // 将第一个点放入s集合

		for (int i = 0; i < num; i++) { // 初始化辅助数组
			lowcost[i] = this.edges[0][i];
			closest[i] = 0;
			s[i] = false;
		}
		for (int i = 0; i < num; i++) {
			int min = maxWeight;
			int j = 0;
			for (int k = 1; k < num; k++) {
				if ((lowcost[k] < min) && (!s[k])) {// 根据最小权加入新点
					min = lowcost[k];
					j = k;
				}
			}

			if (i < num)
				System.out.println("加入点" + vList[j].getName() + ". " + vList[j].getName() + "---"
						+ vList[closest[j]].getName() + min);// 新加入点的j和与j相连的点

			s[j] = true;// 加入新点j
			for (int k = 1; k < num; k++) {
				if ((this.edges[j][k] < lowcost[k]) && (!s[k])) {// 根据新加入的点j,求得最小权
					lowcost[k] = this.edges[j][k];
					closest[k] = j;
				}
			}
		}
	}

	/*
	 * 形成哈密尔顿回路算法
	 */
	public String getHamiltonCircuit() {
		circleRoute = new StringBuilder();
		getHamiltonCircuit(edges);
		System.out.println(circleRoute.toString());
		return circleRoute.toString();
	}
	
	//初始化，调用深度优先算法遍历
	public void getHamiltonCircuit(int[][] adjMatrix) {
		boolean[] used = new boolean[adjMatrix.length]; // 用于标记图中顶点是否被访问
		temp_path = new int[adjMatrix.length];
		int[] path = new int[adjMatrix.length]; // 记录哈密顿回路路径
		for (int i = 0; i < adjMatrix.length; i++) {
			used[i] = false; // 初始化，所有顶点均未被遍历
			path[i] = -1; // 初始化，未选中起点及到达任何顶点
		}
		used[0] = true; // 表示从第1个顶点开始遍历
		path[0] = 0; // 表示哈密顿回路起点为第0个顶点
		dfs(adjMatrix, path, used, 1); // 从第0个顶点开始进行深度优先遍历,如果存在哈密顿回路，输出一条回路，否则无输出
	}
	
	
	

	/*
	 * 参数step:当前行走的步数，即已经遍历顶点的个数;利用深度优先遍历
	 * 先深度遍历得到深度优先遍历的序列
     * 根据vlist的大小得到有多少个景点需要去，确定for循环的次数
     * 取第一个点为初始点，遍历找到第一个与初始点相连的点，以及路。
     * 通过i，path[step-1]两个变量来控制起点景点和终点景点，通过这两个点间的距离，并判断是否 可以直接到达，
     * 如果不可以直接到达则加入起点景点到起点之前的景点的边，并判断起点之前的景点是否与终点可以直接到达，
     * 如果不能到达则继续，回到一个景点并判断是否可以直接到达终点景点遍历结点序列与导游线路图转换的策略：
     * 设遍历结果为v1→v2→…→vi→vi+1→…→vn,对于结点vi和vi+1，如果vi和vi+1存在边，则直接转换。
     * 否则，加入边vi→vi-1，如果vi-1和vi+1存在边，则加入边vi-1→vi+1。
     * 再否则，加入边vi-1→vi-2，如果vi-2和vi+1存在边，则加入边vi-2→vi+1。
     * 如果vi-2和vi+1还不存在边，继续回溯，一定能找到某个整数k（因为景点分布图是连通图），使得vi-k和vi+1存在边，
     * 则加入边vi-k→vi+1。
     * 如果不是连通图，利用temp_path保存从step1到step12经过的边形成的路径，再利用迪杰斯特拉算法找到最后这个点到最开始的点的最短路径。
	 */
	public boolean dfs(int[][] adjMatrix, int[] path, boolean[] used, int step) {
		if (step == adjMatrix.length) { // 当已经遍历完图中所有顶点
			temp_path = Arrays.copyOf(path, step);//将最后一步之前形成的路保存到temp-path中，不包括起始点
			if (adjMatrix[path[step - 1]][0] < 32767 || finish) { // 最后一步到达的顶点能够回到起点
				for (int i = 0; i < path.length; i++)
					circleRoute.append(vList[path[i]].getName() + "——>" + "\n");
				circleRoute.append(vList[path[0]].getName() + "\n");

				return true;
			}
			return false;

		} else {
			for (int i = 0; i < adjMatrix.length; i++) {
				if (!used[i] && adjMatrix[path[step - 1]][i] < 32767) {// 没有访问过且两点相连接
					
					
					used[i] = true;
					path[step] = i;// path中加入邻接矩阵中下标i代表的边,将该点加入哈密尔顿回路
					if (dfs(adjMatrix, path, used, step + 1))
						return true;
					else {
						used[i] = false; // 进行回溯处理
						path[step] = -1;
					}
				}

			}
		}
		if (step == 1) {
			finish = true;
			for (int i = 0; i < temp_path.length; i++) {

				circleRoute.append(vList[temp_path[i]].getName() + "——>" + "\n");
			}
			circleRoute.append(dijkstra(vList[temp_path[0]].getName(), vList[temp_path[temp_path.length - 1]].getName()));
			return true;
		}
		return false;
	}

}

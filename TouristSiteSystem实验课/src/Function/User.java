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

	private int vNum;// vList����Ĵ�С
	private int eNum;// edges����Ĵ�С
	private Vertex[] vList;// �����ڽӱ��ͷ��㼴���еĶ���ļ���
	private Edge[] eList; // �������еı�
	private int[][] edges;// �ڽӾ������ڱ����
	private static final int maxWeight = 32767; // ����������֮��û��·
	StringBuilder circleRoute = new StringBuilder(); // �����������·��ͼ
	int[] temp_path = null;// ���ڹ��ܶ��ٻ�·�㷨����step1-step12��û�γɻ�·��·
	boolean finish = false;// ���ڹ��ܶ��ٻ�·�㷨�ж��Ƿ����

	public User() {
		vList = new Vertex[80];
		eList = new Edge[80];
	}

	// ��ʼ������ͱߵ�����
	public User(Edge[] E, Vertex[] V) {
		// �õ��йض��������ͱߵ�����
		vNum = Manager.firstfree;
		eNum = Manager.firstedge;
		vList = new Vertex[vNum];
		eList = new Edge[eNum];
		// ��ʼ���ڽӾ���
		// �����ڽӾ��󣬳�ʼ���ڽӾ���
		edges = new int[vNum][vNum];
		for (int m = 0; m < vNum; m++) {
			for (int n = 0; n < vNum; n++) {
				if (n == m) {
					edges[n][m] = 0;
				} else {
					edges[n][m] = maxWeight;
				}
			}

			// �õ��йض�����Ϣ,��ʼ��
			for (int i = 0; i < vNum; i++) {
				vList[i] = new Vertex();
				vList[i].setName(V[i].getName());
				vList[i].setInfor(V[i].getInfor());
				vList[i].setPopular(V[i].getPopular());
				vList[i].setRest(V[i].getRest());
				vList[i].setToilet(V[i].getToilet());
				vList[i].setFirstEdge(null);
			}

			// �õ��йرߵ���Ϣ
			for (int i = 0; i < eNum; i++) {
				String str1 = E[i].getFromVertex();// Str1 ����˱ߵ���ʼ�������
				String str2 = E[i].getToVertex();// str2����˱ߵ��յ������
				int v1 = getPosition(str1);// �ҵ���ʼ�����ڽӱ�ͷ�ڵ�����������ڵ�λ�ã��±��ʾ
				int v2 = getPosition(str2);// �ҵ��յ����ڽӱ�ͷ�ڵ�����������ڵ�λ�ã��±��ʾ
				// ��ʼ��eList[]
				eList[i] = new Edge();
				eList[i].setFromVertex(E[i].getFromVertex());
				eList[i].setToVertex(E[i].getToVertex());
				eList[i].setWeight(E[i].getWeight());
			
				// ��ʼ��node1
				Edge node1 = new Edge();
				node1.setWeight(E[i].getWeight());
				node1.vertex = v1;// ��node1���±�����ʼ����������±�v1
				this.edges[v1][v2] = E[i].getWeight();
				this.edges[v2][v1] = E[i].getWeight();

				// ��e���ӵ�v2���������ĩβ
				if (vList[v2].getFirstEdge() == null) {
					vList[v2].setFirstEdge(node1);
				} else {
					linkLast(vList[v2].getFirstEdge(), node1);
				}
				// ��������ͼ��Ҫ����
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
	 * ���ݾ������ַ��ظ�������vList�е��±�λ��
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
	 * ��node������ӵ�list��
	 */
	private void linkLast(Edge list, Edge node) {
		Edge p = list;
		while (p.getNextEdge() != null) {
			p = p.getNextEdge();
		}
		p.setNextEdge(node);
	}

	/*
	 * ���ڽ�������ڽӾ������ʽ���ͼ,����String���������ڽ��������
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
	 * ���Ҳ�ѯ����
	 */
	public String search(String name) {
		StringBuilder spotInfor = new StringBuilder();
		for (int i = 0; i < vNum; i++) {
			if (vList[i].getName().contains(name) || vList[i].getInfor().contains(name)) {
				spotInfor.append(vList[i].getInfor() + "\n");
				spotInfor.append("��Ϣ��:" + vList[i].getRest());
				spotInfor.append("����:" + vList[i].getToilet());
			}
		}
		return spotInfor.toString();

	}

	/*
	 * ���վ���Ĳ�·���ӵ͵�������ð������
	 */
	public String sortDegree() {
		Vertex temp;
		for (int i = 0; i < vNum - 1; i++) {
			for (int j = 0; j < vNum - 1 - i; j++) {
				int j1 = getDegree(vList[j].getName());// j1����V[j]��Ķ�
				int j2 = getDegree(vList[j + 1].getName());// j2����V[j+1]�Ķ�
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
	 * ���վ�����ܻ�ӭ�ȴӵ͵�������ð������
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
	 * �жϾ���Ķȣ��жϸõ�Ͷ�����������
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
	 * ����������������·������̾��룬ʹ��Dijkstra�㷨���������յ㾰������ �㷨˼��G={V,E} 
	 * 1.��ʼʱ��visited={V0},T=V-S={���ඥ��}��T�ж����Ӧ�ľ���ֵ
	 * ������<V0,Vi>��d(V0,Vi)Ϊ<V0,Vi>���ϵ�Ȩֵ;��������<V0,Vi>,d(V0,Vi)ΪmaxWeight 
	 * 2.��T��ѡȡһ����visited�ж����й�������Ȩֵ��С�Ķ���v�����뵽visited�� 
	 * 3.������T�ж���ľ���ֵ�����޸ģ����ӽ�v���м䶥�㣬��V0��Vi�ľ���ֵ���̣����޸Ĵ˾���ֵ
	 * �ظ���������2��3��ֱ��S�а������ж��㣬��W=ViΪֹ
	 */
	public String dijkstra(String startName, String endName) {
		StringBuilder adjMatrix = new StringBuilder();
		int v0 = getPosition(startName);// �õ���ʼ���ھ��������ж�Ӧ���±�
		int v1 = getPosition(endName); // �õ���ֹ���ھ��������ж�Ӧ���±�
		int[] dist = new int[vNum]; // ��¼����ÿ���㵽����Ȩֵ
		int[] prev = new int[vNum]; // ��¼���ɹ��ĵ�ļ���
		boolean[] visited = new boolean[vNum];// ��Ǹõ��Ƿ񱻷��ʹ�

		// ��ʼ��visited,dist,prev����
		for (int i = 0; i < vNum; i++) {
			// һ��ʼ�ٶ�ȡֱ��·�����
			dist[i] = this.edges[v0][i]; // ��ʼ����v0�������ľ��밴�����dist����
			visited[i] = false; // ÿ��������Ϊû���ʹ�

			// ֱ����������ľ��ɵ���ǳ�����
			if (i != v0 && dist[i] < maxWeight) {
				prev[i] = v0;// ����ֱ��
			} else {
				prev[i] = -1;// �޷�ֱ��
			}
		}

		// ��ʼʱԴ��v0��visited������ʾv0 ��v0�����·���Ѿ��ҵ�
		visited[v0] = true;
		// �ж��Ƿ�õ㵽����������̣�������С·��
		int minDist;
		int v = 0;
		for (int i = 0; i < vNum; i++) {

			minDist = maxWeight;
			// �ҳ���С�����Ӧ�ĵ�
			for (int j = 0; j < vNum; j++) {
				// ���һ������δ������,����dist[]��ֵ����С����ѡȡ�������Ϊ��һ�����ʵľ���
				if (!visited[j] && dist[j] < minDist) {
					v = j;// ����j��ת�������
					minDist = dist[j];
				}
			}
			visited[v] = true;
			// ����v����visited,��v0��v�����·��Ϊmindist
			// �ٶ���v0��v������vֱ��������㣬���µ�ǰ���һ�����ɵ㼰����
			// �����Ѿ�"����v0�����·��"֮�󣬸���"δ��ȡ���·���Ķ�������·����ǰ������"
			for (int j = 0; j < vNum; j++) {
				if (!visited[j] && this.edges[v][j] < maxWeight) {
					if (minDist + this.edges[v][j] <= dist[j]) {
						// �������һ��v�㵽��j��� ���·������Ҫ��,�͸���
						dist[j] = minDist + this.edges[v][j];
						prev[j] = v;
					}
				}
			}
		}

		// ������·��������ÿ����
		int m = v1;
		while (prev[m] != -1) {
			adjMatrix.append(vList[m].getName() + "����>" + vList[prev[m]].getName() + "\n");// ����m����ĵ��m��ǰһ����
			m = prev[m];// ������·��
		}
		// adjMatrix.append(vList[v0].getName()+"-->"+vList[v1].getName()+" Ϊ
		// "+dist[v1]);
		return adjMatrix.toString();
	}

	/*
	 * ��ͼ������С�������㷨
	 */
	public void minTree() {
		int num = vNum; // �������
		int[] lowcost = new int[num + 1]; // ���¼��ϵ���СȨ
		int[] closest = new int[num + 1]; // ������s������������СȨ�ߵĵ�
		boolean[] s = new boolean[num + 1]; // s[i] == true����i����s������
		s[0] = true; // ����һ�������s����

		for (int i = 0; i < num; i++) { // ��ʼ����������
			lowcost[i] = this.edges[0][i];
			closest[i] = 0;
			s[i] = false;
		}
		for (int i = 0; i < num; i++) {
			int min = maxWeight;
			int j = 0;
			for (int k = 1; k < num; k++) {
				if ((lowcost[k] < min) && (!s[k])) {// ������СȨ�����µ�
					min = lowcost[k];
					j = k;
				}
			}

			if (i < num)
				System.out.println("�����" + vList[j].getName() + ". " + vList[j].getName() + "---"
						+ vList[closest[j]].getName() + min);// �¼�����j����j�����ĵ�

			s[j] = true;// �����µ�j
			for (int k = 1; k < num; k++) {
				if ((this.edges[j][k] < lowcost[k]) && (!s[k])) {// �����¼���ĵ�j,�����СȨ
					lowcost[k] = this.edges[j][k];
					closest[k] = j;
				}
			}
		}
	}

	/*
	 * �γɹ��ܶ��ٻ�·�㷨
	 */
	public String getHamiltonCircuit() {
		circleRoute = new StringBuilder();
		getHamiltonCircuit(edges);
		System.out.println(circleRoute.toString());
		return circleRoute.toString();
	}
	
	//��ʼ����������������㷨����
	public void getHamiltonCircuit(int[][] adjMatrix) {
		boolean[] used = new boolean[adjMatrix.length]; // ���ڱ��ͼ�ж����Ƿ񱻷���
		temp_path = new int[adjMatrix.length];
		int[] path = new int[adjMatrix.length]; // ��¼���ܶٻ�··��
		for (int i = 0; i < adjMatrix.length; i++) {
			used[i] = false; // ��ʼ�������ж����δ������
			path[i] = -1; // ��ʼ����δѡ����㼰�����κζ���
		}
		used[0] = true; // ��ʾ�ӵ�1�����㿪ʼ����
		path[0] = 0; // ��ʾ���ܶٻ�·���Ϊ��0������
		dfs(adjMatrix, path, used, 1); // �ӵ�0�����㿪ʼ����������ȱ���,������ڹ��ܶٻ�·�����һ����·�����������
	}
	
	
	

	/*
	 * ����step:��ǰ���ߵĲ��������Ѿ���������ĸ���;����������ȱ���
	 * ����ȱ����õ�������ȱ���������
     * ����vlist�Ĵ�С�õ��ж��ٸ�������Ҫȥ��ȷ��forѭ���Ĵ���
     * ȡ��һ����Ϊ��ʼ�㣬�����ҵ���һ�����ʼ�������ĵ㣬�Լ�·��
     * ͨ��i��path[step-1]����������������㾰����յ㾰�㣬ͨ�����������ľ��룬���ж��Ƿ� ����ֱ�ӵ��
     * ���������ֱ�ӵ����������㾰�㵽���֮ǰ�ľ���ıߣ����ж����֮ǰ�ľ����Ƿ����յ����ֱ�ӵ��
     * ������ܵ�����������ص�һ�����㲢�ж��Ƿ����ֱ�ӵ����յ㾰�������������뵼����·ͼת���Ĳ��ԣ�
     * ��������Ϊv1��v2������vi��vi+1������vn,���ڽ��vi��vi+1�����vi��vi+1���ڱߣ���ֱ��ת����
     * ���򣬼����vi��vi-1�����vi-1��vi+1���ڱߣ�������vi-1��vi+1��
     * �ٷ��򣬼����vi-1��vi-2�����vi-2��vi+1���ڱߣ�������vi-2��vi+1��
     * ���vi-2��vi+1�������ڱߣ��������ݣ�һ�����ҵ�ĳ������k����Ϊ����ֲ�ͼ����ͨͼ����ʹ��vi-k��vi+1���ڱߣ�
     * ������vi-k��vi+1��
     * ���������ͨͼ������temp_path�����step1��step12�����ı��γɵ�·���������õϽ�˹�����㷨�ҵ��������㵽�ʼ�ĵ�����·����
	 */
	public boolean dfs(int[][] adjMatrix, int[] path, boolean[] used, int step) {
		if (step == adjMatrix.length) { // ���Ѿ�������ͼ�����ж���
			temp_path = Arrays.copyOf(path, step);//�����һ��֮ǰ�γɵ�·���浽temp-path�У���������ʼ��
			if (adjMatrix[path[step - 1]][0] < 32767 || finish) { // ���һ������Ķ����ܹ��ص����
				for (int i = 0; i < path.length; i++)
					circleRoute.append(vList[path[i]].getName() + "����>" + "\n");
				circleRoute.append(vList[path[0]].getName() + "\n");

				return true;
			}
			return false;

		} else {
			for (int i = 0; i < adjMatrix.length; i++) {
				if (!used[i] && adjMatrix[path[step - 1]][i] < 32767) {// û�з��ʹ�������������
					
					
					used[i] = true;
					path[step] = i;// path�м����ڽӾ������±�i����ı�,���õ������ܶ��ٻ�·
					if (dfs(adjMatrix, path, used, step + 1))
						return true;
					else {
						used[i] = false; // ���л��ݴ���
						path[step] = -1;
					}
				}

			}
		}
		if (step == 1) {
			finish = true;
			for (int i = 0; i < temp_path.length; i++) {

				circleRoute.append(vList[temp_path[i]].getName() + "����>" + "\n");
			}
			circleRoute.append(dijkstra(vList[temp_path[0]].getName(), vList[temp_path[temp_path.length - 1]].getName()));
			return true;
		}
		return false;
	}

}

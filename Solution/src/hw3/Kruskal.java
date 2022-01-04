package hw3;

import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {
	private int nodeCount;
	private ArrayList<Edge> graphEdges;
	Node nodex;

	public Kruskal(Node node) {
		nodex = node;
		graphEdges = new ArrayList<Edge>();
		graphEdges.add(new Edge(0, 0, 0));
		nodeCount = node.cities.city_names.length + 1;
		for (int i = 0; i < node.cities.city_names.length; i++) {
			for (int j = 0; j < node.cities.city_names.length; j++) {
				if (node.cities.distances[i][j] != 0) {
					graphEdges.add(new Edge(i + 1, j + 1, node.cities.distances[i][j]));
				}

			}
		}
	}

	public String kruskalMST() {
		String outputMessage = "";
		Collections.sort(graphEdges);
		ArrayList<Edge> mstEdges = new ArrayList<Edge>();
		DisjointSet nodeSet = new DisjointSet(nodeCount + 1);
		for (int i = 1; i < graphEdges.size() && mstEdges.size() < (nodeCount - 1); i++) {
			Edge currentEdge = graphEdges.get(i);
			int root1 = nodeSet.find(currentEdge.getVertex1());
			int root2 = nodeSet.find(currentEdge.getVertex2());
			if (root1 != root2) {
				mstEdges.add(currentEdge);
				nodeSet.union(root1, root2);
			}
		}
		for (Edge edge : mstEdges) {
			outputMessage += nodex.cities.city_names[edge.getVertex1() - 1] + "-"
					+ nodex.cities.city_names[edge.getVertex2() - 1] + ",";
		}
		return outputMessage;
	}
}

class Edge implements Comparable<Edge> {
	private int vertex1;
	private int vertex2;
	private int weight;

	public Edge(int vertex1, int vertex2, int weight) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = weight;
	}

	public int getVertex1() {
		return vertex1;
	}

	public int getVertex2() {
		return vertex2;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public int compareTo(Edge otherEdge) {
		return this.getWeight() - otherEdge.getWeight();
	}

	@Override
	public String toString() {
		return "(" + getVertex1() + ", " + getVertex2() + ") weight: " + getWeight();
	}
}

class DisjointSet {
	private int[] s;

	public int[] getSet() {
		return s;
	}

	public DisjointSet(int numElements) {
		s = new int[numElements];
		for (int i = 0; i < s.length; i++)
			s[i] = -1;
	}

	public void union(int root1, int root2) {
		if (s[root2] < s[root1])
			s[root1] = root2;
		else {
			if (s[root1] == s[root2])
				s[root1]--;
			s[root2] = root1;
		}
	}

	public int find(int x) {
		if (s[x] < 0)
			return x;
		else
			return s[x] = find(s[x]);
	}
}
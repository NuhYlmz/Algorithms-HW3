package hw3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadFirstSearch {
	int length;
	ArrayList<String> nodeList = new ArrayList<String>();
	Queue<String> queue = new LinkedList<String>();
	String node;
	int[] visitedNodes;
	int[][] connections;

	public BreadFirstSearch(Node source) {
		length = source.cities.city_names.length;
		visitedNodes = new int[length];
		for (int i = 0; i < length; i++) {
			nodeList.add(source.cities.city_names[i]);
		}
		node = nodeList.get(0);
		visitedNodes[0] = 1;
		queue.add(node);
		connections = source.cities.distances;
	}

	public void bfs() {
		System.out.print("Breadth-First Search -->");
		while (!queue.isEmpty()) {
			node = (String) queue.remove();
			int nodeIndex = nodeList.indexOf(node);
			System.out.print(" " + node);

			for (int i = 0; i < connections[nodeIndex].length; i++) {
				if (connections[nodeIndex][i] > 0 && visitedNodes[i] != 1) {
					queue.add(nodeList.get(i));
					visitedNodes[i] = 1;
				}
			}

		}

	}

}

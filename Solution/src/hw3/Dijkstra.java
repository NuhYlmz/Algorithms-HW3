package hw3;

public class Dijkstra {
	static int counter = 0;
	static int V;

	void printPath(Node n, int parent[], int j) {
		if (parent[j] == -1)
			return;
		printPath(n, parent, parent[j]);
		System.out.print(" -> ");
		System.out.print(" " + n.cities.city_names[j]);
	}

	int minDistance(int dist[], Boolean sptSet[]) {
		int min = Integer.MAX_VALUE;
		int min_index = -1;
		for (int v = 0; v < V; v++)
			if (sptSet[v] == false && dist[v] <= min) {
				min = dist[v];
				min_index = v;
			}
		return min_index;
	}

	public int dijkstra(Node n, int src, int dest) {

		V = n.cities.city_names.length;
		int adjacency[][] = n.cities.distances;
		int dist[] = new int[V];
		int parent[] = new int[V];
		parent[0] = -1;
		Boolean sptSet[] = new Boolean[V];

		for (int i = 0; i < V; i++) {
			dist[i] = Integer.MAX_VALUE;
			sptSet[i] = false;
		}

		dist[src] = 0;

		for (int count = 0; count < V - 1; count++) {

			int u = minDistance(dist, sptSet);

			sptSet[u] = true;

			for (int v = 0; v < V; v++) {
				if (!sptSet[v] && adjacency[u][v] != 0 && dist[u] != Integer.MAX_VALUE
						&& dist[u] + adjacency[u][v] < dist[v]) {
					dist[v] = dist[u] + adjacency[u][v];
					parent[v] = u;
				}
			}
		}

		System.out.print(n.cities.city_names[src]);
		printPath(n, parent, dest);
		return dist[dest];
	}

}
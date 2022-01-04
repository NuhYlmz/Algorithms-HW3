package hw3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		BinarySearchTree tree = new BinarySearchTree();
		int zip_key;
		String cities[] = new String[10];
		int dist[][];
		String temp[];
		ArrayList<String> citiesarr = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		try {
			String line = br.readLine();
			while (line != null) {
				zip_key = Integer.parseInt(line.trim());
				// System.out.printf("%s\n",line);
				line = br.readLine();
				while (!line.matches(".*\\d+.*")) {
					citiesarr.add(line.trim());
					// System.out.printf("%s\n",line);
					line = br.readLine();
				}
				cities = new String[citiesarr.size()];

				// Arrayl to array
				for (int i = 0; i < citiesarr.size(); i++) {
					cities[i] = citiesarr.get(i);
					// System.out.printf("%s\n",line);

				}

				dist = new int[citiesarr.size()][citiesarr.size()];
				for (int i = 0; i < citiesarr.size(); i++) {
					temp = line.split("\\s+");
					for (int j = 0; j < citiesarr.size(); j++) {
						dist[i][j] = Integer.parseInt(temp[j]);
					}
					// System.out.printf("%s\n",line);
					line = br.readLine();
				}
				citiesarr.clear();
				Graph tempgraph = new Graph(cities, dist);
				tree.insert(zip_key, tempgraph);

			}
		} finally {
			br.close();
		}
		// System.out.println();

		// LOOP
		while (true) {
			BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("User Input:");
			String s = br2.readLine();
			String inputs[] = s.split(" ");
			// Switch-case
			switch (inputs[0]) {
			case "D":
				Node dd = tree.search(tree.root, Integer.parseInt(inputs[1]));
				Dijkstra d1 = new Dijkstra();
				String a1 = inputs[2];
				String a2 = inputs[3];
				int x1 = 0, x2 = 0;
				for (int i = 0; i < dd.cities.city_names.length; i++) {
					if (a1.equals(dd.cities.city_names[i])) {
						x1 = i;
					}
					if (a2.equals(dd.cities.city_names[i])) {
						x2 = i;
					}
				}
				System.out.print("Output: ");
				int total = d1.dijkstra(dd, x1, x2);
				System.out.printf("\nTotal Distance: %d Km\n", total);
				break;
			case "BPR":
				System.out.println("Output: ");
				tree.printPreorder(tree.root);
				break;
			case "K":
				System.out.print("Output: ");
				Node ss = tree.search(tree.root, Integer.parseInt(inputs[1]));
				Kruskal krus1 = new Kruskal(ss);
				System.out.println(krus1.kruskalMST());
				break;
			case "Q":
				System.out.print("Output: Bye! (Program terminates)");
				return;

			default:
				System.out.println("Output: Wrong input.");
				break;
			}
			// Switch-case

		}
		// LOOP

	}

}

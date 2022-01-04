package hw3;

public class Node {
	int zip_key;
	Node left, right;
	Graph cities;
	public Node(int zip_key,Graph cities) {
		this.zip_key = zip_key;
		this.cities=cities;
		left = right = null;
	}
}

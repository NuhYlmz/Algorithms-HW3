package hw3;

public class BinarySearchTree {
	Node root;
	BreadFirstSearch bfs;

	BinarySearchTree() {
		root = null;
	}

	void insert(int zip_key, Graph cities) {
		root = insertRec(root, zip_key, cities);
	}

	Node insertRec(Node root, int zip_key, Graph cities) {
		if (root == null) {
			root = new Node(zip_key, cities);
			return root;
		}
		if (zip_key < root.zip_key)
			root.left = insertRec(root.left, zip_key, cities);
		else if (zip_key > root.zip_key)
			root.right = insertRec(root.right, zip_key, cities);
		return root;
	}

	public Node search(Node root, int key) {
		if (root == null || root.zip_key == key)
			return root;

		if (root.zip_key > key)
			return search(root.left, key);

		return search(root.right, key);
	}

	void printPreorder(Node node) {
		if (node == null)
			return;
		System.out.print(node.zip_key + "\n");
		bfs = new BreadFirstSearch(node);
		bfs.bfs();
		System.out.println();
		printPreorder(node.left);
		printPreorder(node.right);
	}
}

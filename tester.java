
public class tester {
	public static void main(String[] args) {
		AVLTree t = new AVLTree();
		//System.out.println(t.empty());
		System.out.println("inserting 1");
		t.insert(2, "G");
		//System.out.println(t.getRoot().getKey());
		System.out.println("inserting 10");
		t.insert(10, "g");
		System.out.println("inserting 2");
		t.insert(1, "L");
		AVLTree.print(t.getRoot());
		System.out.println("inserting 3");
		t.insert(3, "k");
		System.out.println("inserting 20");
		t.insert(20, "g");
		AVLTree.print(t.getRoot());
		System.out.println("inserting 4");
		t.insert(4, "g");
		AVLTree.print(t.getRoot());
		System.out.println("inserting 6");
		t.insert(6, "g");
		AVLTree.print(t.getRoot());
		System.out.println("inserting 8");
		t.insert(8, "g");
		System.out.println("inserting 12");
		t.insert(12, "g");
		System.out.println("inserting 15");
		t.insert(15, "g");
		AVLTree.print(t.getRoot());
		System.out.println(t.getRoot().getLeft().getKey() + " & " + t.getRoot().getLeft().getHeight());
		System.out.println("inserting 7");
		t.insert(7, "g");
		AVLTree.print(t.getRoot());
		System.out.println("inserting 18");
		t.insert(18, "g");
		System.out.println("inserting 13");
		t.insert(13, "g");
		System.out.println("inserting 14");
		t.insert(14, "g");
		System.out.println("inserting 11");
		t.insert(11, "g");
		//System.out.println(t.getRoot().getHeight());
		System.out.println("printing...");
		AVLTree.print(t.getRoot());
		//AVLTree.printR(t.getRoot());
		//System.out.println(" ");
		//AVLTree.printL(t.getRoot());
	}
}

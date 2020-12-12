import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


/**
 *
 * AVLTree
 *
 * An implementation of a AVL Tree with distinct integer keys and info
 *
 */

public class AVLTree {
	
	 public static void main(String[] args) { 
			// for (int i=0;i<50;i++) {
				// System.out.println(i + ": ");
				 //AVLTest test = new AVLTest();
				 //test.testDelete(); }
		 // AVLTree tree = new AVLTree(); 
		  //tree.insert(2, "a");
		  //tree.insert(3, "c");
		  //tree.insert(1, "b");
		  //tree.insert(6, "b");
		  //tree.insert(4, "b");
		  //tree.insert(5, "b");
		  //tree.insert(7, "b");

		  //Test.printTree(tree.getRoot(),0);
		  //System.out.println("TREE SIZE: "+ tree.size());
		  //print(tree.getRoot());
		  //tree.delete(7);
		  //System.out.println("delete node 7: " + tree.delete(7));
		  //Test.printTree(tree.getRoot(),0);
		  //System.out.println("TREE SIZE: "+ tree.size());
		  //System.out.println("delete node 5: " + tree.delete(5));
		  //Test.printTree(tree.getRoot(),0);
		 //System.out.println("delete node 3: " + tree.delete(3));
		  //Test.printTree(tree.getRoot(),0);
		  //System.out.println("delete node 6: " + tree.delete(6));
		  //Test.printTree(tree.getRoot(),0);
		//  tree.delete(6);
		  //Test.printTree(tree.getRoot(),0);
		  //System.out.println("TREE SIZE: "+ tree.size());
		  //tree.delete(1);
		  //Test.printTree(tree.getRoot(),0);
		  //System.out.println("TREE SIZE: "+ tree.size());
		  //tree.delete(5);
		  //Test.printTree(tree.getRoot(),0);
		  //System.out.println("TREE SIZE: "+ tree.size());
		  //tree.delete(1);
		  //tree.delete(5);
		 // print(tree.getRoot());
		  //Test.printTree(tree.getRoot(),0);
		  //AVLTree[] t = tree.split(7);
		  //System.out.println("Small tree: ");
		  //Test.printTree(t[0].getRoot(),0);
		  //System.out.println("TREE SIZE: "+ t[0].size());
		  //System.out.println("Big tree: ");
		  //Test.printTree(t[1].getRoot(),0);
		  //System.out.println("TREE SIZE: "+ t[1].size());
		 // Test.printTree(tree.getRoot(),0);
		 // AVLTree[] t = tree.split(6);
		 // System.out.println("Small tree: ");
		 // Test.printTree(t[0].getRoot(),0);
		 // System.out.println("Big tree: ");
		  //Test.printTree(t[1].getRoot(),0);
		 int size = 20000;
		 Integer[] arr= new Integer[size];
		 for (int i=0; i<size;i++) {
			 arr[i]=i;
		 }
		 List<Integer> lst= Arrays.asList(arr);
		 Collections.shuffle(lst);
		 lst.toArray(arr);
		// System.out.println(Arrays.toString(arr));
		 int num_swaps=0;
		 int i=0;
		 boolean check = true;
		 while (check) {
			 i=0;
			 check=false;
			 while (arr[i] < arr[i+1]) {
				 i+=1;
				 if (i==size-1) break;
				 }
			 if (i<size-1) {
		     while (arr[i] >arr[i+1]) {
		    	check=true;
				int temp = arr[i];
				arr[i] = arr[i+1];
				arr[i+1] = temp;
				num_swaps+=1;
				i+=1;
				if (i==size-1) break;
				 }
			 }
			 }	 
		 
		// System.out.println(Arrays.toString(arr));
		 System.out.println(num_swaps);
		 }

	public static void print2(IAVLNode x) {
		if (x == null) System.out.println("null");
		else {
		//System.out.println(x.getKey() + " height: " + x.getHeight());
		if (x.getLeft().getKey() != -1) {
			print(x.getLeft());
		}
		if (x.getRight().getKey() != -1) {
			print(x.getRight());
		}
		else {
			System.out.println(x.getKey());
		}
		}
	}

	public static void print(IAVLNode x) {
		System.out.println(x.getKey());
		if (x.getLeft().getKey() != -1)
			print(x.getLeft());
		if (x.getRight().getKey() != -1)
			print(x.getRight());
	}

	private IAVLNode root;
	private int size;
	
	public AVLTree() {
		this(null);
		this.size = 0;
	}
	public AVLTree(IAVLNode root) {
		this.root = root;
		this.size = 1;
	}
	
	
	public AVLNode getPredecessor(AVLNode node) {
		if (node.getLeft().getKey() != -1) {
			node=(AVLNode) node.getLeft();
			while (node.getRight().getKey() != -1) node=(AVLNode) node.getRight();
			return node;
		}
		else {
			while(node.getParent() != null) {
				AVLNode parent= (AVLNode) node.getParent();
				if (parent.getRight().getKey() == node.getKey()) return parent;
				node=parent;
			}
		}
		return null;
	}
	
	public void updateNodeSize(AVLNode node) {
		AVLNode r = (AVLNode) node.getRight();
		AVLNode l = (AVLNode) node.getLeft();
		node.setNodeSize(r.getNodeSize()+l.getNodeSize()+1);
	}
	public AVLNode getSuccessor(AVLNode node) {
		if (node.getRight().getKey() != -1) {
			node=(AVLNode) node.getRight();
			while (node.getLeft().getKey() != -1) node=(AVLNode) node.getLeft();
			return node;
		}
		else {
			while(node.getParent() != null) {
				AVLNode parent= (AVLNode) node.getParent();
				if (parent.getLeft().getKey() == node.getKey()) return parent;
				node=parent;
			}
		}
		return null;
		
	}

	/**
	 * public boolean empty()
	 *
	 * returns true if and only if the tree is empty
	 *
	 */
	public boolean empty() {
		if (this.getRoot() != null) {
			return false;
		}
		return true; 
	}

	/**
	 * public String search(int k)
	 *
	 * returns the info of an item with key k if it exists in the tree otherwise,
	 * returns null
	 */
	public String search(int k) {
		IAVLNode node = this.getRoot();
		while (node.getKey() != -1) {
			if (k > node.getKey()) {
				node = node.getRight();				
			}
			else if (k < node.getKey()) {
				node = node.getLeft();				
			}
			else if (k == node.getKey()) {
				return node.getValue();
			}
		}
		
		return null; 
	}

	/**
	 * public int insert(int k, String i)
	 *
	 * inserts an item with key k and info i to the AVL tree. the tree must remain
	 * valid (keep its invariants). returns the number of rebalancing operations, or
	 * 0 if no rebalancing operations were necessary. promotion/rotation - counted
	 * as one rebalnce operation, double-rotation is counted as 2. returns -1 if an
	 * item with key k already exists in the tree.
	 */
	public int insert(int k, String i) {
		if (this.empty()) {
			this.root = new AVLNode (k,i);
			return 0;
		}
		//System.out.println("S: "+ this.size);
		IAVLNode z = searchPlace(k);
		//System.out.println("z is: " +z.getKey());
		if (z.getKey() == k) return -1;
		int oldS = this.size ;
		this.size = oldS+1;
		IAVLNode x = new AVLNode (k,i);
		x.setHeight(0);
		int rebalancing = 0;
		if (!z.getRight().isRealNode() && !z.getLeft().isRealNode()) { //the parent z is a leaf
			placeSon (z, x);
			rebalancing = promote(z, rebalancing);
			x = z;
			z = z.getParent();
			
			while (x.getKey() != this.getRoot().getKey() && x.getHeight() == z.getHeight()) { 
				if (hightDef(z, x) == 1) { // Case 1
					rebalancing = promote(z, rebalancing);
					x = z;
					z = z.getParent();
				} else { // Case 2 or Case 3, height deference = 2
					if (z.getKey() < x.getKey()) {
						if (hightDef(x, x.getRight()) == 2 && hightDef(x, x.getLeft()) == 1) { // case 2
							rebalancing = singleRotation((AVLNode) z, rebalancing, 'I');
							break;
						} else { // case 3
							rebalancing = doubleRotation((AVLNode) z, rebalancing, 'I');
							break;
							}
						}else {
							if (hightDef(x, x.getLeft()) == 2 && hightDef(x, x.getRight()) == 1) { // case 2
								rebalancing = singleRotation((AVLNode) z, rebalancing, 'I');
								break;
							} else { // case 3
								rebalancing = doubleRotation((AVLNode) z, rebalancing, 'I');
								break;
							}
					}
				}
			}
				
		}else { //the parent z is not a leaf - tree stays valid
			placeSon (z, x);	
		}
		while (x != null) {
			updateNodeSize((AVLNode) x);
			x = x.getParent();
		}
		return rebalancing;

	}
	
	private int hightDef (IAVLNode parent, IAVLNode son) { // check the height difference between parent an OTHER son (not the son that is a param)
		if (parent.getLeft().getKey() == son.getKey()) {
			return parent.getHeight() - parent.getRight().getHeight();
 		}else {
 			return parent.getHeight() - parent.getLeft().getHeight();
 		
 		}
	}
	
	private int promote (IAVLNode node, int cnt) {
		updateNodeSize ((AVLNode) node);
		int h = node.getHeight();
		node.setHeight(h+1);
		return cnt+1;
	}
	
	
	private int fixHeights_R (IAVLNode z, IAVLNode y, IAVLNode a, IAVLNode b, int cnt, char type) {
		int i = 0;
		if (type == 'D') {
			if (a.getHeight()==b.getHeight()) {
				z.setHeight(z.getHeight() -1);
				i++;
				y.setHeight(y.getHeight() +1);
				i++;
			}
			else {
				z.setHeight(z.getHeight() -2);
				i = i+2;
			}
			
		}else {
			z.setHeight(z.getHeight() -1);
			i++;
		}
		
		return cnt+i;
	}

	private int fixHeights_DR (IAVLNode z, IAVLNode y, IAVLNode a, int cnt, char type) {
		int i = 0;
		y.setHeight(y.getHeight() -1);
		i++;
		a.setHeight(a.getHeight() +1);
		i++;
		if (type == 'D') {
			z.setHeight(z.getHeight() -2);
			i = i+2;
		}else {
			z.setHeight(z.getHeight() -1);
			i++;
		}
		
		return cnt+i;
	}
	
	private void placeSon (IAVLNode parent, IAVLNode child) {
		if (child.getKey() > parent.getKey()) { //set as right son
			parent.setRight(child);
			child.setParent(parent);
		}else { //set as left son
			parent.setLeft(child);
			child.setParent(parent);
		}
	}
	
	
	private IAVLNode searchPlace (int k) {
		IAVLNode node = this.getRoot();
		while (node.getKey() != -1) {
			if (k > node.getKey()) {
				node = node.getRight();				
			}
			else if (k < node.getKey()) {
				node = node.getLeft();				
			}
			else if (k == node.getKey()) {
				return node;
			}
		} return node.getParent();
	}
	
	
	/**
	 * public int delete(int k)
	 *
	 * deletes an item with key k from the binary tree, if it is there; the tree
	 * must remain valid (keep its invariants). returns the number of rebalancing
	 * operations, or 0 if no rebalancing operations were needed. demotion/rotation
	 * - counted as one rebalnce operation, double-rotation is counted as 2. returns
	 * -1 if an item with key k was not found in the tree.
	 */
	public int delete(int k) { //O(logn)
		//---------first part----------- finding the node
		IAVLNode node = root;
		if (node==null) return -1; //empty tree
		while (node.getKey() != -1) { // searching for node with key k
			if (node.getKey() == k) break;
			if (node.getKey() > k) node= node.getLeft();
			else node=node.getRight();  
		}
		if (node.getKey() == -1) return -1; //there isn't any node with key k in the tree
		
		//-----------second part----------- deleting the node
		IAVLNode parent = node.getParent();
		parent = help_delete((AVLNode) node,parent, k); //deletes node k and returns it's parent/it's successor parent
		if (parent==null) return 0;
		// ------------third part----------- balancing the tree
		int height_parent = parent.getHeight();
		int height_rightSon = parent.getRight().getHeight();
		int height_leftSon = parent.getLeft().getHeight();
		int num_rotates =0;
		while (height_parent != Math.max(height_rightSon, height_leftSon) +1 ||
				Math.abs(height_rightSon - height_leftSon) > 1) { // if neither is true -> we balanced the tree 
			if (height_leftSon == height_rightSon) { // case 1
				parent.setHeight(height_leftSon+1);
				parent=parent.getParent();
				num_rotates+=1;
				System.out.println("case 1");
			}
			else {
				AVLNode maxSon;
				if (height_rightSon > height_leftSon) maxSon = (AVLNode) parent.getRight();
				else maxSon = (AVLNode) parent.getLeft();
				int max_rightGrandson_height = maxSon.getRight().getHeight();
				int max_leftGrandson_height = maxSon.getLeft().getHeight();
				if (max_rightGrandson_height == max_leftGrandson_height ) {//case 2
					System.out.println("case 2");
					num_rotates = singleRotation((AVLNode) parent,num_rotates, 'D');
					return num_rotates;
				}
				if ((maxSon == (AVLNode) parent.getLeft() &&  max_rightGrandson_height < max_leftGrandson_height) ||
					(maxSon == (AVLNode) parent.getRight() && max_rightGrandson_height > max_leftGrandson_height )) { //case 3
					num_rotates = singleRotation((AVLNode) parent, num_rotates, 'D');
					parent=maxSon;
					System.out.println("case 3");
				}
				else { // case 4
					IAVLNode temp=parent;
					if (maxSon.getKey() == parent.getLeft().getKey()) parent = maxSon.getRight();
					else parent=maxSon.getLeft();
					num_rotates = doubleRotation((AVLNode) temp, num_rotates, 'D');
					System.out.println("case 4");
				}
				
			}
			if (parent==null) break;
			height_parent = parent.getHeight();
			height_rightSon = parent.getRight().getHeight();
			height_leftSon = parent.getLeft().getHeight();
		}	
		while (parent != null) {
			updateNodeSize((AVLNode) parent);
			parent=parent.getParent();
		}
			   return num_rotates;	
		  
	}
	
	private IAVLNode help_delete(AVLNode node,IAVLNode parent, int k) { //O(logn)
		boolean isRoot = false;
		if (parent == null) isRoot=true;
		if (node.getRight().getKey() == -1 && node.getLeft().getKey()==-1) { // node is a leaf
			if (!isRoot) {
				AVLNode empty= new AVLNode();
				help_delete_2(parent, empty, k);
			}
			else this.root=null;
		}
		else {
			if (node.getRight().getKey() == -1) { // node has only left son
				if (!isRoot) help_delete_2(parent,node.getLeft(), k);
				else this.root=(AVLNode) node.getLeft();
			}
			if (node.getLeft().getKey() == -1) { // node has only right son
				if (!isRoot) help_delete_2(parent, node.getRight(), k);
				else this.root=(AVLNode) node.getRight();
			}
			if (node.getRight().getKey() != -1 && node.getLeft().getKey()!=-1 ) { //node has 2 sons
			   AVLNode successor=getSuccessor(node);
			   //System.out.println("The successor is: " + successor.getKey());
			   node.setValue(successor.getValue());
			   node.setKey(successor.getKey());
			   parent = successor.getParent();
			   if (successor.getRight().getKey() == -1 && successor.getLeft().getKey()==-1) { // successor is a leaf
						AVLNode empty= new AVLNode();
						help_delete_2(parent, empty, successor.getKey());
			   }
			   else help_delete_2(parent,successor.getRight(), k); //successor has a right son
			   
			}
		}
		//System.out.println("The parent is: " + parent.getKey());
		return parent;
	}
		
	private void help_delete_2 (IAVLNode parent, IAVLNode son, int k) { //O(1)
		if (parent.getRight().getKey() == k) parent.setRight(son);
		else parent.setLeft(son);
		son.setParent(parent);
		updateNodeSize((AVLNode) parent);
	}
	
	private int singleRotation(AVLNode z, int balancing, char type) {
		AVLNode parent = (AVLNode) z.getParent();
		AVLNode y;
		AVLNode a;
		AVLNode b;
		if (z.getLeft().getHeight() < z.getRight().getHeight()) {
			 y = (AVLNode) z.getRight();
			 a = (AVLNode) y.getLeft();
			 b = (AVLNode) y.getRight();
			 y.setLeft(z);
			 y.setRight(b);
			 z.setRight(a);
		}
		else {
			 y = (AVLNode) z.getLeft();
			 b = (AVLNode) y.getLeft();
			 a = (AVLNode) y.getRight();
			 y.setLeft(b);
			 y.setRight(z);
			 z.setLeft(a);
		}
		z.setParent(y);
		b.setParent(y);
		a.setParent(z);
		y.setParent(parent);
		updateNodeSize(z);
	    updateNodeSize(y);
		if (parent != null) {
			if (parent.getKey() < y.getKey()) parent.setRight(y);
			else parent.setLeft(y);
		}else {
			this.root = y;
		}
		
		balancing = fixHeights_R(z, y, a, b, balancing, type) + 1;

		return balancing;
	}
	

	private int doubleRotation(AVLNode z, int balancing, char type) {
		AVLNode parent = (AVLNode) z.getParent();
		AVLNode y;
		AVLNode a;
		AVLNode c;
		AVLNode d;
		if (z.getLeft().getHeight() < z.getRight().getHeight()) {
			 y = (AVLNode) z.getRight();
			 a = (AVLNode) y.getLeft();
			 d = (AVLNode) a.getRight();
			 c = (AVLNode) a.getLeft();
			 a.setLeft(z);
			 a.setRight(y);
			 z.setRight(c);
			 y.setLeft(d);
		}
		else {
			 y = (AVLNode) z.getLeft();
			 a = (AVLNode) y.getRight();
			 c = (AVLNode) a.getRight();
			 d = (AVLNode) a.getLeft();
			 a.setLeft(y);
			 a.setRight(z);
			 z.setLeft(c);
			 y.setRight(d);
		}
		z.setParent(a);
		y.setParent(a);
		c.setParent(z);
		d.setParent(y);
		a.setParent(parent);
		updateNodeSize(z);
	    updateNodeSize(y);
	    updateNodeSize(a);
		if (parent != null) {
			if (parent.getKey() < a.getKey()) parent.setRight(a);
			else parent.setLeft(a);

		}else {
			this.root = a;
		}

		balancing = fixHeights_DR(z, y, a, balancing, type) + 2;
		
		return balancing;
	}

	/**
	 * public String min()
	 *
	 * Returns the info of the item with the smallest key in the tree, or null if
	 * the tree is empty
	 */
	public String min() {
		if (!empty()) {
			IAVLNode node = this.getRoot();
			while(node.getLeft().getKey() != -1) {
				node = node.getLeft();
			}
			return node.getValue(); 
		}
		return null;
	}

	/**
	 * public String max()
	 *
	 * Returns the info of the item with the largest key in the tree, or null if the
	 * tree is empty
	 */
	public String max() {
		if (!empty()) {
			IAVLNode node = this.getRoot();
			while(node.getRight().getKey() != -1) {
				node = node.getRight();
			}
			return node.getValue(); 
		}
		return null;
	}

	/**
	 * public int[] keysToArray()
	 *
	 * Returns a sorted array which contains all keys in the tree, or an empty array
	 * if the tree is empty.
	 */
	public int[] keysToArray() {
		Stack<IAVLNode> stack = new Stack<IAVLNode>();
		int i = 0;
		int[] arr = new int[this.size()];
		//System.out.println("SIZE "+this.size());
		IAVLNode node = this.getRoot();
		stack.push(node);
		while (i < this.size()) {
			while (node.getKey() != -1) {
				stack.push(node);
				node = node.getLeft();
			}
			node = stack.pop();
			arr[i] = node.getKey();
			i++;
			if (i < this.size()) {
				if (node.getRight().getKey() != -1) {
					node = node.getRight();
					//stack.push(node);
				} else {
					node = stack.pop();
					arr[i] = node.getKey();
					i++;
					// node = node.getRight();
					if (node.getRight().getKey() == -1) {
						node = stack.pop();
						arr[i] = node.getKey();
						i++;

					}
					while (node.getRight().getKey() == -1) {
						node = stack.pop();
						arr[i] = node.getKey();
						i++;
					}
					node = node.getRight();

				}
					//node = node.getRight();
					//stack.push(node);
				
			}
		}
		return arr;

	}


	/**
	 * public String[] infoToArray()
	 *
	 * Returns an array which contains all info in the tree, sorted by their
	 * respective keys, or an empty array if the tree is empty.
	 */
	public String[] infoToArray() {
		Stack<IAVLNode> stack = new Stack<IAVLNode>();
		int i = 0;
		String[] arr = new String[this.size()];
		//System.out.println("SIZE "+this.size());
		IAVLNode node = this.getRoot();
		stack.push(node);
		while (i < this.size()) {
			while (node.getKey() != -1) {
				stack.push(node);
				node = node.getLeft();
			}
			node = stack.pop();
			arr[i] = node.getValue();
			i++;
			if (i < this.size()) {
				if (node.getRight().getKey() != -1) {
					node = node.getRight();
					//stack.push(node);
				} else {
					node = stack.pop();
					arr[i] = node.getValue();
					i++;
					// node = node.getRight();
					if (node.getRight().getKey() == -1) {
						node = stack.pop();
						arr[i] = node.getValue();
						i++;

					}
					while (node.getRight().getKey() == -1) {
						node = stack.pop();
						arr[i] = node.getValue();
						i++;
					}
					node = node.getRight();

				}
					//node = node.getRight();
					//stack.push(node);
				
			}
		}
		return arr;

	}

	/**
	 * public int size()
	 *
	 * Returns the number of nodes in the tree.
	 *
	 * precondition: none postcondition: none
	 */
	public int size() {
		AVLNode r = (AVLNode) this.root;
		return r.getNodeSize();
	}

	/**
	 * public int getRoot()
	 *
	 * Returns the root AVL node, or null if the tree is empty
	 *
	 * precondition: none postcondition: none
	 */
	public IAVLNode getRoot() {
		return this.root;
	}

	/**
	 * public string split(int x)
	 *
	 * splits the tree into 2 trees according to the key x. Returns an array [t1,
	 * t2] with two AVL trees. keys(t1) < x < keys(t2). precondition: search(x) !=
	 * null (i.e. you can also assume that the tree is not empty) postcondition:
	 * none
	 */
	public AVLTree[] split(int x) {
		AVLTree bigTree;
		AVLTree smallTree;
		IAVLNode node = this.root;
		while (node.getKey() != x && node.getKey() != -1) {
			if(node.getKey() > x) node=node.getLeft();
			else node=node.getRight();
		}
		AVLTree temp;
		bigTree= new AVLTree(node.getRight());
		smallTree= new AVLTree(node.getLeft());
		IAVLNode parent= node.getParent();
		while (parent != null) {
			IAVLNode parents_node = new AVLNode(parent.getKey(),parent.getValue());
			if (parent.getKey() > node.getKey()) {
				parent.getRight().setParent(null);
				temp=new AVLTree(parent.getRight());
				bigTree.join(parents_node, temp);
			}
			else {
				parent.getLeft().setParent(null);
				temp=new AVLTree(parent.getLeft());
				smallTree.join(parents_node, temp);
			}
			parent=parent.getParent();
		}
		
		AVLTree[] array = {smallTree, bigTree};
		return array;
	}

	/**
	 * public join(IAVLNode x, AVLTree t)
	 *
	 * joins t and x with the tree. Returns the complexity of the operation
	 * (|tree.rank - t.rank| + 1). precondition: keys(x,t) < keys() or keys(x,t) >
	 * keys(). t/tree might be empty (rank = -1). postcondition: none
	 */
	public int join(IAVLNode x, AVLTree t) {
		int cost = 1;
		int this_rank = -1;
		int t_rank = -1;
		AVLTree larger = null;
		AVLTree smaller = null;
		if (this.root != null) this_rank = this.root.getHeight();
		if (t.getRoot() != null) t_rank = t.getRoot().getHeight(); 
		cost = Math.abs(this_rank - t_rank) + 1;
		x.setHeight(Math.min(this_rank, t_rank) + 1);
		if (t_rank > this_rank) {
			larger = t;
			smaller = this;
		} else {
			larger = this;
			smaller = t;
		}
		if (t_rank == -1 || t.getRoot().getKey() == -1) {
			this.insert(x.getKey(), x.getValue());
			return cost;
		}else if (this_rank == -1 || this.getRoot().getKey() == -1) {
			t.insert(x.getKey(), x.getValue());
			this.root = t.getRoot();
			this.size = t.size + 1;
			t.root = null;
			t.size = 0;
			return cost;
		}
		  
		if (x.getKey() > smaller.getRoot().getKey()) { // small<x<big 
			IAVLNode temp = larger.getRoot();
			while (smaller.getRoot().getHeight() < temp.getHeight()) {
				temp = temp.getLeft();
			}
			IAVLNode parent = temp.getParent();
			if (parent != null) parent.setLeft(x);
			x.setRight(temp);
			temp.setParent(x);
			x.setLeft(smaller.getRoot());
			smaller.getRoot().setParent(x);
			x.setParent(parent);
		}

		else { // big<x<small
			IAVLNode temp = larger.getRoot();
			while (smaller.getRoot().getHeight() < temp.getHeight()) {
				temp = temp.getRight();
			}
			IAVLNode parent = temp.getParent();
			if (parent != null) parent.setRight(x);
			x.setLeft(temp);
			temp.setParent(x);
			x.setRight(smaller.getRoot());
			smaller.getRoot().setParent(x);
			x.setParent(parent);
		}
		this.size = larger.size + smaller.size +1;
		updateNodeSize ((AVLNode) x);
		this.root = larger.root;
		Balance (x.getRight(), larger.getRoot());
		while (x != null) {
			updateNodeSize ((AVLNode) x);
			x = x.getParent();
		}
		return cost;
	}
	
	public void Balance(IAVLNode x, IAVLNode The_root) {
		IAVLNode z = x.getParent();
		while (x.getKey() != The_root.getKey() && x.getHeight() == z.getHeight()) { 
			if (hightDef(z, x) == 1) { // Case 1
				promote(z, 0);
				x = z;
				z = z.getParent();
			} else { // Case 2 or Case 3, height deference = 2
				if (z.getKey() < x.getKey()) {
					if (hightDef(x, x.getRight()) == 2 && hightDef(x, x.getLeft()) == 1) { // case 2
						singleRotation((AVLNode) z, 0, 'I');
						break;
					} else { // case 3
						doubleRotation((AVLNode) z, 0, 'I');
						break;
						}
					}else {
						if (hightDef(x, x.getLeft()) == 2 && hightDef(x, x.getRight()) == 1) { // case 2
							singleRotation((AVLNode) z, 0, 'I');
							break;
						} else { // case 3
							doubleRotation((AVLNode) z, 0, 'I');
							break;
						}
				}
			}
		}
	}

	/**
	 * public interface IAVLNode ! Do not delete or modify this - otherwise all
	 * tests will fail !
	 */
	public interface IAVLNode {
		public int getKey(); // returns node's key (for virtuval node return -1)

		public String getValue(); // returns node's value [info] (for virtuval node return null)

		public void setLeft(IAVLNode node); // sets left child

		public IAVLNode getLeft(); // returns left child (if there is no left child return null)

		public void setRight(IAVLNode node); // sets right child

		public IAVLNode getRight(); // returns right child (if there is no right child return null)

		public void setParent(IAVLNode node); // sets parent

		public IAVLNode getParent(); // returns the parent (if there is no parent return null)

		public boolean isRealNode(); // Returns True if this is a non-virtual AVL node

		public void setHeight(int height); // sets the height of the node

		public int getHeight(); // Returns the height of the node (-1 for virtual nodes)
	}

	/**
	 * public class AVLNode
	 *
	 * If you wish to implement classes other than AVLTree (for example AVLNode), do
	 * it in this file, not in another file. This class can and must be modified.
	 * (It must implement IAVLNode)
	 */
	
	public class AVLNode implements IAVLNode {
		  private int key; 
		  private String value;
		  private AVLNode right;
		  private AVLNode left;
		  private AVLNode parent;
		  private int height;
		  private int rank;
		  private int size;
		  
		  public AVLNode(int key, String value) {  //constructor for node
			  this.key = key;
			  this.value = value;
			  AVLNode r = new AVLNode();
			  AVLNode l = new AVLNode();
			  this.right = r;
			  this.left = l;
			  r.parent = this;
			  l.parent = this;
			  this.size=1;
		  }

		public AVLNode() { //constructor for virtual node
			  this.key = -1;
			  this.value = null;
			  this.height = -1;
			  this.rank = -1;
			  this.size =0;
		  }
			public int getKey()
			{
				return this.key; 
			}
			public void setKey(int i)
			{
				this.key=i;
			}
			public String getValue()
			{
				return this.value; 
			}
			public void setValue(String value2) {
				this.value=value2;
					
			}
			public int getNodeSize() {
				return this.size; 
			}
			public void setNodeSize(int i) {
				this.size=i;
			}
			
			public void setLeft(IAVLNode node)
			{
				this.left=(AVLNode)node; 
			}
			public IAVLNode getLeft()
			{
				return this.left; 
			}
			public void setRight(IAVLNode node)
			{
				this.right=(AVLNode)node; 
			}
			public IAVLNode getRight()
			{
				return this.right; 
			}
			public void setParent(IAVLNode node)
			{
				this.parent=(AVLNode)node; 
			}
			public IAVLNode getParent()
			{
				return this.parent; 
			}
			// Returns True if this is a non-virtual AVL node
			public boolean isRealNode()
			{
				if (this.key != -1) return true;
				return false;
			}
	    public void setHeight(int height)
	    {
	      this.height = height;
	    }
	    public int getHeight()
	    {
	      return this.height; 
	    }
	    public void setRank(int rank)
	    {
	      this.rank = rank;
	    }
	    public int getRank()
	    {
	      return this.rank; 
	    }
	    public void setSize(int size)
	    {
	      this.size = size;
	    }
	    public int getSize()
	    {
	      return this.size; 
	    }
	  }

}

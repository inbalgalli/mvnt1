import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import AVLTree.AVLNode;
import AVLTree.IAVLNode;

/**
 *
 * AVLTree
 *
 * An implementation of a AVL Tree with distinct integer keys and info
 *
 */

public class AVLTree {
	private AVLNode root;
	
	public AVLTree() {
		this(null);
	}
	public AVLTree(AVLNode root) {
		this.root = root;
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
			return true;
		}
		return false; 
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
		IAVLNode z = searchPlace(k);
		if (z.getKey() == k) return -1;
		IAVLNode x = new AVLNode (k,i);
		x.setHeight(0);
		int rebalancing = 0;
		if (!z.getRight().isRealNode() && !z.getLeft().isRealNode()) { //the parent z is a leaf
			placeSon (z, x);
			rebalancing = promote(z, rebalancing);
			if (z.getHeight() == z.getParent().getHeight()) { //problem going up
				x = z;
				z = z.getParent();
				while (x.getHeight() == z.getHeight()) { 
					
					if (hightDef (z, x) == 1) { //Case 1
						rebalancing = promote(z, rebalancing);
						x = z;
						z = z.getParent();
					}else { //Case 2 or Case 3, height deference = 2 
						if (hightDef(x, x.getRight()) == 2 && hightDef(x, x.getLeft()) == 1) { //case 2
							
						}
						
					}
				}
			
			
			
			}else{
				return rebalancing; 
			}
				

		}else { //the parent z is not a leaf - tree stays valid
			placeSon (z, x);
			return rebalancing;
		}
		
		
	}
	
	private int hightDef (IAVLNode parent, IAVLNode son) {
		if (parent.getLeft().getKey() == son.getKey()) {
			return parent.getHeight() - parent.getRight().getHeight();
 		}else {
 			return parent.getHeight() - parent.getLeft().getHeight();
 		}
	}
	
	private int promote (IAVLNode node, int cnt) {
		int h = node.getHeight();
		node.setHeight(h+1);
		return cnt+1;
	}
	
	private int demote (IAVLNode node, int cnt) {
		int h = node.getHeight();
		node.setHeight(h+1);
		return cnt+1;
	}	
	
	private void placeSon (IAVLNode parent, IAVLNode child) {
		if (child.getKey() > parent.getKey()) { //set as right son
			parent.setRight(child);
		}else { //set as left son
			parent.setLeft(child);
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
	public int delete(int k) {
		AVLNode node = root;
		while (node.getKey() != -1) {
			if (node.getKey() == k) break;
			if (node.getKey() > k) node=(AVLNode) node.getLeft();
			else node=(AVLNode) node.getRight();  
		}
		if (node.getKey() == -1) return 0; //there isn't any node with key k in the tree
		AVLNode parent = (AVLNode) node.getParent();
		help_delete(node,parent, k);
		int height_parent = parent.getHeight();
		int height_rightSon = parent.getRight().getHeight();
		int height_leftSon = parent.getLeft().getHeight();
		int num_rotates =0;
		while (height_parent != Math.max(height_rightSon, height_leftSon) +1 ||
				Math.abs(height_rightSon - height_leftSon) > 1) { // we balanced the tree 
			if (height_leftSon == height_rightSon) {
				parent.setHeight(height_parent-1);
				parent=(AVLNode)parent.getParent();
				
			}
			else {
				AVLNode minSon;
				if (height_rightSon > height_leftSon) minSon = (AVLNode) parent.getLeft();
				else minSon = (AVLNode) parent.getRight();
				int min_rightGrandson_height = minSon.getRight().getHeight();
				int min_leftGrandson_height = minSon.getLeft().getHeight();
				if (min_rightGrandson_height == min_leftGrandson_height ) {
					parent = singleRotation(parent);
					num_rotates +=1;
					return num_rotates;
				}
				if ((minSon == (AVLNode) parent.getLeft() &&  min_rightGrandson_height > min_leftGrandson_height) ||
					(minSon == (AVLNode) parent.getRight() && min_rightGrandson_height < min_leftGrandson_height )) {
					parent = singleRotation(parent);
					num_rotates +=1;
				}
				else {
					parent = doubleRotation(parent);
					num_rotates +=2;
				}
				
			}
			height_parent = parent.getHeight();
			height_rightSon = parent.getRight().getHeight();
			height_leftSon = parent.getLeft().getHeight();
		}	   
			   return num_rotates;	// to be replaced by student code
		  
	}
	
	private void help_delete(AVLNode node,AVLNode parent, int k) {
		if (parent != null) {
			if (node.getRight().getKey() == -1 && node.getLeft().getKey()==-1) { // node is a leaf
				AVLNode empty= new AVLNode();
				help_delete_2(parent, empty, k);
			}
			else {
				if (node.getRight().getKey() == -1) { // node has only left son
					AVLNode left = (AVLNode) node.getLeft();
					help_delete_2(parent, left, k);
				}
				if (node.getLeft().getKey() == -1) { // node has only right son
					AVLNode right = (AVLNode) node.getRight();
					help_delete_2(parent, right, k);
				}
				if (node.getRight().getKey() != -1 && node.getLeft().getKey()!=-1 ) { //node has 2 sons
				   AVLNode successor=getSuccessor(node);
				   delete(successor.getKey()); //worst case will stop at the fourth if 
				   help_delete_2(parent, successor, k);
				}
			}
		}
		else { //change the root
			AVLNode successor=getSuccessor(node);
			help_delete(successor,(AVLNode) successor.getParent(), successor.getKey()); //worst case will stop at the fourth if 
			successor.setRight(node.getRight());
			successor.setLeft(node.getLeft());
			node.getRight().setParent(successor);
			node.getLeft().setParent(successor);
			this.root=successor;
		}
	}
		
	private void help_delete_2 (AVLNode parent, AVLNode son, int k) {
		if (parent.getRight().getKey() == k) {
			parent.setRight(son);
		    son.setParent(parent);
		}
		else {
			parent.setLeft(son);
		    son.setParent(parent);
		}
	}
	
	private IAVLNode singleRotation(IAVLNode z) {
		IAVLNode parent = z.getParent();
		IAVLNode y;
		IAVLNode a;
		IAVLNode b;
		if (z.getLeft().getHeight() > z.getRight().getHeight()) {
			 y = z.getRight();
			 a = y.getLeft();
			 b = y.getRight();
			 y.setLeft(z);
			 y.setRight(b);
			 z.setRight(a);
		}
		else {
			 y = z.getLeft();
			 b = y.getLeft();
			 a = y.getRight();
			 y.setLeft(b);
			 y.setRight(z);
			 z.setLeft(a);
		}
		z.setParent(y);
		b.setParent(y);
		a.setParent(z);
		y.setParent(parent);
		if (parent != null) {
			if (parent.getKey() > y.getKey()) parent.setRight(y);
			else parent.setLeft(y);
		}
		if (a.getHeight()==b.getHeight()) {
			z.setHeight(z.getHeight() -1);
			y.setHeight(y.getHeight() +1);
		}
		else z.setHeight(z.getHeight() -2);
		return y;
	}
	
	private AVLNode doubleRotation(AVLNode z) {
		AVLNode parent = (AVLNode)z.getParent();
		AVLNode y;
		AVLNode a;
		AVLNode c;
		AVLNode d;
		if (z.getLeft().getHeight() > z.getRight().getHeight()) {
			 y =(AVLNode) z.getRight();
			 a =(AVLNode) y.getLeft();
			 d =(AVLNode) a.getRight();
			 c =(AVLNode) a.getLeft();
			 a.setLeft(z);
			 a.setRight(y);
			 z.setRight(c);
			 y.setLeft(d);
		}
		else {
			 y =(AVLNode) z.getLeft();
			 a =(AVLNode) y.getRight();
			 c =(AVLNode) a.getRight();
			 d =(AVLNode) a.getLeft();
			 a.setLeft(y);
			 a.setRight(z);
			 z.setLeft(a);
			 z.setLeft(c);
			 y.setRight(d);
		}
		z.setParent(a);
		y.setParent(a);
		c.setParent(z);
		d.setParent(y);
		y.setParent(parent);
		if (parent != null) {
			if (parent.getKey() > a.getKey()) parent.setRight(a);
			else parent.setLeft(a);
		}
		a.setHeight(a.getHeight() +1);
		z.setHeight(z.getHeight() -2);
		y.setHeight(y.getHeight() -1);
		
		return a;
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
		IAVLNode node = this.getRoot();
		stack.push(node);
		while (!stack.empty()) {
			while (node.getLeft().getKey() != -1) {
				node = node.getLeft();
				stack.push(node);
			}
			arr[i] = node.getKey();
			i++;
			node = stack.pop();
			if (node.getRight().getKey() != -1) {
				node = node.getRight();
				stack.push(node);
			}else {
				node = stack.pop();
				arr[i] = node.getKey();
				i++;
				if (node.getRight().getKey() == -1) {
					while (node.getRight().getKey() == -1) {
						node = stack.pop();
						arr[i] = node.getKey();
						i++;
						node = node.getRight();
					}
				}else {
					node = node.getRight();
					stack.push(node);
				}
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
		String[] arr = new String[42]; // to be replaced by student code
		return arr; // to be replaced by student code
	}

	/**
	 * public int size()
	 *
	 * Returns the number of nodes in the tree.
	 *
	 * precondition: none postcondition: none
	 */
	public int size() {
		AVLNode node = (AVLNode) this.getRoot();
		if (node == null) {
			return 0;
		}
		return node.size; // to be replaced by student code
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
		return null;
	}

	/**
	 * public join(IAVLNode x, AVLTree t)
	 *
	 * joins t and x with the tree. Returns the complexity of the operation
	 * (|tree.rank - t.rank| + 1). precondition: keys(x,t) < keys() or keys(x,t) >
	 * keys(). t/tree might be empty (rank = -1). postcondition: none
	 */
	public int join(IAVLNode x, AVLTree t) {
		return 0;
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
		  private AVLNode empty_node = new AVLNode();  //empty node in the end of the tree
		  private int height;
		  private int rank;
		  private int size;
		  
		  public AVLNode(int key, String value) {  //constructor for node
			  this.key = key;
			  this.value = value;
			  this.right = empty_node;
			  this.left = empty_node;
		  }
		  public AVLNode() { //constructor for virtual node
			  this.key = -1;
			  this.value = null;
			  this.rank = -1;
		  }
			public int getKey()
			{
				return this.key; 
			}
			public String getValue()
			{
				return this.value; 
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

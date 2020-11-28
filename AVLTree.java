import java.util.ArrayList;
import java.util.List;

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
		return 42; // to be replaced by student code
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
		return 42; // to be replaced by student code
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
		List<IAVLNode> stack = new ArrayList<IAVLNode>();
		int i = 0;
		int stackCNT = 0;
		int[] arr = new int[this.size()];
		IAVLNode node = this.getRoot();
		stack.add(node);
		while (!stack.isEmpty()) {
			while (node.getLeft().getKey() != -1) {
				node = node.getLeft();
				stack.add(node);
				stackCNT++;
			}
			arr[i] = node.getKey();
			i++;
			node = stack.remove(stackCNT);
			stackCNT--;
			if (node.getRight().getKey() != -1) {
				node = node.getRight();
				stack.add(node);
				stackCNT++;
			}else {
				node = stack.remove(stackCNT);
				stackCNT--;
				arr[i] = node.getKey();
				i++;
				if (node.getRight().getKey() == -1) {
					while (node.getRight().getKey() == -1) {
						node = stack.remove(stackCNT);
						stackCNT--;
						arr[i] = node.getKey();
						i++;
						node = node.getRight();
					}
				}else {
					node = node.getRight();
					stack.add(node);
					stackCNT++;
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

package study.chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import study.chapter1.Queue;

public class RedBlackTreeSTL<Key extends Comparable<Key>, Value> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private int node_count;
	private Node header;

	private Node root() {
		return header.parent;
	}

	private class Node {
		private Key key;
		private Value val;
		private Node left, right, parent;
		private boolean color;

		public Node() {
		}

		public Node(Key key, Value val) {
			this.key = key;
			this.val = val;
		}

		public String toString() {
			return this.key + " " + this.val;
		}
	}

	private boolean colorOf(Node x) {
		return (x == null ? BLACK : x.color);
	}

	public RedBlackTreeSTL() {
		header = new Node();
		header.color = RED;
		header.parent = null;
		header.left = header;
		header.right = header;
	}

	private boolean isRed(Node x) {
		if (x == null)
			return false;
		return x.color == RED;
	}

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 * 
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() {
		return node_count;
	}

	/**
	 * Is this symbol table empty?
	 * 
	 * @return {@code true} if this symbol table is empty and {@code false}
	 *         otherwise
	 */
	public boolean isEmpty() {
		return node_count == 0;
	}

	/***************************************************************************
	 * Standard BST search.
	 ***************************************************************************/

	/**
	 * Returns the value associated with the given key.
	 * 
	 * @param key
	 *            the key
	 * @return the value associated with the given key if the key is in the
	 *         symbol table and {@code null} if the key is not in the symbol
	 *         table
	 * @throws IllegalArgumentException
	 *             if {@code key} is {@code null}
	 */
	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		return get(root(), key);
	}

	// value associated with the given key in subtree rooted at x; null if no
	// such key
	private Value get(Node x, Key key) {
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0)
				x = x.left;
			else if (cmp > 0)
				x = x.right;
			else
				return x.val;
		}
		return null;
	}

	private Node getNode(Key key) {
		Node p = root();
		while (p != null) {
			int cmp = key.compareTo(p.key);
			if (cmp < 0)
				p = p.left;
			else if (cmp > 0)
				p = p.right;
			else
				return p;
		}
		return null;
	}

	/**
	 * Does this symbol table contain the given key?
	 * 
	 * @param key
	 *            the key
	 * @return {@code true} if this symbol table contains {@code key} and
	 *         {@code false} otherwise
	 * @throws IllegalArgumentException
	 *             if {@code key} is {@code null}
	 */
	public boolean contains(Key key) {
		return get(key) != null;
	}

	public void put(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException(
					"first argument to put() is null");
		if (val == null) {
			delete(key, val);
			return;
		}
		int cmp;
		Node y = header;
		Node x = root();
		while (x != null) {
			y = x;
			cmp = key.compareTo(x.key);
			if (cmp < 0)
				x = x.left;
			else if (cmp > 0)
				x = x.right;
			else {
				x.val = val;
				return;
			}
		}
		insert(y, key, val);
	}

	private void insert(Node y, Key key, Value val) {
		Node z;
		if (y == header || key.compareTo(y.key) < 0) {
			z = new Node(key, val);
			y.left = z;
			if (y == header) {
				header.parent = z;
				header.right = z;
			} else if (y == header.left) {
				header.left = z;
			}
		} else {
			z = new Node(key, val);
			y.right = z;
			if (y == header.right)
				header.right = z;
		}
		z.parent = y;
		z.left = null;
		z.right = null;
		rebalance(z);
		++node_count;
	}

	/***************************************************************************
	 * Red-black tree deletion.
	 ***************************************************************************/

	/**
	 * Removes the smallest key and associated value from the symbol table.
	 * 
	 * @throws NoSuchElementException
	 *             if the symbol table is empty
	 */
	public void deleteMin() {
		if (isEmpty())
			throw new NoSuchElementException("BST underflow");
		erase(header.left);
	}

	/**
	 * Removes the largest key and associated value from the symbol table.
	 * 
	 * @throws NoSuchElementException
	 *             if the symbol table is empty
	 */
	public void deleteMax() {
		if (isEmpty())
			throw new NoSuchElementException("BST underflow");

		erase(header.right);
	}

	public Value delete(Key key, Value val) {
		Node p = getNode(key);
		if (p == null)
			return null;

		Value oldValue = p.val;
		erase(p);
		return oldValue;
	}

	public void erase(Node x) {
		rebalance_for_erase(x);
		--node_count;
	}

	private void rotateLeft(Node x) {
		Node y = x.right;
		x.right = y.left;
		if (y.left != null)
			y.left.parent = x;
		y.parent = x.parent;

		if (x == root())
			header.parent = y;
		else if (x == x.parent.left)
			x.parent.left = y;
		else
			x.parent.right = y;
		y.left = x;
		x.parent = y;
	}

	private void rotateRight(Node x) {
		Node y = x.left;
		x.left = y.right;
		if (y.right != null)
			y.right.parent = x;
		y.parent = x.parent;

		if (x == root())
			header.parent = y;
		else if (x == x.parent.left)
			x.parent.left = y;
		else
			x.parent.right = y;
		y.right = x;
		x.parent = y;
	}

	// restore red-black tree invariant
	private void rebalance(Node x) {
		x.color = RED;
		while (x != root() && x.parent.color == RED) {
			if (x.parent == x.parent.parent.left) {
				Node y = x.parent.parent.right;
				if (y != null && y.color == RED) {
					x.parent.color = BLACK;
					y.color = BLACK;
					x.parent.parent.color = RED;
					x = x.parent.parent;
				} else {
					if (x == x.parent.right) {
						x = x.parent;
						rotateLeft(x);
					}
					x.parent.color = BLACK;
					x.parent.parent.color = RED;
					rotateRight(x.parent.parent);
				}
			} else {
				Node y = x.parent.parent.left;
				if (y != null && y.color == RED) {
					x.parent.color = BLACK;
					y.color = BLACK;
					x.parent.parent.color = RED;
					x = x.parent.parent;
				} else {
					if (x == x.parent.left) {
						x = x.parent;
						rotateRight(x);
					}
					x.parent.color = BLACK;
					x.parent.parent.color = RED;
					rotateLeft(x.parent.parent);
				}
			}
		}
		root().color = BLACK;
	}

	private void rebalance_for_erase(Node z) {
		Node y = z;
		Node x = null;
		Node x_parent = null;
		if (y.left == null) // z has at most one non-null child. y==z
			x = y.right; // x might be null
		else if (y.right == null) // z has exactly one non-null child. y==z
			x = y.left; // x is not null
		else { // z has two non-null children. Set y to
			y = y.right; // z's successor. x might be null
			while (y.left != null)
				y = y.left;
			x = y.right;
		}
		if (y != z) { // relink y in place of z. y is z's successor
			z.left.parent = y;
			y.left = z.left;
			if (y != z.right) {
				x_parent = y.parent;
				if (x != null)
					x.parent = y.parent;
				y.parent.left = x;
				y.right = z.right;
				z.right.parent = y;
			} else
				x_parent = y;
			if (z == root())
				header.parent = y;
			else if (z.parent.left == z)
				z.parent.left = y;
			else
				z.parent.right = y;
			y.parent = z.parent;

			boolean tmp = y.color; // swap(y.color,z.color)
			y.color = z.color;
			z.color = tmp;

			y = z;
			// y now points to node to be actually deleted
		} else {
			x_parent = y.parent;
			if (x != null)
				x.parent = y.parent;
			if (z == root())
				header.parent = x;
			else if (z.parent.left == z)
				z.parent.left = x;
			else
				z.parent.right = x;
			if (header.left == z)
				if (z.right == null)
					header.left = z.parent;
				else
					header.left = min(x);
			if (header.right == z)
				if (z.left == null)
					header.right = z.parent;
				else
					header.right = max(x);
		}
		if (y.color != RED) {
			while (x != root() && (colorOf(x) == BLACK))
				if (x == x_parent.left) {
					Node w = x_parent.right;
					if (w.color == RED) {
						w.color = BLACK;
						x_parent.color = RED;
						rotateLeft(x_parent);
						w = x_parent.right;
					}
					if (colorOf(w.left) == BLACK && colorOf(w.right) == BLACK) {
						w.color = RED;
						x = x_parent;
						x_parent = x_parent.parent;
					} else {
						if (colorOf(w.right) == BLACK) {
							if (w.left != null)
								w.left.color = BLACK;
							w.color = RED;
							rotateRight(w);
							w = x_parent.right;
						}
						w.color = x_parent.color;
						x_parent.color = BLACK;
						if (w.right != null)
							w.right.color = BLACK;
						rotateLeft(x_parent);
						break;
					}
				} else {
					Node w = x.parent.left;
					if (w.color == RED) {
						w.color = BLACK;
						x_parent.color = RED;
						rotateRight(x_parent);
						w = x_parent.left;
					}
					if (colorOf(w.left) == BLACK && colorOf(w.right) == BLACK) {
						w.color = RED;
						x = x_parent;
						x_parent = x_parent.parent;
					} else {
						if (colorOf(w.left) == BLACK) {
							if (w.right != null)
								w.right.color = BLACK;
							w.color = RED;
							rotateLeft(w);
							w = x_parent.left;
						}
						w.color = x_parent.color;
						x_parent.color = BLACK;
						if (w.left != null)
							w.left.color = BLACK;
						rotateRight(x_parent);
						break;
					}
				}
			if (x != null)
				x.color = BLACK;
		}

	}

	/***************************************************************************
	 * Utility functions.
	 ***************************************************************************/

	/**
	 * Returns the height of the BST (for debugging).
	 * 
	 * @return the height of the BST (a 1-node tree has height 0)
	 */
	public int height() {
		return height(header.parent);
	}

	private int height(Node x) {
		if (x == null)
			return -1;
		return 1 + Math.max(height(x.left), height(x.right));
	}

	/***************************************************************************
	 * Ordered symbol table methods.
	 ***************************************************************************/

	/**
	 * Returns the smallest key in the symbol table.
	 * 
	 * @return the smallest key in the symbol table
	 * @throws NoSuchElementException
	 *             if the symbol table is empty
	 */
	public Key min() {
		if (isEmpty())
			throw new NoSuchElementException(
					"calls min() with empty symbol table");
		return header.left.key;
	}

	// the smallest key in subtree rooted at x; null if no such key
	private Node min(Node x) {
		// assert x != null;
		if (x.left == null)
			return x;
		else
			return min(x.left);
	}

	/**
	 * Returns the largest key in the symbol table.
	 * 
	 * @return the largest key in the symbol table
	 * @throws NoSuchElementException
	 *             if the symbol table is empty
	 */
	public Key max() {
		if (isEmpty())
			throw new NoSuchElementException(
					"calls max() with empty symbol table");
		return header.right.key;
	}

	// the largest key in the subtree rooted at x; null if no such key
	private Node max(Node x) {
		// assert x != null;
		if (x.right == null)
			return x;
		else
			return max(x.right);
	}

	/**
	 * Returns the largest key in the symbol table less than or equal to
	 * {@code key}.
	 * 
	 * @param key
	 *            the key
	 * @return the largest key in the symbol table less than or equal to
	 *         {@code key}
	 * @throws NoSuchElementException
	 *             if there is no such key
	 * @throws IllegalArgumentException
	 *             if {@code key} is {@code null}
	 */
	public Key floor(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to floor() is null");
		if (isEmpty())
			throw new NoSuchElementException(
					"calls floor() with empty symbol table");
		Node x = floor(root(), key);
		if (x == null)
			return null;
		else
			return x.key;
	}

	// the largest key in the subtree rooted at x less than or equal to the
	// given key
	private Node floor(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		if (cmp < 0)
			return floor(x.left, key);
		Node t = floor(x.right, key);
		if (t != null)
			return t;
		else
			return x;
	}

	/**
	 * Returns the smallest key in the symbol table greater than or equal to
	 * {@code key}.
	 * 
	 * @param key
	 *            the key
	 * @return the smallest key in the symbol table greater than or equal to
	 *         {@code key}
	 * @throws NoSuchElementException
	 *             if there is no such key
	 * @throws IllegalArgumentException
	 *             if {@code key} is {@code null}
	 */
	public Key ceiling(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to ceiling() is null");
		if (isEmpty())
			throw new NoSuchElementException(
					"calls ceiling() with empty symbol table");
		Node x = ceiling(root(), key);
		if (x == null)
			return null;
		else
			return x.key;
	}

	// the smallest key in the subtree rooted at x greater than or equal to the
	// given key
	private Node ceiling(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		if (cmp > 0)
			return ceiling(x.right, key);
		Node t = ceiling(x.left, key);
		if (t != null)
			return t;
		else
			return x;
	}

	public String toString() {
		int maxLevel = height();
		String s = "";
		if (isEmpty())
			return s;
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(root());
		for (int level = 0; level <= maxLevel; level++) {
			int floor = maxLevel - level;
			int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
			int firstSpaces = (int) Math.pow(2, (floor)) - 1;
			int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

			for (int i = 0; i < firstSpaces; i++)
				s += " ";
			List<Node> newNodes = new ArrayList<Node>();
			for (Node node : nodes) {
				if (node != null) {
					s += node.key; // + " " + (node.color ? "Red" : "Black");
					newNodes.add(node.left);
					newNodes.add(node.right);
				} else {
					newNodes.add(null);
					newNodes.add(null);
					s += " ";
				}

				for (int i = 0; i < betweenSpaces; i++)
					s += " ";
			}
			s += "\n";
			for (int i = 1; i <= endgeLines; i++) {
				for (int j = 0; j < nodes.size(); j++) {
					for (int k = 0; k < firstSpaces - i; k++)
						s += " ";
					if (nodes.get(j) == null) {
						for (int k = 0; k < endgeLines + endgeLines + i
								+ 1; k++)
							s += " ";
						continue;
					}
					if (nodes.get(j).left != null)
						s += "/";
					else
						s += " ";
					for (int k = 0; k < i + i - 1; k++)
						s += " ";
					if (nodes.get(j).right != null)
						s += "\\";
					else
						s += " ";
					for (int k = 0; k < endgeLines + endgeLines - i; k++)
						s += " ";
				}
				s += "\n";

			}
			nodes = newNodes;
		}
		return s;
	}

	Node increment(Node node) {
		if (node.right != null) {
			node = node.right;
			while (node.left != null)
				node = node.left;
		}

		else {
			Node y = node.parent;
			while (node == y.right) {
				node = y;
				y = y.parent;
			}

			if (node.right != y)
				node = y;
		}
		return node;
	}

	Node decrement(Node node) {
		if (node.color == RED && node.parent.parent == node)
			node = node.right;
		else if (node.left != null) {
			Node y = node.left;
			while (y.right != null)
				y = y.right;
			node = y;
		}

		else {
			Node y = node.parent;
			while (node == y.left) {
				node = y;
				y = y.parent;
			}
			node = y;
		}
		return node;
	}

	/***************************************************************************
	 * Range count and range search.
	 ***************************************************************************/

	/**
	 * Returns all keys in the symbol table as an {@code Iterable}. To iterate
	 * over all of the keys in the symbol table named {@code st}, use the
	 * foreach notation: {@code for (Key key : st.keys())}.
	 * 
	 * @return all keys in the symbol table as an {@code Iterable}
	 */
	public Iterable<Key> keys() {
		if (isEmpty())
			return new Queue<Key>();
		return keys(min(), max());
	}

	/**
	 * Returns all keys in the symbol table in the given range, as an
	 * {@code Iterable}.
	 *
	 * @param lo
	 *            minimum endpoint
	 * @param hi
	 *            maximum endpoint
	 * @return all keys in the sybol table between {@code lo} (inclusive) and
	 *         {@code hi} (inclusive) as an {@code Iterable}
	 * @throws IllegalArgumentException
	 *             if either {@code lo} or {@code hi} is {@code null}
	 */
	public Iterable<Key> keys(Key lo, Key hi) {
		if (lo == null)
			throw new IllegalArgumentException(
					"first argument to keys() is null");
		if (hi == null)
			throw new IllegalArgumentException(
					"second argument to keys() is null");

		Queue<Key> queue = new Queue<Key>();
		// if (isEmpty() || lo.compareTo(hi) > 0) return queue;
		keys(root(), queue, lo, hi);
		return queue;
	}

	// add the keys between lo and hi in the subtree rooted at x
	// to the queue
	private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
		if (x == null)
			return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if (cmplo < 0)
			keys(x.left, queue, lo, hi);
		if (cmplo <= 0 && cmphi >= 0)
			queue.enqueue(x.key);
		if (cmphi > 0)
			keys(x.right, queue, lo, hi);
	}

	/***************************************************************************
	 * Check integrity of red-black tree data structure.
	 ***************************************************************************/
	private boolean check() {
		if (!isBST())
			System.out.println("Not in symmetric order");
		if (!isRB())
			System.out.println("Not a RB tree");
		if (!isBalanced())
			System.out.println("Not balanced");
		if (!is234())
			System.out.println("Not a 2-3-4 tree");
		return isBST() && isRB() && isBalanced() && is234();
	}

	// does this binary tree satisfy symmetric order?
	// Note: this test also ensures that data structure is a binary tree since
	// order is strict
	private boolean isBST() {
		return isBST(root(), null, null);
	}

	// is the tree rooted at x a BST with all keys strictly between min and max
	// (if min or max is null, treat as empty constraint)
	// Credit: Bob Dondero's elegant solution
	private boolean isBST(Node x, Key min, Key max) {
		if (x == null)
			return true;
		if (min != null && x.key.compareTo(min) <= 0)
			return false;
		if (max != null && x.key.compareTo(max) >= 0)
			return false;
		return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
	}

	// Does the tree have no red right links, and at most one (left)
	// red links in a row on any path?
	private boolean isRB() {
		return isRB(root());
	}

	private boolean isRB(Node x) {
		if (x == null)
			return true;
		if (x != root() && isRed(x) && isRed(x.left))
			return false;
		return isRB(x.left) && isRB(x.right);
	}

	// do all paths from root to leaf have same number of black edges?
	private boolean isBalanced() {
		int black = 0; // number of black links on path from root to min
		Node x = root();
		while (x != null) {
			if (!isRed(x))
				black++;
			x = x.left;
		}
		return isBalanced(root(), black);
	}

	// does every path from the root to a leaf have the given number of black
	// links?
	private boolean isBalanced(Node x, int black) {
		if (x == null)
			return black == 0;
		if (!isRed(x))
			black--;
		return isBalanced(x.left, black) && isBalanced(x.right, black);
	}

	// Does the tree have no red right links, and at most one (left)
	// red links in a row on any path?
	private boolean is234() {
		return is234(root());
	}

	private boolean is234(Node x) {
		if (x == null)
			return true;
		if (isRed(x) && (isRed(x.left) || isRed(x.right)))
			return false;
		return is234(x.left) && is234(x.right);
	}

	public static void main(String[] args) {
		RedBlackTreeSTL<String, Integer> st = new RedBlackTreeSTL<String, Integer>();
		String input = "S E A R C H E X A M P L E";
		String[] keys = input.split(" ");
		for (int i = 0; i < keys.length; i++) {
			String key = keys[i];
			st.put(key, i);
			System.out.println(st);
			st.check();
		}

		// for (String s : st.levelOrder())
		// System.out.println(s + " " + st.get(s));

		while (!st.isEmpty()) {
			st.deleteMin();
			st.check();
			System.out.println(st);
		}

		for (String s : st.keys())
			System.out.println(s + " " + st.get(s));
	}

}

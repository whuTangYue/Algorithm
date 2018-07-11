package study.chapter2;

import java.util.LinkedList;
import java.util.Queue;

import study.chapter1.Deque;


//using deque , so it cost extra space
public class LinkedMaxPQ<T extends Comparable<T>> {
	private Node root;
	private Deque<Node> deque = new Deque<Node>();
	private int N;

	private class Node {
		T item;
		Node parent;
		Node lchild, rchild;
	}

	public LinkedMaxPQ() {
		N = 0;
		root = new Node();
		deque.addLast(root);
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public void add(T t) {
		if (isEmpty()) {
			root.item = t;
			N++;
			return;
		}
		Node newNode = new Node();
		newNode.item = t;
		while (!deque.isEmpty()) {
			Node tmp = deque.peek();
			if (tmp.lchild == null) {
				tmp.lchild = newNode;
				newNode.parent = tmp;
				deque.addLast(tmp.lchild);
				N++;
				swim(newNode);
				return;
			}
			if (tmp.rchild == null) {
				tmp.rchild = newNode;
				newNode.parent = tmp;
				deque.addLast(tmp.rchild);
				N++;
				swim(newNode);
				return;
			}
			deque.removeFirst();
		}
	}

	public T delMax() {
		if (isEmpty()) {
			return null;
		} else if (N == 1) {
			N--;
			return root.item;
		}
		T max = root.item;
		Node last = deque.removeLast();
		if (last.parent.rchild == last) {
			deque.addFirst(last.parent);
		}
		exch(last, root);
		if (last.parent.lchild == last) {
			last.parent.lchild = null;
		} else {
			last.parent.rchild = null;
		}
		last.parent = null;
		last = null;
		N--;
		sink(root);
		return max;
	}

	private void swim(Node node) {
		while (node.parent != null && less(node.parent, node)) {
			exch(node.parent, node);
			node = node.parent;
		}
	}

	private void sink(Node node) {
		while (node.lchild != null) {
			Node t = node.lchild;
			if (node.rchild != null && less(t, node.rchild)) {
				t = node.rchild;
			}
			if (!less(node, t)) {
				break;
			}
			exch(node, t);
			node = t;
		}
	}

	private boolean less(Node a, Node b) {
		return a.item.compareTo(b.item) < 0;
	}

	private void exch(Node a, Node b) {
		T t = a.item;
		a.item = b.item;
		b.item = t;
	}

	public void show() {
		Queue<Node> q = new LinkedList<Node>();
		q.offer(root);
		int current = 1;// the num of node of current height to be print
		int next = 0;// the num of node of next height
		while (!q.isEmpty()) {
			Node node = q.poll();
			System.out.print(node.item+" ");
			current--;
			if (node.lchild != null) {
				q.offer(node.lchild);
				next++;
			}
			if (node.rchild != null) {
				q.offer(node.rchild);
				next++;
			}
			if (current == 0) {
				System.out.println();
				current = next;
				next = 0;
			}
		}
	}

	public static void main(String[] args) {
		LinkedMaxPQ<Integer> pq = new LinkedMaxPQ<>();
		for (int i = 0; i < 20; i++) {
			pq.add(i);
		}
		pq.show();
		while (!pq.isEmpty()) {
			System.out.print(pq.delMax() + " ");
		}
	}

}

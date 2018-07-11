package study.chapter3;

import depend.In;
import study.chapter1.Queue;

public class SequentialSearchST<Key, Value> implements SymbolTable<Key, Value>{
	private int N;
	private Node first;

	private class Node {
		Key key;
		Value val;
		Node next;

		public Node(Key key, Value val, Node next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean contains(Key key) {
		if (key == null)
			throw new IllegalArgumentException(
					"argument to contains() is null");
		return get(key) != null;
	}

	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				return x.val;
			}
		}
		return null;
	}

	public void put(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException(
					"first argument to put() is null");
		if (val == null) {
			delete(key);
			return;
		}
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				x.val = val;
				return;
			}
		}
		first = new Node(key, val, first);
	}

	public void delete(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to delete() is null");
		first = delete(first, key);
	}

	private Node delete(Node x, Key key) {
		if (x == null)
			return null;
		if (key.equals(x.key)) {
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}

	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for (Node x = first; x != null; x = x.next)
			queue.enqueue(x.key);
		return queue;
	}

	public static void main(String[] args) {
		int distinct = 0;
		int words = 0;
		int minlen = 8;

		In in = new In("src/study/chapter3/tale.txt");
		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
		while (!in.isEmpty()) {
			String key = in.readString();
			if (key.length() < minlen)
				continue;
			words++;
			if (st.contains(key)) {
				st.put(key, st.get(key) + 1);
			} else {
				st.put(key, 1);
				distinct++;
			}
		}
		String max = "";
		st.put(max, 0);
		for (String word : st.keys()) {
			if (st.get(word) > st.get(max))
				max = word;
		}

		System.out.println(max + " " + st.get(max));
		System.out.println("distinct = " + distinct);
		System.out.println("words    = " + words);

	}

}

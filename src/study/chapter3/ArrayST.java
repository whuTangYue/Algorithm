package study.chapter3;

import study.chapter1.Queue;

public class ArrayST<Key, Value> {
	private static final int INIT_SIZE = 8;

	protected Value[] vals;
	protected Key[] keys;
	protected int n = 0;

	@SuppressWarnings("unchecked")
	public ArrayST() {
		keys = (Key[]) new Object[INIT_SIZE];
		vals = (Value[]) new Object[INIT_SIZE];
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		Key[] tempk = (Key[]) new Object[capacity];
		Value[] tempv = (Value[]) new Object[capacity];
		for (int i = 0; i < n; i++)
			tempk[i] = keys[i];
		for (int i = 0; i < n; i++)
			tempv[i] = vals[i];
		keys = tempk;
		vals = tempv;
	}

	public void put(Key key, Value val) {

		// to deal with duplicates
		delete(key);

		// double size of arrays if necessary
		if (n >= vals.length)
			resize(2 * n);

		// add new key and value at the end of array
		vals[n] = val;
		keys[n] = key;
		n++;
	}

	public Value get(Key key) {
		for (int i = 0; i < n; i++)
			if (keys[i].equals(key))
				return vals[i];
		return null;
	}
	
	public boolean contains(Key key) {
		if (key == null)
			throw new IllegalArgumentException(
					"argument to contains() is null");
		return get(key) != null;
	}

	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for (int i = 0; i < n; i++)
			queue.enqueue(keys[i]);
		return queue;
	}

	// remove given key (and associated value)
	public void delete(Key key) {
		for (int i = 0; i < n; i++) {
			if (key.equals(keys[i])) {
				keys[i] = keys[n - 1];
				vals[i] = vals[n - 1];
				keys[n - 1] = null;
				vals[n - 1] = null;
				n--;
				if (n > 0 && n == keys.length / 4)
					resize(keys.length / 2);
				return;
			}
		}
	}

	public static void main(String[] args) {
		ArrayST<String, Integer> st = new ArrayST<String, Integer>();
		int n = 10;
		for (int i = 0; i < n; i++) {
			String key = Integer.toString(n - i);
			st.put(key, i);
		}
		for (String s : st.keys())
			System.out.println(s + " " + st.get(s));
	}

}

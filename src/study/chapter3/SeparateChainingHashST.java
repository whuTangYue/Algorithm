package study.chapter3;

import study.chapter1.Queue;

public class SeparateChainingHashST<Key, Value>
		implements SymbolTable<Key, Value> {
	private static final int INIT_CAPACITY = 997;

	private int N; // num of key-value pairs
	private int M; // hash table size
	private SequentialSearchST<Key, Value>[] st;

	public SeparateChainingHashST() {
		this(INIT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public SeparateChainingHashST(int m) {
		this.M = m;
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
		for (int i = 0; i < m; i++)
			st[i] = new SequentialSearchST<Key, Value>();
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	private void resize(int chains) {
		SeparateChainingHashST<Key, Value> tmp = new SeparateChainingHashST<Key, Value>(
				chains);
		for (int i = 0; i < M; i++) {
			for (Key key : st[i].keys()) {
				tmp.put(key, st[i].get(key));
			}
		}
		this.M = tmp.M;
		this.N = tmp.N;
		this.st = tmp.st;
	}

	@Override
	public void put(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException("argument to put() is null");
		if (val == null) {
			delete(key);
			return;
		}
		if (N >= 10 * M)
			resize(2 * M);
		int i = hash(key);
		if (!st[i].contains(key))
			N++;
		st[i].put(key, val);

	}

	@Override
	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		return st[hash(key)].get(key);
	}

	@Override
	public void delete(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to delete() is null");
		int i = hash(key);
		if (st[i].contains(key))
			N--;
		st[i].delete(key);
		if (M > INIT_CAPACITY && N <= 2 * M)
			resize(M / 2);
	}

	@Override
	public boolean contains(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public int size() {
		return N;
	}

	@Override
	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for (int i = 0; i < M; i++) {
			for (Key key : st[i].keys())
				queue.enqueue(key);
		}
		return queue;
	}

	public static void main(String[] args) {
		SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>();
		String input = "S E A R C H E X A M P L E";
		String[] keys = input.split(" ");
		for (int i = 0; i < keys.length; i++) {
			String key = keys[i];
			st.put(key, i);
		}

		System.out.println();

		for (String s : st.keys())
			System.out.println(s + " " + st.get(s));
	}

}

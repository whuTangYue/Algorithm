package study.chapter5;

import depend.In;
import study.chapter1.Queue;

public class TrieST<Value> {
	private static int R;
	private Alphabet alphabet;

	private Node root;
	private int n;

	private static class Node {
		private Object val;
		private Node[] next = new Node[R];
	}

	public TrieST() {
		this.alphabet = Alphabet.EXTENDED_ASCII;
		TrieST.R = alphabet.radix();
	}

	public TrieST(Alphabet alphabet) {
		this.alphabet = alphabet;
		TrieST.R = alphabet.radix();
	}

	@SuppressWarnings("unchecked")
	public Value get(String key) {
		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		Node x = get(root, key, 0);
		if (x == null)
			return null;
		return (Value) x.val;
	}

	public boolean contains(String key) {
		if (key == null)
			throw new IllegalArgumentException(
					"argument to contains() is null");
		return get(key) != null;
	}

	private Node get(Node x, String key, int d) {
		if (x == null)
			return null;
		if (d == key.length())
			return x;

		char c = key.charAt(d);
		return get(x.next[alphabet.toIndex(c)], key, d + 1);
	}

	public void put(String key, Value val) {
		if (key == null)
			throw new IllegalArgumentException(
					"first argument to put() is null");
		if (val == null)
			delete(key);
		else
			root = put(root, key, val, 0);
	}

	private Node put(Node x, String key, Value val, int d) {
		if (x == null)
			x = new Node();
		if (d == key.length()) {
			if (x.val == null)
				n++;
			x.val = val;
			return x;
		}
		char c = key.charAt(d);
		int index = alphabet.toIndex(c);
		x.next[index] = put(x.next[index], key, val, d + 1);
		return x;
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Iterable<String> keys() {
		return keysWithPrefix("");
	}

	public Iterable<String> keysWithPrefix(String prefix) {
		Queue<String> results = new Queue<String>();
		Node x = get(root, prefix, 0);
		collect(x, new StringBuilder(prefix), results);
		return results;
	}

	public Iterable<String> keysThatMatch(String pattern) {
		Queue<String> results = new Queue<String>();
		collect(root, new StringBuilder(), pattern, results);
		return results;
	}

	private void collect(Node x, StringBuilder prefix, Queue<String> results) {
		if (x == null)
			return;
		if (x.val != null)
			results.enqueue(prefix.toString());
		for (int index = 0; index < R; index++) {
			char c;
			if(alphabet!=null) c = alphabet.toChar(index);
			else c = (char) index;
			prefix.append(c);
			collect(x.next[index], prefix, results);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}

	private void collect(Node x, StringBuilder prefix, String pattern,
			Queue<String> results) {
		if (x == null)
			return;
		int d = prefix.length();
		if (d == pattern.length() && x.val != null)
			results.enqueue(prefix.toString());
		if (d == pattern.length())
			return;
		char c = pattern.charAt(d);
		if (c == '.') {
			for (int index = 0; index < R; index++) {
				prefix.append(alphabet.toChar(index));
				collect(x.next[index], prefix, pattern, results);
				prefix.deleteCharAt(prefix.length() - 1);
			}
		} else {
			prefix.append(c);
			collect(x.next[alphabet.toIndex(c)], prefix, pattern, results);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}

	public String longestPrefixOf(String query) {
		if (query == null)
			throw new IllegalArgumentException(
					"argument to longestPrefixOf() is null");
		int length = longestPrefixOf(root, query, 0, -1);
		if (length == -1)
			return null;
		else
			return query.substring(0, length);
	}

	private int longestPrefixOf(Node x, String query, int d, int length) {
		if (x == null)
			return length;
		if (x.val != null)
			length = d;
		if (d == query.length())
			return length;
		char c = query.charAt(d);
		return longestPrefixOf(x.next[alphabet.toIndex(c)], query, d + 1, length);
	}

	public void delete(String key) {
		if (key == null)
			throw new IllegalArgumentException("argument to delete() is null");
		root = delete(root, key, 0);
	}

	private Node delete(Node x, String key, int d) {
		if (x == null)
			return null;
		if (d == key.length()) {
			if (x.val != null)
				n--;
			x.val = null;
		} else {
			char c = key.charAt(d);
			x.next[c] = delete(x.next[c], key, d + 1);
		}

		// remove subtrie rooted at x if it is completely empty
		if (x.val != null)
			return x;
		for (int c = 0; c < R; c++)
			if (x.next[c] != null)
				return x;
		return null;
	}

	public static void main(String[] args) {

		// build symbol table from standard input
		TrieST<Integer> st = new TrieST<Integer>(Alphabet.BASE64);
		In in = new In("src/data/shellsST.txt");
		for (int i = 0; !in.isEmpty(); i++) {
			String key = in.readString();
			st.put(key, i);
		}

		// print results
		if (st.size() < 100) {
			System.out.println("keys(\"\"):");
			for (String key : st.keys()) {
				System.out.println(key + " " + st.get(key));
			}
			System.out.println();
		}

		System.out.println("longestPrefixOf(\"shellsort\"):");
		System.out.println(st.longestPrefixOf("shellsort"));
		System.out.println();

		System.out.println("longestPrefixOf(\"quicksort\"):");
		System.out.println(st.longestPrefixOf("quicksort"));
		System.out.println();

		System.out.println("keysWithPrefix(\"shor\"):");
		for (String s : st.keysWithPrefix("shor"))
			System.out.println(s);
		System.out.println();

		System.out.println("keysThatMatch(\".he.l.\"):");
		for (String s : st.keysThatMatch(".he.l."))
			System.out.println(s);
	}

}

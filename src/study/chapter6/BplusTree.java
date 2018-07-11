package study.chapter6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BplusTree<Key extends Comparable<Key>, Value> {
	private static final int M = 6;
	private int n;

	private abstract class Page {
		boolean isExternal;
		List<Key> keys;

		int size() {
			return keys.size();
		}

		@SuppressWarnings("unused")
		boolean isEmpty() {
			return size() == 0;
		}

		boolean isFull() {
			return size() == M;
		}

		boolean isExternal() {
			return isExternal;
		}

		boolean contains(Key key) {
			int loc = Collections.binarySearch(keys, key);
			return loc >= 0;
		}

		Iterable<Key> keys() {
			return keys;
		}

		abstract Page next(Key key);

		abstract Value get(Key key);

		abstract boolean put(Key key, Value val);

		abstract void put(Page p);

		abstract Page split();
	}

	private class InternalPage extends Page {
		List<Page> pages;

		InternalPage() {
			isExternal = false;
			keys = new ArrayList<Key>();
			pages = new ArrayList<Page>();
		}

		@Override
		Page next(Key key) {
			int loc = Collections.binarySearch(keys, key);
			int index = loc >= 0 ? loc + 1 : -loc - 1;
			return pages.get(index);
		}

		@Override
		Value get(Key key) {
			return next(key).get(key);
		}

		@Override
		boolean put(Key key, Value val) {
			Page child = next(key);
			boolean b = child.put(key, val);
			if (child.isFull()) {
				Page sibing = child.split();
				put(sibing);
			}
			return b;
		}

		@Override
		void put(Page p) {
			assert (!isFull());
			Key min = p.keys.get(0);
			int loc = Collections.binarySearch(keys, min);
			int index = loc >= 0 ? loc + 1 : -loc - 1;
			if (loc >= 0) {
				pages.set(index, p);
			} else {
				keys.add(index, min);
				pages.add(index + 1, p);
			}
		}

		@Override
		Page split() {
			InternalPage tmp = new InternalPage();
			int from = size() / 2 + 1;
			int to = size();
			tmp.keys.addAll(keys.subList(from, to));
			tmp.pages.addAll(pages.subList(from, to + 1));
			keys.subList(from - 1, to).clear();
			pages.subList(from, to + 1).clear();
			return tmp;
		}
	}

	private class ExternalPage extends Page {
		List<Value> vals;

		ExternalPage() {
			isExternal = true;
			keys = new ArrayList<Key>();
			vals = new ArrayList<Value>();
		}

		@Override
		Page next(Key key) {
			return null;
		}

		@Override
		Value get(Key key) {
			int loc = Collections.binarySearch(keys, key);
			return loc >= 0 ? vals.get(loc) : null;
		}

		@Override
		boolean put(Key key, Value val) {
			assert (!isFull());
			int loc = Collections.binarySearch(keys, key);
			int index = loc >= 0 ? loc : -loc - 1;
			if (loc >= 0) {
				vals.set(index, val);
				return false;
			} else {
				keys.add(index, key);
				vals.add(index, val);
				return true;
			}
		}

		@Override
		void put(Page p) {
			throw new IllegalArgumentException("Do not put page to ExternalPage");
		}

		@Override
		Page split() {
			ExternalPage tmp = new ExternalPage();
			int from = (size() + 1) / 2;
			int to = size();
			tmp.keys.addAll(keys.subList(from, to));
			tmp.vals.addAll(vals.subList(from, to));
			keys.subList(from, to).clear();
			vals.subList(from, to).clear();
			return tmp;
		}
	}

	private Page root;

	public BplusTree() {
		root = new ExternalPage();
	}

	public int size() {
		return n;
	}

	public boolean contains(Key key) {
		return contains(root, key);
	}

	private boolean contains(Page h, Key key) {
		if (h.isExternal())
			return h.contains(key);
		return contains(h.next(key), key);
	}

	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		return root.get(key);
	}

	public void put(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException("argument key to put() is null");
		if (root.put(key, val))
			n++;
		if (root.isFull()) {
			Page lefthalf = root;
			Page righthalf = root.split();
			InternalPage newRoot = new InternalPage();
			newRoot.keys.add(righthalf.keys.get(0));
			newRoot.pages.add(lefthalf);
			newRoot.pages.add(righthalf);
			root = newRoot;
		}
	}

	public String toString() {
		return toString(root, "") + "\n";
	}

	@SuppressWarnings("unchecked")
	private String toString(Page h, String indent) {
		StringBuilder s = new StringBuilder();

		if (h.isExternal()) {
			for (Key key : h.keys()) {
				s.append(indent + key + " " + h.get(key) + "\n");
			}
		} else {
			InternalPage page = (InternalPage) h;
			for (int j = 0; j <= h.size(); j++) {
				if (j > 0)
					s.append(indent + "(" + h.keys.get(j - 1) + ")\n");
				s.append(toString(page.pages.get(j), indent + "     "));
			}
		}
		return s.toString();
	}

	public static void main(String[] args) {
		BplusTree<String, String> st = new BplusTree<String, String>();

		st.put("www.cs.princeton.edu", "128.112.136.12");
		st.put("www.cs.princeton.edu", "128.112.136.11");
		st.put("www.princeton.edu", "128.112.128.15");
		st.put("www.yale.edu", "130.132.143.21");
		st.put("www.simpsons.com", "209.052.165.60");
		st.put("www.apple.com", "17.112.152.32");
		st.put("www.amazon.com", "207.171.182.16");
		st.put("www.ebay.com", "66.135.192.87");
		st.put("www.cnn.com", "64.236.16.20");
		st.put("www.google.com", "216.239.41.99");
		st.put("www.nytimes.com", "199.239.136.200");
		st.put("www.microsoft.com", "207.126.99.140");
		st.put("www.dell.com", "143.166.224.230");
		st.put("www.slashdot.org", "66.35.250.151");
		st.put("www.espn.com", "199.181.135.201");
		st.put("www.weather.com", "63.111.66.11");
		st.put("www.yahoo.com", "216.109.118.65");

		System.out.println("cs.princeton.edu:  " + st.get("www.cs.princeton.edu"));
		System.out.println("hardvardsucks.com: " + st.get("www.harvardsucks.com"));
		System.out.println("simpsons.com:      " + st.get("www.simpsons.com"));
		System.out.println("apple.com:         " + st.get("www.apple.com"));
		System.out.println("ebay.com:          " + st.get("www.ebay.com"));
		System.out.println("dell.com:          " + st.get("www.dell.com"));
		System.out.println();

		System.out.println("size:    " + st.size());
		System.out.println(st);
		System.out.println();
	}
}

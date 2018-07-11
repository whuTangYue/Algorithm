package study.chapter3;

import java.util.NoSuchElementException;

import study.chapter1.Queue;

public class BinarySearchST<Key extends Comparable<Key>, Value> implements SymbolTable<Key,Value> {
	protected static final int INIT_CAPACITY = 2;
	protected Key[] keys;
	protected Value[] vals;
	protected int N;
	
	public static void main(String[] args) {
		BinarySearchST<Integer, Integer> st = new BinarySearchST<Integer, Integer>();
		int n = 10;
		for (int i = 0; i < n; i++) {
			Integer key = n - i;
			st.put(key, i);
		}
		for (Integer i : st.keys())
			System.out.println(i + " " + st.get(i));
	}

	public BinarySearchST() {
		this(INIT_CAPACITY);
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return N == 0;
	}
	
	public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

	@SuppressWarnings("unchecked")
	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
	}

	@SuppressWarnings("unchecked")
	protected void resize(int capacity) {
		assert capacity > N;
		Key[] temKey = (Key[]) new Comparable[capacity];
		Value[] temVal = (Value[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			temKey[i] = keys[i];
			temVal[i] = vals[i];
		}
		vals = temVal;
		keys = temKey;
	}

	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		if (isEmpty())
			return null;
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0)
			return vals[i];
		return null;
	}

	public int rank(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to rank() is null");
		int lo = 0;
		int hi = N - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if (cmp < 0)
				hi = mid - 1;
			else if (cmp > 0)
				lo = mid + 1;
			else
				return mid;
		}
		return lo;
	}

	public void put(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException(
					"first argument to put() is null");
		if (val == null) {
			delete(key);
			return;
		}
		int i = rank(key);

		if (i < N && keys[i].compareTo(key) == 0) {
			vals[i] = val;
			return;
		}
		if (keys.length == N)
			resize(2 * keys.length);
		for (int j = N; j > i; j--) {
			keys[j] = keys[j - 1];
			vals[j] = vals[j - 1];
		}
		keys[i] = key;
		vals[i] = val;
		N++;
		assert check();
	}

	public void delete(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to delete() is null");
		if (isEmpty())
			return;
		int i = rank(key);

		delete(i);
	}

	protected void delete(int i) {
		if (i < 0 || i > N)
			throw new IllegalArgumentException("delete() out of range");
		if (i == N) {
			return;
		}
		for (int j = i; j < N - 1; j++) {
			keys[j] = keys[j + 1];
			vals[j] = vals[j + 1];
		}
		N--;
		keys[N] = null;
		vals[N] = null;

		if (N > 0 && N == keys.length / 4)
			resize(keys.length / 2);

		assert check();
	}

	public void delMin() {
		if (isEmpty())
			throw new NoSuchElementException("Symbol table underflow err");
		delete(0);
	}

	public void delMax() {
		if (isEmpty())
			throw new NoSuchElementException("Symbol table underflow err");
		delete(N - 1);
	}

	public Key min() {
		if (isEmpty())
			throw new NoSuchElementException(
					"called min() with empty symbol table");
		return keys[0];
	}

	public Key max() {
		if (isEmpty())
			throw new NoSuchElementException(
					"called max() with empty symbol table");
		return keys[N - 1];
	}
	
	public Key select(int k) {
		if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        return keys[k];
	}
	public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null"); 
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) return keys[i];
        if (i == 0) return null;
        else return keys[i-1];
    }
	
	public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null"); 
        int i = rank(key);
        if (i == N) return null; 
        else return keys[i];
    }
	
	public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null"); 
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null"); 

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else              return rank(hi) - rank(lo);
    }

	public Iterable<Key> keys() {
        return keys(min(), max());
    }
	
	public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null"); 
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null"); 

        Queue<Key> queue = new Queue<Key>(); 
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++) 
            queue.enqueue(keys[i]);
        if (contains(hi)) queue.enqueue(keys[rank(hi)]);
        return queue; 
    }
	
	private boolean check() {
        return isSorted() && rankCheck();
    }

    // are the items in the array in ascending order?
    protected boolean isSorted() {
        for (int i = 1; i < size(); i++)
            if (keys[i].compareTo(keys[i-1]) < 0) return false;
        return true;
    }

    // check that rank(select(i)) = i
    protected boolean rankCheck() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (int i = 0; i < size(); i++)
            if (keys[i].compareTo(select(rank(keys[i]))) != 0) return false;
        return true;
    }

}

package study.chapter3;

import study.chapter1.Queue;

public class LinearProbingHashST<Key, Value>
		implements SymbolTable<Key, Value> {
	private static final int INIT_CAPACITY = 4;

	private int N;
	private int M;
	private Key[] keys;
	private Value[] vals;

	public LinearProbingHashST() {
		this(INIT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public LinearProbingHashST(int capacity) {
		M = capacity;
		N = 0;
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
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

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	private void resize(int capacity) {
		LinearProbingHashST<Key, Value> tmp = new LinearProbingHashST<Key, Value>(
				capacity);
		for(int i=0;i<M;i++) {
			if(keys[i]!=null) {
				tmp.put(keys[i], vals[i]);
			}
		}
		keys = tmp.keys;
		vals = tmp.vals;
		M = tmp.M;
	}

	@Override
	public void put(Key key, Value val) {
		if(key==null) throw new IllegalArgumentException("first argument to put() is null");
		if(val==null) {
			delete(key);
			return;
		}
		if(N>=M/2) resize(2*M);
		
		int i;
		for(i=hash(key);keys[i]!=null;i=(i+1)%M) {
			if(keys[i].equals(key)) {
				vals[i]=val;
				return;
			}
		}
		keys[i]=key;
		vals[i]=val;
		N++;
	}

	@Override
	public Value get(Key key) {
		if(key==null) throw new IllegalArgumentException("argument to get() is null");
		for(int i=hash(key);keys[i]!=null;i=(i+1)%M)
			if(keys[i].equals(key))
				return vals[i];
		return null;
	}

	@Override
	public void delete(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // find position i of key
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % M;
        }

        // delete key and associated value
        keys[i] = null;
        vals[i] = null;

        // rehash all keys in same cluster
        i = (i + 1) % M;
        while (keys[i] != null) {
            // delete keys[i] an vals[i] and reinsert
            Key   keyToRehash = keys[i];
            Value valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % M;
        }

        N--;

        // halves size of array if it's 12.5% full or less
        if (N > 0 && N <= M/8) resize(M/2);

        assert check();
	}

	@Override
	 public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < M; i++)
            if (keys[i] != null) queue.enqueue(keys[i]);
        return queue;
    }
	
	private boolean check() {

        // check that hash table is at most 50% full
        if (M < 2*N) {
            System.err.println("Hash table size m = " + M + "; array size n = " + N);
            return false;
        }

        // check that each key in table can be found by get()
        for (int i = 0; i < M; i++) {
            if (keys[i] == null) continue;
            else if (get(keys[i]) != vals[i]) {
                System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + vals[i]);
                return false;
            }
        }
        return true;
    }
	
	public static void main(String[] args) { 
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();
        String input = "S E A R C H E X A M P L E";
        String[] keys = input.split(" ");
        for (int i = 0; i<keys.length; i++) {
            String key = keys[i];
            st.put(key, i);
        }

        for (String s : st.keys())
            System.out.println(s + " " + st.get(s));
    }
}

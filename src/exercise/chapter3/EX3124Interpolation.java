package exercise.chapter3;

import study.chapter3.BinarySearchST;
import study.chapter3.SearchCompare;

/*mid  = low + (high - low)*(key - a[low)/(a[high]-a[low])*/

public class EX3124Interpolation<Key, Value>
		extends BinarySearchST<Integer, Value> {
	public EX3124Interpolation() {
		this(INIT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public EX3124Interpolation(int capacity) {
		keys = new Integer[capacity];
		vals = (Value[]) new Object[capacity];
	}

	@SuppressWarnings("unchecked")
	protected void resize(int capacity) {
		assert capacity > N;
		Integer[] temKey = new Integer[capacity];
		Value[] temVal = (Value[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			temKey[i] = keys[i];
			temVal[i] = vals[i];
		}
		vals = temVal;
		keys = temKey;
	}

	public int rank(Integer key) {
		if (key == null)
			throw new IllegalArgumentException("argument to rank() is null");
		if (N == 0)
			return 0;
		int lo = 0;
		int hi = N - 1;
		int mid;
		if (key > keys[hi])
			return N;
		while (lo < hi && keys[lo] != keys[hi]) {
			if (key < keys[lo])
				return lo;
			mid = lo + ((hi - lo) * (key - keys[lo]) / (keys[hi] - keys[lo]));
			if (key < keys[mid])
				hi = mid;
			else if (key > keys[mid])
				lo = mid + 1;
			else
				return mid;
		}

		return lo;
	}

	public static void main(String[] args) {
		EX3124Interpolation<Integer, Integer> st = new EX3124Interpolation<Integer, Integer>();
		BinarySearchST<Integer, Integer> bst = new BinarySearchST<Integer, Integer>();
		System.out.println(SearchCompare.time_int(st));
		System.out.println(SearchCompare.time_int(bst));
	}

}

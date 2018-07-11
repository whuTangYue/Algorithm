package study.chapter2;

import java.util.NoSuchElementException;

public class IndexMinPQ<T extends Comparable<? super T>> {
	private int maxN;
	private int N;
	private int[] pq;
	private int[] qp; // qp[pq[i]] = pq[qp[i]] = i
	private T[] keys;

	@SuppressWarnings("unchecked")
	public IndexMinPQ(int maxN) {
		if (maxN < 0)
			throw new IllegalArgumentException();
		this.maxN = maxN;
		keys = (T[]) new Comparable[maxN + 1];
		pq = new int[maxN + 1];
		qp = new int[maxN + 1];
		for (int i = 0; i <= maxN; i++) {
			qp[i] = -1;
		}
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public boolean contains(int k) {
		return qp[k] != -1;
	}

	public int size() {
		return N;
	}

	public T min() {
		return keys[pq[1]];
	}

	public int minIndex() {
		if (N == 0)
			throw new NoSuchElementException("Priority queue underflow");
		return pq[1];
	}

	public void change(int k, T t) {
		if (k < 0 || k >= maxN)
			throw new IllegalArgumentException();
		if (!contains(k))
			throw new NoSuchElementException(
					"index is not in the priority queue");
		keys[k] = t;
		swim(qp[k]);
		sink(qp[k]);
	}

	public void delete(int k) {
		int index = qp[k];
		exch(index, N--);
		swim(index);
		sink(index);
		keys[k] = null;
		qp[k] = -1;
	}

	public void insert(int k, T t) {
		if (!contains(k)) {
			N++;
			qp[k] = N;
			pq[N] = k;
			keys[k] = t;
			swim(N);
		} else {
			change(k, t);
		}
	}

	public int delMin() {
		int indexOfMin = pq[1];
		exch(1, N--);
		sink(1);
		keys[pq[N + 1]] = null;
		qp[pq[N + 1]] = -1;

		return indexOfMin;
	}

	private boolean less(int i, int j) {
		return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
	}

	private void exch(int i, int j) {
		int t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}

	private void swim(int k) {
		while (k > 1 && less(k, k / 2)) {
			exch(k / 2, k);
			k = k / 2;
		}
	}

	private void sink(int k) {
		while (2 * k <= N) {
			int j = 2 * k;
			if (j < N && less(j + 1, j))
				j++;
			if (less(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}

}

package study.chapter2;

import java.util.Arrays;

//PriorityQueue
public class MaxPQ<E extends Comparable<? super E>> {

	private E[] pq;
	private int N = 0;

	@SuppressWarnings("unchecked")
	public MaxPQ(int maxN) {
		pq = (E[]) new Comparable[maxN + 1];
	}
	
	public MaxPQ() {
		this(1);
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public void add(E e) {
		int i = N;
		if (i == pq.length - 1)
			resize(2 * pq.length);
		N = i + 1;
		pq[N] = e;
		swim(N);
	}

	public E max() {
		return pq[1];
	}
	
	public E delMax() {
		E max = pq[1];
		exch(1, N--);
		pq[N + 1] = null;
		sink(1);
		if (N > 1 && N <= pq.length / 4 )
			resize(pq.length / 2);
		return max;
	}

	private void resize(int newCapacity) {
		pq = Arrays.copyOf(pq, newCapacity);
	}

	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}

	private void exch(int i, int j) {
		E t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}

	private void swim(int k) {
		while (k > 1 && less(k / 2, k)) {
			exch(k / 2, k);
			k = k / 2;
		}
	}

	private void sink(int k) {
		while (2 * k <= N) {
			int j = 2 * k;
			if (j < N && less(j, j + 1))
				j++;
			if (!less(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}
	
	public static void main(String[] args) {
		MaxPQ<Character> pq = new MaxPQ<Character>(2);
		String test = "P R I O * R * * I * T * Y * * * Q U E * * * U * E";
		for (char c : test.toCharArray()) {
			if (c == '*') {
				System.out.print(pq.max() + " ");
				pq.delMax();
			} else if (c != ' ') {
				pq.add(c);
			}
		}

	}

}

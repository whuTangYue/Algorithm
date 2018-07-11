package exercise.chapter2;

import study.chapter2.MaxPQ;
import study.chapter2.MinPQ;

public class EX2430DynamicMedian {
	MinPQ<Integer> minpq;
	MaxPQ<Integer> maxpq;

	public EX2430DynamicMedian() {
		minpq = new MinPQ<Integer>();
		maxpq = new MaxPQ<Integer>();
	}

	public void add(Integer n) {
		if (maxpq.size() == minpq.size()) {
			if ((minpq.min() != null) && (n > minpq.min())) {
				maxpq.add(minpq.delMin());
				minpq.add(n);
			} else {
				maxpq.add(n);
			}
		} else {
			if (n < maxpq.max()) {
				minpq.add(maxpq.delMax());
				maxpq.add(n);
			} else {
				minpq.add(n);
			}
		}
	}

	public double getMedian() {
		if (maxpq.isEmpty()) {
			return 0;
		}
		if (maxpq.size() == minpq.size()) {
			return ((double) minpq.min() + (double) maxpq.max())/2;
		} else {
			return maxpq.max();
		}
	}

	public static void main(String[] args) {
		int N = 10;
		EX2430DynamicMedian dm = new EX2430DynamicMedian();
		for (int i = 0; i < N; i++) {
			dm.add(i);
			System.out.println(dm.getMedian());		
		}
		
	}

}

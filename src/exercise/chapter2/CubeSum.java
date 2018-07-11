package exercise.chapter2;

import study.chapter2.MinPQ;

public class CubeSum implements Comparable<CubeSum> {
	final int sum;
	final int i;
	final int j;

	public CubeSum(int i, int j) {
		this.i = i;
		this.j = j;
		this.sum = i * i * i + j * j * j;
	}

	public int compareTo(CubeSum that) {
		if (this.sum < that.sum)
			return -1;
		if (this.sum > that.sum)
			return +1;
		return 0;
	}

	public String toString() {
		return i + "^3" + " + " + j + "^3";
	}

	public static void main(String[] args) {
		int n = 1000;
		MinPQ<CubeSum> pq = new MinPQ<CubeSum>();
		for (int i = 0; i <= n; i++) {
			pq.add(new CubeSum(i, i));
		}
		int run = 1;
		CubeSum prev = pq.delMin();

		// find smallest sum, print it out, and update
		while (!pq.isEmpty()) {
			CubeSum s = pq.delMin();
			if (s.sum == prev.sum) {
				run++;
				if (run == 2)
					System.out.print(prev.sum + " = " + prev);
				System.out.print(" = " + s);
			}else {
				if(run>1)System.out.println();
				run=1;
			}
			prev = s;
			if (s.j < n)
				pq.add(new CubeSum(s.i, s.j + 1));
		}
	}

}

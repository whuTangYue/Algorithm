package exercise.chapter2;

import java.util.Random;

public class EX2435Sample {
	private double[] p;
	private int leafstart;

	public void random() {
		double T = p[0];
		Random random = new Random();
		int n = 0;
		while (n < leafstart) {
			double r = random.nextDouble();
			if (r * p[n] <= p[iLeftChild(n)]) {
				n = iLeftChild(n);
			} else {
				n = iRightChild(n);
			}
		}
		int i = n - leafstart;
		double probability = p[n] / T;
		System.out.println(i + " " + probability);
	}

	public void change(int i, double v) {
		int n = leafstart + i;
		p[n] = v;
		while (n != 0) {
			p[iParent(n)] = p[iLeftChild(iParent(i))]
					+ p[iRightChild(iParent(i))];
			n = iParent(n);
		}

	}

	public EX2435Sample(double[] p) {
		int len = p.length;
		int l = 0;
		int r = 0;
		while (r - l + 1 < len) {
			l = iLeftChild(l);
			r = iRightChild(r);
		}
		this.p = new double[r + 1];
		this.leafstart = l;
		for (int i = 0; i < len; i++) {
			this.p[l + i] = p[i];
		}
		for (int i = r; i >= 1; i--) {
			this.p[iParent(i)] = this.p[iLeftChild(iParent(i))]
					+ this.p[iRightChild(iParent(i))];
		}

	}

	private static int iParent(int i) {
		return Math.floorDiv(i - 1, 2);
	}

	private static int iLeftChild(int i) {
		return 2 * i + 1;
	}

	private static int iRightChild(int i) {
		return 2 * i + 2;
	}

	public static void main(String[] args) {
		int N = 25;
		double[] p = new double[N];
		for(int i=0;i<p.length;i++) {
			p[i]=0.1;
		}
		EX2435Sample sample = new EX2435Sample(p);
		sample.random();
		

	}

}

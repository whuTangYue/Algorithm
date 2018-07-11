package study.chapter1;

import depend.StdDraw;
import depend.StdRandom;

public class VisualAccumulator {
	private double total;
	private int N;
	private int trials = 10;
	private double max = 1.0;

	public VisualAccumulator(int trials, double max) {
		this.trials = trials;
		this.max = max;
		StdDraw.setXscale(0, trials);
		StdDraw.setYscale(0, max);
		StdDraw.setPenRadius(0.005);
	}

	public void addDataValue(double val) {
		N++;
		total += val;
		if(N>trials) {
			StdDraw.setXscale(0, N);
		}
		if(val>max) {
			StdDraw.setYscale(0, val);
		}
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.point(N, val);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.point(N, total / N);
	}

	public double mean() {
		return total / N;
	}

	public String toString() {
		return "Mean (" + N + " values):" + String.format("%7.5f", mean());
	}

	public static void main(String[] args) {
		int T = 100;
		VisualAccumulator a = new VisualAccumulator(T, 1.0);
		for (int t = 0; t < T; t++) {
			a.addDataValue(StdRandom.uniform());
		}
		System.out.println(a);

	}

}

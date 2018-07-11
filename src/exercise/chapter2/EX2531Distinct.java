package exercise.chapter2;

import java.util.Arrays;

import depend.StdRandom;
import depend.StdStats;

public class EX2531Distinct {
	// return number of distinct entries in array a[]
	public static <T extends Comparable<T>> int distinct(T[] a) {
		if (a.length == 0)
			return 0;
		Arrays.sort(a);
		int distinct = 1;
		for (int i = 1; i < a.length; i++)
			if (a[i].compareTo(a[i - 1]) != 0)
				distinct++;
		return distinct;
	}

	public static void main(String[] args) {
		int trials = 100;
		int[] N = { 1000, 10000, 100000, 1000000 };
		System.out.printf("%13s %13s %13s %23s\n", "Values Generated | ",
				"Max Value | ", "Distinct Values | ",
				"Expected Distinct Values");
		for (int n : N) {
			int[] M = { n / 2, n, 2 * n };
			for (int m : M) {
				int[] distinct = new int[trials];
				for (int t = 0; t < trials; t++) {
					Integer[] a = new Integer[n];
					for (int i = 0; i < n; i++) {
						a[i] = StdRandom.uniform(m);
					}
					distinct[t] = distinct(a);
				}

				double empirical = StdStats.mean(distinct);
				double alpha = (double) n / m;
				double theoretical = m * (1 - Math.exp(-alpha));
				System.out.printf("%16d %13d %18.2f %27.2f\n", n,
						m, empirical, theoretical);

			}
		}
	}
}

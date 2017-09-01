package study.chapter2;

import java.util.Random;

public class SortCompare {
	public static double time(String alg, Double[] a) {
		long time1 = System.currentTimeMillis();
		if (alg.equals("Insertion"))
			InsertionSort.sort(a);
		if (alg.equals("Selection"))
			SelectionSort.sort(a);
		if (alg.equals("Shell"))
			ShellSort.sort(a);

		long time2 = System.currentTimeMillis();
		return (double) (time2 - time1);
	}

	public static double timeRandomInput(String alg, int N, int T) {
		double total = 0.0;
		Double[] a = new Double[N];
		Random random = new Random();
		for (int t = 0; t < T; t++) {
			for (int i = 0; i < N; i++) {
				a[i] = random.nextDouble();
			}
			total += time(alg, a);
		}
		System.out.println(Sort.isSorted(a));
		return total / T;
	}

	public static void main(String[] args) {
		int N = 1000;
		int T = 1000;
		double t1 = timeRandomInput("Insertion", N, T);
		double t2 = timeRandomInput("Selection", N, T);
		double t3 = timeRandomInput("Shell", N, T);
		System.out.println(N + " " + T);
		System.out.println(t1 + "\n" + t2 + "\n" + t3);

	}

}

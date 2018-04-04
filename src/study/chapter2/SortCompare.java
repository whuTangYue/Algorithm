package study.chapter2;

import java.util.Arrays;
import java.util.Random;

interface SortMethod {
	void sort(double[] a);
}

public class SortCompare {
	private static double time(SortMethod sortMethod, double[] a) {
		long time1 = System.currentTimeMillis();
		sortMethod.sort(a);

		long time2 = System.currentTimeMillis();
		return (double) (time2 - time1);
	}

	public static double timeRandomInput(SortMethod sortMethod, int N, int T) {
		double total = 0.0;
		double[] a = new double[N];
		Random random = new Random();
		for (int t = 0; t < T; t++) {
			for (int i = 0; i < N; i++) {
				a[i] = random.nextDouble();
			}
			total += time(sortMethod, a);
		}
		System.out.println(Sort.isSorted(a));
		return total / T;
	}

	public static void main(String[] args) {
		int N = 100000;
		int T = 100;
		// double t1 = timeRandomInput(InsertionSort::sort, N, T);
		// double t2 = timeRandomInput(SelectionSort::sort, N, T);
		double t3 = SortCompare.timeRandomInput(ShellSort::sort, N, T);
		double t4 = SortCompare.timeRandomInput(MergeSort::sort, N, T);
		double t5 = SortCompare.timeRandomInput(QuickSort::sort, N, T);
		double t6 = SortCompare.timeRandomInput(Arrays::parallelSort, N, T);
		double t7 = SortCompare.timeRandomInput(Arrays::sort, N, T);
		double t8 = SortCompare.timeRandomInput(
				exercise.chapter2.EX2216NaturalMergeSort::sort, N, T);
		System.out.println(N + " " + T);
		System.out.println(/* t1 + "\n" + t2 + "\n" */+t3 + "\n" + t4 + "\n"
				+ t5 + "\n" + t6 + "\n" + t7 + "\n" + t8);

	}

}

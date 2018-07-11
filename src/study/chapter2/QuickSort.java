package study.chapter2;

import java.util.Random;

public class QuickSort extends Sort {

	private static void shuffle(double[] a) {
		int N = a.length;
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			int r = i + random.nextInt(N - i);
			double temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}

	public static <T extends Comparable<? super T>> void sort(T[] a) {
		shuffle(a);
		sort(a, 0, a.length - 1);
	}

	public static void sort(double[] a) {
		shuffle(a);
		sort(a, 0, a.length - 1);
	}

	private static <T extends Comparable<? super T>> void insertionSort(T[] a,
			int lo, int hi) {
		for (int i = lo; i <= hi; i++) {
			T tmp = a[i];
			int j = i;
			for (j = i; j > lo && a[j - 1].compareTo(tmp) > 0; j--)
				a[j] = a[j - 1];
			a[j] = tmp;
		}
	}

	private static void insertionSort(double[] a, int lo, int hi) {
		for (int i = lo; i <= hi; i++) {
			double tmp = a[i];
			int j = i;
			for (j = i; j > lo && tmp < a[j - 1]; j--)
				a[j] = a[j - 1];
			a[j] = tmp;
		}
	}

	public static <T extends Comparable<? super T>> void sort(T[] a, int lo,
			int hi) {
		assert a != null && lo >= 0 && lo <= hi && hi <= a.length;

		int M = 7;
		if (hi <= lo + M) {
			insertionSort(a, lo, hi);// 切换到插入排序
			return;
		}
		int lt = lo, i = lo + 1, gt = hi;
		T v = a[lo];
		while (i <= gt) {
			int cmp = a[i].compareTo(v);
			if (cmp < 0)
				exch(a, lt++, i++);
			else if (cmp > 0)
				exch(a, i, gt--);
			else
				i++;
		}
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);

	}

	public static void sort3way(double[] a, int lo, int hi) {
		assert a != null && lo >= 0 && lo <= hi && hi <= a.length;

		int M = 7;
		if (hi <= lo + M) {
			insertionSort(a, lo, hi);// 切换到插入排序
			return;
		}
		int lt = lo, i = lo + 1, gt = hi;
		double v = a[lo];
		while (i <= gt) {
			if (a[i] < v)
				exch(a, lt++, i++);
			else if (a[i] > v)
				exch(a, i, gt--);
			else
				i++;
		}
		sort3way(a, lo, lt - 1);
		sort3way(a, gt + 1, hi);

	}

	public static void sort(double[] a, int lo, int hi) {
		assert a != null && lo >= 0 && lo <= hi && hi <= a.length;

		int M = 7;
		if (hi <= lo + M) {
			insertionSort(a, lo, hi);// 切换到插入排序
			return;
		}
		double p = a[lo];
		int pivot = lo;
		for(int j=lo+1;j<=hi;j++) {
			if (a[j]<p) {
				exch(a,++pivot,j);
			}
		}
		exch(a,pivot,lo);
		sort(a, lo, pivot - 1);
		sort(a, pivot + 1, hi);
	}

	public static void main(String[] args) {
		int N = 1000;
		int T = 1000;
		double t1 = SortCompare.timeRandomInput(QuickSort::sort, N, T);
		System.out.println(N + " " + T);
		System.out.println(t1 + "\n");

	}

}

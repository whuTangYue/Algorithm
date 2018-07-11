package study.chapter2;

import java.util.Arrays;

public class MergeSort extends Sort {
	private static final int INSERTIONSORT_THRESHOLD = 7;

	public static void sort(double[] a) {
		if (a.length < INSERTIONSORT_THRESHOLD) {
			insertionSort(a, 0, a.length - 1);// 切换到插入排序
			return;
		}
		double[] aux = a.clone();
		mergeSort(aux, a, 0, a.length - 1, 0);
	}

	@SuppressWarnings("unchecked")
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		if (a.length < INSERTIONSORT_THRESHOLD) {
			insertionSort(a, 0, a.length - 1);// 切换到插入排序
			return;
		}
		Object[] aux = a.clone();
		mergeSort((T[]) aux, a, 0, a.length - 1, 0);
	}

	public static void sort(double[] a, int fromIndex, int toIndex) {
		if ((toIndex - fromIndex + 1) < INSERTIONSORT_THRESHOLD) {
			insertionSort(a, fromIndex, toIndex);// 切换到插入排序
			return;
		}
		double[] aux = Arrays.copyOfRange(a, fromIndex, toIndex);
		mergeSort(aux, a, fromIndex, toIndex, -fromIndex);
	}

	@SuppressWarnings("unchecked")
	public static <T extends Comparable<? super T>> void sort(T[] a,
			int fromIndex, int toIndex) {
		if ((toIndex - fromIndex + 1) < INSERTIONSORT_THRESHOLD) {
			insertionSort(a, fromIndex, toIndex);// 切换到插入排序
			return;
		}
		Object[] aux = Arrays.copyOfRange(a, fromIndex, toIndex);
		mergeSort((T[]) aux, a, fromIndex, toIndex, -fromIndex);
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

	private static void mergeSort(double[] src, double[] dest, int low,
			int high, int off) {
		int length = high - low + 1;
		if (length < INSERTIONSORT_THRESHOLD) {
			insertionSort(dest, low, high);// 切换到插入排序
			// for (int i = low; i <= high; i++)
			// for (int j = i; j > low
			// && (dest[j - 1]).compareTo(dest[j]) > 0; j--)
			// exch(dest, j, j - 1);
			return;
		}
		// Recursively sort halves of dest into src
		int destLow = low;
		int destHigh = high;
		low += off;
		high += off;
		int mid = (low + high + 1) >>> 1;
		mergeSort(dest, src, low, mid - 1, -off);
		mergeSort(dest, src, mid, high, -off);

		// If list is already sorted, just copy from src to dest. This is an
		// optimization that results in faster sorts for nearly ordered lists.
		if (src[mid - 1] <= src[mid]) {
			System.arraycopy(src, low, dest, destLow, length);
			return;
		}

		// Merge sorted halves (now in src) into dest
		for (int i = destLow, p = low, q = mid; i <= destHigh; i++) {
			if (q > high || p < mid && src[p] <= src[q])
				dest[i] = src[p++];
			else
				dest[i] = src[q++];
		}
	}

	private static <T extends Comparable<? super T>> void mergeSort(T[] src,
			T[] dest, int low, int high, int off) {
		int length = high - low + 1;
		if (length < INSERTIONSORT_THRESHOLD) {
			insertionSort(dest, low, high);// 切换到插入排序
			// for (int i = low; i <= high; i++)
			// for (int j = i; j > low
			// && (dest[j - 1]).compareTo(dest[j]) > 0; j--)
			// exch(dest, j, j - 1);
			return;
		}
		// Recursively sort halves of dest into src
		int destLow = low;
		int destHigh = high;
		low += off;
		high += off;
		int mid = (low + high + 1) >>> 1;
		mergeSort(dest, src, low, mid - 1, -off);
		mergeSort(dest, src, mid, high, -off);

		// If list is already sorted, just copy from src to dest. This is an
		// optimization that results in faster sorts for nearly ordered lists.
		if ((src[mid - 1]).compareTo(src[mid]) <= 0) {
			System.arraycopy(src, low, dest, destLow, length);
			return;
		}

		// Merge sorted halves (now in src) into dest
		for (int i = destLow, p = low, q = mid; i <= destHigh; i++) {
			if (q > high || p < mid && (src[p]).compareTo(src[q]) <= 0)
				dest[i] = src[p++];
			else
				dest[i] = src[q++];
		}
	}

	public static void main(String[] args) {
		int N = 50000;
		int T = 100;
		double t1 = SortCompare.timeRandomInput(ShellSort::sort, N, T);
		double t2 = SortCompare.timeRandomInput(MergeSort::sort, N, T);
		double t3 = SortCompare.timeRandomInput(QuickSort::sort, N, T);
		double t4 = SortCompare.timeRandomInput(Arrays::parallelSort, N, T);
		System.out.println(N + " " + T);
		System.out.println(t1 + "\n" + t2 + "\n" + t3 + "\n" + t4);
		// Integer[] a = new Integer[10];
		// Random random = new Random();
		// for (int i = 0; i < a.length; i++) {
		// a[i] = random.nextInt(30);
		// }
		// sort(a);
		//
		// assert isSorted(a);
		// show(a);
	}

}

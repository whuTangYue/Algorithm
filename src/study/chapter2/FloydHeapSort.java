package study.chapter2;

public class FloydHeapSort extends Sort {

	public static <T extends Comparable<? super T>> void sort(T[] a) {
		int N = a.length;
		for (int i = (N - 1) / 2; i >= 0; i--) {
			sink(a, i, N - 1);
		}
		for (int i = N - 1; i > 0; i--) {
			exch(a, 0, i);
			sift_down(a, 0, i - 1);
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

	private static <T extends Comparable<? super T>> int leafSearch(T[] a,
			int i, int end) {
		int j = i;
		while (iLeftChild(j) <= end) {
			if (iRightChild(j) <= end
					&& less(a[iLeftChild(j)], a[iRightChild(j)]))
				j = iRightChild(j);
			else
				j = iLeftChild(j);
		}
		return j;
	}

	private static <T extends Comparable<? super T>> void sink(T[] array, int k,
			int end) {
		int child = 2 * k + 1;
		while (child <= end) {
			if (child < end && less(array[child], array[child + 1]))
				child++;
			if (less(array[k], array[child])) {
				exch(array, k, child);
				k = child;
				child = 2 * k + 1;
			} else
				break;
		}
	}

	private static <T extends Comparable<? super T>> void sift_down(T[] a,
			int i, int end) {
		int j = leafSearch(a, i, end);
		while (less(a[j], a[i])) {
			j = iParent(j);
		}
		T x = a[j];
		a[j] = a[i];
		while (j > i) {
			T tmp = x;
			x = a[iParent(j)];
			a[iParent(j)] = tmp;
			j = iParent(j);
		}
	}

	public static void main(String[] args) {
		FloydHeapSort.test();
	}

}

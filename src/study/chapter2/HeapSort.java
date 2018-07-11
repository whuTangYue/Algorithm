package study.chapter2;

//同时最优地利用空间和时间，最坏情况下2NlgN次比较和恒定额外空间
//但是堆排序无法利用缓存。数组元素很少和相邻的其他元素进行比较，因此缓存未命中数远高于大多数比较相邻元素的算法
public class HeapSort extends Sort {

	public static <T extends Comparable<? super T>> void sort(T[] a) {
		int N = a.length;
		for (int i = (N - 1) / 2; i >= 0; i--) {
			sink(a, i, N - 1);
		}
		for (int i = N - 1; i > 0; i--) {
			exch(a, 0, i);
			sink(a, 0, i - 1);
		}
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

	public static void main(String[] args) {
		HeapSort.test();
	}

}

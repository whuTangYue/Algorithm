package exercise.chapter2;

import java.util.Random;

import exercise.depend.StdDraw;

public class EX2124InsertionSortWithSentry {
	public static <T extends Comparable<T>> void sort(T[] a) {
		int N = a.length;
		int min = 0;
		for (int i = 1; i < N; i++) {
			if (less(a[i], a[min]))
				min = i;
		}
		if (min > 0)
			exch(a, 0, min);
		for (int i = 1; i < N; i++) {
			// 较大元素右移而不是交换，访问数组次数减半，大幅提高速度
			T tmp = a[i];
			int j = i;
			for (j = i; less(tmp, a[j - 1]); j--)
				a[j] = a[j - 1];
			a[j] = tmp;
		}
	}

	protected static <T extends Comparable<T>> boolean less(T a, T a2) {
		return a.compareTo(a2) < 0;
	}

	protected static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
		T t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	protected static <T> void show(T[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	protected static void show(Double[] a) {
		int n = a.length;

		StdDraw.setCanvasSize(1024, 512);
		StdDraw.setXscale(0, n);

		StdDraw.show(0);
		for (int i = 0; i < n; i++) {
			StdDraw.filledRectangle(0.4 + i, a[i] / 2, 0.4, a[i] / 2);
		}
		StdDraw.show();
	}

	public static <T extends Comparable<T>> boolean isSorted(T[] a) {
		for (int i = 1; i < a.length; i++) {
			if (less(a[i], a[i - 1]))
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		int N = 1000;
		Double[] a = new Double[N];
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			a[i] = random.nextDouble();
		}

		sort(a);
		assert isSorted(a);
		show(a);

	}

}

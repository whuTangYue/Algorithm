package study.chapter2;

import java.util.Random;

public class Sort {
	public static void sort(double[] a) {
		// Bubble sort O(n2)
		for (int i = a.length - 1; i > 0; i--) {
			for (int j = 0; j <= i; j++) {
				if (less(a[j + 1], a[j])) {
					exch(a, j, j + 1);
				}
			}
		}
	}

	public static <T extends Comparable<? super T>> void sort(T[] a) {
		// Bubble sort O(n2)
		for (int i = a.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (less(a[j + 1], a[j])) {
					exch(a, j, j + 1);
				}
			}
		}
	}

	protected static <T extends Comparable<? super T>> boolean less(T a, T a2) {
		return a.compareTo(a2) < 0;
	}

	protected static boolean less(double a, double a2) {
		return a < a2;
	}

	protected static <T extends Comparable<? super T>> void exch(T[] a, int i,
			int j) {
		T t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	protected static void exch(double[] a, int i, int j) {
		double t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	protected static <T extends Comparable<? super T>> void show(T[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	protected static void show(double[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	public static <T extends Comparable<? super T>> boolean isSorted(T[] a) {
		for (int i = 1; i < a.length; i++) {
			if (less(a[i], a[i - 1]))
				return false;
		}
		return true;
	}

	public static boolean isSorted(double[] a) {
		for (int i = 1; i < a.length; i++) {
			if (less(a[i], a[i - 1]))
				return false;
		}
		return true;
	}

	public static boolean check() {
		String[] a = new String[8];
		for (int i = 0; i < 8; i++) {
			a[i] = i + " ";
		}
		shuffle(a);
		String[] b = a.clone();
		sort(a);
		assert isSorted(a);
		for (int i = 0; i < a.length; i++) {
			boolean found = false;
			for (int j = 0; j < a.length; j++) {
				if (a[i] == b[j]) {
					found = true;
					break;
				}
			}
			if (!found) {
				return false;
			}
		}
		return true;
	}

	//选择排序交换部分使用新建对象的方法，用于测试check()
//	public static void SelectionSort(String[] s) {
//		for (int i = 0; i < s.length; i++) {
//			int min = i;
//			for (int j = i + 1; j < s.length; j++) {
//				if (s[j].compareTo(s[min]) < 0) {
//					min = j;
//				}
//			}
//			String temp = new String(s[i].toCharArray());
//			s[i] = s[min];
//			s[min] = temp;
//		}
//	}

	public static <T extends Comparable<? super T>> void shuffle(T[] a) {

		int N = a.length;
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			int r = i + random.nextInt(N - i);
			exch(a, i, r);
		}

	}

	public static void test() {
		test(8);
	}

	public static void test(int N) {
		Integer[] array = new Integer[N];
		for (int i = 0; i < N; i++) {
			array[i] = i;
		}
		shuffle(array);
		show(array);
		sort(array);
		assert isSorted(array);
		show(array);
	}

	public static void main(String[] args) {
		test();
		System.out.println(check());

	}
}

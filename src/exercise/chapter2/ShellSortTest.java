package exercise.chapter2;

import java.util.Random;

public class ShellSortTest {
	static class Sort {
		public static <T extends Comparable<T>> void sort(T[] a) {
			// Bubble sort O(n2)
			for (int i = a.length - 1; i > 0; i--) {
				for (int j = 0; j <= i; j++) {
					if (less(a[j + 1], a[j])) {
						exch(a, j, j + 1);
					}
				}
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

		protected static <T extends Comparable<T>> void show(T[] a) {
			for (int i = 0; i < a.length; i++) {
				System.out.print(a[i] + " ");
			}
			System.out.println();
		}

		public static <T extends Comparable<T>> boolean isSorted(T[] a) {
			for (int i = 1; i < a.length; i++) {
				if (less(a[i], a[i - 1]))
					return false;
			}
			return true;
		}

	}

	static class ShellSort extends Sort {
		public static <T extends Comparable<T>> void sort(T[] a) {
			int N = a.length;
			int h = 1;
			while (h < N / 3)
				h = 3 * h + 1;
			while (h >= 1) {
				for (int i = h; i < N; i++) {
					T tmp = a[i];
					int j = i;
					for (j = i; j >= h && less(tmp, a[j - h]); j -= h)
						a[j] = a[j - h];
					a[j] = tmp;

					// for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
					// exch(a, j, j - h);
				}
				h = h / 3;
			}
		}
	}

	interface SortMethod {
		void sort(Double[] a);
	}

	private static double time(SortMethod sortMethod, Double[] a) {
		long time1 = System.currentTimeMillis();
		sortMethod.sort(a);

		long time2 = System.currentTimeMillis();
		return (double) (time2 - time1);
	}

	public static double timeRandomInput(SortMethod sortMethod, int N, int T) {
		double total = 0.0;
		Double[] a = new Double[N];
		Random random = new Random();
		for (int t = 0; t < T; t++) {
			for (int i = 0; i < N; i++) {
				a[i] = random.nextDouble();
			}
			total += time(sortMethod, a);
		}
		return total / T;
	}

	public static void main(String[] args) {
		int T = 1000;
		for (int N = 128; N < 10000000; N *= 2) {
			double t = timeRandomInput(ShellSort::sort, N, T);
			System.out.println("N: "+N+" t: "+t);
		}
	}
}

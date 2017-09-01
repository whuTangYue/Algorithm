package study.chapter2;

public class Sort {
	public static <T extends Comparable<T>> void sort(T[] a) {

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

	public static void main(String[] args) {
		String[] a = { "1", "", "a", "c", "b" };

		sort(a);
		assert isSorted(a);
		show(a);

	}
}

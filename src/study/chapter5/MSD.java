package study.chapter5;

import depend.In;

/**
 * The {@code MSD} class provides static methods for sorting an array of
 * extended ASCII strings or integers using MSD radix sort.
 * <p>
 * For additional documentation, see
 * <a href="https://algs4.cs.princeton.edu/51radix">Section 5.1</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class MSD {
	private static final int BITS_PER_BYTE = 8;
	private static final int BITS_PER_INT = 32; // each Java int is 32 bits
	private static final int R = 256; // extended ASCII alphabet size
	private static final int CUTOFF = 7; // cutoff to insertion sort

	// do not instantiate
	private MSD() {
	}

	/**
	 * Rearranges the array of extended ASCII strings in ascending order.
	 *
	 * @param a
	 *            the array to be sorted
	 */
	public static void sort(String[] a) {
		int n = a.length;
		String[] aux = new String[n];
		sort(a, 0, n - 1, 0, aux);
	}

	// sort from a[lo] to a[hi], starting at the dth character
	private static void sort(String[] a, int lo, int hi, int d, String[] aux) {

		// cutoff to insertion sort for small subarrays
		if (hi <= lo + CUTOFF) {
			insertion(a, lo, hi, d);
			return;
		}

		// compute frequency counts
		int[] count = new int[R + 2];
		for (int i = lo; i <= hi; i++) {
			int c = a[i].charAt(d);
			count[c + 2]++;
		}

		// transform counts to indicies
		for (int r = 0; r < R + 1; r++)
			count[r + 1] += count[r];

		// distribute
		for (int i = lo; i <= hi; i++) {
			int c = a[i].charAt(d);
			aux[count[c + 1]++] = a[i];
		}

		// copy back
		for (int i = lo; i <= hi; i++)
			a[i] = aux[i - lo];

		// recursively sort for each character (excludes sentinel -1)
		for (int r = 0; r < R; r++)
			sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1, aux);
	}

	// insertion sort a[lo..hi], starting at dth character
	private static void insertion(String[] a, int lo, int hi, int d) {
		for (int i = lo; i <= hi; i++)
			for (int j = i; j > lo && less(a[j], a[j - 1], d); j--)
				exch(a, j, j - 1);
	}

	// exchange a[i] and a[j]
	private static void exch(String[] a, int i, int j) {
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	// is v less than w, starting at character d
	private static boolean less(String v, String w, int d) {
		// assert v.substring(0, d).equals(w.substring(0, d));
		for (int i = d; i < Math.min(v.length(), w.length()); i++) {
			if (v.charAt(i) < w.charAt(i))
				return true;
			if (v.charAt(i) > w.charAt(i))
				return false;
		}
		return v.length() < w.length();
	}

	public static void sort(int[] a) {
		int n = a.length;
		int[] aux = new int[n];
		sort(a, 0, n - 1, 0, aux);
	}

	private static void sort(int[] a, int lo, int hi, int d, int[] aux) {

		// cutoff to insertion sort for small subarrays
		if (hi <= lo + CUTOFF) {
			insertion(a, lo, hi, d);
			return;
		}
		int[] count;
		if (d == 0) {
			// handle the negative integer
			count = new int[3];
			for (int i = lo; i <= hi; i++) {
				if (a[i] < 0)
					count[1]++;
				else
					count[2]++;
			}
			count[2] += count[1];
			for (int i = lo; i <= hi; i++) {
				if (a[i] < 0)
					aux[count[0]++] = a[i];
				else
					aux[count[1]++] = a[i];
			}

			// copy back
			for (int i = lo; i <= hi; i++)
				a[i] = aux[i - lo];
			if (count[0] > 0)
				sort(a, lo, lo + count[0] - 1, d + 1, aux);
			for (int r = 0; r < 2; r++)
				if (count[r + 1] > count[r])
					sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1, aux);
		} else {

			// compute frequency counts (need R = 256)
			count = new int[R + 1];
			int mask = R - 1; // 0xFF;
			int shift = BITS_PER_INT - BITS_PER_BYTE * d;
			for (int i = lo; i <= hi; i++) {
				int c = (a[i] >> shift) & mask;
				count[c + 1]++;

			}

			// transform counts to indicies
			for (int r = 0; r < R; r++)
				count[r + 1] += count[r];

			// distribute
			for (int i = lo; i <= hi; i++) {
				int c = (a[i] >> shift) & mask;
				aux[count[c]++] = a[i];
			}

			// copy back
			for (int i = lo; i <= hi; i++)
				a[i] = aux[i - lo];

			// no more bits
			if (d == 5)
				return;

			// recursively sort for each character
			if (count[0] > 0)
				sort(a, lo, lo + count[0] - 1, d + 1, aux);
			for (int r = 0; r < R; r++)
				if (count[r + 1] > count[r])
					sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1, aux);
		}
	}

	private static void insertion(int[] a, int lo, int hi, int d) {
		for (int i = lo; i <= hi; i++)
			for (int j = i; j > lo && a[j] < a[j - 1]; j--)
				exch(a, j, j - 1);
	}

	// exchange a[i] and a[j]
	private static void exch(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		In in = new In("src/data/words3.txt");
		String[] a = in.readAllStrings();
		// sort the strings
		sort(a);

		// print results
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);

		int[] b = { 5, 4, 3, 2, 1, -3, 234, 212, 255, 0, 256, 300, 400, 3999199,
				-255, -256, -1, -2 };
		sort(b);
		for (int i = 0; i < b.length; i++)
			System.out.println(b[i]);
	}

}

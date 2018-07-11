package study.chapter6;

import depend.In;

/* A data type that computes the suffix array of a string using 3-way
 *  radix quicksort.
 * This implementation uses '\0' as a sentinel and assumes that the charater
 *  '\0' does not appear in the text.
*/
public class SuffixArray {
	private static final int CUTOFF = 5;

	private final char[] text;
	private final int[] index; // index[i] = j means text.substring(j) is ith largest suffix
	private final int n; // number of characters in text

	public SuffixArray(String text) {
		n = text.length();
		this.text = (text + '\0').toCharArray();
		this.index = new int[n];
		for (int i = 0; i < n; i++) {
			index[i] = i;
		}
		sort(0, n - 1, 0);
	}

	private void sort(int lo, int hi, int d) {
		if (hi <= lo + CUTOFF) {
			insertion(lo, hi, d);
			return;
		}

		int lt = lo, gt = hi;
		char v = text[index[lo] + d];
		int i = lo + 1;
		while (i <= gt) {
			char t = text[index[i] + d];
			if (t < v)
				exch(lt++, i++);
			else if (t > v)
				exch(i, gt--);
			else
				i++;
		}

		sort(lo, lt - 1, d);
		if (v > 0)
			sort(lt, gt, d + 1);
		sort(gt + 1, hi, d);
	}

	private void insertion(int lo, int hi, int d) {
		for (int i = lo; i <= hi; i++)
			for (int j = i; j > lo && less(index[j], index[j - 1], d); j--)
				exch(j, j - 1);
	}

	// is text[i+d..n) < text[j+d..n) ?
	private boolean less(int i, int j, int d) {
		if (i == j)
			return false;
		i = i + d;
		j = j + d;
		while (i < n && j < n) {
			if (text[i] < text[j])
				return true;
			if (text[i] > text[j])
				return false;
			i++;
			j++;
		}
		return i > j;
	}

	// exchange index[i] and index[j]
	private void exch(int i, int j) {
		int swap = index[i];
		index[i] = index[j];
		index[j] = swap;
	}

	/**
	 * Returns the length of the input string.
	 * 
	 * @return the length of the input string
	 */
	public int length() {
		return n;
	}

	public int index(int i) {
		if (i < 0 || i >= n)
			throw new IllegalArgumentException();
		return index[i];
	}

	public int lcp(int i) {
		if (i < 1 || i >= n)
			throw new IllegalArgumentException();
		return lcp(index[i], index[i - 1]);
	}

	// longest common prefix of text[i..n) and text[j..n)
	private int lcp(int i, int j) {
		int length = 0;
		while (i < n && j < n) {
			if (text[i] != text[j])
				return length;
			i++;
			j++;
			length++;
		}
		return length;
	}

	/**
	 * Returns the <em>i</em>th smallest suffix as a string.
	 * 
	 * @param i
	 *            the index
	 * @return the <em>i</em> smallest suffix as a string
	 * @throws java.lang.IllegalArgumentException
	 *             unless {@code 0 <= i < n}
	 */
	public String select(int i) {
		if (i < 0 || i >= n)
			throw new IllegalArgumentException();
		return new String(text, index[i], n - index[i]);
	}

	/**
	 * Returns the number of suffixes strictly less than the {@code query} string.
	 * We note that {@code rank(select(i))} equals {@code i} for each {@code i}
	 * between 0 and <em>n</em>-1.
	 * 
	 * @param query
	 *            the query string
	 * @return the number of suffixes strictly less than {@code query}
	 */
	public int rank(String query) {
		int lo = 0, hi = n - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = compare(query, index[mid]);
			if (cmp < 0)
				hi = mid - 1;
			else if (cmp > 0)
				lo = mid + 1;
			else
				return mid;
		}
		return lo;
	}

	// is query < text[i..n) ?
	private int compare(String query, int i) {
		int m = query.length();
		int j = 0;
		while (i < n && j < m) {
			if (query.charAt(j) != text[i])
				return query.charAt(j) - text[i];
			i++;
			j++;

		}
		if (i < n)
			return -1;
		if (j < m)
			return +1;
		return 0;
	}

	public static void main(String[] args) {
		In in = new In("src/data/abra.txt");
		String s = in.readAll().replaceAll("\\s+", " ").trim();
		System.out.println(s);
		SuffixArray suffix = new SuffixArray(s);
		System.out.println("  i ind lcp rnk  select");
		System.out.println("---------------------------");

		for (int i = 0; i < s.length(); i++) {
			int index = suffix.index(i);
			String ith = "\"" + s.substring(index, Math.min(index + 50, s.length())) + "\"";
			int rank = suffix.rank(s.substring(index));
			assert s.substring(index).equals(suffix.select(i));
			if (i == 0) {
				System.out.printf("%3d %3d %3s %3d  %s\n", i, index, "-", rank, ith);
			} else {
				// int lcp = suffix.lcp(suffix2.index(i), suffix2.index(i-1));
				int lcp = suffix.lcp(i);
				System.out.printf("%3d %3d %3d %3d  %s\n", i, index, lcp, rank, ith);
			}
		}
	}

}

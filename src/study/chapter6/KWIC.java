package study.chapter6;

import depend.In;

public class KWIC {

	// Do not instantiate.
	private KWIC() {
	}

	public static String kwic(In in, int context) {
		// read in text
		String text = in.readAll().replaceAll("\\s+", " ");
		int n = text.length();

		// build suffix array
		SuffixArray sa = new SuffixArray(text);

		String res = "";

		// find all occurrences of queries and give context

		String query = "best of times";

		for (int i = sa.rank(query); i < n; i++) {
			int from1 = sa.index(i);
			int to1 = Math.min(n, from1 + query.length());
			if (!query.equals(text.substring(from1, to1)))
				break;
			int from2 = Math.max(0, sa.index(i) - context);
			int to2 = Math.min(n, sa.index(i) + context + query.length());
			res += text.substring(from2, to2) + "\n";

		}
		return res;
	}

	public static void main(String[] args) {
		In in = new In("src/data/tale.txt");
		int context = 30;
		System.out.println(kwic(in, context));
	}

}

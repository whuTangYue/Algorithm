package study.chapter5;

public class BoyerMoore {
	private final int R;
	private int[] right;
	private String pat;

	public BoyerMoore(String pat) {
		this.R = 256;
		this.pat = pat;

		right = new int[R];
		for (int c = 0; c < R; c++) {
			right[c] = -1;
		}
		for (int j = 0; j < pat.length(); j++) {
			right[pat.charAt(j)] = j;
		}
	}

	public int search(String txt) {
		int m = pat.length();
		int n = txt.length();
		int skip;
		for (int i = 0; i <= n - m; i += skip) {
			skip = 0;
			for (int j = m - 1; j >= 0; j--) {
				if (pat.charAt(j) != txt.charAt(i + j)) {
					skip = j - right[txt.charAt(i + j)];
					if (skip < 1)
						skip = 1;
					break;
				}
			}
			if (skip == 0)
				return i;
		}
		return n;
	}

	public static void main(String[] args) {
		String pat = "abc";
		String txt = "abaaaaaaabbbaaaaabcabca";

		BoyerMoore boyermoore = new BoyerMoore(pat);

		int offset = boyermoore.search(txt);
		System.out.println("text:    " + txt);
		System.out.print("pattern: ");
		for (int i = 0; i < offset; i++) {
			System.out.print(" ");
		}
		System.out.println(pat);


	}

}

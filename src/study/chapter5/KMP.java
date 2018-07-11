package study.chapter5;

public class KMP {
	private String pat;
	private int[][] dfa;
	private final int R;

	public KMP(String pat) {
		this.R = 256;
		this.pat = pat;
		int M = pat.length();
		dfa = new int[R][M];
		dfa[pat.charAt(0)][0] = 1;
		for (int x = 0, j = 1; j < M; j++) {
			for (int c = 0; c < R; c++) {
				dfa[c][j] = dfa[c][x];
			}
			dfa[pat.charAt(j)][j] = j + 1;
			x = dfa[pat.charAt(j)][x];
		}
	}

	public int search(String txt) {
		int M = pat.length();
		int N = txt.length();
		int i, j;
		for (i = 0, j = 0; i < N && j < M; i++) {
			j = dfa[txt.charAt(i)][j];
		}
		if (j == M)
			return i - M;
		else
			return N;
	}

	public static void main(String[] args) {
		String pat = "abc";
		String txt = "abaaaaaaabbbaaaaabcabca";

		KMP kmp = new KMP(pat);

		int offset = kmp.search(txt);
		System.out.println("text:    " + txt);
		System.out.print("pattern: ");
		for (int i = 0; i < offset; i++) {
			System.out.print(" ");
		}
		System.out.println(pat);

	}

}

package study.chapter5;

public class StringPattern {
	public static int search(String pat, String txt) {
		int j, M = pat.length();
		int i, N = txt.length();
		for (i = 0, j = 0; i < N && j < M; i++) {
			if (txt.charAt(i) == pat.charAt(j))
				j++;
			else {
				i -= j;
				j = 0;
			}
		}
		if (j == M)
			return i - M; // find match
		else
			return N; // not find
	}

	public static void main(String[] args) {
		String pat = "abc";
		String txt = "adnfc,.znfjdhagfgbabcalkdfnaksjg";
		
		int offset = search(pat,txt);
		System.out.println("text:    " + txt);
		System.out.print("pattern: ");
		for(int i = 0;i<offset;i++) {
			System.out.print(" ");
		}
		System.out.println(pat);

	}

}

package study.chapter5;

import java.math.BigInteger;
import java.util.Random;

public class RabinKarp {
	private String pat;
	private long patHash;
	private int M;
	private long Q;
	private int R;
	private long RM; // R^(M-1) % Q

	public RabinKarp(String pat) {
		this.pat = pat;
		R = 256;
		M = pat.length();
		Q = longRandomPrime();

		RM = 1;
		for (int i = 1; i < M; i++) {
			RM = (R * RM) % Q;
		}
		patHash = hash(pat, M);
	}

	private long hash(String key, int M) {
		long h = 0;
		for (int j = 0; j < M; j++) {
			h = (R * h + key.charAt(j)) % Q;
		}
		return h;
	}

	// Las Vegas version
	private boolean check(String txt, int i) {
		for (int j = 0; j < M; j++) {
			if (pat.charAt(j) != txt.charAt(i + j))
				return false;
		}
		return true;
	}

	public int search(String txt) {
		int N = txt.length();
		if (N < M)
			return N;
		long txtHash = hash(txt, M);
		if ((patHash == txtHash) && check(txt, 0)) {
			return 0;
		}
		for (int i = M; i < N; i++) {
			txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
			txtHash = (txtHash * R + txt.charAt(i)) % Q;
			if (patHash == txtHash) {
				if (check(txt, i - M + 1))
					return i - M + 1;
			}
		}
		return N;
	}

	private static long longRandomPrime() {
		BigInteger prime = BigInteger.probablePrime(31, new Random());
		return prime.longValue();
	}

	public static void main(String[] args) {
		String pat = "abc";
		String txt = "abaaaaaaabbbaaaaabcabca";

		RabinKarp searcher = new RabinKarp(pat);

		int offset = searcher.search(txt);
		System.out.println("text:    " + txt);
		System.out.print("pattern: ");
		for (int i = 0; i < offset; i++) {
			System.out.print(" ");
		}
		System.out.println(pat);

	}

}

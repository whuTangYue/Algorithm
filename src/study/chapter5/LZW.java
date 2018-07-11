package study.chapter5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;

import depend.BinaryStdIn;
import depend.BinaryStdOut;

public class LZW {
	private static final int R = 256; // number of input chars
	private static final int L = 4096; // number of codewords = 2^W
	private static final int W = 12; // codeword width

	// Do not instantiate.
	private LZW() {
	}

	/**
	 * Reads a sequence of 8-bit bytes from standard input; compresses them using
	 * LZW compression with 12-bit codewords; and writes the results to standard
	 * output.
	 */
	public static void compress() {
		String input = BinaryStdIn.readString();
		TST<Integer> st = new TST<Integer>();
		for (int i = 0; i < R; i++)
			st.put("" + (char) i, i);
		int code = R + 1; // R is codeword for EOF

		while (input.length() > 0) {
			String s = st.longestPrefixOf(input); // Find max prefix match s.
			BinaryStdOut.write(st.get(s), W); // Print s's encoding.
			int t = s.length();
			if (t < input.length() && code < L) // Add s to symbol table.
				st.put(input.substring(0, t + 1), code++);
			input = input.substring(t); // Scan past s in input.
		}
		BinaryStdOut.write(R, W);
		BinaryStdIn.close();
		BinaryStdOut.close();
	}

	/**
	 * Reads a sequence of bit encoded using LZW compression with 12-bit codewords
	 * from standard input; expands them; and writes the results to standard output.
	 */
	public static void expand() {
		String[] st = new String[L];
		int i;
		for (i = 0; i < R; i++) {
			st[i] = "" + (char) i;
		}
		st[i++] = " ";

		int codeword = BinaryStdIn.readInt(W);
		String val = st[codeword];
		while (true) {
			BinaryStdOut.write(val);
			codeword = BinaryStdIn.readInt(W);
			if (codeword == R)
				break;
			String s = st[codeword];
			if (i == codeword) {
				s = val + val.charAt(0);
			}
			if (i < L) {
				st[i++] = val + s.charAt(0);
			}
			val = s;
		}
		BinaryStdIn.close();
		BinaryStdOut.close();
	}

	public static void main(String[] args) {
		InputStream in = System.in;
		try {
			System.setIn(new FileInputStream("src/data/tale.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintStream out = System.out;
		try {
			System.setOut(new PrintStream("src/data/tale_huf.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		compress();

		System.setOut(out);

		System.out.println("compress done");
		try {
			System.setIn(new FileInputStream("src/data/tale_huf.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.setOut(new PrintStream("src/data/tale_huf_expand.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		expand();
		System.setOut(out);
		System.out.println("expand done");
		System.setIn(in);
	}
}

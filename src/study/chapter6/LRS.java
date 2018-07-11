package study.chapter6;

import depend.In;

// Longest Repeated Substring
public class LRS {
	
	private LRS() {}
	
	public static String lrs(String text) {
		int n = text.length();
        SuffixArray sa = new SuffixArray(text);
        String lrs = "";
        for (int i = 1; i < n; i++) {
            int length = sa.lcp(i);
            if (length > lrs.length()) {
                // lrs = sa.select(i).substring(0, length);
                lrs = text.substring(sa.index(i), sa.index(i) + length);
            }
        }
        return lrs;
	}

	public static void main(String[] args) {
		In in = new In("src/data/tale.txt");
		String s = in.readAll().replaceAll("\\s+", " ").trim();
        System.out.println("'" + lrs(s) + "'");
	}

}

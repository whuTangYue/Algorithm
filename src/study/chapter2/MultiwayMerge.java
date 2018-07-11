package study.chapter2;

import java.util.Iterator;
import java.util.Scanner;

public class MultiwayMerge {

	public static void merge(Iterator<String>[] s) {
		int N = s.length;
		IndexMinPQ<String> pq = new IndexMinPQ<String>(N);

		for (int i = 0; i < s.length; i++) {
			if (s[i].hasNext()) {
				pq.insert(i + 1, s[i].next());
			}

		}

		while (!pq.isEmpty())

		{
			System.out.print(pq.min()+" ");
			int i = pq.delMin() - 1;

			if (s[i].hasNext()) {
				pq.insert(i + 1, s[i].next());
			}

		}
	}

	public static void main(String[] args) {
		String m1 = "A B C F G I I Z";
		String m2 = "B D H P Q Q";
		String m3 = "A B E F J N";
		String[] m = { m1, m2, m3 };
		Scanner[] s = new Scanner[3];
		for (int i = 0; i < 3; i++) {
			s[i] = new Scanner(m[i]);
		}
		merge(s);

	}

}

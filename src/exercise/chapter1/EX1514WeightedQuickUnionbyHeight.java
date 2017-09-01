package exercise.chapter1;

import java.util.Arrays;
import java.util.Scanner;

public class EX1514WeightedQuickUnionbyHeight {

	private int[] id;
	private int[] ht;
	private int count;

	public EX1514WeightedQuickUnionbyHeight(int N) {
		count = N;
		id = new int[N];
		ht = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			ht[i] = 1;
		}
	}

	public int count() {
		return count;
	}

	public int find(int p) {
		while (p != id[p]) {
			p = id[p];
		}
		return p;
	}

	public void union(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		if (pRoot == qRoot)
			return;
		if (ht[pRoot] < ht[qRoot]) {
			id[pRoot] = qRoot;
		} else if (ht[pRoot] > ht[qRoot]) {
			id[qRoot] = pRoot;
		} else {
			id[qRoot] = pRoot;
			ht[pRoot]++;
		}
		count--;
	}

	public static void main(String[] args) {
		String input = "9-0 3-4 5-8 7-2 2-1 5-7 0-3 4-2";
		Scanner sc = new Scanner(input);
		EX1514WeightedQuickUnionbyHeight UF = new EX1514WeightedQuickUnionbyHeight(10);
		System.out.println(Arrays.toString(UF.id));
		while (sc.hasNext()) {
			String[] union = sc.next().split("-");
			int p = Integer.parseInt(union[0]);
			int q = Integer.parseInt(union[1]);
			UF.union(p, q);
			System.out.println("id: " + Arrays.toString(UF.id));
			System.out.println("ht: " + Arrays.toString(UF.ht));
		}
		sc.close();

	}

}

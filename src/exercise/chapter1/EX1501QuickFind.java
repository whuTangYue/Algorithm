package exercise.chapter1;

import java.util.Arrays;
import java.util.Scanner;

public class EX1501QuickFind {
	int cnt;

	private int[] id;
	private int count;

	public EX1501QuickFind(int N) {
		count = N;
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}

	public int count() {
		return count;
	}

	public int find(int p) {
		return id[p];
	}

	public void union(int p, int q) {
		int pID = find(p);
		int qID = find(q);
		if (pID == qID)
			return;
		for (int i = 0; i < id.length; i++)
			if (id[i] == pID) {
				id[i] = qID;
				cnt++;
			}
		count--;
	}

	public static void main(String[] args) {
		String input = "9-0 3-4 5-8 7-2 2-1 5-7 0-3 4-2";
		Scanner sc = new Scanner(input);
		EX1501QuickFind UF = new EX1501QuickFind(10);
		System.out.println(Arrays.toString(UF.id));
		while (sc.hasNext()) {
			String[] union = sc.next().split("-");
			int p = Integer.parseInt(union[0]);
			int q = Integer.parseInt(union[1]);
			UF.union(p, q);
			System.out.println(Arrays.toString(UF.id)+" "+UF.cnt);
		}
		sc.close();
	}

}

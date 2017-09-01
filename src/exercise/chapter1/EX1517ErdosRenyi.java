package exercise.chapter1;

import java.util.Random;

public class EX1517ErdosRenyi {
	public static int count(int N) {
		if (N < 2)
			throw new IllegalArgumentException("count: N must be > 1");
		EX1513PathCompressWeightedQuickUnion UF = new EX1513PathCompressWeightedQuickUnion(N);
		Random random = new Random();
		int cnt = 0;
		while (UF.count() != 1) {
			int p = random.nextInt(N);
			int q = random.nextInt(N);
			if (!UF.connected(p, q))
				UF.union(p, q);
			cnt++;

		}
		return cnt;
	}

	public static void main(String[] args) {
		int N = 10;
		int cnt = count(N);
		System.out.println(cnt);
		cnt=0;
		for(int i=0;i<10000;i++){
			cnt+=count(N);
		}
		System.out.println(cnt/10000.0);
		System.out.println(0.5*N*Math.log(N)/Math.log(2));
	}

}

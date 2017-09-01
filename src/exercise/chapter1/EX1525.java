package exercise.chapter1;

import java.util.Arrays;
import java.util.Scanner;

public class EX1525 {

	public static void main(String[] args) {
		int T = 10000;

		int cnt = 0;
		long time = 0;

		double timeN = 0.0;
		double timePrev = 1.0;
		for (int N = 2; N < 500; N += N) {
			for (int i = 0; i < T; i++) {
				EX1513PathCompressWeightedQuickUnion UF = new EX1513PathCompressWeightedQuickUnion(N * N);
				String grid = Arrays.toString(EX1518RandomGrid.generate(N));
				String input = grid.substring(1, grid.length() - 1);
				Scanner sc = new Scanner(input);
				sc.useDelimiter(", ");
				long time1 = System.nanoTime();
				while (UF.count() != 1) {
					if (sc.hasNext()) {
						String s = sc.next();
						int p = Integer.parseInt(s.split("-")[0]) - 1;
						int q = Integer.parseInt(s.split("-")[1]) - 1;
						if (!UF.connected(p, q))
							UF.union(p, q);
						cnt++;
					}
				}
				long time2 = System.nanoTime();
				sc.close();
				time += (time2 - time1) / 1000;
			}
			double count = (double) cnt / T;
			timeN = (double) time / T;
			System.out.println(N + " " + count + " " + timeN + " " + timeN / timePrev);
			timePrev = timeN;
		}
	}

}

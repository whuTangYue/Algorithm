package study.chapter1;

import java.util.Random;

public class RandomSeq {
	public static void main(String[] args) {

		int N = 10;
		double lo = 1.0;
		double hi = 9.3;
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			double x = lo + (hi - lo) * random.nextDouble();
			System.out.printf("%.2f\n", x);
		}
	}
}

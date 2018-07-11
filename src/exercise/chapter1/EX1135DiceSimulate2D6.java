package exercise.chapter1;

import java.util.Random;

//dice simulate
//计算两骰子之和的准确概率分布，用实验模拟N次掷骰子，记录出现频率。
//N要多大才能保证实验值和准确值吻合程度达到小数点后三位
public class EX1135DiceSimulate2D6 {

	public static void main(String[] args) {
		double[] theorydist = theory2d6();
		int N = 1;
		boolean match = false;
		double[] simudist = simulate2d6(N);
		while (!match) {
			N *= 10;
			simudist = simulate2d6(N);
			match = isMatch(theorydist, simudist, 0.001);
		}
		for (int i = 0; i < theorydist.length; i++)
			System.out.printf("%-10.5f",theorydist[i]);
		System.out.println();
		for (int i = 0; i < simudist.length; i++)
			System.out.printf("%-10.5f",simudist[i]);
		System.out.println();
		System.out.println(N + "");

	}

	private static double[] theory2d6() {
		int sides = 6;
		double[] dist = new double[2 * sides + 1];
		for (int i = 1; i <= sides; i++) {
			for (int j = 1; j <= sides; j++)
				dist[i + j] += 1.0;
		}
		for (int k = 2; k <= 2 * sides; k++)
			dist[k] /= 36.0;
		return dist;
	}

	private static double[] simulate2d6(int N) {
		int sides = 6;
		double[] dist = new double[2 * sides + 1];
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			int a = 1 + random.nextInt(sides);
			int b = 1 + random.nextInt(sides);
			dist[a + b] += 1.0;
		}
		for (int k = 2; k <= 2 * sides; k++)
			dist[k] /= N;
		return dist;
	}

	private static boolean isMatch(double[] dist0, double[] dist1, double accurary) {
		for (int i = 2; i <= 2 * 6; ++i)
			if (Math.abs(dist0[i] - dist1[i]) >= accurary)
				return false;
		return true;
	}

}

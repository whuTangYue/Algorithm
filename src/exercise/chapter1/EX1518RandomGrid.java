package exercise.chapter1;

import java.util.Arrays;

public class EX1518RandomGrid {
	static class Connection {
		int p;
		int q;

		public Connection(int p, int q) {
			this.p = p;
			this.q = q;
		}

		public String toString() {
			return p + "-" + q;
		}
	}

	public static Connection[] generate(int N) {
		if (N < 2)
			throw new IllegalArgumentException("count: N must be > 1");
		EX1334RandomBag<Connection> randomBag = new EX1334RandomBag<Connection>(2 * 2 * N * (N - 1));
		for (int i = 1; i <= N - 1; i++)
			for (int j = 1; j <= N; j++) {
				Connection c1 = new Connection(i + (j - 1) * N, i + 1 + (j - 1) * N);
				randomBag.add(c1);
				Connection c2 = new Connection(i + 1 + (j - 1) * N, i + (j - 1) * N);
				randomBag.add(c2);
				Connection c3 = new Connection(j + (i - 1) * N, j + i * N);
				randomBag.add(c3);
				Connection c4 = new Connection(j + i * N, j + (i - 1) * N);
				randomBag.add(c4);
			}
		Connection[] grim = new Connection[2 * 2 * N * (N - 1)];
		int i = 0;
		for (Connection c : randomBag) {
			grim[i] = c;
			i++;
		}
		return grim;
	}

	public static void main(String[] args) {
		int N = 3;
		System.out.println(Arrays.toString(generate(N)));

	}

}

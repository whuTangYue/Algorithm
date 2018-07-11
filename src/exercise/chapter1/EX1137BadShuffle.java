package exercise.chapter1;

import java.util.Arrays;
import java.util.Random;

public class EX1137BadShuffle {
	private static void fshuffle(int[] a) {
		int N = a.length;
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			int r = random.nextInt(N - i);
			int temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}
	
	public static int[][] ShuffleTest(int M, int N) {
		int[] a = new int[M];
		int[][] b = new int[M][M];
		for (int j = 0; j < N; j++) {
			for (int i = 0; i < M; i++) {
				a[i] = i;
			}
			fshuffle(a);
			for (int i = 0; i < M; i++) {
				b[a[i]][i]++;
			}
		}
		return b;
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.deepToString(ShuffleTest(5, 10000)));
		System.out.println(10000 / 5);
	}

}

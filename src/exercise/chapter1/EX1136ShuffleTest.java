package exercise.chapter1;

import java.util.Arrays;
import java.util.Random;

//ShuffleTest
//实验检查乱序代码是否能产生预期的效果
//接受参数M，N，将大小为M的数组打乱N次并在每次打乱前将数组重新初始化为a[i]=i，打印一个M*M的表格，对于所有列j，行i表示i在打乱后落到j位置的次数
//数组中所有元素的值应接近于N/M
public class EX1136ShuffleTest {
	public static void shuffle(double[] a) {
		int N = a.length;
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			int r = i + random.nextInt(N - i);
			double temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}

	public static void shuffle(int[] a) {
		int N = a.length;
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			int r = i + random.nextInt(N - i);
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
			shuffle(a);
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

package exercise.chapter1;

import java.util.Arrays;
import java.util.Random;

//ShuffleTest
//ʵ������������Ƿ��ܲ���Ԥ�ڵ�Ч��
//���ܲ���M��N������СΪM���������N�β���ÿ�δ���ǰ���������³�ʼ��Ϊa[i]=i����ӡһ��M*M�ı�񣬶���������j����i��ʾi�ڴ��Һ��䵽jλ�õĴ���
//����������Ԫ�ص�ֵӦ�ӽ���N/M
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

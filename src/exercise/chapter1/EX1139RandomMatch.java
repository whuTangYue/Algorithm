package exercise.chapter1;

import java.util.Arrays;
import java.util.Random;

public class EX1139RandomMatch {

	public static void main(String[] args) {
		randomMatch(1000);
	}

	private static void randomMatch(int T) {
		for (int N = 1000; N <= 1000000; N *= 10) {
			int count = 0;
			double avg = 0;
			for (int k = 0; k < T; k++) {
				int[] arr1 = randomArray(N);
				int[] arr2 = randomArray(N);
				Arrays.sort(arr1);
				Arrays.sort(arr2);
				for (int i = 0; i < arr1.length; i++) {
					int key = arr1[i];
					int find = BinarySearch(key, arr2);
					if (find >= 0)
						count++;
				}
			}
			avg = 1.0 * count / T;
			System.out.println(N + "  " + avg);
		}
	}

	/**
	 * 返回长度为length的随机6位正整数数组
	 *
	 * @param length
	 *            数组长度
	 * @return 随机6位正整数数组
	 */
	private static int[] randomArray(int length) {
		Random random = new Random();
		int[] arr = new int[length];
		for (int i = 0; i < length; ++i)
			arr[i] = 100000 + random.nextInt(1000000);
		return arr;
	}

	public static int BinarySearch(int key, int[] a) {
		int lo = 0;
		int hi = a.length - 1;
		while (lo <= hi) {
			int mid = lo + ((hi - lo) / 2);
			if (key < a[mid])
				hi = mid - 1;
			else if (key > a[mid])
				lo = mid + 1;
			else
				return mid;
		}
		return -1;
	}

}

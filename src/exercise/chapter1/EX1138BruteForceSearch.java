package exercise.chapter1;

import java.util.Arrays;
import java.util.Date;

import depend.In;

public class EX1138BruteForceSearch {

	public static void main(String[] args) {
		int[] whiteList = new In(args[0]).readAllInts();
		int[] list = new In(args[1]).readAllInts();
		long time0 = new Date().getTime();

		for (int i = 0; i < list.length; i++) {
			int key = list[i];
			int find = BruteForceSearch(key, whiteList);
			if (find >= 0)
				System.out.println(key);
		}
		long time1 = new Date().getTime();
		long elapsedTime = time1 - time0;
		System.out.println("暴力查找用时： " + elapsedTime + " ms");
		Arrays.sort(whiteList);
		long time2 = new Date().getTime();
		for (int i = 0; i < list.length; i++) {
			int key = list[i];
			int find = BinarySearch(key, whiteList);
			if (find >= 0)
				System.out.println(key);
		}
		long time3 = new Date().getTime();
		elapsedTime = time3 - time2;
		System.out.println("二分查找用时： " + elapsedTime + " ms");

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

	private static int BruteForceSearch(int key, int[] a) {
		for (int i = 0; i < a.length; i++)
			if (a[i] == key)
				return i;
		return -1;
	}

}

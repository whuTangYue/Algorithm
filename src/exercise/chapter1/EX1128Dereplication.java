package exercise.chapter1;

import java.util.Arrays;

//删除重复元素
//修改BinarySearch 的测试用例，删去排序之后白名单中所有重复元素
public class EX1128Dereplication {

	public static void main(String[] args) {
//		int[] whitelist = new In(args[0]).readAllInts();
		int[] whitelist = { 1, 3, 3, 3, 5, 6, 6, 10, 7, 8, 9, 10 };
		Arrays.sort(whitelist);
		int[] temp = new int[whitelist.length - 1];
		temp[0] = whitelist[0];
		int j = 1;
		for (int i = 1; i < whitelist.length; i++) {
			if (whitelist[i] != whitelist[i - 1]) {
				temp[j] = whitelist[i];
				j++;
			}
		}
		whitelist = new int[j];
		System.arraycopy(temp, 0, whitelist, 0, j);
		System.out.println(Arrays.toString(whitelist));

	}

	public static int rank(int key, int[] a) {
		int lo = 0;
		int hi = a.length - 1;
		while (lo <= hi) {
			int mid = lo + ((hi - lo) >> 1);
			if (key < a[mid])
				hi = mid - 1;
			else if (key > a[mid])
				lo = mid + 1;
			else {
				while (mid >= 0 && a[mid] == key)
					mid--;
				return mid + 1;
			}
		}
		return -1;
	}

	public static int count(int key, int[] a) {
		int count = 0;
		int lower = rank(key, a);
		while (a[lower + count] == key && (lower + count + 1 < a.length))
			count++;
		return count;
	}
}

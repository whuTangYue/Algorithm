package exercise.chapter1;

import java.util.Arrays;

//��ֵ��
//rank():����һ������һ����������������Ϊ����������������С�ڸü���Ԫ������
//count():���������е��ڸü���Ԫ������

//eclipse
//in run/debug settings
//set program argument to    largeW.txt +/- 
//set input file to largeT.txt
public class EX1129KeyCount {
	public static void main(String[] args) {
		// int[] whitelist = new In(args[0]).readAllInts();
		int[] whitelist = { 1, 3, 3, 3, 5, 6, 6, 7 };
		Arrays.sort(whitelist);
		int key = 3;
		int rank = rank(key, whitelist);
		if (rank >= -1)
			System.out.println("key:" + key + " rank:" + rank + " count:" + count(key, whitelist));
		
//		System.out.println("Input a key");
//		while (!StdIn.isEmpty()) {
//
//			int key = StdIn.readInt();
//			int rank = rank(key, whitelist);
//			if (rank >= -1)
//				System.out.println("key:" + key + " rank:" + rank + " count:" + count(key, whitelist));
//			System.out.println("Input a key");
//		}

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
		if (lower >= 0)
			while (a[lower + count] == key && (lower + count + 1 < a.length))
				count++;
		return count;
	}

}

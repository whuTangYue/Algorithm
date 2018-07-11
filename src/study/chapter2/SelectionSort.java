package study.chapter2;

import java.util.Random;

//选择排序
//大约N^2/2次比较和N次交换
//运行时间与输入无关
//数据移动次数最少
public class SelectionSort extends Sort{
	public static void sort(double[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int min = i;
			for (int j = i + 1; j < N; j++)
				if (less(a[j], a[min]))
					min = j;
			exch(a, i, min);
		}
	}
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int min = i;
			for (int j = i + 1; j < N; j++)
				if (less(a[j], a[min]))
					min = j;
			exch(a, i, min);
		}

	}

	public static void main(String[] args) {
		Integer[] a = new Integer[10];
		Random random = new Random();
		for (int t = 0; t < 10; t++) {
			for (int i = 0; i < 10; i++) {
				a[i] = random.nextInt(30);
			}
			sort(a);
		}
		assert isSorted(a);
		show(a);

	}
}

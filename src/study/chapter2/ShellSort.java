package study.chapter2;

import java.util.Random;

//希尔排序
//较高效率的排序算法中代码比较简单，在中等数量输入下表现良好
//递增序列h=3*h+1
public class ShellSort extends Sort {

	public static <T extends Comparable<T>> void sort(T[] a) {
		int N = a.length;
		int h = 1;
		while (h < N / 3)
			h = 3 * h + 1;
		while (h >= 1) {
			for (int i = h; i < N; i++) {
				T tmp = a[i];
				int j = i;
				for (j = i; j >= h && less(tmp, a[j - h]); j -= h)
					a[j] = a[j - h];
				a[j] = tmp;
				
				
//				for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
//					exch(a, j, j - h);
			}
			h = h / 3;
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

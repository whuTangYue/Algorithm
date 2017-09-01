 package study.chapter2;

import java.util.Random;

//插入排序
//交换次数与数组中倒置数相同，比较次数大于等于倒置数量，小于等于倒置数+数组大小-1
//对部分有序数组十分高效，也很适合小规模数组
public class InsertionSort extends Sort {
	public static <T extends Comparable<T>> void sort(T[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			//较大元素右移而不是交换，访问数组次数减半，大幅提高速度
			T tmp = a[i];
			int j = i;
			for (j = i; j > 0 && less(tmp, a[j - 1]); j--)
				a[j] = a[j - 1];
			a[j] = tmp;
			
//			for (int j = i; j > 0 && less(a[j], a[j - 1]); j--)
//				exch(a,j,j-1);
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

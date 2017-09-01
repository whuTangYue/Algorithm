package study.chapter2;

import java.util.Random;

//ѡ������
//��ԼN^2/2�αȽϺ�N�ν���
//����ʱ���������޹�
//�����ƶ���������
public class SelectionSort extends Sort {

	public static <T extends Comparable<T>> void sort(T[] a) {
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

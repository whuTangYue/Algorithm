 package study.chapter2;

import java.util.Random;

//��������
//���������������е�������ͬ���Ƚϴ������ڵ��ڵ���������С�ڵ��ڵ�����+�����С-1
//�Բ�����������ʮ�ָ�Ч��Ҳ���ʺ�С��ģ����
public class InsertionSort extends Sort {
	public static <T extends Comparable<T>> void sort(T[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			//�ϴ�Ԫ�����ƶ����ǽ�������������������룬�������ٶ�
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

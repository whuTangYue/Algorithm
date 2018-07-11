package exercise.chapter2;

import java.util.Random;

import study.chapter1.Queue;
import study.chapter2.Sort;

//随机测试性能略弱于MergeSort，远弱于Arrays::sort
//数组偏向有序时性能应该更好，没测试
public class EX2216NaturalMergeSort extends study.chapter2.Sort {

	// Merge src[l:m] and src[m+1,r] to dest[l,r]
	private static void Merge(double[] src, double[] dest, int l, int m,
			int r) {
		int i = l, j = m + 1;
		while ((i <= m) && (j <= r)) {
			if (src[i] <= src[j]) {
				dest[l++] = src[i++];
			} else {
				dest[l++] = src[j++];
			}
		}
		if (i > m) {
			for (; j <= r; j++) {
				dest[l++] = src[j];
			}
		} else {
			for (; i <= m; i++) {
				dest[l++] = src[i];
			}
		}

	}

	private static void Pass(double[] a, Queue<Integer> q) {
		q.enqueue(0);
		for (int i = 1; i < a.length; i++) {
			if (a[i] < a[i - 1]) {
				q.enqueue(i);
			}
		}
	}

	private static void MergeSort(double[] src, double[] dest,
			Queue<Integer> queue) {
		int l, m, r;
		Queue<Integer> nq = new Queue<Integer>();
		while (!queue.isEmpty()) {
			l = queue.dequeue();
			if (!queue.isEmpty()) {
				m = queue.dequeue() - 1;
				if (!queue.isEmpty()) {
					r = queue.peek() - 1;
				} else {
					r = src.length - 1;
				}
				Merge(src, dest, l, m, r);
			} else {
				for (int i = l; i < src.length; i++) {
					dest[i] = src[i];
				}
			}
			nq.enqueue(l);
		}
		if(nq.size()==1) {
			System.arraycopy(dest, 0, src, 0, src.length);
			return;
		}

		MergeSort(dest,src,nq);
	}

	public static void sort(double[] a) {
		Queue<Integer> q = new Queue<Integer>();
		Pass(a, q);
		double[] b = a.clone();
		MergeSort(b,a,q);

	}

	public static void main(String[] args) {
		double[] a = new double[15];
		Random random = new Random();
		for (int t = 0; t < 1; t++) {
			for (int i = 0; i < 15; i++) {
				a[i] = random.nextInt(100);
			}
			sort(a);
		}
		System.out.println(Sort.isSorted(a));
		show(a);
	}

}

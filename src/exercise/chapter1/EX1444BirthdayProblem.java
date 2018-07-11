package exercise.chapter1;

import java.util.Random;

//ex1.4.44 �������⡣
//�������н���һ������N������һϵ��0��N-1�����������ʵ����֤������һ���ظ������ǰ���ɵ���������Ϊsqrt(pi*n/2)
// ������������ӦΪsum(k=1~N)(N!/((N-k)!N^k)),ԼΪsqrt(pi*n/2)-1/3+1/12sqrt(pi*n/2)-...
// ��N��̫��ʱ����Ŀ����ֵ��̶����1/3
public class EX1444BirthdayProblem {

	public static double predicted(int n) {
		return (Math.sqrt(Math.PI * n / 2.0)-1.0/3.0);
	}

	public static double experienced(int n) {
		int Times = 100000;
		int cnt = 0;
		int[] a = new int[1000];
		boolean[] b = new boolean[n];

		Random random = new Random();
		for (int t = 0; t < Times; t++) {
			for (int i = 0; i < n; i++)
				b[i] = false;
			for (int i = 0; i < 1000; i++) {
				a[i] = random.nextInt(n);
				if (b[a[i]]) {
					cnt += i;
					break;
				} else
					b[a[i]] = true;
			}
		}
		return (double) (cnt) / Times;
	}

	public static void main(String[] args) {
		int n = 366;
		System.out.println(predicted(n));
		System.out.println(experienced(n));

	}

}

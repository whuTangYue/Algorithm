package exercise.chapter1;

import java.util.Random;

//ex1.4.44 生日问题。
//从命令行接受一个整数N，产生一系列0到N-1的随机整数，实验验证产生第一个重复随机数前生成的整数数量为sqrt(pi*n/2)
// 更正：该数量应为sum(k=1~N)(N!/((N-k)!N^k)),约为sqrt(pi*n/2)-1/3+1/12sqrt(pi*n/2)-...
// 在N不太大时与题目所给值会固定相差1/3
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

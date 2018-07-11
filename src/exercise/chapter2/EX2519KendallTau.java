package exercise.chapter2;

import depend.StdRandom;

public class EX2519KendallTau {

	// return Kendall tau distance between two permutations
	// public static long distance(int[] a, int[] b) {
	// if (a.length != b.length) {
	// throw new IllegalArgumentException("Array dimensions disagree");
	// }
	// int n = a.length;
	//
	// int[] ainv = new int[n];
	// for (int i = 0; i < n; i++)
	// ainv[a[i]] = i;
	//
	// int[] bnew = new int[n];
	// for (int i = 0; i < n; i++)
	// bnew[i] = ainv[b[i]];
	//
	// return EX2219InverseNumber.cntInverseNumber(bnew);
	// }

	public static int distance(int[] a, int[] b) {
		int n = a.length;
		String[] ar = new String[(n - 1) * n / 2];
		String[] br = new String[(n - 1) * n / 2];
		for (int i = 0, k = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) { //找出二元约束集
				ar[k] = a[i] + " " + a[j];
				br[k++] = b[i] + " " + b[j];
			}
		}
		int nReverse = 0;
		for (int i = 0; i < ar.length; i++) {//查找两字符串相同二元约束集个数
			for (int j = 0; j < br.length; j++) {
				if (ar[i].equals(br[j])) {
					nReverse++;
				}
			}
		}
		return (ar.length - nReverse);
	}

	// return a random permutation of size n
	public static int[] permutation(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = i;
		StdRandom.shuffle(a);
		return a;
	}

	public static void main(String[] args) {

		// two random permutation of size n
		// int n = 10;
		// int[] a = EX2519KendallTau.permutation(n);
		// int[] b = EX2519KendallTau.permutation(n);

		// print initial permutation
		// for (int i = 0; i < n; i++)
		// System.out.println(a[i] + " " + b[i]);
		// System.out.println();

		int[] a = { 1, 2, 3, 4, 5 };
		int[] b = { 3, 4, 1, 2, 5 };

		System.out.println("inversions = " + EX2519KendallTau.distance(a, b));
	}
}
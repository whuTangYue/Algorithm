package exercise.chapter2;

import java.util.Arrays;

public class EX2219InverseNumber {
	private static int[] aux;

	private static int merge(int[] a, int lo, int mid, int hi) {
		int InverseNum = 0;
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}
		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (aux[j] < aux[i]) {
				a[k] = aux[j++];
				InverseNum += mid - i + 1;
			} else
				a[k] = aux[i++];
		}
		//System.out.println(Arrays.toString(a));
		//System.out.println(InverseNum);
		return InverseNum;
	}
	
	public static int cntInverseNumber(int[] a) {
		aux = new int[a.length];
		int InverseNum = sort(a,0,a.length-1);
		return InverseNum;
	}
	
	private static int sort(int[] a,int lo,int hi) {
		if(hi<=lo) return 0;
		int mid = lo+(hi-lo)/2;
		int InverseNum = 0;
		InverseNum +=sort(a,lo,mid);
		InverseNum +=sort(a,mid+1,hi);
		InverseNum +=merge(a,lo,mid,hi);
		return InverseNum;
	}

	public static void main(String[] args) {
		int[] a = {1, 7, 2, 9, 6, 4, 5, 3};
		int InverseNumber = cntInverseNumber(a);
		System.out.println(InverseNumber);
		System.out.println(Arrays.toString(a));

	}

}

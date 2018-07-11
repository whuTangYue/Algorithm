package exercise.chapter1;

import java.util.Random;

public class EX1403DoublingTest {
	public static double timeTrial(int N) {
		int MAX = 1000000;
		int[] a = new int[N];
		Random random = new Random();
		for(int i=0;i<N;i++)
			a[i]=random.nextInt(MAX);
		long time1 = System.currentTimeMillis();
		EX1415ThreeSumFaster.TwoSumFaster(a, 0);
		long time2 = System.currentTimeMillis();
		return ((double)(time2-time1))/1000;
	}
	
	public static void main(String[] args){
		for(int N = 250;true;N+=N){
			double time = timeTrial(N);
			System.out.println(N+" "+time);
		}
	}

}

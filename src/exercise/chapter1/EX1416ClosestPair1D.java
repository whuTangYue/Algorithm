package exercise.chapter1;

import java.util.Arrays;


public class EX1416ClosestPair1D {
	
	public static double[] closestPair(double[] nums){
		if(nums==null||nums.length<2) return new double[0];
		if(nums.length==2) return nums;
		Arrays.sort(nums);//O(nlogn)
		double[] m=new double[2];
		double min=Double.POSITIVE_INFINITY;
		double d=0;
		for(int i =0;i<nums.length-1;i++){
			d=Math.abs(nums[i+1]-nums[i]);
			if(d==0) return new double[]{nums[i],nums[i+1]};
			else if (d<min){m[0]=nums[i];m[1]=nums[i+1];min=d;}
		} //O(n)
		return m;
		
	}

	public static void main(String[] args) {
		double[] nums = {1.1,2.2,3.2,4.3,3.4,2.5,9.4,1.5,3.6,4.9,10.0};
		System.out.println(Arrays.toString(nums));
		System.out.println(Arrays.toString(closestPair(nums)));

	}

}

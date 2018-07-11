package study.chapter1;

public class Euclid {
	public static long gcd(long a,long b){
		if(b==0) return a;
		else return gcd(b,a%b);
	}
	public static int gcd(int a,int b){
		if(b==0) return a;
		else return gcd(b,a%b);
	}
	public static void main(String[]args){
		System.out.println(Long.MAX_VALUE-gcd(Long.MAX_VALUE,Long.MAX_VALUE-1));
		System.out.println(Long.MAX_VALUE%(Long.MAX_VALUE-1));
		System.out.println(gcd(77,2));
	}
}

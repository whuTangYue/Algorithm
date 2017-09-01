package study.chapter1;
import java.math.BigInteger;

public class Factorial {
	public static BigInteger factorial(long n){return recfact(1,n);}
	private static BigInteger recfact(long start,long n){
		long i;
		if(n<=16){
			BigInteger r=new BigInteger(start+"");
			for (i=start+1;i<start+n;i++) {
				BigInteger j=new BigInteger(i+"");
				r=r.multiply(j);
			}
			return r;
		}
		i=n/2;
		return recfact(start,i).multiply(recfact(start+i,n-i));
	}

	public static void main(String[] args) {
		for(int N=0;N<40;N++)
//			int N=47;
				System.out.println(N+" "+factorial(N));
	}
}

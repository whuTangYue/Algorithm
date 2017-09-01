package exercise.chapter1;

import java.math.BigInteger;

import study.chapter1.Matrix;


//fibonacci
public class EX1119Fibonacci {

	public static void main(String[] args) {
		for (int N = 0; N < 100; N++)
			// int N=47;
			System.out.println(N + " " + fib(N));
	}

	public static BigInteger fib(int N) {
		if (N == 0)
			return BigInteger.ZERO;
		if (N == 1)
			return BigInteger.ONE;
		BigInteger[][] F = new BigInteger[2][2];
		F[0][0] = BigInteger.ONE;
		F[0][1] = BigInteger.ONE;
		F[1][0] = BigInteger.ONE;
		F[1][1] = BigInteger.ZERO;
		BigInteger[][] an = new BigInteger[2][2];
		an = MatrixPow(F, N);
		BigInteger f = an[1][0];
		return f;
	}
	
	private static BigInteger[][] MatrixPow(BigInteger[][] m,int n){
		BigInteger[][] result = new BigInteger[m.length][m[0].length];
		
		for(int i=0;i<result.length;i++){
			for(int j=0;j<result.length;j++)
				result[i][j]=BigInteger.ZERO;
			result[i][i]=BigInteger.ONE;
		}
		BigInteger[][] tmp =m;
		for(;n>0;n>>>=1)
		{
			if ((n & 1)==1)
				result = Matrix.mult(result, tmp);
			tmp = Matrix.mult(tmp, tmp);
		}
		return result;
	}

}

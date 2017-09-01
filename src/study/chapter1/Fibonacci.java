package study.chapter1;
import java.math.BigInteger;

//Fibonacci算法的两种实现
//一为"动态规划"(F) 线性复杂度
//一为"分治"(fib) log2(N)复杂度，采用BigInteger大数类
//BigInteger数组要初始化为0
public class Fibonacci {
	
	public static void main(String[] args) {
		for(int N=0;N<100;N++)
//		int N=47;
			System.out.println(N+" "+fib(N));
	}
	
	public static long F(int N){
		if(N==0) return 0;
		if(N==1) return 1;
//		return F(N-1)+F(N-2);
		long[] ans = new long[N+1];
		ans[0]=0;
		ans[1]=1;
		for(int i=2; i<=N; i++)  
	        ans[i] = ans[i-1] + ans[i-2];  
		return ans[N];
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
	public static BigInteger fib(int N){
		if(N==0) return BigInteger.ZERO;
		if(N==1) return BigInteger.ONE;
		BigInteger[][] F = new BigInteger[2][2];
		F[0][0]=BigInteger.ONE;
		F[0][1]=BigInteger.ONE;
		F[1][0]=BigInteger.ONE;
		F[1][1]=BigInteger.ZERO;
		BigInteger[][] an = new BigInteger[2][2];
		an=MatrixPow(F,N);
		BigInteger f=an[1][0];
		return f;
	}
	
	

}

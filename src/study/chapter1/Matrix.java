package study.chapter1;
import java.math.BigInteger;

import depend.StdRandom;
public class Matrix {
	// return a random m-by-n matrix with values between 0 and 1
    public static double[][] random(int m, int n) {
        double[][] a = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = StdRandom.uniform(0.0, 1.0);
        return a;
    }

    // return n-by-n identity matrix I
    public static double[][] identity(int n) {
        double[][] a = new double[n][n];
        for (int i = 0; i < n; i++)
            a[i][i] = 1;
        return a;
    }
	//一维数组点乘
	public static double dot(double[] x,double[] y){
		double result = 0;
		if(x.length==y.length){
			for(int i=0;i<x.length;i++)
				result+=x[i]*y[i];
			return result;
			}
		else  throw new IllegalArgumentException();
	}
	//矩阵与矩阵之积
	public static double[][] mult(double[][] a,double[][] b){
		if(a[0].length == b.length)
		{
			double[][] result = new double[a.length][b[0].length];
			for(int i = 0;i<a.length ;i++)
			{
				for(int j = 0;j<b[0].length;j++)
				{
					double temp = 0.0;
					for(int x = 0;x<b.length;x++)
					{
						temp+=a[i][x]*b[x][j];

					}
					result[i][j] = temp;
				}
			}
			return result;
		}
		else throw new IllegalArgumentException();
	}
	public static long[][] mult(long[][] a,long[][] b){
		if(a[0].length == b.length)
		{
			long[][] result = new long[a.length][b[0].length];
			for(int i = 0;i<a.length ;i++)
			{
				for(int j = 0;j<b[0].length;j++)
				{
					long temp = 0;
					for(int x = 0;x<b.length;x++)
					{
						temp+=a[i][x]*b[x][j];

					}
					result[i][j] = temp;
				}
			}
			return result;
		}
		else throw new IllegalArgumentException();
	}
	public static BigInteger[][] mult(BigInteger[][] a,BigInteger[][] b){
		if(a[0].length == b.length)
		{
			BigInteger[][] result = new BigInteger[a.length][b[0].length];
			for(int i = 0;i<a.length ;i++)
			{
				for(int j = 0;j<b[0].length;j++)
				{
					BigInteger temp = new BigInteger("0");
					for(int x = 0;x<b.length;x++)
					{
						temp=temp.add(a[i][x].multiply(b[x][j]));

					}
					result[i][j] = temp;
				}
			}
			return result;
		}
		else throw new IllegalArgumentException();
	}
	//转置矩阵
	public static double[][] transpose(double[][] a){
		double[][] b= new double[a.length][a[0].length];
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++){
				b[j][i]=a[i][j];
			}
		}
		return b;
	}
	//矩阵与向量之积
	public static double[][] mult(double[][] a,double[] x){
		if(a[0].length == x.length)
		{
			double[][] result = new double[a.length][x.length];
			for(int i=0;i<a.length ;i++)
			{
				for(int j=0;j<x.length;j++)
				{
					double temp=0.0;
					for(int xi=0;xi<x.length;xi++)
					{
						temp+=a[i][xi]*x[j];

					}
					result[i][j] = temp;
				}
			}
			return result;
		}
		else throw new IllegalArgumentException();
	}
	//向量与矩阵之积
	public static double[] mult(double[] y,double[][] a){
		if(y.length == a.length)
		{
			double[] result = new double[a[0].length];
			for(int i = 0;i<y.length ;i++)
			{
				for(int j = 0;j<a[0].length;j++)
				{
					double temp = 0.0;
					for(int yi = 0;yi<a.length;yi++)
					{
						temp+=y[i]*a[yi][j];

					}
					result[i] = temp;
				}
			}
			return result;
		}
		else throw new IllegalArgumentException();
	}
}

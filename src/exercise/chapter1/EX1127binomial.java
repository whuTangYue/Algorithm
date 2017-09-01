package exercise.chapter1;

//binomial
//二项分布
public class EX1127binomial {

	public static double binomial(int N,int k,double p){
		double v[][]=new double[N+1][k+1];
		for(int i=0;i<=N;i++)
			for(int j=0;j<=k;j++)
			{
				if(i==0&&j==0) v[i][j]=1;
				if(i>0&&j==0) v[i][j]=(1.0-p)*v[i-1][j];
				if(i>0&&j>0) v[i][j]=(1.0-p)*v[i-1][j]+p*v[i-1][j-1];
			}
		return v[N][k];
	}
//	public static double binomial(int N,int k,double p){
//		if(N==0&&k==0) return 1.0;
//		if(N<0||k<0) return 0.0;
//		return (1.0-p)*binomial(N-1,k,p)+p*binomial(N-1,k-1,p);
//	}
	public static void main(String[] args){
//		int n=Integer.parseInt(args[0]),
//			k=Integer.parseInt(args[1]);
//		double p=Double.parseDouble(args[2]);
		System.out.println(binomial(10,5,0.25));
	}


}

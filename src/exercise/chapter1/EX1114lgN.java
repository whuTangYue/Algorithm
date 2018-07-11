package exercise.chapter1;

//lg(N) output an integer no larger than log2(N),not use Math lib
public class EX1114lgN {

	public static void main(String[] args) {
		System.out.println(intLog2(65)+"");

	}
	
	public static int intLog2(int N){
		int n;
		for(n=0;N!=0;n++){
			N=N>>1;
		}
		return n-1;
	}

}

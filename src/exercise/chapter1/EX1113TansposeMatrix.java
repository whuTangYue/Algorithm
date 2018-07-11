package exercise.chapter1;

public class EX1113TansposeMatrix {

	public static void main(String[] args) {
		int[][] a=new int[3][4];
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++)
				a[i][j]=(int) (Math.random()*10);
		}
		int[][] b=transpose(a);
		printArray(a);
		System.out.print("transpose:\n");
		printArray(b);

	}
	private static void printArray(int[][] a) {
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++){
				System.out.print(a[i][j]+" ");

			}
			System.out.print("\n");
		}
	}
	//转置矩阵
	private static int[][] transpose(int[][] a){
		int[][] b= new int[a[0].length][a.length];
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++){
				b[j][i]=a[i][j];
			}
		}
		return b;
	}
}

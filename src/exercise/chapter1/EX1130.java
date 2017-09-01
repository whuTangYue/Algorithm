package exercise.chapter1;

//数组练习
//N*N 数组a[][] 当i，j互质时a[i][j]=true,否则false
public class EX1130 {

	public static void main(String[] args) {
		int N = 9;
		boolean[][] boolArray = new boolean[N][N];
		for (int i = 0; i < boolArray.length; i++) {
			for (int j = 0; j < boolArray[0].length; j++) {
				if (gcd(i, j) == 1)
					boolArray[i][j] = true;
				else
					boolArray[i][j] = false;
			}
		}
		for (int i = 0; i < boolArray.length; i++) {
			for (int j = 0; j < boolArray[i].length; j++) {
				System.out.print(boolArray[i][j] + " ");
			}
			System.out.println();
		}

	}

	public static int gcd(int a, int b) {
		if(a==0||b==0) return -1;
		int t;
		while (a % b != 0) {
			t = a % b;
			a = b;
			b = t;
		}
		return b;
	}

}

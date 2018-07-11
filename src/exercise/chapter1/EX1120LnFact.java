package exercise.chapter1;


//ln(N!)
public class EX1120LnFact {

	public static void main(String[] args) {
		for (int N = 1; N < 100; N++)
			System.out.println(LnFact(N));

	}

	private static double LnFact(int N) {
		if (N == 1)
			return 0;
		return Math.log(N) + LnFact(N - 1);
	}

}

package study.chapter1;

import java.util.Scanner;

public class Average {
	public static void main(String[] args) {
		String input = "1.1 2.3 3.4 4.5 5.6";
		Scanner sc = new Scanner(input);
		double sum = 0.0;
		int cnt = 0;
		while (sc.hasNext()) {
			sum += sc.nextDouble();
			cnt++;
		}
		double avg = sum / cnt;
		System.out.printf("Average is %.5f\n", avg);
		sc.close();
	}

}

package study.chapter1;

import java.util.Scanner;

public class Reverse {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		String input = "1 2 3 4 5";
		Scanner sc = new Scanner(input);
		while(sc.hasNext())
			stack.push(sc.nextInt());
		for(int i :stack)
			System.out.println(i);
		sc.close();

	}

}

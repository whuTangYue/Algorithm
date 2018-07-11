package study.chapter1;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		String[] strings=str.split(" ");
		System.out.println(strings[strings.length-1].length());
		scan.close();

	}

}

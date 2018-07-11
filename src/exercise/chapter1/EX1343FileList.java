package exercise.chapter1;

import java.io.File;

public class EX1343FileList {

	public static void printDirectory(File file, int n) {
		for (int i = 0; i < n; i++)
			System.out.print("-");
		System.out.println(file.getName());
		if (file.isDirectory()) {
			File[] childfiles = file.listFiles();
			for (File childfile : childfiles) {
				printDirectory(childfile, n + 5);
			}
		}
	}

	public static void main(String[] args) {
		printDirectory(new File("D:\\books\\"), 0);

	}

}

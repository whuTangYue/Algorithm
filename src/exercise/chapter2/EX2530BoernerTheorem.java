package exercise.chapter2;

import java.util.Arrays;

import depend.StdRandom;

public class EX2530BoernerTheorem {
	// insertion sort every column , small to big
	public static void sortColumn(int[][] matrix) {
		for (int j = 0; j < matrix[0].length; j++) {
			int min = 0;
			for (int i = 0; i < matrix.length; i++) {
				if (matrix[i][j] < matrix[min][j])
					min = i;
			}
			int tmp = matrix[min][j];
			matrix[min][j] = matrix[0][j];
			matrix[0][j] = tmp;
			for (int i = 0; i < matrix.length; i++) {
				int t = matrix[i][j];
				int k = i;
				for (k = i; k > 0 && t < matrix[k - 1][j]; k--)
					matrix[k][j] = matrix[k - 1][j];
				matrix[k][j] = t;
			}
		}
	}

	public static void sortRow(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			Arrays.sort(matrix[i]);
		}
	}

	public static boolean checkColumn(int[][] matrix) {
		for (int j = 0; j < matrix[0].length; j++) {
			for (int i = 1; i < matrix.length; i++) {
				if (matrix[i][j] < matrix[i - 1][j])
					return false;
			}
		}
		return true;
	}

	public static boolean checkRow(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[i][j] < matrix[i][j - 1])
					return false;
			}
		}
		return true;
	}

	public static int[][] generator(int rows, int cols) {
		int[][] a = new int[rows][cols];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				a[i][j] = StdRandom.uniform(100);
		return a;
	}

	public static void main(String[] args) {
		int rows = 30;
		int cols = 40;
		int[][] matrix = generator(rows, cols);
		sortColumn(matrix);
		System.out.println(checkColumn(matrix));
		sortRow(matrix);
		System.out.println(checkColumn(matrix));

	}

}

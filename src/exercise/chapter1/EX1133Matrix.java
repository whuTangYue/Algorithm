package exercise.chapter1;

//Matrix
//矩阵类
public class EX1133Matrix {

	public static void main(String[] args) {
		System.out.println("-------- 向量点乘 ---------");
		double[] a0 = { 1, 2, 3 };
		double[] b0 = { 4, 5, 6 };
		double res0 = dot(a0, b0);
		System.out.println(res0);

		System.out.println("-------- 矩阵乘法 ---------");
		double[][] a1 = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
		double[][] b1 = { { 1, 2, 3 }, { 4, 5, 6 } };
		double[][] res1 = mult(a1, b1);
		dispMatrix(res1);

		System.out.println("-------- 矩阵转置 ---------");
		double[][] a2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		double[][] c2 = transpose(a2);
		dispMatrix(a2);
		System.out.println("transpose:");
		dispMatrix(c2);

		System.out.println("----- 矩阵和向量之积 ------");
		double[][] a3 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		double[] b3 = { 1, 2, 3 };
		double[][] c3 = mult(a3, b3);
		dispMatrix(c3);

		System.out.println("----- 向量和矩阵之积 ------");
		double[] a4 = { 1, 2, 3 };
		double[][] b4 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		double[] c4 = mult(a4, b4);
		dispMatrix(c4);

	}

	// 一维数组点乘
	public static double dot(double[] x, double[] y) {
		double result = 0;
		if (x.length == y.length) {
			for (int i = 0; i < x.length; i++)
				result += x[i] * y[i];
			return result;
		} else
			throw new IllegalArgumentException();
	}

	// 矩阵与矩阵之积
	public static double[][] mult(double[][] a, double[][] b) {
		if (a[0].length == b.length) {
			double[][] result = new double[a.length][b[0].length];
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < b[0].length; j++) {
					double temp = 0.0;
					for (int x = 0; x < b.length; x++) {
						temp += a[i][x] * b[x][j];

					}
					result[i][j] = temp;
				}
			}
			return result;
		} else
			throw new IllegalArgumentException();
	}

	// 转置矩阵
	public static double[][] transpose(double[][] a) {
		double[][] b = new double[a.length][a[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				b[j][i] = a[i][j];
			}
		}
		return b;
	}

	// 矩阵与向量之积
	public static double[][] mult(double[][] a, double[] x) {
		if (a[0].length == x.length) {
			double[][] result = new double[a.length][x.length];
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < x.length; j++) {
					double temp = 0.0;
					for (int xi = 0; xi < x.length; xi++) {
						temp += a[i][xi] * x[j];

					}
					result[i][j] = temp;
				}
			}
			return result;
		} else
			throw new IllegalArgumentException();
	}

	// 向量与矩阵之积
	public static double[] mult(double[] y, double[][] a) {
		if (y.length == a.length) {
			double[] result = new double[a[0].length];
			for (int i = 0; i < y.length; i++) {
				for (int j = 0; j < a[0].length; j++) {
					double temp = 0.0;
					for (int yi = 0; yi < a.length; yi++) {
						temp += y[i] * a[yi][j];

					}
					result[i] = temp;
				}
			}
			return result;
		} else
			throw new IllegalArgumentException();
	}

	public static void dispMatrix(double[] Matrix) {
		for (int i = 0; i < Matrix.length; i++)
			System.out.printf("%-10.3f\n", Matrix[i]);
	}

	public static void dispMatrix(double[][] Matrix) {
		for (int i = 0; i < Matrix.length; i++) {
			for (int j = 0; j < Matrix[i].length; j++)
				System.out.printf("%-10.3f", Matrix[i][j]);
			System.out.println();
		}
	}

}

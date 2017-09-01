package study.chapter2;

import java.util.Random;

import exercise.depend.StdDraw;

public class DrawDoubleArray {
	public static void Draw(Double[] a) {
		int n = a.length;

		StdDraw.setCanvasSize(1024, 512);
		StdDraw.setXscale(0, n);

		for (int i = 0; i < n; i++) {
			StdDraw.filledRectangle(0.4 + i, a[i] / 2, 0.4, a[i] / 2);
		}

	}

	public static void main(String[] args) {
		int N = 20;
		Double[] a = new Double[N];
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			a[i] = random.nextDouble();
		}
		Draw(a);

	}

}

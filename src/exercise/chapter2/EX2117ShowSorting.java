package exercise.chapter2;

import java.util.Random;

import exercise.depend.StdDraw;

public class EX2117ShowSorting {
	public static void drawDoubleArray(Double[] a) {
		int n = a.length;

		StdDraw.setCanvasSize(1024, 512);
		StdDraw.setXscale(0, n);

		for (int i = 0; i < n; i++) {
			StdDraw.filledRectangle(0.4 + i, a[i] / 2, 0.4, a[i] / 2);
		}
	}

	public static Double[] randomDoubleArray(int N) {
		Double[] a = new Double[N];
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			a[i] = random.nextDouble();
		}
		return a;
	}

	public static void show(Double[] a) {
		int n = a.length;
		StdDraw.show(1000);
		StdDraw.clear();
		for (int i = 0; i < n; i++) {
			StdDraw.filledRectangle(0.4 + i, a[i] / 2, 0.4, a[i] / 2);
		}
		
	}
	
	public static void shellSort(Double[] a){
		int N = a.length;
		int h = 1;
		while (h < N / 3)
			h = 3 * h + 1;
		while (h >= 1) {
			for (int i = h; i < N; i++) {
				Double tmp = a[i];
				int j = i;
				for (j = i; j >= h && tmp<a[j - h]; j -= h){
					a[j] = a[j - h];
				}
					
				a[j] = tmp;
				show(a);
				
				
//				for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
//					exch(a, j, j - h);
			}
			h = h / 3;
			show(a);
		}
	}

	public static void main(String[] args) {
		int N = 20;
		Double[] a = randomDoubleArray(N);
		int n = a.length;

		StdDraw.setCanvasSize(1024, 512);
		StdDraw.setXscale(0, n);
		shellSort(a);
		

	}

}

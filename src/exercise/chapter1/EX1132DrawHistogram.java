package exercise.chapter1;

//import exercise.depend.In;
import exercise.depend.StdDraw;
import exercise.depend.StdIn;

//直方图
//设输入流中有一系列double值，从命令行接受一个整数N和两个double值l和r，将(l,r)分为N段并
//使用StdDraw画出输入流中值落入每段数量的直方图
public class EX1132DrawHistogram {

private static int[] dataHistogram(int N, double l, double r, double[] arr) {
	int[] hArray = new int[N];
	double delta;

	delta = (r - l) / N;

	for (int i = 0; i < arr.length; i++) {
		if (arr[i] >= l && arr[i] < r) {
			int idx = (int) ((arr[i] - l) / delta);
			hArray[idx]++;
		}
	}
	return hArray;
}

public static void main(String[] args) {
//		In in = new In("largeW.txt");
//		double[] list = in.readAllDoubles();
	double[] list = {0.1,0.2,0.3,0.4,0.5,0.12,0.23};
	System.out.print("input N：");
	int N = StdIn.readInt();
	System.out.print("input l：");
	double l = StdIn.readDouble();
	System.out.print("input r：");
	double r = StdIn.readDouble();
	int[] hArray = dataHistogram(N, l, r, list);
	double max = hArray[0];
	for (int i = 1; i < hArray.length; ++i) {
		if (max < hArray[i])
			max = hArray[i];
	}
	StdDraw.setXscale(l, r);
	StdDraw.setPenColor(StdDraw.BOOK_BLUE);
	double interval = (r - l) / N;
	double x0 = l + interval / 2.0;
	for (int i = 0; i < hArray.length; i++) {
		double x = x0 + i * interval;
		double y = hArray[i] / (max + 1) / 2.0;
		double hw = 0.99 * interval / 2.0;
		double hh = y;
		StdDraw.filledRectangle(x, y, hw, hh);
	}
}

}

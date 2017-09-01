package exercise.chapter1;

import java.util.Random;

import exercise.depend.StdDraw;

//�������
//����һ������N��һ��double p(0<p<1)��Ϊ��������һ��Բ�ϻ�����С0.05�����ȵ�N���㣬Ȼ��an����p�û�������
public class EX1131RandomLink {

	public static void main(String[] args) {
		randomLink(70,0.01);

	}
	/**
	 * ��Բ
	 * @param x Բ��x����
	 * @param y Բ��y����
	 * @param r �뾶r
	 */
	private static void drawCircle(double x, double y, double r) {
	    StdDraw.setXscale(0, 2 * x);
	    StdDraw.setYscale(0, 2 * y);
	    StdDraw.setPenRadius(0.003);
	    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
	    StdDraw.circle(x, y, r);
	}

	/**
	 * ��Բ�����
	 * @param x0 Բ��x����
	 * @param y0 Բ��y����
	 * @param r �뾶r
	 * @param N N����
	 */
	private static double[][] drawPoints(double x0, double y0, double r, int N) {
	    double[][] points = new double[N][2];
	    StdDraw.setPenRadius(0.005);
	    StdDraw.setPenColor(StdDraw.BOOK_RED);
	    for(int idx = 0; idx < N; ++idx) {
	        double x = x0 + r * Math.cos(2 * Math.PI * idx / N);
	        double y = y0 + r * Math.sin(2 * Math.PI * idx / N);
	        StdDraw.point(x, y);
	        points[idx][0] = x;
	        points[idx][1] = y;
	    }
	    return points;
	}

	/**
	 * �Ը���p������Ӷ��㼯points�еĵ�
	 * @param points    �㼯
	 * @param p ����p
	 */
	private static void randomLinkPoints(double[][] points, double p) {
	    StdDraw.setPenRadius(0.002);
	    StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
	    Random random = new Random();
	    int length = points.length;
	    for(int i = 0; i < length; ++i)
	        for(int j = 0; j < length; ++j)
	            if(random.nextDouble()<p)
	                StdDraw.line(points[i][0], points[i][1], points[j][0], points[j][1]); // Ӧ���ٽ���һ������x�����y��������ݽṹ
	}

	/**
	 * ��Բ�ϻ�N����Ȼ��ÿ������Ը���p����
	 * @param N N����
	 * @param p ����p
	 */
	private static void randomLink(int N, double p) {
	    double x = 10.0;
	    double y = 10.0;
	    double r = 9.0;
	    drawCircle(x, y, r);
	    double[][] points = drawPoints(x, y, r, N);
	    randomLinkPoints(points, p);
	}
	

}

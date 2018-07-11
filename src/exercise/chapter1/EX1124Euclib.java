package exercise.chapter1;

//欧几里得算法计算105和24的最大公约数过程中的到的一系列p和q的值
//拓展该方法，从命令行接受两参数，计算最大公约数并打印出每次递归时的两参数，计算1 111 111和1 234 567的最大公约数
public class EX1124Euclib {
	public static long gcd(long a, long b) {
		System.out.println("p:" + a + " q:" + b);
		if (b == 0)
			return a;
		if (b == 1)
			return b;
		else
			return gcd(b, a % b);
	}

	public static int gcd(int a, int b) {
		System.out.println("p:" + a + " q:" + b);
		if (b == 0)
			return a;
		if (b == 1)
			return b;
		else
			return gcd(b, a % b);
	}

	public static void main(String[] args) {
		// System.out.println(Long.MAX_VALUE -
		// gcd(Long.MAX_VALUE,Long.MAX_VALUE-1));
		// System.out.println(Long.MAX_VALUE%(Long.MAX_VALUE-1));
		System.out.println("gcd:" + gcd(105, 24));
		System.out.println("gcd:" + gcd(1111111,1234567));
	}
}

package exercise.chapter1;

//ŷ������㷨����105��24�����Լ�������еĵ���һϵ��p��q��ֵ
//��չ�÷������������н������������������Լ������ӡ��ÿ�εݹ�ʱ��������������1 111 111��1 234 567�����Լ��
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

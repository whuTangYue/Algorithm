package exercise.chapter1;

public class EX1207Mystery {
	public static void main(String[] args) {
		String s = "hello world";
		System.out.println(mystery(s));

	}
	
	public static String mystery(String s){
		int N = s.length();
		if(N<=1) return s;
		String a = s.substring(0, N/2);
		String b = s.substring(N/2, N);
		return mystery(b)+mystery(a);
	}

}

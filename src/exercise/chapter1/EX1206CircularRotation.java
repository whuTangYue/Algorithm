package exercise.chapter1;

public class EX1206CircularRotation {
	public static boolean isCirRotation(String s,String t)
	{
		if (s.length() == t.length() && (s.concat(s).indexOf(t) >= 0)) return true;
		else return false;
	}

	public static void main(String[] args) {
		String s = "helloworld";
		String t = "worldhello";
		System.out.println(isCirRotation(s,t));

	}
}

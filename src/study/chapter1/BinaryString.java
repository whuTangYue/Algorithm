package study.chapter1;

public class BinaryString {
	final static char[] digits = {         
		    '0' , '1' , '2' , '3' , '4' , '5' ,         
		    '6' , '7' , '8' , '9' , 'a' , 'b' ,         
		    'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,         
		    'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,         
		    'o' , 'p' , 'q' , 'r' , 's' , 't' ,         
		    'u' , 'v' , 'w' , 'x' , 'y' , 'z'     
		    }; //所有可能的将数字表示为字符串的字符集合。
	
	public static String toBinaryString(int i){
		return toUnsignedString(i,1);
	}
	private static String toUnsignedString(int i,int shift){
		char[] buf = new char[32];
		int charPos = 32;
		int radix = 1<<shift;
		int mask = radix - 1;
		do{
			buf[--charPos] = digits[i & mask];
			i>>>=shift;
		}while (i!=0);
		return new String(buf,charPos,(32-charPos));
	}

	public static void main(String[] args) {
		System.out.println(toBinaryString(10));
	}

}

package exercise.chapter1;

//display "equal" if 3 input number is equal
public class EX1103 {

	public static void main(String[] args)  {
		int a=1;
		int b=1;
		int c=2;
		if(isEqual3(a,b,c)) System.out.println("equal");
		else System.out.println("not equal");
		

	}
	
	private static boolean isEqual3(int a,int b,int c){
		if(a==b&&a==c) return true;
		else return false;
	}

}

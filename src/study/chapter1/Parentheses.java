package study.chapter1;
import java.util.Scanner;

public class Parentheses {
	public static boolean isBalance(String s){
		Stack<Character> p = new Stack<Character>();
		int n = s.length();
        for (int i = 0; i < n; i++)
        {
        	char c = s.charAt(i);
			if(c=='('||c=='['||c=='{') 
				p.push(c);
			if((c==')'&&(p.isEmpty()||p.pop()!='('))||
			   (c==']'&&(p.isEmpty()||p.pop()!='['))||
			   (c=='}'&&(p.isEmpty()||p.pop()!='{')))
				return false;
		}
		return p.isEmpty();
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();
//		String s = "{}[]())";
		System.out.println(isBalance(s));
		scan.close();

	}

}

package exercise.chapter1;

import java.util.Stack;

public class EX1310InfixToPostfix {
	public static String InfixToPostfix(String infix){
		Stack<String> stack = new Stack<String>();
		String res ="";
		String[] strings = infix.split(" ");
		for(int i =0;i<strings.length;i++)
		{
			String s = strings[i];
			if      (s.equals("+")) stack.push(s);
            else if (s.equals("*")) stack.push(s);
            else if (s.equals("-")) stack.push(s);
            else if (s.equals("/")) stack.push(s);
            else if (s.equals(")")) while(!stack.isEmpty())
            		res+=stack.pop()+" ";
            else if (s.equals("(")||s.equals(" ")) ;
            else                    res+=s+" ";
		}
		return res;
	}
	
	public static void main(String[] args) {
		String s="2 + ( ( 3 + 4 ) * ( 5 * 6 ) )";
		System.out.println(InfixToPostfix(s));

	}

}

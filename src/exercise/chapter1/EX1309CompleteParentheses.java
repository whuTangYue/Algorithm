package exercise.chapter1;

import java.util.Stack;

public class EX1309CompleteParentheses {

	//
	//输入各元素之间需用空格分隔，不省略括号，即不考虑优先级
	public static String completeParentheses(String args) {
		Stack<String> optr = new Stack<String>();
		Stack<String> expr = new Stack<String>();
		String op;
		String ex;
		String[] strings = args.split(" ");
		for(int i =0;i<strings.length;i++)
		{
			String s = strings[i];
			if      (s.equals("("))               ;
            else if (s.equals("+") ||
                     s.equals("-") ||
                     s.equals("*") ||
                     s.equals("/")) optr.push(s);
            else if (s.equals(")"))
            {
            	while(!optr.isEmpty())
            	{
                if(!optr.isEmpty()) op = optr.pop(); else op = "";
                if(!expr.isEmpty()) ex = expr.pop(); else ex = "";

                if (op.equals("+") ||
                    op.equals("-") ||
                    op.equals("*") ||
                    op.equals("/"))
                	ex = String.format("( %s %s %s )", expr.pop(), op, ex);
//                ex = "("+expr.pop()+op+ex+")";
                expr.push(ex);
            	}
            }
            else expr.push(s);
		}
		return expr.pop();

	}

	 public static void main(String[] args) {
	        String str = "( 2 + ( ( 3 + 4 ) * ( 5 * 6 ) ) )";
	        String res = completeParentheses(str);
	        System.out.println(res);
	    }

}

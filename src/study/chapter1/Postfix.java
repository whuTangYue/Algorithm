package study.chapter1;

public class Postfix {
	
	//Reads in an infix expression and outputs an equivalent postfix  expression.
	//输入各元素之间需用空格分隔，未省略括号，即不考虑优先级
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
	
	//Evaluates postfix expresions using a stack.
	//All token must be separated by whitespace, e.g., 1 5+ is illegal.
	public static double EvaluatePostfix(String postfix){
		Stack<Double> stack = new Stack<Double>();
		String[] strings = postfix.split(" ");
		for(int i =0;i<strings.length;i++)
		{
			String s = strings[i];
			if      (s.equals("+")) stack.push(stack.pop() + stack.pop());
            else if (s.equals("*")) stack.push(stack.pop() * stack.pop());
            else if (s.equals("-")) stack.push(stack.pop() - stack.pop());
            else if (s.equals("/")) stack.push(stack.pop() / stack.pop());
            else stack.push(Double.parseDouble(s));
		}
		return stack.pop();

	}

	public static void main(String[] args) {
		String s="2 + ( ( 3 + 4 ) * ( 5 * 6 ) )";
		System.out.println(EvaluatePostfix(InfixToPostfix(s)));

	}

}

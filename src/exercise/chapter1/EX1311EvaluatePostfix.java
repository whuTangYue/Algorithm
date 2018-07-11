package exercise.chapter1;

import java.util.Stack;

public class EX1311EvaluatePostfix {
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
			String s="2 3 4 + + 5 6 * * ";
			System.out.println(EvaluatePostfix(s));

		}

}

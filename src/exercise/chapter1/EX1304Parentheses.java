package exercise.chapter1;

import java.util.Stack;

//使用栈判定文本流中括号是否配对完整
public class EX1304Parentheses {

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
		String s = "{}[]()()([[]])";
		System.out.println(isBalance(s));

	}

}

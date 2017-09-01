package study.chapter1;
import java.util.Stack;
public class TrianglePattern {
	public static void trianglePatternPrint(int n){
		int count = 0;
		int length = 0;
		String s = "";
		Stack<String> stack= new Stack<String>();
		for(int i=1;i<=n;i++)
		{
			count++;
			length++;
			for(int j=1;j<length;j++)
			{
				s+=count+"*";
				count++;
			}
			s+=count;
			stack.push(s);
			System.out.println(s);
			s="";
		}
		for(int i=1;i<=n;i++)
		{
			s=stack.pop();
			System.out.println(s);
		}
	}
	public static void main(String[] args)
	{
		trianglePatternPrint(4);
 	}
}
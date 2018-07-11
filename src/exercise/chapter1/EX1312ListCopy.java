package exercise.chapter1;

import study.chapter1.Stack;

public class EX1312ListCopy {
	public static Stack<String> copy(Stack<String> that) {
		Stack<String> stack = new Stack<String>();
		for (String item : that)
			stack.push(item);
		return stack;
	}

	public static void main(String[] args) {
		Stack<String> collection = new Stack<String>();
		String string = "to be or not to - be - - that - - - is";
		String[] strings = string.split(" ");
		for (int i = 0; i < strings.length; i++) {
			String item = strings[i];
			if (!item.equals("-"))
				collection.push(item);
			else if (!collection.isEmpty())
				System.out.print(collection.pop() + " ");
		}
		System.out.println("(" + collection.size() + " left on stack)");
		Stack<String> collection2 = copy(collection);
		for (String s : collection2)
			System.out.println(s);
	}
}

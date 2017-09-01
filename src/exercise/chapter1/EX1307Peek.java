package exercise.chapter1;


//the implementation of Stack is in file Stack.java
public class EX1307Peek {

	public static void main(String[] args) {
		Stack<String> collection = new Stack<String>();
		String string = "to be or not to - be - - that - - - is";
		String[] strings = string.split(" ");
		for(int i =0;i<strings.length;i++)
		{
			String item = strings[i];
			if (!item.equals("-"))
				collection.push(item);
			else if (!collection.isEmpty()) System.out.print(collection.pop() + " ");
		}
		System.out.println("(" + collection.size() + " left on stack)");
		System.out.println(collection.peek());

	}

}

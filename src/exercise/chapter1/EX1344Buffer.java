package exercise.chapter1;

public class EX1344Buffer {

	public static void main(String[] args) {
		Buffer b = new Buffer("Hello", "World");
		System.out.println(b); // Hello*World
		System.out.println(b.delete()); // W
		System.out.println(b); // Hello*orld
		b.right(4);
		System.out.println(b); // Helloorld_
		b.left(4);
		b.insert('w');
		System.out.println(b);

	}

}

class Buffer {
	private Stack<Character> leftStack;
	private Stack<Character> rightStack;

	public Buffer() {
		leftStack = new Stack<Character>();
		rightStack = new Stack<Character>();
	}

	public Buffer(String s) {
		leftStack = new Stack<Character>();
		rightStack = new Stack<Character>();
		if (s != null)
			for (char c : s.toCharArray())
				insert(c);
	}

	public Buffer(String s1, String s2) {
		leftStack = new Stack<Character>();
		rightStack = new Stack<Character>();
		if (s1 != null)
			for (char c : s1.toCharArray())
				leftStack.push(c);
		char[] ca = s2.toCharArray();
		if (s2 != null)
			for (int i = ca.length - 1; i > -1; i--)
				rightStack.push(ca[i]);
	}

	public void insert(char c) {
		leftStack.push(c);
	}

	public char delete() {
		if (!rightStack.isEmpty())
			return rightStack.pop();
		else
			return '\0';
	}

	public void left(int k) {
		for (int i = 0; i < k; i++)
			if (!leftStack.isEmpty())
				rightStack.push(leftStack.pop());
	}

	public void right(int k) {
		for (int i = 0; i < k; i++)
			if (!rightStack.isEmpty())
				leftStack.push(rightStack.pop());
	}

	public int size() {
		return leftStack.size() + rightStack.size();
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
        for (char c : leftStack)
            s.append(c);
        s.reverse().append('_');
        for (char c : rightStack)
            s.append(c);
        return s.toString();
	}
}

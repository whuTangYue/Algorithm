package exercise.chapter1;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item>{
	public static void main(String[] args)
	{
		Stack<String> collection = new Stack<String>();
		String string = "to be or not to - be - - that - - - is";
		String[] strings = string.split(" ");
		for(int i =0;i<strings.length;i++)
		{
			String s = strings[i];
			if (!s.equals("-"))
				collection.push(s);
			else if (!collection.isEmpty()) System.out.print(collection.pop() + " ");
		}
		System.out.println("(" + collection.size() + " left on stack)");
		System.out.println(collection);
		Stack<String> collection2 = copy(collection);
		for (String s : collection2)
			System.out.println(s);
	}
	private Node first;
	private int  N;
	private class Node
	{
		Item item;
		Node next;
		Node(){}
		
		//copy a single Node
		Node(Node x)
		{
			this.item = x.item;
			this.next = x.next;
		}
	}
	
	//copy a stack.
	//Nonrecursive solution: create a copy constructor for a single Node object.
	public Stack(Stack<Item> s) {
		if (s.first != null) {
	      first = new Node(s.first);
	      for (Node x = first; x.next != null; x = x.next)
	         x.next = new Node(x.next); 
	      }
	}
	public Stack(){
		first = null;
		N = 0;}
	public boolean isEmpty() { return first == null; }
	public int     size()    { return N;}
	public void    push(Item item)
	{
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}
	public Item pop()
	{
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		Item item = first.item;
		first =first.next;
		N--;
		return item;
	}
	
	public Item peek()
	{
		if (isEmpty()) throw new NoSuchElementException("Stack underflow");
		return first.item;
	}
	
	public static Stack<String> copy(Stack<String> that)
	{
		Stack<String> stack = new Stack<String>();
		for(String item:that)
			stack.push(item);
		return stack;
	}
	
	public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }
	
	public Iterator<Item> iterator() { return new StackIterator(); }
	private class StackIterator implements Iterator<Item>
	{
		private Node current = first;
		public boolean hasNext() {return current !=null; }
		public void remove(){}
		public Item next()
		{
			if (!hasNext()) throw new NoSuchElementException();
			Item item =current.item;
			current = current.next;
			return item;
		}
	}
}


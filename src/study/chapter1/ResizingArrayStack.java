package study.chapter1;
import java.util.Iterator;


public class ResizingArrayStack<Item> implements Iterable<Item>
{
	public static void main(String[] args)
	{
		ResizingArrayStack<String> collection = new ResizingArrayStack<String>(5);
		String string = "to be or not to be";
		String[] strings = string.split(" ");
		for (int i = 0; i < strings.length; i++) {
			String item = strings[i];
			collection.push(item);
		}
		System.out.println("(" + collection.size() + " left on stack)");
		for (String s : collection)
			System.out.println(s);
	}
	private Item[] a;
	private int    N;
	@SuppressWarnings("unchecked")
	public ResizingArrayStack(int cap){
		a = (Item[]) new Object[cap];
	}
	public boolean isEmpty(){return N ==0;}
	public int     size()   {return N;}
	public void    push(Item item){
		if (N == a.length) resize(2*a.length);
		a[N++] = item;
	}
	public Item pop(){
		Item item = a[--N];
		a[N] = null;
		if (N > 0 &&N == a.length/4) resize(a.length/2);
		return item;
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int max)
	{
		Item[] temp = (Item[]) new Object[max];
		for(int i =0 ;i < N; i++)
			temp[i] = a[i];
		a = temp;
	}
	@Override
	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
	}
	private class ReverseArrayIterator implements Iterator<Item>
	{
		private int i = N;
		public boolean hasNext() { return i > 0; }
		public Item    next()    { return a[--i];}
		public void    remove()  {               }
	}
}

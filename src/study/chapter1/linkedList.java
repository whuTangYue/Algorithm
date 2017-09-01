package study.chapter1;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class linkedList<Item> implements Iterable<Item>{
	private Node first;
	private class Node{
		Item item;
		Node next;
		Node(Node that){
			this.item=that.item;
			this.next=that.next;
		}
		Node(Item item){
			this.item=item;
		}
		boolean hasnext(){
			return this.next!=null;
		}
	}
	
	public linkedList(){
		first=null;
	}
	public linkedList(linkedList<Item> list){
		if(first == null)  
			throw new NoSuchElementException("LinkedList is empty!");
		else {
			first = new Node(list.first);
			for (Node x = first; x.next != null; x = x.next)
				x.next = new Node(x.next); 
			}
	}
	public void add(Item item){
		Node newfirst = new Node(item);
		newfirst.next=first;
		first=newfirst;
	}
	public void delete(int k){
		if(first == null)  
			throw new NoSuchElementException("LinkedList is empty!");
		else {
			Node x = new Node(first);
			for (int i=2;i<k;i++)
				if(!x.hasnext()) throw new NoSuchElementException("no such node");
				else x=x.next;
			x.next=x.next.next;
		}
	}
	public void removeAfter(Node node){
		if(node!=null&&node.next!=null) node.next=node.next.next;
	}
	public void insertAfter(Node node1,Node node2){
		if(node1!=null&&node2!=null&&node1.item.getClass()==node2.item.getClass())
		{
		node2.next=node1.next.next;
		node1.next=node2;
		}
	}
	
	
	public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<Item> iterator() { return new linkedListIterator();}
	private class linkedListIterator implements Iterator<Item>
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

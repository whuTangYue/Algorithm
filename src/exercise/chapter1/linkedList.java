package exercise.chapter1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class linkedList<Item> implements Iterable<Item> {
	public static void main(String[] args) {
		linkedList<String> list = new linkedList<String>();
		String string = "to be or not to be";
		String[] strings = string.split(" ");
		for (int i = 0; i < strings.length; i++) {
			String item = strings[i];
			list.add(item);
		}
		System.out.println(list);
		list.delete(list.length() -1 );
		System.out.println(list);
		linkedList<String> list1 = new linkedList<String>(list);
		System.out.println(list1 + "" + list1.length());
		list.addFirst("to be or not to be");
		System.out.println(list);

	}

	private Node head, tail;
	private int length;

	private class Node {
		Item item;
		Node next;

		Node() {
		}

		Node(Node that) {
			this.item = that.item;
			this.next = that.next;
		}

		Node(Item item) {
			this.item = item;
		}

		boolean hasnext() {
			return this.next != null;
		}
	}

	public linkedList() {
		head = tail = new Node();
		length = 0;
	}

	public linkedList(linkedList<Item> list) {
		if (list.length() == 0)
			throw new NoSuchElementException("LinkedList is empty!");
		else {
			head = new Node(list.head);
			for (Node x = head; x.next != null; x = x.next) {
				x.next = new Node(x.next);
				length++;
			}
		}
	}

	public boolean isEmpty() {
		return head.next == null;
	}

	public void add(Item item) {
		Node newnode = new Node(item);
		tail.next = newnode;
		tail = newnode;
		length++;
	}
	public void addFirst(Item item){
		Node node = new Node(item);
		node.next = head.next;
		head.next = node;
	}

	private void checkIndexBounds(int index) {
		if (index < 0 || index >= length)
			throw new IndexOutOfBoundsException("index: " + index + ",length: " + length);
	}

	// delete the element of list,index is 0~length-1
	public Item delete(int index) {
		Node x = getNode(index);
		Item item = x.next.item;
		x.next = x.next.next;
		length--;
		return item;
	}

	public int length() {
		return length;
	}

	private Node getNode(int index) {
		if (isEmpty())
			throw new NoSuchElementException("LinkedList is empty!");
		checkIndexBounds(index);

		Node node = head;
		for (int i = 0; i < index; i++) {
			if (!node.hasnext())
				throw new NoSuchElementException("no such node");
			else
				node = node.next;
		}
		return node;
	}

	public Item get(int index) {
		Node node = getNode(index);
		return node.item;
	}

//	private void removeAfter(Node node) {
//		if (node != null && node.next != null)
//			node.next = node.next.next;
//	}
//
//	private void insertAfter(Node node1, Node node2) {
//		if (node1 != null && node2 != null && node1.item.getClass() == node2.item.getClass()) {
//			node2.next = node1.next.next;
//			node1.next = node2;
//		}
//	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Item item : this)
			s.append(item + " ");
		return s.toString();
	}

	@Override
	public Iterator<Item> iterator() {
		return new linkedListIterator();
	}

	private class linkedListIterator implements Iterator<Item> {
		private Node current = head;
		private Node old = head;

		public boolean hasNext() {
			return current.next != null;
		}

		public void remove() {
			if (current == null) {
				throw new NoSuchElementException();
			} else {
				old.next = current.next;
			}

		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			old = current;
			current = current.next;
			Item item = current.item;
			return item;
		}
	}

}

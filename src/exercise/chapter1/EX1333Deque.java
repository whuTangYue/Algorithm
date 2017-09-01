package exercise.chapter1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EX1333Deque {

	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<Integer>();
		deque.pushLeft(1);
		deque.pushRight(2);
		deque.pushLeft(0);
		deque.pushRight(4);
		System.out.println(deque);
		System.out.println(deque.popRight() + " " + deque.size());
		System.out.println(deque.popLeft() + " " + deque.size());
		System.out.println(deque);

	}

}

class Deque<Item> implements Iterable<Item> {
	private class Node {
		Item item;
		Node prev;
		Node next;

		Node() {
		}

		Node(Item item) {
			this.item = item;
		}
	}

	private int N;
	private Node head;
	private Node tail;

	public Deque() {
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.prev = head;
		N = 0;
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Item item : this)
			s.append(item + " ");
		return s.toString();
	}

	public void pushLeft(Item item) {
		Node node = new Node(item);
		node.next = head.next;
		node.prev = head;
		head.next = node;
		node.next.prev = node;

		N++;
	}

	public void pushRight(Item item) {
		Node node = new Node(item);
		node.prev = tail.prev;
		node.next = tail;
		tail.prev = node;
		node.prev.next = node;

		N++;
	}

	public Item popLeft() {
		if (isEmpty())
			throw new NoSuchElementException("LinkedList is empty!");
		Item item = head.next.item;
		head.next = head.next.next;
		head.next.prev = head;
		N--;
		return item;
	}

	public Item popRight() {
		if (isEmpty())
			throw new NoSuchElementException("Deque is empty!");
		Item item = tail.prev.item;
		tail.prev = tail.prev.prev;
		tail.prev.next = tail;
		N--;
		return item;
	}

	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {
		private Node prev = head;
		private Node current = head;
		private Node next = current.next;

		@Override
		public boolean hasNext() {
			return next != tail;
		}
		
		public void remove(){
			if(isEmpty())
				throw new NoSuchElementException("Deque is empty!");
			if(current == head || current == tail)
				throw new NoSuchElementException();
			prev.next = next;
			next.prev = prev;
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			prev = current;
			current = current.next;
			next = current.next;
			Item item = current.item;
			return item;
		}

	}

}
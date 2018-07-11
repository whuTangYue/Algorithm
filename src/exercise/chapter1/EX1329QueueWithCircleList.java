package exercise.chapter1;

import java.util.NoSuchElementException;

public class EX1329QueueWithCircleList {

	public static void main(String[] args) {
		Queue<String> queue = new Queue<String>();
		String string = "to be or not to - be - - that - - - is";
		String[] strings = string.split(" ");
		for (int i = 0; i < strings.length; i++) {
			String item = strings[i];
			if (!item.equals("-"))
				queue.enqueue(item);
			else if (!queue.isEmpty())
				System.out.print(queue.dequeue() + " ");
		}
		System.out.println("(" + queue.size() + " left on queue)");

	}

}

class Queue<Item> {
	private class Node {
		Item item;
		Node next;
	}

	private Node last;
	private int N;

	public Queue() {
		last = null;
		N = 0;
	}

	public boolean isEmpty() {
		return last == null;
	}

	public int size() {
		return N;
	}

	// add item to last node
	public void enqueue(Item item) {
		if (isEmpty()) {
			last = new Node();
			last.item = item;
			last.next = last;
		} else {
			Node node = new Node();
			node.item = item;
			node.next = last.next;
			last.next = node;
			last = node;
		}
		N++;
	}

	// delete node from first
	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue is empty!");
		} else {
			Item item = last.next.item;
			if (N == 1)
				last = null;
			else
				last.next = last.next.next;
			N--;
			return item;
		}
	}

}
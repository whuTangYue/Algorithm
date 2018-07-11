package exercise.chapter1;

import java.util.Iterator;
import java.util.Random;

public class EX1335RandomQueue {

	public static void main(String[] args) {
		RandomQueue<Integer> queue = new RandomQueue<Integer>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(4);
		queue.enqueue(5);
		for (int i : queue)
			System.out.print(i + " ");

	}

}

class RandomQueue<Item> implements Iterable<Item> {
	private int N;
	private final int DEFAULT_SIZE = 32;
	private Item[] queue;

	@SuppressWarnings("unchecked")
	public RandomQueue() {
		queue = (Item[]) new Object[DEFAULT_SIZE];
		N = 0;
	}

	@SuppressWarnings("unchecked")
	public RandomQueue(int cap) {
		queue = (Item[]) new Object[cap];
		N = 0;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public void enqueue(Item item) {
		if (N == queue.length)
			resize(2 * queue.length);
		queue[N++] = item;
	}

	public Item dequeue() {
		randomSwap();
		Item item = queue[--N];
		queue[N] = null;
		if (N > 0 && N == queue.length / 4)
			resize(queue.length / 2);
		return item;
	}

	public Item Sample() {
		randomSwap();
		return queue[N - 1];
	}

	@SuppressWarnings("unchecked")
	private void resize(int max) {
		Item[] temp = (Item[]) new Object[max];
		for (int i = 0; i < N; i++)
			temp[i] = queue[i];
		queue = temp;
	}

	private void randomSwap() {
		if(N<=1) return;
		Random random = new Random();
		int r = random.nextInt(N);
		Item temp = queue[N - 1];
		queue[N - 1] = queue[r];
		queue[r] = temp;
	}

	@Override
	public Iterator<Item> iterator() {
		return new RandomQueueIterator();
	}

	private class RandomQueueIterator implements Iterator<Item> {
		private int i = N;

		@Override
		public boolean hasNext() {
			return i != 0;
		}

		@Override
		public Item next() {
			if(N<=1) return queue[--i];
			Random random = new Random();
			int r = random.nextInt(i);
			Item temp = queue[i - 1];
			queue[i - 1] = queue[r];
			queue[r] = temp;
			return queue[--i];
		}

	}
}
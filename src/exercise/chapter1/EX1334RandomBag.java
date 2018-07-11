package exercise.chapter1;

import java.util.Iterator;
import java.util.Random;

public class EX1334RandomBag<Item> implements Iterable<Item> {

	public static void main(String[] args) {
		EX1334RandomBag<Integer> bag = new EX1334RandomBag<Integer>();
		bag.add(1);
		bag.add(2);
		bag.add(9);
		bag.add(10);
		for (int i : bag)
			System.out.print(i + " ");

	}

	private int N;
	private Item[] bag;

	@SuppressWarnings("unchecked")
	public EX1334RandomBag() {
		bag = (Item[]) new Object[2];
		N = 0;
	}

	@SuppressWarnings("unchecked")
	public EX1334RandomBag(int cap) {
		bag = (Item[]) new Object[cap];
		N = 0;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public void add(Item item) {
		if (N == bag.length)
			resize(2 * bag.length);
		bag[N++] = item;
	}

	@SuppressWarnings("unchecked")
	private void resize(int max) {
		Item[] temp = (Item[]) new Object[max];
		System.arraycopy(bag, 0, temp, 0, bag.length);
		bag = temp;
	}

	private void shuffle() {
		Random random = new Random();
		for (int i = 0; i < N; i++) {
			int r = i + random.nextInt(N - i);
			Item temp = bag[i];
			bag[i] = bag[r];
			bag[r] = temp;
		}
	}

	@Override
	public Iterator<Item> iterator() {
		shuffle();
		return new RandomBagIterator();
	}

	private class RandomBagIterator implements Iterator<Item> {
		private int i = N;

		@Override
		public boolean hasNext() {
			return i != 0;
		}

		@Override
		public Item next() {
			return bag[--i];
		}

	}
}
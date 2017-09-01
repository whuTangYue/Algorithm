package exercise.chapter1;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class EX1339RingBuffer {

	public static void main(String[] args) {
		RingBuffer<Integer> buffer = new RingBuffer<Integer>();
		for(int i=0;i<9;i++){
			if(!buffer.isFull())
				buffer.in(i);
		}
		for(int i=0;i<9;i++){
			if(!buffer.isEmpty())
				System.out.print(buffer.out()+" ");
		}

	}

}

class RingBuffer<Item> {
	private final int DEFAULT_SIZE = 8;
	private int capacity;
	private Item[] buffer;
	private int head = 0;
	private int tail = 0;

	@SuppressWarnings("unchecked")
	public RingBuffer() {
		capacity = DEFAULT_SIZE;
		buffer = (Item[]) new Object[capacity];
	}

	@SuppressWarnings("unchecked")
	public RingBuffer(final int initSize) {
		capacity = initSize;
		buffer = (Item[]) new Object[capacity];
	}

	public boolean isFull() {
		return head == tail && buffer[tail] != null;
	}

	public boolean isEmpty() {
		return head == tail && buffer[tail] == null;
	}

	public void clear() {
		Arrays.fill(buffer, null);
		head = 0;
		tail = 0;
	}

	public void in(Item item) {
		if (isFull())
			throw new NoSuchElementException("buffer is full!");
		buffer[tail] = item;
		tail++;
		tail = tail == capacity ? 0 : tail;
	}
	
	public Item out(){
		if (isEmpty())
			throw new NoSuchElementException("buffer is empty!");
		Item item = buffer[head];
		buffer[head]=null;
		head++;
		head = head == capacity ? 0 : head;
		return item;
	}
}

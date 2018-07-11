package exercise.chapter1;

public class EX1337Josephus {

	public static void Josephus(int N, int M) {
		Queue<Integer> queue = new Queue<Integer>();
		for (int i = 0; i < N; i++)
			queue.enqueue(i);
		while (!queue.isEmpty()) {
			for (int i = 0; i < M - 1; i++)
				queue.enqueue(queue.dequeue());
			System.out.print(queue.dequeue() + " ");
		}
	}

	public static void main(String[] args) {
		Josephus(100, 2);

	}

}

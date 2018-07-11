package exercise.chapter1;

public class EX1437TestAutoBoxing {

	class FixedCapacityStackOfInts {
		private int[] stack;
		private int N;

		public FixedCapacityStackOfInts(int cap) {
			stack = new int[cap];
		}

		public void push(int n) {
			stack[N++] = n;
		}

		public int pop() {
			return stack[--N];
		}
	}

	class FixedCapacityStack<Item> {
		private Item[] stack;
		private int N;

		@SuppressWarnings("unchecked")
		public FixedCapacityStack(int cap) {
			stack = (Item[]) new Object[cap];
		}

		public void push(Item item) {
			stack[N++] = item;
		}

		public Item pop() {
			return stack[--N];
		}
	}


	public static void main(String[] args) {
		FixedCapacityStackOfInts stack1 = new EX1437TestAutoBoxing().new FixedCapacityStackOfInts(100);
		FixedCapacityStack<Integer> stack2 = new EX1437TestAutoBoxing().new FixedCapacityStack<Integer>(100);
		for (int N = 10000; N < 10000000; N += N) {
			long time1 = System.currentTimeMillis();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < 100; j++) {
					stack1.push(j);
				}
				for (int j = 0; j < 100; j++) {
					stack1.pop();
				}
			}
			long time2 = System.currentTimeMillis();
			long time3 = System.currentTimeMillis();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < 100; j++) {
					stack2.push(j);
				}
				for (int j = 0; j < 100; j++) {
					stack2.pop();
				}
			}
			long time4 = System.currentTimeMillis();

			System.out.println(N + " stack1:" + (time2 - time1) + " stack2:" + (time4 - time3));
		}
	}

}

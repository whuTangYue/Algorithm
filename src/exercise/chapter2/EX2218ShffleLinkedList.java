package exercise.chapter2;

import java.util.Arrays;
import java.util.Random;

// shuffle a linked list
// 1. Gilbert–Shannon–Reeds model
//		shuffle left, shuffle right, then merge
//    O(1) space, O(nlogn) time
//    归并打乱，根据 Gilbert–Shannon–Reeds 模型， 可以得到接近均匀的结果
// 2. O(n log n) algorithm that produces not-quite-uniform shuffles 
//    involves simply riffle shuffling the list 3/2 log_2(n) times.
//		According to wiki: Gilbert–Shannon–Reeds model
//		as well as we shuffle the card, cut and riffle, for n times
//		if n > 3/2* log2(len)  leaves only a constant number of bits of information.
//	  相当于现实中洗牌，洗牌足够多次之后可以认为接近uniform shuffles
// 3. linked list to array, shuffle the array, then array to linked list
//    链表转数组，经典方法打乱数组，然后转回来
//    O(n) space, O(n) time

// shuffle test
// Count the number of times each element appeared in various places after shuffle
// 统计每个元素打乱之后出现在各个位置的次数
public class EX2218ShffleLinkedList {
	static private class Node {
		int val;
		Node next;

		public Node(int value) {
			val = value;
			next = null;
		}
	}

	private static Node riffleLists(Node head, Node left, int len1, Node right,
			int len2) {
		Random random = new Random();
		Node next;
		int len = len1 + len2;
		for (int i = 0; i < len; i++) {
			double d = random.nextDouble();
			// System.out.println(d + " ");
			if (d < (double) len1 / (double) (len1 + len2)) {
				next = left;
				left = left.next;
				len1 = len1 - 1;
			} else {
				next = right;
				right = right.next;
				len2 = len2 - 1;
			}
			head.next = next;
			head = next;
		}
		head.next = right;
		return head;
	}

	private static Node index(Node first, int index) {
		Node current = first;
		int n = index;
		while (n > 0) {
			if (current != null) {
				current = current.next;
				n--;
			} else
				throw new IndexOutOfBoundsException(index + " " + n);
		}
		return current;

	}

	// riffle shuffle
	public static Node shuffleLinkedList1(Node first) {
		int len = 1;
		Node next = first.next;
		while (next != null) {
			next = next.next;
			len++;
		}
		int i = 1;
		Node dummy = new Node(0);
		dummy.next = first;
		Node head;
		while (i < len) {
			head = dummy;
			int nleft = len;
			while (nleft > i) {
				head = riffleLists(head, head.next, i, index(head, i + 1),
						Math.min(i, nleft - i));
				nleft -= 2 * i;
			}
			i *= 2;
		}
		return dummy.next;
	}

	public static Node shuffleLinkedList2(Node first) {
		int len = 1;
		Node next = first.next;
		while (next != null) {
			next = next.next;
			len++;
		}
		Node head = new Node(0);
		head.next = first;
		for (int i = 0; i < 3 / 2 * Math.log(len) / Math.log(2) + 3; i++) {
			int half = len / 2;
			riffleLists(head, head.next, half, index(head, half + 1),
					len - half);
		}
		return head.next;
	}

	public static Node shuffleLinkedList3(Node first) {
		int[] a = List2Array(first);
		study.chapter1.Shuffle.shuffle(a);
		return Array2List(a);
	}

	private static Node Array2List(int[] a) {
		Node head = new Node(0);
		Node p = head;
		for (int i = 0; i < a.length; i++) {
			Node newNode = new Node(a[i]);
			p.next = newNode;
			p = newNode;
		}
		return head.next;
	}

	private static int[] List2Array(Node first) {
		Node p = first;
		int len = 1;
		Node next = first.next;
		while (next != null) {
			next = next.next;
			len++;
		}
		int[] a = new int[len];
		for (int i = 0; i < len; i++) {
			a[i] = p.val;
			p = p.next;
		}
		return a;
	}

	public static int[][] ShuffleTest(int M, int N) {
		int[] a = new int[M];
		int[][] b = new int[M][M];
		for (int j = 0; j < N; j++) {
			for (int i = 0; i < M; i++) {
				a[i] = i;
			}
			Node first = Array2List(a);
			first = shuffleLinkedList3(first);
			a = List2Array(first);
			for (int i = 0; i < M; i++) {
				b[a[i]][i]++;
			}
		}
		return b;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.deepToString(ShuffleTest(5, 100000)));
		System.out.println(100000 / 5);
	}

}

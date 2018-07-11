package exercise.chapter2;

import java.util.Random;

import study.chapter1.Queue;

public class EX2217SortLinkedList {

	static class ListNode<T extends Comparable<? super T>> {
		T val;
		ListNode<T> next;

		ListNode(T t) {
			val = t;
			next = null;
		}
	}

	public static <T extends Comparable<? super T>> void SortLinkedList(
			ListNode<T> head) {
		Queue<ListNode<T>> q = new Queue<ListNode<T>>();
		getPartition(head, q);
		q = Merge(q);
		head = q.dequeue();
	}

	private static <T extends Comparable<? super T>> Queue<ListNode<T>> Merge(
			Queue<ListNode<T>> q) {
		if (q.size() == 1) {
			return q;
		}
		Queue<ListNode<T>> nq = new Queue<ListNode<T>>();
		while (!q.isEmpty()) {
			ListNode<T> head1 = q.dequeue();
			if (!q.isEmpty()) {
				ListNode<T> head2 = q.dequeue();
				ListNode<T> head = LinkMerge(head1, head2);
				nq.enqueue(head);
			} else {
				nq.enqueue(head1);
			}
		}
		return Merge(nq);
	}

	private static <T extends Comparable<? super T>> ListNode<T> LinkMerge(
			ListNode<T> head1, ListNode<T> head2) {
		ListNode<T> result;
		if (head1 == null)
			return head2;
		if (head2 == null)
			return head1;
		if (head1.val.compareTo(head2.val) <= 0) {
			result = head1;
			result.next = LinkMerge(head1.next, head2);
		} else {
			result = head2;
			result.next = LinkMerge(head1, head2.next);
		}
		return result;
	}

	private static <T extends Comparable<? super T>> void getPartition(
			ListNode<T> head, Queue<ListNode<T>> q) {
		q.enqueue(head);
		if (head.next == null)
			return;
		ListNode<T> p = head;
		while (p.next != null) {
			if (p.next.val.compareTo(p.val) < 0) {
				ListNode<T> partition = p;
				q.enqueue(p.next);
				p = p.next;
				partition.next = null;
			} else {
				p = p.next;
			}
		}
	}

	public static void main(String[] args) {
		ListNode<Integer> head = new EX2217SortLinkedList.ListNode<Integer>(0);
		ListNode<Integer> p = head;
		Random random = new Random();

		for (int i = 0; i < 50; i++) {
			ListNode<Integer> newNode = new ListNode<Integer>(
					random.nextInt(100));
			p.next = newNode;
			p = newNode;
		}
		SortLinkedList(head);
		for (ListNode<Integer> node = head; node != null; node = node.next) {
			System.out.print(node.val + " ");
		}

	}

}

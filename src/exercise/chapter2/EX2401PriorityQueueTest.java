package exercise.chapter2;

public class EX2401PriorityQueueTest {

	public static void main(String[] args) {
		study.chapter2.IndexMinPQ<Character> pq = new study.chapter2.IndexMinPQ<Character>(
				20);
		String test = "P R I O * R * * I * T * Y * * * Q U E * * * U * E";
		int n = 0;
		for (char c : test.toCharArray()) {
			if (c == '*') {
				System.out.print(pq.min() + " ");
				pq.delMin();
			} else if (c != ' ') {
				n++;
				pq.insert(n,c);
			}
		}

	}

}

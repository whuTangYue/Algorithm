package exercise.chapter3;

import depend.In;
import study.chapter1.Queue;
import study.chapter3.BinarySearchST;

public class EX3119FrequencyCounter {

	public static void main(String[] args) {
		int distinct = 0;
		int words = 0;
		int minlen = 8;
		In in = new In("src/data/tale.txt");
		BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
		while (!in.isEmpty()) {
			String key = in.readString();
			if (key.length() < minlen)
				continue;
			words++;
			if (st.contains(key)) {
				st.put(key, st.get(key) + 1);
			} else {
				st.put(key, 1);
				distinct++;
			}
		}
		String max = "";
		st.put(max, 0);
		Queue<String> q = new Queue<String>();
		for (String word : st.keys()) {
			if (st.get(word) > st.get(max)) {
				max = word;
			}
		}
		for (String word : st.keys()) {
			if (st.get(word) == st.get(max)) {
				q.enqueue(word);
			}
		}
		System.out.println(q + " " + st.get(max));
		System.out.println("distinct = " + distinct);
		System.out.println("words    = " + words);

	}

}

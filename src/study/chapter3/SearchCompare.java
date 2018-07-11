package study.chapter3;

import java.util.Random;

import depend.In;

public class SearchCompare {
	public static long time(SymbolTable<String,Integer> st) {
		long time1 = System.currentTimeMillis();
		FrequencyCounter(st);

		long time2 = System.currentTimeMillis();
		return (time2 - time1);
	}
	public static long time_int(SymbolTable<Integer,Integer> st) {
		long time1 = System.currentTimeMillis();
		FrequencyCounterInt(st);

		long time2 = System.currentTimeMillis();
		return (time2 - time1);
	}
	
	public static void FrequencyCounterInt(SymbolTable<Integer,Integer> st) {
		int N = 100000;
		long distinct = 0;
		long words = 0;
		Random random = new Random();
		while (words<N) {
			Integer key = random.nextInt(N/10);
			words++;
			if (st.contains(key)) {
				st.put(key, st.get(key) + 1);
			} else {
				st.put(key, 1);
				distinct++;
			}
		}
		int max = 0;
		st.put(max, 0);
		for (Integer word : st.keys()) {
			if (st.get(word) > st.get(max)) {
				max = word;
			}
		}
		System.out.println(max + " " + st.get(max));
		System.out.println("distinct = " + distinct);
		System.out.println("words    = " + words);
	}

	public static void FrequencyCounter(SymbolTable<String, Integer> st) {
		int distinct = 0;
		int words = 0;
		int minlen = 8;
		In in = new In("src/data/tale.txt");
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
		for (String word : st.keys()) {
			if (st.get(word) > st.get(max)) {
				max = word;
			}
		}
		System.out.println(max + " " + st.get(max));
		System.out.println("distinct = " + distinct);
		System.out.println("words    = " + words);
	}

	public static void main(String[] args) {
		study.chapter3.BinarySearchST<Integer, Integer> bst = new study.chapter3.BinarySearchST<Integer, Integer>();
		study.chapter3.SequentialSearchST<Integer, Integer> sst = new study.chapter3.SequentialSearchST<Integer, Integer>();
		System.out.println(time_int(bst));
		System.out.println(time_int(sst));

	}

}

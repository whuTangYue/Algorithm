package study.chapter3;

import depend.In;
import depend.Stopwatch;
import depend.VisualAccumulator;

public class FrequencyCounter {

	public static String frequencyCounter(SymbolTable<String, Integer> st,
			int minlen, String title) {
		int maxNum = 50000;
		double maxTime = 1000;
		String xAxisLabel = "calls to get() or put()";
		String yAxisLabel = "running time";

		VisualAccumulator visualAcc = new VisualAccumulator(0, maxNum, maxTime,
				title, xAxisLabel, yAxisLabel);
		double totalTime = 0;
		Stopwatch timer;
		In in = new In("src/data/tale.txt");
		while (!in.isEmpty()) {
			String key = in.readString();
			if (key.length() < minlen)
				continue;
			if (st.contains(key)) {
				timer = new Stopwatch();
				int freq = st.get(key);
				totalTime += timer.elapsedTime() * 1000;
				visualAcc.addDataValue(totalTime,false);
				timer = new Stopwatch();
				st.put(key, freq + 1);
				totalTime += timer.elapsedTime() * 1000;
				visualAcc.addDataValue(totalTime,false);
			} else {
				timer = new Stopwatch();
				st.put(key, 1);
				totalTime += timer.elapsedTime() * 1000;
				visualAcc.addDataValue(totalTime,false);
			}
		}
		String max = "";
		timer = new Stopwatch();
		st.put(max, 0);
		totalTime += timer.elapsedTime() * 1000;
		visualAcc.addDataValue(totalTime,false);
		for (String word : st.keys()) {
			timer = new Stopwatch();
			int freq = st.get(word);
			totalTime += timer.elapsedTime() * 1000;
			visualAcc.addDataValue(totalTime,false);
			timer = new Stopwatch();
			int maxfreq = st.get(max);
			totalTime += timer.elapsedTime() * 1000;
			if (freq > maxfreq)
				max = word;
		}
		timer = new Stopwatch();
		int maxfreq = st.get(max);
		totalTime += timer.elapsedTime() * 1000;
		visualAcc.addDataValue(totalTime,false);
		
		visualAcc.writeLastComputedValue();
		return max + " " + maxfreq+" "+totalTime;
	}

	public static void main(String[] args) {
		RedBlackBST<String, Integer> st = new RedBlackBST<String, Integer>();
		//frequencyCounter(st, 8,"bst");
		System.out.println(frequencyCounter(st, 8,"bst"));
	}

}

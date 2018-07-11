package study.chapter3;

import depend.In;
import depend.StdIn;

public class LookupCSV {
	// do not instantiate
	private LookupCSV() {}

	public static void main(String[] args) {
		int keyField = 0;
		int valField = 1;
		
		ST<String,String> st = new ST<String,String>();
		
		In in = new In("src/data/ip.csv");
		while(in.hasNextLine()) {
			String line = in.readLine();
			String[] tokens = line.split(",");
			String key = tokens[keyField];
			String val = tokens[valField];
			st.put(key, val);
		}
		while(!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if(st.contains(s)) System.out.println(st.get(s));
			else System.out.println("not found");
		}

	}

}

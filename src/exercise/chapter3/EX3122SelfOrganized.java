package exercise.chapter3;

public class EX3122SelfOrganized<K, V> extends study.chapter3.ArrayST<K, V> {
	public V get(K key) {
		for (int i = 0; i < n; i++)
			if (keys[i].equals(key)) {
				V v = vals[i];
				K k = keys[i];
				for(int j =0;j<i;j++) {
					vals[j+1]=vals[j];
					keys[j+1]=keys[j];
				}
				vals[0]=v;
				keys[0]=k;
				return v;
			}
		return null;
	}

	public static void main(String[] args) {
		EX3122SelfOrganized<String, Integer> st = new EX3122SelfOrganized<String, Integer>();
		int n = 10;
		for (int i = 0; i < n; i++) {
			String key = Integer.toString(n - i);
			st.put(key, i);
		}
		for (String s : st.keys())
			System.out.println(s + " " + st.get(s));

	}

}

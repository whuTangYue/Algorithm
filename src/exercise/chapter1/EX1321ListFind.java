package exercise.chapter1;

import java.util.Iterator;

public class EX1321ListFind {

	public static boolean find(linkedList<String> list, String key) {
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().equals(key))
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		linkedList<String> list = new linkedList<String>();
		String string = "to be or not to be";
		String[] strings = string.split(" ");
		for (int i = 0; i < strings.length; i++) {
			String item = strings[i];
			list.add(item);
		}
		System.out.println(list);
		System.out.println(find(list,"e"));

	}

}

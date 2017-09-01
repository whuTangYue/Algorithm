package exercise.chapter1;

import java.util.Iterator;

public class EX1326ListRemove {
	
	public static void remove(linkedList<String> list,String key){
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().equals(key))
				iterator.remove();
		}
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
		remove(list,"be");
		System.out.println(list);

	}

}

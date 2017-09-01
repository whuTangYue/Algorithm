package exercise.chapter1;

import java.util.Iterator;

public class EX1340MoveToFront {
	
	public static void MoveToFront(linkedList<String> list,String s){
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().equals(s))
				iterator.remove();
		}
		list.addFirst(s);
	}

	public static void main(String[] args) {
		linkedList<String> list = new linkedList<String>();
		String string = "to be or not to be";
		String[] strings = string.split(" ");
		for (int i = 0; i < strings.length; i++) {
			String item = strings[i];
			MoveToFront(list,item);
		}
		System.out.println(list);

	}

}

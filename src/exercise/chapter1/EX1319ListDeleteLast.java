package exercise.chapter1;


public class EX1319ListDeleteLast {

	public static void main(String[] args) {
		linkedList<String> list = new linkedList<String>();
		String string = "to be or not to be";
		String[] strings = string.split(" ");
		for (int i = 0; i < strings.length; i++) {
			String item = strings[i];
			list.add(item);
		}
		System.out.println(list);

		list.delete(list.length());
		System.out.println(list);
	}

}

package exercise.chapter1;

public class EX1327ListMax {
	
	public static int max(linkedList<Integer> list){
		int max = 0;
		for(int i : list){
			if (i>max) max = i;
		}
		return max;
		
	}

	public static void main(String[] args) {
		linkedList<Integer> list = new linkedList<Integer>();
		list.add(1);
		list.add(10);
		list.add(20);
		list.add(-5);
		System.out.println(max(list));

	}

}

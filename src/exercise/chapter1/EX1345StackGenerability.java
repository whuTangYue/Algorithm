package exercise.chapter1;

import java.util.ArrayList;
import java.util.Arrays;

public class EX1345StackGenerability {
	public static boolean CheckInputSeq(String[] inputSeq) {
		int sum = 0;
		for (int i = 0; i < inputSeq.length; i++) {
			String s = inputSeq[i];
			if (!s.equals("-")) {
				sum++;
			} else {
				sum--;
				if (sum < 0)
					return false;
			}
		}
		return true;

	}

	public static <Item> boolean CheckStackGenerability(ArrayList<Item> pushArray, ArrayList<Item> popArray) {
		if (pushArray.size() < popArray.size())
			return false;
		Stack<Item> s = new Stack<Item>();
		int j = 0;
		for (int i = 0; i < pushArray.size(); i++) {
			s.push(pushArray.get(i));
			while (!s.isEmpty() && s.peek() == popArray.get(j)) {
				s.pop();
				j++;
			}
		}
		return s.size() == pushArray.size() - popArray.size();
	}

	public static <Item> String GetGenerateInput(ArrayList<Item> pushArray, ArrayList<Item> popArray) {
		if (CheckStackGenerability(pushArray, popArray)) {
			StringBuilder input = new StringBuilder();
			Stack<Item> s = new Stack<Item>();
			int j = 0;
			for (int i = 0; i < pushArray.size(); i++) {
				s.push(pushArray.get(i));
				input.append(pushArray.get(i) + " ");
				while (!s.isEmpty() && s.peek() == popArray.get(j)) {
					s.pop();
					input.append("- ");
					j++;
				}
			}
			return input.toString();
		} else
			return null;
	}

	public static void main(String[] args) {
		String input = "1 2 3 4 5 - 6 - - 7 - - - 8 9 - - -";
		String[] inputseq = input.split(" ");
		System.out.println(input);
		System.out.println(CheckInputSeq(inputseq) + " " + (CheckInputSeq(inputseq) ? "不会溢出" : "会溢出"));

		ArrayList<Integer> pushArray = new ArrayList<Integer>();
		ArrayList<Integer> popArray = new ArrayList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < inputseq.length; i++) {
			String s = inputseq[i];
			int n = 0;
			if (!s.equals("-")) {
				try {
					n = Integer.valueOf(s).intValue();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				stack.push(n);
				pushArray.add(n);
			} else if (!stack.isEmpty())
				popArray.add(stack.pop());
		}

		System.out.println(pushArray);
		System.out.println(popArray);

		System.out.println(CheckStackGenerability(pushArray, popArray) + " "
				+ (CheckStackGenerability(pushArray, popArray) ? "可生成" : "不可生成"));

		System.out.println(GetGenerateInput(pushArray, popArray));
		
		Integer[] list = {4,6,8,7,5,3,2,9,0,1};
		ArrayList<Integer> testArray = new ArrayList<Integer>(Arrays.asList(list));
		System.out.println(testArray);
		
		System.out.println(CheckStackGenerability(pushArray, testArray) + " "
				+ (CheckStackGenerability(pushArray, testArray) ? "可生成" : "不可生成"));

		System.out.println(GetGenerateInput(pushArray, testArray));
		

	}

}

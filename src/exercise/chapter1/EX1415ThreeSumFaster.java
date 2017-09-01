package exercise.chapter1;

import java.util.ArrayList;

public class EX1415ThreeSumFaster {

	// public static ArrayList<ArrayList<Integer>> TwoSumFaster(int[] nums, int
	// target) {
	// ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
	// Arrays.sort(nums);
	// int left = 0;
	// int right = nums.length - 1;
	// while (left < right) {
	// int sum = nums[left] + nums[right];
	// if (sum < target) {
	// left++;
	// } else if (sum > target) {
	// right--;
	// } else {
	// TwoSumAdd(list, nums, left, right);
	// if (nums[left + 1] == nums[left])
	// TwoSumAdd(list, nums, left + 1, right);
	// if (nums[right - 1] == nums[right])
	// TwoSumAdd(list, nums, left, right - 1);
	// left++;
	// right--;
	// }
	// }
	// return list;
	// }
	//
	// private static void TwoSumAdd(ArrayList<ArrayList<Integer>> list, int[]
	// nums, int left, int right) {
	// ArrayList<Integer> twosum = new ArrayList<Integer>();
	// twosum.add(left + 1);
	// twosum.add(right + 1);
	// list.add(twosum);
	// }

	public static ArrayList<ArrayList<Integer>> TwoSumFaster(int[] nums, int target) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		Map map = new Map();
		for (int i = 0; i < nums.length; i++) {
			int n = nums[i];
			ArrayList<Integer> matchs = map.searchValue(target - n);
			if (!matchs.isEmpty()) {
				for (int j : matchs) {
					ArrayList<Integer> twosum = new ArrayList<Integer>();
					twosum.add(j + 1);
					twosum.add(i + 1);
					list.add(twosum);
				}
			}
			map.insertItem(i, n);
		}

		return list;
	}

	public static ArrayList<ArrayList<Integer>> ThreeSumFaster(int[] nums, int target) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		Map map = new Map();
		for (int i = 0; i < nums.length - 1; i++) {
			int n = nums[i];
			for (int j = i + 1; j < nums.length; j++) {
				int m = nums[j];
				ArrayList<Integer> matchs = map.searchValue(target - n - m);
				if (!matchs.isEmpty()) {
					for (int p : matchs) {
						ArrayList<Integer> twosum = new ArrayList<Integer>();
						twosum.add(i + 1);
						twosum.add(p + 1);
						twosum.add(j + 1);
						list.add(twosum);
					}
				}
				
				map.insertItem(j, m);
			}

			map.clear();
		}

		return list;
	}

	public static void main(String[] args) {
		int nums[] = { -1, 0, 1, 2, 2, -1, -4 };
		System.out.println(TwoSumFaster(nums, 0));
		System.out.println(ThreeSumFaster(nums, 0));

	}

}

class Map {
	private int N;
	private List[] list;

	private class Node {
		int index;
		int value;
		Node next;

		Node(int index, int value) {
			this.index = index;
			this.value = value;
		}
	}

	private class List {
		Node head;
		Node tail;
	}

	public Map() {
		this(100);
	}

	public Map(int ListNum) {
		N = ListNum;
		list = new List[ListNum];
		for (int i = 0; i < N; i++)
			list[i] = new List();
	}
	public void clear(){
		for (int i = 0; i < N; i++)
			list[i] = new List();
	}

	public void insertItem(int index, int value) {
		int key = (int) (Integer.toUnsignedLong(value) % N);
		Node node = new Node(index, value);
		if (list[key].head == null) {
			list[key].head = node;
			list[key].tail = node;
		} else {
			list[key].tail.next = node;
			list[key].tail = node;
		}
	}

	public ArrayList<Integer> searchValue(int value) {
		int key = (int) (Integer.toUnsignedLong(value) % N);
		ArrayList<Integer> array = new ArrayList<Integer>();

		Node node = list[key].head;
		while (node != null) {
			if (value == node.value) {
				array.add(node.index);
			}
			node = node.next;
		}

		return array;
	}
}

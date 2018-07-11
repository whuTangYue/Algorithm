package exercise.chapter2;

import study.chapter2.Sort;

public class EX2116Check {
	public static <T extends Comparable<? super T>> boolean check() {
		String[] a = new String[8];
		for (int i = 0; i < 8; i++) {
			a[i] = i + " ";
		}
		Sort.shuffle(a);
		String[] b = a.clone();
		SelectionSort(a);
		assert Sort.isSorted(a);
		for (int i = 0; i < a.length; i++) {
			boolean found = false;
			for (int j = 0; j < a.length; j++) {
				if (a[i] == b[j]) {
					found = true;
					break;
				}
			}
			if (!found) {
				return false;
			}
		}
		return true;
	}

	//选择排序交换部分使用新建对象的方法，用于测试check()
	public static void SelectionSort(String[] s) {
		for (int i = 0; i < s.length; i++) {
			int min = i;
			for (int j = i + 1; j < s.length; j++) {
				if (s[j].compareTo(s[min]) < 0) {
					min = j;
				}
			}
			String temp = new String(s[i].toCharArray());
			s[i] = s[min];
			s[min] = temp;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(check());

	}
}

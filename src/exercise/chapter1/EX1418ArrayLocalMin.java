package exercise.chapter1;

public class EX1418ArrayLocalMin {
	
	public static int arrayLocalMin(int[] nums) {
		int lo = 0;
		if (nums.length == 2)
			return nums[0] < nums[1] ? 0 : 1;
		int hi = nums.length - 1;
		int mid = lo + (hi - lo) / 2;
		while (hi - lo > 1) {
			mid = lo + (hi - lo) / 2;
			if (nums[mid] < nums[mid - 1] && nums[mid] < nums[mid + 1])
				return mid;
			else if (nums[mid - 1] < nums[mid + 1])
				hi = mid;
			else if (nums[mid - 1] > nums[mid + 1])
				lo = mid;
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] a = new int[] { 1, 0, 3 };
		int i = arrayLocalMin(a);
		System.out.println(i);
		if (i != -1)
			System.out.println(a[arrayLocalMin(a)]);

	}

}

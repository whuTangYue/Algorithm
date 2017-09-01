package study.chapter1;



public class BinarySearch {
	public static int deep=0;
	public static int rank(int key,int[] a){
		int lo = 0;
		int hi = a.length - 1;
		while (lo<=hi)
		{
			int mid = lo +(hi - lo)/2;
			if      (key <a[mid]) hi = mid -1;
			else if (key >a[mid]) lo = mid +1;
			else return mid;
		}
		return -1;
	}
	public static int rank(int key,int[] a,int lo,int hi){
		if(lo<=hi)
		{
			while (lo<=hi)
			{
			int mid = lo +(hi - lo)/2;
			if      (key <a[mid]) hi = mid -1;
			else if (key >a[mid]) lo = mid +1;
			else return mid;
			}
		}
		return -1;
	}
	public static int rank_r(int key,int[] a){
		return rank_r(key,a,0,a.length-1);
	}
	private static int rank_r(int key,int[] a,int lo,int hi){
		if(lo<=hi)
		{
			deep++;
			int mid=lo+(hi-lo)/2;
			if      (key <a[mid]) return rank_r(key,a,lo,mid-1);
			else if (key >a[mid]) return rank_r(key,a,mid+1,hi);
			else return mid;
		}
		return -1;
	}
	public static void main(String[] args){
		int[] array={1,3,5,7,9};
        int location=rank(3,array);
        if(location<0)
            System.out.println("key not found");
        else
            System.out.println("key location="+(location+1));
	}
}

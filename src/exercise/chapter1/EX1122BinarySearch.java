package exercise.chapter1;

//BinarySearch 每当rank被调用时，打印出lo和hi并按递归深度缩进。
public class EX1122BinarySearch {

	public static void main(String[] args) {
		int[] array={1,3,5,7,9};
        int location=rank(3,array);
        if(location<0)
            System.out.println("key not found");
        else
            System.out.println("key location="+(location+1));

	}

	public static int deep=0;
	public static int rank(int key,int[] a){
		return rank(key,a,0,a.length-1);
	}
	private static int rank(int key,int[] a,int lo,int hi){
		for(int i=0;i<deep;++i)
            System.out.print(" ");
        System.out.println("low: "+lo+" high: "+hi);
		if(lo<=hi)
		{
			deep++;
			int mid=lo+(hi-lo)/2;
			if      (key <a[mid]) return rank(key,a,lo,mid-1);
			else if (key >a[mid]) return rank(key,a,mid+1,hi);
			else return mid;
		}
		return -1;
	}

}

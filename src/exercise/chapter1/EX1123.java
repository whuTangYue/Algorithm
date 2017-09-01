package exercise.chapter1;
import java.util.Arrays;

import exercise.depend.In;
import exercise.depend.StdIn;

// ΪBinarySearch �����������һ��������+��ӡ����׼�����в��ڰ������е�ֵ��-��ӡ���ڰ������е�ֵ
//eclipse
//in run/debug settings
//set program argument to    largeW.txt +/- 
//set input file to largeT.txt
public class EX1123 {

	public static void main(String[] args) {
		int[] whitelist = new In(args[0]).readAllInts();
		Arrays.sort(whitelist);
		if(args[1].equals("+"))
		{
			while (!StdIn.isEmpty())
			{
				int key = StdIn.readInt();
				if (rank(key,whitelist)<0)
				System.out.println(key+"");
			}
		}
		else if(args[1].equals("-"))
		{
			while (!StdIn.isEmpty())
			{
				int key = StdIn.readInt();
				if (rank(key,whitelist)>=0)
				System.out.println(key+"");
			}
		}

	}
	
	public static int rank(int key,int[] a){
		int lo = 0;
		int hi = a.length - 1;
		while (lo<=hi)
		{
			int mid = lo +((hi - lo)/2);
			if      (key <a[mid]) hi = mid -1;
			else if (key >a[mid]) lo = mid +1;
			else return mid;
		}
		return -1;
	}

}

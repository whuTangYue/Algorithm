package exercise.chapter1;

//histogram
//����һ�����������һ������M������һ����СΪM�����飬���е�i��Ԫ�ص�ֵΪi�������г��ֵĴ���
public class EX1115histogram {

	public static void main(String[] args) {
		int[] array={1,2,3,1,2,8,9,5,6,6,6,6};
		printArray(histogram(array,8));

	}
	
	private static int[] histogram(int[] a,int M){
		 int[] hArray=new int[M];
	        for(int i=0;i<M;i++){
	            int count=0;
	            for(int j=0;j<a.length;++j){
	                if(i==a[j]){
	                    count++;
	                }
	            }
	            hArray[i]=count;
	        }
	        return hArray;
	}
	private static void printArray(int[] a) {
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
	}

}

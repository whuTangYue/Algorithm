package exercise.chapter1;

//print out a bool Array
public class EX1111PrintBoolArray {
	public static void main(String[] args) {
		boolean[][] boolArray={{true,false,false},{false,false,true},{true,false,true}};
		
		
		System.out.println(ex(boolArray));
	}
	
	private static String ex(boolean[][] array){
		String a="";
        a+=" ";
        for(int i=0;i<array[0].length;++i){
            a+=i+1;
        }
        a+="\n";
        
        for(int i=0;i<array.length;++i){
            //print the row number
            a+=i+1;
            //print the boolean element
            for(int j=0;j<array[i].length;++j){
                if(array[i][j]==true)
                    a+="*";
                else
                    a+=" ";
            }
            a+="\n";
        }
        return a;
	}

}

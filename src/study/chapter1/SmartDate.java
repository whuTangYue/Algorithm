package study.chapter1;
import java.text.SimpleDateFormat;

public class SmartDate extends Date{

	public SmartDate(int m, int d, int y) {
		super(m,d,y);
		if(!this.isValidDate()) throw new RuntimeException("inValid Date!");
	}
	public boolean isValidDate(){
		SimpleDateFormat format= new SimpleDateFormat("yyyy/MM/dd");
		boolean valid=true;
		try{
			format.setLenient(false);
			format.parse(this.toString());
		}catch(Exception e){valid=false;}
		return valid;
	}

	public static void main(String[] args) {
		SmartDate date=new SmartDate(2016,7,31);
		System.out.println(date);

	}

}

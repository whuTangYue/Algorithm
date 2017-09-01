package study.chapter1;

public class DateTest{
	public static void datetest(Datable date){System.out.println(date);}

	public static void main(String[] args) {
		Testdate testdate=new Testdate(2016,7,23);
		datetest(testdate);

	}

}
class Testdate extends Date{
	public Testdate(int m, int d, int y) {
		super(m, d, y);
		// TODO Auto-generated constructor stub
	}
	public String toString()
	{return month()+"."+day()+"."+year();}
}
package study.chapter1;
public interface Datable
{
	int month();
	int day();
	int year();
	public String toString();
	public boolean equals(Object x);
}
class Date implements Datable{
	 protected final int month;
	 protected final int day;
	 protected final int year;
	 
	 public Date(int m,int d,int y)
	 {month=m;day=d;year=y;}
	 public Date(String date){
		 String[] fields = date.split("/");
		 month = Integer.parseInt(fields[0]);
		 day = Integer.parseInt(fields[1]);
		 year = Integer.parseInt(fields[2]);
	 }

	
	public int month() {
		return month;
	}

	public int day() {
		return day;
	}

	public int year() {
		return year;
	}
	public String toString()
	{return month()+"/"+day()+"/"+year();}
	public boolean equals(Object x)
	{
		if(this==x) return true;
		if(x==null) return false;
		if(this.getClass()!=x.getClass()) return false;
		Date that=(Date) x;
		if(this.day!=that.day) return false;
		if(this.month!=that.day) return false;
		if(this.year!=that.day) return false;
		return true;
	}

}

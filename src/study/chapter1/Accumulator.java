package study.chapter1;

public class Accumulator {
	private double m;
	private double s;
	private int N;

	public void addDataValue(double x){
		N++;
		s=s+1.0*N/(N-1)*(x-m)*(x-m);
		m=m+(x-m)/N;
	}
	public double mean()
	{return m;}
	public double var()
	{return s/(N-1);}
	public double stddev()
	{return Math.sqrt(this.var());}
	public String toString(){
		return "Mean ("+N+" values):"
				+String.format("%7.5f", mean());
	}
	
	public static void main(String[] args) {
		Accumulator a = new Accumulator();
		for(int t = 0;t<1000;t++)
			a.addDataValue(Math.random());
		System.out.println(a);
	}

}

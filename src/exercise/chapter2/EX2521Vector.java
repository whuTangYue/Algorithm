package exercise.chapter2;

import java.util.Arrays;

public class EX2521Vector implements Comparable<EX2521Vector> {
	private final int n; // length of the vector
	private double[] data; // array of vector's components

	// create the zero vector of length n
	public EX2521Vector(int n) {
		this.n = n;
		this.data = new double[n];
	}

	// create a vector from an array
	/*
	public EX2521Vector(double[] data) {
		n = data.length;

		// defensive copy so that client can't alter our copy of data[]
		this.data = new double[n];
		for (int i = 0; i < n; i++)
			this.data[i] = data[i];
	}
	*/
	public EX2521Vector(double... data) {
        n = data.length;

        // defensive copy so that client can't alter our copy of data[]
        this.data = new double[n];
        for (int i = 0; i < n; i++)
            this.data[i] = data[i];
    }

	// return the length of the vector
	public int length() {
		return n;
	}

	// return a string representation of the vector
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append('(');
		for (int i = 0; i < n; i++) {
			s.append(data[i]);
			if (i < n - 1)
				s.append(", ");
		}
		s.append(')');
		return s.toString();
	}

	@Override
	public int compareTo(EX2521Vector that) {
		for (int i = 0; i < n; i++) {
			if (this.data[i] < that.data[i])
				return -1;
			else if (this.data[i] > that.data[i])
				return +1;
			else
				continue;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		EX2521Vector v1 = new EX2521Vector(0.3,0.2,0.1);
		EX2521Vector v2 = new EX2521Vector(0.2,0.2,0.3);
		EX2521Vector v3 = new EX2521Vector(0.2,0.2,0.1);
		EX2521Vector v4 = new EX2521Vector(0.1,0.2,0.1);
		EX2521Vector[] list = {v1,v2,v3,v4};
		Arrays.sort(list);
		System.out.println(Arrays.toString(list));
	}

}

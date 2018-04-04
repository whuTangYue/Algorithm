package exercise.chapter1;

import study.chapter1.Euclid;

//Rational
//为有理数实现一个不可变数据类型，支持加减乘除操作
public class EX1216Rational {
	public static void main(String[] args) {
		Rational a = new Rational(1, 2);
		Rational b = new Rational(3, 4);
		System.out.println(a.plus(b).toString());

	}
}

class Rational {
	private final int numerator;
	private final int denominator;

	public Rational(int numerator, int denominator) {
		if (denominator == 0) {
			throw new RuntimeException("Denominator cannot be zero");
		}
		int gcd = Euclid.gcd(numerator, denominator);
		this.numerator = numerator / gcd;
		this.denominator = denominator / gcd;
	}

	public int numerator() {
		return numerator;
	}

	public int denominator() {
		return denominator;
	}

	// return double precision representation of this rational number
	public double toDouble() {
		return (double) numerator / denominator;
	}

	// return string representation of this rational number
	public String toString() {
		if (denominator == 1)
			return numerator + "";
		else
			return numerator + "/" + denominator;
	}

	public Rational plus(Rational that) {
		long num = this.numerator() * that.denominator() + this.denominator() * that.numerator();
		if (num >= Long.MAX_VALUE && num <= Long.MIN_VALUE)
			throw new RuntimeException("Error:Overflow");
		long den = this.denominator() * that.denominator();
		long gcd = Euclid.gcd(num, den);
		num = num / gcd;
		den = den / gcd;
		int numerator;
		int denominator;
		if (num <= Integer.MAX_VALUE && num >= Integer.MIN_VALUE)
			numerator = (int) num;
		else
			throw new RuntimeException("Error:Overflow");
		if (den <= Integer.MAX_VALUE && den >= Integer.MIN_VALUE)
			denominator = (int) den;
		else
			throw new RuntimeException("Error:Overflow");
		Rational plus = new Rational(numerator, denominator);
		return plus;
	}


}

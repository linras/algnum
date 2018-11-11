package projekt2;

import java.math.BigInteger;

public class Rational extends RealNumber<Rational>{
	public static Rational PI = new Rational(52163L,16604L); // dokładność do 6 miejsc po przecinku
	private BigInteger numer; //numerator- licznik
	private BigInteger denom;	//denominator- mianownik
	
	public Rational() {
		numer = new BigInteger("1");
		denom = new BigInteger("1");
	}
	public Rational(Rational f) {
		this(f.numer,f.denom);
	}
	
	public Rational(long n, long d) {
		numer = new BigInteger(""+n);
		denom = new BigInteger(""+d);
		this.cancelling();
	}
	
	public Rational(BigInteger n, BigInteger d) {
		numer = new BigInteger(n.toString());
		denom = new BigInteger(d.toString());
		this.cancelling();
	}
	public Rational(long n) {
		numer = new BigInteger(""+n);
		denom = BigInteger.ONE;
	}
	
	public BigInteger gcd() {
		//System.out.println(numer + " "+ denom);
		return Rational.gcd(numer.abs(), denom.abs());
	}
	
	public static BigInteger gcd(BigInteger a, BigInteger b) { //greatest common divisor- najw wsp dzielnik
		BigInteger c;
		while(!b.equals(BigInteger.ZERO)) {
			c = a.mod(b);
			a = b;
			b = c;
		}
		return a;
	}
	//algorytm skracania:
	public void cancelling() {
		if (denom.equals(BigInteger.ZERO)) {
			denom = BigInteger.ONE;
		}
		BigInteger gcd = this.gcd();
		numer = numer.divide(gcd);
		denom = denom.divide(gcd);
		
		if(denom.compareTo(BigInteger.ZERO)<0) { //denom <0
			denom= denom.negate();
			numer= numer.negate();
		}
	}
	@Override
	public Rational add(Rational a) {
		BigInteger num=BigInteger.ZERO,den=BigInteger.ZERO;
		//num += this.numer*a.denom + this.denom*a.numer;
		num = num.add(this.numer.multiply(a.denom));
		num = num.add(this.denom.multiply(a.numer));
		//den += this.denom*a.denom;
		den = den.add(this.denom.multiply(a.denom));
		return new Rational(num, den);
	}
	@Override
	public Rational add(long a) {
		BigInteger num=BigInteger.ZERO,den=BigInteger.ZERO;
		//num += this.numer + this.denom*a;
		num = num.add(this.numer).add(this.denom.multiply(new BigInteger(""+a)));
		//den += this.denom;
		den = den.add(this.denom);
		return new Rational(num, den);
	}
	@Override
	public Rational sub(Rational a) {
		BigInteger num=BigInteger.ZERO,den=BigInteger.ZERO;
		//num += this.numer*a.denom - this.denom*a.numer;
		num = num.add(this.numer.multiply(a.denom)).subtract(this.denom.multiply(a.numer));
		//den += this.denom*a.denom;
		den =den.add(this.denom.multiply(a.denom));
		return new Rational(num, den);
	}
	@Override
	public Rational sub(long a) {
		BigInteger num=BigInteger.ZERO,den=BigInteger.ZERO;
		//num += this.numer - this.denom*a;
		num = num.add(this.numer).subtract(this.denom);
		//den += this.denom
		den = den.add(this.denom);
		return new Rational(num, den);
	}
	@Override
	public Rational mul(Rational a) {
		BigInteger num=BigInteger.ZERO,den=BigInteger.ZERO;
		//num += this.numer*a.numer;
		num = num.add(this.numer.multiply(a.numer));
		//den += this.denom*a.denom;
		den = den.add(this.denom.multiply(a.denom));
		return new Rational(num, den);
	}
	@Override
	public Rational mul(long a) {
		BigInteger num=BigInteger.ZERO,den=BigInteger.ZERO;
		num = num.add(this.numer.multiply(new BigInteger(""+a)));
		//num += this.numer*a;
		den = den.add(this.denom);
		//den += this.denom;
		return new Rational(num, den);
	}
	@Override
	public Rational div(Rational a) {
		BigInteger num=BigInteger.ZERO,den=BigInteger.ZERO;
		num = num.add(this.numer.multiply(a.denom));
		//num += this.numer*a.denom;
		den = den.add(this.denom.multiply(a.numer));
		//den += this.denom*a.numer;
		return new Rational(num, den);
	}
	@Override
	public Rational div(long a) {
		BigInteger num=BigInteger.ZERO,den=BigInteger.ZERO;
		//num += this.numer;
		num = num.add(this.numer);
		den = den.add(this.denom.multiply(new BigInteger(""+a)));
		//den += this.denom*a;
		return new Rational(num, den);
	}
	@Override
	public Rational inv() {
		return new Rational(denom, numer);
	}

	@Override
	public String toString() {
		return ""+asDouble();
		//return numer + "/" + denom;
	}
	
	public double asDouble() {
		return numer.doubleValue()/denom.doubleValue();
	}
	public float asFloat() {
		return numer.floatValue()/denom.floatValue();
	}
	@Override
	public Rational[][] returnNew(int pion, int poziom) {
		return new Rational[pion][poziom];
	}
	@Override
	public int eq(Rational a) {
		return this.numer.multiply(a.denom).compareTo(this.denom.multiply(a.numer));
	}
	@Override
	public Rational abs() {
		return new Rational(this.numer.abs(),this.denom);
	}
}
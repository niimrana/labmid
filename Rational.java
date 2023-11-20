
public class Rational {
	private int numerator;
	private int denominator;
	public Rational() {
		this(0);
	}
	public Rational(int numerator, int denominator) {
		if (denominator==0)
		       throw new RuntimeException("Zero denominator");
		else if (denominator<0)
			throw new RuntimeException("Negative denominator");
		this.numerator = numerator;
		this.denominator = denominator;
		this.reduce();
	}
	public Rational(int i) {
		numerator = i;
		denominator = 1;
	}
	public Rational(Rational r) {
		numerator = r.numerator;
		denominator = r.denominator;
	}
	public Rational plus(Rational r) {
		Rational newR = new Rational(this.numerator * r.denominator + this.denominator * r.numerator, this.denominator * r.denominator);
		newR.reduce();
		return newR;
	}
	public Rational times(Rational r) {
		Rational newR = new Rational(this.numerator * r.numerator, this.denominator * r.denominator);
		newR.reduce();
		return newR; 
	}
	public Rational dividedBy(Rational r) {
		Rational newR = new Rational(r.denominator, r.numerator);
		Rational anR = this.times(newR);
		anR.reduce();
		return anR; 
	}
	public float toFloat() {
		return (float)numerator/(float)denominator; 
	}
	private void reduce(){
		// Find GCD of numerator and denominator using Euclidean algorithm
	    int n = numerator;
	    int d = denominator;
	    int x;
	    do {
	    	x = n % d;
	    	n = d;
	    	d = x;
	    }
		while(d > 0);	
	    // Reduce numerator and denominator by dividing them with their gcd
	    numerator = numerator / n;
	    denominator = denominator / n;
	}
	public String toString() {
		String s = numerator + "/" + denominator;
		return s;
	}
	public boolean equals(Rational r) {
		return (this.numerator == r.numerator && this.denominator == r.denominator);
	}
	public int compareTo(Rational r) {
	     int left = this.numerator * r.denominator;
	     int right = this.denominator * r.numerator;
	     if (left < right) 
	    	 return -1;
	     if (left > right) 
	    	 return 1;
	     return 0;
	}
	/*
	public static void main(String[] args) {
        Rational x, y, z;
        
        x = new Rational(1, 5);
        y = new Rational(1, 9);
        z = x.plus(y);
        System.out.println(x.toString() + " + " + y.toString() + " = " + z.toString());
	
        x = new Rational(1, 1000);
        y = new Rational(1, 7000);
        z = x.plus(y);
        System.out.println(x.toString() + " + " + y.toString() + " = " + z.toString());
		
        x = new Rational(8, 5);
        y = new Rational(3, 4);
        z = x.times(y);
        System.out.println(x.toString() + " * " + y.toString() + " = " + z.toString());
	 
        x = new Rational(56789, 1234);
        y = new Rational(12345, 897);
        z = x.times(y);
        System.out.println(x.toString() + " * " + y.toString() + " = " + z.toString());

        x = new Rational(1, 6);
        y = new Rational(4, 9);
        z = x.dividedBy(y);
        System.out.println(x.toString() + " / " + y.toString() + " = " + z.toString());
        
        x = new Rational(3, 8);
        System.out.println(x.toString() + " in float is " + x.toFloat());
        
        x = new Rational(1, 6);
        y = new Rational(4, 9);
        z = x.dividedBy(y);
        System.out.println(x.toString() + " == " + y.toString() + " is " + x.equals(y));
        
        x = new Rational(1, 9);
        y = new Rational(1, 9);
        z = x.dividedBy(y);
        System.out.println(x.toString() + " == " + y.toString() + " is " + x.equals(y));
        
        x = new Rational(1, 6);
        y = new Rational(4, 9);
        z = x.dividedBy(y);
        System.out.println(x.toString() + " when compared to " + y.toString() + " returned " + x.compareTo(y));
        
        x = new Rational(5, 11);
        y = new Rational(4, 9);
        z = x.dividedBy(y);
        System.out.println(x.toString() + " when compared to " + y.toString() + " returned " + x.compareTo(y));
     
    }*/
}
